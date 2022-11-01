package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import androidx.recyclerview.R$dimen;
import com.facebook.react.R$style;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.yoga.YogaDirection;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaNode;
import java.util.ArrayList;
import java.util.Map;
@TargetApi(23)
/* loaded from: classes.dex */
public class ReactTextShadowNode extends ReactBaseTextShadowNode {
    public static final TextPaint sTextPaintInstance = new TextPaint(1);
    public Spannable mPreparedSpannableText;
    public boolean mShouldNotifyOnTextLayout;
    public final YogaMeasureFunction mTextMeasureFunction;

    public ReactTextShadowNode() {
        YogaMeasureFunction yogaMeasureFunction = new YogaMeasureFunction() { // from class: com.facebook.react.views.text.ReactTextShadowNode.1
            @Override // com.facebook.yoga.YogaMeasureFunction
            public long measure(YogaNode yogaNode, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
                AnonymousClass1 r2;
                Rect rect;
                Rect rect2;
                Rect rect3;
                Spannable spannable = ReactTextShadowNode.this.mPreparedSpannableText;
                R$dimen.assertNotNull(spannable, "Spannable element has not been prepared in onBeforeLayout");
                Layout access$100 = ReactTextShadowNode.access$100(ReactTextShadowNode.this, spannable, f, yogaMeasureMode);
                ReactTextShadowNode reactTextShadowNode = ReactTextShadowNode.this;
                int i = -1;
                int i2 = 0;
                if (reactTextShadowNode.mAdjustsFontSizeToFit) {
                    int effectiveFontSize = reactTextShadowNode.mTextAttributes.getEffectiveFontSize();
                    int effectiveFontSize2 = ReactTextShadowNode.this.mTextAttributes.getEffectiveFontSize();
                    float f3 = effectiveFontSize;
                    int max = (int) Math.max(ReactTextShadowNode.this.mMinimumFontScale * f3, PixelUtil.toPixelFromDIP(4.0f));
                    while (effectiveFontSize2 > max && ((ReactTextShadowNode.this.mNumberOfLines != i && access$100.getLineCount() > ReactTextShadowNode.this.mNumberOfLines) || (yogaMeasureMode2 != YogaMeasureMode.UNDEFINED && access$100.getHeight() > f2))) {
                        effectiveFontSize2 -= (int) PixelUtil.toPixelFromDIP(1.0f);
                        float f4 = effectiveFontSize2 / f3;
                        ReactAbsoluteSizeSpan[] reactAbsoluteSizeSpanArr = (ReactAbsoluteSizeSpan[]) spannable.getSpans(i2, spannable.length(), ReactAbsoluteSizeSpan.class);
                        int length = reactAbsoluteSizeSpanArr.length;
                        int i3 = 0;
                        while (i3 < length) {
                            ReactAbsoluteSizeSpan reactAbsoluteSizeSpan = reactAbsoluteSizeSpanArr[i3];
                            spannable.setSpan(new ReactAbsoluteSizeSpan((int) Math.max(reactAbsoluteSizeSpan.getSize() * f4, max)), spannable.getSpanStart(reactAbsoluteSizeSpan), spannable.getSpanEnd(reactAbsoluteSizeSpan), spannable.getSpanFlags(reactAbsoluteSizeSpan));
                            spannable.removeSpan(reactAbsoluteSizeSpan);
                            i3++;
                            f4 = f4;
                        }
                        access$100 = ReactTextShadowNode.access$100(ReactTextShadowNode.this, spannable, f, yogaMeasureMode);
                        i = -1;
                        i2 = 0;
                    }
                }
                ReactTextShadowNode reactTextShadowNode2 = ReactTextShadowNode.this;
                if (reactTextShadowNode2.mShouldNotifyOnTextLayout) {
                    ThemedReactContext themedContext = reactTextShadowNode2.getThemedContext();
                    TextPaint textPaint = ReactTextShadowNode.sTextPaintInstance;
                    DisplayMetrics displayMetrics = themedContext.getResources().getDisplayMetrics();
                    WritableArray createArray = Arguments.createArray();
                    TextPaint textPaint2 = new TextPaint(textPaint);
                    textPaint2.setTextSize(textPaint2.getTextSize() * 100.0f);
                    int i4 = 0;
                    textPaint2.getTextBounds("T", 0, 1, new Rect());
                    double height = (rect.height() / 100.0f) / displayMetrics.density;
                    textPaint2.getTextBounds("x", 0, 1, new Rect());
                    double height2 = (rect2.height() / 100.0f) / displayMetrics.density;
                    while (i4 < access$100.getLineCount()) {
                        access$100.getLineBounds(i4, new Rect());
                        WritableMap createMap = Arguments.createMap();
                        createMap.putDouble("x", access$100.getLineLeft(i4) / displayMetrics.density);
                        createMap.putDouble("y", rect3.top / displayMetrics.density);
                        createMap.putDouble("width", access$100.getLineWidth(i4) / displayMetrics.density);
                        createMap.putDouble("height", rect3.height() / displayMetrics.density);
                        createMap.putDouble("descender", access$100.getLineDescent(i4) / displayMetrics.density);
                        createMap.putDouble("ascender", (-access$100.getLineAscent(i4)) / displayMetrics.density);
                        createMap.putDouble("baseline", access$100.getLineBaseline(i4) / displayMetrics.density);
                        createMap.putDouble("capHeight", height);
                        createMap.putDouble("xHeight", height2);
                        createMap.putString("text", spannable.subSequence(access$100.getLineStart(i4), access$100.getLineEnd(i4)).toString());
                        createArray.pushMap(createMap);
                        i4++;
                        themedContext = themedContext;
                    }
                    WritableMap createMap2 = Arguments.createMap();
                    createMap2.putArray("lines", createArray);
                    if (themedContext.hasActiveCatalystInstance()) {
                        r2 = this;
                        ((RCTEventEmitter) themedContext.getJSModule(RCTEventEmitter.class)).receiveEvent(ReactTextShadowNode.this.mReactTag, "topTextLayout", createMap2);
                    } else {
                        r2 = this;
                        ReactSoftException.logSoftException("ReactTextShadowNode", new ReactNoCrashSoftException("Cannot get RCTEventEmitter, no CatalystInstance"));
                    }
                } else {
                    r2 = this;
                }
                int i5 = ReactTextShadowNode.this.mNumberOfLines;
                if (i5 == -1 || i5 >= access$100.getLineCount()) {
                    return R$style.make(access$100.getWidth(), access$100.getHeight());
                }
                return R$style.make(access$100.getWidth(), access$100.getLineBottom(ReactTextShadowNode.this.mNumberOfLines - 1));
            }
        };
        this.mTextMeasureFunction = yogaMeasureFunction;
        if (!(this instanceof ReactRawTextShadowNode)) {
            this.mYogaNode.setMeasureFunction(yogaMeasureFunction);
        }
    }

