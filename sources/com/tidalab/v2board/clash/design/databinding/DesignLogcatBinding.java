package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.LogcatDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
/* loaded from: classes.dex */
public abstract class DesignLogcatBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final FrameLayout actionLayout;
    public final ActivityBarLayout activityBarLayout;
    public LogcatDesign mSelf;
    public boolean mStreaming;
    public final AppRecyclerView recyclerList;

    public DesignLogcatBinding(Object obj, View view, int i, FrameLayout frameLayout, ActivityBarLayout activityBarLayout, AppRecyclerView appRecyclerView) {
        super(obj, view, i);
        this.actionLayout = frameLayout;
        this.activityBarLayout = activityBarLayout;
        this.recyclerList = appRecyclerView;
    }

    public abstract void setSelf(LogcatDesign logcatDesign);

    public abstract void setStreaming(boolean z);
}
