package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: UByte.kt */
/* loaded from: classes.dex */
public final class UByte implements Comparable<UByte> {
    public static final Companion Companion = new Companion(null);
    public final byte data;

    /* compiled from: UByte.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ UByte(byte b) {
        this.data = b;
    }

    @Override // java.lang.Comparable
    public int compareTo(UByte uByte) {
        return Intrinsics.compare(this.data & 255, uByte.data & 255);
    }

    public boolean equals(Object obj) {
        return (obj instanceof UByte) && this.data == ((UByte) obj).data;
    }

    public int hashCode() {
        return this.data;
    }

    public String toString() {
        return String.valueOf(this.data & 255);
    }
}
