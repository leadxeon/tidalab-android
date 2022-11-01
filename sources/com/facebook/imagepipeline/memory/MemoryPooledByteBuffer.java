package com.facebook.imagepipeline.memory;

import androidx.recyclerview.R$dimen;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import java.util.Objects;
/* loaded from: classes.dex */
public class MemoryPooledByteBuffer implements PooledByteBuffer {
    public CloseableReference<MemoryChunk> mBufRef;
    public final int mSize;

    public MemoryPooledByteBuffer(CloseableReference<MemoryChunk> closeableReference, int i) {
        Objects.requireNonNull(closeableReference);
        R$dimen.checkArgument(i >= 0 && i <= closeableReference.get().getSize());
        this.mBufRef = closeableReference.clone();
        this.mSize = i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        CloseableReference<MemoryChunk> closeableReference = this.mBufRef;
        Class<CloseableReference> cls = CloseableReference.TAG;
        if (closeableReference != null) {
            closeableReference.close();
        }
        this.mBufRef = null;
    }

    public synchronized void ensureValid() {
        try {
            synchronized (this) {
            }
        } catch (Throwable th) {
            throw th;
        }
        if (!CloseableReference.isValid(this.mBufRef)) {
            throw new PooledByteBuffer.ClosedException();
        }
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized boolean isClosed() {
        return !CloseableReference.isValid(this.mBufRef);
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized byte read(int i) {
        ensureValid();
        boolean z = true;
        R$dimen.checkArgument(i >= 0);
        if (i >= this.mSize) {
            z = false;
        }
        R$dimen.checkArgument(z);
        return this.mBufRef.get().read(i);
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized int size() {
        ensureValid();
        return this.mSize;
    }

    @Override // com.facebook.common.memory.PooledByteBuffer
    public synchronized int read(int i, byte[] bArr, int i2, int i3) {
        ensureValid();
        R$dimen.checkArgument(i + i3 <= this.mSize);
        return this.mBufRef.get().read(i, bArr, i2, i3);
    }
}
