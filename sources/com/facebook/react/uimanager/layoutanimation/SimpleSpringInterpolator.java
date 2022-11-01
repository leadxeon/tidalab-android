package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Interpolator;
/* loaded from: classes.dex */
public class SimpleSpringInterpolator implements Interpolator {
    public final float mSpringDamping;

    public SimpleSpringInterpolator(float f) {
        this.mSpringDamping = f;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        double pow = Math.pow(2.0d, (-10.0f) * f);
        float f2 = this.mSpringDamping;
        return (float) ((Math.sin((((f - (f2 / 4.0f)) * 3.141592653589793d) * 2.0d) / f2) * pow) + 1.0d);
    }
}
