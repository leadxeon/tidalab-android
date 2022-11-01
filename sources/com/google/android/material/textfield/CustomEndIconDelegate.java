package com.google.android.material.textfield;
/* loaded from: classes.dex */
public class CustomEndIconDelegate extends EndIconDelegate {
    public CustomEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void initialize() {
        this.textInputLayout.setEndIconOnClickListener(null);
        this.textInputLayout.setEndIconOnLongClickListener(null);
    }
}
