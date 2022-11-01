package com.swmansion.gesturehandler;

import android.view.MotionEvent;
/* loaded from: classes.dex */
public class RotationGestureDetector {
    public float mAnchorX;
    public float mAnchorY;
    public double mAngleDiff;
    public long mCurrTime;
    public boolean mInProgress;
    public OnRotationGestureListener mListener;
    public int[] mPointerIds = new int[2];
    public double mPrevAngle;
    public long mPrevTime;

    /* loaded from: classes.dex */
    public interface OnRotationGestureListener {
    }

    public RotationGestureDetector(OnRotationGestureListener onRotationGestureListener) {
        this.mListener = onRotationGestureListener;
    }

    public final void updateCurrent(MotionEvent motionEvent) {
        this.mPrevTime = this.mCurrTime;
        this.mCurrTime = motionEvent.getEventTime();
        int findPointerIndex = motionEvent.findPointerIndex(this.mPointerIds[0]);
        int findPointerIndex2 = motionEvent.findPointerIndex(this.mPointerIds[1]);
        float x = motionEvent.getX(findPointerIndex);
        float y = motionEvent.getY(findPointerIndex);
        float x2 = motionEvent.getX(findPointerIndex2);
        float y2 = motionEvent.getY(findPointerIndex2);
        float f = y2 - y;
        this.mAnchorX = (x + x2) * 0.5f;
        this.mAnchorY = (y + y2) * 0.5f;
        double d = -Math.atan2(f, x2 - x);
        if (Double.isNaN(this.mPrevAngle)) {
            this.mAngleDiff = 0.0d;
        } else {
            this.mAngleDiff = this.mPrevAngle - d;
        }
        this.mPrevAngle = d;
        double d2 = this.mAngleDiff;
        if (d2 > 3.141592653589793d) {
            this.mAngleDiff = d2 - 3.141592653589793d;
        } else if (d2 < -3.141592653589793d) {
            this.mAngleDiff = d2 + 3.141592653589793d;
        }
        double d3 = this.mAngleDiff;
        if (d3 > 1.5707963267948966d) {
            this.mAngleDiff = d3 - 3.141592653589793d;
        } else if (d3 < -1.5707963267948966d) {
            this.mAngleDiff = d3 + 3.141592653589793d;
        }
    }
}
