package com.facebook.fbreact.specs;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class NativePlatformConstantsIOSSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    public NativePlatformConstantsIOSSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public final Map<String, Object> getConstants() {
        return getTypedExportedConstants();
    }

    public abstract Map<String, Object> getTypedExportedConstants();
}
