package com.tidalab.v2board.clash.design.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.R$styleable;
import com.tidalab.v2board.clash.design.databinding.ComponentLargeActionLabelBinding;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
/* compiled from: LargeActionCard.kt */
/* loaded from: classes.dex */
public final class LargeActionCard extends MaterialCardView {
    public final ComponentLargeActionLabelBinding binding;

    /* JADX WARN: Finally extract failed */
    public LargeActionCard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        LayoutInflater from = LayoutInflater.from(context);
        int i = ComponentLargeActionLabelBinding.$r8$clinit;
        DataBinderMapper dataBinderMapper = DataBindingUtil.sMapper;
        this.binding = (ComponentLargeActionLabelBinding) ViewDataBinding.inflateInternal(from, R.layout.component_large_action_label, this, true, null);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.Clickable, 0, 0);
        setFocusable(obtainStyledAttributes.getBoolean(1, true));
        setClickable(obtainStyledAttributes.getBoolean(2, true));
        Drawable drawable = obtainStyledAttributes.getDrawable(1);
        setForeground(drawable == null ? PathParser.getDrawableCompat(context, InputKt.resolveThemedResourceId(context, 16843534)) : drawable);
        Unit unit = Unit.INSTANCE;
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.LargeActionCard, 0, 0);
        try {
            setIcon(obtainStyledAttributes2.getDrawable(0));
            setText(obtainStyledAttributes2.getString(2));
            setSubtext(obtainStyledAttributes2.getString(1));
            obtainStyledAttributes2.recycle();
            setMinimumHeight(InputKt.getPixels(context, R.dimen.large_action_card_min_height));
            setRadius(InputKt.getPixels(context, R.dimen.large_action_card_radius));
            setElevation(InputKt.getPixels(context, R.dimen.large_action_card_elevation));
            setCardBackgroundColor(InputKt.resolveThemedColor(context, R.attr.colorSurface));
        } catch (Throwable th) {
            obtainStyledAttributes2.recycle();
            throw th;
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
    }

    public final void setText(CharSequence charSequence) {
        this.binding.textView.setText(charSequence);
    }
}
