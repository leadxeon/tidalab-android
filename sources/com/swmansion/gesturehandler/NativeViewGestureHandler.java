package com.swmansion.gesturehandler;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public class NativeViewGestureHandler extends GestureHandler<NativeViewGestureHandler> {
    public boolean mDisallowInterruption;
    public boolean mShouldActivateOnStart;

    public NativeViewGestureHandler() {
        this.mShouldCancelWhenOutside = true;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onCancel() {
        long uptimeMillis = SystemClock.uptimeMillis();
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
        obtain.setAction(3);
        this.mView.onTouchEvent(obtain);
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onHandle(MotionEvent motionEvent) {
        View view = this.mView;
        int i = this.mState;
        boolean z = true;
        if (motionEvent.getActionMasked() == 1) {
            view.onTouchEvent(motionEvent);
            if ((i == 0 || i == 2) && view.isPressed()) {
                activate();
            }
            end();
        } else if (i == 0 || i == 2) {
            if (this.mShouldActivateOnStart) {
                if (view instanceof ViewGroup) {
                    ((ViewGroup) view).onInterceptTouchEvent(motionEvent);
                }
                view.onTouchEvent(motionEvent);
                activate();
                return;
            }
            if (!(view instanceof ViewGroup) || !((ViewGroup) view).onInterceptTouchEvent(motionEvent)) {
                z = false;
            }
            if (z) {
                view.onTouchEvent(motionEvent);
                activate();
            } else if (i != 2) {
                begin();
            }
        } else if (i == 4) {
            view.onTouchEvent(motionEvent);
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        return !this.mDisallowInterruption;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        if (gestureHandler instanceof NativeViewGestureHandler) {
            NativeViewGestureHandler nativeViewGestureHandler = (NativeViewGestureHandler) gestureHandler;
            if (nativeViewGestureHandler.mState == 4 && nativeViewGestureHandler.mDisallowInterruption) {
                return false;
            }
        }
        boolean z = !this.mDisallowInterruption;
        int i = this.mState;
        return !(i == 4 && gestureHandler.mState == 4 && z) && i == 4 && z;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public boolean shouldRequireToWaitForFailure(GestureHandler gestureHandler) {
        return false;
    }
}
