package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CancellableContinuationImpl.kt */
/* loaded from: classes.dex */
public final class CompletedContinuation {
    public final Throwable cancelCause;
    public final CancelHandler cancelHandler;
    public final Object idempotentResume;
    public final Function1<Throwable, Unit> onCancellation;
    public final Object result;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1<? super Throwable, Unit> function1, Object obj2, Throwable th) {
        this.result = obj;
        this.cancelHandler = cancelHandler;
        this.onCancellation = function1;
        this.idempotentResume = obj2;
        this.cancelCause = th;
    }

    public static CompletedContinuation copy$default(CompletedContinuation completedContinuation, Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i) {
        Object obj3 = null;
        Object obj4 = (i & 1) != 0 ? completedContinuation.result : null;
        if ((i & 2) != 0) {
            cancelHandler = completedContinuation.cancelHandler;
        }
        Function1<Throwable, Unit> function12 = (i & 4) != 0 ? completedContinuation.onCancellation : null;
        if ((i & 8) != 0) {
            obj3 = completedContinuation.idempotentResume;
        }
        if ((i & 16) != 0) {
            th = completedContinuation.cancelCause;
        }
        Objects.requireNonNull(completedContinuation);
        return new CompletedContinuation(obj4, cancelHandler, function12, obj3, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedContinuation)) {
            return false;
        }
        CompletedContinuation completedContinuation = (CompletedContinuation) obj;
        return Intrinsics.areEqual(this.result, completedContinuation.result) && Intrinsics.areEqual(this.cancelHandler, completedContinuation.cancelHandler) && Intrinsics.areEqual(this.onCancellation, completedContinuation.onCancellation) && Intrinsics.areEqual(this.idempotentResume, completedContinuation.idempotentResume) && Intrinsics.areEqual(this.cancelCause, completedContinuation.cancelCause);
    }

    public int hashCode() {
        Object obj = this.result;
        int i = 0;
        int hashCode = (obj == null ? 0 : obj.hashCode()) * 31;
        CancelHandler cancelHandler = this.cancelHandler;
        int hashCode2 = (hashCode + (cancelHandler == null ? 0 : cancelHandler.hashCode())) * 31;
        Function1<Throwable, Unit> function1 = this.onCancellation;
        int hashCode3 = (hashCode2 + (function1 == null ? 0 : function1.hashCode())) * 31;
        Object obj2 = this.idempotentResume;
        int hashCode4 = (hashCode3 + (obj2 == null ? 0 : obj2.hashCode())) * 31;
        Throwable th = this.cancelCause;
        if (th != null) {
            i = th.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CompletedContinuation(result=");
        outline13.append(this.result);
        outline13.append(", cancelHandler=");
        outline13.append(this.cancelHandler);
        outline13.append(", onCancellation=");
        outline13.append(this.onCancellation);
        outline13.append(", idempotentResume=");
        outline13.append(this.idempotentResume);
        outline13.append(", cancelCause=");
        outline13.append(this.cancelCause);
        outline13.append(')');
        return outline13.toString();
    }

    public CompletedContinuation(Object obj, CancelHandler cancelHandler, Function1 function1, Object obj2, Throwable th, int i) {
        cancelHandler = (i & 2) != 0 ? null : cancelHandler;
        function1 = (i & 4) != 0 ? null : function1;
        obj2 = (i & 8) != 0 ? null : obj2;
        th = (i & 16) != 0 ? null : th;
        this.result = obj;
        this.cancelHandler = cancelHandler;
        this.onCancellation = function1;
        this.idempotentResume = obj2;
        this.cancelCause = th;
    }
}
