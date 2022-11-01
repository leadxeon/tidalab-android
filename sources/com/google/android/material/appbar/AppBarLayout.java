package com.google.android.material.appbar;

import android.animation.AnimatorInflater;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.NestedScrollingChild;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements CoordinatorLayout.AttachedBehavior {
    public int currentOffset;
    public ValueAnimator elevationOverlayAnimator;
    public boolean haveChildWithInterpolator;
    public WindowInsetsCompat lastInsets;
    public boolean liftOnScroll;
    public WeakReference<View> liftOnScrollTargetView;
    public int liftOnScrollTargetViewId;
    public boolean liftable;
    public boolean lifted;
    public Drawable statusBarForeground;
    public int[] tmpStatesArray;
    public int totalScrollRange = -1;
    public int downPreScrollRange = -1;
    public int downScrollRange = -1;
    public int pendingAction = 0;

    /* loaded from: classes.dex */
    public static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        public WeakReference<View> lastNestedScrollingChildRef;
        public int lastStartedType;
        public ValueAnimator offsetAnimator;
        public int offsetDelta;
        public int offsetToChildIndexOnLayout = -1;
        public boolean offsetToChildIndexOnLayoutIsMinHeight;
        public float offsetToChildIndexOnLayoutPerc;

        public BaseBehavior() {
        }

        public static boolean checkFlag(int i, int i2) {
            return (i & i2) == i2;
        }

        public final void animateOffsetTo(final CoordinatorLayout coordinatorLayout, final T t, int i, float f) {
            int i2;
            int abs = Math.abs(getTopBottomOffsetForScrollingSibling() - i);
            float abs2 = Math.abs(f);
            if (abs2 > 0.0f) {
                i2 = Math.round((abs / abs2) * 1000.0f) * 3;
            } else {
                i2 = (int) (((abs / t.getHeight()) + 1.0f) * 150.0f);
            }
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            if (topBottomOffsetForScrollingSibling == i) {
                ValueAnimator valueAnimator = this.offsetAnimator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.offsetAnimator.cancel();
                    return;
                }
                return;
            }
            ValueAnimator valueAnimator2 = this.offsetAnimator;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.offsetAnimator = valueAnimator3;
                valueAnimator3.setInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
                this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator4) {
                        BaseBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, t, ((Integer) valueAnimator4.getAnimatedValue()).intValue());
                    }
                });
            } else {
                valueAnimator2.cancel();
            }
            this.offsetAnimator.setDuration(Math.min(i2, 600));
            this.offsetAnimator.setIntValues(topBottomOffsetForScrollingSibling, i);
            this.offsetAnimator.start();
        }

        public final View findFirstScrollingChild(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = coordinatorLayout.getChildAt(i);
                if ((childAt instanceof NestedScrollingChild) || (childAt instanceof ListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
            int i2;
            AppBarLayout appBarLayout = (AppBarLayout) view;
            super.onLayoutChild(coordinatorLayout, appBarLayout, i);
            int pendingAction = appBarLayout.getPendingAction();
            int i3 = this.offsetToChildIndexOnLayout;
            if (i3 >= 0 && (pendingAction & 8) == 0) {
                View childAt = appBarLayout.getChildAt(i3);
                int i4 = -childAt.getBottom();
                if (this.offsetToChildIndexOnLayoutIsMinHeight) {
                    i2 = appBarLayout.getTopInset() + childAt.getMinimumHeight() + i4;
                } else {
                    i2 = Math.round(childAt.getHeight() * this.offsetToChildIndexOnLayoutPerc) + i4;
                }
                setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, i2);
            } else if (pendingAction != 0) {
                boolean z = (pendingAction & 4) != 0;
                if ((pendingAction & 2) != 0) {
                    int i5 = -appBarLayout.getUpNestedPreScrollRange();
                    if (z) {
                        animateOffsetTo(coordinatorLayout, appBarLayout, i5, 0.0f);
                    } else {
                        setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, i5);
                    }
                } else if ((pendingAction & 1) != 0) {
                    if (z) {
                        animateOffsetTo(coordinatorLayout, appBarLayout, 0, 0.0f);
                    } else {
                        setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, 0);
                    }
                }
            }
            appBarLayout.pendingAction = 0;
            this.offsetToChildIndexOnLayout = -1;
            setTopAndBottomOffset(AppOpsManagerCompat.clamp(getTopAndBottomOffset(), -appBarLayout.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(coordinatorLayout, appBarLayout, getTopAndBottomOffset(), 0, true);
            appBarLayout.currentOffset = getTopAndBottomOffset();
            if (!appBarLayout.willNotDraw()) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                appBarLayout.postInvalidateOnAnimation();
            }
            updateAccessibilityActions(coordinatorLayout, appBarLayout);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (((ViewGroup.MarginLayoutParams) ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams())).height != -2) {
                return false;
            }
            coordinatorLayout.onMeasureChild(appBarLayout, i, i2, View.MeasureSpec.makeMeasureSpec(0, 0), i4);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int[] iArr, int i3) {
            onNestedPreScroll(coordinatorLayout, (AppBarLayout) view, view2, i2, iArr);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
            onNestedScroll(coordinatorLayout, (AppBarLayout) view, i4, iArr);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, View view, Parcelable parcelable) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (parcelable instanceof SavedState) {
                SavedState savedState = (SavedState) parcelable;
                this.offsetToChildIndexOnLayout = savedState.firstVisibleChildIndex;
                this.offsetToChildIndexOnLayoutPerc = savedState.firstVisibleChildPercentageShown;
                this.offsetToChildIndexOnLayoutIsMinHeight = savedState.firstVisibleChildAtMinimumHeight;
                return;
            }
            this.offsetToChildIndexOnLayout = -1;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, View view) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            AbsSavedState absSavedState = View.BaseSavedState.EMPTY_STATE;
            int topAndBottomOffset = getTopAndBottomOffset();
            int childCount = appBarLayout.getChildCount();
            boolean z = false;
            for (int i = 0; i < childCount; i++) {
                View childAt = appBarLayout.getChildAt(i);
                int bottom = childAt.getBottom() + topAndBottomOffset;
                if (childAt.getTop() + topAndBottomOffset <= 0 && bottom >= 0) {
                    SavedState savedState = new SavedState(absSavedState);
                    savedState.firstVisibleChildIndex = i;
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    if (bottom == appBarLayout.getTopInset() + childAt.getMinimumHeight()) {
                        z = true;
                    }
                    savedState.firstVisibleChildAtMinimumHeight = z;
                    savedState.firstVisibleChildPercentageShown = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return absSavedState;
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0027, code lost:
            if (((r4.getTotalScrollRange() != 0) && r3.getHeight() - r5.getHeight() <= r4.getHeight()) != false) goto L_0x002b;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onStartNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r3, com.google.android.material.appbar.AppBarLayout r4, android.view.View r5, int r6, int r7) {
            /*
                r2 = this;
                r6 = r6 & 2
                r0 = 1
                r1 = 0
                if (r6 == 0) goto L_0x002a
                boolean r6 = r4.liftOnScroll
                if (r6 != 0) goto L_0x002b
                int r6 = r4.getTotalScrollRange()
                if (r6 == 0) goto L_0x0012
                r6 = 1
                goto L_0x0013
            L_0x0012:
                r6 = 0
            L_0x0013:
                if (r6 == 0) goto L_0x0026
                int r3 = r3.getHeight()
                int r5 = r5.getHeight()
                int r3 = r3 - r5
                int r4 = r4.getHeight()
                if (r3 > r4) goto L_0x0026
                r3 = 1
                goto L_0x0027
            L_0x0026:
                r3 = 0
            L_0x0027:
                if (r3 == 0) goto L_0x002a
                goto L_0x002b
            L_0x002a:
                r0 = 0
            L_0x002b:
                if (r0 == 0) goto L_0x0034
                android.animation.ValueAnimator r3 = r2.offsetAnimator
                if (r3 == 0) goto L_0x0034
                r3.cancel()
            L_0x0034:
                r3 = 0
                r2.lastNestedScrollingChildRef = r3
                r2.lastStartedType = r7
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.onStartNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, android.view.View, int, int):boolean");
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            if (this.lastStartedType == 0 || i == 1) {
                snapToChildIfNeeded(coordinatorLayout, appBarLayout);
                if (appBarLayout.liftOnScroll) {
                    appBarLayout.setLiftedState(appBarLayout.shouldLift(view2));
                }
            }
            this.lastNestedScrollingChildRef = new WeakReference<>(view2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:36:0x00a8  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00af  */
        /* JADX WARN: Removed duplicated region for block: B:39:0x00b2  */
        @Override // com.google.android.material.appbar.HeaderBehavior
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int setHeaderTopBottomOffset(androidx.coordinatorlayout.widget.CoordinatorLayout r8, android.view.View r9, int r10, int r11, int r12) {
            /*
                r7 = this;
                com.google.android.material.appbar.AppBarLayout r9 = (com.google.android.material.appbar.AppBarLayout) r9
                int r0 = r7.getTopBottomOffsetForScrollingSibling()
                r1 = 0
                if (r11 == 0) goto L_0x00bd
                if (r0 < r11) goto L_0x00bd
                if (r0 > r12) goto L_0x00bd
                int r3 = androidx.core.app.AppOpsManagerCompat.clamp(r10, r11, r12)
                if (r0 == r3) goto L_0x00bf
                boolean r10 = r9.haveChildWithInterpolator
                if (r10 == 0) goto L_0x0088
                int r10 = java.lang.Math.abs(r3)
                int r11 = r9.getChildCount()
                r12 = 0
            L_0x0020:
                if (r12 >= r11) goto L_0x0088
                android.view.View r2 = r9.getChildAt(r12)
                android.view.ViewGroup$LayoutParams r4 = r2.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$LayoutParams r4 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r4
                android.view.animation.Interpolator r5 = r4.scrollInterpolator
                int r6 = r2.getTop()
                if (r10 < r6) goto L_0x0085
                int r6 = r2.getBottom()
                if (r10 > r6) goto L_0x0085
                if (r5 == 0) goto L_0x0088
                int r11 = r4.scrollFlags
                r12 = r11 & 1
                if (r12 == 0) goto L_0x0058
                int r12 = r2.getHeight()
                int r6 = r4.topMargin
                int r12 = r12 + r6
                int r4 = r4.bottomMargin
                int r12 = r12 + r4
                int r1 = r1 + r12
                r11 = r11 & 2
                if (r11 == 0) goto L_0x0058
                java.util.concurrent.atomic.AtomicInteger r11 = androidx.core.view.ViewCompat.sNextGeneratedId
                int r11 = r2.getMinimumHeight()
                int r1 = r1 - r11
            L_0x0058:
                java.util.concurrent.atomic.AtomicInteger r11 = androidx.core.view.ViewCompat.sNextGeneratedId
                boolean r11 = r2.getFitsSystemWindows()
                if (r11 == 0) goto L_0x0065
                int r11 = r9.getTopInset()
                int r1 = r1 - r11
            L_0x0065:
                if (r1 <= 0) goto L_0x0088
                int r11 = r2.getTop()
                int r10 = r10 - r11
                float r11 = (float) r1
                float r10 = (float) r10
                float r10 = r10 / r11
                float r10 = r5.getInterpolation(r10)
                float r10 = r10 * r11
                int r10 = java.lang.Math.round(r10)
                int r11 = java.lang.Integer.signum(r3)
                int r12 = r2.getTop()
                int r12 = r12 + r10
                int r12 = r12 * r11
                goto L_0x0089
            L_0x0085:
                int r12 = r12 + 1
                goto L_0x0020
            L_0x0088:
                r12 = r3
            L_0x0089:
                boolean r10 = r7.setTopAndBottomOffset(r12)
                int r11 = r0 - r3
                int r12 = r3 - r12
                r7.offsetDelta = r12
                if (r10 != 0) goto L_0x009c
                boolean r10 = r9.haveChildWithInterpolator
                if (r10 == 0) goto L_0x009c
                r8.dispatchDependentViewsChanged(r9)
            L_0x009c:
                int r10 = r7.getTopAndBottomOffset()
                r9.currentOffset = r10
                boolean r10 = r9.willNotDraw()
                if (r10 != 0) goto L_0x00ad
                java.util.concurrent.atomic.AtomicInteger r10 = androidx.core.view.ViewCompat.sNextGeneratedId
                r9.postInvalidateOnAnimation()
            L_0x00ad:
                if (r3 >= r0) goto L_0x00b2
                r10 = -1
                r4 = -1
                goto L_0x00b4
            L_0x00b2:
                r10 = 1
                r4 = 1
            L_0x00b4:
                r5 = 0
                r0 = r7
                r1 = r8
                r2 = r9
                r0.updateAppBarLayoutDrawableState(r1, r2, r3, r4, r5)
                r1 = r11
                goto L_0x00bf
            L_0x00bd:
                r7.offsetDelta = r1
            L_0x00bf:
                r7.updateAccessibilityActions(r8, r9)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.setHeaderTopBottomOffset(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, int, int, int):int");
        }

        public final void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, T t) {
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            int childCount = t.getChildCount();
            int i = 0;
            while (true) {
                if (i >= childCount) {
                    i = -1;
                    break;
                }
                View childAt = t.getChildAt(i);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (checkFlag(layoutParams.scrollFlags, 32)) {
                    top -= ((LinearLayout.LayoutParams) layoutParams).topMargin;
                    bottom += ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                }
                int i2 = -topBottomOffsetForScrollingSibling;
                if (top <= i2 && bottom >= i2) {
                    break;
                }
                i++;
            }
            if (i >= 0) {
                View childAt2 = t.getChildAt(i);
                LayoutParams layoutParams2 = (LayoutParams) childAt2.getLayoutParams();
                int i3 = layoutParams2.scrollFlags;
                if ((i3 & 17) == 17) {
                    int i4 = -childAt2.getTop();
                    int i5 = -childAt2.getBottom();
                    if (i == t.getChildCount() - 1) {
                        i5 += t.getTopInset();
                    }
                    if (checkFlag(i3, 2)) {
                        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                        i5 += childAt2.getMinimumHeight();
                    } else if (checkFlag(i3, 5)) {
                        AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                        int minimumHeight = childAt2.getMinimumHeight() + i5;
                        if (topBottomOffsetForScrollingSibling < minimumHeight) {
                            i4 = minimumHeight;
                        } else {
                            i5 = minimumHeight;
                        }
                    }
                    if (checkFlag(i3, 32)) {
                        i4 += ((LinearLayout.LayoutParams) layoutParams2).topMargin;
                        i5 -= ((LinearLayout.LayoutParams) layoutParams2).bottomMargin;
                    }
                    if (topBottomOffsetForScrollingSibling < (i5 + i4) / 2) {
                        i4 = i5;
                    }
                    animateOffsetTo(coordinatorLayout, t, AppOpsManagerCompat.clamp(i4, -t.getTotalScrollRange(), 0), 0.0f);
                }
            }
        }

        public final void updateAccessibilityActions(final CoordinatorLayout coordinatorLayout, final T t) {
            AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD;
            ViewCompat.removeActionWithId(accessibilityActionCompat.getId(), coordinatorLayout);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(coordinatorLayout, 0);
            AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD;
            ViewCompat.removeActionWithId(accessibilityActionCompat2.getId(), coordinatorLayout);
            ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(coordinatorLayout, 0);
            final View findFirstScrollingChild = findFirstScrollingChild(coordinatorLayout);
            if (findFirstScrollingChild != null && t.getTotalScrollRange() != 0 && (((CoordinatorLayout.LayoutParams) findFirstScrollingChild.getLayoutParams()).mBehavior instanceof ScrollingViewBehavior)) {
                if (getTopBottomOffsetForScrollingSibling() != (-t.getTotalScrollRange()) && findFirstScrollingChild.canScrollVertically(1)) {
                    ViewCompat.replaceAccessibilityAction(coordinatorLayout, accessibilityActionCompat, null, new AccessibilityViewCommand(this) { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.3
                        @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                        public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                            t.setExpanded(r3);
                            return true;
                        }
                    });
                }
                if (getTopBottomOffsetForScrollingSibling() == 0) {
                    return;
                }
                if (findFirstScrollingChild.canScrollVertically(-1)) {
                    final int i = -t.getDownNestedPreScrollRange();
                    if (i != 0) {
                        ViewCompat.replaceAccessibilityAction(coordinatorLayout, accessibilityActionCompat2, null, new AccessibilityViewCommand() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.2
                            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                            public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                                BaseBehavior.this.onNestedPreScroll(coordinatorLayout, t, findFirstScrollingChild, i, new int[]{0, 0});
                                return true;
                            }
                        });
                        return;
                    }
                    return;
                }
                ViewCompat.replaceAccessibilityAction(coordinatorLayout, accessibilityActionCompat2, null, new AccessibilityViewCommand(this) { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.3
                    @Override // androidx.core.view.accessibility.AccessibilityViewCommand
                    public boolean perform(View view, AccessibilityViewCommand.CommandArguments commandArguments) {
                        t.setExpanded(r3);
                        return true;
                    }
                });
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0070  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void updateAppBarLayoutDrawableState(androidx.coordinatorlayout.widget.CoordinatorLayout r7, T r8, int r9, int r10, boolean r11) {
            /*
                r6 = this;
                int r0 = java.lang.Math.abs(r9)
                int r1 = r8.getChildCount()
                r2 = 0
                r3 = 0
            L_0x000a:
                if (r3 >= r1) goto L_0x0020
                android.view.View r4 = r8.getChildAt(r3)
                int r5 = r4.getTop()
                if (r0 < r5) goto L_0x001d
                int r5 = r4.getBottom()
                if (r0 > r5) goto L_0x001d
                goto L_0x0021
            L_0x001d:
                int r3 = r3 + 1
                goto L_0x000a
            L_0x0020:
                r4 = 0
            L_0x0021:
                if (r4 == 0) goto L_0x009f
                android.view.ViewGroup$LayoutParams r0 = r4.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$LayoutParams r0 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r0
                int r0 = r0.scrollFlags
                r1 = r0 & 1
                r3 = 1
                if (r1 == 0) goto L_0x005d
                java.util.concurrent.atomic.AtomicInteger r1 = androidx.core.view.ViewCompat.sNextGeneratedId
                int r1 = r4.getMinimumHeight()
                if (r10 <= 0) goto L_0x004a
                r10 = r0 & 12
                if (r10 == 0) goto L_0x004a
                int r9 = -r9
                int r10 = r4.getBottom()
                int r10 = r10 - r1
                int r0 = r8.getTopInset()
                int r10 = r10 - r0
                if (r9 < r10) goto L_0x005d
                goto L_0x005b
            L_0x004a:
                r10 = r0 & 2
                if (r10 == 0) goto L_0x005d
                int r9 = -r9
                int r10 = r4.getBottom()
                int r10 = r10 - r1
                int r0 = r8.getTopInset()
                int r10 = r10 - r0
                if (r9 < r10) goto L_0x005d
            L_0x005b:
                r9 = 1
                goto L_0x005e
            L_0x005d:
                r9 = 0
            L_0x005e:
                boolean r10 = r8.liftOnScroll
                if (r10 == 0) goto L_0x006a
                android.view.View r9 = r6.findFirstScrollingChild(r7)
                boolean r9 = r8.shouldLift(r9)
            L_0x006a:
                boolean r9 = r8.setLiftedState(r9)
                if (r11 != 0) goto L_0x009c
                if (r9 == 0) goto L_0x009f
                java.util.List r7 = r7.getDependents(r8)
                int r9 = r7.size()
                r10 = 0
            L_0x007b:
                if (r10 >= r9) goto L_0x009a
                java.lang.Object r11 = r7.get(r10)
                android.view.View r11 = (android.view.View) r11
                android.view.ViewGroup$LayoutParams r11 = r11.getLayoutParams()
                androidx.coordinatorlayout.widget.CoordinatorLayout$LayoutParams r11 = (androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams) r11
                androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior r11 = r11.mBehavior
                boolean r0 = r11 instanceof com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior
                if (r0 == 0) goto L_0x0097
                com.google.android.material.appbar.AppBarLayout$ScrollingViewBehavior r11 = (com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior) r11
                int r7 = r11.overlayTop
                if (r7 == 0) goto L_0x009a
                r2 = 1
                goto L_0x009a
            L_0x0097:
                int r10 = r10 + 1
                goto L_0x007b
            L_0x009a:
                if (r2 == 0) goto L_0x009f
            L_0x009c:
                r8.jumpDrawablesToCurrentState()
            L_0x009f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.updateAppBarLayoutDrawableState(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, int, int, boolean):void");
        }

        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i, int[] iArr) {
            int i2;
            int i3;
            if (i != 0) {
                if (i < 0) {
                    int i4 = -appBarLayout.getTotalScrollRange();
                    i2 = appBarLayout.getDownNestedPreScrollRange() + i4;
                    i3 = i4;
                } else {
                    i3 = -appBarLayout.getUpNestedPreScrollRange();
                    i2 = 0;
                }
                if (i3 != i2) {
                    iArr[1] = scroll(coordinatorLayout, appBarLayout, i, i3, i2);
                }
            }
            if (appBarLayout.liftOnScroll) {
                appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i, int[] iArr) {
            if (i < 0) {
                iArr[1] = scroll(coordinatorLayout, appBarLayout, i, -appBarLayout.getDownNestedScrollRange(), 0);
            }
            if (i == 0) {
                updateAccessibilityActions(coordinatorLayout, appBarLayout);
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        /* loaded from: classes.dex */
        public static class SavedState extends androidx.customview.view.AbsSavedState {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.SavedState.1
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
            public boolean firstVisibleChildAtMinimumHeight;
            public int firstVisibleChildIndex;
            public float firstVisibleChildPercentageShown;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.firstVisibleChildIndex = parcel.readInt();
                this.firstVisibleChildPercentageShown = parcel.readFloat();
                this.firstVisibleChildAtMinimumHeight = parcel.readByte() != 0;
            }

            @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeParcelable(this.mSuperState, i);
                parcel.writeInt(this.firstVisibleChildIndex);
                parcel.writeFloat(this.firstVisibleChildPercentageShown);
                parcel.writeByte(this.firstVisibleChildAtMinimumHeight ? (byte) 1 : (byte) 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
            return onStartNestedScroll(coordinatorLayout, (AppBarLayout) view, view2, i, i2);
        }
    }

    /* loaded from: classes.dex */
    public static class Behavior extends BaseBehavior<AppBarLayout> {
        public Behavior() {
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    /* loaded from: classes.dex */
    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        public AppBarLayout findFirstDependency(List<View> list) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = list.get(i);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) view2.getLayoutParams()).mBehavior;
            if (behavior instanceof BaseBehavior) {
                ViewCompat.offsetTopAndBottom(view, (((view2.getBottom() - view.getTop()) + ((BaseBehavior) behavior).offsetDelta) + this.verticalLayoutGap) - getOverlapPixelsForOffset(view2));
            }
            if (!(view2 instanceof AppBarLayout)) {
                return false;
            }
            AppBarLayout appBarLayout = (AppBarLayout) view2;
            if (!appBarLayout.liftOnScroll) {
                return false;
            }
            appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                ViewCompat.removeActionWithId(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD.getId(), coordinatorLayout);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(coordinatorLayout, 0);
                ViewCompat.removeActionWithId(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD.getId(), coordinatorLayout);
                ViewCompat.notifyViewAccessibilityStateChangedIfNeeded(coordinatorLayout, 0);
            }
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z) {
            AppBarLayout findFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view));
            if (findFirstDependency != null) {
                rect.offset(view.getLeft(), view.getTop());
                Rect rect2 = this.tempRect1;
                rect2.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect2.contains(rect)) {
                    findFirstDependency.setExpanded(false, !z, true);
                    return true;
                }
            }
            return false;
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ScrollingViewBehavior_Layout);
            this.overlayTop = obtainStyledAttributes.getDimensionPixelSize(0, 0);
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Finally extract failed */
    public AppBarLayout(Context context) {
        super(MaterialThemeOverlay.wrap(context, null, R.attr.appBarLayoutStyle, 2131886706), null, R.attr.appBarLayoutStyle);
        Context context2 = getContext();
        setOrientation(1);
        int i = Build.VERSION.SDK_INT;
        setOutlineProvider(ViewOutlineProvider.BOUNDS);
        Context context3 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context3, null, ViewUtilsLollipop.STATE_LIST_ANIM_ATTRS, R.attr.appBarLayoutStyle, 2131886706, new int[0]);
        try {
            if (obtainStyledAttributes.hasValue(0)) {
                setStateListAnimator(AnimatorInflater.loadStateListAnimator(context3, obtainStyledAttributes.getResourceId(0, 0)));
            }
            obtainStyledAttributes.recycle();
            TypedArray obtainStyledAttributes2 = ThemeEnforcement.obtainStyledAttributes(context2, null, R$styleable.AppBarLayout, R.attr.appBarLayoutStyle, 2131886706, new int[0]);
            Drawable drawable = obtainStyledAttributes2.getDrawable(0);
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            setBackground(drawable);
            if (getBackground() instanceof ColorDrawable) {
                MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
                materialShapeDrawable.setFillColor(ColorStateList.valueOf(((ColorDrawable) getBackground()).getColor()));
                materialShapeDrawable.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context2);
                materialShapeDrawable.updateZ();
                setBackground(materialShapeDrawable);
            }
            if (obtainStyledAttributes2.hasValue(4)) {
                setExpanded(obtainStyledAttributes2.getBoolean(4, false), false, false);
            }
            if (obtainStyledAttributes2.hasValue(3)) {
                ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, obtainStyledAttributes2.getDimensionPixelSize(3, 0));
            }
            if (i >= 26) {
                if (obtainStyledAttributes2.hasValue(2)) {
                    setKeyboardNavigationCluster(obtainStyledAttributes2.getBoolean(2, false));
                }
                if (obtainStyledAttributes2.hasValue(1)) {
                    setTouchscreenBlocksFocus(obtainStyledAttributes2.getBoolean(1, false));
                }
            }
            this.liftOnScroll = obtainStyledAttributes2.getBoolean(5, false);
            this.liftOnScrollTargetViewId = obtainStyledAttributes2.getResourceId(6, -1);
            setStatusBarForeground(obtainStyledAttributes2.getDrawable(7));
            obtainStyledAttributes2.recycle();
            ViewCompat.Api21Impl.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.appbar.AppBarLayout.1
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    AppBarLayout appBarLayout = AppBarLayout.this;
                    Objects.requireNonNull(appBarLayout);
                    AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                    WindowInsetsCompat windowInsetsCompat2 = appBarLayout.getFitsSystemWindows() ? windowInsetsCompat : null;
                    if (!Objects.equals(appBarLayout.lastInsets, windowInsetsCompat2)) {
                        appBarLayout.lastInsets = windowInsetsCompat2;
                        appBarLayout.updateWillNotDraw();
                        appBarLayout.requestLayout();
                    }
                    return windowInsetsCompat;
                }
            });
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.statusBarForeground != null && getTopInset() > 0) {
            int save = canvas.save();
            canvas.translate(0.0f, -this.currentOffset);
            this.statusBarForeground.draw(canvas);
            canvas.restoreToCount(save);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarForeground;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.AttachedBehavior
    public CoordinatorLayout.Behavior<AppBarLayout> getBehavior() {
        return new Behavior();
    }

    public int getDownNestedPreScrollRange() {
        int i;
        int i2 = this.downPreScrollRange;
        if (i2 != -1) {
            return i2;
        }
        int i3 = 0;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = layoutParams.scrollFlags;
            if ((i4 & 5) == 5) {
                int i5 = ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                if ((i4 & 8) != 0) {
                    AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                    i = i5 + childAt.getMinimumHeight();
                } else if ((i4 & 2) != 0) {
                    AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                    i = i5 + (measuredHeight - childAt.getMinimumHeight());
                } else {
                    i = i5 + measuredHeight;
                }
                if (childCount == 0) {
                    AtomicInteger atomicInteger3 = ViewCompat.sNextGeneratedId;
                    if (childAt.getFitsSystemWindows()) {
                        i = Math.min(i, measuredHeight - getTopInset());
                    }
                }
                i3 += i;
            } else if (i3 > 0) {
                break;
            }
        }
        int max = Math.max(0, i3);
        this.downPreScrollRange = max;
        return max;
    }

    public int getDownNestedScrollRange() {
        int i = this.downScrollRange;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + childAt.getMeasuredHeight();
            int i4 = layoutParams.scrollFlags;
            if ((i4 & 1) == 0) {
                break;
            }
            i3 += measuredHeight;
            if ((i4 & 2) != 0) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                i3 -= childAt.getMinimumHeight();
                break;
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.downScrollRange = max;
        return max;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.liftOnScrollTargetViewId;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        int minimumHeight = getMinimumHeight();
        if (minimumHeight == 0) {
            int childCount = getChildCount();
            minimumHeight = childCount >= 1 ? getChildAt(childCount - 1).getMinimumHeight() : 0;
            if (minimumHeight == 0) {
                return getHeight() / 3;
            }
        }
        return (minimumHeight * 2) + topInset;
    }

    public int getPendingAction() {
        return this.pendingAction;
    }

    public Drawable getStatusBarForeground() {
        return this.statusBarForeground;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        WindowInsetsCompat windowInsetsCompat = this.lastInsets;
        if (windowInsetsCompat != null) {
            return windowInsetsCompat.getSystemWindowInsetTop();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i = this.totalScrollRange;
        if (i != -1) {
            return i;
        }
        int childCount = getChildCount();
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= childCount) {
                break;
            }
            View childAt = getChildAt(i2);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int i4 = layoutParams.scrollFlags;
            if ((i4 & 1) == 0) {
                break;
            }
            i3 = measuredHeight + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + i3;
            if (i2 == 0) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (childAt.getFitsSystemWindows()) {
                    i3 -= getTopInset();
                }
            }
            if ((i4 & 2) != 0) {
                AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
                i3 -= childAt.getMinimumHeight();
                break;
            }
            i2++;
        }
        int max = Math.max(0, i3);
        this.totalScrollRange = max;
        return max;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof MaterialShapeDrawable) {
            R$style.setParentAbsoluteElevation(this, (MaterialShapeDrawable) background);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i) {
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] iArr = this.tmpStatesArray;
        int[] onCreateDrawableState = super.onCreateDrawableState(i + iArr.length);
        boolean z = this.liftable;
        iArr[0] = z ? R.attr.state_liftable : -2130969338;
        iArr[1] = (!z || !this.lifted) ? -2130969339 : R.attr.state_lifted;
        iArr[2] = z ? R.attr.state_collapsible : -2130969336;
        iArr[3] = (!z || !this.lifted) ? -2130969335 : R.attr.state_collapsed;
        return LinearLayout.mergeDrawableStates(onCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        super.onLayout(z, i, i2, i3, i4);
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        boolean z3 = true;
        if (getFitsSystemWindows() && shouldOffsetFirstChild()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                ViewCompat.offsetTopAndBottom(getChildAt(childCount), topInset);
            }
        }
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        this.haveChildWithInterpolator = false;
        int childCount2 = getChildCount();
        int i5 = 0;
        while (true) {
            if (i5 >= childCount2) {
                break;
            } else if (((LayoutParams) getChildAt(i5).getLayoutParams()).scrollInterpolator != null) {
                this.haveChildWithInterpolator = true;
                break;
            } else {
                i5++;
            }
        }
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (!this.liftOnScroll) {
            int childCount3 = getChildCount();
            int i6 = 0;
            while (true) {
                if (i6 >= childCount3) {
                    z2 = false;
                    break;
                }
                int i7 = ((LayoutParams) getChildAt(i6).getLayoutParams()).scrollFlags;
                if ((i7 & 1) == 1 && (i7 & 10) != 0) {
                    z2 = true;
                    break;
                }
                i6++;
            }
            if (!z2) {
                z3 = false;
            }
        }
        if (this.liftable != z3) {
            this.liftable = z3;
            refreshDrawableState();
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode != 1073741824) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (getFitsSystemWindows() && shouldOffsetFirstChild()) {
                int measuredHeight = getMeasuredHeight();
                if (mode == Integer.MIN_VALUE) {
                    measuredHeight = AppOpsManagerCompat.clamp(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i2));
                } else if (mode == 0) {
                    measuredHeight += getTopInset();
                }
                setMeasuredDimension(getMeasuredWidth(), measuredHeight);
            }
        }
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        R$style.setElevation(this, f);
    }

    public void setExpanded(boolean z) {
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        setExpanded(z, isLaidOut(), true);
    }

    public void setLiftOnScroll(boolean z) {
        this.liftOnScroll = z;
    }

    public void setLiftOnScrollTargetViewId(int i) {
        this.liftOnScrollTargetViewId = i;
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    public boolean setLiftedState(boolean z) {
        if (this.lifted == z) {
            return false;
        }
        this.lifted = z;
        refreshDrawableState();
        if (this.liftOnScroll && (getBackground() instanceof MaterialShapeDrawable)) {
            final MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable) getBackground();
            float dimension = getResources().getDimension(R.dimen.design_appbar_elevation);
            float f = z ? 0.0f : dimension;
            if (!z) {
                dimension = 0.0f;
            }
            ValueAnimator valueAnimator = this.elevationOverlayAnimator;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(f, dimension);
            this.elevationOverlayAnimator = ofFloat;
            ofFloat.setDuration(getResources().getInteger(R.integer.app_bar_elevation_anim_duration));
            this.elevationOverlayAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
            this.elevationOverlayAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.material.appbar.AppBarLayout.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    materialShapeDrawable.setElevation(((Float) valueAnimator2.getAnimatedValue()).floatValue());
                }
            });
            this.elevationOverlayAnimator.start();
        }
        return true;
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i) {
        if (i == 1) {
            super.setOrientation(i);
            return;
        }
        throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.statusBarForeground;
        if (drawable2 != drawable) {
            Drawable drawable3 = null;
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            if (drawable != null) {
                drawable3 = drawable.mutate();
            }
            this.statusBarForeground = drawable3;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.statusBarForeground.setState(getDrawableState());
                }
                Drawable drawable4 = this.statusBarForeground;
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                AppOpsManagerCompat.setLayoutDirection(drawable4, getLayoutDirection());
                this.statusBarForeground.setVisible(getVisibility() == 0, false);
                this.statusBarForeground.setCallback(this);
            }
            updateWillNotDraw();
            AtomicInteger atomicInteger2 = ViewCompat.sNextGeneratedId;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarForegroundColor(int i) {
        setStatusBarForeground(new ColorDrawable(i));
    }

    public void setStatusBarForegroundResource(int i) {
        setStatusBarForeground(AppCompatResources.getDrawable(getContext(), i));
    }

    @Deprecated
    public void setTargetElevation(float f) {
        ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, f);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setVisible(z, false);
        }
    }

    public boolean shouldLift(View view) {
        int i;
        View view2 = null;
        if (this.liftOnScrollTargetView == null && (i = this.liftOnScrollTargetViewId) != -1) {
            View findViewById = view != null ? view.findViewById(i) : null;
            if (findViewById == null && (getParent() instanceof ViewGroup)) {
                findViewById = ((ViewGroup) getParent()).findViewById(this.liftOnScrollTargetViewId);
            }
            if (findViewById != null) {
                this.liftOnScrollTargetView = new WeakReference<>(findViewById);
            }
        }
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            view2 = weakReference.get();
        }
        if (view2 != null) {
            view = view2;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    public final boolean shouldOffsetFirstChild() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        if (childAt.getVisibility() == 8) {
            return false;
        }
        AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
        return !childAt.getFitsSystemWindows();
    }

    public final void updateWillNotDraw() {
        setWillNotDraw(!(this.statusBarForeground != null && getTopInset() > 0));
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.statusBarForeground;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* renamed from: generateDefaultLayoutParams  reason: collision with other method in class */
    public LinearLayout.LayoutParams mo6generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    /* renamed from: generateLayoutParams  reason: collision with other method in class */
    public LinearLayout.LayoutParams mo7generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public final void setExpanded(boolean z, boolean z2, boolean z3) {
        int i = 0;
        int i2 = (z ? 1 : 2) | (z2 ? 4 : 0);
        if (z3) {
            i = 8;
        }
        this.pendingAction = i2 | i;
        requestLayout();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new LayoutParams((LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends LinearLayout.LayoutParams {
        public int scrollFlags;
        public Interpolator scrollInterpolator;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.scrollFlags = 1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.AppBarLayout_Layout);
            this.scrollFlags = obtainStyledAttributes.getInt(0, 0);
            if (obtainStyledAttributes.hasValue(1)) {
                this.scrollInterpolator = android.view.animation.AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(1, 0));
            }
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.scrollFlags = 1;
        }
    }
}
