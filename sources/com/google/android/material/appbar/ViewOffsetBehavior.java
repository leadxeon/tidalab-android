package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
/* loaded from: classes.dex */
public class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public int tempTopBottomOffset = 0;
    public ViewOffsetHelper viewOffsetHelper;

    public ViewOffsetBehavior() {
    }

    public int getTopAndBottomOffset() {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.offsetTop;
        }
        return 0;
    }

    public void layoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        coordinatorLayout.onLayoutChild(v, i);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v, int i) {
        layoutChild(coordinatorLayout, v, i);
        if (this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new ViewOffsetHelper(v);
        }
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        viewOffsetHelper.layoutTop = viewOffsetHelper.view.getTop();
        viewOffsetHelper.layoutLeft = viewOffsetHelper.view.getLeft();
        this.viewOffsetHelper.applyOffsets();
        int i2 = this.tempTopBottomOffset;
        if (i2 == 0) {
            return true;
        }
        ViewOffsetHelper viewOffsetHelper2 = this.viewOffsetHelper;
        if (viewOffsetHelper2.offsetTop != i2) {
            viewOffsetHelper2.offsetTop = i2;
            viewOffsetHelper2.applyOffsets();
        }
        this.tempTopBottomOffset = 0;
        return true;
    }

    public boolean setTopAndBottomOffset(int i) {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper == null) {
            this.tempTopBottomOffset = i;
            return false;
        } else if (viewOffsetHelper.offsetTop == i) {
            return false;
        } else {
            viewOffsetHelper.offsetTop = i;
            viewOffsetHelper.applyOffsets();
            return true;
        }
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
