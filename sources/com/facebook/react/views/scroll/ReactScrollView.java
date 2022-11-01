package com.facebook.react.views.scroll;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R$dimen;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ReactScrollView extends ScrollView implements ReactClippingViewGroup, ViewGroup.OnHierarchyChangeListener, View.OnLayoutChangeListener {
    public static Field sScrollerField = null;
    public static boolean sTriedToGetScrollerField = false;
    public boolean mActivelyScrolling;
    public Rect mClippingRect;
    public View mContentView;
    public boolean mDragging;
    public Drawable mEndBackground;
    public FpsListener mFpsListener;
    public Runnable mPostTouchRunnable;
    public boolean mRemoveClippedSubviews;
    public String mScrollPerfTag;
    public boolean mSendMomentumEvents;
    public List<Integer> mSnapOffsets;
    public final OnScrollDispatchHelper mOnScrollDispatchHelper = new OnScrollDispatchHelper();
    public final VelocityHelper mVelocityHelper = new VelocityHelper();
    public final Rect mRect = new Rect();
    public String mOverflow = "hidden";
    public boolean mPagingEnabled = false;
    public boolean mScrollEnabled = true;
    public int mEndFillColor = 0;
    public int mSnapInterval = 0;
    public float mDecelerationRate = 0.985f;
    public boolean mSnapToStart = true;
    public boolean mSnapToEnd = true;
    public ReactViewBackgroundManager mReactBackgroundManager = new ReactViewBackgroundManager(this);
    public final OverScroller mScroller = getOverScrollerFromParent();

    public ReactScrollView(ReactContext reactContext, FpsListener fpsListener) {
        super(reactContext);
        this.mFpsListener = null;
        this.mFpsListener = fpsListener;
        setOnHierarchyChangeListener(this);
        setScrollBarStyle(33554432);
    }

    private int getMaxScrollY() {
        return Math.max(0, this.mContentView.getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
    }

    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                Field declaredField = ScrollView.class.getDeclaredField("mScroller");
                sScrollerField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.w("ReactNative", "Failed to get mScroller field for ScrollView! This app will exhibit the bounce-back scrolling bug :(");
            }
        }
        Field field = sScrollerField;
        OverScroller overScroller = null;
        if (field != null) {
            try {
                Object obj = field.get(this);
                if (obj instanceof OverScroller) {
                    overScroller = (OverScroller) obj;
                } else {
                    Log.w("ReactNative", "Failed to cast mScroller field in ScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to get mScroller from ScrollView!", e);
            }
        }
        return overScroller;
    }

    private int getSnapInterval() {
        int i = this.mSnapInterval;
        return i != 0 ? i : getHeight();
    }

    @Override // android.widget.ScrollView, android.view.View
    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View childAt = getChildAt(0);
            if (!(this.mEndBackground == null || childAt == null || childAt.getBottom() >= getHeight())) {
                this.mEndBackground.setBounds(0, childAt.getBottom(), getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        getDrawingRect(this.mRect);
        String str = this.mOverflow;
        str.hashCode();
        if (!str.equals("visible")) {
            canvas.clipRect(this.mRect);
        }
        super.draw(canvas);
    }

    public final void enableFpsListener() {
        if (isScrollPerfLoggingEnabled()) {
            R$dimen.assertNotNull(this.mFpsListener);
            R$dimen.assertNotNull(this.mScrollPerfTag);
            this.mFpsListener.enable(this.mScrollPerfTag);
        }
    }

    @Override // android.widget.ScrollView
    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.mScrollEnabled || (keyCode != 19 && keyCode != 20)) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
    }

    public void flashScrollIndicators() {
        awakenScrollBars();
    }

    @Override // android.widget.ScrollView
    public void fling(int i) {
        float signum = Math.signum(this.mOnScrollDispatchHelper.mYFlingVelocity);
        if (signum == 0.0f) {
            signum = Math.signum(i);
        }
        int abs = (int) (Math.abs(i) * signum);
        if (this.mPagingEnabled) {
            flingAndSnap(abs);
        } else if (this.mScroller != null) {
            this.mScroller.fling(getScrollX(), getScrollY(), 0, abs, 0, 0, 0, Integer.MAX_VALUE, 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            postInvalidateOnAnimation();
        } else {
            super.fling(abs);
        }
        handlePostTouchScrolling(0, abs);
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0108, code lost:
        if (getScrollY() <= r5) goto L_0x010a;
     */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0160  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void flingAndSnap(int r19) {
        /*
            Method dump skipped, instructions count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.scroll.ReactScrollView.flingAndSnap(int):void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean getChildVisibleRect(View view, Rect rect, Point point) {
        return super.getChildVisibleRect(view, rect, point);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void getClippingRect(Rect rect) {
        Rect rect2 = this.mClippingRect;
        R$dimen.assertNotNull(rect2);
        rect.set(rect2);
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public boolean getRemoveClippedSubviews() {
        return this.mRemoveClippedSubviews;
    }

    public final void handlePostTouchScrolling(int i, int i2) {
        if ((this.mSendMomentumEvents || this.mPagingEnabled || isScrollPerfLoggingEnabled()) && this.mPostTouchRunnable == null) {
            if (this.mSendMomentumEvents) {
                enableFpsListener();
                R$style.emitScrollEvent(this, ScrollEventType.MOMENTUM_BEGIN, i, i2);
            }
            this.mActivelyScrolling = false;
            Runnable runnable = new Runnable() { // from class: com.facebook.react.views.scroll.ReactScrollView.1
                public boolean mSnappingToPage = false;

                @Override // java.lang.Runnable
                public void run() {
                    ReactScrollView reactScrollView = ReactScrollView.this;
                    if (reactScrollView.mActivelyScrolling) {
                        reactScrollView.mActivelyScrolling = false;
                        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                        reactScrollView.postOnAnimationDelayed(this, 20L);
                    } else if (!reactScrollView.mPagingEnabled || this.mSnappingToPage) {
                        if (reactScrollView.mSendMomentumEvents) {
                            R$style.emitScrollEvent(reactScrollView, ScrollEventType.MOMENTUM_END, 0.0f, 0.0f);
                        }
                        ReactScrollView reactScrollView2 = ReactScrollView.this;
                        reactScrollView2.mPostTouchRunnable = null;
                        if (reactScrollView2.isScrollPerfLoggingEnabled()) {
                            R$dimen.assertNotNull(reactScrollView2.mFpsListener);
                            R$dimen.assertNotNull(reactScrollView2.mScrollPerfTag);
                            reactScrollView2.mFpsListener.disable(reactScrollView2.mScrollPerfTag);
                        }
                    } else {
                        this.mSnappingToPage = true;
                        reactScrollView.flingAndSnap(0);
                        ReactScrollView reactScrollView3 = ReactScrollView.this;
                        AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                        reactScrollView3.postOnAnimationDelayed(this, 20L);
                    }
                }
            };
            this.mPostTouchRunnable = runnable;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            postOnAnimationDelayed(runnable, 20L);
        }
    }

    public final boolean isScrollPerfLoggingEnabled() {
        String str;
        return (this.mFpsListener == null || (str = this.mScrollPerfTag) == null || str.isEmpty()) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewAdded(View view, View view2) {
        this.mContentView = view2;
        view2.addOnLayoutChangeListener(this);
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public void onChildViewRemoved(View view, View view2) {
        this.mContentView.removeOnLayoutChangeListener(this);
        this.mContentView = null;
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                R$style.notifyNativeGestureStarted(this, motionEvent);
                R$style.emitScrollEvent(this, ScrollEventType.BEGIN_DRAG, 0.0f, 0.0f);
                this.mDragging = true;
                enableFpsListener();
                return true;
            }
        } catch (IllegalArgumentException e) {
            Log.w("ReactNative", "Error intercepting touch event.", e);
        }
        return false;
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        scrollTo(getScrollX(), getScrollY());
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (this.mContentView != null) {
            int scrollY = getScrollY();
            int maxScrollY = getMaxScrollY();
            if (scrollY > maxScrollY) {
                scrollTo(getScrollX(), maxScrollY);
            }
        }
    }

    @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        R$style.assertExplicitMeasureSpec(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    @Override // android.widget.ScrollView, android.view.View
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        int maxScrollY;
        OverScroller overScroller = this.mScroller;
        if (!(overScroller == null || this.mContentView == null || overScroller.isFinished() || this.mScroller.getCurrY() == this.mScroller.getFinalY() || i2 < (maxScrollY = getMaxScrollY()))) {
            this.mScroller.abortAnimation();
            i2 = maxScrollY;
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    @Override // android.view.View
    public void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        this.mActivelyScrolling = true;
        if (this.mOnScrollDispatchHelper.onScrollChanged(i, i2)) {
            if (this.mRemoveClippedSubviews) {
                updateClippingRect();
            }
            OnScrollDispatchHelper onScrollDispatchHelper = this.mOnScrollDispatchHelper;
            R$style.emitScrollEvent(this, ScrollEventType.SCROLL, onScrollDispatchHelper.mXFlingVelocity, onScrollDispatchHelper.mYFlingVelocity);
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        this.mVelocityHelper.calculateVelocity(motionEvent);
        if ((motionEvent.getAction() & 255) == 1 && this.mDragging) {
            VelocityHelper velocityHelper = this.mVelocityHelper;
            float f = velocityHelper.mXVelocity;
            float f2 = velocityHelper.mYVelocity;
            R$style.emitScrollEvent(this, ScrollEventType.END_DRAG, f, f2);
            this.mDragging = false;
            handlePostTouchScrolling(Math.round(f), Math.round(f2));
        }
        return super.onTouchEvent(motionEvent);
    }

    public final int predictFinalScrollPosition(int i) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.mDecelerationRate);
        overScroller.fling(getScrollX(), getScrollY(), 0, i, 0, 0, 0, getMaxScrollY(), 0, ((getHeight() - getPaddingBottom()) - getPaddingTop()) / 2);
        return overScroller.getFinalY();
    }

    @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (view2 != null) {
            Rect rect = new Rect();
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int computeScrollDeltaToGetChildRectOnScreen = computeScrollDeltaToGetChildRectOnScreen(rect);
            if (computeScrollDeltaToGetChildRectOnScreen != 0) {
                scrollBy(0, computeScrollDeltaToGetChildRectOnScreen);
            }
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mReactBackgroundManager.setBackgroundColor(i);
    }

    public void setBorderRadius(float f) {
        this.mReactBackgroundManager.setBorderRadius(f);
    }

    public void setBorderStyle(String str) {
        this.mReactBackgroundManager.getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setDecelerationRate(float f) {
        this.mDecelerationRate = f;
        OverScroller overScroller = this.mScroller;
        if (overScroller != null) {
            overScroller.setFriction(1.0f - f);
        }
    }

    public void setEndFillColor(int i) {
        if (i != this.mEndFillColor) {
            this.mEndFillColor = i;
            this.mEndBackground = new ColorDrawable(this.mEndFillColor);
        }
    }

    public void setOverflow(String str) {
        this.mOverflow = str;
        invalidate();
    }

    public void setPagingEnabled(boolean z) {
        this.mPagingEnabled = z;
    }

    public void setRemoveClippedSubviews(boolean z) {
        if (z && this.mClippingRect == null) {
            this.mClippingRect = new Rect();
        }
        this.mRemoveClippedSubviews = z;
        updateClippingRect();
    }

    public void setScrollEnabled(boolean z) {
        this.mScrollEnabled = z;
    }

    public void setScrollPerfTag(String str) {
        this.mScrollPerfTag = str;
    }

    public void setSendMomentumEvents(boolean z) {
        this.mSendMomentumEvents = z;
    }

    public void setSnapInterval(int i) {
        this.mSnapInterval = i;
    }

    public void setSnapOffsets(List<Integer> list) {
        this.mSnapOffsets = list;
    }

    public void setSnapToEnd(boolean z) {
        this.mSnapToEnd = z;
    }

    public void setSnapToStart(boolean z) {
        this.mSnapToStart = z;
    }

    @Override // com.facebook.react.uimanager.ReactClippingViewGroup
    public void updateClippingRect() {
        if (this.mRemoveClippedSubviews) {
            R$dimen.assertNotNull(this.mClippingRect);
            ReactClippingViewGroupHelper.calculateClippingRect(this, this.mClippingRect);
            View childAt = getChildAt(0);
            if (childAt instanceof ReactClippingViewGroup) {
                ((ReactClippingViewGroup) childAt).updateClippingRect();
            }
        }
    }
}
