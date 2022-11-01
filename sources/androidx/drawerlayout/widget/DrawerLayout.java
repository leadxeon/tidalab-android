package androidx.drawerlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class DrawerLayout extends ViewGroup {
    public Rect mChildHitRect;
    public Matrix mChildInvertedMatrix;
    public boolean mChildrenCanceledTouch;
    public boolean mDrawStatusBarBackground;
    public float mDrawerElevation;
    public int mDrawerState;
    public boolean mInLayout;
    public float mInitialMotionX;
    public float mInitialMotionY;
    public Object mLastInsets;
    public final ViewDragCallback mLeftCallback;
    public final ViewDragHelper mLeftDragger;
    public DrawerListener mListener;
    public List<DrawerListener> mListeners;
    public int mMinDrawerMargin;
    public final ArrayList<View> mNonDrawerViews;
    public final ViewDragCallback mRightCallback;
    public final ViewDragHelper mRightDragger;
    public float mScrimOpacity;
    public Drawable mStatusBarBackground;
    public static final int[] THEME_ATTRS = {16843828};
    public static final int[] LAYOUT_ATTRS = {16842931};
    public final ChildAccessibilityDelegate mChildAccessibilityDelegate = new ChildAccessibilityDelegate();
    public int mScrimColor = -1728053248;
    public Paint mScrimPaint = new Paint();
    public boolean mFirstLayout = true;
    public int mLockModeLeft = 3;
    public int mLockModeRight = 3;
    public int mLockModeStart = 3;
    public int mLockModeEnd = 3;

    /* loaded from: classes.dex */
    public class AccessibilityDelegate extends AccessibilityDelegateCompat {
        public final Rect mTmpRect = new Rect();

        public AccessibilityDelegate() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            if (accessibilityEvent.getEventType() != 32) {
                return this.mOriginalDelegate.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
            }
            accessibilityEvent.getText();
            View findVisibleDrawer = DrawerLayout.this.findVisibleDrawer();
            if (findVisibleDrawer == null) {
                return true;
            }
            int drawerViewAbsoluteGravity = DrawerLayout.this.getDrawerViewAbsoluteGravity(findVisibleDrawer);
            DrawerLayout drawerLayout = DrawerLayout.this;
            Objects.requireNonNull(drawerLayout);
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            Gravity.getAbsoluteGravity(drawerViewAbsoluteGravity, drawerLayout.getLayoutDirection());
            return true;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.mOriginalDelegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
            accessibilityEvent.setClassName(DrawerLayout.class.getName());
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            int[] iArr = DrawerLayout.THEME_ATTRS;
            this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
            accessibilityNodeInfoCompat.mInfo.setClassName(DrawerLayout.class.getName());
            accessibilityNodeInfoCompat.mInfo.setFocusable(false);
            accessibilityNodeInfoCompat.mInfo.setFocused(false);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
            accessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            int[] iArr = DrawerLayout.THEME_ATTRS;
            return this.mOriginalDelegate.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }
    }

    /* loaded from: classes.dex */
    public static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            this.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.mInfo);
            if (!DrawerLayout.includeChildForAccessibility(view)) {
                accessibilityNodeInfoCompat.setParent(null);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface DrawerListener {
        void onDrawerClosed(View view);

        void onDrawerOpened(View view);

        void onDrawerSlide(View view, float f);

        void onDrawerStateChanged(int i);
    }

    /* loaded from: classes.dex */
    public class ViewDragCallback extends ViewDragHelper.Callback {
        public final int mAbsGravity;
        public ViewDragHelper mDragger;
        public final Runnable mPeekRunnable = new Runnable() { // from class: androidx.drawerlayout.widget.DrawerLayout.ViewDragCallback.1
            @Override // java.lang.Runnable
            public void run() {
                int i;
                View view;
                ViewDragCallback viewDragCallback = ViewDragCallback.this;
                int i2 = viewDragCallback.mDragger.mEdgeSize;
                boolean z = viewDragCallback.mAbsGravity == 3;
                if (z) {
                    view = DrawerLayout.this.findDrawerWithGravity(3);
                    i = (view != null ? -view.getWidth() : 0) + i2;
                } else {
                    view = DrawerLayout.this.findDrawerWithGravity(5);
                    i = DrawerLayout.this.getWidth() - i2;
                }
                if (view == null) {
                    return;
                }
                if (((z && view.getLeft() < i) || (!z && view.getLeft() > i)) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                    viewDragCallback.mDragger.smoothSlideViewTo(view, i, view.getTop());
                    ((LayoutParams) view.getLayoutParams()).isPeeking = true;
                    DrawerLayout.this.invalidate();
                    viewDragCallback.closeOtherDrawer();
                    DrawerLayout drawerLayout = DrawerLayout.this;
                    if (!drawerLayout.mChildrenCanceledTouch) {
                        long uptimeMillis = SystemClock.uptimeMillis();
                        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                        int childCount = drawerLayout.getChildCount();
                        for (int i3 = 0; i3 < childCount; i3++) {
                            drawerLayout.getChildAt(i3).dispatchTouchEvent(obtain);
                        }
                        obtain.recycle();
                        drawerLayout.mChildrenCanceledTouch = true;
                    }
                }
            }
        };

        public ViewDragCallback(int i) {
            this.mAbsGravity = i;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                return Math.max(-view.getWidth(), Math.min(i, 0));
            }
            int width = DrawerLayout.this.getWidth();
            return Math.max(width - view.getWidth(), Math.min(i, width));
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        public final void closeOtherDrawer() {
            int i = 3;
            if (this.mAbsGravity == 3) {
                i = 5;
            }
            View findDrawerWithGravity = DrawerLayout.this.findDrawerWithGravity(i);
            if (findDrawerWithGravity != null) {
                DrawerLayout.this.closeDrawer(findDrawerWithGravity, true);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            if (DrawerLayout.this.isDrawerView(view)) {
                return view.getWidth();
            }
            return 0;
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onEdgeDragStarted(int i, int i2) {
            View view;
            if ((i & 1) == 1) {
                view = DrawerLayout.this.findDrawerWithGravity(3);
            } else {
                view = DrawerLayout.this.findDrawerWithGravity(5);
            }
            if (view != null && DrawerLayout.this.getDrawerLockMode(view) == 0) {
                this.mDragger.captureChildView(view, i2);
            }
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onEdgeTouched(int i, int i2) {
            DrawerLayout.this.postDelayed(this.mPeekRunnable, 160L);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewCaptured(View view, int i) {
            ((LayoutParams) view.getLayoutParams()).isPeeking = false;
            closeOtherDrawer();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            DrawerLayout.this.updateDrawerState(i, this.mDragger.mCapturedView);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            int width = view.getWidth();
            float width2 = (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3) ? i + width : DrawerLayout.this.getWidth() - i) / width;
            DrawerLayout.this.setDrawerViewOffset(view, width2);
            view.setVisibility(width2 == 0.0f ? 4 : 0);
            DrawerLayout.this.invalidate();
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            int i;
            Objects.requireNonNull(DrawerLayout.this);
            float f3 = ((LayoutParams) view.getLayoutParams()).onScreen;
            int width = view.getWidth();
            if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, 3)) {
                int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                i = (i2 > 0 || (i2 == 0 && f3 > 0.5f)) ? 0 : -width;
            } else {
                i = DrawerLayout.this.getWidth();
                if (f < 0.0f || (f == 0.0f && f3 > 0.5f)) {
                    i -= width;
                }
            }
            this.mDragger.settleCapturedViewAt(i, view.getTop());
            DrawerLayout.this.invalidate();
        }

        public void removeCallbacks() {
            DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
        }

        @Override // androidx.customview.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            return DrawerLayout.this.isDrawerView(view) && DrawerLayout.this.checkDrawerViewAbsoluteGravity(view, this.mAbsGravity) && DrawerLayout.this.getDrawerLockMode(view) == 0;
        }
    }

    public DrawerLayout(Context context) {
        super(context, null, 0);
        setDescendantFocusability(262144);
        float f = getResources().getDisplayMetrics().density;
        this.mMinDrawerMargin = (int) ((64.0f * f) + 0.5f);
        float f2 = 400.0f * f;
        ViewDragCallback viewDragCallback = new ViewDragCallback(3);
        this.mLeftCallback = viewDragCallback;
        ViewDragCallback viewDragCallback2 = new ViewDragCallback(5);
        this.mRightCallback = viewDragCallback2;
        ViewDragHelper create = ViewDragHelper.create(this, 1.0f, viewDragCallback);
        this.mLeftDragger = create;
        create.mTrackingEdges = 1;
        create.mMinVelocity = f2;
        viewDragCallback.mDragger = create;
        ViewDragHelper create2 = ViewDragHelper.create(this, 1.0f, viewDragCallback2);
        this.mRightDragger = create2;
        create2.mTrackingEdges = 2;
        create2.mMinVelocity = f2;
        viewDragCallback2.mDragger = create2;
        setFocusableInTouchMode(true);
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setImportantForAccessibility(1);
        ViewCompat.setAccessibilityDelegate(this, new AccessibilityDelegate());
        setMotionEventSplittingEnabled(false);
        if (getFitsSystemWindows()) {
            setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener(this) { // from class: androidx.drawerlayout.widget.DrawerLayout.1
                @Override // android.view.View.OnApplyWindowInsetsListener
                public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                    DrawerLayout drawerLayout = (DrawerLayout) view;
                    boolean z = true;
                    boolean z2 = windowInsets.getSystemWindowInsetTop() > 0;
                    drawerLayout.mLastInsets = windowInsets;
                    drawerLayout.mDrawStatusBarBackground = z2;
                    if (z2 || drawerLayout.getBackground() != null) {
                        z = false;
                    }
                    drawerLayout.setWillNotDraw(z);
                    drawerLayout.requestLayout();
                    return windowInsets.consumeSystemWindowInsets();
                }
            });
            setSystemUiVisibility(1280);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(THEME_ATTRS);
            try {
                this.mStatusBarBackground = obtainStyledAttributes.getDrawable(0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.mDrawerElevation = f * 10.0f;
        this.mNonDrawerViews = new ArrayList<>();
    }

    public static String gravityToString(int i) {
        return (i & 3) == 3 ? "LEFT" : (i & 5) == 5 ? "RIGHT" : Integer.toHexString(i);
    }

    public static boolean includeChildForAccessibility(View view) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        return (view.getImportantForAccessibility() == 4 || view.getImportantForAccessibility() == 2) ? false : true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i, int i2) {
        if (getDescendantFocusability() != 393216) {
            int childCount = getChildCount();
            boolean z = false;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (!isDrawerView(childAt)) {
                    this.mNonDrawerViews.add(childAt);
                } else if (!isDrawerView(childAt)) {
                    throw new IllegalArgumentException("View " + childAt + " is not a drawer");
                } else if ((((LayoutParams) childAt.getLayoutParams()).openState & 1) == 1) {
                    childAt.addFocusables(arrayList, i, i2);
                    z = true;
                }
            }
            if (!z) {
                int size = this.mNonDrawerViews.size();
                for (int i4 = 0; i4 < size; i4++) {
                    View view = this.mNonDrawerViews.get(i4);
                    if (view.getVisibility() == 0) {
                        view.addFocusables(arrayList, i, i2);
                    }
                }
            }
            this.mNonDrawerViews.clear();
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (findOpenDrawer() != null || isDrawerView(view)) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            view.setImportantForAccessibility(4);
            return;
        }
        AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
        view.setImportantForAccessibility(1);
    }

    public boolean checkDrawerViewAbsoluteGravity(View view, int i) {
        return (getDrawerViewAbsoluteGravity(view) & i) == i;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public void closeDrawer(View view, boolean z) {
        if (isDrawerView(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.mFirstLayout) {
                layoutParams.onScreen = 0.0f;
                layoutParams.openState = 0;
            } else if (z) {
                layoutParams.openState |= 4;
                if (checkDrawerViewAbsoluteGravity(view, 3)) {
                    this.mLeftDragger.smoothSlideViewTo(view, -view.getWidth(), view.getTop());
                } else {
                    this.mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
                }
            } else {
                moveDrawerToOffset(view, 0.0f);
                int i = layoutParams.gravity;
                updateDrawerState(0, view);
                view.setVisibility(4);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    public void closeDrawers(boolean z) {
        boolean z2;
        int childCount = getChildCount();
        boolean z3 = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (isDrawerView(childAt) && (!z || layoutParams.isPeeking)) {
                int width = childAt.getWidth();
                if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                    z2 = this.mLeftDragger.smoothSlideViewTo(childAt, -width, childAt.getTop());
                } else {
                    z2 = this.mRightDragger.smoothSlideViewTo(childAt, getWidth(), childAt.getTop());
                }
                z3 |= z2;
                layoutParams.isPeeking = false;
            }
        }
        this.mLeftCallback.removeCallbacks();
        this.mRightCallback.removeCallbacks();
        if (z3) {
            invalidate();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        int childCount = getChildCount();
        float f = 0.0f;
        for (int i = 0; i < childCount; i++) {
            f = Math.max(f, ((LayoutParams) getChildAt(i).getLayoutParams()).onScreen);
        }
        this.mScrimOpacity = f;
        boolean continueSettling = this.mLeftDragger.continueSettling(true);
        boolean continueSettling2 = this.mRightDragger.continueSettling(true);
        if (continueSettling || continueSettling2) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        boolean z;
        if ((motionEvent.getSource() & 2) == 0 || motionEvent.getAction() == 10 || this.mScrimOpacity <= 0.0f) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        int childCount = getChildCount();
        if (childCount == 0) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        for (int i = childCount - 1; i >= 0; i--) {
            View childAt = getChildAt(i);
            if (this.mChildHitRect == null) {
                this.mChildHitRect = new Rect();
            }
            childAt.getHitRect(this.mChildHitRect);
            if (this.mChildHitRect.contains((int) x, (int) y) && !isContentView(childAt)) {
                if (!childAt.getMatrix().isIdentity()) {
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.offsetLocation(getScrollX() - childAt.getLeft(), getScrollY() - childAt.getTop());
                    Matrix matrix = childAt.getMatrix();
                    if (!matrix.isIdentity()) {
                        if (this.mChildInvertedMatrix == null) {
                            this.mChildInvertedMatrix = new Matrix();
                        }
                        matrix.invert(this.mChildInvertedMatrix);
                        obtain.transform(this.mChildInvertedMatrix);
                    }
                    z = childAt.dispatchGenericMotionEvent(obtain);
                    obtain.recycle();
                } else {
                    float scrollX = getScrollX() - childAt.getLeft();
                    float scrollY = getScrollY() - childAt.getTop();
                    motionEvent.offsetLocation(scrollX, scrollY);
                    z = childAt.dispatchGenericMotionEvent(motionEvent);
                    motionEvent.offsetLocation(-scrollX, -scrollY);
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        int height = getHeight();
        boolean isContentView = isContentView(view);
        int width = getWidth();
        int save = canvas.save();
        int i = 0;
        if (isContentView) {
            int childCount = getChildCount();
            int i2 = 0;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                if (childAt != view && childAt.getVisibility() == 0) {
                    Drawable background = childAt.getBackground();
                    if ((background != null && background.getOpacity() == -1) && isDrawerView(childAt) && childAt.getHeight() >= height) {
                        if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                            int right = childAt.getRight();
                            if (right > i2) {
                                i2 = right;
                            }
                        } else {
                            int left = childAt.getLeft();
                            if (left < width) {
                                width = left;
                            }
                        }
                    }
                }
            }
            canvas.clipRect(i2, 0, width, getHeight());
            i = i2;
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        canvas.restoreToCount(save);
        float f = this.mScrimOpacity;
        if (f > 0.0f && isContentView) {
            int i4 = this.mScrimColor;
            this.mScrimPaint.setColor((((int) ((((-16777216) & i4) >>> 24) * f)) << 24) | (i4 & 16777215));
            canvas.drawRect(i, 0.0f, width, getHeight(), this.mScrimPaint);
        }
        return drawChild;
    }

    public View findDrawerWithGravity(int i) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        int absoluteGravity = Gravity.getAbsoluteGravity(i, getLayoutDirection()) & 7;
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            if ((getDrawerViewAbsoluteGravity(childAt) & 7) == absoluteGravity) {
                return childAt;
            }
        }
        return null;
    }

    public View findOpenDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((((LayoutParams) childAt.getLayoutParams()).openState & 1) == 1) {
                return childAt;
            }
        }
        return null;
    }

    public View findVisibleDrawer() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (isDrawerView(childAt)) {
                if (isDrawerView(childAt)) {
                    if (((LayoutParams) childAt.getLayoutParams()).onScreen > 0.0f) {
                        return childAt;
                    }
                } else {
                    throw new IllegalArgumentException("View " + childAt + " is not a drawer");
                }
            }
        }
        return null;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams ? new LayoutParams((LayoutParams) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams) : new LayoutParams(layoutParams);
    }

    public float getDrawerElevation() {
        return this.mDrawerElevation;
    }

    public int getDrawerLockMode(View view) {
        if (isDrawerView(view)) {
            int i = ((LayoutParams) view.getLayoutParams()).gravity;
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            int layoutDirection = getLayoutDirection();
            if (i == 3) {
                int i2 = this.mLockModeLeft;
                if (i2 != 3) {
                    return i2;
                }
                int i3 = layoutDirection == 0 ? this.mLockModeStart : this.mLockModeEnd;
                if (i3 != 3) {
                    return i3;
                }
            } else if (i == 5) {
                int i4 = this.mLockModeRight;
                if (i4 != 3) {
                    return i4;
                }
                int i5 = layoutDirection == 0 ? this.mLockModeEnd : this.mLockModeStart;
                if (i5 != 3) {
                    return i5;
                }
            } else if (i == 8388611) {
                int i6 = this.mLockModeStart;
                if (i6 != 3) {
                    return i6;
                }
                int i7 = layoutDirection == 0 ? this.mLockModeLeft : this.mLockModeRight;
                if (i7 != 3) {
                    return i7;
                }
            } else if (i == 8388613) {
                int i8 = this.mLockModeEnd;
                if (i8 != 3) {
                    return i8;
                }
                int i9 = layoutDirection == 0 ? this.mLockModeRight : this.mLockModeLeft;
                if (i9 != 3) {
                    return i9;
                }
            }
            return 0;
        }
        throw new IllegalArgumentException("View " + view + " is not a drawer");
    }

    public int getDrawerViewAbsoluteGravity(View view) {
        int i = ((LayoutParams) view.getLayoutParams()).gravity;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        return Gravity.getAbsoluteGravity(i, getLayoutDirection());
    }

    public Drawable getStatusBarBackgroundDrawable() {
        return this.mStatusBarBackground;
    }

    public boolean isContentView(View view) {
        return ((LayoutParams) view.getLayoutParams()).gravity == 0;
    }

    public boolean isDrawerView(View view) {
        int i = ((LayoutParams) view.getLayoutParams()).gravity;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        int absoluteGravity = Gravity.getAbsoluteGravity(i, view.getLayoutDirection());
        return ((absoluteGravity & 3) == 0 && (absoluteGravity & 5) == 0) ? false : true;
    }

    public void moveDrawerToOffset(View view, float f) {
        float f2 = ((LayoutParams) view.getLayoutParams()).onScreen;
        float width = view.getWidth();
        int i = ((int) (width * f)) - ((int) (f2 * width));
        if (!checkDrawerViewAbsoluteGravity(view, 3)) {
            i = -i;
        }
        view.offsetLeftAndRight(i);
        setDrawerViewOffset(view, f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            Object obj = this.mLastInsets;
            int systemWindowInsetTop = obj != null ? ((WindowInsets) obj).getSystemWindowInsetTop() : 0;
            if (systemWindowInsetTop > 0) {
                this.mStatusBarBackground.setBounds(0, 0, getWidth(), systemWindowInsetTop);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        if (r0 != 3) goto L_0x006a;
     */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0054 A[LOOP:0: B:10:0x0024->B:19:0x0054, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0052 A[SYNTHETIC] */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r9) {
        /*
            r8 = this;
            int r0 = r9.getActionMasked()
            androidx.customview.widget.ViewDragHelper r1 = r8.mLeftDragger
            boolean r1 = r1.shouldInterceptTouchEvent(r9)
            androidx.customview.widget.ViewDragHelper r2 = r8.mRightDragger
            boolean r2 = r2.shouldInterceptTouchEvent(r9)
            r1 = r1 | r2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x006c
            if (r0 == r2) goto L_0x0065
            r9 = 2
            if (r0 == r9) goto L_0x001e
            r9 = 3
            if (r0 == r9) goto L_0x0065
            goto L_0x006a
        L_0x001e:
            androidx.customview.widget.ViewDragHelper r9 = r8.mLeftDragger
            float[] r0 = r9.mInitialMotionX
            int r0 = r0.length
            r4 = 0
        L_0x0024:
            if (r4 >= r0) goto L_0x0057
            boolean r5 = r9.isPointerDown(r4)
            if (r5 != 0) goto L_0x002d
            goto L_0x004f
        L_0x002d:
            float[] r5 = r9.mLastMotionX
            r5 = r5[r4]
            float[] r6 = r9.mInitialMotionX
            r6 = r6[r4]
            float r5 = r5 - r6
            float[] r6 = r9.mLastMotionY
            r6 = r6[r4]
            float[] r7 = r9.mInitialMotionY
            r7 = r7[r4]
            float r6 = r6 - r7
            float r5 = r5 * r5
            float r6 = r6 * r6
            float r6 = r6 + r5
            int r5 = r9.mTouchSlop
            int r5 = r5 * r5
            float r5 = (float) r5
            int r5 = (r6 > r5 ? 1 : (r6 == r5 ? 0 : -1))
            if (r5 <= 0) goto L_0x004f
            r5 = 1
            goto L_0x0050
        L_0x004f:
            r5 = 0
        L_0x0050:
            if (r5 == 0) goto L_0x0054
            r9 = 1
            goto L_0x0058
        L_0x0054:
            int r4 = r4 + 1
            goto L_0x0024
        L_0x0057:
            r9 = 0
        L_0x0058:
            if (r9 == 0) goto L_0x006a
            androidx.drawerlayout.widget.DrawerLayout$ViewDragCallback r9 = r8.mLeftCallback
            r9.removeCallbacks()
            androidx.drawerlayout.widget.DrawerLayout$ViewDragCallback r9 = r8.mRightCallback
            r9.removeCallbacks()
            goto L_0x006a
        L_0x0065:
            r8.closeDrawers(r2)
            r8.mChildrenCanceledTouch = r3
        L_0x006a:
            r9 = 0
            goto L_0x0094
        L_0x006c:
            float r0 = r9.getX()
            float r9 = r9.getY()
            r8.mInitialMotionX = r0
            r8.mInitialMotionY = r9
            float r4 = r8.mScrimOpacity
            r5 = 0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 <= 0) goto L_0x0091
            androidx.customview.widget.ViewDragHelper r4 = r8.mLeftDragger
            int r0 = (int) r0
            int r9 = (int) r9
            android.view.View r9 = r4.findTopChildUnder(r0, r9)
            if (r9 == 0) goto L_0x0091
            boolean r9 = r8.isContentView(r9)
            if (r9 == 0) goto L_0x0091
            r9 = 1
            goto L_0x0092
        L_0x0091:
            r9 = 0
        L_0x0092:
            r8.mChildrenCanceledTouch = r3
        L_0x0094:
            if (r1 != 0) goto L_0x00bb
            if (r9 != 0) goto L_0x00bb
            int r9 = r8.getChildCount()
            r0 = 0
        L_0x009d:
            if (r0 >= r9) goto L_0x00b2
            android.view.View r1 = r8.getChildAt(r0)
            android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
            androidx.drawerlayout.widget.DrawerLayout$LayoutParams r1 = (androidx.drawerlayout.widget.DrawerLayout.LayoutParams) r1
            boolean r1 = r1.isPeeking
            if (r1 == 0) goto L_0x00af
            r9 = 1
            goto L_0x00b3
        L_0x00af:
            int r0 = r0 + 1
            goto L_0x009d
        L_0x00b2:
            r9 = 0
        L_0x00b3:
            if (r9 != 0) goto L_0x00bb
            boolean r9 = r8.mChildrenCanceledTouch
            if (r9 == 0) goto L_0x00ba
            goto L_0x00bb
        L_0x00ba:
            r2 = 0
        L_0x00bb:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (findVisibleDrawer() != null) {
                keyEvent.startTracking();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        View findVisibleDrawer = findVisibleDrawer();
        if (findVisibleDrawer != null && getDrawerLockMode(findVisibleDrawer) == 0) {
            closeDrawers(false);
        }
        return findVisibleDrawer != null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        int i5;
        int i6;
        this.mInLayout = true;
        int i7 = i3 - i;
        int childCount = getChildCount();
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (isContentView(childAt)) {
                    int i9 = ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin;
                    childAt.layout(i9, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, childAt.getMeasuredWidth() + i9, childAt.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin);
                } else {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (checkDrawerViewAbsoluteGravity(childAt, 3)) {
                        float f2 = measuredWidth;
                        i5 = (-measuredWidth) + ((int) (layoutParams.onScreen * f2));
                        f = (measuredWidth + i5) / f2;
                    } else {
                        float f3 = measuredWidth;
                        f = (i7 - i6) / f3;
                        i5 = i7 - ((int) (layoutParams.onScreen * f3));
                    }
                    boolean z2 = f != layoutParams.onScreen;
                    int i10 = layoutParams.gravity & 112;
                    if (i10 == 16) {
                        int i11 = i4 - i2;
                        int i12 = (i11 - measuredHeight) / 2;
                        int i13 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        if (i12 < i13) {
                            i12 = i13;
                        } else {
                            int i14 = i12 + measuredHeight;
                            int i15 = ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
                            if (i14 > i11 - i15) {
                                i12 = (i11 - i15) - measuredHeight;
                            }
                        }
                        childAt.layout(i5, i12, measuredWidth + i5, measuredHeight + i12);
                    } else if (i10 != 80) {
                        int i16 = ((ViewGroup.MarginLayoutParams) layoutParams).topMargin;
                        childAt.layout(i5, i16, measuredWidth + i5, measuredHeight + i16);
                    } else {
                        int i17 = i4 - i2;
                        childAt.layout(i5, (i17 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin) - childAt.getMeasuredHeight(), measuredWidth + i5, i17 - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                    }
                    if (z2) {
                        setDrawerViewOffset(childAt, f);
                    }
                    int i18 = layoutParams.onScreen > 0.0f ? 0 : 4;
                    if (childAt.getVisibility() != i18) {
                        childAt.setVisibility(i18);
                    }
                }
            }
        }
        this.mInLayout = false;
        this.mFirstLayout = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0050  */
    @Override // android.view.View
    @android.annotation.SuppressLint({"WrongConstant"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r17, int r18) {
        /*
            Method dump skipped, instructions count: 441
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onMeasure(int, int):void");
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        View findDrawerWithGravity;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        int i = savedState.openDrawerGravity;
        if (!(i == 0 || (findDrawerWithGravity = findDrawerWithGravity(i)) == null)) {
            openDrawer(findDrawerWithGravity, true);
        }
        int i2 = savedState.lockModeLeft;
        if (i2 != 3) {
            setDrawerLockMode(i2, 3);
        }
        int i3 = savedState.lockModeRight;
        if (i3 != 3) {
            setDrawerLockMode(i3, 5);
        }
        int i4 = savedState.lockModeStart;
        if (i4 != 3) {
            setDrawerLockMode(i4, 8388611);
        }
        int i5 = savedState.lockModeEnd;
        if (i5 != 3) {
            setDrawerLockMode(i5, 8388613);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            int i2 = layoutParams.openState;
            boolean z = true;
            boolean z2 = i2 == 1;
            if (i2 != 2) {
                z = false;
            }
            if (z2 || z) {
                savedState.openDrawerGravity = layoutParams.gravity;
                break;
            }
        }
        savedState.lockModeLeft = this.mLockModeLeft;
        savedState.lockModeRight = this.mLockModeRight;
        savedState.lockModeStart = this.mLockModeStart;
        savedState.lockModeEnd = this.mLockModeEnd;
        return savedState;
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0059, code lost:
        if (getDrawerLockMode(r7) != 2) goto L_0x005c;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            r6 = this;
            androidx.customview.widget.ViewDragHelper r0 = r6.mLeftDragger
            r0.processTouchEvent(r7)
            androidx.customview.widget.ViewDragHelper r0 = r6.mRightDragger
            r0.processTouchEvent(r7)
            int r0 = r7.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x0060
            if (r0 == r2) goto L_0x0020
            r7 = 3
            if (r0 == r7) goto L_0x001a
            goto L_0x006e
        L_0x001a:
            r6.closeDrawers(r2)
            r6.mChildrenCanceledTouch = r1
            goto L_0x006e
        L_0x0020:
            float r0 = r7.getX()
            float r7 = r7.getY()
            androidx.customview.widget.ViewDragHelper r3 = r6.mLeftDragger
            int r4 = (int) r0
            int r5 = (int) r7
            android.view.View r3 = r3.findTopChildUnder(r4, r5)
            if (r3 == 0) goto L_0x005b
            boolean r3 = r6.isContentView(r3)
            if (r3 == 0) goto L_0x005b
            float r3 = r6.mInitialMotionX
            float r0 = r0 - r3
            float r3 = r6.mInitialMotionY
            float r7 = r7 - r3
            androidx.customview.widget.ViewDragHelper r3 = r6.mLeftDragger
            int r3 = r3.mTouchSlop
            float r0 = r0 * r0
            float r7 = r7 * r7
            float r7 = r7 + r0
            int r3 = r3 * r3
            float r0 = (float) r3
            int r7 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r7 >= 0) goto L_0x005b
            android.view.View r7 = r6.findOpenDrawer()
            if (r7 == 0) goto L_0x005b
            int r7 = r6.getDrawerLockMode(r7)
            r0 = 2
            if (r7 != r0) goto L_0x005c
        L_0x005b:
            r1 = 1
        L_0x005c:
            r6.closeDrawers(r1)
            goto L_0x006e
        L_0x0060:
            float r0 = r7.getX()
            float r7 = r7.getY()
            r6.mInitialMotionX = r0
            r6.mInitialMotionY = r7
            r6.mChildrenCanceledTouch = r1
        L_0x006e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.drawerlayout.widget.DrawerLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void openDrawer(View view, boolean z) {
        if (isDrawerView(view)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (this.mFirstLayout) {
                layoutParams.onScreen = 1.0f;
                layoutParams.openState = 1;
                updateChildrenImportantForAccessibility(view, true);
            } else if (z) {
                layoutParams.openState |= 2;
                if (checkDrawerViewAbsoluteGravity(view, 3)) {
                    this.mLeftDragger.smoothSlideViewTo(view, 0, view.getTop());
                } else {
                    this.mRightDragger.smoothSlideViewTo(view, getWidth() - view.getWidth(), view.getTop());
                }
            } else {
                moveDrawerToOffset(view, 1.0f);
                int i = layoutParams.gravity;
                updateDrawerState(0, view);
                view.setVisibility(0);
            }
            invalidate();
            return;
        }
        throw new IllegalArgumentException("View " + view + " is not a sliding drawer");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z) {
            closeDrawers(true);
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (!this.mInLayout) {
            super.requestLayout();
        }
    }

    public void setDrawerElevation(float f) {
        this.mDrawerElevation = f;
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (isDrawerView(childAt)) {
                float f2 = this.mDrawerElevation;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                childAt.setElevation(f2);
            }
        }
    }

    @Deprecated
    public void setDrawerListener(DrawerListener drawerListener) {
        List<DrawerListener> list;
        DrawerListener drawerListener2 = this.mListener;
        if (!(drawerListener2 == null || (list = this.mListeners) == null)) {
            list.remove(drawerListener2);
        }
        if (drawerListener != null) {
            if (this.mListeners == null) {
                this.mListeners = new ArrayList();
            }
            this.mListeners.add(drawerListener);
        }
        this.mListener = drawerListener;
    }

    public void setDrawerLockMode(int i, int i2) {
        View findDrawerWithGravity;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        int absoluteGravity = Gravity.getAbsoluteGravity(i2, getLayoutDirection());
        if (i2 == 3) {
            this.mLockModeLeft = i;
        } else if (i2 == 5) {
            this.mLockModeRight = i;
        } else if (i2 == 8388611) {
            this.mLockModeStart = i;
        } else if (i2 == 8388613) {
            this.mLockModeEnd = i;
        }
        if (i != 0) {
            (absoluteGravity == 3 ? this.mLeftDragger : this.mRightDragger).cancel();
        }
        if (i == 1) {
            View findDrawerWithGravity2 = findDrawerWithGravity(absoluteGravity);
            if (findDrawerWithGravity2 != null) {
                closeDrawer(findDrawerWithGravity2, true);
            }
        } else if (i == 2 && (findDrawerWithGravity = findDrawerWithGravity(absoluteGravity)) != null) {
            openDrawer(findDrawerWithGravity, true);
        }
    }

    public void setDrawerViewOffset(View view, float f) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (f != layoutParams.onScreen) {
            layoutParams.onScreen = f;
            List<DrawerListener> list = this.mListeners;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        this.mListeners.get(size).onDrawerSlide(view, f);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void setScrimColor(int i) {
        this.mScrimColor = i;
        invalidate();
    }

    public void setStatusBarBackground(Drawable drawable) {
        this.mStatusBarBackground = drawable;
        invalidate();
    }

    public void setStatusBarBackgroundColor(int i) {
        this.mStatusBarBackground = new ColorDrawable(i);
        invalidate();
    }

    public final void updateChildrenImportantForAccessibility(View view, boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if ((z || isDrawerView(childAt)) && (!z || childAt != view)) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                childAt.setImportantForAccessibility(4);
            } else {
                AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                childAt.setImportantForAccessibility(1);
            }
        }
    }

    public void updateDrawerState(int i, View view) {
        View rootView;
        int i2 = this.mLeftDragger.mDragState;
        int i3 = this.mRightDragger.mDragState;
        int i4 = 2;
        if (i2 == 1 || i3 == 1) {
            i4 = 1;
        } else if (!(i2 == 2 || i3 == 2)) {
            i4 = 0;
        }
        if (view != null && i == 0) {
            float f = ((LayoutParams) view.getLayoutParams()).onScreen;
            if (f == 0.0f) {
                LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                if ((layoutParams.openState & 1) == 1) {
                    layoutParams.openState = 0;
                    List<DrawerListener> list = this.mListeners;
                    if (list != null) {
                        for (int size = list.size() - 1; size >= 0; size--) {
                            this.mListeners.get(size).onDrawerClosed(view);
                        }
                    }
                    updateChildrenImportantForAccessibility(view, false);
                    if (hasWindowFocus() && (rootView = getRootView()) != null) {
                        rootView.sendAccessibilityEvent(32);
                    }
                }
            } else if (f == 1.0f) {
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                if ((layoutParams2.openState & 1) == 0) {
                    layoutParams2.openState = 1;
                    List<DrawerListener> list2 = this.mListeners;
                    if (list2 != null) {
                        for (int size2 = list2.size() - 1; size2 >= 0; size2--) {
                            this.mListeners.get(size2).onDrawerOpened(view);
                        }
                    }
                    updateChildrenImportantForAccessibility(view, true);
                    if (hasWindowFocus()) {
                        sendAccessibilityEvent(32);
                    }
                }
            }
        }
        if (i4 != this.mDrawerState) {
            this.mDrawerState = i4;
            List<DrawerListener> list3 = this.mListeners;
            if (list3 != null) {
                for (int size3 = list3.size() - 1; size3 >= 0; size3--) {
                    this.mListeners.get(size3).onDrawerStateChanged(i4);
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void setStatusBarBackground(int i) {
        Drawable drawable;
        if (i != 0) {
            Context context = getContext();
            Object obj = ContextCompat.sLock;
            drawable = ContextCompat.Api21Impl.getDrawable(context, i);
        } else {
            drawable = null;
        }
        this.mStatusBarBackground = drawable;
        invalidate();
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public boolean isPeeking;
        public float onScreen;
        public int openState;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = 0;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, DrawerLayout.LAYOUT_ATTRS);
            this.gravity = obtainStyledAttributes.getInt(0, 0);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = 0;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = 0;
            this.gravity = layoutParams.gravity;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = 0;
        }
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.drawerlayout.widget.DrawerLayout.SavedState.1
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public Object[] newArray(int i) {
                return new SavedState[i];
            }

            @Override // android.os.Parcelable.Creator
            public Object createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        public int lockModeEnd;
        public int lockModeLeft;
        public int lockModeRight;
        public int lockModeStart;
        public int openDrawerGravity;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.openDrawerGravity = 0;
            this.openDrawerGravity = parcel.readInt();
            this.lockModeLeft = parcel.readInt();
            this.lockModeRight = parcel.readInt();
            this.lockModeStart = parcel.readInt();
            this.lockModeEnd = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mSuperState, i);
            parcel.writeInt(this.openDrawerGravity);
            parcel.writeInt(this.lockModeLeft);
            parcel.writeInt(this.lockModeRight);
            parcel.writeInt(this.lockModeStart);
            parcel.writeInt(this.lockModeEnd);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
            this.openDrawerGravity = 0;
        }
    }

    public void setDrawerLockMode(int i) {
        setDrawerLockMode(i, 3);
        setDrawerLockMode(i, 5);
    }
}
