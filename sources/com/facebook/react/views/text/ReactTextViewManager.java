package com.facebook.react.views.text;

import android.content.Context;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.IViewManagerWithChildren;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.yoga.YogaMeasureMode;
import java.util.Map;
@ReactModule(name = ReactTextViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactTextViewManager extends ReactTextAnchorViewManager<ReactTextView, ReactTextShadowNode> implements IViewManagerWithChildren {
    public static final String REACT_CLASS = "RCTText";

    private int getTextBreakStrategy(String str) {
        if (str == null) {
            return 1;
        }
        if (!str.equals("balanced")) {
            return !str.equals("simple") ? 1 : 0;
        }
        return 2;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return R$style.of("topTextLayout", R$style.of("registrationName", "onTextLayout"), "topInlineViewLayout", R$style.of("registrationName", "onInlineViewLayout"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<ReactTextShadowNode> getShadowNodeClass() {
        return ReactTextShadowNode.class;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2) {
        Layout layout;
        int i;
        TextPaint textPaint = TextLayoutManager.sTextPaintInstance;
        Spannable orCreateSpannableForText = TextLayoutManager.getOrCreateSpannableForText(context, readableMap);
        BoringLayout.Metrics isBoring = BoringLayout.isBoring(orCreateSpannableForText, textPaint);
        float desiredWidth = isBoring == null ? Layout.getDesiredWidth(orCreateSpannableForText, textPaint) : Float.NaN;
        boolean z = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f < 0.0f;
        if (isBoring == null && (z || (!R$style.isUndefined(desiredWidth) && desiredWidth <= f))) {
            int ceil = (int) Math.ceil(desiredWidth);
            if (Build.VERSION.SDK_INT < 23) {
                layout = new StaticLayout(orCreateSpannableForText, textPaint, ceil, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
            } else {
                layout = StaticLayout.Builder.obtain(orCreateSpannableForText, 0, orCreateSpannableForText.length(), textPaint, ceil).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(true).setBreakStrategy(1).setHyphenationFrequency(1).build();
            }
        } else if (isBoring != null && (z || isBoring.width <= f)) {
            layout = BoringLayout.make(orCreateSpannableForText, textPaint, isBoring.width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, isBoring, true);
        } else if (Build.VERSION.SDK_INT < 23) {
            layout = new StaticLayout(orCreateSpannableForText, textPaint, (int) f, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, true);
        } else {
            layout = StaticLayout.Builder.obtain(orCreateSpannableForText, 0, orCreateSpannableForText.length(), textPaint, (int) f).setAlignment(Layout.Alignment.ALIGN_NORMAL).setLineSpacing(0.0f, 1.0f).setIncludePad(true).setBreakStrategy(1).setHyphenationFrequency(1).build();
        }
        int i2 = readableMap2.hasKey("maximumNumberOfLines") ? readableMap2.getInt("maximumNumberOfLines") : -1;
        float width = layout.getWidth();
        if (i2 == -1 || i2 == 0 || i2 >= layout.getLineCount()) {
            i = layout.getHeight();
        } else {
            i = layout.getLineBottom(i2 - 1);
        }
        float f3 = R$style.sScreenDisplayMetrics.scaledDensity;
        return R$style.make(width / f3, i / f3);
    }

    @Override // com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactTextShadowNode createShadowNodeInstance() {
        return new ReactTextShadowNode();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactTextView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactTextView(themedReactContext);
    }

    public void onAfterUpdateTransaction(ReactTextView reactTextView) {
        super.onAfterUpdateTransaction((ReactTextViewManager) reactTextView);
        reactTextView.setEllipsize((reactTextView.mNumberOfLines == Integer.MAX_VALUE || reactTextView.mAdjustsFontSizeToFit) ? null : reactTextView.mEllipsizeLocation);
    }

    public void setPadding(ReactTextView reactTextView, int i, int i2, int i3, int i4) {
        reactTextView.setPadding(i, i2, i3, i4);
    }

    public void updateExtraData(ReactTextView reactTextView, Object obj) {
        ReactTextUpdate reactTextUpdate = (ReactTextUpdate) obj;
        if (reactTextUpdate.mContainsImages) {
            TextInlineImageSpan.possiblyUpdateInlineImageSpans(reactTextUpdate.mText, reactTextView);
        }
        reactTextView.setText(reactTextUpdate);
    }

    public Object updateState(ReactTextView reactTextView, ReactStylesDiffMap reactStylesDiffMap, StateWrapper stateWrapper) {
        ReadableNativeMap state = stateWrapper.getState();
        ReadableNativeMap map = state.getMap("attributedString");
        ReadableNativeMap map2 = state.getMap("paragraphAttributes");
        Spannable orCreateSpannableForText = TextLayoutManager.getOrCreateSpannableForText(reactTextView.getContext(), map);
        reactTextView.setSpanned(orCreateSpannableForText);
        TextAttributeProps textAttributeProps = new TextAttributeProps(reactStylesDiffMap);
        return new ReactTextUpdate(orCreateSpannableForText, state.hasKey("mostRecentEventCount") ? state.getInt("mostRecentEventCount") : -1, false, textAttributeProps.mTextAlign, getTextBreakStrategy(map2.getString("textBreakStrategy")), 0);
    }
}
