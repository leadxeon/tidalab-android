package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
@SuppressLint({"AppCompatCustomView"})
/* loaded from: classes.dex */
public class VisibilityAwareImageButton extends ImageButton {
    public int userSetVisibility = getVisibility();

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }

    public final void internalSetVisibility(int i, boolean z) {
        super.setVisibility(i);
        if (z) {
            this.userSetVisibility = i;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        this.userSetVisibility = i;
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
