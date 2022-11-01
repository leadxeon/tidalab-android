package com.facebook.react.views.swiperefresh;

import android.view.MotionEvent;
import android.view.ViewConfiguration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
/* loaded from: classes.dex */
public class ReactSwipeRefreshLayout extends SwipeRefreshLayout {
    public boolean mIntercepted;
    public float mPrevTouchX;
    public int mTouchSlop;
    public boolean mDidLayout = false;
    public boolean mRefreshing = false;
    public float mProgressViewOffset = 0.0f;

    public ReactSwipeRefreshLayout(ReactContext reactContext) {
        super(reactContext);
        this.mTouchSlop = ViewConfiguration.get(reactContext).getScaledTouchSlop();
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z;
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mPrevTouchX = motionEvent.getX();
            this.mIntercepted = false;
        } else if (action == 2) {
            float abs = Math.abs(motionEvent.getX() - this.mPrevTouchX);
            if (this.mIntercepted || abs > this.mTouchSlop) {
                this.mIntercepted = true;
                z = false;
                if (z || !super.onInterceptTouchEvent(motionEvent)) {
                    return false;
                }
                R$style.notifyNativeGestureStarted(this, motionEvent);
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                return true;
            }
        }
        z = true;
        if (z) {
        }
        return false;
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!this.mDidLayout) {
            this.mDidLayout = true;
            setProgressViewOffset(this.mProgressViewOffset);
            setRefreshing(this.mRefreshing);
        }
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout, android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(z);
        }
    }

    public void setProgressViewOffset(float f) {
        this.mProgressViewOffset = f;
        if (this.mDidLayout) {
            int progressCircleDiameter = getProgressCircleDiameter();
            int round = Math.round(PixelUtil.toPixelFromDIP(f)) - progressCircleDiameter;
            int round2 = Math.round(PixelUtil.toPixelFromDIP(f + 64.0f) - progressCircleDiameter);
            this.mScale = false;
            this.mOriginalOffsetTop = round;
            this.mSpinnerOffsetEnd = round2;
            this.mUsingCustomStart = true;
            reset();
            super.mRefreshing = false;
        }
    }

    @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    public void setRefreshing(boolean z) {
        this.mRefreshing = z;
        if (this.mDidLayout) {
            super.setRefreshing(z);
        }
    }
}
