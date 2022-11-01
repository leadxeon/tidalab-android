package com.facebook.react.fabric.events;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.fabric.FabricSoLoader;
import com.facebook.react.uimanager.events.BatchEventDispatchedListener;
@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes.dex */
public class EventBeatManager implements BatchEventDispatchedListener {
    @DoNotStrip
    private final HybridData mHybridData;

    static {
        FabricSoLoader.staticInit();
    }

    private static native HybridData initHybrid();

    private native void tick();

    @Override // com.facebook.react.uimanager.events.BatchEventDispatchedListener
    public void onBatchEventDispatched() {
        tick();
    }
}
