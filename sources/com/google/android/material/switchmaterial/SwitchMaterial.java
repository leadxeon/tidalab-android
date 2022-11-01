package com.google.android.material.switchmaterial;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
/* loaded from: classes.dex */
public class SwitchMaterial extends SwitchCompat {
    public static final int[][] ENABLED_CHECKED_STATES = {new int[]{16842910, 16842912}, new int[]{16842910, -16842912}, new int[]{-16842910, 16842912}, new int[]{-16842910, -16842912}};
    public final ElevationOverlayProvider elevationOverlayProvider;
    public ColorStateList materialThemeColorsThumbTintList;
    public ColorStateList materialThemeColorsTrackTintList;
    public boolean useMaterialThemeColors;

    public SwitchMaterial(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.switchStyle, 2131886763), attributeSet, R.attr.switchStyle);
        Context context2 = getContext();
        this.elevationOverlayProvider = new ElevationOverlayProvider(context2);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.SwitchMaterial, R.attr.switchStyle, 2131886763, new int[0]);
        this.useMaterialThemeColors = obtainStyledAttributes.getBoolean(0, false);
        obtainStyledAttributes.recycle();
    }

    private ColorStateList getMaterialThemeColorsThumbTintList() {
        if (this.materialThemeColorsThumbTintList == null) {
            int color = R$style.getColor(this, R.attr.colorSurface);
            int color2 = R$style.getColor(this, R.attr.colorControlActivated);
            float dimension = getResources().getDimension(R.dimen.mtrl_switch_thumb_elevation);
            if (this.elevationOverlayProvider.elevationOverlayEnabled) {
                dimension += R$style.getParentAbsoluteElevation(this);
            }
            int compositeOverlayIfNeeded = this.elevationOverlayProvider.compositeOverlayIfNeeded(color, dimension);
            int[][] iArr = ENABLED_CHECKED_STATES;
            int[] iArr2 = new int[iArr.length];
            iArr2[0] = R$style.layer(color, color2, 1.0f);
            iArr2[1] = compositeOverlayIfNeeded;
            iArr2[2] = R$style.layer(color, color2, 0.38f);
            iArr2[3] = compositeOverlayIfNeeded;
            this.materialThemeColorsThumbTintList = new ColorStateList(iArr, iArr2);
        }
        return this.materialThemeColorsThumbTintList;
    }

    private ColorStateList getMaterialThemeColorsTrackTintList() {
        if (this.materialThemeColorsTrackTintList == null) {
            int[][] iArr = ENABLED_CHECKED_STATES;
            int[] iArr2 = new int[iArr.length];
            int color = R$style.getColor(this, R.attr.colorSurface);
            int color2 = R$style.getColor(this, R.attr.colorControlActivated);
            int color3 = R$style.getColor(this, R.attr.colorOnSurface);
            iArr2[0] = R$style.layer(color, color2, 0.54f);
            iArr2[1] = R$style.layer(color, color3, 0.32f);
            iArr2[2] = R$style.layer(color, color2, 0.12f);
            iArr2[3] = R$style.layer(color, color3, 0.12f);
            this.materialThemeColorsTrackTintList = new ColorStateList(iArr, iArr2);
        }
        return this.materialThemeColorsTrackTintList;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && getThumbTintList() == null) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
        }
        if (this.useMaterialThemeColors && getTrackTintList() == null) {
            setTrackTintList(getMaterialThemeColorsTrackTintList());
        }
    }

    public void setUseMaterialThemeColors(boolean z) {
        this.useMaterialThemeColors = z;
        if (z) {
            setThumbTintList(getMaterialThemeColorsThumbTintList());
            setTrackTintList(getMaterialThemeColorsTrackTintList());
            return;
        }
        setThumbTintList(null);
        setTrackTintList(null);
    }
}
