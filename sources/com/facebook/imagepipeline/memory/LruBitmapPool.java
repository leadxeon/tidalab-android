package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BucketMap;
import java.util.LinkedList;
import java.util.Objects;
/* loaded from: classes.dex */
public class LruBitmapPool implements BitmapPool {
    public int mCurrentSize;
    public int mMaxBitmapSize;
    public final int mMaxPoolSize;
    public final PoolStatsTracker mPoolStatsTracker;
    public final LruBucketsPoolBackend<Bitmap> mStrategy = new BitmapPoolBackend();

    public LruBitmapPool(int i, int i2, PoolStatsTracker poolStatsTracker, MemoryTrimmableRegistry memoryTrimmableRegistry) {
        this.mMaxPoolSize = i;
        this.mMaxBitmapSize = i2;
        this.mPoolStatsTracker = poolStatsTracker;
    }

    @Override // com.facebook.common.memory.Pool
    public Bitmap get(int i) {
        Bitmap bitmap;
        Bitmap pop;
        synchronized (this) {
            int i2 = this.mCurrentSize;
            int i3 = this.mMaxPoolSize;
            if (i2 > i3) {
                synchronized (this) {
                    while (this.mCurrentSize > i3 && (pop = this.mStrategy.pop()) != null) {
                        int size = this.mStrategy.getSize(pop);
                        this.mCurrentSize -= size;
                        this.mPoolStatsTracker.onFree(size);
                    }
                }
            }
            bitmap = this.mStrategy.get(i);
            if (bitmap != null) {
                int size2 = this.mStrategy.getSize(bitmap);
                this.mCurrentSize -= size2;
                this.mPoolStatsTracker.onValueReuse(size2);
            } else {
                this.mPoolStatsTracker.onAlloc(i);
                bitmap = Bitmap.createBitmap(1, i, Bitmap.Config.ALPHA_8);
            }
        }
        return bitmap;
    }

    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    public void release(Object obj) {
        boolean add;
        Bitmap bitmap = (Bitmap) obj;
        int size = this.mStrategy.getSize(bitmap);
        if (size <= this.mMaxBitmapSize) {
            this.mPoolStatsTracker.onValueRelease(size);
            BitmapPoolBackend bitmapPoolBackend = (BitmapPoolBackend) this.mStrategy;
            Objects.requireNonNull(bitmapPoolBackend);
            if (bitmapPoolBackend.isReusable(bitmap)) {
                synchronized (bitmapPoolBackend) {
                    add = bitmapPoolBackend.mCurrentItems.add(bitmap);
                }
                if (add) {
                    BucketMap<T> bucketMap = bitmapPoolBackend.mMap;
                    int size2 = bitmapPoolBackend.getSize(bitmap);
                    synchronized (bucketMap) {
                        BucketMap.LinkedEntry linkedEntry = (BucketMap.LinkedEntry) bucketMap.mMap.get(size2);
                        if (linkedEntry == null) {
                            BucketMap.LinkedEntry linkedEntry2 = new BucketMap.LinkedEntry(null, size2, new LinkedList(), null, null);
                            bucketMap.mMap.put(size2, linkedEntry2);
                            linkedEntry = linkedEntry2;
                        }
                        linkedEntry.value.addLast(bitmap);
                        bucketMap.moveToFront(linkedEntry);
                    }
                }
            }
            synchronized (this) {
                this.mCurrentSize += size;
            }
        }
    }
}
