package com.facebook.react.views.scroll;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.OverScroller;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.R$dimen;
import com.facebook.react.R$style;
import com.facebook.react.uimanager.ReactClippingViewGroup;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class ReactHorizontalScrollView extends HorizontalScrollView implements ReactClippingViewGroup {
    public static Field sScrollerField = null;
    public static boolean sTriedToGetScrollerField = false;
    public boolean mActivelyScrolling;
    public Rect mClippingRect;
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
    public boolean mDisableIntervalMomentum = false;
    public int mSnapInterval = 0;
    public float mDecelerationRate = 0.985f;
    public boolean mSnapToStart = true;
    public boolean mSnapToEnd = true;
    public boolean mPagedArrowScrolling = false;
    public final Rect mTempRect = new Rect();
    public ReactViewBackgroundManager mReactBackgroundManager = new ReactViewBackgroundManager(this);
    public final OverScroller mScroller = getOverScrollerFromParent();

    public ReactHorizontalScrollView(Context context, FpsListener fpsListener) {
        super(context);
        this.mFpsListener = null;
        this.mFpsListener = fpsListener;
    }

    private OverScroller getOverScrollerFromParent() {
        if (!sTriedToGetScrollerField) {
            sTriedToGetScrollerField = true;
            try {
                Field declaredField = HorizontalScrollView.class.getDeclaredField("mScroller");
                sScrollerField = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.w("ReactNative", "Failed to get mScroller field for HorizontalScrollView! This app will exhibit the bounce-back scrolling bug :(");
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
                    Log.w("ReactNative", "Failed to cast mScroller field in HorizontalScrollView (probably due to OEM changes to AOSP)! This app will exhibit the bounce-back scrolling bug :(");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to get mScroller from HorizontalScrollView!", e);
            }
        }
        return overScroller;
    }

    private int getSnapInterval() {
        int i = this.mSnapInterval;
        return i != 0 ? i : getWidth();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (!this.mPagingEnabled || this.mPagedArrowScrolling) {
            super.addFocusables(arrayList, i, i2);
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        super.addFocusables(arrayList2, i, i2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            View view = (View) it.next();
            boolean z = false;
            if (!(getScrollDelta(view) == 0)) {
                int scrollDelta = getScrollDelta(view);
                view.getDrawingRect(this.mTempRect);
                if (scrollDelta != 0 && Math.abs(scrollDelta) < this.mTempRect.width()) {
                    z = true;
                }
                if (!z && !view.isFocused()) {
                }
            }
            arrayList.add(view);
        }
    }

    @Override // android.widget.HorizontalScrollView
    public boolean arrowScroll(int i) {
        if (!this.mPagingEnabled) {
            return super.arrowScroll(i);
        }
        boolean z = true;
        this.mPagedArrowScrolling = true;
        if (getChildCount() > 0) {
            View findNextFocus = FocusFinder.getInstance().findNextFocus(this, findFocus(), i);
            View childAt = getChildAt(0);
            if (childAt == null || findNextFocus == null || findNextFocus.getParent() != childAt) {
                smoothScrollToNextPage(i);
            } else {
                if (!(getScrollDelta(findNextFocus) == 0)) {
                    int scrollDelta = getScrollDelta(findNextFocus);
                    findNextFocus.getDrawingRect(this.mTempRect);
                    if (!(scrollDelta != 0 && Math.abs(scrollDelta) < this.mTempRect.width() / 2)) {
                        smoothScrollToNextPage(i);
                    }
                }
                findNextFocus.requestFocus();
            }
        } else {
            z = false;
        }
        this.mPagedArrowScrolling = false;
        return z;
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void draw(Canvas canvas) {
        if (this.mEndFillColor != 0) {
            View childAt = getChildAt(0);
            if (!(this.mEndBackground == null || childAt == null || childAt.getRight() >= getWidth())) {
                this.mEndBackground.setBounds(childAt.getRight(), 0, getWidth(), getHeight());
                this.mEndBackground.draw(canvas);
            }
        }
        super.draw(canvas);
    }

    @Override // android.widget.HorizontalScrollView
    public boolean executeKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.mScrollEnabled || (keyCode != 21 && keyCode != 22)) {
            return super.executeKeyEvent(keyEvent);
        }
        return false;
    }

    public void flashScrollIndicators() {
        awakenScrollBars();
    }

    @Override // android.widget.HorizontalScrollView
    public void fling(int i) {
        int signum = (int) (Math.signum(this.mOnScrollDispatchHelper.mXFlingVelocity) * Math.abs(i));
        if (this.mPagingEnabled) {
            flingAndSnap(signum);
        } else if (this.mScroller != null) {
            int width = getWidth();
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            this.mScroller.fling(getScrollX(), getScrollY(), signum, 0, 0, Integer.MAX_VALUE, 0, 0, ((width - getPaddingStart()) - getPaddingEnd()) / 2, 0);
            postInvalidateOnAnimation();
        } else {
            super.fling(signum);
        }
        handlePostTouchScrolling(signum, 0);
    }

    public final void flingAndSnap(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = i;
        if (getChildCount() > 0) {
            if (this.mSnapInterval == 0 && this.mSnapOffsets == null) {
                double snapInterval = getSnapInterval();
                double scrollX = getScrollX();
                double predictFinalScrollPosition = predictFinalScrollPosition(i);
                double d = scrollX / snapInterval;
                int floor = (int) Math.floor(d);
                int ceil = (int) Math.ceil(d);
                int round = (int) Math.round(d);
                int round2 = (int) Math.round(predictFinalScrollPosition / snapInterval);
                if (i6 > 0 && ceil == floor) {
                    ceil++;
                } else if (i6 < 0 && floor == ceil) {
                    floor--;
                }
                if (i6 > 0 && round < ceil && round2 > floor) {
                    round = ceil;
                } else if (i6 < 0 && round > floor && round2 < ceil) {
                    round = floor;
                }
                double d2 = round * snapInterval;
                if (d2 != scrollX) {
                    this.mActivelyScrolling = true;
                    smoothScrollTo((int) d2, getScrollY());
                    return;
                }
                return;
            }
            int max = Math.max(0, computeHorizontalScrollRange() - getWidth());
            int predictFinalScrollPosition2 = predictFinalScrollPosition(i);
            if (this.mDisableIntervalMomentum) {
                predictFinalScrollPosition2 = getScrollX();
            }
            int width = getWidth();
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            int paddingStart = (width - getPaddingStart()) - getPaddingEnd();
            Locale locale = Locale.getDefault();
            Locale locale2 = TextUtilsCompat.ROOT;
            boolean z = TextUtils.getLayoutDirectionFromLocale(locale) == 1;
            if (z) {
                predictFinalScrollPosition2 = max - predictFinalScrollPosition2;
                i6 = -i6;
            }
            List<Integer> list = this.mSnapOffsets;
            if (list != null) {
                i5 = list.get(0).intValue();
                List<Integer> list2 = this.mSnapOffsets;
                i4 = list2.get(list2.size() - 1).intValue();
                i2 = max;
                i3 = 0;
                for (int i7 = 0; i7 < this.mSnapOffsets.size(); i7++) {
                    int intValue = this.mSnapOffsets.get(i7).intValue();
                    if (intValue <= predictFinalScrollPosition2 && predictFinalScrollPosition2 - intValue < predictFinalScrollPosition2 - i3) {
                        i3 = intValue;
                    }
                    if (intValue >= predictFinalScrollPosition2 && intValue - predictFinalScrollPosition2 < i2 - predictFinalScrollPosition2) {
                        i2 = intValue;
                    }
                }
            } else {
                double snapInterval2 = getSnapInterval();
                double d3 = predictFinalScrollPosition2 / snapInterval2;
                i3 = (int) (Math.floor(d3) * snapInterval2);
                i2 = Math.min((int) (Math.ceil(d3) * snapInterval2), max);
                i4 = max;
                i5 = 0;
            }
            int i8 = predictFinalScrollPosition2 - i3;
            int i9 = i2 - predictFinalScrollPosition2;
            predictFinalScrollPosition2 = i8 < i9 ? i3 : i2;
            int scrollX2 = getScrollX();
            if (z) {
                scrollX2 = max - scrollX2;
            }
            if (this.mSnapToEnd || predictFinalScrollPosition2 < i4) {
                if (this.mSnapToStart || predictFinalScrollPosition2 > i5) {
                    if (i6 > 0) {
                        i6 += (int) (i9 * 10.0d);
                        predictFinalScrollPosition2 = i2;
                    } else if (i6 < 0) {
                        i6 -= (int) (i8 * 10.0d);
                        predictFinalScrollPosition2 = i3;
                    }
                } else if (scrollX2 > i5) {
                    predictFinalScrollPosition2 = i5;
                }
            } else if (scrollX2 < i4) {
                predictFinalScrollPosition2 = i4;
            }
            int min = Math.min(Math.max(0, predictFinalScrollPosition2), max);
            if (z) {
                min = max - min;
                i6 = -i6;
            }
            OverScroller overScroller = this.mScroller;
            if (overScroller != null) {
                this.mActivelyScrolling = true;
                int scrollX3 = getScrollX();
                int scrollY = getScrollY();
                if (i6 == 0) {
                    i6 = min - getScrollX();
                }
                overScroller.fling(scrollX3, scrollY, i6, 0, min, min, 0, 0, (min == 0 || min == max) ? paddingStart / 2 : 0, 0);
                postInvalidateOnAnimation();
                return;
            }
            smoothScrollTo(min, getScrollY());
        }
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

    public final int getScrollDelta(View view) {
        view.getDrawingRect(this.mTempRect);
        offsetDescendantRectToMyCoords(view, this.mTempRect);
        return computeScrollDeltaToGetChildRectOnScreen(this.mTempRect);
    }

    public final void handlePostTouchScrolling(int i, int i2) {
        if ((this.mSendMomentumEvents || this.mPagingEnabled || isScrollPerfLoggingEnabled()) && this.mPostTouchRunnable == null) {
            if (this.mSendMomentumEvents) {
                R$style.emitScrollEvent(this, ScrollEventType.MOMENTUM_BEGIN, i, i2);
            }
            this.mActivelyScrolling = false;
            Runnable runnable = new Runnable() { // from class: com.facebook.react.views.scroll.ReactHorizontalScrollView.1
                public boolean mSnappingToPage = false;

                @Override // java.lang.Runnable
                public void run() {
                    ReactHorizontalScrollView reactHorizontalScrollView = ReactHorizontalScrollView.this;
                    if (reactHorizontalScrollView.mActivelyScrolling) {
                        reactHorizontalScrollView.mActivelyScrolling = false;
                        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                        reactHorizontalScrollView.postOnAnimationDelayed(this, 20L);
                    } else if (!reactHorizontalScrollView.mPagingEnabled || this.mSnappingToPage) {
                        if (reactHorizontalScrollView.mSendMomentumEvents) {
                            R$style.emitScrollEvent(reactHorizontalScrollView, ScrollEventType.MOMENTUM_END, 0.0f, 0.0f);
                        }
                        ReactHorizontalScrollView reactHorizontalScrollView2 = ReactHorizontalScrollView.this;
                        reactHorizontalScrollView2.mPostTouchRunnable = null;
                        if (reactHorizontalScrollView2.isScrollPerfLoggingEnabled()) {
                            R$dimen.assertNotNull(reactHorizontalScrollView2.mFpsListener);
                            R$dimen.assertNotNull(reactHorizontalScrollView2.mScrollPerfTag);
                            reactHorizontalScrollView2.mFpsListener.disable(reactHorizontalScrollView2.mScrollPerfTag);
                        }
                    } else {
                        this.mSnappingToPage = true;
                        reactHorizontalScrollView.flingAndSnap(0);
                        ReactHorizontalScrollView reactHorizontalScrollView3 = ReactHorizontalScrollView.this;
                        AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                        reactHorizontalScrollView3.postOnAnimationDelayed(this, 20L);
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

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        getDrawingRect(this.mRect);
        String str = this.mOverflow;
        str.hashCode();
        if (!str.equals("visible")) {
            canvas.clipRect(this.mRect);
        }
        super.onDraw(canvas);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mScrollEnabled) {
            return false;
        }
        try {
            if (super.onInterceptTouchEvent(motionEvent)) {
                R$style.notifyNativeGestureStarted(this, motionEvent);
                R$style.emitScrollEvent(this, ScrollEventType.BEGIN_DRAG, 0.0f, 0.0f);
                this.mDragging = true;
                if (isScrollPerfLoggingEnabled()) {
                    R$dimen.assertNotNull(this.mFpsListener);
                    R$dimen.assertNotNull(this.mScrollPerfTag);
                    this.mFpsListener.enable(this.mScrollPerfTag);
                }
                return true;
            }
        } catch (IllegalArgumentException e) {
            Log.w("ReactNative", "Error intercepting touch event.", e);
        }
        return false;
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        scrollTo(getScrollX(), getScrollY());
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        R$style.assertExplicitMeasureSpec(i, i2);
        setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        int computeHorizontalScrollRange;
        OverScroller overScroller = this.mScroller;
        if (overScroller != null && !overScroller.isFinished() && this.mScroller.getCurrX() != this.mScroller.getFinalX() && i >= (computeHorizontalScrollRange = computeHorizontalScrollRange() - getWidth())) {
            this.mScroller.abortAnimation();
            i = computeHorizontalScrollRange;
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

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mRemoveClippedSubviews) {
            updateClippingRect();
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
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

    @Override // android.widget.HorizontalScrollView
    public boolean pageScroll(int i) {
        boolean pageScroll = super.pageScroll(i);
        if (this.mPagingEnabled && pageScroll) {
            handlePostTouchScrolling(0, 0);
        }
        return pageScroll;
    }

    public final int predictFinalScrollPosition(int i) {
        OverScroller overScroller = new OverScroller(getContext());
        overScroller.setFriction(1.0f - this.mDecelerationRate);
        int max = Math.max(0, computeHorizontalScrollRange() - getWidth());
        int width = getWidth();
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        overScroller.fling(getScrollX(), getScrollY(), i, 0, 0, max, 0, 0, ((width - getPaddingStart()) - getPaddingEnd()) / 2, 0);
        return overScroller.getFinalX();
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        int scrollDelta;
        if (!(view2 == null || this.mPagingEnabled || (scrollDelta = getScrollDelta(view2)) == 0)) {
            scrollBy(scrollDelta, 0);
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

    public void setDisableIntervalMomentum(boolean z) {
        this.mDisableIntervalMomentum = z;
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

    public final void smoothScrollToNextPage(int i) {
        int width = getWidth();
        int scrollX = getScrollX();
        int i2 = scrollX / width;
        if (scrollX % width != 0) {
            i2++;
        }
        int i3 = i == 17 ? i2 - 1 : i2 + 1;
        if (i3 < 0) {
            i3 = 0;
        }
        smoothScrollTo(i3 * width, getScrollY());
        handlePostTouchScrolling(0, 0);
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
