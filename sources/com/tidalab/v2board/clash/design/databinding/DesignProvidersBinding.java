package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ProvidersDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
/* loaded from: classes.dex */
public abstract class DesignProvidersBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public ProvidersDesign mSelf;
    public final CommonRecyclerListBinding mainList;
    public final ImageView updateView;

    public DesignProvidersBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, CommonRecyclerListBinding commonRecyclerListBinding, ImageView imageView) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.mainList = commonRecyclerListBinding;
        this.updateView = imageView;
    }

    public abstract void setSelf(ProvidersDesign providersDesign);
}
