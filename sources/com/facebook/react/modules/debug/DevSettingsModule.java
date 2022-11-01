package com.facebook.react.modules.debug;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
@ReactModule(name = DevSettingsModule.NAME)
/* loaded from: classes.dex */
public class DevSettingsModule extends ReactContextBaseJavaModule {
    public static final String NAME = "DevSettings";
    private final DevSupportManager mDevSupportManager;

    public DevSettingsModule(ReactApplicationContext reactApplicationContext, DevSupportManager devSupportManager) {
        super(reactApplicationContext);
        this.mDevSupportManager = devSupportManager;
    }

    @ReactMethod
    public void addMenuItem(String str) {
        this.mDevSupportManager.addCustomDevOption(str, new DevOptionHandler(this, str) { // from class: com.facebook.react.modules.debug.DevSettingsModule.2
        });
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void onFastRefresh() {
    }

    @ReactMethod
    public void reload() {
        if (this.mDevSupportManager.getDevSupportEnabled()) {
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.facebook.react.modules.debug.DevSettingsModule.1
                @Override // java.lang.Runnable
                public void run() {
                    DevSettingsModule.this.mDevSupportManager.handleReloadJS();
                }
            });
        }
    }

    @ReactMethod
    public void reloadWithReason(String str) {
        reload();
    }

    @ReactMethod
    public void setHotLoadingEnabled(boolean z) {
        this.mDevSupportManager.setHotModuleReplacementEnabled(z);
    }

    @ReactMethod
    public void setIsDebuggingRemotely(boolean z) {
        this.mDevSupportManager.setRemoteJSDebugEnabled(z);
    }

    @ReactMethod
    public void setProfilingEnabled(boolean z) {
        this.mDevSupportManager.setFpsDebugEnabled(z);
    }

    @ReactMethod
    public void toggleElementInspector() {
        this.mDevSupportManager.toggleElementInspector();
    }
}
