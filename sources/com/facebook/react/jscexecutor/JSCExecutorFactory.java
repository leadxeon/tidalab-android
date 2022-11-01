package com.facebook.react.jscexecutor;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
import com.facebook.react.bridge.WritableNativeMap;
/* loaded from: classes.dex */
public class JSCExecutorFactory implements JavaScriptExecutorFactory {
    public final String mAppName;
    public final String mDeviceName;

    public JSCExecutorFactory(String str, String str2) {
        this.mAppName = str;
        this.mDeviceName = str2;
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public JavaScriptExecutor create() throws Exception {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.putString("OwnerIdentity", "ReactNative");
        writableNativeMap.putString("AppIdentity", this.mAppName);
        writableNativeMap.putString("DeviceIdentity", this.mDeviceName);
        return new JSCExecutor(writableNativeMap);
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void startSamplingProfiler() {
        throw new UnsupportedOperationException(GeneratedOutlineSupport.outline8("Starting sampling profiler not supported on ", "JSIExecutor+JSCRuntime"));
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void stopSamplingProfiler(String str) {
        throw new UnsupportedOperationException(GeneratedOutlineSupport.outline8("Stopping sampling profiler not supported on ", "JSIExecutor+JSCRuntime"));
    }

    public String toString() {
        return "JSIExecutor+JSCRuntime";
    }
}
