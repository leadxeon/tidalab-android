package kotlin.reflect;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: KTypeProjection.kt */
/* loaded from: classes.dex */
public final class KTypeProjection {
    public static final Companion Companion = new Companion(null);
    public final KType type;
    public final KVariance variance;

    /* compiled from: KTypeProjection.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        public final KTypeProjection invariant(KType kType) {
            return new KTypeProjection(KVariance.INVARIANT, kType);
        }
    }

    static {
        if (1 == 0) {
            throw new IllegalArgumentException("Star projection must have no type specified.".toString());
        }
    }

    public KTypeProjection(KVariance kVariance, KType kType) {
        this.variance = kVariance;
        this.type = kType;
        if (!(!false)) {
            throw new IllegalArgumentException(("The projection variance " + kVariance + " requires type to be specified.").toString());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KTypeProjection)) {
            return false;
        }
        KTypeProjection kTypeProjection = (KTypeProjection) obj;
        return Intrinsics.areEqual(this.variance, kTypeProjection.variance) && Intrinsics.areEqual(this.type, kTypeProjection.type);
    }

    public int hashCode() {
        KVariance kVariance = this.variance;
        int i = 0;
        int hashCode = (kVariance != null ? kVariance.hashCode() : 0) * 31;
        KType kType = this.type;
        if (kType != null) {
            i = kType.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        KVariance kVariance = this.variance;
        if (kVariance == null) {
            return "*";
        }
        int ordinal = kVariance.ordinal();
        if (ordinal == 0) {
            return String.valueOf(this.type);
        }
        if (ordinal == 1) {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("in ");
            outline13.append(this.type);
            return outline13.toString();
        } else if (ordinal == 2) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("out ");
            outline132.append(this.type);
            return outline132.toString();
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
