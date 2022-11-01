package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import okhttp3.internal.http2.Http2;
/* loaded from: classes.dex */
public class PoolConfig {
    public final int mBitmapPoolMaxBitmapSize;
    public final PoolParams mBitmapPoolParams = DefaultBitmapPoolParams.get();
    public final PoolStatsTracker mBitmapPoolStatsTracker = NoOpPoolStatsTracker.getInstance();
    public final String mBitmapPoolType;
    public final PoolParams mFlexByteArrayPoolParams;
    public final PoolParams mMemoryChunkPoolParams;
    public final PoolStatsTracker mMemoryChunkPoolStatsTracker;
    public final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    public final PoolParams mSmallByteArrayPoolParams;
    public final PoolStatsTracker mSmallByteArrayPoolStatsTracker;

    /* loaded from: classes.dex */
    public static class Builder {
        public Builder(AnonymousClass1 r1) {
        }
    }

    public PoolConfig(Builder builder, AnonymousClass1 r14) {
        int i;
        FrescoSystrace.isTracing();
        int i2 = DefaultFlexByteArrayPoolParams.DEFAULT_MAX_NUM_THREADS;
        int i3 = i2 * 4194304;
        SparseIntArray sparseIntArray = new SparseIntArray();
        for (int i4 = 131072; i4 <= 4194304; i4 *= 2) {
            sparseIntArray.put(i4, i2);
        }
        this.mFlexByteArrayPoolParams = new PoolParams(4194304, i3, sparseIntArray, 131072, 4194304, DefaultFlexByteArrayPoolParams.DEFAULT_MAX_NUM_THREADS);
        this.mMemoryTrimmableRegistry = NoOpMemoryTrimmableRegistry.getInstance();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1024, 5);
        sparseIntArray2.put(2048, 5);
        sparseIntArray2.put(4096, 5);
        sparseIntArray2.put(8192, 5);
        sparseIntArray2.put(Http2.INITIAL_MAX_FRAME_SIZE, 5);
        sparseIntArray2.put(32768, 5);
        sparseIntArray2.put(65536, 5);
        sparseIntArray2.put(131072, 5);
        sparseIntArray2.put(262144, 2);
        sparseIntArray2.put(524288, 2);
        sparseIntArray2.put(1048576, 2);
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        int i5 = min < 16777216 ? 3145728 : min < 33554432 ? 6291456 : 12582912;
        int min2 = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min2 < 16777216) {
            i = min2 / 2;
        } else {
            i = (min2 / 4) * 3;
        }
        this.mMemoryChunkPoolParams = new PoolParams(i5, i, sparseIntArray2);
        this.mMemoryChunkPoolStatsTracker = NoOpPoolStatsTracker.getInstance();
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray3.put(Http2.INITIAL_MAX_FRAME_SIZE, 5);
        this.mSmallByteArrayPoolParams = new PoolParams(81920, 1048576, sparseIntArray3);
        this.mSmallByteArrayPoolStatsTracker = NoOpPoolStatsTracker.getInstance();
        this.mBitmapPoolType = "legacy";
        this.mBitmapPoolMaxBitmapSize = 4194304;
        FrescoSystrace.isTracing();
    }
}