    public static Layout access$100(ReactTextShadowNode reactTextShadowNode, Spannable spannable, float f, YogaMeasureMode yogaMeasureMode) {
        TextPaint textPaint = sTextPaintInstance;
        textPaint.setTextSize(reactTextShadowNode.mTextAttributes.getEffectiveFontSize());
        BoringLayout.Metrics isBoring = BoringLayout.isBoring(spannable, textPaint);
        float desiredWidth = isBoring == null ? Layout.getDesiredWidth(spannable, textPaint) : Float.NaN;
        boolean z = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f < 0.0f;
        Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
        int textAlign = reactTextShadowNode.getTextAlign();
        if (textAlign == 1) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else if (textAlign == 3) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else if (textAlign == 5) {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        if (isBoring == null && (z || (!R$style.isUndefined(desiredWidth) && desiredWidth <= f))) {
            int ceil = (int) Math.ceil(desiredWidth);
            int i = Build.VERSION.SDK_INT;
            if (i < 23) {
                return new StaticLayout(spannable, textPaint, ceil, alignment, 1.0f, 0.0f, reactTextShadowNode.mIncludeFontPadding);
            }
            StaticLayout.Builder hyphenationFrequency = StaticLayout.Builder.obtain(spannable, 0, spannable.length(), textPaint, ceil).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(reactTextShadowNode.mIncludeFontPadding).setBreakStrategy(reactTextShadowNode.mTextBreakStrategy).setHyphenationFrequency(0);
            if (i >= 26) {
                hyphenationFrequency.setJustificationMode(reactTextShadowNode.mJustificationMode);
            }
            if (i >= 28) {
                hyphenationFrequency.setUseLineSpacingFromFallbacks(true);
            }
            return hyphenationFrequency.build();
        } else if (isBoring != null && (z || isBoring.width <= f)) {
            return BoringLayout.make(spannable, textPaint, isBoring.width, alignment, 1.0f, 0.0f, isBoring, reactTextShadowNode.mIncludeFontPadding);
        } else {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 < 23) {
                return new StaticLayout(spannable, textPaint, (int) f, alignment, 1.0f, 0.0f, reactTextShadowNode.mIncludeFontPadding);
            }
            StaticLayout.Builder hyphenationFrequency2 = StaticLayout.Builder.obtain(spannable, 0, spannable.length(), textPaint, (int) f).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(reactTextShadowNode.mIncludeFontPadding).setBreakStrategy(reactTextShadowNode.mTextBreakStrategy).setHyphenationFrequency(0);
            if (i2 >= 28) {
                hyphenationFrequency2.setUseLineSpacingFromFallbacks(true);
            }
            return hyphenationFrequency2.build();
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public Iterable<? extends ReactShadowNode> calculateLayoutOnChildren() {
        Map<Integer, ReactShadowNode> map = this.mInlineViews;
        if (map == null || map.isEmpty()) {
            return null;
        }
        Spannable spannable = this.mPreparedSpannableText;
        R$dimen.assertNotNull(spannable, "Spannable element has not been prepared in onBeforeLayout");
        Spannable spannable2 = spannable;
        TextInlineViewPlaceholderSpan[] textInlineViewPlaceholderSpanArr = (TextInlineViewPlaceholderSpan[]) spannable2.getSpans(0, spannable2.length(), TextInlineViewPlaceholderSpan.class);
        ArrayList arrayList = new ArrayList(textInlineViewPlaceholderSpanArr.length);
        for (TextInlineViewPlaceholderSpan textInlineViewPlaceholderSpan : textInlineViewPlaceholderSpanArr) {
            ReactShadowNode reactShadowNode = this.mInlineViews.get(Integer.valueOf(textInlineViewPlaceholderSpan.mReactTag));
            reactShadowNode.calculateLayout();
            arrayList.add(reactShadowNode);
        }
        return arrayList;
    }

    public final int getTextAlign() {
        int i = this.mTextAlign;
        if (this.mYogaNode.getLayoutDirection() != YogaDirection.RTL) {
            return i;
        }
        if (i == 5) {
            return 3;
        }
        if (i == 3) {
            return 5;
        }
        return i;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public boolean isVirtualAnchor() {
        return false;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public void markUpdated() {
        super.markUpdated();
        dirty();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public void onBeforeLayout(NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        this.mPreparedSpannableText = spannedFromShadowNode(this, null, true, nativeViewHierarchyOptimizer);
        super.markUpdated();
        dirty();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl
    public void onCollectExtraUpdates(UIViewOperationQueue uIViewOperationQueue) {
        Spannable spannable = this.mPreparedSpannableText;
        if (spannable != null) {
            uIViewOperationQueue.enqueueUpdateExtraData(this.mReactTag, new ReactTextUpdate(spannable, -1, this.mContainsImages, getPadding(4), getPadding(1), getPadding(5), getPadding(3), getTextAlign(), this.mTextBreakStrategy, this.mJustificationMode, -1, -1));
        }
    }

    @ReactProp(name = "onTextLayout")
    public void setShouldNotifyOnTextLayout(boolean z) {
        this.mShouldNotifyOnTextLayout = z;
    }
}
