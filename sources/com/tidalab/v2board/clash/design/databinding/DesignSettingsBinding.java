package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.SettingsDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
import com.tidalab.v2board.clash.design.view.ObservableScrollView;
/* loaded from: classes.dex */
public abstract class DesignSettingsBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public SettingsDesign mSelf;
    public final ObservableScrollView scrollRoot;

    public DesignSettingsBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, ObservableScrollView observableScrollView) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.scrollRoot = observableScrollView;
    }

    public abstract void setSelf(SettingsDesign settingsDesign);
}
