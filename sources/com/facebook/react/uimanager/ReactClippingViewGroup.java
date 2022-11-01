package com.facebook.react.uimanager;

import android.graphics.Rect;
/* loaded from: classes.dex */
public interface ReactClippingViewGroup {
    void getClippingRect(Rect rect);

    boolean getRemoveClippedSubviews();

    void updateClippingRect();
}
