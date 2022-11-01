package com.google.android.material.progressindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.material.R$styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public final class LinearProgressIndicatorSpec extends BaseProgressIndicatorSpec {
    public boolean drawHorizontallyInverse;
    public int indeterminateAnimationType;
    public int indicatorDirection;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinearProgressIndicatorSpec(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.linearProgressIndicatorStyle, 2131886768);
        int i = LinearProgressIndicator.$r8$clinit;
        int[] iArr = R$styleable.LinearProgressIndicator;
        boolean z = false;
        ThemeEnforcement.checkCompatibleTheme(context, attributeSet, R.attr.linearProgressIndicatorStyle, 2131886768);
        ThemeEnforcement.checkTextAppearance(context, attributeSet, iArr, R.attr.linearProgressIndicatorStyle, 2131886768, new int[0]);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.linearProgressIndicatorStyle, 2131886768);
        this.indeterminateAnimationType = obtainStyledAttributes.getInt(0, 1);
        this.indicatorDirection = obtainStyledAttributes.getInt(1, 0);
        obtainStyledAttributes.recycle();
        validateSpec();
        this.drawHorizontallyInverse = this.indicatorDirection == 1 ? true : z;
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicatorSpec
    public void validateSpec() {
        if (this.indeterminateAnimationType != 0) {
            return;
        }
        if (this.trackCornerRadius > 0) {
            throw new IllegalArgumentException("Rounded corners are not supported in contiguous indeterminate animation.");
        } else if (this.indicatorColors.length < 3) {
            throw new IllegalArgumentException("Contiguous indeterminate animation must be used with 3 or more indicator colors.");
        }
    }
}
