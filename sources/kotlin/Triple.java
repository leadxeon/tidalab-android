package kotlin;

import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Tuples.kt */
/* loaded from: classes.dex */
public final class Triple<A, B, C> implements Serializable {
    public final A first;
    public final B second;
    public final C third;

    public Triple(A a, B b, C c) {
        this.first = a;
        this.second = b;
        this.third = c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Triple)) {
            return false;
        }
        Triple triple = (Triple) obj;
        return Intrinsics.areEqual(this.first, triple.first) && Intrinsics.areEqual(this.second, triple.second) && Intrinsics.areEqual(this.third, triple.third);
    }

    public int hashCode() {
        A a = this.first;
        int i = 0;
        int hashCode = (a != null ? a.hashCode() : 0) * 31;
        B b = this.second;
        int hashCode2 = (hashCode + (b != null ? b.hashCode() : 0)) * 31;
        C c = this.third;
        if (c != null) {
            i = c.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return '(' + this.first + ", " + this.second + ", " + this.third + ')';
    }
}
