package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
/* compiled from: ContinuationImpl.kt */
/* loaded from: classes.dex */
public final class CompletedContinuation implements Continuation<Object> {
    public static final CompletedContinuation INSTANCE = new CompletedContinuation();

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete".toString());
    }

    public String toString() {
        return "This continuation is already complete";
    }
}
