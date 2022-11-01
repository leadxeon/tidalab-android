package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.RectF;
/* loaded from: classes.dex */
public interface TransformCallback {
    void getRootBounds(RectF rectF);

    void getTransform(Matrix matrix);
}
