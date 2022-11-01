package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.selects.SelectClause0;
import kotlinx.coroutines.selects.SelectClause1;
/* compiled from: CompletableDeferred.kt */
/* loaded from: classes.dex */
public interface CompletableDeferred<T> extends Job {
    @Override // kotlinx.coroutines.Job
    /* synthetic */ ChildHandle attachChild(ChildJob childJob);

    /* synthetic */ Object await(Continuation<? super T> continuation);

    /* synthetic */ void cancel();

    @Override // kotlinx.coroutines.Job
    /* synthetic */ void cancel(CancellationException cancellationException);

    /* synthetic */ boolean cancel(Throwable th);

    boolean complete(T t);

    boolean completeExceptionally(Throwable th);

    @Override // kotlin.coroutines.CoroutineContext
    /* synthetic */ <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2);

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    /* synthetic */ <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key);

    @Override // kotlinx.coroutines.Job
    /* synthetic */ CancellationException getCancellationException();

    /* synthetic */ Sequence<Job> getChildren();

    /* synthetic */ T getCompleted();

    /* synthetic */ Throwable getCompletionExceptionOrNull();

    @Override // kotlin.coroutines.CoroutineContext.Element
    /* synthetic */ CoroutineContext.Key<?> getKey();

    /* synthetic */ SelectClause1<T> getOnAwait();

    /* synthetic */ SelectClause0 getOnJoin();

    /* synthetic */ DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> function1);

    @Override // kotlinx.coroutines.Job
    /* synthetic */ DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1<? super Throwable, Unit> function1);

    @Override // kotlinx.coroutines.Job
    /* synthetic */ boolean isActive();

    /* synthetic */ boolean isCancelled();

    /* synthetic */ boolean isCompleted();

    @Override // kotlinx.coroutines.Job
    /* synthetic */ Object join(Continuation<? super Unit> continuation);

    @Override // kotlin.coroutines.CoroutineContext
    /* synthetic */ CoroutineContext minusKey(CoroutineContext.Key<?> key);

    @Override // kotlin.coroutines.CoroutineContext
    /* synthetic */ CoroutineContext plus(CoroutineContext coroutineContext);

    /* synthetic */ Job plus(Job job);

    @Override // kotlinx.coroutines.Job
    /* synthetic */ boolean start();
}
