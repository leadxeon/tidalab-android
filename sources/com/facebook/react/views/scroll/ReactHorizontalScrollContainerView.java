package com.facebook.react.views.scroll;

import android.content.Context;
import android.widget.HorizontalScrollView;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.views.view.ReactViewGroup;
/* loaded from: classes.dex */
public class ReactHorizontalScrollContainerView extends ReactViewGroup {
    public int mCurrentWidth = 0;
    public int mLayoutDirection;

    public ReactHorizontalScrollContainerView(Context context) {
        super(context);
        this.mLayoutDirection = I18nUtil.getInstance().isRTL(context) ? 1 : 0;
    }

    @Override // com.facebook.react.views.view.ReactViewGroup, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mLayoutDirection == 1) {
            setLeft(0);
            setRight((i3 - i) + 0);
            HorizontalScrollView horizontalScrollView = (HorizontalScrollView) getParent();
            horizontalScrollView.scrollTo((getWidth() + horizontalScrollView.getScrollX()) - this.mCurrentWidth, horizontalScrollView.getScrollY());
        }
        this.mCurrentWidth = getWidth();
    }
}
