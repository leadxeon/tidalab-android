package com.facebook.common.memory;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.InputStream;
/* loaded from: classes.dex */
public class PooledByteBufferInputStream extends InputStream {
    public final PooledByteBuffer mPooledByteBuffer;
    public int mOffset = 0;
    public int mMark = 0;

    public PooledByteBufferInputStream(PooledByteBuffer pooledByteBuffer) {
        R$dimen.checkArgument(!pooledByteBuffer.isClosed());
        this.mPooledByteBuffer = pooledByteBuffer;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.mPooledByteBuffer.size() - this.mOffset;
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.mMark = this.mOffset;
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return true;
    }

    @Override // java.io.InputStream
    public int read() {
        if (available() <= 0) {
            return -1;
        }
        PooledByteBuffer pooledByteBuffer = this.mPooledByteBuffer;
        int i = this.mOffset;
        this.mOffset = i + 1;
        return pooledByteBuffer.read(i) & 255;
    }

    @Override // java.io.InputStream
    public void reset() {
        this.mOffset = this.mMark;
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        R$dimen.checkArgument(j >= 0);
        int min = Math.min((int) j, available());
        this.mOffset += min;
        return min;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("length=");
            outline13.append(bArr.length);
            outline13.append("; regionStart=");
            outline13.append(i);
            outline13.append("; regionLength=");
            outline13.append(i2);
            throw new ArrayIndexOutOfBoundsException(outline13.toString());
        }
        int available = available();
        if (available <= 0) {
            return -1;
        }
        if (i2 <= 0) {
            return 0;
        }
        int min = Math.min(available, i2);
        this.mPooledByteBuffer.read(this.mOffset, bArr, i, min);
        this.mOffset += min;
        return min;
    }
}
