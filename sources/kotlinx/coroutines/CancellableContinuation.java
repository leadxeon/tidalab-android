package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
/* compiled from: CancellableContinuation.kt */
/* loaded from: classes.dex */
public interface CancellableContinuation<T> extends Continuation<T> {
    boolean cancel(Throwable th);

    void completeResume(Object obj);

    void invokeOnCancellation(Function1<? super Throwable, Unit> function1);

    boolean isCompleted();

    void resumeUndispatched(CoroutineDispatcher coroutineDispatcher, T t);

    Object tryResume(T t, Object obj);

    Object tryResume(T t, Object obj, Function1<? super Throwable, Unit> function1);
}
