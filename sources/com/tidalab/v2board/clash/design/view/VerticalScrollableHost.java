package com.tidalab.v2board.clash.design.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.FrameLayout;
/* compiled from: VerticalScrollableHost.kt */
/* loaded from: classes.dex */
public final class VerticalScrollableHost extends FrameLayout {
    public final double degree;
    public float initialX;
    public float initialY;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VerticalScrollableHost(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        super(context, null, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
        int i4 = i3 & 2;
        this.degree = Math.tan(Math.toRadians(15.0d));
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        ViewParent parent = getParent();
        if (parent == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            this.initialX = motionEvent.getX();
            this.initialY = motionEvent.getY();
            parent.requestDisallowInterceptTouchEvent(true);
        } else if (motionEvent.getAction() == 2) {
            if (Math.abs(motionEvent.getY() - this.initialY) / Math.abs(motionEvent.getX() - this.initialX) < this.degree) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
