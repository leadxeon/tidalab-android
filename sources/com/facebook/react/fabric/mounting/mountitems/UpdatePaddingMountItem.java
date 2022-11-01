package com.facebook.react.fabric.mounting.mountitems;

import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class UpdatePaddingMountItem implements MountItem {
    public final int mBottom;
    public final int mLeft;
    public final int mReactTag;
    public final int mRight;
    public final int mTop;

    public UpdatePaddingMountItem(int i, int i2, int i3, int i4, int i5) {
        this.mReactTag = i;
        this.mLeft = i2;
        this.mTop = i3;
        this.mRight = i4;
        this.mBottom = i5;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("UpdatePaddingMountItem [");
        outline13.append(this.mReactTag);
        outline13.append("] - left: ");
        outline13.append(this.mLeft);
        outline13.append(" - top: ");
        outline13.append(this.mTop);
        outline13.append(" - right: ");
        outline13.append(this.mRight);
        outline13.append(" - bottom: ");
        outline13.append(this.mBottom);
        return outline13.toString();
    }
}
