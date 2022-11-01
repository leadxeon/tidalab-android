package com.facebook.react.uimanager;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class ReactStylesDiffMap {
    public final ReadableMap mBackingMap;

    public ReactStylesDiffMap(ReadableMap readableMap) {
        this.mBackingMap = readableMap;
    }

    public int getInt(String str, int i) {
        return this.mBackingMap.isNull(str) ? i : this.mBackingMap.getInt(str);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("{ ");
        outline13.append(ReactStylesDiffMap.class.getSimpleName());
        outline13.append(": ");
        outline13.append(this.mBackingMap.toString());
        outline13.append(" }");
        return outline13.toString();
    }
}
