package com.facebook.drawee.drawable;

import android.graphics.Matrix;
import android.graphics.Rect;
/* loaded from: classes.dex */
public class ScalingUtils$ScaleTypeCenterInside extends ScalingUtils$AbstractScaleType {
    public static final ScalingUtils$ScaleType INSTANCE = new ScalingUtils$ScaleTypeCenterInside();

    @Override // com.facebook.drawee.drawable.ScalingUtils$AbstractScaleType
    public void getTransformImpl(Matrix matrix, Rect rect, int i, int i2, float f, float f2, float f3, float f4) {
        float min = Math.min(Math.min(f3, f4), 1.0f);
        float width = ((rect.width() - (i * min)) * 0.5f) + rect.left;
        float height = (rect.height() - (i2 * min)) * 0.5f;
        matrix.setScale(min, min);
        matrix.postTranslate((int) (width + 0.5f), (int) (height + rect.top + 0.5f));
    }

    public String toString() {
        return "center_inside";
    }
}
