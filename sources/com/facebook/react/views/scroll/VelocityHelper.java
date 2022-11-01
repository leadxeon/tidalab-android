package com.facebook.react.views.scroll;

import android.view.MotionEvent;
import android.view.VelocityTracker;
/* loaded from: classes.dex */
public class VelocityHelper {
    public VelocityTracker mVelocityTracker;
    public float mXVelocity;
    public float mYVelocity;

    public void calculateVelocity(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        if (action == 1 || action == 3) {
            this.mVelocityTracker.computeCurrentVelocity(1);
            this.mXVelocity = this.mVelocityTracker.getXVelocity();
            this.mYVelocity = this.mVelocityTracker.getYVelocity();
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.mVelocityTracker = null;
            }
        }
    }
}
