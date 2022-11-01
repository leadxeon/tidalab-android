package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.selects.SelectBuilderImpl;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
/* compiled from: CompletableDeferred.kt */
/* loaded from: classes.dex */
public final class CompletableDeferredImpl<T> extends JobSupport implements CompletableDeferred<T>, SelectClause1<T> {
    public CompletableDeferredImpl(Job job) {
        super(true);
        initParentJob(job);
    }

    @Override // kotlinx.coroutines.CompletableDeferred
    public Object await(Continuation<? super T> continuation) {
        Object state$kotlinx_coroutines_core;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
                    return JobSupportKt.unboxState(state$kotlinx_coroutines_core);
                }
                throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            }
        } while (startInternal(state$kotlinx_coroutines_core) < 0);
        JobSupport.AwaitContinuation awaitContinuation = new JobSupport.AwaitContinuation(InputKt.intercepted(continuation), this);
        awaitContinuation.initCancellability();
        awaitContinuation.invokeOnCancellation(new DisposeOnCancel(invokeOnCompletion(false, true, new ResumeAwaitOnCompletion(awaitContinuation))));
        return awaitContinuation.getResult();
    }

    @Override // kotlinx.coroutines.CompletableDeferred
    public boolean complete(T t) {
        return makeCompleting$kotlinx_coroutines_core(t);
    }

    @Override // kotlinx.coroutines.CompletableDeferred
    public boolean completeExceptionally(Throwable th) {
        return makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(th, false, 2));
    }

    @Override // kotlinx.coroutines.CompletableDeferred
    public T getCompleted() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(!(state$kotlinx_coroutines_core instanceof Incomplete))) {
            throw new IllegalStateException("This job has not completed yet".toString());
        } else if (!(state$kotlinx_coroutines_core instanceof CompletedExceptionally)) {
            return (T) JobSupportKt.unboxState(state$kotlinx_coroutines_core);
        } else {
            throw ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        }
    }

    @Override // kotlinx.coroutines.CompletableDeferred
    public SelectClause1<T> getOnAwait() {
        return this;
    }

    @Override // kotlinx.coroutines.selects.SelectClause1
    public <R> void registerSelectClause1(SelectInstance<? super R> selectInstance, Function2<? super T, ? super Continuation<? super R>, ? extends Object> function2) {
        Object state$kotlinx_coroutines_core;
        SelectBuilderImpl selectBuilderImpl;
        do {
            state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            selectBuilderImpl = (SelectBuilderImpl) selectInstance;
            if (!selectBuilderImpl.isSelected()) {
                if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
                    if (!selectBuilderImpl.trySelect()) {
                        return;
                    }
                    if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                        selectBuilderImpl.resumeSelectWithException(((CompletedExceptionally) state$kotlinx_coroutines_core).cause);
                        return;
                    } else {
                        InputKt.startCoroutineUnintercepted(function2, JobSupportKt.unboxState(state$kotlinx_coroutines_core), selectBuilderImpl);
                        return;
                    }
                }
            } else {
                return;
            }
        } while (startInternal(state$kotlinx_coroutines_core) != 0);
        selectBuilderImpl.disposeOnSelect(invokeOnCompletion(false, true, new SelectAwaitOnCompletion(selectInstance, function2)));
    }
}
