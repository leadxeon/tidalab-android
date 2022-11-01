package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeVibrationSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeVibrationSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void cancel();

    @ReactMethod
    public abstract void vibrate(Double d);

    @ReactMethod
    public abstract void vibrateByPattern(ReadableArray readableArray, double d);
}
