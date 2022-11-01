package com.google.android.material.shape;

import android.graphics.RectF;
import java.util.Arrays;
/* loaded from: classes.dex */
public final class RelativeCornerSize implements CornerSize {
    public final float percent;

    public RelativeCornerSize(float f) {
        this.percent = f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RelativeCornerSize) && this.percent == ((RelativeCornerSize) obj).percent;
    }

    @Override // com.google.android.material.shape.CornerSize
    public float getCornerSize(RectF rectF) {
        return rectF.height() * this.percent;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.percent)});
    }
}
