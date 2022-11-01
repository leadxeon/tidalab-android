package com.google.android.material.appbar;

import android.view.View;
import androidx.core.view.ViewCompat;
/* loaded from: classes.dex */
public class ViewOffsetHelper {
    public int layoutLeft;
    public int layoutTop;
    public int offsetLeft;
    public int offsetTop;
    public final View view;

    public ViewOffsetHelper(View view) {
        this.view = view;
    }

    public void applyOffsets() {
        View view = this.view;
        ViewCompat.offsetTopAndBottom(view, this.offsetTop - (view.getTop() - this.layoutTop));
        View view2 = this.view;
        ViewCompat.offsetLeftAndRight(view2, this.offsetLeft - (view2.getLeft() - this.layoutLeft));
    }
}
