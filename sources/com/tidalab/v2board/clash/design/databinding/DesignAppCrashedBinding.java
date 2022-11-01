package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.AppCrashedDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
import com.tidalab.v2board.clash.design.view.ObservableScrollView;
/* loaded from: classes.dex */
public abstract class DesignAppCrashedBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public final TextView logsView;
    public AppCrashedDesign mSelf;
    public final ObservableScrollView scrollRoot;

    public DesignAppCrashedBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, TextView textView, ObservableScrollView observableScrollView) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.logsView = textView;
        this.scrollRoot = observableScrollView;
    }

    public abstract void setSelf(AppCrashedDesign appCrashedDesign);
}
