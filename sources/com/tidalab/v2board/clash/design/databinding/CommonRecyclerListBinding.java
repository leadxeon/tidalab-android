package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ui.Insets;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
/* loaded from: classes.dex */
public abstract class CommonRecyclerListBinding extends ViewDataBinding {
    public Insets mInsets;
    public final AppRecyclerView recyclerList;

    public CommonRecyclerListBinding(Object obj, View view, int i, AppRecyclerView appRecyclerView) {
        super(obj, view, i);
        this.recyclerList = appRecyclerView;
    }

    public abstract void setInsets(Insets insets);
}
