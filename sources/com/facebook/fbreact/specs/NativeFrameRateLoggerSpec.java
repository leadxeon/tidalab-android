package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeFrameRateLoggerSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeFrameRateLoggerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void beginScroll();

    @ReactMethod
    public abstract void endScroll();

    @ReactMethod
    public abstract void setContext(String str);

    @ReactMethod
    public abstract void setGlobalOptions(ReadableMap readableMap);
}
