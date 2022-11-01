package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public interface JavaJSExecutor {

    /* loaded from: classes.dex */
    public interface Factory {
        JavaJSExecutor create() throws Exception;
    }

    /* loaded from: classes.dex */
    public static class ProxyExecutorException extends Exception {
        public ProxyExecutorException(Throwable th) {
            super(th);
        }
    }

    void close();

    @DoNotStrip
    String executeJSCall(String str, String str2) throws ProxyExecutorException;

    @DoNotStrip
    void loadApplicationScript(String str) throws ProxyExecutorException;

    @DoNotStrip
    void setGlobalVariable(String str, String str2);
}
