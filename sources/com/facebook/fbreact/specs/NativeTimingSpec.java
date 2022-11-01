package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeTimingSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeTimingSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void createTimer(double d, double d2, double d3, boolean z);

    @ReactMethod
    public abstract void deleteTimer(double d);

    @ReactMethod
    public abstract void setSendIdleEvents(boolean z);
}
