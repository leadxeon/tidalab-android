package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
/* loaded from: classes.dex */
public abstract class AdapterEditableTextMapBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final ImageView deleteView;
    public final TextView keyView;
    public final TextView valueView;

    public AdapterEditableTextMapBinding(Object obj, View view, int i, ImageView imageView, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.deleteView = imageView;
        this.keyView = textView;
        this.valueView = textView2;
    }
}
