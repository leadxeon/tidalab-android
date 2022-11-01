package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;
/* loaded from: classes.dex */
public abstract class DialogFetchStatusBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final LinearProgressIndicator progressIndicator;
    public final TextView text;

    public DialogFetchStatusBinding(Object obj, View view, int i, LinearProgressIndicator linearProgressIndicator, TextView textView) {
        super(obj, view, i);
        this.progressIndicator = linearProgressIndicator;
        this.text = textView;
    }
}
