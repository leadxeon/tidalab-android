package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.collection.SimpleArrayMap;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class MotionSpec {
    public final SimpleArrayMap<String, MotionTiming> timings = new SimpleArrayMap<>();
    public final SimpleArrayMap<String, PropertyValuesHolder[]> propertyValues = new SimpleArrayMap<>();

    public static MotionSpec createFromAttribute(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0) {
            return null;
        }
        return createFromResource(context, resourceId);
    }

    public static MotionSpec createFromResource(Context context, int i) {
        try {
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, i);
            if (loadAnimator instanceof AnimatorSet) {
                return createSpecFromAnimators(((AnimatorSet) loadAnimator).getChildAnimations());
            }
            if (loadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(loadAnimator);
            return createSpecFromAnimators(arrayList);
        } catch (Exception e) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Can't load animation resource ID #0x");
            outline13.append(Integer.toHexString(i));
            Log.w("MotionSpec", outline13.toString(), e);
            return null;
        }
    }

    public static MotionSpec createSpecFromAnimators(List<Animator> list) {
        MotionSpec motionSpec = new MotionSpec();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Animator animator = list.get(i);
            if (animator instanceof ObjectAnimator) {
                ObjectAnimator objectAnimator = (ObjectAnimator) animator;
                motionSpec.propertyValues.put(objectAnimator.getPropertyName(), objectAnimator.getValues());
                String propertyName = objectAnimator.getPropertyName();
                long startDelay = objectAnimator.getStartDelay();
                long duration = objectAnimator.getDuration();
                TimeInterpolator interpolator = objectAnimator.getInterpolator();
                if ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) {
                    interpolator = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
                } else if (interpolator instanceof AccelerateInterpolator) {
                    interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
                } else if (interpolator instanceof DecelerateInterpolator) {
                    interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
                }
                MotionTiming motionTiming = new MotionTiming(startDelay, duration, interpolator);
                motionTiming.repeatCount = objectAnimator.getRepeatCount();
                motionTiming.repeatMode = objectAnimator.getRepeatMode();
                motionSpec.timings.put(propertyName, motionTiming);
            } else {
                throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
            }
        }
        return motionSpec;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MotionSpec)) {
            return false;
        }
        return this.timings.equals(((MotionSpec) obj).timings);
    }

    public MotionTiming getTiming(String str) {
        if (this.timings.getOrDefault(str, null) != null) {
            return this.timings.getOrDefault(str, null);
        }
        throw new IllegalArgumentException();
    }

    public int hashCode() {
        return this.timings.hashCode();
    }

    public String toString() {
        return '\n' + MotionSpec.class.getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.timings + "}\n";
    }
}
