package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
/* loaded from: classes.dex */
public abstract class PreferenceClickableBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final View iconView;
    public final TextView summaryView;
    public final TextView titleView;

    public PreferenceClickableBinding(Object obj, View view, int i, View view2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.iconView = view2;
        this.summaryView = textView;
        this.titleView = textView2;
    }
}
