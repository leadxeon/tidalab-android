package kotlin;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: ULong.kt */
/* loaded from: classes.dex */
public final class ULong implements Comparable<ULong> {
    public static final Companion Companion = new Companion(null);
    public final long data;

    /* compiled from: ULong.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public /* synthetic */ ULong(long j) {
        this.data = j;
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m12toStringimpl(long j) {
        if (j >= 0) {
            InputKt.checkRadix(10);
            return Long.toString(j, 10);
        }
        long j2 = 10;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        StringBuilder sb = new StringBuilder();
        InputKt.checkRadix(10);
        sb.append(Long.toString(j3, 10));
        InputKt.checkRadix(10);
        sb.append(Long.toString(j4, 10));
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(ULong uLong) {
        return InputKt.ulongCompare(this.data, uLong.data);
    }

    public boolean equals(Object obj) {
        return (obj instanceof ULong) && this.data == ((ULong) obj).data;
    }

    public int hashCode() {
        long j = this.data;
        return (int) (j ^ (j >>> 32));
    }

    public String toString() {
        return m12toStringimpl(this.data);
    }
}
