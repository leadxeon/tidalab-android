package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class DiffClampAnimatedNode extends ValueAnimatedNode {
    public final int mInputNodeTag;
    public double mLastValue = 0.0d;
    public final double mMax;
    public final double mMin;
    public final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public DiffClampAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.mInputNodeTag = readableMap.getInt("input");
        this.mMin = readableMap.getDouble("min");
        this.mMax = readableMap.getDouble("max");
        this.mValue = 0.0d;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNodeTag);
        if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
            throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.DiffClamp node");
        }
        double value = ((ValueAnimatedNode) nodeById).getValue();
        double d = value - this.mLastValue;
        this.mLastValue = value;
        this.mValue = Math.min(Math.max(this.mValue + d, this.mMin), this.mMax);
    }
}
