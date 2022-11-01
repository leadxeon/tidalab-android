package com.facebook.soloader;

import android.content.Context;
import com.facebook.soloader.UnpackingSoSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/* loaded from: classes.dex */
public final class ExoSoSource extends UnpackingSoSource {

    /* loaded from: classes.dex */
    public final class ExoUnpacker extends UnpackingSoSource.Unpacker {
        public final FileDso[] mDsos;

        /* loaded from: classes.dex */
        public final class FileBackedInputDsoIterator extends UnpackingSoSource.InputDsoIterator {
            public int mCurrentDso;

            public FileBackedInputDsoIterator(AnonymousClass1 r2) {
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public boolean hasNext() {
                return this.mCurrentDso < ExoUnpacker.this.mDsos.length;
            }

            @Override // com.facebook.soloader.UnpackingSoSource.InputDsoIterator
            public UnpackingSoSource.InputDso next() throws IOException {
                FileDso[] fileDsoArr = ExoUnpacker.this.mDsos;
                int i = this.mCurrentDso;
                this.mCurrentDso = i + 1;
                FileDso fileDso = fileDsoArr[i];
                FileInputStream fileInputStream = new FileInputStream(fileDso.backingFile);
                try {
                    return new UnpackingSoSource.InputDso(fileDso, fileInputStream);
                } catch (Throwable th) {
                    fileInputStream.close();
                    throw th;
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:28:0x00d8, code lost:
            throw new java.lang.RuntimeException("illegal line in exopackage metadata: [" + r10 + "]");
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public ExoUnpacker(com.facebook.soloader.ExoSoSource r17, com.facebook.soloader.UnpackingSoSource r18) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 286
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.ExoSoSource.ExoUnpacker.<init>(com.facebook.soloader.ExoSoSource, com.facebook.soloader.UnpackingSoSource):void");
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        public UnpackingSoSource.DsoManifest getDsoManifest() throws IOException {
            return new UnpackingSoSource.DsoManifest(this.mDsos);
        }

        @Override // com.facebook.soloader.UnpackingSoSource.Unpacker
        public UnpackingSoSource.InputDsoIterator openDsoIterator() throws IOException {
            return new FileBackedInputDsoIterator(null);
        }
    }

    /* loaded from: classes.dex */
    public static final class FileDso extends UnpackingSoSource.Dso {
        public final File backingFile;

        public FileDso(String str, String str2, File file) {
            super(str, str2);
            this.backingFile = file;
        }
    }

    public ExoSoSource(Context context, String str) {
        super(context, str);
    }

    @Override // com.facebook.soloader.UnpackingSoSource
    public UnpackingSoSource.Unpacker makeUnpacker() throws IOException {
        return new ExoUnpacker(this, this);
    }
}
