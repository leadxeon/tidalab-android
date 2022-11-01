package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.PropertiesDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
import com.tidalab.v2board.clash.design.view.ObservableScrollView;
import com.tidalab.v2board.clash.service.model.Profile;
/* loaded from: classes.dex */
public abstract class DesignPropertiesBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final FrameLayout actionLayout;
    public final ActivityBarLayout activityBarLayout;
    public boolean mProcessing;
    public Profile mProfile;
    public PropertiesDesign mSelf;
    public final ObservableScrollView scrollRoot;
    public final TextView tips;

    public DesignPropertiesBinding(Object obj, View view, int i, FrameLayout frameLayout, ActivityBarLayout activityBarLayout, ObservableScrollView observableScrollView, TextView textView) {
        super(obj, view, i);
        this.actionLayout = frameLayout;
        this.activityBarLayout = activityBarLayout;
        this.scrollRoot = observableScrollView;
        this.tips = textView;
    }

    public abstract void setProcessing(boolean z);

    public abstract void setProfile(Profile profile);

    public abstract void setSelf(PropertiesDesign propertiesDesign);
}
