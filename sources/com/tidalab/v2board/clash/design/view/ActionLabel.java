package com.tidalab.v2board.clash.design.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.tidalab.v2board.clash.design.databinding.ComponentActionLabelBinding;
/* compiled from: ActionLabel.kt */
/* loaded from: classes.dex */
public final class ActionLabel extends FrameLayout {
    public final ComponentActionLabelBinding binding;

    public ActionLabel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0, 12);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ActionLabel(android.content.Context r4, android.util.AttributeSet r5, int r6, int r7, int r8) {
        /*
            r3 = this;
            r0 = r8 & 2
            r1 = 0
            if (r0 == 0) goto L_0x0006
            r5 = r1
        L_0x0006:
            r0 = r8 & 4
            r2 = 0
            if (r0 == 0) goto L_0x000c
            r6 = 0
        L_0x000c:
            r8 = r8 & 8
            if (r8 == 0) goto L_0x0011
            r7 = 0
        L_0x0011:
            r3.<init>(r4, r5, r6, r7)
            android.view.LayoutInflater r8 = android.view.LayoutInflater.from(r4)
            int r0 = com.tidalab.v2board.clash.design.databinding.ComponentActionLabelBinding.$r8$clinit
            androidx.databinding.DataBinderMapper r0 = androidx.databinding.DataBindingUtil.sMapper
            r0 = 2131492903(0x7f0c0027, float:1.8609271E38)
            r2 = 1
            androidx.databinding.ViewDataBinding r8 = androidx.databinding.ViewDataBinding.inflateInternal(r8, r0, r3, r2, r1)
            com.tidalab.v2board.clash.design.databinding.ComponentActionLabelBinding r8 = (com.tidalab.v2board.clash.design.databinding.ComponentActionLabelBinding) r8
            r3.binding = r8
            android.content.res.Resources$Theme r4 = r4.getTheme()
            int[] r8 = com.tidalab.v2board.clash.design.R$styleable.ActionLabel
            android.content.res.TypedArray r4 = r4.obtainStyledAttributes(r5, r8, r6, r7)
            android.graphics.drawable.Drawable r5 = r4.getDrawable(r2)     // Catch: all -> 0x004d
            r3.setIcon(r5)     // Catch: all -> 0x004d
            r5 = 3
            java.lang.String r5 = r4.getString(r5)     // Catch: all -> 0x004d
            r3.setText(r5)     // Catch: all -> 0x004d
            r5 = 2
            java.lang.String r5 = r4.getString(r5)     // Catch: all -> 0x004d
            r3.setSubtext(r5)     // Catch: all -> 0x004d
            r4.recycle()
            return
        L_0x004d:
            r5 = move-exception
            r4.recycle()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.design.view.ActionLabel.<init>(android.content.Context, android.util.AttributeSet, int, int, int):void");
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

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.binding.mRoot.setOnClickListener(onClickListener);
    }

    public final void setSubtext(CharSequence charSequence) {
        this.binding.subtextView.setText(charSequence);
        this.binding.subtextView.setVisibility(charSequence == null ? 8 : 0);
    }

    public final void setText(CharSequence charSequence) {
        this.binding.textView.setText(charSequence);
    }
}
