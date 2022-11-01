package com.google.android.material.appbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.tidalab.v2board.clash.foss.R;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes.dex */
public class MaterialToolbar extends Toolbar {
    public Integer navigationIconTint;

    public MaterialToolbar(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, R.attr.toolbarStyle, 2131886826), attributeSet, R.attr.toolbarStyle);
        Context context2 = getContext();
        int i = 0;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialToolbar, R.attr.toolbarStyle, 2131886826, new int[0]);
        if (obtainStyledAttributes.hasValue(0)) {
            setNavigationIconTint(obtainStyledAttributes.getColor(0, -1));
        }
        obtainStyledAttributes.recycle();
        Drawable background = getBackground();
        if (background == null || (background instanceof ColorDrawable)) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            materialShapeDrawable.setFillColor(ColorStateList.valueOf(background != null ? ((ColorDrawable) background).getColor() : i));
            materialShapeDrawable.drawableState.elevationOverlayProvider = new ElevationOverlayProvider(context2);
            materialShapeDrawable.updateZ();
            AtomicInteger atomicInteger = ViewCompat.sNextGeneratedId;
            materialShapeDrawable.setElevation(getElevation());
            setBackground(materialShapeDrawable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Drawable background = getBackground();
        if (background instanceof MaterialShapeDrawable) {
            R$style.setParentAbsoluteElevation(this, (MaterialShapeDrawable) background);
        }
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        R$style.setElevation(this, f);
    }

    @Override // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable) {
        if (!(drawable == null || this.navigationIconTint == null)) {
            drawable = AppOpsManagerCompat.wrap(drawable);
            drawable.setTint(this.navigationIconTint.intValue());
        }
        super.setNavigationIcon(drawable);
    }

    public void setNavigationIconTint(int i) {
        this.navigationIconTint = Integer.valueOf(i);
        Drawable navigationIcon = getNavigationIcon();
        if (navigationIcon != null) {
            setNavigationIcon(navigationIcon);
        }
    }
}
