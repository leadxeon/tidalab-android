package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.view.View;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.dynamicanimation.animation.DynamicAnimation;
import java.util.ArrayList;
import java.util.Objects;
/* loaded from: classes.dex */
public abstract class DynamicAnimation<T extends DynamicAnimation<T>> implements AnimationHandler.AnimationFrameCallback {
    public float mMinVisibleChange;
    public final FloatPropertyCompat mProperty;
    public final Object mTarget;
    public static final ViewProperty SCALE_X = new ViewProperty("scaleX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.4
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getScaleX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f) {
            view.setScaleX(f);
        }
    };
    public static final ViewProperty SCALE_Y = new ViewProperty("scaleY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.5
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getScaleY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f) {
            view.setScaleY(f);
        }
    };
    public static final ViewProperty ROTATION = new ViewProperty("rotation") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.6
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getRotation();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f) {
            view.setRotation(f);
        }
    };
    public static final ViewProperty ROTATION_X = new ViewProperty("rotationX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.7
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getRotationX();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f) {
            view.setRotationX(f);
        }
    };
    public static final ViewProperty ROTATION_Y = new ViewProperty("rotationY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.8
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getRotationY();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f) {
            view.setRotationY(f);
        }
    };
    public static final ViewProperty ALPHA = new ViewProperty("alpha") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.12
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getAlpha();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f) {
            view.setAlpha(f);
        }
    };
    public float mVelocity = 0.0f;
    public float mValue = Float.MAX_VALUE;
    public boolean mStartValueIsSet = false;
    public boolean mRunning = false;
    public float mMinValue = -3.4028235E38f;
    public long mLastFrameTime = 0;
    public final ArrayList<OnAnimationEndListener> mEndListeners = new ArrayList<>();
    public final ArrayList<OnAnimationUpdateListener> mUpdateListeners = new ArrayList<>();

    /* renamed from: androidx.dynamicanimation.animation.DynamicAnimation$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static class AnonymousClass1 extends ViewProperty {
    }

    /* loaded from: classes.dex */
    public static class MassState {
        public float mValue;
        public float mVelocity;
    }

    /* loaded from: classes.dex */
    public interface OnAnimationEndListener {
        void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2);
    }

    /* loaded from: classes.dex */
    public interface OnAnimationUpdateListener {
        void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f, float f2);
    }

    /* loaded from: classes.dex */
    public static abstract class ViewProperty extends FloatPropertyCompat<View> {
        public ViewProperty(String str, AnonymousClass1 r2) {
            super(str);
        }
    }

    public <K> DynamicAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        this.mTarget = k;
        this.mProperty = floatPropertyCompat;
        if (floatPropertyCompat == ROTATION || floatPropertyCompat == ROTATION_X || floatPropertyCompat == ROTATION_Y) {
            this.mMinVisibleChange = 0.1f;
        } else if (floatPropertyCompat == ALPHA) {
            this.mMinVisibleChange = 0.00390625f;
        } else if (floatPropertyCompat == SCALE_X || floatPropertyCompat == SCALE_Y) {
            this.mMinVisibleChange = 0.00390625f;
        } else {
            this.mMinVisibleChange = 1.0f;
        }
    }

    public static <T> void removeNullEntries(ArrayList<T> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    public void cancel() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be canceled on the main thread");
        } else if (this.mRunning) {
            endAnimationInternal(true);
        }
    }

    @Override // androidx.dynamicanimation.animation.AnimationHandler.AnimationFrameCallback
    public boolean doAnimationFrame(long j) {
        long j2 = this.mLastFrameTime;
        if (j2 == 0) {
            this.mLastFrameTime = j;
            setPropertyValue(this.mValue);
            return false;
        }
        long j3 = j - j2;
        this.mLastFrameTime = j;
        SpringAnimation springAnimation = (SpringAnimation) this;
        if (springAnimation.mPendingPosition != Float.MAX_VALUE) {
            SpringForce springForce = springAnimation.mSpring;
            double d = springForce.mFinalPosition;
            long j4 = j3 / 2;
            MassState updateValues = springForce.updateValues(springAnimation.mValue, springAnimation.mVelocity, j4);
            SpringForce springForce2 = springAnimation.mSpring;
            springForce2.mFinalPosition = springAnimation.mPendingPosition;
            springAnimation.mPendingPosition = Float.MAX_VALUE;
            MassState updateValues2 = springForce2.updateValues(updateValues.mValue, updateValues.mVelocity, j4);
            springAnimation.mValue = updateValues2.mValue;
            springAnimation.mVelocity = updateValues2.mVelocity;
        } else {
            MassState updateValues3 = springAnimation.mSpring.updateValues(springAnimation.mValue, springAnimation.mVelocity, j3);
            springAnimation.mValue = updateValues3.mValue;
            springAnimation.mVelocity = updateValues3.mVelocity;
        }
        float max = Math.max(springAnimation.mValue, springAnimation.mMinValue);
        springAnimation.mValue = max;
        float min = Math.min(max, Float.MAX_VALUE);
        springAnimation.mValue = min;
        float f = springAnimation.mVelocity;
        SpringForce springForce3 = springAnimation.mSpring;
        Objects.requireNonNull(springForce3);
        boolean z = true;
        if (((double) Math.abs(f)) < springForce3.mVelocityThreshold && ((double) Math.abs(min - ((float) springForce3.mFinalPosition))) < springForce3.mValueThreshold) {
            springAnimation.mValue = (float) springAnimation.mSpring.mFinalPosition;
            springAnimation.mVelocity = 0.0f;
        } else {
            z = false;
        }
        float min2 = Math.min(this.mValue, Float.MAX_VALUE);
        this.mValue = min2;
        float max2 = Math.max(min2, this.mMinValue);
        this.mValue = max2;
        setPropertyValue(max2);
        if (z) {
            endAnimationInternal(false);
        }
        return z;
    }

    public final void endAnimationInternal(boolean z) {
        this.mRunning = false;
        AnimationHandler instance = AnimationHandler.getInstance();
        instance.mDelayedCallbackStartTime.remove(this);
        int indexOf = instance.mAnimationCallbacks.indexOf(this);
        if (indexOf >= 0) {
            instance.mAnimationCallbacks.set(indexOf, null);
            instance.mListDirty = true;
        }
        this.mLastFrameTime = 0L;
        this.mStartValueIsSet = false;
        for (int i = 0; i < this.mEndListeners.size(); i++) {
            if (this.mEndListeners.get(i) != null) {
                this.mEndListeners.get(i).onAnimationEnd(this, z, this.mValue, this.mVelocity);
            }
        }
        removeNullEntries(this.mEndListeners);
    }

    public void setPropertyValue(float f) {
        this.mProperty.setValue(this.mTarget, f);
        for (int i = 0; i < this.mUpdateListeners.size(); i++) {
            if (this.mUpdateListeners.get(i) != null) {
                this.mUpdateListeners.get(i).onAnimationUpdate(this, this.mValue, this.mVelocity);
            }
        }
        removeNullEntries(this.mUpdateListeners);
    }
}
