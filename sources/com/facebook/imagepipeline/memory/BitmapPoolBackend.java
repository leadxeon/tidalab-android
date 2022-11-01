package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.memory.BucketMap;
import com.facebook.imageutils.BitmapUtil;
/* loaded from: classes.dex */
public class BitmapPoolBackend extends LruBucketsPoolBackend<Bitmap> {
    @Override // com.facebook.imagepipeline.memory.LruBucketsPoolBackend
    public Bitmap get(int i) {
        Object obj;
        BucketMap<T> bucketMap = this.mMap;
        synchronized (bucketMap) {
            BucketMap.LinkedEntry linkedEntry = (BucketMap.LinkedEntry) bucketMap.mMap.get(i);
            if (linkedEntry == null) {
                obj = null;
            } else {
                obj = linkedEntry.value.pollFirst();
                bucketMap.moveToFront(linkedEntry);
            }
        }
        if (obj != null) {
            synchronized (this) {
                this.mCurrentItems.remove(obj);
            }
        }
        Bitmap bitmap = (Bitmap) obj;
        if (bitmap == null || !isReusable(bitmap)) {
            return null;
        }
        bitmap.eraseColor(0);
        return bitmap;
    }

    @Override // com.facebook.imagepipeline.memory.LruBucketsPoolBackend
    public int getSize(Bitmap bitmap) {
        return BitmapUtil.getSizeInBytes(bitmap);
    }

    public boolean isReusable(Bitmap bitmap) {
        if (bitmap == null) {
            return false;
        }
        if (bitmap.isRecycled()) {
            FLog.wtf("BitmapPoolBackend", "Cannot reuse a recycled bitmap: %s", bitmap);
            return false;
        } else if (bitmap.isMutable()) {
            return true;
        } else {
            FLog.wtf("BitmapPoolBackend", "Cannot reuse an immutable bitmap: %s", bitmap);
            return false;
        }
    }
}
