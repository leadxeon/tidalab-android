package com.facebook.hermes.reactexecutor;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.soloader.SoLoader;
/* loaded from: classes.dex */
public class HermesExecutor extends JavaScriptExecutor {
    public static String mode_;

    static {
        SoLoader.loadLibrary("hermes");
        try {
            SoLoader.loadLibrary("hermes-executor-debug");
            mode_ = "Debug";
        } catch (UnsatisfiedLinkError unused) {
            SoLoader.loadLibrary("hermes-executor-release");
            mode_ = "Release";
        }
    }

    public HermesExecutor() {
        super(initHybridDefaultConfig());
    }

    public static native boolean canLoadFile(String str);

    private static native HybridData initHybrid(long j, boolean z, int i);

    private static native HybridData initHybridDefaultConfig();

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("HermesExecutor");
        outline13.append(mode_);
        return outline13.toString();
    }
}
