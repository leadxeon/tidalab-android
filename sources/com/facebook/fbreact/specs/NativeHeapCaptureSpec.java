package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeHeapCaptureSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeHeapCaptureSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void captureComplete(String str, String str2);

    @ReactMethod
    public abstract void captureHeap(String str);
}
