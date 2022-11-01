package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
/* loaded from: classes.dex */
public abstract class ComponentActionLabelBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final View iconView;
    public final TextView subtextView;
    public final TextView textView;

    public ComponentActionLabelBinding(Object obj, View view, int i, View view2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.iconView = view2;
        this.subtextView = textView;
        this.textView = textView2;
    }
}
