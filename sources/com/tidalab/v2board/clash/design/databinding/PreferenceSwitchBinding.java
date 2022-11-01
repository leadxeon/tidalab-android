package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;
/* loaded from: classes.dex */
public abstract class PreferenceSwitchBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final View iconView;
    public final TextView summaryView;
    public final SwitchMaterial switchView;
    public final TextView titleView;

    public PreferenceSwitchBinding(Object obj, View view, int i, View view2, TextView textView, SwitchMaterial switchMaterial, TextView textView2) {
        super(obj, view, i);
        this.iconView = view2;
        this.summaryView = textView;
        this.switchView = switchMaterial;
        this.titleView = textView2;
    }
}
