package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class NativeModuleRegistryBuilder {
    public final Map<String, ModuleHolder> mModules = new HashMap();
    public final ReactApplicationContext mReactApplicationContext;
    public final ReactInstanceManager mReactInstanceManager;

    public NativeModuleRegistryBuilder(ReactApplicationContext reactApplicationContext, ReactInstanceManager reactInstanceManager) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mReactInstanceManager = reactInstanceManager;
    }
}
