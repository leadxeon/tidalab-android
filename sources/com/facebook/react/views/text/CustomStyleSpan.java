package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import com.facebook.react.R$style;
/* loaded from: classes.dex */
public class CustomStyleSpan extends MetricAffectingSpan implements ReactSpan {
    public final AssetManager mAssetManager;
    public final String mFeatureSettings;
    public final String mFontFamily;
    public final int mStyle;
    public final int mWeight;

    public CustomStyleSpan(int i, int i2, String str, String str2, AssetManager assetManager) {
        this.mStyle = i;
        this.mWeight = i2;
        this.mFeatureSettings = str;
        this.mFontFamily = str2;
        this.mAssetManager = assetManager;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        int i = this.mStyle;
        int i2 = this.mWeight;
        String str = this.mFeatureSettings;
        Typeface applyStyles = R$style.applyStyles(textPaint.getTypeface(), i, i2, this.mFontFamily, this.mAssetManager);
        textPaint.setFontFeatureSettings(str);
        textPaint.setTypeface(applyStyles);
        textPaint.setSubpixelText(true);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        int i = this.mStyle;
        int i2 = this.mWeight;
        String str = this.mFeatureSettings;
        Typeface applyStyles = R$style.applyStyles(textPaint.getTypeface(), i, i2, this.mFontFamily, this.mAssetManager);
        textPaint.setFontFeatureSettings(str);
        textPaint.setTypeface(applyStyles);
        textPaint.setSubpixelText(true);
    }
}
