package com.facebook.imagepipeline.memory;

import android.util.Log;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.Objects;
/* loaded from: classes.dex */
public class BufferMemoryChunk implements MemoryChunk, Closeable {
    public ByteBuffer mBuffer;
    public final long mId = System.identityHashCode(this);
    public final int mSize;

    public BufferMemoryChunk(int i) {
        this.mBuffer = ByteBuffer.allocateDirect(i);
        this.mSize = i;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.mBuffer = null;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public void copy(int i, MemoryChunk memoryChunk, int i2, int i3) {
        Objects.requireNonNull(memoryChunk);
        if (memoryChunk.getUniqueId() == this.mId) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Copying from BufferMemoryChunk ");
            outline13.append(Long.toHexString(this.mId));
            outline13.append(" to BufferMemoryChunk ");
            outline13.append(Long.toHexString(memoryChunk.getUniqueId()));
            outline13.append(" which are the same ");
            Log.w("BufferMemoryChunk", outline13.toString());
            R$dimen.checkArgument(false);
        }
        if (memoryChunk.getUniqueId() < this.mId) {
            synchronized (memoryChunk) {
                synchronized (this) {
                    doCopy(i, memoryChunk, i2, i3);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (memoryChunk) {
                doCopy(i, memoryChunk, i2, i3);
            }
        }
    }

    public final void doCopy(int i, MemoryChunk memoryChunk, int i2, int i3) {
        if (memoryChunk instanceof BufferMemoryChunk) {
            R$dimen.checkState(!isClosed());
            R$dimen.checkState(!memoryChunk.isClosed());
            R$dimen.checkBounds(i, memoryChunk.getSize(), i2, i3, this.mSize);
            this.mBuffer.position(i);
            memoryChunk.getByteBuffer().position(i2);
            byte[] bArr = new byte[i3];
            this.mBuffer.get(bArr, 0, i3);
            memoryChunk.getByteBuffer().put(bArr, 0, i3);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized ByteBuffer getByteBuffer() {
        return this.mBuffer;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getNativePtr() {
        throw new UnsupportedOperationException("Cannot get the pointer of a BufferMemoryChunk");
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public int getSize() {
        return this.mSize;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getUniqueId() {
        return this.mId;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized boolean isClosed() {
        return this.mBuffer == null;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized byte read(int i) {
        boolean z = true;
        R$dimen.checkState(!isClosed());
        R$dimen.checkArgument(i >= 0);
        if (i >= this.mSize) {
            z = false;
        }
        R$dimen.checkArgument(z);
        return this.mBuffer.get(i);
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int write(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        R$dimen.checkState(!isClosed());
        adjustByteCount = R$dimen.adjustByteCount(i, i3, this.mSize);
        R$dimen.checkBounds(i, bArr.length, i2, adjustByteCount, this.mSize);
        this.mBuffer.position(i);
        this.mBuffer.put(bArr, i2, adjustByteCount);
        return adjustByteCount;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int read(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        Objects.requireNonNull(bArr);
        R$dimen.checkState(!isClosed());
        adjustByteCount = R$dimen.adjustByteCount(i, i3, this.mSize);
        R$dimen.checkBounds(i, bArr.length, i2, adjustByteCount, this.mSize);
        this.mBuffer.position(i);
        this.mBuffer.get(bArr, i2, adjustByteCount);
        return adjustByteCount;
    }
}
