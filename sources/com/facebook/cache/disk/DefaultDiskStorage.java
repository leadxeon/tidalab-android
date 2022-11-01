package com.facebook.cache.disk;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.file.FileTreeVisitor;
import com.facebook.common.file.FileUtils$CreateDirectoryException;
import com.facebook.common.file.FileUtils$ParentDirNotFoundException;
import com.facebook.common.file.FileUtils$RenameException;
import com.facebook.common.internal.CountingOutputStream;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class DefaultDiskStorage implements DiskStorage {
    public static final /* synthetic */ int $r8$clinit = 0;
    public static final long TEMP_FILE_LIFETIME_MS = TimeUnit.MINUTES.toMillis(30);
    public final CacheErrorLogger mCacheErrorLogger;
    public final Clock mClock;
    public final boolean mIsExternal;
    public final File mRootDirectory;
    public final File mVersionDirectory;

    /* loaded from: classes.dex */
    public class EntriesCollector implements FileTreeVisitor {
        public final List<DiskStorage.Entry> result = new ArrayList();

        public EntriesCollector(AnonymousClass1 r2) {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void postVisitDirectory(File file) {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void preVisitDirectory(File file) {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void visitFile(File file) {
            FileInfo access$000 = DefaultDiskStorage.access$000(DefaultDiskStorage.this, file);
            if (access$000 != null && access$000.type == ".cnt") {
                this.result.add(new EntryImpl(access$000.resourceId, file, null));
            }
        }
    }

    /* loaded from: classes.dex */
    public static class EntryImpl implements DiskStorage.Entry {
        public final String id;
        public final FileBinaryResource resource;
        public long size = -1;
        public long timestamp = -1;

        public EntryImpl(String str, File file, AnonymousClass1 r3) {
            Objects.requireNonNull(str);
            this.id = str;
            this.resource = FileBinaryResource.createOrNull(file);
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public String getId() {
            return this.id;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public long getSize() {
            if (this.size < 0) {
                this.size = this.resource.size();
            }
            return this.size;
        }

        @Override // com.facebook.cache.disk.DiskStorage.Entry
        public long getTimestamp() {
            if (this.timestamp < 0) {
                this.timestamp = this.resource.mFile.lastModified();
            }
            return this.timestamp;
        }
    }

    /* loaded from: classes.dex */
    public static class FileInfo {
        public final String resourceId;
        public final String type;

        public FileInfo(String str, String str2) {
            this.type = str;
            this.resourceId = str2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.type);
            sb.append("(");
            return GeneratedOutlineSupport.outline11(sb, this.resourceId, ")");
        }
    }

    /* loaded from: classes.dex */
    public static class IncompleteFileException extends IOException {
        public IncompleteFileException(long j, long j2) {
            super("File was not written completely. Expected: " + j + ", found: " + j2);
        }
    }

    /* loaded from: classes.dex */
    public class InserterImpl implements DiskStorage.Inserter {
        public final String mResourceId;
        public final File mTemporaryFile;

        public InserterImpl(String str, File file) {
            this.mResourceId = str;
            this.mTemporaryFile = file;
        }

        public boolean cleanUp() {
            return !this.mTemporaryFile.exists() || this.mTemporaryFile.delete();
        }

        public FileBinaryResource commit(Object obj) throws IOException {
            File contentFileFor = DefaultDiskStorage.this.getContentFileFor(this.mResourceId);
            try {
                R$dimen.rename(this.mTemporaryFile, contentFileFor);
                if (contentFileFor.exists()) {
                    Objects.requireNonNull((SystemClock) DefaultDiskStorage.this.mClock);
                    contentFileFor.setLastModified(System.currentTimeMillis());
                }
                return FileBinaryResource.createOrNull(contentFileFor);
            } catch (FileUtils$RenameException e) {
                Throwable cause = e.getCause();
                if (cause != null && !(cause instanceof FileUtils$ParentDirNotFoundException)) {
                    boolean z = cause instanceof FileNotFoundException;
                }
                CacheErrorLogger cacheErrorLogger = DefaultDiskStorage.this.mCacheErrorLogger;
                int i = DefaultDiskStorage.$r8$clinit;
                Objects.requireNonNull((NoOpCacheErrorLogger) cacheErrorLogger);
                throw e;
            }
        }

        /* JADX WARN: Finally extract failed */
        public void writeData(WriterCallback writerCallback, Object obj) throws IOException {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.mTemporaryFile);
                try {
                    CountingOutputStream countingOutputStream = new CountingOutputStream(fileOutputStream);
                    BufferedDiskCache.AnonymousClass6 r5 = (BufferedDiskCache.AnonymousClass6) writerCallback;
                    BufferedDiskCache.this.mPooledByteStreams.copy(r5.val$encodedImage.getInputStream(), countingOutputStream);
                    countingOutputStream.flush();
                    long j = countingOutputStream.mCount;
                    fileOutputStream.close();
                    if (this.mTemporaryFile.length() != j) {
                        throw new IncompleteFileException(j, this.mTemporaryFile.length());
                    }
                } catch (Throwable th) {
                    fileOutputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e) {
                CacheErrorLogger cacheErrorLogger = DefaultDiskStorage.this.mCacheErrorLogger;
                int i = DefaultDiskStorage.$r8$clinit;
                Objects.requireNonNull((NoOpCacheErrorLogger) cacheErrorLogger);
                throw e;
            }
        }
    }

    /* loaded from: classes.dex */
    public class PurgingVisitor implements FileTreeVisitor {
        public boolean insideBaseDirectory;

        public PurgingVisitor(AnonymousClass1 r2) {
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void postVisitDirectory(File file) {
            if (!DefaultDiskStorage.this.mRootDirectory.equals(file) && !this.insideBaseDirectory) {
                file.delete();
            }
            if (this.insideBaseDirectory && file.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                this.insideBaseDirectory = false;
            }
        }

        @Override // com.facebook.common.file.FileTreeVisitor
        public void preVisitDirectory(File file) {
            if (!this.insideBaseDirectory && file.equals(DefaultDiskStorage.this.mVersionDirectory)) {
                this.insideBaseDirectory = true;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:9:0x002b, code lost:
            if (r3 > (java.lang.System.currentTimeMillis() - com.facebook.cache.disk.DefaultDiskStorage.TEMP_FILE_LIFETIME_MS)) goto L_0x0036;
         */
        @Override // com.facebook.common.file.FileTreeVisitor
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void visitFile(java.io.File r10) {
            /*
                r9 = this;
                boolean r0 = r9.insideBaseDirectory
                if (r0 == 0) goto L_0x0039
                com.facebook.cache.disk.DefaultDiskStorage r0 = com.facebook.cache.disk.DefaultDiskStorage.this
                com.facebook.cache.disk.DefaultDiskStorage$FileInfo r0 = com.facebook.cache.disk.DefaultDiskStorage.access$000(r0, r10)
                r1 = 0
                r2 = 1
                if (r0 != 0) goto L_0x000f
                goto L_0x0037
            L_0x000f:
                java.lang.String r0 = r0.type
                java.lang.String r3 = ".tmp"
                if (r0 != r3) goto L_0x002e
                long r3 = r10.lastModified()
                com.facebook.cache.disk.DefaultDiskStorage r0 = com.facebook.cache.disk.DefaultDiskStorage.this
                com.facebook.common.time.Clock r0 = r0.mClock
                com.facebook.common.time.SystemClock r0 = (com.facebook.common.time.SystemClock) r0
                java.util.Objects.requireNonNull(r0)
                long r5 = java.lang.System.currentTimeMillis()
                long r7 = com.facebook.cache.disk.DefaultDiskStorage.TEMP_FILE_LIFETIME_MS
                long r5 = r5 - r7
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                if (r0 <= 0) goto L_0x0037
                goto L_0x0036
            L_0x002e:
                java.lang.String r3 = ".cnt"
                if (r0 != r3) goto L_0x0033
                r1 = 1
            L_0x0033:
                androidx.recyclerview.R$dimen.checkState(r1)
            L_0x0036:
                r1 = 1
            L_0x0037:
                if (r1 != 0) goto L_0x003c
            L_0x0039:
                r10.delete()
            L_0x003c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.cache.disk.DefaultDiskStorage.PurgingVisitor.visitFile(java.io.File):void");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public DefaultDiskStorage(java.io.File r6, int r7, com.facebook.cache.common.CacheErrorLogger r8) {
        /*
            r5 = this;
            r5.<init>()
            r5.mRootDirectory = r6
            r0 = 0
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()     // Catch: Exception -> 0x0020
            if (r1 == 0) goto L_0x0026
            java.lang.String r1 = r1.toString()     // Catch: Exception -> 0x0020
            java.lang.String r6 = r6.getCanonicalPath()     // Catch: IOException -> 0x0019, Exception -> 0x0020
            boolean r6 = r6.contains(r1)     // Catch: IOException -> 0x0019, Exception -> 0x0020
            goto L_0x0027
        L_0x0019:
            r6 = r8
            com.facebook.cache.common.NoOpCacheErrorLogger r6 = (com.facebook.cache.common.NoOpCacheErrorLogger) r6     // Catch: Exception -> 0x0020
            java.util.Objects.requireNonNull(r6)     // Catch: Exception -> 0x0020
            goto L_0x0026
        L_0x0020:
            r6 = r8
            com.facebook.cache.common.NoOpCacheErrorLogger r6 = (com.facebook.cache.common.NoOpCacheErrorLogger) r6
            java.util.Objects.requireNonNull(r6)
        L_0x0026:
            r6 = 0
        L_0x0027:
            r5.mIsExternal = r6
            java.io.File r6 = new java.io.File
            java.io.File r1 = r5.mRootDirectory
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r3 = "v2"
            r2[r0] = r3
            r3 = 100
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4 = 1
            r2[r4] = r3
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r3 = 2
            r2[r3] = r7
            r7 = 0
            java.lang.String r3 = "%s.ols%d.%d"
            java.lang.String r7 = java.lang.String.format(r7, r3, r2)
            r6.<init>(r1, r7)
            r5.mVersionDirectory = r6
            r5.mCacheErrorLogger = r8
            java.io.File r7 = r5.mRootDirectory
            boolean r7 = r7.exists()
            if (r7 != 0) goto L_0x005b
            goto L_0x0066
        L_0x005b:
            boolean r7 = r6.exists()
            if (r7 != 0) goto L_0x0067
            java.io.File r7 = r5.mRootDirectory
            androidx.recyclerview.R$dimen.deleteRecursively(r7)
        L_0x0066:
            r0 = 1
        L_0x0067:
            if (r0 == 0) goto L_0x0082
            androidx.recyclerview.R$dimen.mkdirs(r6)     // Catch: FileUtils$CreateDirectoryException -> 0x006d
            goto L_0x0082
        L_0x006d:
            com.facebook.cache.common.CacheErrorLogger r6 = r5.mCacheErrorLogger
            java.lang.String r7 = "version directory could not be created: "
            java.lang.StringBuilder r7 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r7)
            java.io.File r8 = r5.mVersionDirectory
            r7.append(r8)
            r7.toString()
            com.facebook.cache.common.NoOpCacheErrorLogger r6 = (com.facebook.cache.common.NoOpCacheErrorLogger) r6
            java.util.Objects.requireNonNull(r6)
        L_0x0082:
            com.facebook.common.time.SystemClock r6 = com.facebook.common.time.SystemClock.INSTANCE
            r5.mClock = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.cache.disk.DefaultDiskStorage.<init>(java.io.File, int, com.facebook.cache.common.CacheErrorLogger):void");
    }

    public static FileInfo access$000(DefaultDiskStorage defaultDiskStorage, File file) {
        FileInfo fileInfo;
        Objects.requireNonNull(defaultDiskStorage);
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(46);
        if (lastIndexOf > 0) {
            String substring = name.substring(lastIndexOf);
            String str = ".cnt";
            if (!str.equals(substring)) {
                str = ".tmp".equals(substring) ? ".tmp" : null;
            }
            if (str != null) {
                String substring2 = name.substring(0, lastIndexOf);
                if (str.equals(".tmp")) {
                    int lastIndexOf2 = substring2.lastIndexOf(46);
                    if (lastIndexOf2 > 0) {
                        substring2 = substring2.substring(0, lastIndexOf2);
                    }
                }
                fileInfo = new FileInfo(str, substring2);
                if (fileInfo == null && new File(defaultDiskStorage.getSubdirectoryPath(fileInfo.resourceId)).equals(file.getParentFile())) {
                    return fileInfo;
                }
                return null;
            }
        }
        fileInfo = null;
        if (fileInfo == null) {
            return null;
        }
        return fileInfo;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void clearAll() {
        R$dimen.deleteContents(this.mRootDirectory);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean contains(String str, Object obj) {
        return getContentFileFor(str).exists();
    }

    public final long doRemove(File file) {
        if (!file.exists()) {
            return 0L;
        }
        long length = file.length();
        if (file.delete()) {
            return length;
        }
        return -1L;
    }

    public File getContentFileFor(String str) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13(getSubdirectoryPath(str));
        outline13.append(File.separator);
        outline13.append(str);
        outline13.append(".cnt");
        return new File(outline13.toString());
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public Collection getEntries() throws IOException {
        EntriesCollector entriesCollector = new EntriesCollector(null);
        R$dimen.walkFileTree(this.mVersionDirectory, entriesCollector);
        return Collections.unmodifiableList(entriesCollector.result);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public FileBinaryResource getResource(String str, Object obj) {
        File contentFileFor = getContentFileFor(str);
        if (!contentFileFor.exists()) {
            return null;
        }
        Objects.requireNonNull((SystemClock) this.mClock);
        contentFileFor.setLastModified(System.currentTimeMillis());
        return FileBinaryResource.createOrNull(contentFileFor);
    }

    public final String getSubdirectoryPath(String str) {
        String valueOf = String.valueOf(Math.abs(str.hashCode() % 100));
        StringBuilder sb = new StringBuilder();
        sb.append(this.mVersionDirectory);
        return GeneratedOutlineSupport.outline11(sb, File.separator, valueOf);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public DiskStorage.Inserter insert(String str, Object obj) throws IOException {
        File file = new File(getSubdirectoryPath(str));
        if (!file.exists()) {
            try {
                R$dimen.mkdirs(file);
            } catch (FileUtils$CreateDirectoryException e) {
                Objects.requireNonNull((NoOpCacheErrorLogger) this.mCacheErrorLogger);
                throw e;
            }
        }
        try {
            return new InserterImpl(str, File.createTempFile(str + ".", ".tmp", file));
        } catch (IOException e2) {
            Objects.requireNonNull((NoOpCacheErrorLogger) this.mCacheErrorLogger);
            throw e2;
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean isExternal() {
        return this.mIsExternal;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void purgeUnexpectedResources() {
        R$dimen.walkFileTree(this.mRootDirectory, new PurgingVisitor(null));
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(DiskStorage.Entry entry) {
        return doRemove(((EntryImpl) entry).resource.mFile);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(String str) {
        return doRemove(getContentFileFor(str));
    }
}
