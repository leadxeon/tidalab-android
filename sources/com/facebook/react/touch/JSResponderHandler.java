package com.facebook.react.touch;

import android.view.ViewParent;
/* loaded from: classes.dex */
public class JSResponderHandler implements OnInterceptTouchEventListener {
    public volatile int mCurrentJSResponder = -1;
    public ViewParent mViewParentBlockingNativeResponder;

    public void setJSResponder(int i, ViewParent viewParent) {
        this.mCurrentJSResponder = i;
        ViewParent viewParent2 = this.mViewParentBlockingNativeResponder;
        if (viewParent2 != null) {
            viewParent2.requestDisallowInterceptTouchEvent(false);
            this.mViewParentBlockingNativeResponder = null;
        }
        if (viewParent != null) {
            viewParent.requestDisallowInterceptTouchEvent(true);
            this.mViewParentBlockingNativeResponder = viewParent;
        }
    }
}
