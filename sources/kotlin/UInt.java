package kotlin;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: UInt.kt */
/* loaded from: classes.dex */
public final class UInt implements Comparable<UInt> {
    public static final Companion Companion = new Companion(null);
    public final int data;

    /* compiled from: UInt.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ UInt(int i) {
        this.data = i;
    }

    @Override // java.lang.Comparable
    public int compareTo(UInt uInt) {
        return InputKt.uintCompare(this.data, uInt.data);
    }

    public boolean equals(Object obj) {
        return (obj instanceof UInt) && this.data == ((UInt) obj).data;
    }

    public int hashCode() {
        return this.data;
    }

    public String toString() {
        return String.valueOf(this.data & 4294967295L);
    }
}
