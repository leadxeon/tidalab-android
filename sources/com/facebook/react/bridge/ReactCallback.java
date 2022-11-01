package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
@DoNotStrip
/* loaded from: classes.dex */
public interface ReactCallback {
    @DoNotStrip
    void decrementPendingJSCalls();

    @DoNotStrip
    void incrementPendingJSCalls();

    @DoNotStrip
    void onBatchComplete();
}
