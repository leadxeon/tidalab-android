package com.google.android.material.theme;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textview.MaterialTextView;
/* loaded from: classes.dex */
public class MaterialComponentsViewInflater extends AppCompatViewInflater {
    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new MaterialAutoCompleteTextView(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new MaterialButton(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new MaterialCheckBox(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new MaterialRadioButton(context, attributeSet);
    }

    @Override // androidx.appcompat.app.AppCompatViewInflater
    public AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        return new MaterialTextView(context, attributeSet);
    }
}
