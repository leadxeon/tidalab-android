package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeHeadlessJsTaskSupportSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeHeadlessJsTaskSupportSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void notifyTaskFinished(double d);

    @ReactMethod
    public abstract void notifyTaskRetry(double d, Promise promise);
}
