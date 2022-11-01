package com.facebook.react.fabric.mounting.mountitems;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes.dex */
public class UpdatePropsMountItem implements MountItem {
    public final int mReactTag;
    public final ReadableMap mUpdatedProps;

    public UpdatePropsMountItem(int i, ReadableMap readableMap) {
        this.mReactTag = i;
        this.mUpdatedProps = readableMap;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline10(new StringBuilder("UpdatePropsMountItem ["), this.mReactTag, "]");
    }
}
