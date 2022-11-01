package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BasePool;
/* loaded from: classes.dex */
public abstract class MemoryChunkPool extends BasePool<MemoryChunk> {
    public final int[] mBucketSizes;

    public MemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        SparseIntArray sparseIntArray = poolParams.bucketSizes;
        this.mBucketSizes = new int[sparseIntArray.size()];
        int i = 0;
        while (true) {
            int[] iArr = this.mBucketSizes;
            if (i < iArr.length) {
                iArr[i] = sparseIntArray.keyAt(i);
                i++;
            } else {
                initialize();
                return;
            }
        }
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public void free(MemoryChunk memoryChunk) {
        memoryChunk.close();
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getBucketedSize(int i) {
        int[] iArr;
        if (i > 0) {
            for (int i2 : this.mBucketSizes) {
                if (i2 >= i) {
                    return i2;
                }
            }
            return i;
        }
        throw new BasePool.InvalidSizeException(Integer.valueOf(i));
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getBucketedSizeForValue(MemoryChunk memoryChunk) {
        return memoryChunk.getSize();
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getSizeInBytes(int i) {
        return i;
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public boolean isReusable(MemoryChunk memoryChunk) {
        return !memoryChunk.isClosed();
    }
}
