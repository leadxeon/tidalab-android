package com.facebook.hermes.reactexecutor;

import com.facebook.hermes.instrumentation.HermesSamplingProfiler;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
/* loaded from: classes.dex */
public class HermesExecutorFactory implements JavaScriptExecutorFactory {
    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public JavaScriptExecutor create() {
        return new HermesExecutor();
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void startSamplingProfiler() {
        HermesSamplingProfiler.enable();
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void stopSamplingProfiler(String str) {
        HermesSamplingProfiler.dumpSampledTraceToFile(str);
        HermesSamplingProfiler.disable();
    }

    public String toString() {
        return "JSIExecutor+HermesRuntime";
    }
}
