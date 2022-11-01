package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.ui.ObservableCurrentTime;
import com.tidalab.v2board.clash.service.model.Profile;
/* loaded from: classes.dex */
public abstract class AdapterProfileBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final TextView elapsedView;
    public final RadioButton iconView;
    public View.OnClickListener mClicked;
    public ObservableCurrentTime mCurrentTime;
    public View.OnClickListener mMenu;
    public Profile mProfile;
    public final FrameLayout menuView;
    public final RelativeLayout rootView;

    public AdapterProfileBinding(Object obj, View view, int i, TextView textView, RadioButton radioButton, FrameLayout frameLayout, RelativeLayout relativeLayout) {
        super(obj, view, i);
        this.elapsedView = textView;
        this.iconView = radioButton;
        this.menuView = frameLayout;
        this.rootView = relativeLayout;
    }

    public abstract void setClicked(View.OnClickListener onClickListener);

    public abstract void setCurrentTime(ObservableCurrentTime observableCurrentTime);

    public abstract void setMenu(View.OnClickListener onClickListener);

    public abstract void setProfile(Profile profile);
}
