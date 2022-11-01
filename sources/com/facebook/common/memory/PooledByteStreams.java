package com.facebook.common.memory;

import androidx.recyclerview.R$dimen;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import okhttp3.internal.http2.Http2;
/* loaded from: classes.dex */
public class PooledByteStreams {
    public final ByteArrayPool mByteArrayPool;
    public final int mTempBufSize = Http2.INITIAL_MAX_FRAME_SIZE;

    public PooledByteStreams(ByteArrayPool byteArrayPool) {
        R$dimen.checkArgument(true);
        this.mByteArrayPool = byteArrayPool;
    }

    public long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = this.mByteArrayPool.get(this.mTempBufSize);
        long j = 0;
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, this.mTempBufSize);
                if (read == -1) {
                    return j;
                }
                outputStream.write(bArr, 0, read);
                j += read;
            } finally {
                this.mByteArrayPool.release(bArr);
            }
        }
    }
}
