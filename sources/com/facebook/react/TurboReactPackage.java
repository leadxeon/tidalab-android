package com.facebook.react;

import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Provider;
/* loaded from: classes.dex */
public abstract class TurboReactPackage implements ReactPackage {

    /* loaded from: classes.dex */
    public class ModuleHolderProvider implements Provider<NativeModule> {
        public final String mName;
        public final ReactApplicationContext mReactContext;

        public ModuleHolderProvider(String str, ReactApplicationContext reactApplicationContext) {
            this.mName = str;
            this.mReactContext = reactApplicationContext;
        }

        @Override // javax.inject.Provider
        public NativeModule get() {
            return TurboReactPackage.this.getModule(this.mName, this.mReactContext);
        }
    }

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        throw new UnsupportedOperationException("In case of TurboModules, createNativeModules is not supported. NativeModuleRegistry should instead use getModuleList or getModule method");
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

    public abstract NativeModule getModule(String str, ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();
}
