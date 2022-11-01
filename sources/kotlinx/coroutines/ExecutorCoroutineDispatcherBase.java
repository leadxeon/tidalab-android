package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
/* compiled from: Executors.kt */
/* loaded from: classes.dex */
public abstract class ExecutorCoroutineDispatcherBase extends ExecutorCoroutineDispatcher implements Delay {
    public boolean removesFutureOnCancellation;

    public final void cancelJobOnRejection(CoroutineContext coroutineContext, RejectedExecutionException rejectedExecutionException) {
        CancellationException cancellationException = new CancellationException("The task was rejected");
        cancellationException.initCause(rejectedExecutionException);
        int i = Job.$r8$clinit;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null) {
            job.cancel(cancellationException);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Executor executor = getExecutor();
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        try {
            getExecutor().execute(runnable);
        } catch (RejectedExecutionException e) {
            cancelJobOnRejection(coroutineContext, e);
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            Dispatchers.IO.dispatch(coroutineContext, runnable);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof ExecutorCoroutineDispatcherBase) && ((ExecutorCoroutineDispatcherBase) obj).getExecutor() == getExecutor();
    }

    public int hashCode() {
        return System.identityHashCode(getExecutor());
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long j, CancellableContinuation<? super Unit> cancellableContinuation) {
        ScheduledFuture<?> scheduledFuture = null;
        if (this.removesFutureOnCancellation) {
            ResumeUndispatchedRunnable resumeUndispatchedRunnable = new ResumeUndispatchedRunnable(this, cancellableContinuation);
            CoroutineContext coroutineContext = ((CancellableContinuationImpl) cancellableContinuation).context;
            try {
                Executor executor = getExecutor();
                ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
                if (scheduledExecutorService != null) {
                    scheduledFuture = scheduledExecutorService.schedule(resumeUndispatchedRunnable, j, TimeUnit.MILLISECONDS);
                }
            } catch (RejectedExecutionException e) {
                cancelJobOnRejection(coroutineContext, e);
            }
        }
        if (scheduledFuture != null) {
            ((CancellableContinuationImpl) cancellableContinuation).invokeOnCancellation(new CancelFutureOnCancel(scheduledFuture));
        } else {
            DefaultExecutor.INSTANCE.scheduleResumeAfterDelay(j, cancellableContinuation);
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        return getExecutor().toString();
    }
}
