package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CompletionState.kt */
/* loaded from: classes.dex */
public final class CompletedWithCancellation {
    public final Function1<Throwable, Unit> onCancellation;
    public final Object result;

    /* JADX WARN: Multi-variable type inference failed */
    public CompletedWithCancellation(Object obj, Function1<? super Throwable, Unit> function1) {
        this.result = obj;
        this.onCancellation = function1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CompletedWithCancellation)) {
            return false;
        }
        CompletedWithCancellation completedWithCancellation = (CompletedWithCancellation) obj;
        return Intrinsics.areEqual(this.result, completedWithCancellation.result) && Intrinsics.areEqual(this.onCancellation, completedWithCancellation.onCancellation);
    }

    public int hashCode() {
        Object obj = this.result;
        return this.onCancellation.hashCode() + ((obj == null ? 0 : obj.hashCode()) * 31);
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CompletedWithCancellation(result=");
        outline13.append(this.result);
        outline13.append(", onCancellation=");
        outline13.append(this.onCancellation);
        outline13.append(')');
        return outline13.toString();
    }
}
