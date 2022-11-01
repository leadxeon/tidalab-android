package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
/* loaded from: classes.dex */
public abstract class DesignProfilesBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public final ImageView addView;
    public ProfilesDesign mSelf;
    public final CommonRecyclerListBinding mainList;
    public final FrameLayout updateLayout;
    public final ImageView updateView;

    public DesignProfilesBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, ImageView imageView, CommonRecyclerListBinding commonRecyclerListBinding, FrameLayout frameLayout, ImageView imageView2) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.addView = imageView;
        this.mainList = commonRecyclerListBinding;
        this.updateLayout = frameLayout;
        this.updateView = imageView2;
    }

    public abstract void setSelf(ProfilesDesign profilesDesign);
}
