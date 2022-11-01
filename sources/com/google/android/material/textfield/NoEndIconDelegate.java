package com.google.android.material.textfield;

import android.graphics.drawable.Drawable;
/* loaded from: classes.dex */
public class NoEndIconDelegate extends EndIconDelegate {
    public NoEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void initialize() {
        this.textInputLayout.setEndIconOnClickListener(null);
        this.textInputLayout.setEndIconDrawable((Drawable) null);
        this.textInputLayout.setEndIconContentDescription((CharSequence) null);
    }
}
