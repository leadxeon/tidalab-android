package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import java.io.Closeable;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class UnpackingSoSource extends DirectorySoSource {
    public final Context mContext;
    public String mCorruptedLib;
    public final Map<String, Object> mLibsBeingLoaded = new HashMap();

    /* loaded from: classes.dex */
    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String str, String str2) {
            this.name = str;
            this.hash = str2;
        }
    }

    /* loaded from: classes.dex */
    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsoArr) {
            this.dsos = dsoArr;
        }

        public static final DsoManifest read(DataInput dataInput) throws IOException {
            if (dataInput.readByte() == 1) {
                int readInt = dataInput.readInt();
                if (readInt >= 0) {
                    Dso[] dsoArr = new Dso[readInt];
                    for (int i = 0; i < readInt; i++) {
                        dsoArr[i] = new Dso(dataInput.readUTF(), dataInput.readUTF());
                    }
                    return new DsoManifest(dsoArr);
                }
                throw new RuntimeException("illegal number of shared libraries");
            }
            throw new RuntimeException("wrong dso manifest version");
        }
    }

    /* loaded from: classes.dex */
    public static final class InputDso implements Closeable {
        public final InputStream content;
        public final Dso dso;

        public InputDso(Dso dso, InputStream inputStream) {
            this.dso = dso;
            this.content = inputStream;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.content.close();
        }
    }

    /* loaded from: classes.dex */
    public static abstract class InputDsoIterator implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public abstract boolean hasNext();

        public abstract InputDso next() throws IOException;
    }

    /* loaded from: classes.dex */
    public static abstract class Unpacker implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public abstract DsoManifest getDsoManifest() throws IOException;

        public abstract InputDsoIterator openDsoIterator() throws IOException;
    }

    public UnpackingSoSource(Context context, String str) {
        super(new File(context.getApplicationInfo().dataDir + "/" + str), 1);
        this.mContext = context;
    }

    public static void writeState(File file, byte b) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0L);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    randomAccessFile.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public final void deleteUnmentionedFiles(Dso[] dsoArr) throws IOException {
        String[] list = this.soDirectory.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals("dso_state") && !str.equals("dso_lock") && !str.equals("dso_deps") && !str.equals("dso_manifest")) {
                    boolean z = false;
                    for (int i = 0; !z && i < dsoArr.length; i++) {
                        if (dsoArr[i].name.equals(str)) {
                            z = true;
                        }
                    }
                    if (!z) {
                        File file = new File(this.soDirectory, str);
                        Log.v("fb-UnpackingSoSource", "deleting unaccounted-for file " + file);
                        R$style.dumbDeleteRecursive(file);
                    }
                }
            }
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("unable to list directory ");
        outline13.append(this.soDirectory);
        throw new IOException(outline13.toString());
    }

    public final void extractDso(InputDso inputDso, byte[] bArr) throws IOException {
        RandomAccessFile randomAccessFile;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("extracting DSO ");
        outline13.append(inputDso.dso.name);
        Log.i("fb-UnpackingSoSource", outline13.toString());
        if (this.soDirectory.setWritable(true, true)) {
            File file = new File(this.soDirectory, inputDso.dso.name);
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (IOException e) {
                Log.w("fb-UnpackingSoSource", "error overwriting " + file + " trying to delete and start over", e);
                R$style.dumbDeleteRecursive(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
            }
            try {
                try {
                    int available = inputDso.content.available();
                    if (available > 1) {
                        SysUtil$LollipopSysdeps.fallocateIfSupported(randomAccessFile.getFD(), available);
                    }
                    InputStream inputStream = inputDso.content;
                    int i = 0;
                    while (i < Integer.MAX_VALUE) {
                        int read = inputStream.read(bArr, 0, Math.min(bArr.length, Integer.MAX_VALUE - i));
                        if (read == -1) {
                            break;
                        }
                        randomAccessFile.write(bArr, 0, read);
                        i += read;
                    }
                    randomAccessFile.setLength(randomAccessFile.getFilePointer());
                    if (!file.setExecutable(true, false)) {
                        throw new IOException("cannot make file executable: " + file);
                    }
                } catch (IOException e2) {
                    R$style.dumbDeleteRecursive(file);
                    throw e2;
                }
            } finally {
                randomAccessFile.close();
            }
        } else {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("cannot make directory writable for us: ");
            outline132.append(this.soDirectory);
            throw new IOException(outline132.toString());
        }
    }

    public byte[] getDepsBlock() throws IOException {
        Parcel obtain = Parcel.obtain();
        Unpacker makeUnpacker = makeUnpacker();
        try {
            Dso[] dsoArr = makeUnpacker.getDsoManifest().dsos;
            obtain.writeByte((byte) 1);
            obtain.writeInt(dsoArr.length);
            for (int i = 0; i < dsoArr.length; i++) {
                obtain.writeString(dsoArr[i].name);
                obtain.writeString(dsoArr[i].hash);
            }
            makeUnpacker.close();
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (makeUnpacker != null) {
                    try {
                        makeUnpacker.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public final Object getLibraryLock(String str) {
        Object obj;
        synchronized (this.mLibsBeingLoaded) {
            obj = this.mLibsBeingLoaded.get(str);
            if (obj == null) {
                obj = new Object();
                this.mLibsBeingLoaded.put(str, obj);
            }
        }
        return obj;
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        int loadLibraryFrom;
        synchronized (getLibraryLock(str)) {
            loadLibraryFrom = loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
        }
        return loadLibraryFrom;
    }

    public abstract Unpacker makeUnpacker() throws IOException;

    @Override // com.facebook.soloader.SoSource
    public void prepare(int i) throws IOException {
        File file = this.soDirectory;
        if (file.mkdirs() || file.isDirectory()) {
            FileLocker fileLocker = new FileLocker(new File(this.soDirectory, "dso_lock"));
            try {
                Log.v("fb-UnpackingSoSource", "locked dso store " + this.soDirectory);
                if (refreshLocked(fileLocker, i, getDepsBlock())) {
                    fileLocker = null;
                } else {
                    Log.i("fb-UnpackingSoSource", "dso store is up-to-date: " + this.soDirectory);
                }
                if (fileLocker == null) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("not releasing dso store lock for ");
                    outline13.append(this.soDirectory);
                    outline13.append(" (syncer thread started)");
                    Log.v("fb-UnpackingSoSource", outline13.toString());
                }
            } finally {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("releasing dso store lock for ");
                outline132.append(this.soDirectory);
                Log.v("fb-UnpackingSoSource", outline132.toString());
                fileLocker.close();
            }
        } else {
            throw new IOException("cannot mkdir: " + file);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x009d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean refreshLocked(final com.facebook.soloader.FileLocker r12, int r13, final byte[] r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.refreshLocked(com.facebook.soloader.FileLocker, int, byte[]):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x003f A[Catch: all -> 0x0032, TryCatch #7 {all -> 0x0032, blocks: (B:4:0x002d, B:7:0x0036, B:11:0x003f, B:12:0x0046, B:13:0x0050, B:15:0x0056, B:29:0x009e, B:18:0x005e, B:20:0x0063, B:22:0x0071, B:25:0x0082, B:27:0x0089), top: B:38:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0056 A[Catch: all -> 0x0032, TRY_LEAVE, TryCatch #7 {all -> 0x0032, blocks: (B:4:0x002d, B:7:0x0036, B:11:0x003f, B:12:0x0046, B:13:0x0050, B:15:0x0056, B:29:0x009e, B:18:0x005e, B:20:0x0063, B:22:0x0071, B:25:0x0082, B:27:0x0089), top: B:38:0x002d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void regenerate(byte r8, com.facebook.soloader.UnpackingSoSource.DsoManifest r9, com.facebook.soloader.UnpackingSoSource.InputDsoIterator r10) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "regenerating DSO store "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r0)
            java.lang.Class r1 = r7.getClass()
            java.lang.String r1 = r1.getName()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "fb-UnpackingSoSource"
            android.util.Log.v(r1, r0)
            java.io.File r0 = new java.io.File
            java.io.File r2 = r7.soDirectory
            java.lang.String r3 = "dso_manifest"
            r0.<init>(r2, r3)
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile
            java.lang.String r3 = "rw"
            r2.<init>(r0, r3)
            r0 = 1
            if (r8 != r0) goto L_0x003b
            com.facebook.soloader.UnpackingSoSource$DsoManifest r8 = com.facebook.soloader.UnpackingSoSource.DsoManifest.read(r2)     // Catch: all -> 0x0032, Exception -> 0x0035
            goto L_0x003c
        L_0x0032:
            r8 = move-exception
            goto L_0x00c4
        L_0x0035:
            r8 = move-exception
            java.lang.String r0 = "error reading existing DSO manifest"
            android.util.Log.i(r1, r0, r8)     // Catch: all -> 0x0032
        L_0x003b:
            r8 = 0
        L_0x003c:
            r0 = 0
            if (r8 != 0) goto L_0x0046
            com.facebook.soloader.UnpackingSoSource$DsoManifest r8 = new com.facebook.soloader.UnpackingSoSource$DsoManifest     // Catch: all -> 0x0032
            com.facebook.soloader.UnpackingSoSource$Dso[] r0 = new com.facebook.soloader.UnpackingSoSource.Dso[r0]     // Catch: all -> 0x0032
            r8.<init>(r0)     // Catch: all -> 0x0032
        L_0x0046:
            com.facebook.soloader.UnpackingSoSource$Dso[] r9 = r9.dsos     // Catch: all -> 0x0032
            r7.deleteUnmentionedFiles(r9)     // Catch: all -> 0x0032
            r9 = 32768(0x8000, float:4.5918E-41)
            byte[] r9 = new byte[r9]     // Catch: all -> 0x0032
        L_0x0050:
            boolean r0 = r10.hasNext()     // Catch: all -> 0x0032
            if (r0 == 0) goto L_0x00a4
            com.facebook.soloader.UnpackingSoSource$InputDso r0 = r10.next()     // Catch: all -> 0x0032
            r3 = 1
            r4 = 0
        L_0x005c:
            if (r3 == 0) goto L_0x0087
            com.facebook.soloader.UnpackingSoSource$Dso[] r5 = r8.dsos     // Catch: all -> 0x0085
            int r6 = r5.length     // Catch: all -> 0x0085
            if (r4 >= r6) goto L_0x0087
            r5 = r5[r4]     // Catch: all -> 0x0085
            java.lang.String r5 = r5.name     // Catch: all -> 0x0085
            com.facebook.soloader.UnpackingSoSource$Dso r6 = r0.dso     // Catch: all -> 0x0085
            java.lang.String r6 = r6.name     // Catch: all -> 0x0085
            boolean r5 = r5.equals(r6)     // Catch: all -> 0x0085
            if (r5 == 0) goto L_0x0082
            com.facebook.soloader.UnpackingSoSource$Dso[] r5 = r8.dsos     // Catch: all -> 0x0085
            r5 = r5[r4]     // Catch: all -> 0x0085
            java.lang.String r5 = r5.hash     // Catch: all -> 0x0085
            com.facebook.soloader.UnpackingSoSource$Dso r6 = r0.dso     // Catch: all -> 0x0085
            java.lang.String r6 = r6.hash     // Catch: all -> 0x0085
            boolean r5 = r5.equals(r6)     // Catch: all -> 0x0085
            if (r5 == 0) goto L_0x0082
            r3 = 0
        L_0x0082:
            int r4 = r4 + 1
            goto L_0x005c
        L_0x0085:
            r8 = move-exception
            goto L_0x008d
        L_0x0087:
            if (r3 == 0) goto L_0x009c
            r7.extractDso(r0, r9)     // Catch: all -> 0x0085
            goto L_0x009c
        L_0x008d:
            throw r8     // Catch: all -> 0x008e
        L_0x008e:
            r9 = move-exception
            if (r0 == 0) goto L_0x009b
            java.io.InputStream r10 = r0.content     // Catch: all -> 0x0097
            r10.close()     // Catch: all -> 0x0097
            goto L_0x009b
        L_0x0097:
            r10 = move-exception
            r8.addSuppressed(r10)     // Catch: all -> 0x0032
        L_0x009b:
            throw r9     // Catch: all -> 0x0032
        L_0x009c:
            if (r0 == 0) goto L_0x0050
            java.io.InputStream r0 = r0.content     // Catch: all -> 0x0032
            r0.close()     // Catch: all -> 0x0032
            goto L_0x0050
        L_0x00a4:
            r2.close()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Finished regenerating DSO store "
            r8.append(r9)
            java.lang.Class r9 = r7.getClass()
            java.lang.String r9 = r9.getName()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            android.util.Log.v(r1, r8)
            return
        L_0x00c4:
            throw r8     // Catch: all -> 0x00c5
        L_0x00c5:
            r9 = move-exception
            r2.close()     // Catch: all -> 0x00ca
            goto L_0x00ce
        L_0x00ca:
            r10 = move-exception
            r8.addSuppressed(r10)
        L_0x00ce:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.regenerate(byte, com.facebook.soloader.UnpackingSoSource$DsoManifest, com.facebook.soloader.UnpackingSoSource$InputDsoIterator):void");
    }
}
