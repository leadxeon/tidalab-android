package com.facebook.react.views.scroll;

import android.util.DisplayMetrics;
import androidx.core.view.ViewCompat;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder$Builder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
@ReactModule(name = ReactScrollViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactScrollViewManager extends ViewGroupManager<ReactScrollView> implements ReactScrollViewCommandHelper$ScrollCommandHandler<ReactScrollView> {
    public static final String REACT_CLASS = "RCTScrollView";
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private FpsListener mFpsListener;

    public ReactScrollViewManager() {
        this(null);
    }

    public static Map<String, Object> createExportedCustomDirectEventTypeConstants() {
        MapBuilder$Builder builder = R$style.builder();
        builder.put(ScrollEventType.getJSEventName(ScrollEventType.SCROLL), R$style.of("registrationName", "onScroll"));
        builder.put(ScrollEventType.getJSEventName(ScrollEventType.BEGIN_DRAG), R$style.of("registrationName", "onScrollBeginDrag"));
        builder.put(ScrollEventType.getJSEventName(ScrollEventType.END_DRAG), R$style.of("registrationName", "onScrollEndDrag"));
        builder.put(ScrollEventType.getJSEventName(ScrollEventType.MOMENTUM_BEGIN), R$style.of("registrationName", "onMomentumScrollBegin"));
        builder.put(ScrollEventType.getJSEventName(ScrollEventType.MOMENTUM_END), R$style.of("registrationName", "onMomentumScrollEnd"));
        return builder.build();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return R$style.of("scrollTo", 1, "scrollToEnd", 2, "flashScrollIndicators", 3);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return createExportedCustomDirectEventTypeConstants();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactScrollView reactScrollView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : num.intValue() & 16777215;
        if (num != null) {
            f = num.intValue() >>> 24;
        }
        reactScrollView.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactScrollView reactScrollView, int i, float f) {
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactScrollView.setBorderRadius(f);
            return;
        }
        reactScrollView.mReactBackgroundManager.getOrCreateReactViewBackground().setRadius(f, i - 1);
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactScrollView reactScrollView, String str) {
        reactScrollView.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactScrollView reactScrollView, int i, float f) {
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactScrollView.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderWidth(SPACING_TYPES[i], f);
    }

    @ReactProp(customType = "Color", defaultInt = 0, name = "endFillColor")
    public void setBottomFillColor(ReactScrollView reactScrollView, int i) {
        reactScrollView.setEndFillColor(i);
    }

    @ReactProp(name = "decelerationRate")
    public void setDecelerationRate(ReactScrollView reactScrollView, float f) {
        reactScrollView.setDecelerationRate(f);
    }

    @ReactProp(name = "fadingEdgeLength")
    public void setFadingEdgeLength(ReactScrollView reactScrollView, int i) {
        if (i > 0) {
            reactScrollView.setVerticalFadingEdgeEnabled(true);
            reactScrollView.setFadingEdgeLength(i);
            return;
        }
        reactScrollView.setVerticalFadingEdgeEnabled(false);
        reactScrollView.setFadingEdgeLength(0);
    }

    @ReactProp(name = "nestedScrollEnabled")
    public void setNestedScrollEnabled(ReactScrollView reactScrollView, boolean z) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        reactScrollView.setNestedScrollingEnabled(z);
    }

    @ReactProp(name = "overScrollMode")
    public void setOverScrollMode(ReactScrollView reactScrollView, String str) {
        reactScrollView.setOverScrollMode(R$style.parseOverScrollMode(str));
    }

    @ReactProp(name = "overflow")
    public void setOverflow(ReactScrollView reactScrollView, String str) {
        reactScrollView.setOverflow(str);
    }

    @ReactProp(name = "pagingEnabled")
    public void setPagingEnabled(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setPagingEnabled(z);
    }

    @ReactProp(name = "persistentScrollbar")
    public void setPersistentScrollbar(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setScrollbarFadingEnabled(!z);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setRemoveClippedSubviews(z);
    }

    @ReactProp(defaultBoolean = true, name = "scrollEnabled")
    public void setScrollEnabled(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setScrollEnabled(z);
        reactScrollView.setFocusable(z);
    }

    @ReactProp(name = "scrollPerfTag")
    public void setScrollPerfTag(ReactScrollView reactScrollView, String str) {
        reactScrollView.setScrollPerfTag(str);
    }

    @ReactProp(name = "sendMomentumEvents")
    public void setSendMomentumEvents(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSendMomentumEvents(z);
    }

    @ReactProp(name = "showsVerticalScrollIndicator")
    public void setShowsVerticalScrollIndicator(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setVerticalScrollBarEnabled(z);
    }

    @ReactProp(name = "snapToEnd")
    public void setSnapToEnd(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSnapToEnd(z);
    }

    @ReactProp(name = "snapToInterval")
    public void setSnapToInterval(ReactScrollView reactScrollView, float f) {
        reactScrollView.setSnapInterval((int) (f * R$style.sScreenDisplayMetrics.density));
    }

    @ReactProp(name = "snapToOffsets")
    public void setSnapToOffsets(ReactScrollView reactScrollView, ReadableArray readableArray) {
        DisplayMetrics displayMetrics = R$style.sScreenDisplayMetrics;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(Integer.valueOf((int) (readableArray.getDouble(i) * displayMetrics.density)));
        }
        reactScrollView.setSnapOffsets(arrayList);
    }

    @ReactProp(name = "snapToStart")
    public void setSnapToStart(ReactScrollView reactScrollView, boolean z) {
        reactScrollView.setSnapToStart(z);
    }

    public ReactScrollViewManager(FpsListener fpsListener) {
        this.mFpsListener = null;
        this.mFpsListener = fpsListener;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactScrollView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactScrollView(themedReactContext, this.mFpsListener);
    }

    public void flashScrollIndicators(ReactScrollView reactScrollView) {
        reactScrollView.flashScrollIndicators();
    }

    public void scrollTo(ReactScrollView reactScrollView, ReactScrollViewCommandHelper$ScrollToCommandData reactScrollViewCommandHelper$ScrollToCommandData) {
        if (reactScrollViewCommandHelper$ScrollToCommandData.mAnimated) {
            reactScrollView.smoothScrollTo(reactScrollViewCommandHelper$ScrollToCommandData.mDestX, reactScrollViewCommandHelper$ScrollToCommandData.mDestY);
        } else {
            reactScrollView.scrollTo(reactScrollViewCommandHelper$ScrollToCommandData.mDestX, reactScrollViewCommandHelper$ScrollToCommandData.mDestY);
        }
    }

    public void scrollToEnd(ReactScrollView reactScrollView, ReactScrollViewCommandHelper$ScrollToEndCommandData reactScrollViewCommandHelper$ScrollToEndCommandData) {
        int paddingBottom = reactScrollView.getPaddingBottom() + reactScrollView.getChildAt(0).getHeight();
        if (reactScrollViewCommandHelper$ScrollToEndCommandData.mAnimated) {
            reactScrollView.smoothScrollTo(reactScrollView.getScrollX(), paddingBottom);
        } else {
            reactScrollView.scrollTo(reactScrollView.getScrollX(), paddingBottom);
        }
    }

    public void receiveCommand(ReactScrollView reactScrollView, int i, ReadableArray readableArray) {
        R$style.receiveCommand(this, reactScrollView, i, readableArray);
    }

    public void receiveCommand(ReactScrollView reactScrollView, String str, ReadableArray readableArray) {
        R$style.receiveCommand(this, reactScrollView, str, readableArray);
    }
}
