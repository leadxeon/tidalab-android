package com.facebook.react.jscexecutor;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.soloader.SoLoader;
@DoNotStrip
/* loaded from: classes.dex */
public class JSCExecutor extends JavaScriptExecutor {
    static {
        SoLoader.loadLibrary("jscexecutor");
    }

    public JSCExecutor(ReadableNativeMap readableNativeMap) {
        super(initHybrid(readableNativeMap));
    }

    private static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        return "JSCExecutor";
    }
}
