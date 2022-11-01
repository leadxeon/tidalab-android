package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
/* loaded from: classes.dex */
public abstract class NativeAppearanceSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativeAppearanceSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public abstract void addListener(String str);

    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract String getColorScheme();

    @ReactMethod
    public abstract void removeListeners(double d);
}
