package com.facebook.react.views.drawer;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.drawerlayout.widget.DrawerLayout;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.ReactContext;
/* loaded from: classes.dex */
public class ReactDrawerLayout extends DrawerLayout {
    public int mDrawerPosition = 8388611;
    public int mDrawerWidth = -1;

    public ReactDrawerLayout(ReactContext reactContext) {
        super(reactContext);
    }

    public void closeDrawer() {
        int i = this.mDrawerPosition;
        View findDrawerWithGravity = findDrawerWithGravity(i);
        if (findDrawerWithGravity != null) {
            closeDrawer(findDrawerWithGravity, true);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("No drawer view found with gravity ");
        outline13.append(DrawerLayout.gravityToString(i));
        throw new IllegalArgumentException(outline13.toString());
    }

    @Override // androidx.drawerlayout.widget.DrawerLayout, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        try {
            if (!super.onInterceptTouchEvent(motionEvent)) {
                return false;
            }
            R$style.notifyNativeGestureStarted(this, motionEvent);
            return true;
        } catch (IllegalArgumentException e) {
            Log.w("ReactNative", "Error intercepting touch event.", e);
            return false;
        }
    }

    public void openDrawer() {
        int i = this.mDrawerPosition;
        View findDrawerWithGravity = findDrawerWithGravity(i);
        if (findDrawerWithGravity != null) {
            openDrawer(findDrawerWithGravity, true);
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("No drawer view found with gravity ");
        outline13.append(DrawerLayout.gravityToString(i));
        throw new IllegalArgumentException(outline13.toString());
    }

    public void setDrawerProperties() {
        if (getChildCount() == 2) {
            View childAt = getChildAt(1);
            DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) childAt.getLayoutParams();
            layoutParams.gravity = this.mDrawerPosition;
            ((ViewGroup.MarginLayoutParams) layoutParams).width = this.mDrawerWidth;
            childAt.setLayoutParams(layoutParams);
            childAt.setClickable(true);
        }
    }
}
