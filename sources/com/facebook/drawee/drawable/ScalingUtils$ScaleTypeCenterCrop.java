package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
/* loaded from: classes.dex */
public class ScalingUtils$ScaleTypeCenterCrop extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeCenterCrop();

    @Override // com.facebook.drawee.drawable.ScalingUtils$AbstractScaleType
    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float f5;
        float f6;
        if (f4 > f3) {
            f5 = ((rect.width() - (i * f4)) * 0.5f) + rect.left;
            f6 = rect.top;
            f3 = f4;
        } else {
            f5 = rect.left;
            f6 = ((rect.height() - (i2 * f3)) * 0.5f) + rect.top;
        }
        matrix.setScale(f3, f3);
        matrix.postTranslate((int) (f5 + 0.5f), (int) (f6 + 0.5f));
    }

    public String toString() {
        return "center_crop";
    }
}
