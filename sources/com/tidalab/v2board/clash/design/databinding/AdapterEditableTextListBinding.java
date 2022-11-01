package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
/* loaded from: classes.dex */
public abstract class AdapterEditableTextListBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ImageView deleteView;
    public final TextView textView;

    public AdapterEditableTextListBinding(Object obj, View view, int i, ImageView imageView, TextView textView) {
        super(obj, view, i);
        this.deleteView = imageView;
        this.textView = textView;
    }
}
