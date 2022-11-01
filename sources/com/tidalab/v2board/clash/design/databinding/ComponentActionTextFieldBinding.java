package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
/* loaded from: classes.dex */
public abstract class ComponentActionTextFieldBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final FrameLayout actionView;
    public final View iconView;
    public final TextView textView;
    public final TextView titleView;

    public ComponentActionTextFieldBinding(Object obj, View view, int i, FrameLayout frameLayout, View view2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.actionView = frameLayout;
        this.iconView = view2;
        this.textView = textView;
        this.titleView = textView2;
    }
}
