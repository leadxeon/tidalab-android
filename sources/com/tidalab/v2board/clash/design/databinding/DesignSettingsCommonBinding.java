package com.tidalab.v2board.clash.design.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
import com.tidalab.v2board.clash.design.view.ObservableScrollView;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public abstract class DesignSettingsCommonBinding extends ViewDataBinding {
    public final ActivityBarLayout activityBarLayout;
    public final FrameLayout content;
    public Surface mSurface;
    public final ObservableScrollView scrollRoot;

    public DesignSettingsCommonBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, FrameLayout frameLayout, ObservableScrollView observableScrollView) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.content = frameLayout;
        this.scrollRoot = observableScrollView;
    }

    public static DesignSettingsCommonBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        return (DesignSettingsCommonBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.design_settings_common, viewGroup, z, null);
    }

    public abstract void setSurface(Surface surface);
}
