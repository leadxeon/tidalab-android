package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UShort.kt */
/* loaded from: classes.dex */
public final class UShort implements Comparable<UShort> {
    public static final Companion Companion = new Companion(null);
    public final short data;

    /* compiled from: UShort.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ UShort(short s) {
        this.data = s;
    }

    @Override // java.lang.Comparable
    public int compareTo(UShort uShort) {
        return Intrinsics.compare(this.data & 65535, uShort.data & 65535);
    }

    public boolean equals(Object obj) {
        return (obj instanceof UShort) && this.data == ((UShort) obj).data;
    }

    public int hashCode() {
        return this.data;
    }

    public String toString() {
        return String.valueOf(this.data & 65535);
    }
}
