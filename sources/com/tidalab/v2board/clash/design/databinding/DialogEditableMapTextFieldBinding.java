package com.tidalab.v2board.clash.design.databinding;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.textfield.TextInputEditText;
/* loaded from: classes.dex */
public abstract class DialogEditableMapTextFieldBinding extends ViewDataBinding {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final TextInputEditText keyView;
    public final TextInputEditText valueView;

    public DialogEditableMapTextFieldBinding(Object obj, View view, int i, TextInputEditText textInputEditText, TextInputEditText textInputEditText2) {
        super(obj, view, i);
        this.keyView = textInputEditText;
        this.valueView = textInputEditText2;
    }
}
