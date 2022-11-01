package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import com.swmansion.gesturehandler.RotationGestureDetector;
/* loaded from: classes.dex */
public class RotationGestureHandler extends GestureHandler<RotationGestureHandler> {
    public RotationGestureDetector.OnRotationGestureListener mGestureListener = new AnonymousClass1();
    public double mLastRotation;
    public double mLastVelocity;
    public RotationGestureDetector mRotationGestureDetector;

    /* renamed from: com.swmansion.gesturehandler.RotationGestureHandler$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements RotationGestureDetector.OnRotationGestureListener {
        public AnonymousClass1() {
        }
    }

    public RotationGestureHandler() {
        this.mShouldCancelWhenOutside = false;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onHandle(MotionEvent motionEvent) {
        int i = this.mState;
        if (i == 0) {
            this.mLastVelocity = 0.0d;
            this.mLastRotation = 0.0d;
            this.mRotationGestureDetector = new RotationGestureDetector(this.mGestureListener);
            begin();
        }
        RotationGestureDetector rotationGestureDetector = this.mRotationGestureDetector;
        if (rotationGestureDetector != null) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                rotationGestureDetector.mInProgress = false;
                rotationGestureDetector.mPointerIds[0] = motionEvent.getPointerId(motionEvent.getActionIndex());
                rotationGestureDetector.mPointerIds[1] = -1;
            } else if (actionMasked != 1) {
                if (actionMasked != 2) {
                    if (actionMasked != 5) {
                        if (actionMasked == 6 && rotationGestureDetector.mInProgress) {
                            int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                            int[] iArr = rotationGestureDetector.mPointerIds;
                            if ((pointerId == iArr[0] || pointerId == iArr[1]) && rotationGestureDetector.mInProgress) {
                                rotationGestureDetector.mInProgress = false;
                                RotationGestureDetector.OnRotationGestureListener onRotationGestureListener = rotationGestureDetector.mListener;
                                if (onRotationGestureListener != null) {
                                    RotationGestureHandler.this.end();
                                }
                            }
                        }
                    } else if (!rotationGestureDetector.mInProgress) {
                        rotationGestureDetector.mPointerIds[1] = motionEvent.getPointerId(motionEvent.getActionIndex());
                        rotationGestureDetector.mInProgress = true;
                        rotationGestureDetector.mPrevTime = motionEvent.getEventTime();
                        rotationGestureDetector.mPrevAngle = Double.NaN;
                        rotationGestureDetector.updateCurrent(motionEvent);
                        RotationGestureDetector.OnRotationGestureListener onRotationGestureListener2 = rotationGestureDetector.mListener;
                        if (onRotationGestureListener2 != null) {
                            AnonymousClass1 r1 = (AnonymousClass1) onRotationGestureListener2;
                        }
                    }
                } else if (rotationGestureDetector.mInProgress) {
                    rotationGestureDetector.updateCurrent(motionEvent);
                    RotationGestureDetector.OnRotationGestureListener onRotationGestureListener3 = rotationGestureDetector.mListener;
                    if (onRotationGestureListener3 != null) {
                        AnonymousClass1 r3 = (AnonymousClass1) onRotationGestureListener3;
                        RotationGestureHandler rotationGestureHandler = RotationGestureHandler.this;
                        double d = rotationGestureHandler.mLastRotation;
                        double d2 = rotationGestureDetector.mAngleDiff + d;
                        rotationGestureHandler.mLastRotation = d2;
                        long j = rotationGestureDetector.mCurrTime - rotationGestureDetector.mPrevTime;
                        if (j > 0) {
                            rotationGestureHandler.mLastVelocity = (d2 - d) / j;
                        }
                        if (Math.abs(d2) >= 0.08726646259971647d) {
                            RotationGestureHandler rotationGestureHandler2 = RotationGestureHandler.this;
                            if (rotationGestureHandler2.mState == 2) {
                                rotationGestureHandler2.activate();
                            }
                        }
                    }
                }
            } else if (rotationGestureDetector.mInProgress) {
                rotationGestureDetector.mInProgress = false;
                RotationGestureDetector.OnRotationGestureListener onRotationGestureListener4 = rotationGestureDetector.mListener;
                if (onRotationGestureListener4 != null) {
                    RotationGestureHandler.this.end();
                }
            }
        }
        if (motionEvent.getActionMasked() != 1) {
            return;
        }
        if (i == 4) {
            end();
        } else {
            fail();
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onReset() {
        this.mRotationGestureDetector = null;
        this.mLastVelocity = 0.0d;
        this.mLastRotation = 0.0d;
    }
}
