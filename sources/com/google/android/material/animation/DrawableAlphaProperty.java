package com.google.android.material.animation;

import android.graphics.drawable.Drawable;
import android.util.Property;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class DrawableAlphaProperty extends Property<Drawable, Integer> {
    public static final Property<Drawable, Integer> DRAWABLE_ALPHA_COMPAT = new DrawableAlphaProperty();
    public final WeakHashMap<Drawable, Integer> alphaCache = new WeakHashMap<>();

    public DrawableAlphaProperty() {
        super(Integer.class, "drawableAlphaCompat");
    }

    @Override // android.util.Property
    public Integer get(Drawable drawable) {
        return Integer.valueOf(drawable.getAlpha());
    }

    @Override // android.util.Property
    public void set(Drawable drawable, Integer num) {
        drawable.setAlpha(num.intValue());
    }
}
