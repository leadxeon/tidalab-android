package com.google.android.material.textfield;

import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.content.res.AppCompatResources;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class PasswordToggleEndIconDelegate extends EndIconDelegate {
    public final TextWatcher textWatcher = new TextWatcherAdapter() { // from class: com.google.android.material.textfield.PasswordToggleEndIconDelegate.1
        @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            PasswordToggleEndIconDelegate passwordToggleEndIconDelegate = PasswordToggleEndIconDelegate.this;
            passwordToggleEndIconDelegate.endIconView.setChecked(!PasswordToggleEndIconDelegate.access$000(passwordToggleEndIconDelegate));
        }
    };
    public final TextInputLayout.OnEditTextAttachedListener onEditTextAttachedListener = new TextInputLayout.OnEditTextAttachedListener() { // from class: com.google.android.material.textfield.PasswordToggleEndIconDelegate.2
        @Override // com.google.android.material.textfield.TextInputLayout.OnEditTextAttachedListener
        public void onEditTextAttached(TextInputLayout textInputLayout) {
            EditText editText = textInputLayout.getEditText();
            textInputLayout.setEndIconVisible(true);
            textInputLayout.setEndIconCheckable(true);
            PasswordToggleEndIconDelegate passwordToggleEndIconDelegate = PasswordToggleEndIconDelegate.this;
            passwordToggleEndIconDelegate.endIconView.setChecked(!PasswordToggleEndIconDelegate.access$000(passwordToggleEndIconDelegate));
            editText.removeTextChangedListener(PasswordToggleEndIconDelegate.this.textWatcher);
            editText.addTextChangedListener(PasswordToggleEndIconDelegate.this.textWatcher);
        }
    };
    public final TextInputLayout.OnEndIconChangedListener onEndIconChangedListener = new TextInputLayout.OnEndIconChangedListener() { // from class: com.google.android.material.textfield.PasswordToggleEndIconDelegate.3
        @Override // com.google.android.material.textfield.TextInputLayout.OnEndIconChangedListener
        public void onEndIconChanged(TextInputLayout textInputLayout, int i) {
            final EditText editText = textInputLayout.getEditText();
            if (editText != null && i == 1) {
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                editText.post(new Runnable() { // from class: com.google.android.material.textfield.PasswordToggleEndIconDelegate.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        editText.removeTextChangedListener(PasswordToggleEndIconDelegate.this.textWatcher);
                    }
                });
            }
        }
    };

    public PasswordToggleEndIconDelegate(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    public static boolean access$000(PasswordToggleEndIconDelegate passwordToggleEndIconDelegate) {
        EditText editText = passwordToggleEndIconDelegate.textInputLayout.getEditText();
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public void initialize() {
        this.textInputLayout.setEndIconDrawable(AppCompatResources.getDrawable(this.context, R.drawable.design_password_eye));
        TextInputLayout textInputLayout = this.textInputLayout;
        textInputLayout.setEndIconContentDescription(textInputLayout.getResources().getText(R.string.password_toggle_content_description));
        this.textInputLayout.setEndIconOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.textfield.PasswordToggleEndIconDelegate.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditText editText = PasswordToggleEndIconDelegate.this.textInputLayout.getEditText();
                if (editText != null) {
                    int selectionEnd = editText.getSelectionEnd();
                    if (PasswordToggleEndIconDelegate.access$000(PasswordToggleEndIconDelegate.this)) {
                        editText.setTransformationMethod(null);
                    } else {
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                    if (selectionEnd >= 0) {
                        editText.setSelection(selectionEnd);
                    }
                    PasswordToggleEndIconDelegate.this.textInputLayout.refreshEndIconDrawableState();
                }
            }
        });
        this.textInputLayout.addOnEditTextAttachedListener(this.onEditTextAttachedListener);
        this.textInputLayout.endIconChangedListeners.add(this.onEndIconChangedListener);
        EditText editText = this.textInputLayout.getEditText();
        if (editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224)) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
