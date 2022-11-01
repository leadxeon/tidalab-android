package com.facebook.imagepipeline.memory;

import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import java.util.Objects;
/* loaded from: classes.dex */
public class PoolFactory {
    public BitmapPool mBitmapPool;
    public BufferMemoryChunkPool mBufferMemoryChunkPool;
    public final PoolConfig mConfig;
    public NativeMemoryChunkPool mNativeMemoryChunkPool;
    public PooledByteBufferFactory mPooledByteBufferFactory;
    public PooledByteStreams mPooledByteStreams;
    public ByteArrayPool mSmallByteArrayPool;

    public PoolFactory(PoolConfig poolConfig) {
        this.mConfig = poolConfig;
    }

    public BitmapPool getBitmapPool() {
        if (this.mBitmapPool == null) {
            String str = this.mConfig.mBitmapPoolType;
            char c = 65535;
            switch (str.hashCode()) {
                case -1868884870:
                    if (str.equals("legacy_default_params")) {
                        c = 3;
                        break;
                    }
                    break;
                case -1106578487:
                    if (str.equals("legacy")) {
                        c = 4;
                        break;
                    }
                    break;
                case -404562712:
                    if (str.equals("experimental")) {
                        c = 2;
                        break;
                    }
                    break;
                case -402149703:
                    if (str.equals("dummy_with_tracking")) {
                        c = 1;
                        break;
                    }
                    break;
                case 95945896:
                    if (str.equals("dummy")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            if (c == 0) {
                this.mBitmapPool = new DummyBitmapPool();
            } else if (c == 1) {
                this.mBitmapPool = new DummyTrackingInUseBitmapPool();
            } else if (c == 2) {
                Objects.requireNonNull(this.mConfig);
                int i = this.mConfig.mBitmapPoolMaxBitmapSize;
                NoOpPoolStatsTracker instance = NoOpPoolStatsTracker.getInstance();
                Objects.requireNonNull(this.mConfig);
                this.mBitmapPool = new LruBitmapPool(0, i, instance, null);
            } else if (c != 3) {
                PoolConfig poolConfig = this.mConfig;
                this.mBitmapPool = new BucketsBitmapPool(poolConfig.mMemoryTrimmableRegistry, poolConfig.mBitmapPoolParams, poolConfig.mBitmapPoolStatsTracker);
            } else {
                this.mBitmapPool = new BucketsBitmapPool(this.mConfig.mMemoryTrimmableRegistry, DefaultBitmapPoolParams.get(), this.mConfig.mBitmapPoolStatsTracker);
            }
        }
        return this.mBitmapPool;
    }

    public int getFlexByteArrayPoolMaxNumThreads() {
        return this.mConfig.mFlexByteArrayPoolParams.maxNumThreads;
    }

    public PooledByteBufferFactory getPooledByteBufferFactory(int i) {
        MemoryChunkPool memoryChunkPool;
        if (this.mPooledByteBufferFactory == null) {
            if (i == 0) {
                if (this.mNativeMemoryChunkPool == null) {
                    PoolConfig poolConfig = this.mConfig;
                    this.mNativeMemoryChunkPool = new NativeMemoryChunkPool(poolConfig.mMemoryTrimmableRegistry, poolConfig.mMemoryChunkPoolParams, poolConfig.mMemoryChunkPoolStatsTracker);
                }
                memoryChunkPool = this.mNativeMemoryChunkPool;
            } else if (i == 1) {
                if (this.mBufferMemoryChunkPool == null) {
                    PoolConfig poolConfig2 = this.mConfig;
                    this.mBufferMemoryChunkPool = new BufferMemoryChunkPool(poolConfig2.mMemoryTrimmableRegistry, poolConfig2.mMemoryChunkPoolParams, poolConfig2.mMemoryChunkPoolStatsTracker);
                }
                memoryChunkPool = this.mBufferMemoryChunkPool;
            } else {
                throw new IllegalArgumentException("Invalid MemoryChunkType");
            }
            this.mPooledByteBufferFactory = new MemoryPooledByteBufferFactory(memoryChunkPool, getPooledByteStreams());
        }
        return this.mPooledByteBufferFactory;
    }

    public PooledByteStreams getPooledByteStreams() {
        if (this.mPooledByteStreams == null) {
            this.mPooledByteStreams = new PooledByteStreams(getSmallByteArrayPool());
        }
        return this.mPooledByteStreams;
    }

    public ByteArrayPool getSmallByteArrayPool() {
        if (this.mSmallByteArrayPool == null) {
            PoolConfig poolConfig = this.mConfig;
            this.mSmallByteArrayPool = new GenericByteArrayPool(poolConfig.mMemoryTrimmableRegistry, poolConfig.mSmallByteArrayPoolParams, poolConfig.mSmallByteArrayPoolStatsTracker);
        }
        return this.mSmallByteArrayPool;
    }
}
