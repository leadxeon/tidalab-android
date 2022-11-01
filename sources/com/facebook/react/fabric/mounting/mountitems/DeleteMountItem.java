package com.facebook.react.fabric.mounting.mountitems;

import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class DeleteMountItem implements MountItem {
    public int mReactTag;

    public DeleteMountItem(int i) {
        this.mReactTag = i;
    }

    public String toString() {
        return GeneratedOutlineSupport.outline10(GeneratedOutlineSupport.outline13("DeleteMountItem ["), this.mReactTag, "]");
    }
}
