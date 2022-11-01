package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.model.File;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
/* loaded from: classes.dex */
public abstract class AdapterFileBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final TextView elapsedView;
    public final View iconView;
    public ObservableCurrentTime mCurrentTime;
    public File mFile;
    public View.OnClickListener mMore;
    public View.OnClickListener mOpen;
    public final FrameLayout menuView;
    public final RelativeLayout rootView;

    public AdapterFileBinding(Object obj, View view, int i, TextView textView, View view2, FrameLayout frameLayout, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.elapsedView = textView;
        this.iconView = view2;
        this.menuView = frameLayout;
        this.rootView = relativeLayout;
    }

    public abstract void setCurrentTime(ObservableCurrentTime observableCurrentTime);

    public abstract void setFile(File file);

    public abstract void setMore(View.OnClickListener onClickListener);

    public abstract void setOpen(View.OnClickListener onClickListener);
}
