package com.swmansion.gesturehandler.react;

import com.facebook.react.R$style;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import java.util.Map;
@ReactModule(name = RNGestureHandlerRootViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class RNGestureHandlerRootViewManager extends ViewGroupManager<RNGestureHandlerRootView> {
    public static final String REACT_CLASS = "GestureHandlerRootView";

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return R$style.of("onGestureHandlerEvent", R$style.of("registrationName", "onGestureHandlerEvent"), "onGestureHandlerStateChange", R$style.of("registrationName", "onGestureHandlerStateChange"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public RNGestureHandlerRootView createViewInstance(ThemedReactContext themedReactContext) {
        return new RNGestureHandlerRootView(themedReactContext);
    }

    public void onDropViewInstance(RNGestureHandlerRootView rNGestureHandlerRootView) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = rNGestureHandlerRootView.mRootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.tearDown();
        }
    }
}
