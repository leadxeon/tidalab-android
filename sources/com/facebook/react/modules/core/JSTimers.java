package com.facebook.react.modules.core;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.WritableArray;
@DoNotStrip
/* loaded from: classes.dex */
public interface JSTimers extends JavaScriptModule {
    void callIdleCallbacks(double d);

    void callTimers(WritableArray writableArray);

    void emitTimeDriftWarning(String str);
}
