package com.facebook.react.fabric.mounting.mountitems;
/* loaded from: classes.dex */
public class RemoveDeleteMultiMountItem implements MountItem {
    public int[] mMetadata;

    public RemoveDeleteMultiMountItem(int[] iArr) {
        this.mMetadata = iArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.mMetadata.length; i += 4) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append("RemoveDeleteMultiMountItem (");
            sb.append((i / 4) + 1);
            sb.append("/");
            sb.append(this.mMetadata.length / 4);
            sb.append("): [");
            sb.append(this.mMetadata[i + 0]);
            sb.append("] parent [");
            sb.append(this.mMetadata[i + 1]);
            sb.append("] idx ");
            sb.append(this.mMetadata[i + 2]);
            sb.append(" ");
            sb.append(this.mMetadata[i + 3]);
        }
        return sb.toString();
    }
}
