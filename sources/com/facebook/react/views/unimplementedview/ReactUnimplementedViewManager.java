package com.facebook.react.views.unimplementedview;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
@ReactModule(name = ReactUnimplementedViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactUnimplementedViewManager extends ViewGroupManager<ReactUnimplementedView> {
    public static final String REACT_CLASS = "UnimplementedNativeView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "name")
    public void setName(ReactUnimplementedView reactUnimplementedView, String str) {
        reactUnimplementedView.setName(str);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactUnimplementedView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactUnimplementedView(themedReactContext);
    }
}
