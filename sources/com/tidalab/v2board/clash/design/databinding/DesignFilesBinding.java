package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.FilesDesign;
import com.tidalab.v2board.clash.design.view.ActivityBarLayout;
/* loaded from: classes.dex */
public abstract class DesignFilesBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ActivityBarLayout activityBarLayout;
    public boolean mConfigurationEditable;
    public boolean mCurrentInBaseDir;
    public FilesDesign mSelf;
    public final CommonRecyclerListBinding mainList;
    public final ImageView newView;

    public DesignFilesBinding(Object obj, View view, int i, ActivityBarLayout activityBarLayout, CommonRecyclerListBinding commonRecyclerListBinding, ImageView imageView) {
        super(obj, view, i);
        this.activityBarLayout = activityBarLayout;
        this.mainList = commonRecyclerListBinding;
        this.newView = imageView;
    }

    public abstract void setConfigurationEditable(boolean z);

    public abstract void setCurrentInBaseDir(boolean z);

    public abstract void setSelf(FilesDesign filesDesign);
}
