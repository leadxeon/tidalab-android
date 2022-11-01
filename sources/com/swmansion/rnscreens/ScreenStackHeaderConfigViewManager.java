package com.swmansion.rnscreens;

import android.view.View;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ScreenStackHeaderConfigViewManager.kt */
@ReactModule(name = ScreenStackHeaderConfigViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public final class ScreenStackHeaderConfigViewManager extends ViewGroupManager<ScreenStackHeaderConfig> {
    public static final Companion Companion = new Companion(null);
    public static final String REACT_CLASS = "RNSScreenStackHeaderConfig";

    /* compiled from: ScreenStackHeaderConfigViewManager.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @ReactProp(name = "backButtonInCustomView")
    public final void setBackButtonInCustomView(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        screenStackHeaderConfig.setBackButtonInCustomView(z);
    }

    @ReactProp(customType = "Color", name = "backgroundColor")
    public final void setBackgroundColor(ScreenStackHeaderConfig screenStackHeaderConfig, Integer num) {
        screenStackHeaderConfig.setBackgroundColor(num);
    }

    @ReactProp(customType = "Color", name = "color")
    public final void setColor(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        screenStackHeaderConfig.setTintColor(i);
    }

    @ReactProp(name = "direction")
    public final void setDirection(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        screenStackHeaderConfig.setDirection(str);
    }

    @ReactProp(name = "hidden")
    public final void setHidden(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        screenStackHeaderConfig.setHidden(z);
    }

    @ReactProp(name = "hideBackButton")
    public final void setHideBackButton(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        screenStackHeaderConfig.setHideBackButton(z);
    }

    @ReactProp(name = "hideShadow")
    public final void setHideShadow(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        screenStackHeaderConfig.setHideShadow(z);
    }

    @ReactProp(name = DialogModule.KEY_TITLE)
    public final void setTitle(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        screenStackHeaderConfig.setTitle(str);
    }

    @ReactProp(customType = "Color", name = "titleColor")
    public final void setTitleColor(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        screenStackHeaderConfig.setTitleColor(i);
    }

    @ReactProp(name = "titleFontFamily")
    public final void setTitleFontFamily(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        screenStackHeaderConfig.setTitleFontFamily(str);
    }

    @ReactProp(name = "titleFontSize")
    public final void setTitleFontSize(ScreenStackHeaderConfig screenStackHeaderConfig, float f) {
        screenStackHeaderConfig.setTitleFontSize(f);
    }

    @ReactProp(name = "titleFontWeight")
    public final void setTitleFontWeight(ScreenStackHeaderConfig screenStackHeaderConfig, String str) {
        screenStackHeaderConfig.setTitleFontWeight(str);
    }

    @ReactProp(name = "topInsetEnabled")
    public final void setTopInsetEnabled(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        screenStackHeaderConfig.setTopInsetEnabled(z);
    }

    @ReactProp(name = "translucent")
    public final void setTranslucent(ScreenStackHeaderConfig screenStackHeaderConfig, boolean z) {
        screenStackHeaderConfig.setTranslucent(z);
    }

    public void addView(ScreenStackHeaderConfig screenStackHeaderConfig, View view, int i) {
        if (view instanceof ScreenStackHeaderSubview) {
            screenStackHeaderConfig.mConfigSubviews.add(i, (ScreenStackHeaderSubview) view);
            screenStackHeaderConfig.maybeUpdate();
            return;
        }
        throw new JSApplicationCausedNativeException("Config children should be of type RNSScreenStackHeaderSubview");
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ScreenStackHeaderConfig createViewInstance(ThemedReactContext themedReactContext) {
        return new ScreenStackHeaderConfig(themedReactContext);
    }

    public View getChildAt(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        return screenStackHeaderConfig.mConfigSubviews.get(i);
    }

    public int getChildCount(ScreenStackHeaderConfig screenStackHeaderConfig) {
        return screenStackHeaderConfig.getConfigSubviewsCount();
    }

    public void onAfterUpdateTransaction(ScreenStackHeaderConfig screenStackHeaderConfig) {
        super.onAfterUpdateTransaction((ScreenStackHeaderConfigViewManager) screenStackHeaderConfig);
        screenStackHeaderConfig.onUpdate();
    }

    public void onDropViewInstance(ScreenStackHeaderConfig screenStackHeaderConfig) {
        screenStackHeaderConfig.mDestroyed = true;
    }

    public void removeAllViews(ScreenStackHeaderConfig screenStackHeaderConfig) {
        screenStackHeaderConfig.mConfigSubviews.clear();
        screenStackHeaderConfig.maybeUpdate();
    }

    public void removeViewAt(ScreenStackHeaderConfig screenStackHeaderConfig, int i) {
        screenStackHeaderConfig.mConfigSubviews.remove(i);
        screenStackHeaderConfig.maybeUpdate();
    }
}
