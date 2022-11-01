package com.th3rdwave.safeareacontext;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public class SafeAreaContextPackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(new SafeAreaProviderManager(reactApplicationContext), new SafeAreaViewManager());
    }
}
