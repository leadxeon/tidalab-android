package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.AccessControlDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
/* loaded from: classes.dex */
public abstract class DesignAccessControlBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public AccessControlDesign mSelf;
    public final CommonRecyclerListBinding mainList;
    public final ImageView menuView;
    public final ImageView searchView;

    public DesignAccessControlBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, CommonRecyclerListBinding commonRecyclerListBinding, ImageView imageView, ImageView imageView2) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.mainList = commonRecyclerListBinding;
        this.menuView = imageView;
        this.searchView = imageView2;
    }

    public abstract void setSelf(AccessControlDesign accessControlDesign);
}
