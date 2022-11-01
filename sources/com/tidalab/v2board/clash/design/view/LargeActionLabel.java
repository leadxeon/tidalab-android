package com.tidalab.v2board.clash.design.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.R$styleable;
import com.tidalab.v2board.clash.design.databinding.ComponentLargeActionLabelBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
/* compiled from: LargeActionLabel.kt */
/* loaded from: classes.dex */
public final class LargeActionLabel extends FrameLayout {
    public final ComponentLargeActionLabelBinding binding;

    public LargeActionLabel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        LayoutInflater from = LayoutInflater.from(context);
        int i = ComponentLargeActionLabelBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        this.binding = (ComponentLargeActionLabelBinding) ViewDataBinding.inflateInternal(from, R.layout.component_large_action_label, this, true, null);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.Clickable, 0, 0);
        setFocusable(obtainStyledAttributes.getBoolean(1, true));
        setClickable(obtainStyledAttributes.getBoolean(2, true));
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        setBackground(drawable == null ? PathParser.getDrawableCompat(context, InputKt.resolveThemedResourceId(context, 16843534)) : drawable);
        Unit unit = Unit.INSTANCE;
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.LargeActionLabel, 0, 0);
        try {
            setIcon(obtainStyledAttributes2.getDrawable(0));
            setText(obtainStyledAttributes2.getString(2));
            setSubtext(obtainStyledAttributes2.getString(1));
        } finally {
            obtainStyledAttributes2.recycle();
        }
    }

    public final Drawable getIcon() {
        return this.binding.iconView.getBackground();
    }

    public final CharSequence getSubtext() {
        return this.binding.subtextView.getText();
    }

    public final CharSequence getText() {
        return this.binding.textView.getText();
    }

    public final void setIcon(Drawable drawable) {
        this.binding.iconView.setBackground(drawable);
    }

    public final void setSubtext(CharSequence charSequence) {
        this.binding.subtextView.setText(charSequence);
        if (charSequence == null) {
            this.binding.subtextView.setVisibility(8);
        } else {
            this.binding.subtextView.setVisibility(0);
        }
    }

    public final void setText(CharSequence charSequence) {
        this.binding.textView.setText(charSequence);
    }
}
