package com.facebook.react.animated;

import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class ModulusAnimatedNode extends ValueAnimatedNode {
    public final int mInputNode;
    public final double mModulus;
    public final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public ModulusAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.mInputNode = readableMap.getInt("input");
        this.mModulus = readableMap.getDouble("modulus");
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNode);
        if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
            throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.modulus node");
        }
        double value = ((ValueAnimatedNode) nodeById).getValue();
        double d = this.mModulus;
        this.mValue = ((value % d) + d) % d;
    }
}
