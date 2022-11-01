package com.facebook.react.bridge;

import androidx.recyclerview.R$dimen;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes.dex */
public class JSIModuleRegistry {
    private final Map<JSIModuleType, JSIModuleHolder> mModules = new HashMap();

    public JSIModule getModule(JSIModuleType jSIModuleType) {
        JSIModuleHolder jSIModuleHolder = this.mModules.get(jSIModuleType);
        if (jSIModuleHolder != null) {
            JSIModule jSIModule = jSIModuleHolder.getJSIModule();
            R$dimen.assertNotNull(jSIModule);
            return jSIModule;
        }
        throw new IllegalArgumentException("Unable to find JSIModule for class " + jSIModuleType);
    }

    public void notifyJSInstanceDestroy() {
        for (Map.Entry<JSIModuleType, JSIModuleHolder> entry : this.mModules.entrySet()) {
            if (entry.getKey() != JSIModuleType.TurboModuleManager) {
                entry.getValue().notifyJSInstanceDestroy();
            }
        }
    }

    public void registerModules(List<JSIModuleSpec> list) {
        for (JSIModuleSpec jSIModuleSpec : list) {
            this.mModules.put(jSIModuleSpec.getJSIModuleType(), new JSIModuleHolder(jSIModuleSpec));
        }
    }
}
