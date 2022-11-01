package com.swmansion.rnscreens;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
import com.facebook.react.views.view.ReactViewManager;
import com.swmansion.rnscreens.ScreenStackHeaderSubview;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ScreenStackHeaderSubviewManager.kt */
@ReactModule(name = ScreenStackHeaderSubviewManager.REACT_CLASS)
/* loaded from: classes.dex */
public final class ScreenStackHeaderSubviewManager extends ReactViewManager {
    public static final Companion Companion = new Companion(null);
    public static final String REACT_CLASS = "RNSScreenStackHeaderSubview";

    /* compiled from: ScreenStackHeaderSubviewManager.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "type")
    public final void setType(ScreenStackHeaderSubview screenStackHeaderSubview, String str) {
        ScreenStackHeaderSubview.Type type;
        switch (str.hashCode()) {
            case -1364013995:
                if (str.equals("center")) {
                    type = ScreenStackHeaderSubview.Type.CENTER;
                    screenStackHeaderSubview.setType(type);
                    return;
                }
                throw new JSApplicationIllegalArgumentException(Intrinsics.stringPlus("Unknown type ", str));
            case 3015911:
                if (str.equals("back")) {
                    type = ScreenStackHeaderSubview.Type.BACK;
                    screenStackHeaderSubview.setType(type);
                    return;
                }
                throw new JSApplicationIllegalArgumentException(Intrinsics.stringPlus("Unknown type ", str));
            case 3317767:
                if (str.equals("left")) {
                    type = ScreenStackHeaderSubview.Type.LEFT;
                    screenStackHeaderSubview.setType(type);
                    return;
                }
                throw new JSApplicationIllegalArgumentException(Intrinsics.stringPlus("Unknown type ", str));
            case 108511772:
                if (str.equals("right")) {
                    type = ScreenStackHeaderSubview.Type.RIGHT;
                    screenStackHeaderSubview.setType(type);
                    return;
                }
                throw new JSApplicationIllegalArgumentException(Intrinsics.stringPlus("Unknown type ", str));
            default:
                throw new JSApplicationIllegalArgumentException(Intrinsics.stringPlus("Unknown type ", str));
        }
    }

    @Override // com.facebook.react.views.view.ReactViewManager, com.facebook.react.uimanager.ViewManager
    public ReactViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new ScreenStackHeaderSubview(themedReactContext);
    }
}
