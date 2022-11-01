package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeNetworkingIOSSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeNetworkingIOSSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void abortRequest(double d);

    @ReactMethod
    public abstract void addListener(String str);

    @ReactMethod
    public abstract void clearCookies(Callback callback);

    @ReactMethod
    public abstract void removeListeners(double d);

    @ReactMethod
    public abstract void sendRequest(ReadableMap readableMap, Callback callback);
}
