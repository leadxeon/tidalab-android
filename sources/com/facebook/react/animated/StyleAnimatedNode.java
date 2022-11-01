package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class StyleAnimatedNode extends AnimatedNode {
    public final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    public final Map<String, Integer> mPropMapping = new HashMap();

    public StyleAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableMap map = readableMap.getMap("style");
        ReadableMapKeySetIterator keySetIterator = map.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.mPropMapping.put(nextKey, Integer.valueOf(map.getInt(nextKey)));
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }
}
