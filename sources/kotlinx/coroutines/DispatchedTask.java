package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;
/* compiled from: DispatchedTask.kt */
/* loaded from: classes.dex */
public abstract class DispatchedTask<T> extends Task {
    public int resumeMode;

    public DispatchedTask(int i) {
        this.resumeMode = i;
    }

    public void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th) {
    }

    public abstract Continuation<T> getDelegate$kotlinx_coroutines_core();

    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        if (completedExceptionally == null) {
            return null;
        }
        return completedExceptionally.cause;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        return obj;
    }

    public final void handleFatalException(Throwable th, Throwable th2) {
        if (th != null || th2 != null) {
            if (!(th == null || th2 == null)) {
                InputKt.addSuppressed(th, th2);
            }
            if (th == null) {
                th = th2;
            }
            InputKt.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), new CoroutinesInternalError("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th));
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object obj;
        Job job;
        TaskContext taskContext = this.taskContext;
        try {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) getDelegate$kotlinx_coroutines_core();
            Continuation<T> continuation = dispatchedContinuation.continuation;
            Object obj2 = dispatchedContinuation.countOrElement;
            CoroutineContext context = continuation.getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj2);
            UndispatchedCoroutine<?> updateUndispatchedCompletion = updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS ? CoroutineContextKt.updateUndispatchedCompletion(continuation, context, updateThreadContext) : null;
            CoroutineContext context2 = continuation.getContext();
            Object takeState$kotlinx_coroutines_core = takeState$kotlinx_coroutines_core();
            Throwable exceptionalResult$kotlinx_coroutines_core = getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
            if (exceptionalResult$kotlinx_coroutines_core != null || !InputKt.isCancellableMode(this.resumeMode)) {
                job = null;
            } else {
                int i = Job.$r8$clinit;
                job = (Job) context2.get(Job.Key.$$INSTANCE);
            }
            if (job != null && !job.isActive()) {
                CancellationException cancellationException = job.getCancellationException();
                cancelCompletedResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core, cancellationException);
                continuation.resumeWith(new Result.Failure(cancellationException));
            } else if (exceptionalResult$kotlinx_coroutines_core != null) {
                continuation.resumeWith(new Result.Failure(exceptionalResult$kotlinx_coroutines_core));
            } else {
                continuation.resumeWith(getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core));
            }
            Object obj3 = Unit.INSTANCE;
            if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            }
            try {
                taskContext.afterTask();
            } catch (Throwable th) {
                obj3 = new Result.Failure(th);
            }
            handleFatalException(null, Result.m11exceptionOrNullimpl(obj3));
        } catch (Throwable th2) {
            try {
                taskContext.afterTask();
                obj = Unit.INSTANCE;
            } catch (Throwable th3) {
                obj = new Result.Failure(th3);
            }
            handleFatalException(th2, Result.m11exceptionOrNullimpl(obj));
        }
    }

    public abstract Object takeState$kotlinx_coroutines_core();
}
