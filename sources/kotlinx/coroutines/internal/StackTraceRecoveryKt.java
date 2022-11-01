package kotlinx.coroutines.internal;

import kotlin.Result;
/* compiled from: StackTraceRecovery.kt */
/* loaded from: classes.dex */
public final class StackTraceRecoveryKt {
    public static final String baseContinuationImplClassName;
    public static final String stackTraceRecoveryClassName;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.Result$Failure] */
    /* JADX WARN: Type inference failed for: r3v1, types: [kotlin.Result$Failure] */
    static {
        String str;
        String str2;
        String str3 = "kotlinx.coroutines.internal.StackTraceRecoveryKt";
        String str4 = "kotlin.coroutines.jvm.internal.BaseContinuationImpl";
        try {
            str = Class.forName(str4).getCanonicalName();
        } catch (Throwable th) {
            str = new Result.Failure(th);
        }
        if (Result.m11exceptionOrNullimpl(str) == null) {
            str4 = str;
        }
        baseContinuationImplClassName = str4;
        try {
            str2 = Class.forName(str3).getCanonicalName();
        } catch (Throwable th2) {
            str2 = new Result.Failure(th2);
        }
        if (Result.m11exceptionOrNullimpl(str2) == null) {
            str3 = str2;
        }
        stackTraceRecoveryClassName = str3;
    }
}
