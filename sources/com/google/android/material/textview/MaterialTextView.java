package com.google.android.material.textview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class MaterialTextView extends AppCompatTextView {
    public MaterialTextView(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, 16842884, 0), attributeSet, 16842884);
        Context context2 = getContext();
        boolean z = true;
        if (R$style.resolveBoolean(context2, R.attr.textAppearanceLineHeightEnabled, true)) {
            Resources.Theme theme = context2.getTheme();
            int[] iArr = R$styleable.MaterialTextView;
            TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
            int readFirstAvailableDimension = readFirstAvailableDimension(context2, obtainStyledAttributes, 1, 2);
            obtainStyledAttributes.recycle();
            if (!(readFirstAvailableDimension == -1 ? false : z)) {
                TypedArray obtainStyledAttributes2 = theme.obtainStyledAttributes(attributeSet, iArr, 16842884, 0);
                int resourceId = obtainStyledAttributes2.getResourceId(0, -1);
                obtainStyledAttributes2.recycle();
                if (resourceId != -1) {
                    applyLineHeightFromViewAppearance(theme, resourceId);
                }
            }
        }
    }

    public static int readFirstAvailableDimension(Context context, TypedArray typedArray, int... iArr) {
        int i = -1;
        for (int i2 = 0; i2 < iArr.length && i < 0; i2++) {
            i = R$style.getDimensionPixelSize(context, typedArray, iArr[i2], -1);
        }
        return i;
    }

    public final void applyLineHeightFromViewAppearance(Resources.Theme theme, int i) {
        TypedArray obtainStyledAttributes = theme.obtainStyledAttributes(i, R$styleable.MaterialTextAppearance);
        int readFirstAvailableDimension = readFirstAvailableDimension(getContext(), obtainStyledAttributes, 1, 2);
        obtainStyledAttributes.recycle();
        if (readFirstAvailableDimension >= 0) {
            setLineHeight(readFirstAvailableDimension);
        }
    }

    @Override // androidx.appcompat.widget.AppCompatTextView, android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        if (R$style.resolveBoolean(context, R.attr.textAppearanceLineHeightEnabled, true)) {
            applyLineHeightFromViewAppearance(context.getTheme(), i);
        }
    }
}
