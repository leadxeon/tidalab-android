package com.tidalab.v2board.clash.design.ui;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: Insets.kt */
/* loaded from: classes.dex */
public final class Insets {
    public static final Companion Companion = new Companion(null);
    public static final Insets EMPTY = new Insets(0, 0, 0, 0);
    public final int bottom;
    public final int end;
    public final int start;
    public final int top;

    /* compiled from: Insets.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public Insets(int i, int i2, int i3, int i4) {
        this.start = i;
        this.top = i2;
        this.end = i3;
        this.bottom = i4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Insets)) {
            return false;
        }
        Insets insets = (Insets) obj;
        return this.start == insets.start && this.top == insets.top && this.end == insets.end && this.bottom == insets.bottom;
    }

    public int hashCode() {
        return (((((this.start * 31) + this.top) * 31) + this.end) * 31) + this.bottom;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Insets(start=");
        outline13.append(this.start);
        outline13.append(", top=");
        outline13.append(this.top);
        outline13.append(", end=");
        outline13.append(this.end);
        outline13.append(", bottom=");
        outline13.append(this.bottom);
        outline13.append(')');
        return outline13.toString();
    }
}
