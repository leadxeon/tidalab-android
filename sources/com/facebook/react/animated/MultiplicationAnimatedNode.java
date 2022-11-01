package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class MultiplicationAnimatedNode extends ValueAnimatedNode {
    public final int[] mInputNodes;
    public final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public MultiplicationAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray array = readableMap.getArray("input");
        this.mInputNodes = new int[array.size()];
        int i = 0;
        while (true) {
            int[] iArr = this.mInputNodes;
            if (i < iArr.length) {
                iArr[i] = array.getInt(i);
                i++;
            } else {
                return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002e, code lost:
        throw new com.facebook.react.bridge.JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.multiply node");
     */
    @Override // com.facebook.react.animated.AnimatedNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void update() {
        /*
            r6 = this;
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r6.mValue = r0
            r0 = 0
        L_0x0005:
            int[] r1 = r6.mInputNodes
            int r2 = r1.length
            if (r0 >= r2) goto L_0x002f
            com.facebook.react.animated.NativeAnimatedNodesManager r2 = r6.mNativeAnimatedNodesManager
            r1 = r1[r0]
            com.facebook.react.animated.AnimatedNode r1 = r2.getNodeById(r1)
            if (r1 == 0) goto L_0x0027
            boolean r2 = r1 instanceof com.facebook.react.animated.ValueAnimatedNode
            if (r2 == 0) goto L_0x0027
            double r2 = r6.mValue
            com.facebook.react.animated.ValueAnimatedNode r1 = (com.facebook.react.animated.ValueAnimatedNode) r1
            double r4 = r1.getValue()
            double r4 = r4 * r2
            r6.mValue = r4
            int r0 = r0 + 1
            goto L_0x0005
        L_0x0027:
            com.facebook.react.bridge.JSApplicationCausedNativeException r0 = new com.facebook.react.bridge.JSApplicationCausedNativeException
            java.lang.String r1 = "Illegal node ID set as an input for Animated.multiply node"
            r0.<init>(r1)
            throw r0
        L_0x002f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.animated.MultiplicationAnimatedNode.update():void");
    }
}
