package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
/* loaded from: classes.dex */
public class ScalingUtils$ScaleTypeFitXY extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFitXY();

    @Override // com.facebook.drawee.drawable.ScalingUtils$AbstractScaleType
    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        matrix.setScale(f3, f4);
        matrix.postTranslate((int) (rect.left + 0.5f), (int) (rect.top + 0.5f));
    }

    public String toString() {
        return "fit_xy";
    }
}
