package com.facebook.react.fabric.events;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.fabric.FabricSoLoader;
@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes.dex */
public class EventEmitterWrapper {
    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    static {
        FabricSoLoader.staticInit();
    }

    private static native HybridData initHybrid();

    private native void invokeEvent(String str, NativeMap nativeMap);
}
