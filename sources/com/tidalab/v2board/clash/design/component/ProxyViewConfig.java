package com.tidalab.v2board.clash.design.component;

import android.content.Context;
import android.graphics.Color;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
/* compiled from: ProxyViewConfig.kt */
/* loaded from: classes.dex */
public final class ProxyViewConfig {
    public float cardOffset;
    public final float cardRadius;
    public final int clickableBackground;
    public final int colorSurface;
    public final float contentPadding;
    public final Context context;
    public final float layoutPadding;
    public final int selectedBackground;
    public final int selectedControl;
    public final int shadow = Color.argb(21, Color.red(-12303292), Color.green(-12303292), Color.blue(-12303292));
    public boolean singleLine;
    public final int textMargin;
    public final float textSize;
    public final int unselectedControl;

    public ProxyViewConfig(Context context, boolean z) {
        this.context = context;
        this.singleLine = z;
        this.colorSurface = InputKt.resolveThemedColor(context, R.attr.colorSurface);
        this.clickableBackground = InputKt.resolveThemedResourceId(context, 16843534);
        this.selectedControl = InputKt.resolveThemedColor(context, R.attr.colorOnPrimary);
        this.selectedBackground = InputKt.resolveThemedColor(context, R.attr.colorPrimary);
        this.unselectedControl = InputKt.resolveThemedColor(context, R.attr.colorOnSurface);
        this.layoutPadding = InputKt.getPixels(context, R.dimen.proxy_layout_padding);
        this.contentPadding = InputKt.getPixels(context, R.dimen.proxy_content_padding);
        this.textMargin = InputKt.getPixels(context, R.dimen.proxy_text_margin);
        this.textSize = InputKt.getPixels(context, R.dimen.proxy_text_size);
        this.cardRadius = InputKt.getPixels(context, R.dimen.proxy_card_radius);
        this.cardOffset = InputKt.getPixels(context, R.dimen.proxy_card_offset);
    }

    public final int getUnselectedBackground() {
        if (this.singleLine) {
            return 0;
        }
        return this.colorSurface;
    }
}
