package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
/* loaded from: classes.dex */
public class ScalingUtils$ScaleTypeFocusCrop extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeFocusCrop();

    @Override // com.facebook.drawee.drawable.ScalingUtils$AbstractScaleType
    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float f5;
        float f6;
        if (f4 > f3) {
            float f7 = i * f4;
            f5 = Math.max(Math.min((rect.width() * 0.5f) - (f * f7), 0.0f), rect.width() - f7) + rect.left;
            f6 = rect.top;
            f3 = f4;
        } else {
            f5 = rect.left;
            float f8 = i2 * f3;
            f6 = Math.max(Math.min((rect.height() * 0.5f) - (f2 * f8), 0.0f), rect.height() - f8) + rect.top;
        }
        matrix.setScale(f3, f3);
        matrix.postTranslate((int) (f5 + 0.5f), (int) (f6 + 0.5f));
    }

    public String toString() {
        return "focus_crop";
    }
}
