package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
@DoNotStrip
/* loaded from: classes.dex */
public class ThreadScopeSupport {
    static {
        NativeLoader.loadLibrary("fbjni");
    }

    @DoNotStrip
    private static void runStdFunction(long j) {
        runStdFunctionImpl(j);
    }

    private static native void runStdFunctionImpl(long j);
}
