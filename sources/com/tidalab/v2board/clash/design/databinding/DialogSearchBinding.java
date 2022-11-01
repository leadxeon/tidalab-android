package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ui.Surface;
import com.tidalab.v2board.clash.design.view.AppRecyclerView;
/* loaded from: classes.dex */
public abstract class DialogSearchBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ImageView closeView;
    public final AppCompatEditText keywordView;
    public Surface mSurface;
    public final AppRecyclerView mainList;

    public DialogSearchBinding(Object obj, View view, int i, ImageView imageView, AppCompatEditText appCompatEditText, AppRecyclerView appRecyclerView) {
        super(obj, view, i);
        this.closeView = imageView;
        this.keywordView = appCompatEditText;
        this.mainList = appRecyclerView;
    }

    public abstract void setSurface(Surface surface);
}
