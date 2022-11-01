package com.facebook.react.fabric.mounting.mountitems;

import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public class UpdateLayoutMountItem implements MountItem {
    public final int mHeight;
    public final int mLayoutDirection;
    public final int mReactTag;
    public final int mWidth;
    public final int mX;
    public final int mY;

    public UpdateLayoutMountItem(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mReactTag = i;
        this.mX = i2;
        this.mY = i3;
        this.mWidth = i4;
        this.mHeight = i5;
        int i7 = 2;
        if (i6 != 0) {
            if (i6 == 1) {
                i7 = 0;
            } else if (i6 == 2) {
                i7 = 1;
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("Unsupported layout direction: ", i6));
            }
        }
        this.mLayoutDirection = i7;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("UpdateLayoutMountItem [");
        outline13.append(this.mReactTag);
        outline13.append("] - x: ");
        outline13.append(this.mX);
        outline13.append(" - y: ");
        outline13.append(this.mY);
        outline13.append(" - height: ");
        outline13.append(this.mHeight);
        outline13.append(" - width: ");
        outline13.append(this.mWidth);
        outline13.append(" - layoutDirection: ");
        outline13.append(this.mLayoutDirection);
        return outline13.toString();
    }
}
