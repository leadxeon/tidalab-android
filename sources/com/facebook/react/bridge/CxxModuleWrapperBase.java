package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public class CxxModuleWrapperBase implements NativeModule {
    @DoNotStrip
    private HybridData mHybridData;

    static {
        ReactBridge.staticInit();
    }

    public CxxModuleWrapperBase(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public boolean canOverrideExistingModule() {
        return false;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public native String getName();

    @Override // com.facebook.react.bridge.NativeModule
    public void initialize() {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        this.mHybridData.resetNative();
    }

    public void resetModule(HybridData hybridData) {
        HybridData hybridData2 = this.mHybridData;
        if (hybridData != hybridData2) {
            hybridData2.resetNative();
            this.mHybridData = hybridData;
        }
    }
}
