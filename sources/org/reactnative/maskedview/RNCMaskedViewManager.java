package org.reactnative.maskedview;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
/* loaded from: classes.dex */
public class RNCMaskedViewManager extends ViewGroupManager<RNCMaskedView> {
    private static final String REACT_CLASS = "RNCMaskedView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public RNCMaskedView createViewInstance(ThemedReactContext themedReactContext) {
        return new RNCMaskedView(themedReactContext);
    }
}
