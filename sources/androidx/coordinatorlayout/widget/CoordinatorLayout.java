package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.coordinatorlayout.R$styleable;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pools$Pool;
import androidx.core.util.Pools$SynchronizedPool;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.foss.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2, NestedScrollingParent3 {
    public static final Class<?>[] CONSTRUCTOR_PARAMS;
    public static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    public static final String WIDGET_PACKAGE_NAME;
    public static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors;
    public static final Pools$Pool<Rect> sRectPool;
    public OnApplyWindowInsetsListener mApplyWindowInsetsListener;
    public View mBehaviorTouchView;
    public boolean mDisallowInterceptReset;
    public boolean mDrawStatusBarBackground;
    public boolean mIsAttachedToWindow;
    public int[] mKeylines;
    public WindowInsetsCompat mLastInsets;
    public boolean mNeedsPreDrawListener;
    public View mNestedScrollingTarget;
    public ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    public OnPreDrawListener mOnPreDrawListener;
    public Paint mScrimPaint;
    public Drawable mStatusBarBackground;
    public final List<View> mDependencySortedChildren = new ArrayList();
    public final DirectedAcyclicGraph<View> mChildDag = new DirectedAcyclicGraph<>();
    public final List<View> mTempList1 = new ArrayList();
    public final List<View> mTempDependenciesList = new ArrayList();
    public final int[] mBehaviorConsumed = new int[2];
    public final int[] mNestedScrollingV2ConsumedCompat = new int[2];
    public final NestedScrollingParentHelper mNestedScrollingParentHelper = new NestedScrollingParentHelper();

    /* loaded from: classes.dex */
    public interface AttachedBehavior {
        Behavior getBehavior();
    }

    /* loaded from: classes.dex */
    public static abstract class Behavior<V extends View> {
        public Behavior() {
        }

        public boolean getInsetDodgeRect(CoordinatorLayout coordinatorLayout, V v, Rect rect) {
            return false;
        }

        public int getScrimColor() {
            return -16777216;
        }

        public float getScrimOpacity() {
            return 0.0f;
        }

        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public WindowInsetsCompat onApplyWindowInsets(WindowInsetsCompat windowInsetsCompat) {
            return windowInsetsCompat;
        }

        public void onAttachedToLayoutParams(LayoutParams layoutParams) {
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, V v, View view) {
            return false;
        }

        public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, V v, View view) {
        }

        public void onDetachedFromLayoutParams() {
        }

        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
            return false;
        }

        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, V v, int i, int i2, int i3, int i4) {
            return false;
        }

        public boolean onNestedFling() {
            return false;
        }

        public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
            return false;
        }

        @Deprecated
        public void onNestedPreScroll() {
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
            if (i3 == 0) {
                onNestedPreScroll();
            }
        }

        @Deprecated
        public void onNestedScroll() {
        }

        public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            iArr[0] = iArr[0] + i3;
            iArr[1] = iArr[1] + i4;
            if (i5 == 0) {
                onNestedScroll();
            }
        }

        @Deprecated
        public void onNestedScrollAccepted() {
        }

        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, V v, Rect rect, boolean z) {
            return false;
        }

        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        }

        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
            return View.BaseSavedState.EMPTY_STATE;
        }

        @Deprecated
        public boolean onStartNestedScroll() {
            return false;
        }

        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
            if (i2 == 0) {
                return onStartNestedScroll();
            }
            return false;
        }

        @Deprecated
        public void onStopNestedScroll() {
        }

        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
            if (i == 0) {
                onStopNestedScroll();
            }
        }

        public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
            return false;
        }

        public Behavior(Context context, AttributeSet attributeSet) {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Deprecated
    /* loaded from: classes.dex */
    public @interface DefaultBehavior {
        Class<? extends Behavior> value();
    }

    /* loaded from: classes.dex */
    public class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        public HierarchyChangeListener() {
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewAdded(View view, View view2) {
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewAdded(view, view2);
            }
        }

        @Override // android.view.ViewGroup.OnHierarchyChangeListener
        public void onChildViewRemoved(View view, View view2) {
            CoordinatorLayout.this.onChildViewsChanged(2);
            ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = CoordinatorLayout.this.mOnHierarchyChangeListener;
            if (onHierarchyChangeListener != null) {
                onHierarchyChangeListener.onChildViewRemoved(view, view2);
            }
        }
    }

    /* loaded from: classes.dex */
    public class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
        public OnPreDrawListener() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            CoordinatorLayout.this.onChildViewsChanged(0);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class ViewElevationComparator implements Comparator<View> {
        @Override // java.util.Comparator
        public int compare(View view, View view2) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            float z = view.getZ();
            float z2 = view2.getZ();
            if (z > z2) {
                return -1;
            }
            return z < z2 ? 1 : 0;
        }
    }

    static {
        Package r0 = CoordinatorLayout.class.getPackage();
        WIDGET_PACKAGE_NAME = r0 != null ? r0.getName() : null;
        TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
        CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
        sConstructors = new ThreadLocal<>();
        sRectPool = new Pools$SynchronizedPool(12);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.coordinatorLayoutStyle);
        int[] iArr = R$styleable.CoordinatorLayout;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.coordinatorLayoutStyle, 0);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, iArr, attributeSet, obtainStyledAttributes, R.attr.coordinatorLayoutStyle, 0);
        }
        int resourceId = obtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.mKeylines = resources.getIntArray(resourceId);
            float f = resources.getDisplayMetrics().density;
            int length = this.mKeylines.length;
            for (int i = 0; i < length; i++) {
                int[] iArr2 = this.mKeylines;
                iArr2[i] = (int) (iArr2[i] * f);
            }
        }
        this.mStatusBarBackground = obtainStyledAttributes.getDrawable(1);
        obtainStyledAttributes.recycle();
        setupForInsets();
        super.setOnHierarchyChangeListener(new HierarchyChangeListener());
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public static Rect acquireTempRect() {
        Rect acquire = sRectPool.acquire();
        return acquire == null ? new Rect() : acquire;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof LayoutParams) && super.checkLayoutParams(layoutParams);
    }

    public final void constrainChildRect(LayoutParams layoutParams, Rect rect, int i, int i2) {
        int width = getWidth();
        int height = getHeight();
        int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin));
        int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i2) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin));
        rect.set(max, max2, i + max, i2 + max2);
    }

    public void dispatchDependentViewsChanged(View view) {
        ArrayList<View> orDefault = this.mChildDag.mGraph.getOrDefault(view, null);
        if (!(orDefault == null || orDefault.isEmpty())) {
            for (int i = 0; i < orDefault.size(); i++) {
                View view2 = orDefault.get(i);
                Behavior behavior = ((LayoutParams) view2.getLayoutParams()).mBehavior;
                if (behavior != null) {
                    behavior.onDependentViewChanged(this, view2, view);
                }
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Behavior behavior = layoutParams.mBehavior;
        if (behavior != null) {
            float scrimOpacity = behavior.getScrimOpacity();
            if (scrimOpacity > 0.0f) {
                if (this.mScrimPaint == null) {
                    this.mScrimPaint = new Paint();
                }
                this.mScrimPaint.setColor(layoutParams.mBehavior.getScrimColor());
                Paint paint = this.mScrimPaint;
                int round = Math.round(scrimOpacity * 255.0f);
                if (round < 0) {
                    round = 0;
                } else if (round > 255) {
                    round = 255;
                }
                paint.setAlpha(round);
                int save = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.mScrimPaint);
                canvas.restoreToCount(save);
            }
        }
        return super.drawChild(canvas, view, j);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mStatusBarBackground;
        boolean z = false;
        if (drawable != null && drawable.isStateful()) {
            z = false | drawable.setState(drawableState);
        }
        if (z) {
            invalidate();
        }
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void getChildRect(View view, boolean z, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z) {
            getDescendantRect(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List<View> getDependencies(View view) {
        DirectedAcyclicGraph<View> directedAcyclicGraph = this.mChildDag;
        int i = directedAcyclicGraph.mGraph.mSize;
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < i; i2++) {
            ArrayList<View> valueAt = directedAcyclicGraph.mGraph.valueAt(i2);
            if (valueAt != null && valueAt.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(directedAcyclicGraph.mGraph.keyAt(i2));
            }
        }
        this.mTempDependenciesList.clear();
        if (arrayList != null) {
            this.mTempDependenciesList.addAll(arrayList);
        }
        return this.mTempDependenciesList;
    }

    public final List<View> getDependencySortedChildren() {
        prepareChildren();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    public List<View> getDependents(View view) {
        ArrayList<View> orDefault = this.mChildDag.mGraph.getOrDefault(view, null);
        this.mTempDependenciesList.clear();
        if (orDefault != null) {
            this.mTempDependenciesList.addAll(orDefault);
        }
        return this.mTempDependenciesList;
    }

    public void getDescendantRect(View view, Rect rect) {
        ThreadLocal<Matrix> threadLocal = ViewGroupUtils.sMatrix;
        rect.set(0, 0, view.getWidth(), view.getHeight());
        ThreadLocal<Matrix> threadLocal2 = ViewGroupUtils.sMatrix;
        Matrix matrix = threadLocal2.get();
        if (matrix == null) {
            matrix = new Matrix();
            threadLocal2.set(matrix);
        } else {
            matrix.reset();
        }
        ViewGroupUtils.offsetDescendantMatrix(this, view, matrix);
        ThreadLocal<RectF> threadLocal3 = ViewGroupUtils.sRectF;
        RectF rectF = threadLocal3.get();
        if (rectF == null) {
            rectF = new RectF();
            threadLocal3.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    public final void getDesiredAnchoredChildRectWithoutConstraints(int i, Rect rect, Rect rect2, LayoutParams layoutParams, int i2, int i3) {
        int i4;
        int i5;
        int i6 = layoutParams.gravity;
        if (i6 == 0) {
            i6 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i6, i);
        int i7 = layoutParams.anchorGravity;
        if ((i7 & 7) == 0) {
            i7 |= 8388611;
        }
        if ((i7 & 112) == 0) {
            i7 |= 48;
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i7, i);
        int i8 = absoluteGravity & 7;
        int i9 = absoluteGravity & 112;
        int i10 = absoluteGravity2 & 7;
        int i11 = absoluteGravity2 & 112;
        if (i10 == 1) {
            i4 = rect.left + (rect.width() / 2);
        } else if (i10 != 5) {
            i4 = rect.left;
        } else {
            i4 = rect.right;
        }
        if (i11 == 16) {
            i5 = rect.top + (rect.height() / 2);
        } else if (i11 != 80) {
            i5 = rect.top;
        } else {
            i5 = rect.bottom;
        }
        if (i8 == 1) {
            i4 -= i2 / 2;
        } else if (i8 != 5) {
            i4 -= i2;
        }
        if (i9 == 16) {
            i5 -= i3 / 2;
        } else if (i9 != 80) {
            i5 -= i3;
        }
        rect2.set(i4, i5, i2 + i4, i3 + i5);
    }

    public final int getKeyline(int i) {
        int[] iArr = this.mKeylines;
        if (iArr == null) {
            Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + i);
            return 0;
        } else if (i >= 0 && i < iArr.length) {
            return iArr[i];
        } else {
            Log.e("CoordinatorLayout", "Keyline index " + i + " out of range for " + this);
            return 0;
        }
    }

    public final WindowInsetsCompat getLastWindowInsets() {
        return this.mLastInsets;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        return this.mNestedScrollingParentHelper.getNestedScrollAxes();
    }

    public LayoutParams getResolvedLayoutParams(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (!layoutParams.mBehaviorResolved) {
            if (view instanceof AttachedBehavior) {
                Behavior behavior = ((AttachedBehavior) view).getBehavior();
                if (behavior == null) {
                    Log.e("CoordinatorLayout", "Attached behavior class is null");
                }
                layoutParams.setBehavior(behavior);
                layoutParams.mBehaviorResolved = true;
            } else {
                DefaultBehavior defaultBehavior = null;
                for (Class<?> cls = view.getClass(); cls != null; cls = cls.getSuperclass()) {
                    defaultBehavior = (DefaultBehavior) cls.getAnnotation(DefaultBehavior.class);
                    if (defaultBehavior != null) {
                        break;
                    }
                }
                if (defaultBehavior != null) {
                    try {
                        layoutParams.setBehavior((Behavior) defaultBehavior.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                    } catch (Exception e) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Default behavior class ");
                        outline13.append(defaultBehavior.value().getName());
                        outline13.append(" could not be instantiated. Did you forget a default constructor?");
                        Log.e("CoordinatorLayout", outline13.toString(), e);
                    }
                }
                layoutParams.mBehaviorResolved = true;
            }
        }
        return layoutParams;
    }

    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    public boolean isPointInChildBounds(View view, int i, int i2) {
        Rect acquireTempRect = acquireTempRect();
        getDescendantRect(view, acquireTempRect);
        try {
            return acquireTempRect.contains(i, i2);
        } finally {
            acquireTempRect.setEmpty();
            sRectPool.release(acquireTempRect);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new OnPreDrawListener();
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (getFitsSystemWindows()) {
                requestApplyInsets();
            }
        }
        this.mIsAttachedToWindow = true;
    }

    public final void onChildViewsChanged(int i) {
        int i2;
        boolean z;
        boolean z2;
        boolean z3;
        int width;
        int i3;
        int i4;
        int i5;
        int height;
        int i6;
        int i7;
        int i8;
        int i9;
        Behavior behavior;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        int layoutDirection = getLayoutDirection();
        int size = this.mDependencySortedChildren.size();
        Rect acquireTempRect = acquireTempRect();
        Rect acquireTempRect2 = acquireTempRect();
        Rect acquireTempRect3 = acquireTempRect();
        int i10 = 0;
        while (i10 < size) {
            View view = this.mDependencySortedChildren.get(i10);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            if (i == 0 && view.getVisibility() == 8) {
                size = size;
                acquireTempRect3 = acquireTempRect3;
                i2 = i10;
            } else {
                int i11 = 0;
                while (i11 < i10) {
                    if (layoutParams.mAnchorDirectChild == this.mDependencySortedChildren.get(i11)) {
                        LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                        if (layoutParams2.mAnchorView != null) {
                            Rect acquireTempRect4 = acquireTempRect();
                            Rect acquireTempRect5 = acquireTempRect();
                            Rect acquireTempRect6 = acquireTempRect();
                            getDescendantRect(layoutParams2.mAnchorView, acquireTempRect4);
                            getChildRect(view, false, acquireTempRect5);
                            int measuredWidth = view.getMeasuredWidth();
                            size = size;
                            int measuredHeight = view.getMeasuredHeight();
                            i10 = i10;
                            i9 = i11;
                            acquireTempRect3 = acquireTempRect3;
                            layoutParams = layoutParams;
                            getDesiredAnchoredChildRectWithoutConstraints(layoutDirection, acquireTempRect4, acquireTempRect6, layoutParams2, measuredWidth, measuredHeight);
                            boolean z4 = (acquireTempRect6.left == acquireTempRect5.left && acquireTempRect6.top == acquireTempRect5.top) ? false : true;
                            constrainChildRect(layoutParams2, acquireTempRect6, measuredWidth, measuredHeight);
                            int i12 = acquireTempRect6.left - acquireTempRect5.left;
                            int i13 = acquireTempRect6.top - acquireTempRect5.top;
                            if (i12 != 0) {
                                ViewCompat.offsetLeftAndRight(view, i12);
                            }
                            if (i13 != 0) {
                                ViewCompat.offsetTopAndBottom(view, i13);
                            }
                            if (z4 && (behavior = layoutParams2.mBehavior) != null) {
                                behavior.onDependentViewChanged(this, view, layoutParams2.mAnchorView);
                            }
                            acquireTempRect4.setEmpty();
                            Pools$Pool<Rect> pools$Pool = sRectPool;
                            pools$Pool.release(acquireTempRect4);
                            acquireTempRect5.setEmpty();
                            pools$Pool.release(acquireTempRect5);
                            acquireTempRect6.setEmpty();
                            pools$Pool.release(acquireTempRect6);
                            i11 = i9 + 1;
                        }
                    }
                    i9 = i11;
                    size = size;
                    acquireTempRect3 = acquireTempRect3;
                    i10 = i10;
                    layoutParams = layoutParams;
                    i11 = i9 + 1;
                }
                i2 = i10;
                getChildRect(view, true, acquireTempRect2);
                if (layoutParams.insetEdge != 0 && !acquireTempRect2.isEmpty()) {
                    int absoluteGravity = Gravity.getAbsoluteGravity(layoutParams.insetEdge, layoutDirection);
                    int i14 = absoluteGravity & 112;
                    if (i14 == 48) {
                        acquireTempRect.top = Math.max(acquireTempRect.top, acquireTempRect2.bottom);
                    } else if (i14 == 80) {
                        acquireTempRect.bottom = Math.max(acquireTempRect.bottom, getHeight() - acquireTempRect2.top);
                    }
                    int i15 = absoluteGravity & 7;
                    if (i15 == 3) {
                        acquireTempRect.left = Math.max(acquireTempRect.left, acquireTempRect2.right);
                    } else if (i15 == 5) {
                        acquireTempRect.right = Math.max(acquireTempRect.right, getWidth() - acquireTempRect2.left);
                    }
                }
                if (layoutParams.dodgeInsetEdges != 0 && view.getVisibility() == 0) {
                    AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                    if (view.isLaidOut() && view.getWidth() > 0 && view.getHeight() > 0) {
                        LayoutParams layoutParams3 = (LayoutParams) view.getLayoutParams();
                        Behavior behavior2 = layoutParams3.mBehavior;
                        Rect acquireTempRect7 = acquireTempRect();
                        Rect acquireTempRect8 = acquireTempRect();
                        acquireTempRect8.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                        if (behavior2 == null || !behavior2.getInsetDodgeRect(this, view, acquireTempRect7)) {
                            acquireTempRect7.set(acquireTempRect8);
                        } else if (!acquireTempRect8.contains(acquireTempRect7)) {
                            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Rect should be within the child's bounds. Rect:");
                            outline13.append(acquireTempRect7.toShortString());
                            outline13.append(" | Bounds:");
                            outline13.append(acquireTempRect8.toShortString());
                            throw new IllegalArgumentException(outline13.toString());
                        }
                        acquireTempRect8.setEmpty();
                        Pools$Pool<Rect> pools$Pool2 = sRectPool;
                        pools$Pool2.release(acquireTempRect8);
                        if (acquireTempRect7.isEmpty()) {
                            acquireTempRect7.setEmpty();
                            pools$Pool2.release(acquireTempRect7);
                        } else {
                            int absoluteGravity2 = Gravity.getAbsoluteGravity(layoutParams3.dodgeInsetEdges, layoutDirection);
                            if ((absoluteGravity2 & 48) != 48 || (i7 = (acquireTempRect7.top - ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin) - layoutParams3.mInsetOffsetY) >= (i8 = acquireTempRect.top)) {
                                z2 = false;
                            } else {
                                setInsetOffsetY(view, i8 - i7);
                                z2 = true;
                            }
                            if ((absoluteGravity2 & 80) == 80 && (height = ((getHeight() - acquireTempRect7.bottom) - ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin) + layoutParams3.mInsetOffsetY) < (i6 = acquireTempRect.bottom)) {
                                setInsetOffsetY(view, height - i6);
                                z2 = true;
                            }
                            if (!z2) {
                                setInsetOffsetY(view, 0);
                            }
                            if ((absoluteGravity2 & 3) != 3 || (i4 = (acquireTempRect7.left - ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin) - layoutParams3.mInsetOffsetX) >= (i5 = acquireTempRect.left)) {
                                z3 = false;
                            } else {
                                setInsetOffsetX(view, i5 - i4);
                                z3 = true;
                            }
                            if ((absoluteGravity2 & 5) == 5 && (width = ((getWidth() - acquireTempRect7.right) - ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin) + layoutParams3.mInsetOffsetX) < (i3 = acquireTempRect.right)) {
                                setInsetOffsetX(view, width - i3);
                                z3 = true;
                            }
                            if (!z3) {
                                setInsetOffsetX(view, 0);
                            }
                            acquireTempRect7.setEmpty();
                            pools$Pool2.release(acquireTempRect7);
                        }
                    }
                }
                if (i != 2) {
                    acquireTempRect3 = acquireTempRect3;
                    acquireTempRect3.set(((LayoutParams) view.getLayoutParams()).mLastChildRect);
                    if (acquireTempRect3.equals(acquireTempRect2)) {
                        size = size;
                    } else {
                        ((LayoutParams) view.getLayoutParams()).mLastChildRect.set(acquireTempRect2);
                    }
                } else {
                    acquireTempRect3 = acquireTempRect3;
                }
                size = size;
                for (int i16 = i2 + 1; i16 < size; i16++) {
                    View view2 = this.mDependencySortedChildren.get(i16);
                    LayoutParams layoutParams4 = (LayoutParams) view2.getLayoutParams();
                    Behavior behavior3 = layoutParams4.mBehavior;
                    if (behavior3 != null && behavior3.layoutDependsOn(this, view2, view)) {
                        if (i != 0 || !layoutParams4.mDidChangeAfterNestedScroll) {
                            if (i != 2) {
                                z = behavior3.onDependentViewChanged(this, view2, view);
                            } else {
                                behavior3.onDependentViewRemoved(this, view2, view);
                                z = true;
                            }
                            if (i == 1) {
                                layoutParams4.mDidChangeAfterNestedScroll = z;
                            }
                        } else {
                            layoutParams4.mDidChangeAfterNestedScroll = false;
                        }
                    }
                }
            }
            i10 = i2 + 1;
        }
        acquireTempRect.setEmpty();
        Pools$Pool<Rect> pools$Pool3 = sRectPool;
        pools$Pool3.release(acquireTempRect);
        acquireTempRect2.setEmpty();
        pools$Pool3.release(acquireTempRect2);
        acquireTempRect3.setEmpty();
        pools$Pool3.release(acquireTempRect3);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        resetTouchBehaviors(false);
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        View view = this.mNestedScrollingTarget;
        if (view != null) {
            onStopNestedScroll(view);
        }
        this.mIsAttachedToWindow = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
            WindowInsetsCompat windowInsetsCompat = this.mLastInsets;
            int systemWindowInsetTop = windowInsetsCompat != null ? windowInsetsCompat.getSystemWindowInsetTop() : 0;
            if (systemWindowInsetTop > 0) {
                this.mStatusBarBackground.setBounds(0, 0, getWidth(), systemWindowInsetTop);
                this.mStatusBarBackground.draw(canvas);
            }
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            resetTouchBehaviors(true);
        }
        boolean performIntercept = performIntercept(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            resetTouchBehaviors(true);
        }
        return performIntercept;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        Behavior behavior;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        int layoutDirection = getLayoutDirection();
        int size = this.mDependencySortedChildren.size();
        for (int i5 = 0; i5 < size; i5++) {
            View view = this.mDependencySortedChildren.get(i5);
            if (view.getVisibility() != 8 && ((behavior = ((LayoutParams) view.getLayoutParams()).mBehavior) == null || !behavior.onLayoutChild(this, view, layoutDirection))) {
                onLayoutChild(view, layoutDirection);
            }
        }
    }

    public void onLayoutChild(View view, int i) {
        Rect acquireTempRect;
        Rect acquireTempRect2;
        Pools$Pool<Rect> pools$Pool;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        View view2 = layoutParams.mAnchorView;
        int i2 = 0;
        if (view2 == null && layoutParams.mAnchorId != -1) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        } else if (view2 != null) {
            acquireTempRect = acquireTempRect();
            acquireTempRect2 = acquireTempRect();
            try {
                getDescendantRect(view2, acquireTempRect);
                LayoutParams layoutParams2 = (LayoutParams) view.getLayoutParams();
                int measuredWidth = view.getMeasuredWidth();
                int measuredHeight = view.getMeasuredHeight();
                getDesiredAnchoredChildRectWithoutConstraints(i, acquireTempRect, acquireTempRect2, layoutParams2, measuredWidth, measuredHeight);
                constrainChildRect(layoutParams2, acquireTempRect2, measuredWidth, measuredHeight);
                view.layout(acquireTempRect2.left, acquireTempRect2.top, acquireTempRect2.right, acquireTempRect2.bottom);
            } finally {
                acquireTempRect.setEmpty();
                pools$Pool = sRectPool;
                pools$Pool.release(acquireTempRect);
                acquireTempRect2.setEmpty();
                pools$Pool.release(acquireTempRect2);
            }
        } else {
            int i3 = layoutParams.keyline;
            if (i3 >= 0) {
                LayoutParams layoutParams3 = (LayoutParams) view.getLayoutParams();
                int i4 = layoutParams3.gravity;
                if (i4 == 0) {
                    i4 = 8388661;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(i4, i);
                int i5 = absoluteGravity & 7;
                int i6 = absoluteGravity & 112;
                int width = getWidth();
                int height = getHeight();
                int measuredWidth2 = view.getMeasuredWidth();
                int measuredHeight2 = view.getMeasuredHeight();
                if (i == 1) {
                    i3 = width - i3;
                }
                int keyline = getKeyline(i3) - measuredWidth2;
                if (i5 == 1) {
                    keyline += measuredWidth2 / 2;
                } else if (i5 == 5) {
                    keyline += measuredWidth2;
                }
                if (i6 == 16) {
                    i2 = 0 + (measuredHeight2 / 2);
                } else if (i6 == 80) {
                    i2 = measuredHeight2 + 0;
                }
                int max = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams3).leftMargin, Math.min(keyline, ((width - getPaddingRight()) - measuredWidth2) - ((ViewGroup.MarginLayoutParams) layoutParams3).rightMargin));
                int max2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) layoutParams3).topMargin, Math.min(i2, ((height - getPaddingBottom()) - measuredHeight2) - ((ViewGroup.MarginLayoutParams) layoutParams3).bottomMargin));
                view.layout(max, max2, measuredWidth2 + max, measuredHeight2 + max2);
                return;
            }
            LayoutParams layoutParams4 = (LayoutParams) view.getLayoutParams();
            acquireTempRect = acquireTempRect();
            acquireTempRect.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams4).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) layoutParams4).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) layoutParams4).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) layoutParams4).bottomMargin);
            if (this.mLastInsets != null) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                    acquireTempRect.left = this.mLastInsets.getSystemWindowInsetLeft() + acquireTempRect.left;
                    acquireTempRect.top = this.mLastInsets.getSystemWindowInsetTop() + acquireTempRect.top;
                    acquireTempRect.right -= this.mLastInsets.getSystemWindowInsetRight();
                    acquireTempRect.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
                }
            }
            acquireTempRect2 = acquireTempRect();
            int i7 = layoutParams4.gravity;
            if ((i7 & 7) == 0) {
                i7 |= 8388611;
            }
            if ((i7 & 112) == 0) {
                i7 |= 48;
            }
            Gravity.apply(i7, view.getMeasuredWidth(), view.getMeasuredHeight(), acquireTempRect, acquireTempRect2, i);
            view.layout(acquireTempRect2.left, acquireTempRect2.top, acquireTempRect2.right, acquireTempRect2.bottom);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:79:0x0180, code lost:
        if (r0.onMeasureChild(r30, r20, r8, r21, r23, 0) == false) goto L_0x0190;
     */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0183  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instructions count: 494
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    public void onMeasureChild(View view, int i, int i2, int i3, int i4) {
        measureChildWithMargins(view, i, i2, i3, i4);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f, float f2, boolean z) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean z2 = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isNestedScrollAccepted(0) && (behavior = layoutParams.mBehavior) != null) {
                    z2 |= behavior.onNestedFling();
                }
            }
        }
        if (z2) {
            onChildViewsChanged(1);
        }
        return z2;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f, float f2) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean z = false;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isNestedScrollAccepted(0) && (behavior = layoutParams.mBehavior) != null) {
                    z |= behavior.onNestedPreFling(this, childAt, view, f, f2);
                }
            }
        }
        return z;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr) {
        onNestedPreScroll(view, i, i2, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i, int i2, int i3, int i4) {
        onNestedScroll(view, i, i2, i3, i4, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i) {
        onNestedScrollAccepted(view, view2, i, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        SparseArray<Parcelable> sparseArray = savedState.behaviorStates;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior behavior = getResolvedLayoutParams(childAt).mBehavior;
            if (!(id == -1 || behavior == null || (parcelable2 = sparseArray.get(id)) == null)) {
                behavior.onRestoreInstanceState(this, childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int id = childAt.getId();
            Behavior behavior = ((LayoutParams) childAt.getLayoutParams()).mBehavior;
            if (!(id == -1 || behavior == null || (onSaveInstanceState = behavior.onSaveInstanceState(this, childAt)) == null)) {
                sparseArray.append(id, onSaveInstanceState);
            }
        }
        savedState.behaviorStates = sparseArray;
        return savedState;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i) {
        return onStartNestedScroll(view, view2, i, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0012, code lost:
        if (r3 != false) goto L_0x0016;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004a  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            int r2 = r18.getActionMasked()
            android.view.View r3 = r0.mBehaviorTouchView
            r4 = 1
            r5 = 0
            if (r3 != 0) goto L_0x0015
            boolean r3 = r0.performIntercept(r1, r4)
            if (r3 == 0) goto L_0x0029
            goto L_0x0016
        L_0x0015:
            r3 = 0
        L_0x0016:
            android.view.View r6 = r0.mBehaviorTouchView
            android.view.ViewGroup$LayoutParams r6 = r6.getLayoutParams()
            androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r6
            androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r6 = r6.mBehavior
            if (r6 == 0) goto L_0x0029
            android.view.View r7 = r0.mBehaviorTouchView
            boolean r6 = r6.onTouchEvent(r0, r7, r1)
            goto L_0x002a
        L_0x0029:
            r6 = 0
        L_0x002a:
            android.view.View r7 = r0.mBehaviorTouchView
            r8 = 0
            if (r7 != 0) goto L_0x0035
            boolean r1 = super.onTouchEvent(r18)
            r6 = r6 | r1
            goto L_0x0048
        L_0x0035:
            if (r3 == 0) goto L_0x0048
            long r11 = android.os.SystemClock.uptimeMillis()
            r13 = 3
            r14 = 0
            r15 = 0
            r16 = 0
            r9 = r11
            android.view.MotionEvent r8 = android.view.MotionEvent.obtain(r9, r11, r13, r14, r15, r16)
            super.onTouchEvent(r8)
        L_0x0048:
            if (r8 == 0) goto L_0x004d
            r8.recycle()
        L_0x004d:
            if (r2 == r4) goto L_0x0052
            r1 = 3
            if (r2 != r1) goto L_0x0055
        L_0x0052:
            r0.resetTouchBehaviors(r5)
        L_0x0055:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean performIntercept(MotionEvent motionEvent, int i) {
        boolean z;
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.mTempList1;
        list.clear();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i2 = childCount - 1; i2 >= 0; i2--) {
            list.add(getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i2) : i2));
        }
        Comparator<View> comparator = TOP_SORTED_CHILDREN_COMPARATOR;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
        int size = list.size();
        MotionEvent motionEvent2 = null;
        boolean z2 = false;
        boolean z3 = false;
        for (int i3 = 0; i3 < size; i3++) {
            View view = list.get(i3);
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            Behavior behavior = layoutParams.mBehavior;
            boolean z4 = true;
            if (!(z2 || z3) || actionMasked == 0) {
                if (!z2 && behavior != null) {
                    if (i == 0) {
                        z2 = behavior.onInterceptTouchEvent(this, view, motionEvent);
                    } else if (i == 1) {
                        z2 = behavior.onTouchEvent(this, view, motionEvent);
                    }
                    if (z2) {
                        this.mBehaviorTouchView = view;
                    }
                }
                Behavior behavior2 = layoutParams.mBehavior;
                if (behavior2 == null) {
                    layoutParams.mDidBlockInteraction = false;
                }
                boolean z5 = layoutParams.mDidBlockInteraction;
                if (z5) {
                    z = true;
                } else {
                    z = (behavior2 != null && behavior2.getScrimOpacity() > 0.0f) | z5;
                    layoutParams.mDidBlockInteraction = z;
                }
                if (!z || z5) {
                    z4 = false;
                }
                if (z && !z4) {
                    break;
                }
                z3 = z4;
            } else if (behavior != null) {
                if (motionEvent2 == null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    motionEvent2 = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                }
                if (i == 0) {
                    behavior.onInterceptTouchEvent(this, view, motionEvent2);
                } else if (i == 1) {
                    behavior.onTouchEvent(this, view, motionEvent2);
                }
            }
        }
        list.clear();
        return z2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x0073, code lost:
        if (r5 != false) goto L_0x00ca;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:127:0x016a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void prepareChildren() {
        /*
            Method dump skipped, instructions count: 454
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.prepareChildren():void");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z) {
        Behavior behavior = ((LayoutParams) view.getLayoutParams()).mBehavior;
        if (behavior == null || !behavior.onRequestChildRectangleOnScreen(this, view, rect, z)) {
            return super.requestChildRectangleOnScreen(view, rect, z);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z) {
        super.requestDisallowInterceptTouchEvent(z);
        if (z && !this.mDisallowInterceptReset) {
            resetTouchBehaviors(false);
            this.mDisallowInterceptReset = true;
        }
    }

    public final void resetTouchBehaviors(boolean z) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            Behavior behavior = ((LayoutParams) childAt.getLayoutParams()).mBehavior;
            if (behavior != null) {
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 3, 0.0f, 0.0f, 0);
                if (z) {
                    behavior.onInterceptTouchEvent(this, childAt, obtain);
                } else {
                    behavior.onTouchEvent(this, childAt, obtain);
                }
                obtain.recycle();
            }
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            ((LayoutParams) getChildAt(i2).getLayoutParams()).mDidBlockInteraction = false;
        }
        this.mBehaviorTouchView = null;
        this.mDisallowInterceptReset = false;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z) {
        super.setFitsSystemWindows(z);
        setupForInsets();
    }

    public final void setInsetOffsetX(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i2 = layoutParams.mInsetOffsetX;
        if (i2 != i) {
            ViewCompat.offsetLeftAndRight(view, i - i2);
            layoutParams.mInsetOffsetX = i;
        }
    }

    public final void setInsetOffsetY(View view, int i) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int i2 = layoutParams.mInsetOffsetY;
        if (i2 != i) {
            ViewCompat.offsetTopAndBottom(view, i - i2);
            layoutParams.mInsetOffsetY = i;
        }
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.mStatusBarBackground;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.mStatusBarBackground = drawable3;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.mStatusBarBackground.setState(getDrawableState());
                }
                Drawable drawable4 = this.mStatusBarBackground;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                AppOpsManagerCompat.setLayoutDirection(drawable4, getLayoutDirection());
                this.mStatusBarBackground.setVisible(getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback(this);
            }
            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarBackgroundColor(int i) {
        setStatusBarBackground(new ColorDrawable(i));
    }

    public void setStatusBarBackgroundResource(int i) {
        Drawable drawable;
        if (i != 0) {
            Context context = getContext();
            Object obj = ContextCompat.sLock;
            drawable = ContextCompat.Api21Impl.getDrawable(context, i);
        } else {
            drawable = null;
        }
        setStatusBarBackground(drawable);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.mStatusBarBackground;
        if (drawable != null && drawable.isVisible() != z) {
            this.mStatusBarBackground.setVisible(z, false);
        }
    }

    public final void setupForInsets() {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (getFitsSystemWindows()) {
            if (this.mApplyWindowInsetsListener == null) {
                this.mApplyWindowInsetsListener = new OnApplyWindowInsetsListener() { // from class: androidx.coordinatorlayout.widget.CoordinatorLayout.1
                    @Override // androidx.core.view.OnApplyWindowInsetsListener
                    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                        Behavior behavior;
                        CoordinatorLayout coordinatorLayout = CoordinatorLayout.this;
                        if (!Objects.equals(coordinatorLayout.mLastInsets, windowInsetsCompat)) {
                            coordinatorLayout.mLastInsets = windowInsetsCompat;
                            boolean z = true;
                            boolean z2 = windowInsetsCompat.getSystemWindowInsetTop() > 0;
                            coordinatorLayout.mDrawStatusBarBackground = z2;
                            if (z2 || coordinatorLayout.getBackground() != null) {
                                z = false;
                            }
                            coordinatorLayout.setWillNotDraw(z);
                            if (!windowInsetsCompat.isConsumed()) {
                                int childCount = coordinatorLayout.getChildCount();
                                for (int i = 0; i < childCount; i++) {
                                    View childAt = coordinatorLayout.getChildAt(i);
                                    AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                                    if (childAt.getFitsSystemWindows() && (behavior = ((LayoutParams) childAt.getLayoutParams()).mBehavior) != null) {
                                        windowInsetsCompat = behavior.onApplyWindowInsets(windowInsetsCompat);
                                        if (windowInsetsCompat.isConsumed()) {
                                            break;
                                        }
                                    }
                                }
                            }
                            coordinatorLayout.requestLayout();
                        }
                        return windowInsetsCompat;
                    }
                };
            }
            ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(this, this.mApplyWindowInsetsListener);
            setSystemUiVisibility(1280);
            return;
        }
        ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(this, null);
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mStatusBarBackground;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedPreScroll(View view, int i, int i2, int[] iArr, int i3) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean z = false;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isNestedScrollAccepted(i3) && (behavior = layoutParams.mBehavior) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    behavior.onNestedPreScroll(this, childAt, view, i, i2, iArr2, i3);
                    int[] iArr3 = this.mBehaviorConsumed;
                    i4 = i > 0 ? Math.max(i4, iArr3[0]) : Math.min(i4, iArr3[0]);
                    int[] iArr4 = this.mBehaviorConsumed;
                    i5 = i2 > 0 ? Math.max(i5, iArr4[1]) : Math.min(i5, iArr4[1]);
                    z = true;
                }
            }
        }
        iArr[0] = i4;
        iArr[1] = i5;
        if (z) {
            onChildViewsChanged(1);
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5) {
        onNestedScroll(view, i, i2, i3, i4, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onNestedScrollAccepted(View view, View view2, int i, int i2) {
        Behavior behavior;
        NestedScrollingParentHelper nestedScrollingParentHelper = this.mNestedScrollingParentHelper;
        if (i2 == 1) {
            nestedScrollingParentHelper.mNestedScrollAxesNonTouch = i;
        } else {
            nestedScrollingParentHelper.mNestedScrollAxesTouch = i;
        }
        this.mNestedScrollingTarget = view2;
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
            if (layoutParams.isNestedScrollAccepted(i2) && (behavior = layoutParams.mBehavior) != null && i2 == 0) {
                behavior.onNestedScrollAccepted();
            }
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean onStartNestedScroll(View view, View view2, int i, int i2) {
        int childCount = getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                Behavior behavior = layoutParams.mBehavior;
                if (behavior != null) {
                    boolean onStartNestedScroll = behavior.onStartNestedScroll(this, childAt, view, view2, i, i2);
                    z |= onStartNestedScroll;
                    layoutParams.setNestedScrollAccepted(i2, onStartNestedScroll);
                } else {
                    layoutParams.setNestedScrollAccepted(i2, false);
                }
            }
        }
        return z;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void onStopNestedScroll(View view, int i) {
        NestedScrollingParentHelper nestedScrollingParentHelper = this.mNestedScrollingParentHelper;
        if (i == 1) {
            nestedScrollingParentHelper.mNestedScrollAxesNonTouch = 0;
        } else {
            nestedScrollingParentHelper.mNestedScrollAxesTouch = 0;
        }
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            if (layoutParams.isNestedScrollAccepted(i)) {
                Behavior behavior = layoutParams.mBehavior;
                if (behavior != null) {
                    behavior.onStopNestedScroll(this, childAt, view, i);
                }
                layoutParams.setNestedScrollAccepted(i, false);
                layoutParams.mDidChangeAfterNestedScroll = false;
            }
        }
        this.mNestedScrollingTarget = null;
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void onNestedScroll(View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        Behavior behavior;
        int childCount = getChildCount();
        boolean z = false;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isNestedScrollAccepted(i5) && (behavior = layoutParams.mBehavior) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    behavior.onNestedScroll(this, childAt, view, i, i2, i3, i4, i5, iArr2);
                    int[] iArr3 = this.mBehaviorConsumed;
                    i6 = i3 > 0 ? Math.max(i6, iArr3[0]) : Math.min(i6, iArr3[0]);
                    if (i4 > 0) {
                        i7 = Math.max(i7, this.mBehaviorConsumed[1]);
                    } else {
                        i7 = Math.min(i7, this.mBehaviorConsumed[1]);
                    }
                    z = true;
                }
            }
        }
        iArr[0] = iArr[0] + i6;
        iArr[1] = iArr[1] + i7;
        if (z) {
            onChildViewsChanged(1);
        }
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.coordinatorlayout.widget.CoordinatorLayout.SavedState.1
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
        public SparseArray<Parcelable> behaviorStates;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            int readInt = parcel.readInt();
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            Parcelable[] readParcelableArray = parcel.readParcelableArray(classLoader);
            this.behaviorStates = new SparseArray<>(readInt);
            for (int i = 0; i < readInt; i++) {
                this.behaviorStates.append(iArr[i], readParcelableArray[i]);
            }
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mSuperState, i);
            SparseArray<Parcelable> sparseArray = this.behaviorStates;
            int size = sparseArray != null ? sparseArray.size() : 0;
            parcel.writeInt(size);
            int[] iArr = new int[size];
            Parcelable[] parcelableArr = new Parcelable[size];
            for (int i2 = 0; i2 < size; i2++) {
                iArr[i2] = this.behaviorStates.keyAt(i2);
                parcelableArr[i2] = this.behaviorStates.valueAt(i2);
            }
            parcel.writeIntArray(iArr);
            parcel.writeParcelableArray(parcelableArr, i);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int anchorGravity;
        public int dodgeInsetEdges;
        public int gravity;
        public int insetEdge;
        public int keyline;
        public View mAnchorDirectChild;
        public int mAnchorId;
        public View mAnchorView;
        public Behavior mBehavior;
        public boolean mBehaviorResolved;
        public boolean mDidAcceptNestedScrollNonTouch;
        public boolean mDidAcceptNestedScrollTouch;
        public boolean mDidBlockInteraction;
        public boolean mDidChangeAfterNestedScroll;
        public int mInsetOffsetX;
        public int mInsetOffsetY;
        public final Rect mLastChildRect;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        public boolean isNestedScrollAccepted(int i) {
            if (i == 0) {
                return this.mDidAcceptNestedScrollTouch;
            }
            if (i != 1) {
                return false;
            }
            return this.mDidAcceptNestedScrollNonTouch;
        }

        public void setBehavior(Behavior behavior) {
            Behavior behavior2 = this.mBehavior;
            if (behavior2 != behavior) {
                if (behavior2 != null) {
                    behavior2.onDetachedFromLayoutParams();
                }
                this.mBehavior = behavior;
                this.mBehaviorResolved = true;
                if (behavior != null) {
                    behavior.onAttachedToLayoutParams(this);
                }
            }
        }

        public void setNestedScrollAccepted(int i, boolean z) {
            if (i == 0) {
                this.mDidAcceptNestedScrollTouch = z;
            } else if (i == 1) {
                this.mDidAcceptNestedScrollNonTouch = z;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            Behavior behavior;
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CoordinatorLayout_Layout);
            this.gravity = obtainStyledAttributes.getInteger(0, 0);
            this.mAnchorId = obtainStyledAttributes.getResourceId(1, -1);
            this.anchorGravity = obtainStyledAttributes.getInteger(2, 0);
            this.keyline = obtainStyledAttributes.getInteger(6, -1);
            this.insetEdge = obtainStyledAttributes.getInt(5, 0);
            this.dodgeInsetEdges = obtainStyledAttributes.getInt(4, 0);
            boolean hasValue = obtainStyledAttributes.hasValue(3);
            this.mBehaviorResolved = hasValue;
            if (hasValue) {
                String string = obtainStyledAttributes.getString(3);
                String str = CoordinatorLayout.WIDGET_PACKAGE_NAME;
                if (TextUtils.isEmpty(string)) {
                    behavior = null;
                } else {
                    if (string.startsWith(".")) {
                        string = context.getPackageName() + string;
                    } else if (string.indexOf(46) < 0) {
                        String str2 = CoordinatorLayout.WIDGET_PACKAGE_NAME;
                        if (!TextUtils.isEmpty(str2)) {
                            string = str2 + '.' + string;
                        }
                    }
                    try {
                        ThreadLocal<Map<String, Constructor<Behavior>>> threadLocal = CoordinatorLayout.sConstructors;
                        Map<String, Constructor<Behavior>> map = threadLocal.get();
                        if (map == null) {
                            map = new HashMap<>();
                            threadLocal.set(map);
                        }
                        Constructor<Behavior> constructor = map.get(string);
                        if (constructor == null) {
                            constructor = Class.forName(string, false, context.getClassLoader()).getConstructor(CoordinatorLayout.CONSTRUCTOR_PARAMS);
                            constructor.setAccessible(true);
                            map.put(string, constructor);
                        }
                        behavior = constructor.newInstance(context, attributeSet);
                    } catch (Exception e) {
                        throw new RuntimeException(GeneratedOutlineSupport.outline8("Could not inflate Behavior subclass ", string), e);
                    }
                }
                this.mBehavior = behavior;
            }
            obtainStyledAttributes.recycle();
            Behavior behavior2 = this.mBehavior;
            if (behavior2 != null) {
                behavior2.onAttachedToLayoutParams(this);
            }
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mBehaviorResolved = false;
            this.gravity = 0;
            this.anchorGravity = 0;
            this.keyline = -1;
            this.mAnchorId = -1;
            this.insetEdge = 0;
            this.dodgeInsetEdges = 0;
            this.mLastChildRect = new Rect();
        }
    }
}
