package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewParent;
import androidx.recyclerview.R$dimen;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.views.view.ReactViewGroup;
/* loaded from: classes.dex */
public class RNGestureHandlerRootView extends ReactViewGroup {
    public boolean mEnabled;
    public RNGestureHandlerRootHelper mRootHelper;

    public RNGestureHandlerRootView(Context context) {
        super(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.mEnabled) {
            RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRootHelper;
            R$dimen.assertNotNull(rNGestureHandlerRootHelper);
            if (rNGestureHandlerRootHelper.dispatchTouchEvent(motionEvent)) {
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        boolean z;
        super.onAttachedToWindow();
        UiThreadUtil.assertOnUiThread();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            if ((parent instanceof RNGestureHandlerEnabledRootView) || (parent instanceof RNGestureHandlerRootView)) {
                z = true;
                break;
            }
        }
        z = false;
        boolean z2 = !z;
        this.mEnabled = z2;
        if (!z2) {
            Log.i("ReactNative", "[GESTURE HANDLER] Gesture handler is already enabled for a parent view");
        }
        if (this.mEnabled && this.mRootHelper == null) {
            this.mRootHelper = new RNGestureHandlerRootHelper((ReactContext) getContext(), this);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (this.mEnabled) {
            RNGestureHandlerRootHelper rNGestureHandlerRootHelper = this.mRootHelper;
            R$dimen.assertNotNull(rNGestureHandlerRootHelper);
            rNGestureHandlerRootHelper.requestDisallowInterceptTouchEvent();
        }
        super.requestDisallowInterceptTouchEvent(z);
    }
}
