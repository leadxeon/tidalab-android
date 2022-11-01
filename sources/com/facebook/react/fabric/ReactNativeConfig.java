package com.facebook.react.fabric;

import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public interface ReactNativeConfig {
    @DoNotStrip
    boolean getBool(String str);

    @DoNotStrip
    double getDouble(String str);

    @DoNotStrip
    int getInt64(String str);

    @DoNotStrip
    String getString(String str);
}
