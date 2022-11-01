package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
@TargetApi(23)
/* loaded from: classes.dex */
public abstract class ReactBaseTextShadowNode extends LayoutShadowNode {
    public boolean mAdjustsFontSizeToFit;
    public int mBackgroundColor;
    public int mColor;
    public boolean mContainsImages;
    public String mFontFamily;
    public String mFontFeatureSettings;
    public int mFontStyle;
    public int mFontWeight;
    public boolean mIncludeFontPadding;
    public Map<Integer, ReactShadowNode> mInlineViews;
    public boolean mIsLineThroughTextDecorationSet;
    public boolean mIsUnderlineTextDecorationSet;
    public int mJustificationMode;
    public float mMinimumFontScale;
    public TextAttributes mTextAttributes;
    public int mTextBreakStrategy;
    public int mTextShadowColor;
    public float mTextShadowOffsetDx;
    public float mTextShadowOffsetDy;
    public float mTextShadowRadius;
    public boolean mIsColorSet = false;
    public boolean mIsBackgroundColorSet = false;
    public int mNumberOfLines = -1;
    public int mTextAlign = 0;

    /* loaded from: classes.dex */
    public static class SetSpanOperation {
        public int end;
        public int start;
        public ReactSpan what;

        public SetSpanOperation(int i, int i2, ReactSpan reactSpan) {
            this.start = i;
            this.end = i2;
            this.what = reactSpan;
        }
    }

    public ReactBaseTextShadowNode() {
        this.mTextBreakStrategy = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        this.mJustificationMode = 0;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = 0.0f;
        this.mTextShadowColor = 1426063360;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mIncludeFontPadding = true;
        this.mAdjustsFontSizeToFit = false;
        this.mMinimumFontScale = 0.0f;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        this.mFontFamily = null;
        this.mFontFeatureSettings = null;
        this.mContainsImages = false;
        this.mTextAttributes = new TextAttributes();
    }

