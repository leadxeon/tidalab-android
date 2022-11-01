package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
/* loaded from: classes.dex */
public abstract class CloseableBitmap extends CloseableImage {
    public abstract Bitmap getUnderlyingBitmap();
}
