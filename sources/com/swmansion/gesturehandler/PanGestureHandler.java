package com.swmansion.gesturehandler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
/* loaded from: classes.dex */
public class PanGestureHandler extends GestureHandler<PanGestureHandler> {
    public boolean mAverageTouches;
    public float mLastVelocityX;
    public float mLastVelocityY;
    public float mLastX;
    public float mLastY;
    public float mMinDistSq;
    public float mOffsetX;
    public float mOffsetY;
    public float mStartX;
    public float mStartY;
    public VelocityTracker mVelocityTracker;
    public float mActiveOffsetXStart = Float.MAX_VALUE;
    public float mActiveOffsetXEnd = Float.MIN_VALUE;
    public float mFailOffsetXStart = Float.MIN_VALUE;
    public float mFailOffsetXEnd = Float.MAX_VALUE;
    public float mActiveOffsetYStart = Float.MAX_VALUE;
    public float mActiveOffsetYEnd = Float.MIN_VALUE;
    public float mFailOffsetYStart = Float.MIN_VALUE;
    public float mFailOffsetYEnd = Float.MAX_VALUE;
    public float mMinVelocityX = Float.MAX_VALUE;
    public float mMinVelocityY = Float.MAX_VALUE;
    public float mMinVelocitySq = Float.MAX_VALUE;
    public int mMinPointers = 1;
    public int mMaxPointers = 10;

    public PanGestureHandler(Context context) {
        this.mMinDistSq = Float.MIN_VALUE;
        int scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMinDistSq = scaledTouchSlop * scaledTouchSlop;
    }

    public static void addVelocityMovement(VelocityTracker velocityTracker, MotionEvent motionEvent) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        motionEvent.offsetLocation(rawX, rawY);
        velocityTracker.addMovement(motionEvent);
        motionEvent.offsetLocation(-rawX, -rawY);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0112  */
    @Override // com.swmansion.gesturehandler.GestureHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onHandle(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instructions count: 423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.gesturehandler.PanGestureHandler.onHandle(android.view.MotionEvent):void");
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onReset() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }
}
