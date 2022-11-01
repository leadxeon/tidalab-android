package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public abstract class NativeArray implements NativeArrayInterface {
    @DoNotStrip
    private HybridData mHybridData;

    static {
        ReactBridge.staticInit();
    }

    public NativeArray(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // com.facebook.react.bridge.NativeArrayInterface
    public native String toString();
}
