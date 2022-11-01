package androidx.dynamicanimation.animation;
/* loaded from: classes.dex */
public final class SpringAnimation extends DynamicAnimation<SpringAnimation> {
    public SpringForce mSpring = null;
    public float mPendingPosition = Float.MAX_VALUE;

    public <K> SpringAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        super(k, floatPropertyCompat);
    }
}
