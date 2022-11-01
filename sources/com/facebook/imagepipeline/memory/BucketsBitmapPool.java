package com.facebook.imagepipeline.memory;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import com.facebook.common.memory.MemoryTrimmableRegistry;
@TargetApi(21)
/* loaded from: classes.dex */
public class BucketsBitmapPool extends BasePool<Bitmap> implements BitmapPool {
    public BucketsBitmapPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, poolStatsTracker);
        initialize();
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public Bitmap alloc(int i) {
        return Bitmap.createBitmap(1, (int) Math.ceil(i / 2.0d), Bitmap.Config.RGB_565);
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public void free(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getBucketedSize(int i) {
        return i;
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getBucketedSizeForValue(Bitmap bitmap) {
        return bitmap.getAllocationByteCount();
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public int getSizeInBytes(int i) {
        return i;
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public Bitmap getValue(Bucket<Bitmap> bucket) {
        Bitmap bitmap = (Bitmap) super.getValue(bucket);
        if (bitmap != null) {
            bitmap.eraseColor(0);
        }
        return bitmap;
    }

    @Override // com.facebook.imagepipeline.memory.BasePool
    public boolean isReusable(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        return !bitmap2.isRecycled() && bitmap2.isMutable();
    }
}
