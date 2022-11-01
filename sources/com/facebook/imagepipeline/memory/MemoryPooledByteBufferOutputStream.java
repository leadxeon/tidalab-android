package com.facebook.imagepipeline.memory;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import java.util.Objects;
/* loaded from: classes.dex */
public class MemoryPooledByteBufferOutputStream extends PooledByteBufferOutputStream {
    public CloseableReference<MemoryChunk> mBufRef;
    public int mCount;
    public final MemoryChunkPool mPool;

    /* loaded from: classes.dex */
    public static class InvalidStreamException extends RuntimeException {
        public InvalidStreamException() {
            super("OutputStream no longer valid");
        }
    }

    public MemoryPooledByteBufferOutputStream(MemoryChunkPool memoryChunkPool, int i) {
        R$dimen.checkArgument(i > 0);
        Objects.requireNonNull(memoryChunkPool);
        this.mPool = memoryChunkPool;
        this.mCount = 0;
        this.mBufRef = CloseableReference.of(memoryChunkPool.get(i), memoryChunkPool);
    }

    @Override // com.facebook.common.memory.PooledByteBufferOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference<MemoryChunk> closeableReference = this.mBufRef;
        Class<CloseableReference> cls = CloseableReference.TAG;
        if (closeableReference != null) {
            closeableReference.close();
        }
        this.mBufRef = null;
        this.mCount = -1;
        super.close();
    }

    public final void ensureValid() {
        if (!CloseableReference.isValid(this.mBufRef)) {
            throw new InvalidStreamException();
        }
    }

    public MemoryPooledByteBuffer toByteBuffer() {
        ensureValid();
        return new MemoryPooledByteBuffer(this.mBufRef, this.mCount);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("length=");
            outline13.append(bArr.length);
            outline13.append("; regionStart=");
            outline13.append(i);
            outline13.append("; regionLength=");
            outline13.append(i2);
            throw new ArrayIndexOutOfBoundsException(outline13.toString());
        }
        ensureValid();
        int i3 = this.mCount + i2;
        ensureValid();
        if (i3 > this.mBufRef.get().getSize()) {
            MemoryChunk memoryChunk = this.mPool.get(i3);
            this.mBufRef.get().copy(0, memoryChunk, 0, this.mCount);
            this.mBufRef.close();
            this.mBufRef = CloseableReference.of(memoryChunk, this.mPool);
        }
        this.mBufRef.get().write(this.mCount, bArr, i, i2);
        this.mCount += i2;
    }
}
