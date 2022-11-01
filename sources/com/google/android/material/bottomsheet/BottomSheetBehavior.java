package com.google.android.material.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener;
import com.google.android.material.internal.ViewUtils$RelativePadding;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public int activePointerId;
    public final ArrayList<BottomSheetCallback> callbacks;
    public int childHeight;
    public int collapsedOffset;
    public final ViewDragHelper.Callback dragCallback;
    public boolean draggable;
    public float elevation;
    public int expandHalfwayActionId;
    public int expandedOffset;
    public boolean fitToContents;
    public int fitToContentsOffset;
    public int gestureInsetBottom;
    public boolean gestureInsetBottomIgnored;
    public int halfExpandedOffset;
    public float halfExpandedRatio;
    public boolean hideable;
    public boolean ignoreEvents;
    public Map<View, Integer> importantForAccessibilityMap;
    public int initialY;
    public ValueAnimator interpolatorAnimator;
    public boolean isShapeExpanded;
    public int lastNestedScrollDy;
    public MaterialShapeDrawable materialShapeDrawable;
    public float maximumVelocity;
    public boolean nestedScrolled;
    public WeakReference<View> nestedScrollingChildRef;
    public int parentHeight;
    public int parentWidth;
    public int peekHeight;
    public boolean peekHeightAuto;
    public int peekHeightGestureInsetBuffer;
    public int peekHeightMin;
    public int saveFlags;
    public BottomSheetBehavior<V>.SettleRunnable settleRunnable;
    public ShapeAppearanceModel shapeAppearanceModelDefault;
    public boolean shapeThemingEnabled;
    public boolean skipCollapsed;
    public int state;
    public boolean touchingScrollingChild;
    public VelocityTracker velocityTracker;
    public ViewDragHelper viewDragHelper;
    public WeakReference<V> viewRef;

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass3 implements ViewUtils$OnApplyWindowInsetsListener {
        public AnonymousClass3() {
        }
    }

    /* renamed from: com.google.android.material.bottomsheet.BottomSheetBehavior$5  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass5 implements AccessibilityViewCommand {
        public final /* synthetic */ int val$state;

        public AnonymousClass5(int i) {
            this.val$state = i;
        }

        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
        public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
            BottomSheetBehavior.this.setState(this.val$state);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class BottomSheetCallback {
        public abstract void onSlide(View view, float f);

        public abstract void onStateChanged(View view, int i);
    }

    /* loaded from: classes.dex */
    public class SettleRunnable implements Runnable {
        public boolean isPosted;
        public int targetState;
        public final View view;

        public SettleRunnable(View view, int i) {
            this.view = view;
            this.targetState = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewDragHelper viewDragHelper = BottomSheetBehavior.this.viewDragHelper;
            if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                BottomSheetBehavior.this.setStateInternal(this.targetState);
            } else {
                View view = this.view;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                view.postOnAnimation(this);
            }
            this.isPosted = false;
        }
    }

    public BottomSheetBehavior() {
        this.saveFlags = 0;
        this.fitToContents = true;
        this.settleRunnable = null;
        this.halfExpandedRatio = 0.5f;
        this.elevation = -1.0f;
        this.draggable = true;
        this.state = 4;
        this.callbacks = new ArrayList<>();
        this.expandHalfwayActionId = -1;
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.4
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i, int i2) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i, int i2) {
                int expandedOffset = BottomSheetBehavior.this.getExpandedOffset();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return AppOpsManagerCompat.clamp(i, expandedOffset, bottomSheetBehavior.hideable ? bottomSheetBehavior.parentHeight : bottomSheetBehavior.collapsedOffset);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.hideable) {
                    return bottomSheetBehavior.parentHeight;
                }
                return bottomSheetBehavior.collapsedOffset;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i) {
                if (i == 1) {
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.draggable) {
                        bottomSheetBehavior.setStateInternal(1);
                    }
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
                BottomSheetBehavior.this.dispatchOnSlide(i2);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f, float f2) {
                int i;
                int i2 = 4;
                if (f2 < 0.0f) {
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.fitToContents) {
                        i = bottomSheetBehavior.fitToContentsOffset;
                    } else {
                        int top = view.getTop();
                        BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                        int i3 = bottomSheetBehavior2.halfExpandedOffset;
                        if (top > i3) {
                            i = i3;
                            i2 = 6;
                        } else {
                            i = bottomSheetBehavior2.expandedOffset;
                        }
                    }
                    i2 = 3;
                } else {
                    BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                    if (bottomSheetBehavior3.hideable && bottomSheetBehavior3.shouldHide(view, f2)) {
                        if (Math.abs(f) >= Math.abs(f2) || f2 <= 500.0f) {
                            int top2 = view.getTop();
                            BottomSheetBehavior bottomSheetBehavior4 = BottomSheetBehavior.this;
                            if (!(top2 > (bottomSheetBehavior4.getExpandedOffset() + bottomSheetBehavior4.parentHeight) / 2)) {
                                BottomSheetBehavior bottomSheetBehavior5 = BottomSheetBehavior.this;
                                if (bottomSheetBehavior5.fitToContents) {
                                    i = bottomSheetBehavior5.fitToContentsOffset;
                                } else if (Math.abs(view.getTop() - BottomSheetBehavior.this.expandedOffset) < Math.abs(view.getTop() - BottomSheetBehavior.this.halfExpandedOffset)) {
                                    i = BottomSheetBehavior.this.expandedOffset;
                                } else {
                                    i = BottomSheetBehavior.this.halfExpandedOffset;
                                    i2 = 6;
                                }
                                i2 = 3;
                            }
                        }
                        i = BottomSheetBehavior.this.parentHeight;
                        i2 = 5;
                    } else if (f2 == 0.0f || Math.abs(f) > Math.abs(f2)) {
                        int top3 = view.getTop();
                        BottomSheetBehavior bottomSheetBehavior6 = BottomSheetBehavior.this;
                        if (!bottomSheetBehavior6.fitToContents) {
                            int i4 = bottomSheetBehavior6.halfExpandedOffset;
                            if (top3 < i4) {
                                if (top3 < Math.abs(top3 - bottomSheetBehavior6.collapsedOffset)) {
                                    i = BottomSheetBehavior.this.expandedOffset;
                                    i2 = 3;
                                } else {
                                    i = BottomSheetBehavior.this.halfExpandedOffset;
                                }
                            } else if (Math.abs(top3 - i4) < Math.abs(top3 - BottomSheetBehavior.this.collapsedOffset)) {
                                i = BottomSheetBehavior.this.halfExpandedOffset;
                            } else {
                                i = BottomSheetBehavior.this.collapsedOffset;
                            }
                            i2 = 6;
                        } else if (Math.abs(top3 - bottomSheetBehavior6.fitToContentsOffset) < Math.abs(top3 - BottomSheetBehavior.this.collapsedOffset)) {
                            i = BottomSheetBehavior.this.fitToContentsOffset;
                            i2 = 3;
                        } else {
                            i = BottomSheetBehavior.this.collapsedOffset;
                        }
                    } else {
                        BottomSheetBehavior bottomSheetBehavior7 = BottomSheetBehavior.this;
                        if (bottomSheetBehavior7.fitToContents) {
                            i = bottomSheetBehavior7.collapsedOffset;
                        } else {
                            int top4 = view.getTop();
                            if (Math.abs(top4 - BottomSheetBehavior.this.halfExpandedOffset) < Math.abs(top4 - BottomSheetBehavior.this.collapsedOffset)) {
                                i = BottomSheetBehavior.this.halfExpandedOffset;
                                i2 = 6;
                            } else {
                                i = BottomSheetBehavior.this.collapsedOffset;
                            }
                        }
                    }
                }
                BottomSheetBehavior.this.startSettlingAnimation(view, i2, i, true);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i2 = bottomSheetBehavior.state;
                if (i2 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                    return false;
                }
                if (i2 == 3 && bottomSheetBehavior.activePointerId == i) {
                    WeakReference<View> weakReference = bottomSheetBehavior.nestedScrollingChildRef;
                    View view2 = weakReference != null ? weakReference.get() : null;
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                WeakReference<V> weakReference2 = BottomSheetBehavior.this.viewRef;
                return weakReference2 != null && weakReference2.get() == view;
            }
        };
    }

    public final void calculateCollapsedOffset() {
        int calculatePeekHeight = calculatePeekHeight();
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - calculatePeekHeight, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - calculatePeekHeight;
        }
    }

    public final int calculatePeekHeight() {
        int i;
        if (this.peekHeightAuto) {
            return Math.min(Math.max(this.peekHeightMin, this.parentHeight - ((this.parentWidth * 9) / 16)), this.childHeight);
        }
        if (this.gestureInsetBottomIgnored || (i = this.gestureInsetBottom) <= 0) {
            return this.peekHeight;
        }
        return Math.max(this.peekHeight, i + this.peekHeightGestureInsetBuffer);
    }

    public final void createMaterialShapeDrawable(Context context, AttributeSet attributeSet, boolean z, ColorStateList colorStateList) {
        if (this.shapeThemingEnabled) {
            this.shapeAppearanceModelDefault = ShapeAppearanceModel.builder(context, attributeSet, R.attr.bottomSheetStyle, 2131886708, new AbsoluteCornerSize(0)).build();
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModelDefault);
            this.materialShapeDrawable = materialShapeDrawable;
            materialShapeDrawable.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context);
            materialShapeDrawable.updateZ();
            if (!z || colorStateList == null) {
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16842801, typedValue, true);
                this.materialShapeDrawable.setTint(typedValue.data);
                return;
            }
            this.materialShapeDrawable.setFillColor(colorStateList);
        }
    }

    public void dispatchOnSlide(int i) {
        float f;
        float f2;
        V v = this.viewRef.get();
        if (!(v == null || this.callbacks.isEmpty())) {
            int i2 = this.collapsedOffset;
            if (i > i2 || i2 == getExpandedOffset()) {
                int i3 = this.collapsedOffset;
                f = i3 - i;
                f2 = this.parentHeight - i3;
            } else {
                int i4 = this.collapsedOffset;
                f = i4 - i;
                f2 = i4 - getExpandedOffset();
            }
            float f3 = f / f2;
            for (int i5 = 0; i5 < this.callbacks.size(); i5++) {
                this.callbacks.get(i5).onSlide(v, f3);
            }
        }
    }

    public View findScrollingChild(View view) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (view.isNestedScrollingEnabled()) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View findScrollingChild = findScrollingChild(viewGroup.getChildAt(i));
            if (findScrollingChild != null) {
                return findScrollingChild;
            }
        }
        return null;
    }

    public int getExpandedOffset() {
        return this.fitToContents ? this.fitToContentsOffset : this.expandedOffset;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        ViewDragHelper viewDragHelper;
        if (!v.isShown() || !this.draggable) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        View view = null;
        if (actionMasked == 0) {
            this.activePointerId = -1;
            VelocityTracker velocityTracker = this.velocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.velocityTracker = null;
            }
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x = (int) motionEvent.getX();
            this.initialY = (int) motionEvent.getY();
            if (this.state != 2) {
                WeakReference<View> weakReference = this.nestedScrollingChildRef;
                View view2 = weakReference != null ? weakReference.get() : null;
                if (view2 != null && coordinatorLayout.isPointInChildBounds(view2, x, this.initialY)) {
                    this.activePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.touchingScrollingChild = true;
                }
            }
            this.ignoreEvents = this.activePointerId == -1 && !coordinatorLayout.isPointInChildBounds(v, x, this.initialY);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.touchingScrollingChild = false;
            this.activePointerId = -1;
            if (this.ignoreEvents) {
                this.ignoreEvents = false;
                return false;
            }
        }
        if (!this.ignoreEvents && (viewDragHelper = this.viewDragHelper) != null && viewDragHelper.shouldInterceptTouchEvent(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.nestedScrollingChildRef;
        if (weakReference2 != null) {
            view = weakReference2.get();
        }
        return actionMasked == 2 && view != null && !this.ignoreEvents && this.state != 1 && !coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY()) && this.viewDragHelper != null && Math.abs(((float) this.initialY) - motionEvent.getY()) > ((float) this.viewDragHelper.mTouchSlop);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        MaterialShapeDrawable materialShapeDrawable;
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        if (coordinatorLayout.getFitsSystemWindows() && !v.getFitsSystemWindows()) {
            v.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            this.peekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            if (Build.VERSION.SDK_INT >= 29 && !this.gestureInsetBottomIgnored && !this.peekHeightAuto) {
                final AnonymousClass3 r0 = new AnonymousClass3();
                final ViewUtils$RelativePadding viewUtils$RelativePadding = new ViewUtils$RelativePadding(v.getPaddingStart(), v.getPaddingTop(), v.getPaddingEnd(), v.getPaddingBottom());
                ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(v, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.internal.ViewUtils$3
                    @Override // androidx.core.view.OnApplyWindowInsetsListener
                    public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                        ViewUtils$OnApplyWindowInsetsListener viewUtils$OnApplyWindowInsetsListener = ViewUtils$OnApplyWindowInsetsListener.this;
                        int i2 = viewUtils$RelativePadding.start;
                        BottomSheetBehavior.AnonymousClass3 r3 = (BottomSheetBehavior.AnonymousClass3) viewUtils$OnApplyWindowInsetsListener;
                        BottomSheetBehavior.this.gestureInsetBottom = windowInsetsCompat.mImpl.getMandatorySystemGestureInsets().bottom;
                        BottomSheetBehavior.this.updatePeekHeight(false);
                        return windowInsetsCompat;
                    }
                });
                if (v.isAttachedToWindow()) {
                    v.requestApplyInsets();
                } else {
                    v.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.google.android.material.internal.ViewUtils$4
                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewAttachedToWindow(View view) {
                            view.removeOnAttachStateChangeListener(this);
                            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                            view.requestApplyInsets();
                        }

                        @Override // android.view.View.OnAttachStateChangeListener
                        public void onViewDetachedFromWindow(View view) {
                        }
                    });
                }
            }
            this.viewRef = new WeakReference<>(v);
            if (this.shapeThemingEnabled && (materialShapeDrawable = this.materialShapeDrawable) != null) {
                v.setBackground(materialShapeDrawable);
            }
            MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
            if (materialShapeDrawable2 != null) {
                float f = this.elevation;
                if (f == -1.0f) {
                    f = v.getElevation();
                }
                materialShapeDrawable2.setElevation(f);
                boolean z = this.state == 3;
                this.isShapeExpanded = z;
                this.materialShapeDrawable.setInterpolation(z ? 0.0f : 1.0f);
            }
            updateAccessibilityActions();
            if (v.getImportantForAccessibility() == 0) {
                v.setImportantForAccessibility(1);
            }
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = new ViewDragHelper(coordinatorLayout.getContext(), coordinatorLayout, this.dragCallback);
        }
        int top = v.getTop();
        coordinatorLayout.onLayoutChild(v, i);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentHeight = coordinatorLayout.getHeight();
        int height = v.getHeight();
        this.childHeight = height;
        this.fitToContentsOffset = Math.max(0, this.parentHeight - height);
        this.halfExpandedOffset = (int) ((1.0f - this.halfExpandedRatio) * this.parentHeight);
        calculateCollapsedOffset();
        int i2 = this.state;
        if (i2 == 3) {
            ViewCompat.offsetTopAndBottom(v, getExpandedOffset());
        } else if (i2 == 6) {
            ViewCompat.offsetTopAndBottom(v, this.halfExpandedOffset);
        } else if (this.hideable && i2 == 5) {
            ViewCompat.offsetTopAndBottom(v, this.parentHeight);
        } else if (i2 == 4) {
            ViewCompat.offsetTopAndBottom(v, this.collapsedOffset);
        } else if (i2 == 1 || i2 == 2) {
            ViewCompat.offsetTopAndBottom(v, top - v.getTop());
        }
        this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild(v));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v, View view, float f, float f2) {
        WeakReference<View> weakReference = this.nestedScrollingChildRef;
        return (weakReference == null || view != weakReference.get() || this.state == 3) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int[] iArr, int i3) {
        if (i3 != 1) {
            WeakReference<View> weakReference = this.nestedScrollingChildRef;
            if (view == (weakReference != null ? weakReference.get() : null)) {
                int top = v.getTop();
                int i4 = top - i2;
                if (i2 > 0) {
                    if (i4 < getExpandedOffset()) {
                        iArr[1] = top - getExpandedOffset();
                        ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                        setStateInternal(3);
                    } else if (this.draggable) {
                        iArr[1] = i2;
                        ViewCompat.offsetTopAndBottom(v, -i2);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                } else if (i2 < 0 && !view.canScrollVertically(-1)) {
                    int i5 = this.collapsedOffset;
                    if (i4 > i5 && !this.hideable) {
                        iArr[1] = top - i5;
                        ViewCompat.offsetTopAndBottom(v, -iArr[1]);
                        setStateInternal(4);
                    } else if (this.draggable) {
                        iArr[1] = i2;
                        ViewCompat.offsetTopAndBottom(v, -i2);
                        setStateInternal(1);
                    } else {
                        return;
                    }
                }
                dispatchOnSlide(v.getTop());
                this.lastNestedScrollDy = i2;
                this.nestedScrolled = true;
            }
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i, int i2, int i3, int i4, int i5, int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        int i = this.saveFlags;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.peekHeight = savedState.peekHeight;
            }
            if (i == -1 || (i & 2) == 2) {
                this.fitToContents = savedState.fitToContents;
            }
            if (i == -1 || (i & 4) == 4) {
                this.hideable = savedState.hideable;
            }
            if (i == -1 || (i & 8) == 8) {
                this.skipCollapsed = savedState.skipCollapsed;
            }
        }
        int i2 = savedState.state;
        if (i2 == 1 || i2 == 2) {
            this.state = 4;
        } else {
            this.state = i2;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v) {
        return new SavedState((Parcelable) View.BaseSavedState.EMPTY_STATE, (BottomSheetBehavior<?>) this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, View view2, int i, int i2) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        return (i & 2) != 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V v, View view, int i) {
        int i2;
        float f;
        int i3 = 3;
        if (v.getTop() == getExpandedOffset()) {
            setStateInternal(3);
            return;
        }
        WeakReference<View> weakReference = this.nestedScrollingChildRef;
        if (weakReference != null && view == weakReference.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy > 0) {
                if (this.fitToContents) {
                    i2 = this.fitToContentsOffset;
                } else {
                    int top = v.getTop();
                    int i4 = this.halfExpandedOffset;
                    if (top > i4) {
                        i2 = i4;
                        i3 = 6;
                    } else {
                        i2 = this.expandedOffset;
                    }
                }
                startSettlingAnimation(v, i3, i2, false);
                this.nestedScrolled = false;
            }
            if (this.hideable) {
                VelocityTracker velocityTracker = this.velocityTracker;
                if (velocityTracker == null) {
                    f = 0.0f;
                } else {
                    velocityTracker.computeCurrentVelocity(RNCWebViewManager.COMMAND_CLEAR_FORM_DATA, this.maximumVelocity);
                    f = this.velocityTracker.getYVelocity(this.activePointerId);
                }
                if (shouldHide(v, f)) {
                    i2 = this.parentHeight;
                    i3 = 5;
                    startSettlingAnimation(v, i3, i2, false);
                    this.nestedScrolled = false;
                }
            }
            if (this.lastNestedScrollDy == 0) {
                int top2 = v.getTop();
                if (!this.fitToContents) {
                    int i5 = this.halfExpandedOffset;
                    if (top2 < i5) {
                        if (top2 < Math.abs(top2 - this.collapsedOffset)) {
                            i2 = this.expandedOffset;
                        } else {
                            i2 = this.halfExpandedOffset;
                        }
                    } else if (Math.abs(top2 - i5) < Math.abs(top2 - this.collapsedOffset)) {
                        i2 = this.halfExpandedOffset;
                    } else {
                        i2 = this.collapsedOffset;
                        i3 = 4;
                    }
                    i3 = 6;
                } else if (Math.abs(top2 - this.fitToContentsOffset) < Math.abs(top2 - this.collapsedOffset)) {
                    i2 = this.fitToContentsOffset;
                } else {
                    i2 = this.collapsedOffset;
                    i3 = 4;
                }
            } else {
                if (this.fitToContents) {
                    i2 = this.collapsedOffset;
                } else {
                    int top3 = v.getTop();
                    if (Math.abs(top3 - this.halfExpandedOffset) < Math.abs(top3 - this.collapsedOffset)) {
                        i2 = this.halfExpandedOffset;
                        i3 = 6;
                    } else {
                        i2 = this.collapsedOffset;
                    }
                }
                i3 = 4;
            }
            startSettlingAnimation(v, i3, i2, false);
            this.nestedScrolled = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (!v.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (viewDragHelper != null) {
            viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            this.activePointerId = -1;
            VelocityTracker velocityTracker = this.velocityTracker;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.velocityTracker = null;
            }
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (this.viewDragHelper != null && actionMasked == 2 && !this.ignoreEvents) {
            float abs = Math.abs(this.initialY - motionEvent.getY());
            ViewDragHelper viewDragHelper2 = this.viewDragHelper;
            if (abs > viewDragHelper2.mTouchSlop) {
                viewDragHelper2.captureChildView(v, motionEvent.getPointerId(motionEvent.getActionIndex()));
            }
        }
        return !this.ignoreEvents;
    }

    public final void replaceAccessibilityActionForState(V v, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i) {
        ViewCompat.replaceAccessibilityAction(v, accessibilityActionCompat, null, new AnonymousClass5(i));
    }

    public void setHalfExpandedRatio(float f) {
        if (f <= 0.0f || f >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.halfExpandedRatio = f;
        if (this.viewRef != null) {
            this.halfExpandedOffset = (int) ((1.0f - f) * this.parentHeight);
        }
    }

    public void setHideable(boolean z) {
        if (this.hideable != z) {
            this.hideable = z;
            if (!z && this.state == 5) {
                setState(4);
            }
            updateAccessibilityActions();
        }
    }

    public void setPeekHeight(int i) {
        boolean z = true;
        if (i == -1) {
            if (!this.peekHeightAuto) {
                this.peekHeightAuto = true;
            }
            z = false;
        } else {
            if (this.peekHeightAuto || this.peekHeight != i) {
                this.peekHeightAuto = false;
                this.peekHeight = Math.max(0, i);
            }
            z = false;
        }
        if (z) {
            updatePeekHeight(false);
        }
    }

    public void setState(int i) {
        if (i != this.state) {
            if (this.viewRef != null) {
                settleToStatePendingLayout(i);
            } else if (i == 4 || i == 3 || i == 6 || (this.hideable && i == 5)) {
                this.state = i;
            }
        }
    }

    public void setStateInternal(int i) {
        V v;
        if (this.state != i) {
            this.state = i;
            WeakReference<V> weakReference = this.viewRef;
            if (!(weakReference == null || (v = weakReference.get()) == null)) {
                if (i == 3) {
                    updateImportantForAccessibility(true);
                } else if (i == 6 || i == 5 || i == 4) {
                    updateImportantForAccessibility(false);
                }
                updateDrawableForTargetState(i);
                for (int i2 = 0; i2 < this.callbacks.size(); i2++) {
                    this.callbacks.get(i2).onStateChanged(v, i);
                }
                updateAccessibilityActions();
            }
        }
    }

    public void settleToState(View view, int i) {
        int i2;
        int i3;
        if (i == 4) {
            i2 = this.collapsedOffset;
        } else if (i == 6) {
            i2 = this.halfExpandedOffset;
            if (this.fitToContents && i2 <= (i3 = this.fitToContentsOffset)) {
                i = 3;
                i2 = i3;
            }
        } else if (i == 3) {
            i2 = getExpandedOffset();
        } else if (!this.hideable || i != 5) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Illegal state argument: ", i));
        } else {
            i2 = this.parentHeight;
        }
        startSettlingAnimation(view, i, i2, false);
    }

    public final void settleToStatePendingLayout(final int i) {
        final V v = this.viewRef.get();
        if (v != null) {
            ViewParent parent = v.getParent();
            if (parent != null && parent.isLayoutRequested()) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (v.isAttachedToWindow()) {
                    v.post(new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BottomSheetBehavior.this.settleToState(v, i);
                        }
                    });
                    return;
                }
            }
            settleToState(v, i);
        }
    }

    public boolean shouldHide(View view, float f) {
        if (this.skipCollapsed) {
            return true;
        }
        if (view.getTop() < this.collapsedOffset) {
            return false;
        }
        return Math.abs(((f * 0.1f) + ((float) view.getTop())) - ((float) this.collapsedOffset)) / ((float) calculatePeekHeight()) > 0.5f;
    }

    public void startSettlingAnimation(View view, int i, int i2, boolean z) {
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (viewDragHelper != null && (!z ? viewDragHelper.smoothSlideViewTo(view, view.getLeft(), i2) : viewDragHelper.settleCapturedViewAt(view.getLeft(), i2))) {
            setStateInternal(2);
            updateDrawableForTargetState(i);
            if (this.settleRunnable == null) {
                this.settleRunnable = new SettleRunnable(view, i);
            }
            BottomSheetBehavior<V>.SettleRunnable settleRunnable = this.settleRunnable;
            if (!settleRunnable.isPosted) {
                settleRunnable.targetState = i;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                view.postOnAnimation(settleRunnable);
                this.settleRunnable.isPosted = true;
                return;
            }
            settleRunnable.targetState = i;
            return;
        }
        setStateInternal(i);
    }

    public final void updateAccessibilityActions() {
        V v;
        int i;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null && (v = weakReference.get()) != null) {
            ViewCompat.removeActionWithId(524288, v);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(v, 0);
            ViewCompat.removeActionWithId(262144, v);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(v, 0);
            ViewCompat.removeActionWithId(1048576, v);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(v, 0);
            int i2 = this.expandHalfwayActionId;
            if (i2 != -1) {
                ViewCompat.removeActionWithId(i2, v);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(v, 0);
            }
            int i3 = 6;
            if (this.state != 6) {
                String string = v.getResources().getString(R.string.bottomsheet_action_expand_halfway);
                AnonymousClass5 r10 = new AnonymousClass5(6);
                List<AccessibilityNodeInfoCompat.AccessibilityActionCompat> actionList = ViewCompat.getActionList(v);
                int i4 = 0;
                while (true) {
                    if (i4 >= actionList.size()) {
                        int i5 = -1;
                        int i6 = 0;
                        while (true) {
                            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
                            if (i6 >= iArr.length || i5 != -1) {
                                break;
                            }
                            int i7 = iArr[i6];
                            boolean z = true;
                            for (int i8 = 0; i8 < actionList.size(); i8++) {
                                z &= actionList.get(i8).getId() != i7;
                            }
                            if (z) {
                                i5 = i7;
                            }
                            i6++;
                        }
                        i = i5;
                    } else if (TextUtils.equals(string, actionList.get(i4).getLabel())) {
                        i = actionList.get(i4).getId();
                        break;
                    } else {
                        i4++;
                    }
                }
                if (i != -1) {
                    ViewCompat.addAccessibilityAction(v, new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, i, string, r10, null));
                }
                this.expandHalfwayActionId = i;
            }
            if (this.hideable && this.state != 5) {
                replaceAccessibilityActionForState(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
            }
            int i9 = this.state;
            if (i9 == 3) {
                if (this.fitToContents) {
                    i3 = 4;
                }
                replaceAccessibilityActionForState(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, i3);
            } else if (i9 == 4) {
                if (this.fitToContents) {
                    i3 = 3;
                }
                replaceAccessibilityActionForState(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, i3);
            } else if (i9 == 6) {
                replaceAccessibilityActionForState(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 4);
                replaceAccessibilityActionForState(v, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
            }
        }
    }

    public final void updateDrawableForTargetState(int i) {
        ValueAnimator valueAnimator;
        if (i != 2) {
            boolean z = i == 3;
            if (this.isShapeExpanded != z) {
                this.isShapeExpanded = z;
                if (this.materialShapeDrawable != null && (valueAnimator = this.interpolatorAnimator) != null) {
                    if (valueAnimator.isRunning()) {
                        this.interpolatorAnimator.reverse();
                        return;
                    }
                    float f = z ? 0.0f : 1.0f;
                    this.interpolatorAnimator.setFloatValues(1.0f - f, f);
                    this.interpolatorAnimator.start();
                }
            }
        }
    }

    public final void updateImportantForAccessibility(boolean z) {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            ViewParent parent = weakReference.get().getParent();
            if (parent instanceof CoordinatorLayout) {
                CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
                int childCount = coordinatorLayout.getChildCount();
                if (z) {
                    if (this.importantForAccessibilityMap == null) {
                        this.importantForAccessibilityMap = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i = 0; i < childCount; i++) {
                    View childAt = coordinatorLayout.getChildAt(i);
                    if (childAt != this.viewRef.get() && z) {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    }
                }
                if (!z) {
                    this.importantForAccessibilityMap = null;
                }
            }
        }
    }

    public final void updatePeekHeight(boolean z) {
        V v;
        if (this.viewRef != null) {
            calculateCollapsedOffset();
            if (this.state == 4 && (v = this.viewRef.get()) != null) {
                if (z) {
                    settleToStatePendingLayout(this.state);
                } else {
                    v.requestLayout();
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.SavedState.1
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
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        public boolean fitToContents;
        public boolean hideable;
        public int peekHeight;
        public boolean skipCollapsed;
        public final int state;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
            this.peekHeight = parcel.readInt();
            boolean z = false;
            this.fitToContents = parcel.readInt() == 1;
            this.hideable = parcel.readInt() == 1;
            this.skipCollapsed = parcel.readInt() == 1 ? true : z;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mSuperState, i);
            parcel.writeInt(this.state);
            parcel.writeInt(this.peekHeight);
            parcel.writeInt(this.fitToContents ? 1 : 0);
            parcel.writeInt(this.hideable ? 1 : 0);
            parcel.writeInt(this.skipCollapsed ? 1 : 0);
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.state = bottomSheetBehavior.state;
            this.peekHeight = bottomSheetBehavior.peekHeight;
            this.fitToContents = bottomSheetBehavior.fitToContents;
            this.hideable = bottomSheetBehavior.hideable;
            this.skipCollapsed = bottomSheetBehavior.skipCollapsed;
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i;
        this.saveFlags = 0;
        this.fitToContents = true;
        this.settleRunnable = null;
        this.halfExpandedRatio = 0.5f;
        this.elevation = -1.0f;
        this.draggable = true;
        this.state = 4;
        this.callbacks = new ArrayList<>();
        this.expandHalfwayActionId = -1;
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.4
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View view, int i2, int i22) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View view, int i2, int i22) {
                int expandedOffset = BottomSheetBehavior.this.getExpandedOffset();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return AppOpsManagerCompat.clamp(i2, expandedOffset, bottomSheetBehavior.hideable ? bottomSheetBehavior.parentHeight : bottomSheetBehavior.collapsedOffset);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View view) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                if (bottomSheetBehavior.hideable) {
                    return bottomSheetBehavior.parentHeight;
                }
                return bottomSheetBehavior.collapsedOffset;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int i2) {
                if (i2 == 1) {
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.draggable) {
                        bottomSheetBehavior.setStateInternal(1);
                    }
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View view, int i2, int i22, int i3, int i4) {
                BottomSheetBehavior.this.dispatchOnSlide(i22);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View view, float f, float f2) {
                int i2;
                int i22 = 4;
                if (f2 < 0.0f) {
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.fitToContents) {
                        i2 = bottomSheetBehavior.fitToContentsOffset;
                    } else {
                        int top = view.getTop();
                        BottomSheetBehavior bottomSheetBehavior2 = BottomSheetBehavior.this;
                        int i3 = bottomSheetBehavior2.halfExpandedOffset;
                        if (top > i3) {
                            i2 = i3;
                            i22 = 6;
                        } else {
                            i2 = bottomSheetBehavior2.expandedOffset;
                        }
                    }
                    i22 = 3;
                } else {
                    BottomSheetBehavior bottomSheetBehavior3 = BottomSheetBehavior.this;
                    if (bottomSheetBehavior3.hideable && bottomSheetBehavior3.shouldHide(view, f2)) {
                        if (Math.abs(f) >= Math.abs(f2) || f2 <= 500.0f) {
                            int top2 = view.getTop();
                            BottomSheetBehavior bottomSheetBehavior4 = BottomSheetBehavior.this;
                            if (!(top2 > (bottomSheetBehavior4.getExpandedOffset() + bottomSheetBehavior4.parentHeight) / 2)) {
                                BottomSheetBehavior bottomSheetBehavior5 = BottomSheetBehavior.this;
                                if (bottomSheetBehavior5.fitToContents) {
                                    i2 = bottomSheetBehavior5.fitToContentsOffset;
                                } else if (Math.abs(view.getTop() - BottomSheetBehavior.this.expandedOffset) < Math.abs(view.getTop() - BottomSheetBehavior.this.halfExpandedOffset)) {
                                    i2 = BottomSheetBehavior.this.expandedOffset;
                                } else {
                                    i2 = BottomSheetBehavior.this.halfExpandedOffset;
                                    i22 = 6;
                                }
                                i22 = 3;
                            }
                        }
                        i2 = BottomSheetBehavior.this.parentHeight;
                        i22 = 5;
                    } else if (f2 == 0.0f || Math.abs(f) > Math.abs(f2)) {
                        int top3 = view.getTop();
                        BottomSheetBehavior bottomSheetBehavior6 = BottomSheetBehavior.this;
                        if (!bottomSheetBehavior6.fitToContents) {
                            int i4 = bottomSheetBehavior6.halfExpandedOffset;
                            if (top3 < i4) {
                                if (top3 < Math.abs(top3 - bottomSheetBehavior6.collapsedOffset)) {
                                    i2 = BottomSheetBehavior.this.expandedOffset;
                                    i22 = 3;
                                } else {
                                    i2 = BottomSheetBehavior.this.halfExpandedOffset;
                                }
                            } else if (Math.abs(top3 - i4) < Math.abs(top3 - BottomSheetBehavior.this.collapsedOffset)) {
                                i2 = BottomSheetBehavior.this.halfExpandedOffset;
                            } else {
                                i2 = BottomSheetBehavior.this.collapsedOffset;
                            }
                            i22 = 6;
                        } else if (Math.abs(top3 - bottomSheetBehavior6.fitToContentsOffset) < Math.abs(top3 - BottomSheetBehavior.this.collapsedOffset)) {
                            i2 = BottomSheetBehavior.this.fitToContentsOffset;
                            i22 = 3;
                        } else {
                            i2 = BottomSheetBehavior.this.collapsedOffset;
                        }
                    } else {
                        BottomSheetBehavior bottomSheetBehavior7 = BottomSheetBehavior.this;
                        if (bottomSheetBehavior7.fitToContents) {
                            i2 = bottomSheetBehavior7.collapsedOffset;
                        } else {
                            int top4 = view.getTop();
                            if (Math.abs(top4 - BottomSheetBehavior.this.halfExpandedOffset) < Math.abs(top4 - BottomSheetBehavior.this.collapsedOffset)) {
                                i2 = BottomSheetBehavior.this.halfExpandedOffset;
                                i22 = 6;
                            } else {
                                i2 = BottomSheetBehavior.this.collapsedOffset;
                            }
                        }
                    }
                }
                BottomSheetBehavior.this.startSettlingAnimation(view, i22, i2, true);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View view, int i2) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i22 = bottomSheetBehavior.state;
                if (i22 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                    return false;
                }
                if (i22 == 3 && bottomSheetBehavior.activePointerId == i2) {
                    WeakReference<View> weakReference = bottomSheetBehavior.nestedScrollingChildRef;
                    View view2 = weakReference != null ? weakReference.get() : null;
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                WeakReference<V> weakReference2 = BottomSheetBehavior.this.viewRef;
                return weakReference2 != null && weakReference2.get() == view;
            }
        };
        this.peekHeightGestureInsetBuffer = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BottomSheetBehavior_Layout);
        this.shapeThemingEnabled = obtainStyledAttributes.hasValue(11);
        boolean hasValue = obtainStyledAttributes.hasValue(1);
        if (hasValue) {
            createMaterialShapeDrawable(context, attributeSet, hasValue, R$style.getColorStateList(context, obtainStyledAttributes, 1));
        } else {
            createMaterialShapeDrawable(context, attributeSet, hasValue, null);
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.interpolatorAnimator = ofFloat;
        ofFloat.setDuration(500L);
        this.interpolatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                MaterialShapeDrawable materialShapeDrawable = BottomSheetBehavior.this.materialShapeDrawable;
                if (materialShapeDrawable != null) {
                    materialShapeDrawable.setInterpolation(floatValue);
                }
            }
        });
        this.elevation = obtainStyledAttributes.getDimension(0, -1.0f);
        TypedValue peekValue = obtainStyledAttributes.peekValue(7);
        if (peekValue == null || (i = peekValue.data) != -1) {
            setPeekHeight(obtainStyledAttributes.getDimensionPixelSize(7, -1));
        } else {
            setPeekHeight(i);
        }
        setHideable(obtainStyledAttributes.getBoolean(6, false));
        this.gestureInsetBottomIgnored = obtainStyledAttributes.getBoolean(10, false);
        boolean z = obtainStyledAttributes.getBoolean(4, true);
        if (this.fitToContents != z) {
            this.fitToContents = z;
            if (this.viewRef != null) {
                calculateCollapsedOffset();
            }
            setStateInternal((!this.fitToContents || this.state != 6) ? this.state : 3);
            updateAccessibilityActions();
        }
        this.skipCollapsed = obtainStyledAttributes.getBoolean(9, false);
        this.draggable = obtainStyledAttributes.getBoolean(2, true);
        this.saveFlags = obtainStyledAttributes.getInt(8, 0);
        setHalfExpandedRatio(obtainStyledAttributes.getFloat(5, 0.5f));
        TypedValue peekValue2 = obtainStyledAttributes.peekValue(3);
        if (peekValue2 == null || peekValue2.type != 16) {
            int dimensionPixelOffset = obtainStyledAttributes.getDimensionPixelOffset(3, 0);
            if (dimensionPixelOffset >= 0) {
                this.expandedOffset = dimensionPixelOffset;
            } else {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
        } else {
            int i2 = peekValue2.data;
            if (i2 >= 0) {
                this.expandedOffset = i2;
            } else {
                throw new IllegalArgumentException("offset must be greater than or equal to 0");
            }
        }
        obtainStyledAttributes.recycle();
        this.maximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
