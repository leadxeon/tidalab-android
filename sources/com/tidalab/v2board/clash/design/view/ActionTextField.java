package com.tidalab.v2board.clash.design.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.tidalab.v2board.clash.design.R$styleable;
import com.tidalab.v2board.clash.design.databinding.ComponentActionTextFieldBinding;
import com.tidalab.v2board.clash.foss.R;
/* compiled from: ActionTextField.kt */
/* loaded from: classes.dex */
public final class ActionTextField extends FrameLayout {
    public final ComponentActionTextFieldBinding binding;

    public ActionTextField(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        LayoutInflater from = LayoutInflater.from(context);
        int i = ComponentActionTextFieldBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        this.binding = (ComponentActionTextFieldBinding) ViewDataBinding.inflateInternal(from, R.layout.component_action_text_field, this, true, null);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.ActionTextField, 0, 0);
        try {
            setEnabled(obtainStyledAttributes.getBoolean(0, true));
            setIcon(obtainStyledAttributes.getDrawable(1));
            setTitle(obtainStyledAttributes.getString(4));
            setText(obtainStyledAttributes.getString(3));
            setPlaceholder(obtainStyledAttributes.getString(2));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public final Drawable getIcon() {
        return this.binding.iconView.getBackground();
    }

    public final CharSequence getPlaceholder() {
        return this.binding.textView.getHint();
    }

    public final CharSequence getText() {
        return this.binding.textView.getText();
    }

    public final CharSequence getTitle() {
        return this.binding.titleView.getText();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (z) {
            this.binding.mRoot.setAlpha(1.0f);
            this.binding.actionView.setFocusable(true);
            this.binding.actionView.setClickable(true);
        } else {
            this.binding.mRoot.setAlpha(0.33f);
            this.binding.actionView.setFocusable(false);
            this.binding.actionView.setClickable(false);
        }
        setText(getText());
    }

    public final void setIcon(Drawable drawable) {
        this.binding.iconView.setBackground(drawable);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.binding.actionView.setOnClickListener(onClickListener);
    }

    public final void setPlaceholder(CharSequence charSequence) {
        this.binding.textView.setHint(charSequence);
    }

    public final void setText(CharSequence charSequence) {
        if (isEnabled()) {
            this.binding.textView.setText(charSequence);
        } else {
            this.binding.textView.setText(getContext().getText(R.string.unavailable));
        }
    }

    public final void setTitle(CharSequence charSequence) {
        this.binding.titleView.setText(charSequence);
    }
}
