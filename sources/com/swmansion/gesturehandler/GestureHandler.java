package com.swmansion.gesturehandler;

import android.view.MotionEvent;
import android.view.View;
import com.facebook.react.bridge.UiThreadUtil;
import com.swmansion.gesturehandler.GestureHandler;
import com.swmansion.gesturehandler.react.RNGestureHandlerInteractionManager;
import com.swmansion.gesturehandler.react.RNGestureHandlerModule;
import java.util.Objects;
/* loaded from: classes.dex */
public class GestureHandler<T extends GestureHandler> {
    public static short sNextEventCoalescingKey;
    public static MotionEvent.PointerCoords[] sPointerCoords;
    public static MotionEvent.PointerProperties[] sPointerProps;
    public int mActivationIndex;
    public short mEventCoalescingKey;
    public float[] mHitSlop;
    public RNGestureHandlerInteractionManager mInteractionController;
    public boolean mIsActive;
    public boolean mIsAwaiting;
    public float mLastEventOffsetX;
    public float mLastEventOffsetY;
    public float mLastX;
    public float mLastY;
    public OnTouchEventListener<T> mListener;
    public GestureHandlerOrchestrator mOrchestrator;
    public boolean mShouldCancelWhenOutside;
    public int mTag;
    public View mView;
    public boolean mWithinBounds;
    public float mX;
    public float mY;
    public final int[] mTrackedPointerIDs = new int[12];
    public int mTrackedPointersCount = 0;
    public int mState = 0;
    public boolean mEnabled = true;
    public int mNumberOfPointers = 0;

    public static boolean hitSlopSet(float f) {
        return !Float.isNaN(f);
    }

    public final void activate() {
        int i = this.mState;
        if (i == 0 || i == 2) {
            moveToState(4);
        }
    }

    public final void begin() {
        if (this.mState == 0) {
            moveToState(2);
        }
    }

    public final void cancel() {
        int i = this.mState;
        if (i == 4 || i == 0 || i == 2) {
            onCancel();
            moveToState(3);
        }
    }

    public void dispatchStateChange(int i, int i2) {
        OnTouchEventListener<T> onTouchEventListener = this.mListener;
        if (onTouchEventListener != null) {
            RNGestureHandlerModule.this.onStateChange(this, i, i2);
        }
    }

    public final void end() {
        int i = this.mState;
        if (i == 2 || i == 4) {
            moveToState(5);
        }
    }

    public final void fail() {
        int i = this.mState;
        if (i == 4 || i == 0 || i == 2) {
            moveToState(1);
        }
    }

    public float getLastRelativePositionX() {
        return this.mLastX - this.mLastEventOffsetX;
    }

    public float getLastRelativePositionY() {
        return this.mLastY - this.mLastEventOffsetY;
    }

    public boolean isWithinBounds(View view, float f, float f2) {
        float width = view.getWidth();
        float height = view.getHeight();
        float[] fArr = this.mHitSlop;
        float f3 = 0.0f;
        if (fArr != null) {
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[2];
            float f7 = fArr[3];
            f3 = hitSlopSet(f4) ? 0.0f - f4 : 0.0f;
            if (hitSlopSet(f5)) {
                f3 = 0.0f - f5;
            }
            if (hitSlopSet(f6)) {
                width += f6;
            }
            if (hitSlopSet(f7)) {
                height += f7;
            }
            float[] fArr2 = this.mHitSlop;
            float f8 = fArr2[4];
            float f9 = fArr2[5];
            if (hitSlopSet(f8)) {
                if (!hitSlopSet(f4)) {
                    f3 = width - f8;
                } else if (!hitSlopSet(f6)) {
                    width = f8 + f3;
                }
            }
            if (hitSlopSet(f9)) {
                if (!hitSlopSet(f5)) {
                    f3 = height - f9;
                } else if (!hitSlopSet(f7)) {
                    height = f3 + f9;
                }
            }
        } else {
            f3 = 0.0f;
        }
        return f >= f3 && f <= width && f2 >= f3 && f2 <= height;
    }

