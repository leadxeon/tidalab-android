package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public class CxxCallbackImpl implements Callback {
    @DoNotStrip
    private final HybridData mHybridData;

    @DoNotStrip
    private CxxCallbackImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    private native void nativeInvoke(NativeArray nativeArray);

    @Override // com.facebook.react.bridge.Callback
    public void invoke(Object... objArr) {
        nativeInvoke(Arguments.fromJavaArgs(objArr));
    }
}
