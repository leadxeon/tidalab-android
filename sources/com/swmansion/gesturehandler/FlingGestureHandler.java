package com.swmansion.gesturehandler;

import android.os.Handler;
import android.view.MotionEvent;
/* loaded from: classes.dex */
public class FlingGestureHandler extends GestureHandler<FlingGestureHandler> {
    public Handler mHandler;
    public int mMaxNumberOfPointersSimultaneously;
    public float mStartX;
    public float mStartY;
    public int mDirection = 1;
    public int mNumberOfPointersRequired = 1;
    public final Runnable mFailDelayed = new Runnable() { // from class: com.swmansion.gesturehandler.FlingGestureHandler.1
        @Override // java.lang.Runnable
        public void run() {
            FlingGestureHandler.this.fail();
        }
    };

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onCancel() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onHandle(MotionEvent motionEvent) {
        int i = this.mState;
        if (i == 0) {
            this.mStartX = motionEvent.getRawX();
            this.mStartY = motionEvent.getRawY();
            begin();
            this.mMaxNumberOfPointersSimultaneously = 1;
            Handler handler = this.mHandler;
            if (handler == null) {
                this.mHandler = new Handler();
            } else {
                handler.removeCallbacksAndMessages(null);
            }
            this.mHandler.postDelayed(this.mFailDelayed, 800L);
        }
        if (i == 2) {
            tryEndFling(motionEvent);
            if (motionEvent.getPointerCount() > this.mMaxNumberOfPointersSimultaneously) {
                this.mMaxNumberOfPointersSimultaneously = motionEvent.getPointerCount();
            }
            if (motionEvent.getActionMasked() == 1 && !tryEndFling(motionEvent)) {
                fail();
            }
        }
    }

    @Override // com.swmansion.gesturehandler.GestureHandler
    public void onReset() {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public final boolean tryEndFling(MotionEvent motionEvent) {
        if (this.mMaxNumberOfPointersSimultaneously != this.mNumberOfPointersRequired) {
            return false;
        }
        if (((this.mDirection & 1) == 0 || motionEvent.getRawX() - this.mStartX <= ((float) 160)) && (((this.mDirection & 2) == 0 || this.mStartX - motionEvent.getRawX() <= ((float) 160)) && (((this.mDirection & 4) == 0 || this.mStartY - motionEvent.getRawY() <= ((float) 160)) && ((this.mDirection & 8) == 0 || motionEvent.getRawY() - this.mStartY <= ((float) 160))))) {
            return false;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        activate();
        end();
        return true;
    }
}
