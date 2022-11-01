package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.NewProfileDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
/* loaded from: classes.dex */
public abstract class DesignNewProfileBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public NewProfileDesign mSelf;
    public final CommonRecyclerListBinding mainList;

    public DesignNewProfileBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, CommonRecyclerListBinding commonRecyclerListBinding) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.mainList = commonRecyclerListBinding;
    }

    public abstract void setSelf(NewProfileDesign newProfileDesign);
}
