package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public abstract class JavaScriptExecutor {
    private final HybridData mHybridData;

    public JavaScriptExecutor(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    public void close() {
        this.mHybridData.resetNative();
    }

    public abstract String getName();
}
