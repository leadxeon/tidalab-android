package com.facebook.react.views.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.view.ReactViewGroup;
/* loaded from: classes.dex */
public abstract class ReactClippingViewManager<T extends ReactViewGroup> extends ViewGroupManager<T> {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewGroupManager
    public /* bridge */ /* synthetic */ void addView(ViewGroup viewGroup, View view, int i) {
        addView((ReactClippingViewManager<T>) ((ReactViewGroup) viewGroup), view, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewGroupManager
    public /* bridge */ /* synthetic */ View getChildAt(ViewGroup viewGroup, int i) {
        return getChildAt((ReactClippingViewManager<T>) ((ReactViewGroup) viewGroup), i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewGroupManager
    public /* bridge */ /* synthetic */ int getChildCount(ViewGroup viewGroup) {
        return getChildCount((ReactClippingViewManager<T>) ((ReactViewGroup) viewGroup));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewGroupManager
    public /* bridge */ /* synthetic */ void removeAllViews(ViewGroup viewGroup) {
        removeAllViews((ReactClippingViewManager<T>) ((ReactViewGroup) viewGroup));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.ViewGroupManager
    public /* bridge */ /* synthetic */ void removeViewAt(ViewGroup viewGroup, int i) {
        removeViewAt((ReactClippingViewManager<T>) ((ReactViewGroup) viewGroup), i);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(T t, boolean z) {
        UiThreadUtil.assertOnUiThread();
        t.setRemoveClippedSubviews(z);
    }

    public void addView(T t, View view, int i) {
        UiThreadUtil.assertOnUiThread();
        if (t.getRemoveClippedSubviews()) {
            R$dimen.assertCondition(t.mRemoveClippedSubviews);
            R$dimen.assertNotNull(t.mClippingRect);
            R$dimen.assertNotNull(t.mAllChildren);
            View[] viewArr = t.mAllChildren;
            R$dimen.assertNotNull(viewArr);
            int i2 = t.mAllChildrenCount;
            int length = viewArr.length;
            if (i == i2) {
                if (length == i2) {
                    View[] viewArr2 = new View[length + 12];
                    t.mAllChildren = viewArr2;
                    System.arraycopy(viewArr, 0, viewArr2, 0, length);
                    viewArr = t.mAllChildren;
                }
                int i3 = t.mAllChildrenCount;
                t.mAllChildrenCount = i3 + 1;
                viewArr[i3] = view;
            } else if (i < i2) {
                if (length == i2) {
                    View[] viewArr3 = new View[length + 12];
                    t.mAllChildren = viewArr3;
                    System.arraycopy(viewArr, 0, viewArr3, 0, i);
                    System.arraycopy(viewArr, i, t.mAllChildren, i + 1, i2 - i);
                    viewArr = t.mAllChildren;
                } else {
                    System.arraycopy(viewArr, i, viewArr, i + 1, i2 - i);
                }
                viewArr[i] = view;
                t.mAllChildrenCount++;
            } else {
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline5("index=", i, " count=", i2));
            }
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                if (t.mAllChildren[i5].getParent() == null) {
                    i4++;
                }
            }
            t.updateSubviewClipStatus(t.mClippingRect, i, i4);
            view.addOnLayoutChangeListener(t.mChildrenLayoutChangeListener);
            return;
        }
        t.addView(view, i);
    }

    public View getChildAt(T t, int i) {
        if (!t.getRemoveClippedSubviews()) {
            return t.getChildAt(i);
        }
        View[] viewArr = t.mAllChildren;
        R$dimen.assertNotNull(viewArr);
        return viewArr[i];
    }

    public int getChildCount(T t) {
        if (t.getRemoveClippedSubviews()) {
            return t.getAllChildrenCount();
        }
        return t.getChildCount();
    }

    public void removeAllViews(T t) {
        UiThreadUtil.assertOnUiThread();
        if (t.getRemoveClippedSubviews()) {
            R$dimen.assertCondition(t.mRemoveClippedSubviews);
            R$dimen.assertNotNull(t.mAllChildren);
            for (int i = 0; i < t.mAllChildrenCount; i++) {
                t.mAllChildren[i].removeOnLayoutChangeListener(t.mChildrenLayoutChangeListener);
            }
            t.removeAllViewsInLayout();
            t.mAllChildrenCount = 0;
            return;
        }
        t.removeAllViews();
    }

    public void removeViewAt(T t, int i) {
        UiThreadUtil.assertOnUiThread();
        if (t.getRemoveClippedSubviews()) {
            View childAt = getChildAt((ReactClippingViewManager<T>) t, i);
            if (childAt.getParent() != null) {
                t.removeView(childAt);
            }
            t.removeViewWithSubviewClippingEnabled(childAt);
            return;
        }
        t.removeViewAt(i);
    }
}
