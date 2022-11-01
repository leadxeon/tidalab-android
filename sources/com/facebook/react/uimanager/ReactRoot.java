package com.facebook.react.uimanager;

import android.os.Bundle;
import android.view.ViewGroup;
/* loaded from: classes.dex */
public interface ReactRoot {
    Bundle getAppProperties();

    int getHeightMeasureSpec();

    String getInitialUITemplate();

    ViewGroup getRootViewGroup();

    int getRootViewTag();

    @Deprecated
    String getSurfaceID();

    int getUIManagerType();

    int getWidthMeasureSpec();

    void onStage(int i);

    void runApplication();

    void setRootViewTag(int i);

    void setShouldLogContentAppeared(boolean z);
}
