package com.google.android.material.textfield;

import android.content.Context;
import com.google.android.material.internal.CheckableImageButton;
/* loaded from: classes.dex */
public abstract class EndIconDelegate {
    public Context context;
    public CheckableImageButton endIconView;
    public TextInputLayout textInputLayout;

    public EndIconDelegate(TextInputLayout textInputLayout) {
        this.textInputLayout = textInputLayout;
        this.context = textInputLayout.getContext();
        this.endIconView = textInputLayout.getEndIconView();
    }

    public abstract void initialize();

    public boolean isBoxBackgroundModeSupported(int i) {
        return true;
    }

    public void onSuffixVisibilityChanged(boolean z) {
    }
}
