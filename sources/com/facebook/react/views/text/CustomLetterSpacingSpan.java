package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
@TargetApi(21)
/* loaded from: classes.dex */
public class CustomLetterSpacingSpan extends MetricAffectingSpan implements ReactSpan {
    public final float mLetterSpacing;

    public CustomLetterSpacingSpan(float f) {
        this.mLetterSpacing = f;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        if (!Float.isNaN(this.mLetterSpacing)) {
            textPaint.setLetterSpacing(this.mLetterSpacing);
        }
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        if (!Float.isNaN(this.mLetterSpacing)) {
            textPaint.setLetterSpacing(this.mLetterSpacing);
        }
    }
}
