package com.facebook.react.turbomodule.core;

import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import com.facebook.soloader.SoLoader;
/* loaded from: classes.dex */
public class CallInvokerHolderImpl implements CallInvokerHolder {
    static {
        SoLoader.loadLibrary("turbomodulejsijni");
    }
}
