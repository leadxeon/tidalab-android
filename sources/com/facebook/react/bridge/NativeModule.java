package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public interface NativeModule {

    /* loaded from: classes.dex */
    public interface NativeMethod {
        String getType();

        void invoke(JSInstance jSInstance, ReadableArray readableArray);
    }

    boolean canOverrideExistingModule();

    String getName();

    void initialize();

    void onCatalystInstanceDestroy();
}
