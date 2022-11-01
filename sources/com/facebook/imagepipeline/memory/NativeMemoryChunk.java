package com.facebook.imagepipeline.memory;

import android.util.Log;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.imagepipeline.nativecode.ImagePipelineNativeLoader;
import com.facebook.soloader.SoLoader;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Objects;
@DoNotStrip
/* loaded from: classes.dex */
public class NativeMemoryChunk implements MemoryChunk, Closeable {
    public boolean mIsClosed;
    public final long mNativePtr;
    public final int mSize;

    static {
        List<String> list = ImagePipelineNativeLoader.DEPENDENCIES;
        SoLoader.loadLibrary("imagepipeline");
    }

    public NativeMemoryChunk(int i) {
        R$dimen.checkArgument(i > 0);
        this.mSize = i;
        this.mNativePtr = nativeAllocate(i);
        this.mIsClosed = false;
    }

    @DoNotStrip
    private static native long nativeAllocate(int i);

    @DoNotStrip
    private static native void nativeCopyFromByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    private static native void nativeCopyToByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    private static native void nativeFree(long j);

    @DoNotStrip
    private static native void nativeMemcpy(long j, long j2, int i);

    @DoNotStrip
    private static native byte nativeReadByte(long j);

    @Override // com.facebook.imagepipeline.memory.MemoryChunk, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.mIsClosed) {
            this.mIsClosed = true;
            nativeFree(this.mNativePtr);
        }
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public void copy(int i, MemoryChunk memoryChunk, int i2, int i3) {
        Objects.requireNonNull(memoryChunk);
        if (memoryChunk.getUniqueId() == this.mNativePtr) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Copying from NativeMemoryChunk ");
            outline13.append(Integer.toHexString(System.identityHashCode(this)));
            outline13.append(" to NativeMemoryChunk ");
            outline13.append(Integer.toHexString(System.identityHashCode(memoryChunk)));
            outline13.append(" which share the same address ");
            outline13.append(Long.toHexString(this.mNativePtr));
            Log.w("NativeMemoryChunk", outline13.toString());
            R$dimen.checkArgument(false);
        }
        if (memoryChunk.getUniqueId() < this.mNativePtr) {
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
        if (memoryChunk instanceof NativeMemoryChunk) {
            R$dimen.checkState(!isClosed());
            R$dimen.checkState(!memoryChunk.isClosed());
            R$dimen.checkBounds(i, memoryChunk.getSize(), i2, i3, this.mSize);
            nativeMemcpy(memoryChunk.getNativePtr() + i2, this.mNativePtr + i, i3);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }

    public void finalize() throws Throwable {
        if (!isClosed()) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("finalize: Chunk ");
            outline13.append(Integer.toHexString(System.identityHashCode(this)));
            outline13.append(" still active. ");
            Log.w("NativeMemoryChunk", outline13.toString());
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public ByteBuffer getByteBuffer() {
        return null;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getNativePtr() {
        return this.mNativePtr;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public int getSize() {
        return this.mSize;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public long getUniqueId() {
        return this.mNativePtr;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized boolean isClosed() {
        return this.mIsClosed;
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
        return nativeReadByte(this.mNativePtr + i);
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int write(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        R$dimen.checkState(!isClosed());
        adjustByteCount = R$dimen.adjustByteCount(i, i3, this.mSize);
        R$dimen.checkBounds(i, bArr.length, i2, adjustByteCount, this.mSize);
        nativeCopyFromByteArray(this.mNativePtr + i, bArr, i2, adjustByteCount);
        return adjustByteCount;
    }

    @Override // com.facebook.imagepipeline.memory.MemoryChunk
    public synchronized int read(int i, byte[] bArr, int i2, int i3) {
        int adjustByteCount;
        Objects.requireNonNull(bArr);
        R$dimen.checkState(!isClosed());
        adjustByteCount = R$dimen.adjustByteCount(i, i3, this.mSize);
        R$dimen.checkBounds(i, bArr.length, i2, adjustByteCount, this.mSize);
        nativeCopyToByteArray(this.mNativePtr + i, bArr, i2, adjustByteCount);
        return adjustByteCount;
    }

    public NativeMemoryChunk() {
        this.mSize = 0;
        this.mNativePtr = 0L;
        this.mIsClosed = true;
    }
}
