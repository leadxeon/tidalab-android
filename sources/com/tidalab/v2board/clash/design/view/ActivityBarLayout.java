package com.tidalab.v2board.clash.design.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.tidalab.v2board.clash.design.dialog.InputKt;
/* compiled from: ActivityBarLayout.kt */
/* loaded from: classes.dex */
public final class ActivityBarLayout extends FrameLayout {
    public ActivityBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        setAlpha(0.96f);
        setBackgroundColor(InputKt.resolveThemedColor(context, 16842836));
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        return true;
    }
}
