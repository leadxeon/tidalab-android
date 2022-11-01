package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BasePool;
/* loaded from: classes.dex */
public class GenericByteArrayPool extends BasePool<byte[]> implements ByteArrayPool {
    public final int[] mBucketSizes;

    public GenericByteArrayPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        SparseIntArray sparseIntArray = poolParams.bucketSizes;
        this.mBucketSizes = new int[sparseIntArray.size()];
        for (int i = 0; i < sparseIntArray.size(); i++) {
            this.mBucketSizes[i] = sparseIntArray.keyAt(i);
        }
        initialize();
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public byte[] alloc(int i) {
        return new byte[i];
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public void free(byte[] bArr) {
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
    public int getBucketedSizeForValue(byte[] bArr) {
        return bArr.length;
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getSizeInBytes(int i) {
        return i;
    }
}
