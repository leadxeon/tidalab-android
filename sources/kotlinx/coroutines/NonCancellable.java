package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;
/* compiled from: NonCancellable.kt */
/* loaded from: classes.dex */
public final class NonCancellable extends AbstractCoroutineContextElement implements Job {
    public static final NonCancellable INSTANCE = new NonCancellable();

    public NonCancellable() {
        super(Job.Key.$$INSTANCE);
    }

    @Override // kotlinx.coroutines.Job
    public ChildHandle attachChild(ChildJob childJob) {
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException) {
    }

    @Override // kotlinx.coroutines.Job
    public CancellationException getCancellationException() {
        throw new IllegalStateException("This job is always active");
    }

    @Override // kotlinx.coroutines.Job
    public DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1<? super Throwable, Unit> function1) {
        return NonDisposableHandle.INSTANCE;
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public Object join(Continuation<? super Unit> continuation) {
        throw new UnsupportedOperationException("This job is always active");
    }

    @Override // kotlinx.coroutines.Job
    public boolean start() {
        return false;
    }

    public String toString() {
        return "NonCancellable";
    }
}
