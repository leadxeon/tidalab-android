package com.facebook.react.views.text;

import android.os.Build;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactStylesDiffMap;
/* loaded from: classes.dex */
public class TextAttributeProps {
    public boolean mAllowFontScaling;
    public int mBackgroundColor;
    public int mColor;
    public String mFontFamily;
    public String mFontFeatureSettings;
    public int mFontStyle;
    public int mFontWeight;
    public boolean mIsBackgroundColorSet;
    public boolean mIsColorSet;
    public boolean mIsLineThroughTextDecorationSet;
    public boolean mIsUnderlineTextDecorationSet;
    public float mLetterSpacingInput;
    public final ReactStylesDiffMap mProps;
    public int mTextAlign;
    public int mTextShadowColor;
    public float mTextShadowOffsetDx;
    public float mTextShadowOffsetDy;
    public float mTextShadowRadius;
    public TextTransform mTextTransform;
    public float mLineHeight = Float.NaN;
    public int mFontSize = -1;
    public float mFontSizeInput = -1.0f;
    public float mLineHeightInput = -1.0f;
    public float mHeightOfTallestInlineImage = Float.NaN;

    public TextAttributeProps(ReactStylesDiffMap reactStylesDiffMap) {
        int i;
        String[] split;
        this.mIsColorSet = false;
        this.mAllowFontScaling = true;
        this.mIsBackgroundColorSet = false;
        int i2 = -1;
        this.mLetterSpacingInput = Float.NaN;
        this.mTextAlign = 0;
        this.mTextTransform = TextTransform.UNSET;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = 1.0f;
        this.mTextShadowColor = 1426063360;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        ReadableMap readableMap = null;
        this.mFontFamily = null;
        this.mFontFeatureSettings = null;
        this.mProps = reactStylesDiffMap;
        getIntProp("numberOfLines", -1);
        setLineHeight(getFloatProp("lineHeight", -1.0f));
        this.mLetterSpacingInput = getFloatProp("letterSpacing", Float.NaN);
        boolean booleanProp = getBooleanProp("allowFontScaling", true);
        if (booleanProp != this.mAllowFontScaling) {
            this.mAllowFontScaling = booleanProp;
            setFontSize(this.mFontSizeInput);
            setLineHeight(this.mLineHeightInput);
            this.mLetterSpacingInput = this.mLetterSpacingInput;
        }
        String stringProp = getStringProp("textAlign");
        if ("justify".equals(stringProp)) {
            this.mTextAlign = 3;
        } else if (stringProp == null || "auto".equals(stringProp)) {
            this.mTextAlign = 0;
        } else if ("left".equals(stringProp)) {
            this.mTextAlign = 3;
        } else if ("right".equals(stringProp)) {
            this.mTextAlign = 5;
        } else if ("center".equals(stringProp)) {
            this.mTextAlign = 1;
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textAlign: ", stringProp));
        }
        setFontSize(getFloatProp("fontSize", -1.0f));
        Integer valueOf = reactStylesDiffMap.mBackingMap.hasKey("color") ? Integer.valueOf(reactStylesDiffMap.getInt("color", 0)) : null;
        boolean z = valueOf != null;
        this.mIsColorSet = z;
        if (z) {
            this.mColor = valueOf.intValue();
        }
        Integer valueOf2 = reactStylesDiffMap.mBackingMap.hasKey("foregroundColor") ? Integer.valueOf(reactStylesDiffMap.getInt("foregroundColor", 0)) : null;
        boolean z2 = valueOf2 != null;
        this.mIsColorSet = z2;
        if (z2) {
            this.mColor = valueOf2.intValue();
        }
        Integer valueOf3 = reactStylesDiffMap.mBackingMap.hasKey("backgroundColor") ? Integer.valueOf(reactStylesDiffMap.getInt("backgroundColor", 0)) : null;
        boolean z3 = valueOf3 != null;
        this.mIsBackgroundColorSet = z3;
        if (z3) {
            this.mBackgroundColor = valueOf3.intValue();
        }
        this.mFontFamily = getStringProp("fontFamily");
        String stringProp2 = getStringProp("fontWeight");
        int charAt = (stringProp2 == null || stringProp2.length() != 3 || !stringProp2.endsWith("00") || stringProp2.charAt(0) > '9' || stringProp2.charAt(0) < '1') ? -1 : (stringProp2.charAt(0) - '0') * 100;
        if (charAt >= 500 || "bold".equals(stringProp2)) {
            i = 1;
        } else {
            i = ("normal".equals(stringProp2) || (charAt != -1 && charAt < 500)) ? 0 : -1;
        }
        if (i != this.mFontWeight) {
            this.mFontWeight = i;
        }
        String stringProp3 = getStringProp("fontStyle");
        if ("italic".equals(stringProp3)) {
            i2 = 2;
        } else if ("normal".equals(stringProp3)) {
            i2 = 0;
        }
        if (i2 != this.mFontStyle) {
            this.mFontStyle = i2;
        }
        this.mFontFeatureSettings = R$style.parseFontVariant(reactStylesDiffMap.mBackingMap.hasKey("fontVariant") ? reactStylesDiffMap.mBackingMap.getArray("fontVariant") : null);
        getBooleanProp("includeFontPadding", true);
        String stringProp4 = getStringProp("textDecorationLine");
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (stringProp4 != null) {
            for (String str : stringProp4.split("-")) {
                if ("underline".equals(str)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("strikethrough".equals(str)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
        String stringProp5 = getStringProp("textBreakStrategy");
        if (Build.VERSION.SDK_INT >= 23 && stringProp5 != null && !"highQuality".equals(stringProp5) && !"simple".equals(stringProp5) && !"balanced".equals(stringProp5)) {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textBreakStrategy: ", stringProp5));
        }
        readableMap = reactStylesDiffMap.mBackingMap.hasKey("textShadowOffset") ? reactStylesDiffMap.mBackingMap.getMap("textShadowOffset") : readableMap;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (readableMap.hasKey("height") && !readableMap.isNull("height")) {
                this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
            }
        }
        float intProp = getIntProp("textShadowRadius", 1);
        if (intProp != this.mTextShadowRadius) {
            this.mTextShadowRadius = intProp;
        }
        int intProp2 = getIntProp("textShadowColor", 1426063360);
        if (intProp2 != this.mTextShadowColor) {
            this.mTextShadowColor = intProp2;
        }
        String stringProp6 = getStringProp("textTransform");
        if (stringProp6 == null || "none".equals(stringProp6)) {
            this.mTextTransform = TextTransform.NONE;
        } else if ("uppercase".equals(stringProp6)) {
            this.mTextTransform = TextTransform.UPPERCASE;
        } else if ("lowercase".equals(stringProp6)) {
            this.mTextTransform = TextTransform.LOWERCASE;
        } else if ("capitalize".equals(stringProp6)) {
            this.mTextTransform = TextTransform.CAPITALIZE;
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textTransform: ", stringProp6));
        }
    }

    public final boolean getBooleanProp(String str, boolean z) {
        if (!this.mProps.mBackingMap.hasKey(str)) {
            return z;
        }
        ReactStylesDiffMap reactStylesDiffMap = this.mProps;
        return reactStylesDiffMap.mBackingMap.isNull(str) ? z : reactStylesDiffMap.mBackingMap.getBoolean(str);
    }

    public float getEffectiveLineHeight() {
        return !Float.isNaN(this.mLineHeight) && !Float.isNaN(this.mHeightOfTallestInlineImage) && (this.mHeightOfTallestInlineImage > this.mLineHeight ? 1 : (this.mHeightOfTallestInlineImage == this.mLineHeight ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineImage : this.mLineHeight;
    }

    public final float getFloatProp(String str, float f) {
        if (!this.mProps.mBackingMap.hasKey(str)) {
            return f;
        }
        ReactStylesDiffMap reactStylesDiffMap = this.mProps;
        return reactStylesDiffMap.mBackingMap.isNull(str) ? f : (float) reactStylesDiffMap.mBackingMap.getDouble(str);
    }

    public final int getIntProp(String str, int i) {
        return this.mProps.mBackingMap.hasKey(str) ? this.mProps.getInt(str, i) : i;
    }

    public float getLetterSpacing() {
        float f;
        if (this.mAllowFontScaling) {
            f = PixelUtil.toPixelFromSP(this.mLetterSpacingInput);
        } else {
            f = PixelUtil.toPixelFromDIP(this.mLetterSpacingInput);
        }
        int i = this.mFontSize;
        if (i > 0) {
            return f / i;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("FontSize should be a positive value. Current value: ");
        outline13.append(this.mFontSize);
        throw new IllegalArgumentException(outline13.toString());
    }

    public final String getStringProp(String str) {
        if (this.mProps.mBackingMap.hasKey(str)) {
            return this.mProps.mBackingMap.getString(str);
        }
        return null;
    }

    public void setFontSize(float f) {
        double d;
        this.mFontSizeInput = f;
        if (f != -1.0f) {
            if (this.mAllowFontScaling) {
                d = Math.ceil(PixelUtil.toPixelFromSP(f));
            } else {
                d = Math.ceil(PixelUtil.toPixelFromDIP(f));
            }
            f = (float) d;
        }
        this.mFontSize = (int) f;
    }

    public void setLineHeight(float f) {
        float f2;
        this.mLineHeightInput = f;
        if (f == -1.0f) {
            this.mLineHeight = Float.NaN;
            return;
        }
        if (this.mAllowFontScaling) {
            f2 = PixelUtil.toPixelFromSP(f);
        } else {
            f2 = PixelUtil.toPixelFromDIP(f);
        }
        this.mLineHeight = f2;
    }
}
