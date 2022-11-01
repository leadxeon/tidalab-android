package com.facebook.react.views.text;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
/* loaded from: classes.dex */
public class ShadowStyleSpan extends CharacterStyle implements ReactSpan {
    public final int mColor;
    public final float mDx;
    public final float mDy;
    public final float mRadius;

    public ShadowStyleSpan(float f, float f2, float f3, int i) {
        this.mDx = f;
        this.mDy = f2;
        this.mRadius = f3;
        this.mColor = i;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setShadowLayer(this.mRadius, this.mDx, this.mDy, this.mColor);
    }
}
