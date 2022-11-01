package androidx.core.graphics;

import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes.dex */
public final class Insets {
    public static final Insets NONE = new Insets(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

    public Insets(int i, int i2, int i3, int i4) {
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    public static Insets max(Insets insets, Insets insets2) {
        return of(Math.max(insets.left, insets2.left), Math.max(insets.top, insets2.top), Math.max(insets.right, insets2.right), Math.max(insets.bottom, insets2.bottom));
    }

    public static Insets of(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return NONE;
        }
        return new Insets(i, i2, i3, i4);
    }

    public static Insets toCompatInsets(android.graphics.Insets insets) {
        return of(insets.left, insets.top, insets.right, insets.bottom);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Insets.class != obj.getClass()) {
            return false;
        }
        Insets insets = (Insets) obj;
        return this.bottom == insets.bottom && this.left == insets.left && this.right == insets.right && this.top == insets.top;
    }

    public int hashCode() {
        return (((((this.left * 31) + this.top) * 31) + this.right) * 31) + this.bottom;
    }

    public android.graphics.Insets toPlatformInsets() {
        return android.graphics.Insets.of(this.left, this.top, this.right, this.bottom);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Insets{left=");
        outline13.append(this.left);
        outline13.append(", top=");
        outline13.append(this.top);
        outline13.append(", right=");
        outline13.append(this.right);
        outline13.append(", bottom=");
        outline13.append(this.bottom);
        outline13.append('}');
        return outline13.toString();
    }
}
