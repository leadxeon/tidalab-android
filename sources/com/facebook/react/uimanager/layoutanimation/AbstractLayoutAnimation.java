package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BaseInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.facebook.react.R$style;
import java.util.Map;
/* loaded from: classes.dex */
public abstract class AbstractLayoutAnimation {
    public static final Map<InterpolatorType, BaseInterpolator> INTERPOLATOR = R$style.of(InterpolatorType.LINEAR, new LinearInterpolator(), InterpolatorType.EASE_IN, new AccelerateInterpolator(), InterpolatorType.EASE_OUT, new DecelerateInterpolator(), InterpolatorType.EASE_IN_EASE_OUT, new AccelerateDecelerateInterpolator());
    public AnimatedPropertyType mAnimatedProperty;
    public int mDelayMs;
    public int mDurationMs;
    public Interpolator mInterpolator;

    public final Animation createAnimation(View view, int i, int i2, int i3, int i4) {
        if (!isValid()) {
            return null;
        }
        Animation createAnimationImpl = createAnimationImpl(view, i, i2, i3, i4);
        if (createAnimationImpl != null) {
            createAnimationImpl.setDuration(this.mDurationMs * 1);
            createAnimationImpl.setStartOffset(this.mDelayMs * 1);
            createAnimationImpl.setInterpolator(this.mInterpolator);
        }
        return createAnimationImpl;
    }

    public abstract Animation createAnimationImpl(View view, int i, int i2, int i3, int i4);

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00b1, code lost:
        if (r1.equals("spring") == false) goto L_0x00d5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void initializeFromConfig(com.facebook.react.bridge.ReadableMap r5, int r6) {
        /*
            Method dump skipped, instructions count: 416
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation.initializeFromConfig(com.facebook.react.bridge.ReadableMap, int):void");
    }

    public abstract boolean isValid();

    public void reset() {
        this.mAnimatedProperty = null;
        this.mDurationMs = 0;
        this.mDelayMs = 0;
        this.mInterpolator = null;
    }
}
