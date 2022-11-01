package com.facebook.cache.disk;

import androidx.recyclerview.R$dimen;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.file.FileUtils$CreateDirectoryException;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
/* loaded from: classes.dex */
public class DynamicDefaultDiskStorage implements DiskStorage {
    public final String mBaseDirectoryName;
    public final Supplier<File> mBaseDirectoryPathSupplier;
    public final CacheErrorLogger mCacheErrorLogger;
    public volatile State mCurrentState = new State(null, null);
    public final int mVersion;

    /* loaded from: classes.dex */
    public static class State {
        public final DiskStorage delegate;
        public final File rootDirectory;

        public State(File file, DiskStorage diskStorage) {
            this.delegate = diskStorage;
            this.rootDirectory = file;
        }
    }

    public DynamicDefaultDiskStorage(int i, Supplier<File> supplier, String str, CacheErrorLogger cacheErrorLogger) {
        this.mVersion = i;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mBaseDirectoryPathSupplier = supplier;
        this.mBaseDirectoryName = str;
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void clearAll() throws IOException {
        get().clearAll();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean contains(String str, Object obj) throws IOException {
        return get().contains(str, obj);
    }

    public final void createStorage() throws IOException {
        File file = new File(this.mBaseDirectoryPathSupplier.get(), this.mBaseDirectoryName);
        try {
            R$dimen.mkdirs(file);
            file.getAbsolutePath();
            int i = FLog.$r8$clinit;
            this.mCurrentState = new State(file, new DefaultDiskStorage(file, this.mVersion, this.mCacheErrorLogger));
        } catch (FileUtils$CreateDirectoryException e) {
            Objects.requireNonNull((NoOpCacheErrorLogger) this.mCacheErrorLogger);
            throw e;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0017 A[Catch: all -> 0x0036, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0007, B:7:0x000b, B:13:0x0017, B:15:0x001d, B:17:0x0023, B:18:0x002a, B:19:0x002d), top: B:24:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.facebook.cache.disk.DiskStorage get() throws java.io.IOException {
        /*
            r2 = this;
            monitor-enter(r2)
            com.facebook.cache.disk.DynamicDefaultDiskStorage$State r0 = r2.mCurrentState     // Catch: all -> 0x0036
            com.facebook.cache.disk.DiskStorage r1 = r0.delegate     // Catch: all -> 0x0036
            if (r1 == 0) goto L_0x0014
            java.io.File r0 = r0.rootDirectory     // Catch: all -> 0x0036
            if (r0 == 0) goto L_0x0014
            boolean r0 = r0.exists()     // Catch: all -> 0x0036
            if (r0 != 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            r0 = 0
            goto L_0x0015
        L_0x0014:
            r0 = 1
        L_0x0015:
            if (r0 == 0) goto L_0x002d
            com.facebook.cache.disk.DynamicDefaultDiskStorage$State r0 = r2.mCurrentState     // Catch: all -> 0x0036
            com.facebook.cache.disk.DiskStorage r0 = r0.delegate     // Catch: all -> 0x0036
            if (r0 == 0) goto L_0x002a
            com.facebook.cache.disk.DynamicDefaultDiskStorage$State r0 = r2.mCurrentState     // Catch: all -> 0x0036
            java.io.File r0 = r0.rootDirectory     // Catch: all -> 0x0036
            if (r0 == 0) goto L_0x002a
            com.facebook.cache.disk.DynamicDefaultDiskStorage$State r0 = r2.mCurrentState     // Catch: all -> 0x0036
            java.io.File r0 = r0.rootDirectory     // Catch: all -> 0x0036
            androidx.recyclerview.R$dimen.deleteRecursively(r0)     // Catch: all -> 0x0036
        L_0x002a:
            r2.createStorage()     // Catch: all -> 0x0036
        L_0x002d:
            com.facebook.cache.disk.DynamicDefaultDiskStorage$State r0 = r2.mCurrentState     // Catch: all -> 0x0036
            com.facebook.cache.disk.DiskStorage r0 = r0.delegate     // Catch: all -> 0x0036
            java.util.Objects.requireNonNull(r0)     // Catch: all -> 0x0036
            monitor-exit(r2)
            return r0
        L_0x0036:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.cache.disk.DynamicDefaultDiskStorage.get():com.facebook.cache.disk.DiskStorage");
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public Collection<DiskStorage.Entry> getEntries() throws IOException {
        return get().getEntries();
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public FileBinaryResource getResource(String str, Object obj) throws IOException {
        return get().getResource(str, obj);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public DiskStorage.Inserter insert(String str, Object obj) throws IOException {
        return get().insert(str, obj);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public boolean isExternal() {
        try {
            return get().isExternal();
        } catch (IOException unused) {
            return false;
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public void purgeUnexpectedResources() {
        try {
            get().purgeUnexpectedResources();
        } catch (IOException e) {
            FLog.e(DynamicDefaultDiskStorage.class, "purgeUnexpectedResources", e);
        }
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(DiskStorage.Entry entry) throws IOException {
        return get().remove(entry);
    }

    @Override // com.facebook.cache.disk.DiskStorage
    public long remove(String str) throws IOException {
        return get().remove(str);
    }
}
