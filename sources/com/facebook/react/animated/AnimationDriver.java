package com.facebook.react.animated;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public abstract class AnimationDriver {
    public ValueAnimatedNode mAnimatedValue;
    public Callback mEndCallback;
    public boolean mHasFinished = false;
    public int mId;

    public void resetConfig(ReadableMap readableMap) {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Animation config for ");
        outline13.append(getClass().getSimpleName());
        outline13.append(" cannot be reset");
        throw new JSApplicationCausedNativeException(outline13.toString());
    }

    public abstract void runAnimationStep(long j);
}
