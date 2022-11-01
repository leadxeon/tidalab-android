package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
/* loaded from: classes.dex */
public abstract class DialogTextFieldBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final TextInputEditText textField;
    public final TextInputLayout textLayout;

    public DialogTextFieldBinding(Object obj, View view, int i, TextInputEditText textInputEditText, TextInputLayout textInputLayout) {
        super(obj, view, i);
        this.textField = textInputEditText;
        this.textLayout = textInputLayout;
    }
}
