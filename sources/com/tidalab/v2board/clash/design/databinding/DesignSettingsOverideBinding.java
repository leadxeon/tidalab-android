package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.OverrideSettingsDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
import com.tidalab.v2board.clash.design.view.ObservableScrollView;
/* loaded from: classes.dex */
public abstract class DesignSettingsOverideBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public final ImageView clearView;
    public final FrameLayout content;
    public OverrideSettingsDesign mSelf;
    public final ObservableScrollView scrollRoot;

    public DesignSettingsOverideBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, ImageView imageView, FrameLayout frameLayout, ObservableScrollView observableScrollView) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.clearView = imageView;
        this.content = frameLayout;
        this.scrollRoot = observableScrollView;
    }

    public abstract void setSelf(OverrideSettingsDesign overrideSettingsDesign);
}
