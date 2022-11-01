package kotlinx.coroutines;

import kotlin.Unit;
/* compiled from: Executors.kt */
/* loaded from: classes.dex */
public final class ResumeUndispatchedRunnable implements Runnable {
    public final CancellableContinuation<Unit> continuation;
    public final CoroutineDispatcher dispatcher;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeUndispatchedRunnable(CoroutineDispatcher coroutineDispatcher, CancellableContinuation<? super Unit> cancellableContinuation) {
        this.dispatcher = coroutineDispatcher;
        this.continuation = cancellableContinuation;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.continuation.resumeUndispatched(this.dispatcher, Unit.INSTANCE);
    }
}
