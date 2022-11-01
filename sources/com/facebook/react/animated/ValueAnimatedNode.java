package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class ValueAnimatedNode extends AnimatedNode {
    public Object mAnimatedObject;
    public double mOffset;
    public double mValue;
    public AnimatedNodeValueListener mValueListener;

    public ValueAnimatedNode() {
        this.mAnimatedObject = null;
        this.mValue = Double.NaN;
        this.mOffset = 0.0d;
    }

    public double getValue() {
        if (Double.isNaN(this.mOffset + this.mValue)) {
            update();
        }
        return this.mOffset + this.mValue;
    }

    public ValueAnimatedNode(ReadableMap readableMap) {
        this.mAnimatedObject = null;
        this.mValue = Double.NaN;
        this.mOffset = 0.0d;
        this.mValue = readableMap.getDouble("value");
        this.mOffset = readableMap.getDouble("offset");
    }
}
