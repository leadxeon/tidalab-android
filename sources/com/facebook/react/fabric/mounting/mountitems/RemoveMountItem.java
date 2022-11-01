package com.facebook.react.fabric.mounting.mountitems;

import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class RemoveMountItem implements MountItem {
    public int mIndex;
    public int mParentReactTag;
    public int mReactTag;

    public RemoveMountItem(int i, int i2, int i3) {
        this.mReactTag = i;
        this.mParentReactTag = i2;
        this.mIndex = i3;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("RemoveMountItem [");
        outline13.append(this.mReactTag);
        outline13.append("] - parentTag: ");
        outline13.append(this.mParentReactTag);
        outline13.append(" - index: ");
        outline13.append(this.mIndex);
        return outline13.toString();
    }
}
