package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
/* loaded from: classes.dex */
public class ExtractFromZipSoSource extends UnpackingSoSource {
    public final File mZipFileName;
    public final String mZipSearchPattern;

    /* loaded from: classes.dex */
    public static final class ZipDso extends UnpackingSoSource.Dso implements Comparable {
        public final int abiScore;
        public final ZipEntry backingEntry;

        public ZipDso(String str, ZipEntry zipEntry, int i) {
            super(str, String.format("pseudo-zip-hash-1-%s-%s-%s-%s", zipEntry.getName(), Long.valueOf(zipEntry.getSize()), Long.valueOf(zipEntry.getCompressedSize()), Long.valueOf(zipEntry.getCrc())));
            this.backingEntry = zipEntry;
            this.abiScore = i;
        }

        @Override // java.lang.Comparable
        public int compareTo(Object obj) {
            return this.name.compareTo(((ZipDso) obj).name);
        }
    }

    /* loaded from: classes.dex */
    public class ZipUnpacker extends UnpackingSoSource.Unpacker {
        public ZipDso[] mDsos;
        public final UnpackingSoSource mSoSource;
        public final ZipFile mZipFile;

        /* loaded from: classes.dex */
        public final class ZipBackedInputDsoIterator extends UnpackingSoSource.InputDsoIterator {
            public int mCurrentDso;

            public ZipBackedInputDsoIterator(AnonymousClass1 r2) {
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public boolean hasNext() {
                ZipUnpacker.this.ensureDsos();
                return this.mCurrentDso < ZipUnpacker.this.mDsos.length;
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public UnpackingSoSource.InputDso next() throws IOException {
                ZipUnpacker.this.ensureDsos();
                ZipUnpacker zipUnpacker = ZipUnpacker.this;
                ZipDso[] zipDsoArr = zipUnpacker.mDsos;
                int i = this.mCurrentDso;
                this.mCurrentDso = i + 1;
                ZipDso zipDso = zipDsoArr[i];
                InputStream inputStream = zipUnpacker.mZipFile.getInputStream(zipDso.backingEntry);
                try {
                    return new UnpackingSoSource.InputDso(zipDso, inputStream);
                } catch (Throwable th) {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                }
            }
        }

        public ZipUnpacker(UnpackingSoSource unpackingSoSource) throws IOException {
            this.mZipFile = new ZipFile(ExtractFromZipSoSource.this.mZipFileName);
            this.mSoSource = unpackingSoSource;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.mZipFile.close();
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x0120  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0123  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final com.facebook.soloader.ExtractFromZipSoSource.ZipDso[] ensureDsos() {
            /*
                Method dump skipped, instructions count: 320
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.ExtractFromZipSoSource.ZipUnpacker.ensureDsos():com.facebook.soloader.ExtractFromZipSoSource$ZipDso[]");
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        public final UnpackingSoSource.DsoManifest getDsoManifest() throws IOException {
            return new UnpackingSoSource.DsoManifest(ensureDsos());
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        public final UnpackingSoSource.InputDsoIterator openDsoIterator() throws IOException {
            return new ZipBackedInputDsoIterator(null);
        }
    }

    public ExtractFromZipSoSource(Context context, String str, File file, String str2) {
        super(context, str);
        this.mZipFileName = file;
        this.mZipSearchPattern = str2;
    }
}
