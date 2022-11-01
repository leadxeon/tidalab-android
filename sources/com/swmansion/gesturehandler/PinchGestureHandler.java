package com.swmansion.gesturehandler;

import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewConfiguration;
/* loaded from: classes.dex */
public class PinchGestureHandler extends GestureHandler<PinchGestureHandler> {
    public ScaleGestureDetector.OnScaleGestureListener mGestureListener = new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.swmansion.gesturehandler.PinchGestureHandler.1
        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            PinchGestureHandler pinchGestureHandler = PinchGestureHandler.this;
            double d = pinchGestureHandler.mLastScaleFactor;
            pinchGestureHandler.mLastScaleFactor *= scaleGestureDetector.getScaleFactor();
            long timeDelta = scaleGestureDetector.getTimeDelta();
            if (timeDelta > 0) {
                PinchGestureHandler pinchGestureHandler2 = PinchGestureHandler.this;
                pinchGestureHandler2.mLastVelocity = (pinchGestureHandler2.mLastScaleFactor - d) / timeDelta;
            }
            float abs = Math.abs(PinchGestureHandler.this.mStartingSpan - scaleGestureDetector.getCurrentSpan());
            PinchGestureHandler pinchGestureHandler3 = PinchGestureHandler.this;
            if (abs < pinchGestureHandler3.mSpanSlop || pinchGestureHandler3.mState != 2) {
                return true;
            }
            pinchGestureHandler3.activate();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            PinchGestureHandler.this.mStartingSpan = scaleGestureDetector.getCurrentSpan();
            return true;
        }

        @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }
    };
    public double mLastScaleFactor;
    public double mLastVelocity;
    public ScaleGestureDetector mScaleGestureDetector;
    public float mSpanSlop;
    public float mStartingSpan;

    public PinchGestureHandler() {
        this.mShouldCancelWhenOutside = false;
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onHandle(MotionEvent motionEvent) {
        if (this.mState == 0) {
            Context context = this.mView.getContext();
            this.mLastVelocity = 0.0d;
            this.mLastScaleFactor = 1.0d;
            this.mScaleGestureDetector = new ScaleGestureDetector(context, this.mGestureListener);
            this.mSpanSlop = ViewConfiguration.get(context).getScaledTouchSlop();
            begin();
        }
        ScaleGestureDetector scaleGestureDetector = this.mScaleGestureDetector;
        if (scaleGestureDetector != null) {
            scaleGestureDetector.onTouchEvent(motionEvent);
        }
        int pointerCount = motionEvent.getPointerCount();
        if (motionEvent.getActionMasked() == 6) {
            pointerCount--;
        }
        if (this.mState == 4 && pointerCount < 2) {
            end();
        } else if (motionEvent.getActionMasked() == 1) {
            fail();
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onReset() {
        this.mScaleGestureDetector = null;
        this.mLastVelocity = 0.0d;
        this.mLastScaleFactor = 1.0d;
    }
}
