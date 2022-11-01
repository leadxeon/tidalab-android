package com.facebook.react.bridge.queue;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public class NativeRunnable implements Runnable {
    private final HybridData mHybridData;

    @DoNotStrip
    private NativeRunnable(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // java.lang.Runnable
    public native void run();
}
