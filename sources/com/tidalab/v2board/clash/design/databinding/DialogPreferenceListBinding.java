package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.tidalab.v2board.clash.design.ui.Surface;
/* loaded from: classes.dex */
public abstract class DialogPreferenceListBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final Button cancelView;
    public Surface mSurface;
    public final RecyclerView mainList;
    public final ImageView newView;
    public final Button okView;
    public final Button resetView;
    public final TextView titleView;

    public DialogPreferenceListBinding(Object obj, View view, int i, Button button, RecyclerView recyclerView, ImageView imageView, Button button2, Button button3, TextView textView) {
        super(obj, view, i);
        this.cancelView = button;
        this.mainList = recyclerView;
        this.newView = imageView;
        this.okView = button2;
        this.resetView = button3;
        this.titleView = textView;
    }

    public abstract void setSurface(Surface surface);
}
