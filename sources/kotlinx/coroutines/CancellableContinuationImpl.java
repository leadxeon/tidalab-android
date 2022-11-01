package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.Symbol;
/* compiled from: CancellableContinuationImpl.kt */
/* loaded from: classes.dex */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decision");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");
    private volatile /* synthetic */ int _decision = 0;
    private volatile /* synthetic */ Object _state = Active.INSTANCE;
    public final CoroutineContext context;
    public final Continuation<T> delegate;
    public DisposableHandle parentHandle;

    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationImpl(Continuation<? super T> continuation, int i) {
        super(i);
        this.delegate = continuation;
        this.context = continuation.getContext();
    }

    public final void callCancelHandler(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.invoke(th);
        } catch (Throwable th2) {
            InputKt.handleCoroutineException(this.context, new CompletionHandlerException(Intrinsics.stringPlus("Exception in invokeOnCancellation handler for ", this), th2));
        }
    }

    public final void callOnCancellation(Function1<? super Throwable, Unit> function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            InputKt.handleCoroutineException(this.context, new CompletionHandlerException(Intrinsics.stringPlus("Exception in resume onCancellation handler for ", this), th2));
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(Throwable th) {
        Object obj;
        boolean z;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            z = obj instanceof CancelHandler;
        } while (!_state$FU.compareAndSet(this, obj, new CancelledContinuation(this, th, z)));
        CancelHandler cancelHandler = z ? (CancelHandler) obj : null;
        if (cancelHandler != null) {
            callCancelHandler(cancelHandler, th);
        }
        detachChildIfNonResuable();
        dispatchResume(this.resumeMode);
        return true;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th) {
        while (true) {
            Object obj2 = this._state;
            if (obj2 instanceof NotCompleted) {
                throw new IllegalStateException("Not completed".toString());
            } else if (!(obj2 instanceof CompletedExceptionally)) {
                if (obj2 instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                    if (!(completedContinuation.cancelCause != null)) {
                        if (_state$FU.compareAndSet(this, obj2, CompletedContinuation.copy$default(completedContinuation, null, null, null, null, th, 15))) {
                            CancelHandler cancelHandler = completedContinuation.cancelHandler;
                            if (cancelHandler != null) {
                                callCancelHandler(cancelHandler, th);
                            }
                            Function1<Throwable, Unit> function1 = completedContinuation.onCancellation;
                            if (function1 != null) {
                                callOnCancellation(function1, th);
                                return;
                            }
                            return;
                        }
                    } else {
                        throw new IllegalStateException("Must be called at most once".toString());
                    }
                } else if (_state$FU.compareAndSet(this, obj2, new CompletedContinuation(obj2, null, null, null, th, 14))) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void completeResume(Object obj) {
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle disposableHandle = this.parentHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    public final void detachChildIfNonResuable() {
        if (!isReusable()) {
            detachChild$kotlinx_coroutines_core();
        }
    }

    /* JADX WARN: Finally extract failed */
    public final void dispatchResume(int i) {
        boolean z;
        boolean z2;
        while (true) {
            int i2 = this._decision;
            z = false;
            if (i2 == 0) {
                if (_decision$FU.compareAndSet(this, 0, 2)) {
                    z2 = true;
                    break;
                }
            } else if (i2 == 1) {
                z2 = false;
            } else {
                throw new IllegalStateException("Already resumed".toString());
            }
        }
        if (!z2) {
            Continuation<T> delegate$kotlinx_coroutines_core = getDelegate$kotlinx_coroutines_core();
            if (i == 4) {
                z = true;
            }
            if (z || !(delegate$kotlinx_coroutines_core instanceof DispatchedContinuation) || InputKt.isCancellableMode(i) != InputKt.isCancellableMode(this.resumeMode)) {
                InputKt.resume(this, delegate$kotlinx_coroutines_core, z);
                return;
            }
            CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation) delegate$kotlinx_coroutines_core).dispatcher;
            CoroutineContext context = delegate$kotlinx_coroutines_core.getContext();
            if (coroutineDispatcher.isDispatchNeeded(context)) {
                coroutineDispatcher.dispatch(context, this);
                return;
            }
            ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
            EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core();
            if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
                eventLoop$kotlinx_coroutines_core.dispatchUnconfined(this);
                return;
            }
            eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
            try {
                InputKt.resume(this, getDelegate$kotlinx_coroutines_core(), true);
                do {
                } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
            } catch (Throwable th) {
                try {
                    handleFatalException(th, null);
                } finally {
                    eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
                }
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.context;
    }

    public Throwable getContinuationCancellationCause(Job job) {
        return ((JobSupport) job).getCancellationException();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        Throwable exceptionalResult$kotlinx_coroutines_core = super.getExceptionalResult$kotlinx_coroutines_core(obj);
        if (exceptionalResult$kotlinx_coroutines_core == null) {
            return null;
        }
        return exceptionalResult$kotlinx_coroutines_core;
    }

    public final Object getResult() {
        boolean z;
        boolean isReusable = isReusable();
        while (true) {
            int i = this._decision;
            z = false;
            if (i == 0) {
                if (_decision$FU.compareAndSet(this, 0, 1)) {
                    z = true;
                    break;
                }
            } else if (i != 2) {
                throw new IllegalStateException("Already suspended".toString());
            }
        }
        if (z) {
            if (this.parentHandle == null) {
                installParentHandle();
            }
            if (isReusable) {
                releaseClaimedReusableContinuation();
            }
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        if (isReusable) {
            releaseClaimedReusableContinuation();
        }
        Object obj = this._state;
        if (obj instanceof CompletedExceptionally) {
            throw ((CompletedExceptionally) obj).cause;
        }
        if (InputKt.isCancellableMode(this.resumeMode)) {
            CoroutineContext coroutineContext = this.context;
            int i2 = Job.$r8$clinit;
            Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
            if (job != null && !job.isActive()) {
                CancellationException cancellationException = job.getCancellationException();
                cancelCompletedResult$kotlinx_coroutines_core(obj, cancellationException);
                throw cancellationException;
            }
        }
        return getSuccessfulResult$kotlinx_coroutines_core(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        return obj instanceof CompletedContinuation ? (T) ((CompletedContinuation) obj).result : obj;
    }

    public void initCancellability() {
        DisposableHandle installParentHandle = installParentHandle();
        if (installParentHandle != null && isCompleted()) {
            installParentHandle.dispose();
            this.parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    public final DisposableHandle installParentHandle() {
        CoroutineContext coroutineContext = this.context;
        int i = Job.$r8$clinit;
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job == null) {
            return null;
        }
        DisposableHandle invokeOnCompletion$default = InputKt.invokeOnCompletion$default(job, true, false, new ChildContinuation(this), 2, null);
        this.parentHandle = invokeOnCompletion$default;
        return invokeOnCompletion$default;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(Function1<? super Throwable, Unit> function1) {
        CancelHandler invokeOnCancel = function1 instanceof CancelHandler ? (CancelHandler) function1 : new InvokeOnCancel(function1);
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof Active)) {
                Throwable th = null;
                if (!(obj instanceof CancelHandler)) {
                    boolean z = obj instanceof CompletedExceptionally;
                    boolean z2 = true;
                    if (z) {
                        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
                        Objects.requireNonNull(completedExceptionally);
                        if (!CompletedExceptionally._handled$FU.compareAndSet(completedExceptionally, 0, 1)) {
                            multipleHandlersError(function1, obj);
                            throw null;
                        } else if (obj instanceof CancelledContinuation) {
                            if (!z) {
                                completedExceptionally = null;
                            }
                            if (completedExceptionally != null) {
                                th = completedExceptionally.cause;
                            }
                            callCancelHandler(function1, th);
                            return;
                        } else {
                            return;
                        }
                    } else if (obj instanceof CompletedContinuation) {
                        CompletedContinuation completedContinuation = (CompletedContinuation) obj;
                        if (completedContinuation.cancelHandler != null) {
                            multipleHandlersError(function1, obj);
                            throw null;
                        } else if (!(invokeOnCancel instanceof BeforeResumeCancelHandler)) {
                            Throwable th2 = completedContinuation.cancelCause;
                            if (th2 == null) {
                                z2 = false;
                            }
                            if (z2) {
                                callCancelHandler(function1, th2);
                                return;
                            } else {
                                if (_state$FU.compareAndSet(this, obj, CompletedContinuation.copy$default(completedContinuation, null, invokeOnCancel, null, null, null, 29))) {
                                    return;
                                }
                            }
                        } else {
                            return;
                        }
                    } else if (!(invokeOnCancel instanceof BeforeResumeCancelHandler)) {
                        if (_state$FU.compareAndSet(this, obj, new CompletedContinuation(obj, invokeOnCancel, null, null, null, 28))) {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    multipleHandlersError(function1, obj);
                    throw null;
                }
            } else if (_state$FU.compareAndSet(this, obj, invokeOnCancel)) {
                return;
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return !(this._state instanceof NotCompleted);
    }

    public final boolean isReusable() {
        Continuation<T> continuation = this.delegate;
        return (continuation instanceof DispatchedContinuation) && ((DispatchedContinuation) continuation).isReusable(this);
    }

    public final void multipleHandlersError(Function1<? super Throwable, Unit> function1, Object obj) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + function1 + ", already has " + obj).toString());
    }

    public String nameString() {
        return "CancellableContinuation";
    }

    public final void releaseClaimedReusableContinuation() {
        Continuation<T> continuation = this.delegate;
        Throwable th = null;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        if (dispatchedContinuation != null) {
            th = dispatchedContinuation.tryReleaseClaimedContinuation(this);
        }
        if (th != null) {
            detachChild$kotlinx_coroutines_core();
            cancel(th);
        }
    }

    public final boolean resetStateReusable() {
        Object obj = this._state;
        if (!(obj instanceof CompletedContinuation) || ((CompletedContinuation) obj).idempotentResume == null) {
            this._decision = 0;
            this._state = Active.INSTANCE;
            return true;
        }
        detachChild$kotlinx_coroutines_core();
        return false;
    }

    public final void resumeImpl(Object obj, int i, Function1<? super Throwable, Unit> function1) {
        Object obj2;
        do {
            obj2 = this._state;
            if (obj2 instanceof NotCompleted) {
            } else {
                if (obj2 instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                    Objects.requireNonNull(cancelledContinuation);
                    if (CancelledContinuation._resumed$FU.compareAndSet(cancelledContinuation, 0, 1)) {
                        if (function1 != null) {
                            callOnCancellation(function1, cancelledContinuation.cause);
                            return;
                        }
                        return;
                    }
                }
                throw new IllegalStateException(Intrinsics.stringPlus("Already resumed, but proposed with update ", obj).toString());
            }
        } while (!_state$FU.compareAndSet(this, obj2, resumedState((NotCompleted) obj2, obj, i, function1, null)));
        detachChildIfNonResuable();
        dispatchResume(i);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatched(CoroutineDispatcher coroutineDispatcher, T t) {
        Continuation<T> continuation = this.delegate;
        DispatchedContinuation dispatchedContinuation = continuation instanceof DispatchedContinuation ? (DispatchedContinuation) continuation : null;
        resumeImpl(t, (dispatchedContinuation == null ? null : dispatchedContinuation.dispatcher) == coroutineDispatcher ? 4 : this.resumeMode, null);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        Throwable th = Result.m11exceptionOrNullimpl(obj);
        if (th != null) {
            obj = new CompletedExceptionally(th, false, 2);
        }
        resumeImpl(obj, this.resumeMode, null);
    }

    public final Object resumedState(NotCompleted notCompleted, Object obj, int i, Function1<? super Throwable, Unit> function1, Object obj2) {
        if (obj instanceof CompletedExceptionally) {
            return obj;
        }
        if (!InputKt.isCancellableMode(i) && obj2 == null) {
            return obj;
        }
        if (function1 == null && ((!(notCompleted instanceof CancelHandler) || (notCompleted instanceof BeforeResumeCancelHandler)) && obj2 == null)) {
            return obj;
        }
        return new CompletedContinuation(obj, notCompleted instanceof CancelHandler ? (CancelHandler) notCompleted : null, function1, obj2, null, 16);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        return this._state;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(nameString());
        sb.append('(');
        sb.append(InputKt.toDebugString(this.delegate));
        sb.append("){");
        Object obj = this._state;
        if (obj instanceof NotCompleted) {
            str = "Active";
        } else {
            str = obj instanceof CancelledContinuation ? "Cancelled" : "Completed";
        }
        sb.append(str);
        sb.append("}@");
        sb.append(InputKt.getHexAddress(this));
        return sb.toString();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(T t, Object obj) {
        return tryResumeImpl(t, obj, null);
    }

    public final Symbol tryResumeImpl(Object obj, Object obj2, Function1<? super Throwable, Unit> function1) {
        Object obj3;
        do {
            obj3 = this._state;
            if (obj3 instanceof NotCompleted) {
            } else if (!(obj3 instanceof CompletedContinuation) || obj2 == null || ((CompletedContinuation) obj3).idempotentResume != obj2) {
                return null;
            } else {
                return CancellableContinuationImplKt.RESUME_TOKEN;
            }
        } while (!_state$FU.compareAndSet(this, obj3, resumedState((NotCompleted) obj3, obj, this.resumeMode, function1, obj2)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public Object tryResume(T t, Object obj, Function1<? super Throwable, Unit> function1) {
        return tryResumeImpl(t, obj, function1);
    }

    public final void callCancelHandler(Function1<? super Throwable, Unit> function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            InputKt.handleCoroutineException(this.context, new CompletionHandlerException(Intrinsics.stringPlus("Exception in invokeOnCancellation handler for ", this), th2));
        }
    }
}