    public static void buildSpannedFromShadowNode(ReactBaseTextShadowNode reactBaseTextShadowNode, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list, TextAttributes textAttributes, boolean z, Map<Integer, ReactShadowNode> map, int i) {
        TextAttributes textAttributes2;
        if (textAttributes != null) {
            TextAttributes textAttributes3 = reactBaseTextShadowNode.mTextAttributes;
            textAttributes2 = new TextAttributes();
            textAttributes2.mAllowFontScaling = textAttributes.mAllowFontScaling;
            textAttributes2.mFontSize = !Float.isNaN(textAttributes3.mFontSize) ? textAttributes3.mFontSize : textAttributes.mFontSize;
            textAttributes2.mLineHeight = !Float.isNaN(textAttributes3.mLineHeight) ? textAttributes3.mLineHeight : textAttributes.mLineHeight;
            textAttributes2.mLetterSpacing = !Float.isNaN(textAttributes3.mLetterSpacing) ? textAttributes3.mLetterSpacing : textAttributes.mLetterSpacing;
            textAttributes2.mMaxFontSizeMultiplier = !Float.isNaN(textAttributes3.mMaxFontSizeMultiplier) ? textAttributes3.mMaxFontSizeMultiplier : textAttributes.mMaxFontSizeMultiplier;
            textAttributes2.mHeightOfTallestInlineViewOrImage = !Float.isNaN(textAttributes3.mHeightOfTallestInlineViewOrImage) ? textAttributes3.mHeightOfTallestInlineViewOrImage : textAttributes.mHeightOfTallestInlineViewOrImage;
            TextTransform textTransform = textAttributes3.mTextTransform;
            if (textTransform == TextTransform.UNSET) {
                textTransform = textAttributes.mTextTransform;
            }
            textAttributes2.mTextTransform = textTransform;
        } else {
            textAttributes2 = reactBaseTextShadowNode.mTextAttributes;
        }
        int childCount = reactBaseTextShadowNode.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            ReactShadowNodeImpl childAt = reactBaseTextShadowNode.getChildAt(i2);
            if (childAt instanceof ReactRawTextShadowNode) {
                spannableStringBuilder.append((CharSequence) TextTransform.apply(((ReactRawTextShadowNode) childAt).mText, textAttributes2.mTextTransform));
            } else if (childAt instanceof ReactBaseTextShadowNode) {
                buildSpannedFromShadowNode((ReactBaseTextShadowNode) childAt, spannableStringBuilder, list, textAttributes2, z, map, spannableStringBuilder.length());
            } else if (childAt instanceof ReactTextInlineImageShadowNode) {
                spannableStringBuilder.append("0");
                list.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), ((ReactTextInlineImageShadowNode) childAt).buildInlineImageSpan()));
            } else if (z) {
                int i3 = childAt.mReactTag;
                YogaValue width = childAt.mYogaNode.getWidth();
                YogaValue height = childAt.mYogaNode.getHeight();
                if (width.unit == 2 && height.unit == 2) {
                    float f = width.value;
                    float f2 = height.value;
                    spannableStringBuilder.append("0");
                    list.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), new TextInlineViewPlaceholderSpan(i3, (int) f, (int) f2)));
                    map.put(Integer.valueOf(i3), childAt);
                    childAt.markUpdateSeen();
                } else {
                    throw new IllegalViewOperationException("Views nested within a <Text> must have a width and height");
                }
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected view type nested under a <Text> or <TextInput> node: ");
                outline13.append(childAt.getClass());
                throw new IllegalViewOperationException(outline13.toString());
            }
            childAt.markUpdateSeen();
        }
        int length = spannableStringBuilder.length();
        if (length >= i) {
            if (reactBaseTextShadowNode.mIsColorSet) {
                list.add(new SetSpanOperation(i, length, new ReactForegroundColorSpan(reactBaseTextShadowNode.mColor)));
            }
            if (reactBaseTextShadowNode.mIsBackgroundColorSet) {
                list.add(new SetSpanOperation(i, length, new ReactBackgroundColorSpan(reactBaseTextShadowNode.mBackgroundColor)));
            }
            float effectiveLetterSpacing = textAttributes2.getEffectiveLetterSpacing();
            if (!Float.isNaN(effectiveLetterSpacing) && (textAttributes == null || textAttributes.getEffectiveLetterSpacing() != effectiveLetterSpacing)) {
                list.add(new SetSpanOperation(i, length, new CustomLetterSpacingSpan(effectiveLetterSpacing)));
            }
            int effectiveFontSize = textAttributes2.getEffectiveFontSize();
            if (textAttributes == null || textAttributes.getEffectiveFontSize() != effectiveFontSize) {
                list.add(new SetSpanOperation(i, length, new ReactAbsoluteSizeSpan(effectiveFontSize)));
            }
            if (!(reactBaseTextShadowNode.mFontStyle == -1 && reactBaseTextShadowNode.mFontWeight == -1 && reactBaseTextShadowNode.mFontFamily == null)) {
                list.add(new SetSpanOperation(i, length, new CustomStyleSpan(reactBaseTextShadowNode.mFontStyle, reactBaseTextShadowNode.mFontWeight, reactBaseTextShadowNode.mFontFeatureSettings, reactBaseTextShadowNode.mFontFamily, reactBaseTextShadowNode.getThemedContext().getAssets())));
            }
            if (reactBaseTextShadowNode.mIsUnderlineTextDecorationSet) {
                list.add(new SetSpanOperation(i, length, new ReactUnderlineSpan()));
            }
            if (reactBaseTextShadowNode.mIsLineThroughTextDecorationSet) {
                list.add(new SetSpanOperation(i, length, new ReactStrikethroughSpan()));
            }
            if (!((reactBaseTextShadowNode.mTextShadowOffsetDx == 0.0f && reactBaseTextShadowNode.mTextShadowOffsetDy == 0.0f && reactBaseTextShadowNode.mTextShadowRadius == 0.0f) || Color.alpha(reactBaseTextShadowNode.mTextShadowColor) == 0)) {
                list.add(new SetSpanOperation(i, length, new ShadowStyleSpan(reactBaseTextShadowNode.mTextShadowOffsetDx, reactBaseTextShadowNode.mTextShadowOffsetDy, reactBaseTextShadowNode.mTextShadowRadius, reactBaseTextShadowNode.mTextShadowColor)));
            }
            float effectiveLineHeight = textAttributes2.getEffectiveLineHeight();
            if (!Float.isNaN(effectiveLineHeight) && (textAttributes == null || textAttributes.getEffectiveLineHeight() != effectiveLineHeight)) {
                list.add(new SetSpanOperation(i, length, new CustomLineHeightSpan(effectiveLineHeight)));
            }
            list.add(new SetSpanOperation(i, length, new ReactTagSpan(reactBaseTextShadowNode.mReactTag)));
        }
    }

    @ReactProp(name = "adjustsFontSizeToFit")
    public void setAdjustFontSizeToFit(boolean z) {
        if (z != this.mAdjustsFontSizeToFit) {
            this.mAdjustsFontSizeToFit = z;
            markUpdated();
        }
    }

    @ReactProp(defaultBoolean = true, name = "allowFontScaling")
    public void setAllowFontScaling(boolean z) {
        TextAttributes textAttributes = this.mTextAttributes;
        if (z != textAttributes.mAllowFontScaling) {
            textAttributes.mAllowFontScaling = z;
            markUpdated();
        }
    }

    @ReactProp(customType = "Color", name = "backgroundColor")
    public void setBackgroundColor(Integer num) {
        if (isVirtual()) {
            boolean z = num != null;
            this.mIsBackgroundColorSet = z;
            if (z) {
                this.mBackgroundColor = num.intValue();
            }
            markUpdated();
        }
    }

    @ReactProp(name = "color")
    public void setColor(Integer num) {
        boolean z = num != null;
        this.mIsColorSet = z;
        if (z) {
            this.mColor = num.intValue();
        }
        markUpdated();
    }

    @ReactProp(name = "fontFamily")
    public void setFontFamily(String str) {
        this.mFontFamily = str;
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = "fontSize")
    public void setFontSize(float f) {
        this.mTextAttributes.mFontSize = f;
        markUpdated();
    }

    @ReactProp(name = "fontStyle")
    public void setFontStyle(String str) {
        int parseFontStyle = R$style.parseFontStyle(str);
        if (parseFontStyle != this.mFontStyle) {
            this.mFontStyle = parseFontStyle;
            markUpdated();
        }
    }

    @ReactProp(name = "fontVariant")
    public void setFontVariant(ReadableArray readableArray) {
        String parseFontVariant = R$style.parseFontVariant(readableArray);
        if (!Objects.equals(parseFontVariant, this.mFontFeatureSettings)) {
            this.mFontFeatureSettings = parseFontVariant;
            markUpdated();
        }
    }

    @ReactProp(name = "fontWeight")
    public void setFontWeight(String str) {
        int parseFontWeight = R$style.parseFontWeight(str);
        if (parseFontWeight != this.mFontWeight) {
            this.mFontWeight = parseFontWeight;
            markUpdated();
        }
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(boolean z) {
        this.mIncludeFontPadding = z;
    }

    @ReactProp(defaultFloat = Float.NaN, name = "letterSpacing")
    public void setLetterSpacing(float f) {
        this.mTextAttributes.mLetterSpacing = f;
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = "lineHeight")
    public void setLineHeight(float f) {
        this.mTextAttributes.mLineHeight = f;
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = "maxFontSizeMultiplier")
    public void setMaxFontSizeMultiplier(float f) {
        TextAttributes textAttributes = this.mTextAttributes;
        if (f != textAttributes.mMaxFontSizeMultiplier) {
            textAttributes.setMaxFontSizeMultiplier(f);
            markUpdated();
        }
    }

    @ReactProp(name = "minimumFontScale")
    public void setMinimumFontScale(float f) {
        if (f != this.mMinimumFontScale) {
            this.mMinimumFontScale = f;
            markUpdated();
        }
    }

    @ReactProp(defaultInt = -1, name = "numberOfLines")
    public void setNumberOfLines(int i) {
        if (i == 0) {
            i = -1;
        }
        this.mNumberOfLines = i;
        markUpdated();
    }

    @ReactProp(name = "textAlign")
    public void setTextAlign(String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mJustificationMode = 1;
            }
            this.mTextAlign = 3;
        } else {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mJustificationMode = 0;
            }
            if (str == null || "auto".equals(str)) {
                this.mTextAlign = 0;
            } else if ("left".equals(str)) {
                this.mTextAlign = 3;
            } else if ("right".equals(str)) {
                this.mTextAlign = 5;
            } else if ("center".equals(str)) {
                this.mTextAlign = 1;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textAlign: ", str));
            }
        }
        markUpdated();
    }

    @ReactProp(name = "textBreakStrategy")
    public void setTextBreakStrategy(String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (str == null || "highQuality".equals(str)) {
                this.mTextBreakStrategy = 1;
            } else if ("simple".equals(str)) {
                this.mTextBreakStrategy = 0;
            } else if ("balanced".equals(str)) {
                this.mTextBreakStrategy = 2;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textBreakStrategy: ", str));
            }
            markUpdated();
        }
    }

    @ReactProp(name = "textDecorationLine")
    public void setTextDecorationLine(String str) {
        String[] split;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (str != null) {
            for (String str2 : str.split(" ")) {
                if ("underline".equals(str2)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("line-through".equals(str2)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
        markUpdated();
    }

    @ReactProp(customType = "Color", defaultInt = 1426063360, name = "textShadowColor")
    public void setTextShadowColor(int i) {
        if (i != this.mTextShadowColor) {
            this.mTextShadowColor = i;
            markUpdated();
        }
    }

    @ReactProp(name = "textShadowOffset")
    public void setTextShadowOffset(ReadableMap readableMap) {
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
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = "textShadowRadius")
    public void setTextShadowRadius(float f) {
        if (f != this.mTextShadowRadius) {
            this.mTextShadowRadius = f;
            markUpdated();
        }
    }

    @ReactProp(name = "textTransform")
    public void setTextTransform(String str) {
        if (str == null) {
            this.mTextAttributes.mTextTransform = TextTransform.UNSET;
        } else if ("none".equals(str)) {
            this.mTextAttributes.mTextTransform = TextTransform.NONE;
        } else if ("uppercase".equals(str)) {
            this.mTextAttributes.mTextTransform = TextTransform.UPPERCASE;
        } else if ("lowercase".equals(str)) {
            this.mTextAttributes.mTextTransform = TextTransform.LOWERCASE;
        } else if ("capitalize".equals(str)) {
            this.mTextAttributes.mTextTransform = TextTransform.CAPITALIZE;
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Invalid textTransform: ", str));
        }
        markUpdated();
    }

    public Spannable spannedFromShadowNode(ReactBaseTextShadowNode reactBaseTextShadowNode, String str, boolean z, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        int i;
        int i2 = 0;
        R$dimen.assertCondition(!z || nativeViewHierarchyOptimizer != null, "nativeViewHierarchyOptimizer is required when inline views are supported");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = z ? new HashMap() : null;
        if (str != null) {
            spannableStringBuilder.append((CharSequence) TextTransform.apply(str, reactBaseTextShadowNode.mTextAttributes.mTextTransform));
        }
        buildSpannedFromShadowNode(reactBaseTextShadowNode, spannableStringBuilder, arrayList, null, z, hashMap, 0);
        reactBaseTextShadowNode.mContainsImages = false;
        reactBaseTextShadowNode.mInlineViews = hashMap;
        float f = Float.NaN;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            SetSpanOperation setSpanOperation = (SetSpanOperation) it.next();
            ReactSpan reactSpan = setSpanOperation.what;
            boolean z2 = reactSpan instanceof TextInlineImageSpan;
            if (z2 || (reactSpan instanceof TextInlineViewPlaceholderSpan)) {
                if (z2) {
                    i = ((TextInlineImageSpan) reactSpan).getHeight();
                    reactBaseTextShadowNode.mContainsImages = true;
                } else {
                    TextInlineViewPlaceholderSpan textInlineViewPlaceholderSpan = (TextInlineViewPlaceholderSpan) reactSpan;
                    int i3 = textInlineViewPlaceholderSpan.mHeight;
                    ReactShadowNode reactShadowNode = hashMap.get(Integer.valueOf(textInlineViewPlaceholderSpan.mReactTag));
                    Objects.requireNonNull(nativeViewHierarchyOptimizer);
                    if (reactShadowNode.isLayoutOnly()) {
                        nativeViewHierarchyOptimizer.transitionLayoutOnlyViewToNativeView(reactShadowNode, null);
                    }
                    reactShadowNode.setLayoutParent(reactBaseTextShadowNode);
                    i = i3;
                }
                if (Float.isNaN(f) || i > f) {
                    f = i;
                }
            }
            int i4 = setSpanOperation.start;
            spannableStringBuilder.setSpan(setSpanOperation.what, i4, setSpanOperation.end, ((i4 == 0 ? 18 : 34) & (-16711681)) | ((i2 << 16) & 16711680));
            i2++;
        }
        reactBaseTextShadowNode.mTextAttributes.mHeightOfTallestInlineViewOrImage = f;
        return spannableStringBuilder;
    }
}
