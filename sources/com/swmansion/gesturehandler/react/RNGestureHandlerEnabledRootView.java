package com.swmansion.gesturehandler.react;

import android.os.Bundle;
import android.view.MotionEvent;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
/* loaded from: classes.dex */
public class RNGestureHandlerEnabledRootView extends ReactRootView {
    public RNGestureHandlerRootHelper mGestureRootHelper;
    public ReactInstanceManager mReactInstanceManager;

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mGestureRootHelper;
        if (rNGestureHandlerRootHelper == null || !rNGestureHandlerRootHelper.dispatchTouchEvent(motionEvent)) {
            return super.dispatchTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // com.facebook.react.ReactRootView, android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mGestureRootHelper;
        if (rNGestureHandlerRootHelper != null) {
            rNGestureHandlerRootHelper.requestDisallowInterceptTouchEvent();
        }
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    @Override // com.facebook.react.ReactRootView
    public void startReactApplication(ReactInstanceManager reactInstanceManager, String str, Bundle bundle) {
        super.startReactApplication(reactInstanceManager, str, bundle);
        this.mReactInstanceManager = reactInstanceManager;
    }
}
