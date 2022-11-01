package com.facebook.react.uimanager;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/* loaded from: classes.dex */
public class ViewGroupDrawingOrderHelper {
    public int[] mDrawingOrderIndices;
    public int mNumberOfChildrenWithZIndex = 0;
    public final ViewGroup mViewGroup;

    public ViewGroupDrawingOrderHelper(ViewGroup viewGroup) {
        this.mViewGroup = viewGroup;
    }

    public int getChildDrawingOrder(int i, int i2) {
        if (this.mDrawingOrderIndices == null) {
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < i; i3++) {
                arrayList.add(this.mViewGroup.getChildAt(i3));
            }
            Collections.sort(arrayList, new Comparator<View>(this) { // from class: com.facebook.react.uimanager.ViewGroupDrawingOrderHelper.1
                @Override // java.util.Comparator
                public int compare(View view, View view2) {
                    View view3 = view2;
                    Integer viewZIndex = ViewGroupManager.getViewZIndex(view);
                    Integer num = 0;
                    if (viewZIndex == null) {
                        viewZIndex = num;
                    }
                    Integer viewZIndex2 = ViewGroupManager.getViewZIndex(view3);
                    if (viewZIndex2 != null) {
                        num = viewZIndex2;
                    }
                    return viewZIndex.intValue() - num.intValue();
                }
            });
            this.mDrawingOrderIndices = new int[i];
            for (int i4 = 0; i4 < i; i4++) {
                this.mDrawingOrderIndices[i4] = this.mViewGroup.indexOfChild((View) arrayList.get(i4));
            }
        }
        return this.mDrawingOrderIndices[i2];
    }

    public boolean shouldEnableCustomDrawingOrder() {
        return this.mNumberOfChildrenWithZIndex > 0;
    }
}
