package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.ThreadContextKt;
/* compiled from: AbstractCoroutine.kt */
/* loaded from: classes.dex */
public abstract class AbstractCoroutine<T> extends JobSupport implements Job, Continuation<T>, CoroutineScope {
    public final CoroutineContext context;

    public AbstractCoroutine(CoroutineContext coroutineContext, boolean z, boolean z2) {
        super(z2);
        if (z) {
            initParentJob((Job) coroutineContext.get(Job.Key.$$INSTANCE));
        }
        this.context = coroutineContext.plus(this);
    }

    public void afterResume(Object obj) {
        afterCompletion(obj);
    }

    @Override // kotlinx.coroutines.JobSupport
    public String cancellationExceptionMessage() {
        return Intrinsics.stringPlus(getClass().getSimpleName(), " was cancelled");
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$kotlinx_coroutines_core(Throwable th) {
        InputKt.handleCoroutineException(this.context, th);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.JobSupport
    public String nameString$kotlinx_coroutines_core() {
        boolean z = CoroutineContextKt.useCoroutinesScheduler;
        return super.nameString$kotlinx_coroutines_core();
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void onCompletionInternal(Object obj) {
        if (obj instanceof CompletedExceptionally) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            Throwable th = completedExceptionally.cause;
            completedExceptionally.getHandled();
        }
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Object makeCompletingOnce$kotlinx_coroutines_core = makeCompletingOnce$kotlinx_coroutines_core(InputKt.toState$default(obj, null, 1));
        if (makeCompletingOnce$kotlinx_coroutines_core != JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            afterResume(makeCompletingOnce$kotlinx_coroutines_core);
        }
    }

    public final <R> void start(CoroutineStart coroutineStart, R r, Function2<? super R, ? super Continuation<? super T>, ? extends Object> function2) {
        int ordinal = coroutineStart.ordinal();
        if (ordinal == 0) {
            InputKt.startCoroutineCancellable$default(function2, r, this, null, 4);
        } else if (ordinal == 1) {
        } else {
            if (ordinal == 2) {
                InputKt.intercepted(InputKt.createCoroutineUnintercepted(function2, r, this)).resumeWith(Unit.INSTANCE);
            } else if (ordinal == 3) {
                try {
                    CoroutineContext coroutineContext = this.context;
                    Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, null);
                    if (function2 != null) {
                        TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2);
                        Object invoke = function2.invoke(r, this);
                        ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
                        if (invoke != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            resumeWith(invoke);
                            return;
                        }
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type (R, kotlin.coroutines.Continuation<T>) -> kotlin.Any?");
                } catch (Throwable th) {
                    resumeWith(new Result.Failure(th));
                }
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
    }
}
