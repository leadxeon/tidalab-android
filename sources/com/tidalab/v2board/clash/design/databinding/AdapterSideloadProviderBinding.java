package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.tidalab.v2board.clash.design.model.AppInfo;
/* loaded from: classes.dex */
public abstract class AdapterSideloadProviderBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final View iconView;
    public final TextView labelView;
    public AppInfo mAppInfo;
    public boolean mSelected;
    public final TextView packageNameView;
    public final MaterialRadioButton selectedView;

    public AdapterSideloadProviderBinding(Object obj, View view, int i, View view2, TextView textView, TextView textView2, MaterialRadioButton materialRadioButton) {
        super(obj, view, i);
        this.iconView = view2;
        this.labelView = textView;
        this.packageNameView = textView2;
        this.selectedView = materialRadioButton;
    }

    public abstract void setAppInfo(AppInfo appInfo);

    public abstract void setSelected(boolean z);
}
