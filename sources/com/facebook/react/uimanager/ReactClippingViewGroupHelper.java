package com.facebook.react.uimanager;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
/* loaded from: classes.dex */
public class ReactClippingViewGroupHelper {
    public static final Rect sHelperRect = new Rect();

    public static void calculateClippingRect(View view, Rect rect) {
        ViewParent parent = view.getParent();
        if (parent == null) {
            rect.setEmpty();
            return;
        }
        if (parent instanceof ReactClippingViewGroup) {
            ReactClippingViewGroup reactClippingViewGroup = (ReactClippingViewGroup) parent;
            if (reactClippingViewGroup.getRemoveClippedSubviews()) {
                Rect rect2 = sHelperRect;
                reactClippingViewGroup.getClippingRect(rect2);
                if (!rect2.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom())) {
                    rect.setEmpty();
                    return;
                }
                rect2.offset(-view.getLeft(), -view.getTop());
                rect2.offset(view.getScrollX(), view.getScrollY());
                rect.set(rect2);
                return;
            }
        }
        view.getDrawingRect(rect);
    }
}
