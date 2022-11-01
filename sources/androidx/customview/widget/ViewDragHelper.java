package androidx.customview.widget;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.Arrays;
import java.util.Objects;
/* loaded from: classes.dex */
public class ViewDragHelper {
    public static final Interpolator sInterpolator = new Interpolator() { // from class: androidx.customview.widget.ViewDragHelper.1
        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * f2 * f2 * f2 * f2) + 1.0f;
        }
    };
    public final Callback mCallback;
    public View mCapturedView;
    public int mDragState;
    public int[] mEdgeDragsInProgress;
    public int[] mEdgeDragsLocked;
    public int mEdgeSize;
    public int[] mInitialEdgesTouched;
    public float[] mInitialMotionX;
    public float[] mInitialMotionY;
    public float[] mLastMotionX;
    public float[] mLastMotionY;
    public float mMaxVelocity;
    public float mMinVelocity;
    public final ViewGroup mParentView;
    public int mPointersDown;
    public boolean mReleaseInProgress;
    public OverScroller mScroller;
    public int mTouchSlop;
    public int mTrackingEdges;
    public VelocityTracker mVelocityTracker;
    public int mActivePointerId = -1;
    public final Runnable mSetIdleRunnable = new Runnable() { // from class: androidx.customview.widget.ViewDragHelper.2
        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper.this.setDragState(0);
        }
    };

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract int clampViewPositionHorizontal(View view, int i, int i2);

        public abstract int clampViewPositionVertical(View view, int i, int i2);

        public int getViewHorizontalDragRange(View view) {
            return 0;
        }

        public int getViewVerticalDragRange(View view) {
            return 0;
        }

        public void onEdgeDragStarted(int i, int i2) {
        }

        public void onEdgeTouched(int i, int i2) {
        }

        public void onViewCaptured(View view, int i) {
        }

        public abstract void onViewDragStateChanged(int i);

        public abstract void onViewPositionChanged(View view, int i, int i2, int i3, int i4);

        public abstract void onViewReleased(View view, float f, float f2);

        public abstract boolean tryCaptureView(View view, int i);
    }

    public ViewDragHelper(Context context, ViewGroup viewGroup, Callback callback) {
        if (callback != null) {
            this.mParentView = viewGroup;
            this.mCallback = callback;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.mEdgeSize = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
            this.mMaxVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
            this.mMinVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
            this.mScroller = new OverScroller(context, sInterpolator);
            return;
        }
        throw new IllegalArgumentException("Callback may not be null");
    }

    public static ViewDragHelper create(ViewGroup viewGroup, float f, Callback callback) {
        ViewDragHelper viewDragHelper = new ViewDragHelper(viewGroup.getContext(), viewGroup, callback);
        viewDragHelper.mTouchSlop = (int) ((1.0f / f) * viewDragHelper.mTouchSlop);
        return viewDragHelper;
    }

    public void cancel() {
        this.mActivePointerId = -1;
        float[] fArr = this.mInitialMotionX;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.mInitialMotionY, 0.0f);
            Arrays.fill(this.mLastMotionX, 0.0f);
            Arrays.fill(this.mLastMotionY, 0.0f);
            Arrays.fill(this.mInitialEdgesTouched, 0);
            Arrays.fill(this.mEdgeDragsInProgress, 0);
            Arrays.fill(this.mEdgeDragsLocked, 0);
            this.mPointersDown = 0;
        }
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }

    public void captureChildView(View view, int i) {
        if (view.getParent() == this.mParentView) {
            this.mCapturedView = view;
            this.mActivePointerId = i;
            this.mCallback.onViewCaptured(view, i);
            setDragState(1);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (");
        outline13.append(this.mParentView);
        outline13.append(")");
        throw new IllegalArgumentException(outline13.toString());
    }

    public final boolean checkNewEdgeDrag(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.mInitialEdgesTouched[i] & i2) != i2 || (this.mTrackingEdges & i2) == 0 || (this.mEdgeDragsLocked[i] & i2) == i2 || (this.mEdgeDragsInProgress[i] & i2) == i2) {
            return false;
        }
        int i3 = this.mTouchSlop;
        if (abs <= i3 && abs2 <= i3) {
            return false;
        }
        if (abs < abs2 * 0.5f) {
            Objects.requireNonNull(this.mCallback);
        }
        return (this.mEdgeDragsInProgress[i] & i2) == 0 && abs > ((float) this.mTouchSlop);
    }

    public final boolean checkTouchSlop(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z = this.mCallback.getViewHorizontalDragRange(view) > 0;
        boolean z2 = this.mCallback.getViewVerticalDragRange(view) > 0;
        if (!z || !z2) {
            return z ? Math.abs(f) > ((float) this.mTouchSlop) : z2 && Math.abs(f2) > ((float) this.mTouchSlop);
        }
        float f3 = f2 * f2;
        int i = this.mTouchSlop;
        return f3 + (f * f) > ((float) (i * i));
    }

    public final int clampMag(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        return abs > i3 ? i > 0 ? i3 : -i3 : i;
    }

    public final void clearMotionHistory(int i) {
        if (this.mInitialMotionX != null && isPointerDown(i)) {
            this.mInitialMotionX[i] = 0.0f;
            this.mInitialMotionY[i] = 0.0f;
            this.mLastMotionX[i] = 0.0f;
            this.mLastMotionY[i] = 0.0f;
            this.mInitialEdgesTouched[i] = 0;
            this.mEdgeDragsInProgress[i] = 0;
            this.mEdgeDragsLocked[i] = 0;
            this.mPointersDown = (~(1 << i)) & this.mPointersDown;
        }
    }

    public final int computeAxisDuration(int i, int i2, int i3) {
        int width;
        int i4;
        if (i == 0) {
            return 0;
        }
        float width2 = this.mParentView.getWidth() / 2;
        float sin = (((float) Math.sin((Math.min(1.0f, Math.abs(i) / width) - 0.5f) * 0.47123894f)) * width2) + width2;
        int abs = Math.abs(i2);
        if (abs > 0) {
            i4 = Math.round(Math.abs(sin / abs) * 1000.0f) * 4;
        } else {
            i4 = (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f);
        }
        return Math.min(i4, 600);
    }

    public boolean continueSettling(boolean z) {
        if (this.mDragState == 2) {
            boolean computeScrollOffset = this.mScroller.computeScrollOffset();
            int currX = this.mScroller.getCurrX();
            int currY = this.mScroller.getCurrY();
            int left = currX - this.mCapturedView.getLeft();
            int top = currY - this.mCapturedView.getTop();
            if (left != 0) {
                ViewCompat.offsetLeftAndRight(this.mCapturedView, left);
            }
            if (top != 0) {
                ViewCompat.offsetTopAndBottom(this.mCapturedView, top);
            }
            if (!(left == 0 && top == 0)) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, currX, currY, left, top);
            }
            if (computeScrollOffset && currX == this.mScroller.getFinalX() && currY == this.mScroller.getFinalY()) {
                this.mScroller.abortAnimation();
                computeScrollOffset = false;
            }
            if (!computeScrollOffset) {
                if (z) {
                    this.mParentView.post(this.mSetIdleRunnable);
                } else {
                    setDragState(0);
                }
            }
        }
        return this.mDragState == 2;
    }

    public final void dispatchViewReleased(float f, float f2) {
        this.mReleaseInProgress = true;
        this.mCallback.onViewReleased(this.mCapturedView, f, f2);
        this.mReleaseInProgress = false;
        if (this.mDragState == 1) {
            setDragState(0);
        }
    }

    public View findTopChildUnder(int i, int i2) {
        for (int childCount = this.mParentView.getChildCount() - 1; childCount >= 0; childCount--) {
            ViewGroup viewGroup = this.mParentView;
            Objects.requireNonNull(this.mCallback);
            View childAt = viewGroup.getChildAt(childCount);
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    public final boolean forceSettleCapturedViewAt(int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float f3;
        float f4;
        int left = this.mCapturedView.getLeft();
        int top = this.mCapturedView.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.mScroller.abortAnimation();
            setDragState(0);
            return false;
        }
        View view = this.mCapturedView;
        int clampMag = clampMag(i3, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int clampMag2 = clampMag(i4, (int) this.mMinVelocity, (int) this.mMaxVelocity);
        int abs = Math.abs(i5);
        int abs2 = Math.abs(i6);
        int abs3 = Math.abs(clampMag);
        int abs4 = Math.abs(clampMag2);
        int i7 = abs3 + abs4;
        int i8 = abs + abs2;
        if (clampMag != 0) {
            f = abs3;
            f2 = i7;
        } else {
            f = abs;
            f2 = i8;
        }
        float f5 = f / f2;
        if (clampMag2 != 0) {
            f4 = abs4;
            f3 = i7;
        } else {
            f4 = abs2;
            f3 = i8;
        }
        int computeAxisDuration = computeAxisDuration(i5, clampMag, this.mCallback.getViewHorizontalDragRange(view));
        this.mScroller.startScroll(left, top, i5, i6, (int) ((computeAxisDuration(i6, clampMag2, this.mCallback.getViewVerticalDragRange(view)) * (f4 / f3)) + (computeAxisDuration * f5)));
        setDragState(2);
        return true;
    }

    public boolean isPointerDown(int i) {
        return ((1 << i) & this.mPointersDown) != 0;
    }

    public final boolean isValidPointerForActionMove(int i) {
        if (isPointerDown(i)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    public void processTouchEvent(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            cancel();
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        int i2 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View findTopChildUnder = findTopChildUnder((int) x, (int) y);
            saveInitialMotion(x, y, pointerId);
            tryCaptureViewForDrag(findTopChildUnder, pointerId);
            int i3 = this.mInitialEdgesTouched[pointerId];
            int i4 = this.mTrackingEdges;
            if ((i3 & i4) != 0) {
                this.mCallback.onEdgeTouched(i3 & i4, pointerId);
            }
        } else if (actionMasked == 1) {
            if (this.mDragState == 1) {
                releaseViewForPointerUp();
            }
            cancel();
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                if (this.mDragState == 1) {
                    dispatchViewReleased(0.0f, 0.0f);
                }
                cancel();
            } else if (actionMasked == 5) {
                int pointerId2 = motionEvent.getPointerId(actionIndex);
                float x2 = motionEvent.getX(actionIndex);
                float y2 = motionEvent.getY(actionIndex);
                saveInitialMotion(x2, y2, pointerId2);
                if (this.mDragState == 0) {
                    tryCaptureViewForDrag(findTopChildUnder((int) x2, (int) y2), pointerId2);
                    int i5 = this.mInitialEdgesTouched[pointerId2];
                    int i6 = this.mTrackingEdges;
                    if ((i5 & i6) != 0) {
                        this.mCallback.onEdgeTouched(i5 & i6, pointerId2);
                        return;
                    }
                    return;
                }
                int i7 = (int) x2;
                int i8 = (int) y2;
                View view = this.mCapturedView;
                if (view != null && i7 >= view.getLeft() && i7 < view.getRight() && i8 >= view.getTop() && i8 < view.getBottom()) {
                    i2 = 1;
                }
                if (i2 != 0) {
                    tryCaptureViewForDrag(this.mCapturedView, pointerId2);
                }
            } else if (actionMasked == 6) {
                int pointerId3 = motionEvent.getPointerId(actionIndex);
                if (this.mDragState == 1 && pointerId3 == this.mActivePointerId) {
                    int pointerCount = motionEvent.getPointerCount();
                    while (true) {
                        if (i2 >= pointerCount) {
                            i = -1;
                            break;
                        }
                        int pointerId4 = motionEvent.getPointerId(i2);
                        if (pointerId4 != this.mActivePointerId) {
                            View findTopChildUnder2 = findTopChildUnder((int) motionEvent.getX(i2), (int) motionEvent.getY(i2));
                            View view2 = this.mCapturedView;
                            if (findTopChildUnder2 == view2 && tryCaptureViewForDrag(view2, pointerId4)) {
                                i = this.mActivePointerId;
                                break;
                            }
                        }
                        i2++;
                    }
                    if (i == -1) {
                        releaseViewForPointerUp();
                    }
                }
                clearMotionHistory(pointerId3);
            }
        } else if (this.mDragState != 1) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (i2 < pointerCount2) {
                int pointerId5 = motionEvent.getPointerId(i2);
                if (isValidPointerForActionMove(pointerId5)) {
                    float x3 = motionEvent.getX(i2);
                    float y3 = motionEvent.getY(i2);
                    float f = x3 - this.mInitialMotionX[pointerId5];
                    float f2 = y3 - this.mInitialMotionY[pointerId5];
                    reportNewEdgeDrags(f, f2, pointerId5);
                    if (this.mDragState != 1) {
                        View findTopChildUnder3 = findTopChildUnder((int) x3, (int) y3);
                        if (checkTouchSlop(findTopChildUnder3, f, f2) && tryCaptureViewForDrag(findTopChildUnder3, pointerId5)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i2++;
            }
            saveLastMotion(motionEvent);
        } else if (isValidPointerForActionMove(this.mActivePointerId)) {
            int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
            float x4 = motionEvent.getX(findPointerIndex);
            float y4 = motionEvent.getY(findPointerIndex);
            float[] fArr = this.mLastMotionX;
            int i9 = this.mActivePointerId;
            int i10 = (int) (x4 - fArr[i9]);
            int i11 = (int) (y4 - this.mLastMotionY[i9]);
            int left = this.mCapturedView.getLeft() + i10;
            int top = this.mCapturedView.getTop() + i11;
            int left2 = this.mCapturedView.getLeft();
            int top2 = this.mCapturedView.getTop();
            if (i10 != 0) {
                left = this.mCallback.clampViewPositionHorizontal(this.mCapturedView, left, i10);
                ViewCompat.offsetLeftAndRight(this.mCapturedView, left - left2);
            }
            if (i11 != 0) {
                top = this.mCallback.clampViewPositionVertical(this.mCapturedView, top, i11);
                ViewCompat.offsetTopAndBottom(this.mCapturedView, top - top2);
            }
            if (!(i10 == 0 && i11 == 0)) {
                this.mCallback.onViewPositionChanged(this.mCapturedView, left, top, left - left2, top - top2);
            }
            saveLastMotion(motionEvent);
        }
    }

    public final void releaseViewForPointerUp() {
        this.mVelocityTracker.computeCurrentVelocity(RNCWebViewManager.COMMAND_CLEAR_FORM_DATA, this.mMaxVelocity);
        dispatchViewReleased(clampMag(this.mVelocityTracker.getXVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity), clampMag(this.mVelocityTracker.getYVelocity(this.mActivePointerId), this.mMinVelocity, this.mMaxVelocity));
    }

    public final void reportNewEdgeDrags(float f, float f2, int i) {
        int i2 = 1;
        if (!checkNewEdgeDrag(f, f2, i, 1)) {
            i2 = 0;
        }
        if (checkNewEdgeDrag(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (checkNewEdgeDrag(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (checkNewEdgeDrag(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.mEdgeDragsInProgress;
            iArr[i] = iArr[i] | i2;
            this.mCallback.onEdgeDragStarted(i2, i);
        }
    }

    public final void saveInitialMotion(float f, float f2, int i) {
        float[] fArr = this.mInitialMotionX;
        int i2 = 0;
        if (fArr == null || fArr.length <= i) {
            int i3 = i + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.mInitialMotionY;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.mLastMotionX;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.mLastMotionY;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.mInitialEdgesTouched;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.mEdgeDragsInProgress;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.mEdgeDragsLocked;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.mInitialMotionX = fArr2;
            this.mInitialMotionY = fArr3;
            this.mLastMotionX = fArr4;
            this.mLastMotionY = fArr5;
            this.mInitialEdgesTouched = iArr;
            this.mEdgeDragsInProgress = iArr2;
            this.mEdgeDragsLocked = iArr3;
        }
        float[] fArr9 = this.mInitialMotionX;
        this.mLastMotionX[i] = f;
        fArr9[i] = f;
        float[] fArr10 = this.mInitialMotionY;
        this.mLastMotionY[i] = f2;
        fArr10[i] = f2;
        int[] iArr7 = this.mInitialEdgesTouched;
        int i4 = (int) f;
        int i5 = (int) f2;
        if (i4 < this.mParentView.getLeft() + this.mEdgeSize) {
            i2 = 1;
        }
        if (i5 < this.mParentView.getTop() + this.mEdgeSize) {
            i2 |= 4;
        }
        if (i4 > this.mParentView.getRight() - this.mEdgeSize) {
            i2 |= 2;
        }
        if (i5 > this.mParentView.getBottom() - this.mEdgeSize) {
            i2 |= 8;
        }
        iArr7[i] = i2;
        this.mPointersDown |= 1 << i;
    }

    public final void saveLastMotion(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (isValidPointerForActionMove(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.mLastMotionX[pointerId] = x;
                this.mLastMotionY[pointerId] = y;
            }
        }
    }

    public void setDragState(int i) {
        this.mParentView.removeCallbacks(this.mSetIdleRunnable);
        if (this.mDragState != i) {
            this.mDragState = i;
            this.mCallback.onViewDragStateChanged(i);
            if (this.mDragState == 0) {
                this.mCapturedView = null;
            }
        }
    }

    public boolean settleCapturedViewAt(int i, int i2) {
        if (this.mReleaseInProgress) {
            return forceSettleCapturedViewAt(i, i2, (int) this.mVelocityTracker.getXVelocity(this.mActivePointerId), (int) this.mVelocityTracker.getYVelocity(this.mActivePointerId));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARN: Code restructure failed: missing block: B:46:0x00de, code lost:
        if (r12 != r11) goto L_0x00e7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean shouldInterceptTouchEvent(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instructions count: 313
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.customview.widget.ViewDragHelper.shouldInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean smoothSlideViewTo(View view, int i, int i2) {
        this.mCapturedView = view;
        this.mActivePointerId = -1;
        boolean forceSettleCapturedViewAt = forceSettleCapturedViewAt(i, i2, 0, 0);
        if (!forceSettleCapturedViewAt && this.mDragState == 0 && this.mCapturedView != null) {
            this.mCapturedView = null;
        }
        return forceSettleCapturedViewAt;
    }

    public boolean tryCaptureViewForDrag(View view, int i) {
        if (view == this.mCapturedView && this.mActivePointerId == i) {
            return true;
        }
        if (view == null || !this.mCallback.tryCaptureView(view, i)) {
            return false;
        }
        this.mActivePointerId = i;
        captureChildView(view, i);
        return true;
    }

    public final float clampMag(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        return abs > f3 ? f > 0.0f ? f3 : -f3 : f;
    }
}
