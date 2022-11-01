package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeDevLoadingViewSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeDevLoadingViewSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void hide();

    @ReactMethod
    public abstract void showMessage(String str, ReadableMap readableMap, ReadableMap readableMap2);
}
