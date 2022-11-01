package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.uimanager.BaseViewManagerInterface;
/* loaded from: classes.dex */
public abstract class BaseViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T>> implements ViewManagerDelegate<T> {
    public final U mViewManager;

    public BaseViewManagerDelegate(U u) {
        this.mViewManager = u;
    }
}
