package com.facebook.fbreact.specs;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeAsyncStorageSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeAsyncStorageSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void clear(Callback callback);

    @ReactMethod
    public abstract void getAllKeys(Callback callback);

    @ReactMethod
    public abstract void multiGet(ReadableArray readableArray, Callback callback);

    @ReactMethod
    public abstract void multiMerge(ReadableArray readableArray, Callback callback);

    @ReactMethod
    public abstract void multiRemove(ReadableArray readableArray, Callback callback);

    @ReactMethod
    public abstract void multiSet(ReadableArray readableArray, Callback callback);
}
