package com.facebook.react.views.text;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.PixelUtil;
/* loaded from: classes.dex */
public class TextAttributes {
    public boolean mAllowFontScaling = true;
    public float mFontSize = Float.NaN;
    public float mLineHeight = Float.NaN;
    public float mLetterSpacing = Float.NaN;
    public float mMaxFontSizeMultiplier = Float.NaN;
    public float mHeightOfTallestInlineViewOrImage = Float.NaN;
    public TextTransform mTextTransform = TextTransform.UNSET;

    public int getEffectiveFontSize() {
        double d;
        float f = !Float.isNaN(this.mFontSize) ? this.mFontSize : 14.0f;
        if (this.mAllowFontScaling) {
            d = Math.ceil(PixelUtil.toPixelFromSP(f, getEffectiveMaxFontSizeMultiplier()));
        } else {
            d = Math.ceil(PixelUtil.toPixelFromDIP(f));
        }
        return (int) d;
    }

    public float getEffectiveLetterSpacing() {
        float f;
        if (Float.isNaN(this.mLetterSpacing)) {
            return Float.NaN;
        }
        if (this.mAllowFontScaling) {
            f = PixelUtil.toPixelFromSP(this.mLetterSpacing, getEffectiveMaxFontSizeMultiplier());
        } else {
            f = PixelUtil.toPixelFromDIP(this.mLetterSpacing);
        }
        return f / getEffectiveFontSize();
    }

    public float getEffectiveLineHeight() {
        float f;
        if (Float.isNaN(this.mLineHeight)) {
            return Float.NaN;
        }
        if (this.mAllowFontScaling) {
            f = PixelUtil.toPixelFromSP(this.mLineHeight, getEffectiveMaxFontSizeMultiplier());
        } else {
            f = PixelUtil.toPixelFromDIP(this.mLineHeight);
        }
        return !Float.isNaN(this.mHeightOfTallestInlineViewOrImage) && (this.mHeightOfTallestInlineViewOrImage > f ? 1 : (this.mHeightOfTallestInlineViewOrImage == f ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineViewOrImage : f;
    }

    public float getEffectiveMaxFontSizeMultiplier() {
        if (!Float.isNaN(this.mMaxFontSizeMultiplier)) {
            return this.mMaxFontSizeMultiplier;
        }
        return 0.0f;
    }

    public void setMaxFontSizeMultiplier(float f) {
        if (f == 0.0f || f >= 1.0f) {
            this.mMaxFontSizeMultiplier = f;
            return;
        }
        throw new JSApplicationIllegalArgumentException("maxFontSizeMultiplier must be NaN, 0, or >= 1");
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("TextAttributes {\n  getAllowFontScaling(): ");
        outline13.append(this.mAllowFontScaling);
        outline13.append("\n  getFontSize(): ");
        outline13.append(this.mFontSize);
        outline13.append("\n  getEffectiveFontSize(): ");
        outline13.append(getEffectiveFontSize());
        outline13.append("\n  getHeightOfTallestInlineViewOrImage(): ");
        outline13.append(this.mHeightOfTallestInlineViewOrImage);
        outline13.append("\n  getLetterSpacing(): ");
        outline13.append(this.mLetterSpacing);
        outline13.append("\n  getEffectiveLetterSpacing(): ");
        outline13.append(getEffectiveLetterSpacing());
        outline13.append("\n  getLineHeight(): ");
        outline13.append(this.mLineHeight);
        outline13.append("\n  getEffectiveLineHeight(): ");
        outline13.append(getEffectiveLineHeight());
        outline13.append("\n  getTextTransform(): ");
        outline13.append(this.mTextTransform);
        outline13.append("\n  getMaxFontSizeMultiplier(): ");
        outline13.append(this.mMaxFontSizeMultiplier);
        outline13.append("\n  getEffectiveMaxFontSizeMultiplier(): ");
        outline13.append(getEffectiveMaxFontSizeMultiplier());
        outline13.append("\n}");
        return outline13.toString();
    }
}
