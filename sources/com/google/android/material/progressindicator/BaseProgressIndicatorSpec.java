package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public abstract class BaseProgressIndicatorSpec {
    public int hideAnimationBehavior;
    public int[] indicatorColors;
    public int showAnimationBehavior;
    public int trackColor;
    public int trackCornerRadius;
    public int trackThickness;

    public BaseProgressIndicatorSpec(Context context, AttributeSet attributeSet, int i, int i2) {
        this.indicatorColors = new int[0];
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.mtrl_progress_track_thickness);
        int[] iArr = R$styleable.BaseProgressIndicator;
        ThemeEnforcement.checkCompatibleTheme(context, attributeSet, i, i2);
        ThemeEnforcement.checkTextAppearance(context, attributeSet, iArr, i, i2, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i, i2);
        this.trackThickness = R$style.getDimensionPixelSize(context, obtainStyledAttributes, 8, dimensionPixelSize);
        this.trackCornerRadius = Math.min(R$style.getDimensionPixelSize(context, obtainStyledAttributes, 7, 0), this.trackThickness / 2);
        this.showAnimationBehavior = obtainStyledAttributes.getInt(4, 0);
        this.hideAnimationBehavior = obtainStyledAttributes.getInt(1, 0);
        if (!obtainStyledAttributes.hasValue(2)) {
            this.indicatorColors = new int[]{R$style.getColor(context, R.attr.colorPrimary, -1)};
        } else if (obtainStyledAttributes.peekValue(2).type != 1) {
            this.indicatorColors = new int[]{obtainStyledAttributes.getColor(2, -1)};
        } else {
            int[] intArray = context.getResources().getIntArray(obtainStyledAttributes.getResourceId(2, -1));
            this.indicatorColors = intArray;
            if (intArray.length == 0) {
                throw new IllegalArgumentException("indicatorColors cannot be empty when indicatorColor is not used.");
            }
        }
        if (obtainStyledAttributes.hasValue(6)) {
            this.trackColor = obtainStyledAttributes.getColor(6, -1);
        } else {
            this.trackColor = this.indicatorColors[0];
            TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(new int[]{16842803});
            float f = obtainStyledAttributes2.getFloat(0, 0.2f);
            obtainStyledAttributes2.recycle();
            this.trackColor = R$style.compositeARGBWithAlpha(this.trackColor, (int) (f * 255.0f));
        }
        obtainStyledAttributes.recycle();
    }

    public abstract void validateSpec();
}