    public final void moveToState(int i) {
        OnTouchEventListener<T> onTouchEventListener;
        UiThreadUtil.assertOnUiThread();
        int i2 = this.mState;
        if (i2 != i) {
            this.mState = i;
            if (i == 4) {
                short s = sNextEventCoalescingKey;
                sNextEventCoalescingKey = (short) (s + 1);
                this.mEventCoalescingKey = s;
            }
            GestureHandlerOrchestrator gestureHandlerOrchestrator = this.mOrchestrator;
            gestureHandlerOrchestrator.mHandlingChangeSemaphore++;
            if (GestureHandlerOrchestrator.isFinished(i)) {
                for (int i3 = 0; i3 < gestureHandlerOrchestrator.mAwaitingHandlersCount; i3++) {
                    GestureHandler gestureHandler = gestureHandlerOrchestrator.mAwaitingHandlers[i3];
                    if (GestureHandlerOrchestrator.shouldHandlerWaitForOther(gestureHandler, this)) {
                        if (i == 5) {
                            gestureHandler.cancel();
                            gestureHandler.mIsAwaiting = false;
                        } else {
                            gestureHandlerOrchestrator.tryActivate(gestureHandler);
                        }
                    }
                }
                gestureHandlerOrchestrator.cleanupAwaitingHandlers();
            }
            if (i == 4) {
                gestureHandlerOrchestrator.tryActivate(this);
            } else if (i2 != 4 && i2 != 5) {
                OnTouchEventListener<T> onTouchEventListener2 = this.mListener;
                if (onTouchEventListener2 != null) {
                    RNGestureHandlerModule.this.onStateChange(this, i, i2);
                }
            } else if (this.mIsActive && (onTouchEventListener = this.mListener) != null) {
                RNGestureHandlerModule.this.onStateChange(this, i, i2);
            }
            int i4 = gestureHandlerOrchestrator.mHandlingChangeSemaphore - 1;
            gestureHandlerOrchestrator.mHandlingChangeSemaphore = i4;
            if (gestureHandlerOrchestrator.mIsHandlingTouch || i4 != 0) {
                gestureHandlerOrchestrator.mFinishedHandlersCleanupScheduled = true;
            } else {
                gestureHandlerOrchestrator.cleanupFinishedHandlers();
            }
            onStateChange(i, i2);
        }
    }

    public void onCancel() {
    }

    public void onHandle(MotionEvent motionEvent) {
        moveToState(1);
    }

    public void onReset() {
    }

    public void onStateChange(int i, int i2) {
    }

    public T setHitSlop(float f, float f2, float f3, float f4, float f5, float f6) {
        if (this.mHitSlop == null) {
            this.mHitSlop = new float[6];
        }
        float[] fArr = this.mHitSlop;
        fArr[0] = f;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f4;
        fArr[4] = f5;
        fArr[5] = f6;
        if (hitSlopSet(f5) && hitSlopSet(f) && hitSlopSet(f3)) {
            throw new IllegalArgumentException("Cannot have all of left, right and width defined");
        } else if (hitSlopSet(f5) && !hitSlopSet(f) && !hitSlopSet(f3)) {
            throw new IllegalArgumentException("When width is set one of left or right pads need to be defined");
        } else if (hitSlopSet(f6) && hitSlopSet(f4) && hitSlopSet(f2)) {
            throw new IllegalArgumentException("Cannot have all of top, bottom and height defined");
        } else if (!hitSlopSet(f6) || hitSlopSet(f4) || hitSlopSet(f2)) {
            return this;
        } else {
            throw new IllegalArgumentException("When height is set one of top or bottom pads need to be defined");
        }
    }

    public boolean shouldBeCancelledBy(GestureHandler gestureHandler) {
        RNGestureHandlerInteractionManager rNGestureHandlerInteractionManager;
        if (!(gestureHandler == this || (rNGestureHandlerInteractionManager = this.mInteractionController) == null)) {
            Objects.requireNonNull(rNGestureHandlerInteractionManager);
        }
        return false;
    }

    public boolean shouldRecognizeSimultaneously(GestureHandler gestureHandler) {
        int[] iArr;
        if (gestureHandler == this) {
            return true;
        }
        RNGestureHandlerInteractionManager rNGestureHandlerInteractionManager = this.mInteractionController;
        if (!(rNGestureHandlerInteractionManager == null || (iArr = rNGestureHandlerInteractionManager.mSimultaneousRelations.get(this.mTag)) == null)) {
            for (int i : iArr) {
                if (i == gestureHandler.mTag) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean shouldRequireToWaitForFailure(GestureHandler gestureHandler) {
        if (gestureHandler == this) {
            return false;
        }
        RNGestureHandlerInteractionManager rNGestureHandlerInteractionManager = this.mInteractionController;
        return false;
    }

    public String toString() {
        View view = this.mView;
        String simpleName = view == null ? null : view.getClass().getSimpleName();
        return getClass().getSimpleName() + "@[" + this.mTag + "]:" + simpleName;
    }
}
