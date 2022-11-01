package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import com.facebook.common.references.ResourceReleaser;
/* loaded from: classes.dex */
public class SimpleBitmapReleaser implements ResourceReleaser<Bitmap> {
    public static SimpleBitmapReleaser sInstance;

    public static SimpleBitmapReleaser getInstance() {
        if (sInstance == null) {
            sInstance = new SimpleBitmapReleaser();
        }
        return sInstance;
    }

    @Override // com.facebook.common.references.ResourceReleaser
    public void release(Bitmap bitmap) {
        bitmap.recycle();
    }
}
