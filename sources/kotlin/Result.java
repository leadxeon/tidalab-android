package kotlin;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.Serializable;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Result.kt */
/* loaded from: classes.dex */
public final class Result<T> implements Serializable {

    /* compiled from: Result.kt */
    /* loaded from: classes.dex */
    public static final class Failure implements Serializable {
        public final Throwable exception;

        public Failure(Throwable th) {
            this.exception = th;
        }

        public boolean equals(Object obj) {
            return (obj instanceof Failure) && Intrinsics.areEqual(this.exception, ((Failure) obj).exception);
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Failure(");
            outline13.append(this.exception);
            outline13.append(')');
            return outline13.toString();
        }
    }

    /* renamed from: exceptionOrNull-impl  reason: not valid java name */
    public static final Throwable m11exceptionOrNullimpl(Object obj) {
        if (obj instanceof Failure) {
            return ((Failure) obj).exception;
        }
        return null;
    }
}
