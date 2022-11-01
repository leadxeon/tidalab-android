package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.uimanager.IllegalViewOperationException;
/* loaded from: classes.dex */
public abstract class BaseLayoutAnimation extends AbstractLayoutAnimation {
    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    public Animation createAnimationImpl(View view, int i, int i2, int i3, int i4) {
        AnimatedPropertyType animatedPropertyType = this.mAnimatedProperty;
        if (animatedPropertyType != null) {
            int ordinal = animatedPropertyType.ordinal();
            float f = 0.0f;
            if (ordinal == 0) {
                float alpha = isReverse() ? view.getAlpha() : 0.0f;
                if (!isReverse()) {
                    f = view.getAlpha();
                }
                return new OpacityAnimation(view, alpha, f);
            } else if (ordinal == 1) {
                return new ScaleAnimation(isReverse() ? 1.0f : 0.0f, isReverse() ? 0.0f : 1.0f, 1.0f, 1.0f, 1, 0.5f, 1, 0.0f);
            } else if (ordinal == 2) {
                return new ScaleAnimation(1.0f, 1.0f, isReverse() ? 1.0f : 0.0f, isReverse() ? 0.0f : 1.0f, 1, 0.0f, 1, 0.5f);
            } else if (ordinal == 3) {
                float f2 = isReverse() ? 1.0f : 0.0f;
                float f3 = isReverse() ? 0.0f : 1.0f;
                return new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
            } else {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Missing animation for property : ");
                outline13.append(this.mAnimatedProperty);
                throw new IllegalViewOperationException(outline13.toString());
            }
        } else {
            throw new IllegalViewOperationException("Missing animated property from animation config");
        }
    }

    public abstract boolean isReverse();

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    public boolean isValid() {
        return this.mDurationMs > 0 && this.mAnimatedProperty != null;
    }
}
