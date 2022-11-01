package com.tidalab.v2board.clash.design.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
/* compiled from: AppRecyclerView.kt */
/* loaded from: classes.dex */
public final class AppRecyclerView extends RecyclerView {
    public AppRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setFocusable(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
