package com.facebook.react.bridge;

import android.app.Activity;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public abstract class ReactContextBaseJavaModule extends BaseJavaModule {
    private final ReactApplicationContext mReactApplicationContext;

    public ReactContextBaseJavaModule() {
        this.mReactApplicationContext = null;
    }

    public final Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }

    public final ReactApplicationContext getReactApplicationContext() {
        ReactApplicationContext reactApplicationContext = this.mReactApplicationContext;
        R$dimen.assertNotNull(reactApplicationContext, "Tried to get ReactApplicationContext even though NativeModule wasn't instantiated with one");
        return reactApplicationContext;
    }

    public final ReactApplicationContext getReactApplicationContextIfActiveOrWarn() {
        if (this.mReactApplicationContext.hasActiveCatalystInstance()) {
            return this.mReactApplicationContext;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Catalyst Instance has already disappeared: requested by ");
        outline13.append(getName());
        ReactSoftException.logSoftException("ReactContextBaseJavaModule", new RuntimeException(outline13.toString()));
        return null;
    }

    public ReactContextBaseJavaModule(ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }
}
