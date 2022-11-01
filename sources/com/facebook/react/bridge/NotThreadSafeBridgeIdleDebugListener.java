package com.facebook.react.bridge;
/* loaded from: classes.dex */
public interface NotThreadSafeBridgeIdleDebugListener {
    void onBridgeDestroyed();

    void onTransitionToBridgeBusy();

    void onTransitionToBridgeIdle();
}
