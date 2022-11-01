package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
/* loaded from: classes.dex */
public abstract class PlatformBitmapFactory {
    public abstract CloseableReference<Bitmap> createBitmapInternal(int i, int i2, Bitmap.Config config);
}
