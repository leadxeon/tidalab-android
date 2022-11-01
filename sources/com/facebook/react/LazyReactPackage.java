package com.facebook.react;

import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public abstract class LazyReactPackage implements ReactPackage {
    /* JADX WARN: Finally extract failed */
    @Override // com.facebook.react.ReactPackage
    public final List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec moduleSpec : getNativeModules(reactApplicationContext)) {
            moduleSpec.getType();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, moduleSpec.getName());
            try {
                NativeModule nativeModule = (NativeModule) moduleSpec.getProvider().get();
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                arrayList.add(nativeModule);
            } catch (Throwable th) {
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                throw th;
            }
        }
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ModuleSpec> emptyList = Collections.emptyList();
        if (emptyList == null || emptyList.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec moduleSpec : emptyList) {
            arrayList.add((ViewManager) moduleSpec.getProvider().get());
        }
        return arrayList;
    }

    public abstract List<ModuleSpec> getNativeModules(ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();
}
