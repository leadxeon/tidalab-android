package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.LogsDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
/* loaded from: classes.dex */
public abstract class DesignLogsBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public final ImageView deleteView;
    public LogsDesign mSelf;
    public final RecyclerView recyclerList;

    public DesignLogsBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, ImageView imageView, RecyclerView recyclerView) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.deleteView = imageView;
        this.recyclerList = recyclerView;
    }

    public abstract void setSelf(LogsDesign logsDesign);
}
