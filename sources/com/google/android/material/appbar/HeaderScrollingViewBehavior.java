package com.google.android.material.appbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.appbar.AppBarLayout;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public abstract class HeaderScrollingViewBehavior extends ViewOffsetBehavior<View> {
    public int overlayTop;
    public final Rect tempRect1 = new Rect();
    public final Rect tempRect2 = new Rect();
    public int verticalLayoutGap = 0;

    public HeaderScrollingViewBehavior() {
    }

    public final int getOverlapPixelsForOffset(View view) {
        int i;
        if (this.overlayTop == 0) {
            return 0;
        }
        float f = 0.0f;
        if (view instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) view;
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams()).mBehavior;
            int topBottomOffsetForScrollingSibling = behavior instanceof AppBarLayout.BaseBehavior ? ((AppBarLayout.BaseBehavior) behavior).getTopBottomOffsetForScrollingSibling() : 0;
            if ((downNestedPreScrollRange == 0 || totalScrollRange + topBottomOffsetForScrollingSibling > downNestedPreScrollRange) && (i = totalScrollRange - downNestedPreScrollRange) != 0) {
                f = 1.0f + (topBottomOffsetForScrollingSibling / i);
            }
        }
        int i2 = this.overlayTop;
        return AppOpsManagerCompat.clamp((int) (f * i2), 0, i2);
    }

    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    public void layoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        AppBarLayout findFirstDependency = ((AppBarLayout.ScrollingViewBehavior) this).findFirstDependency(coordinatorLayout.getDependencies(view));
        if (findFirstDependency != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) view.getLayoutParams();
            Rect rect = this.tempRect1;
            rect.set(coordinatorLayout.getPaddingLeft() + ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin, findFirstDependency.getBottom() + ((ViewGroup.MarginLayoutParams) layoutParams).topMargin, (coordinatorLayout.getWidth() - coordinatorLayout.getPaddingRight()) - ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin, ((findFirstDependency.getBottom() + coordinatorLayout.getHeight()) - coordinatorLayout.getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
            WindowInsetsCompat lastWindowInsets = coordinatorLayout.getLastWindowInsets();
            if (lastWindowInsets != null) {
                AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
                if (coordinatorLayout.getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                    rect.left = lastWindowInsets.getSystemWindowInsetLeft() + rect.left;
                    rect.right -= lastWindowInsets.getSystemWindowInsetRight();
                }
            }
            Rect rect2 = this.tempRect2;
            int i2 = layoutParams.gravity;
            Gravity.apply(i2 == 0 ? 8388659 : i2, view.getMeasuredWidth(), view.getMeasuredHeight(), rect, rect2, i);
            int overlapPixelsForOffset = getOverlapPixelsForOffset(findFirstDependency);
            view.layout(rect2.left, rect2.top - overlapPixelsForOffset, rect2.right, rect2.bottom - overlapPixelsForOffset);
            this.verticalLayoutGap = rect2.top - findFirstDependency.getBottom();
            return;
        }
        coordinatorLayout.onLayoutChild(view, i);
        this.verticalLayoutGap = 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int i4) {
        WindowInsetsCompat lastWindowInsets;
        int i5 = view.getLayoutParams().height;
        if (i5 != -1 && i5 != -2) {
            return false;
        }
        AppBarLayout findFirstDependency = ((AppBarLayout.ScrollingViewBehavior) this).findFirstDependency(coordinatorLayout.getDependencies(view));
        if (findFirstDependency == null) {
            return false;
        }
        int size = View.MeasureSpec.getSize(i3);
        if (size > 0) {
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            if (findFirstDependency.getFitsSystemWindows() && (lastWindowInsets = coordinatorLayout.getLastWindowInsets()) != null) {
                size += lastWindowInsets.getSystemWindowInsetBottom() + lastWindowInsets.getSystemWindowInsetTop();
            }
        } else {
            size = coordinatorLayout.getHeight();
        }
        coordinatorLayout.onMeasureChild(view, i, i2, View.MeasureSpec.makeMeasureSpec((size + findFirstDependency.getTotalScrollRange()) - findFirstDependency.getMeasuredHeight(), i5 == -1 ? 1073741824 : Integer.MIN_VALUE), i4);
        return true;
    }

    public HeaderScrollingViewBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
