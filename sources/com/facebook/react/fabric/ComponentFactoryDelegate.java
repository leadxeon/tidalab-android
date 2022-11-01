package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public class ComponentFactoryDelegate {
    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    static {
        FabricSoLoader.staticInit();
    }

    @DoNotStrip
    private static native HybridData initHybrid();
}
