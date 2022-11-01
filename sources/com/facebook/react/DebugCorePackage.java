package com.facebook.react;

import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.devsupport.JSCHeapCapture;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes.dex */
public class DebugCorePackage extends LazyReactPackage {
    @Override // com.facebook.react.LazyReactPackage
    public List<ModuleSpec> getNativeModules(final ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ModuleSpec.nativeModuleSpec(JSCHeapCapture.class, new Provider<NativeModule>(this) { // from class: com.facebook.react.DebugCorePackage.1
            @Override // javax.inject.Provider
            public NativeModule get() {
                return new JSCHeapCapture(reactApplicationContext);
            }
        }));
        return arrayList;
    }

    @Override // com.facebook.react.LazyReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        try {
            try {
                return (ReactModuleInfoProvider) Class.forName(getClass().getCanonicalName() + "$$ReactModuleInfoProvider").newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + DebugCorePackage.class, e);
            } catch (InstantiationException e2) {
                throw new RuntimeException("Unable to instantiate ReactModuleInfoProvider for " + DebugCorePackage.class, e2);
            }
        } catch (ClassNotFoundException unused) {
            return new ReactModuleInfoProvider() { // from class: com.facebook.react.LazyReactPackage.1
                @Override // com.facebook.react.module.model.ReactModuleInfoProvider
                public Map<String, ReactModuleInfo> getReactModuleInfos() {
                    return Collections.emptyMap();
                }
            };
        }
    }
}
