package com.facebook.react.uimanager;

import com.facebook.react.common.ClearableSynchronizedPool;
import com.facebook.yoga.YogaNode;
/* loaded from: classes.dex */
public class YogaNodePool {
    public static final Object sInitLock = new Object();
    public static ClearableSynchronizedPool<YogaNode> sPool;

    public static ClearableSynchronizedPool<YogaNode> get() {
        ClearableSynchronizedPool<YogaNode> clearableSynchronizedPool;
        ClearableSynchronizedPool<YogaNode> clearableSynchronizedPool2 = sPool;
        if (clearableSynchronizedPool2 != null) {
            return clearableSynchronizedPool2;
        }
        synchronized (sInitLock) {
            if (sPool == null) {
                sPool = new ClearableSynchronizedPool<>(1024);
            }
            clearableSynchronizedPool = sPool;
        }
        return clearableSynchronizedPool;
    }
}
