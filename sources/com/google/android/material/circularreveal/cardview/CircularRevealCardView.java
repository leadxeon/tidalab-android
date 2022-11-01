package com.google.android.material.circularreveal.cardview;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.circularreveal.CircularRevealWidget;
/* loaded from: classes.dex */
public class CircularRevealCardView extends MaterialCardView implements CircularRevealWidget {
    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void buildCircularRevealCache() {
        throw null;
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void destroyCircularRevealCache() {
        throw null;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public Drawable getCircularRevealOverlayDrawable() {
        throw null;
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public int getCircularRevealScrimColor() {
        throw null;
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public CircularRevealWidget.RevealInfo getRevealInfo() {
        throw null;
    }

    @Override // android.view.View
    public boolean isOpaque() {
        return super.isOpaque();
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void setCircularRevealOverlayDrawable(Drawable drawable) {
        throw null;
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void setCircularRevealScrimColor(int i) {
        throw null;
    }

    @Override // com.google.android.material.circularreveal.CircularRevealWidget
    public void setRevealInfo(CircularRevealWidget.RevealInfo revealInfo) {
        throw null;
    }
}
