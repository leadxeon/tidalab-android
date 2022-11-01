package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
/* compiled from: Job.kt */
/* loaded from: classes.dex */
public interface Job extends CoroutineContext.Element {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* compiled from: Job.kt */
    /* loaded from: classes.dex */
    public static final class Key implements CoroutineContext.Key<Job> {
        public static final /* synthetic */ Key $$INSTANCE = new Key();
    }

    ChildHandle attachChild(ChildJob childJob);

    void cancel(CancellationException cancellationException);

    CancellationException getCancellationException();

    DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1<? super Throwable, Unit> function1);

    boolean isActive();

    Object join(Continuation<? super Unit> continuation);

    boolean start();
}
