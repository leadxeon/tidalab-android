package kotlinx.coroutines.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.ThreadLocalEventLoop;
/* compiled from: DispatchedContinuation.kt */
/* loaded from: classes.dex */
public final class DispatchedContinuation<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _reusableCancellableContinuation$FU = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation.class, Object.class, "_reusableCancellableContinuation");
    public final Continuation<T> continuation;
    public final Object countOrElement;
    public final CoroutineDispatcher dispatcher;
    public Object _state = DispatchedContinuationKt.UNDEFINED;
    private volatile /* synthetic */ Object _reusableCancellableContinuation = null;

    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation(CoroutineDispatcher coroutineDispatcher, Continuation<? super T> continuation) {
        super(-1);
        this.dispatcher = coroutineDispatcher;
        this.continuation = continuation;
        CoroutineContext context = getContext();
        Symbol symbol = ThreadContextKt.NO_THREAD_ELEMENTS;
        this.countOrElement = context.fold(0, ThreadContextKt$countAll$1.INSTANCE);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th) {
        if (obj instanceof CompletedWithCancellation) {
            ((CompletedWithCancellation) obj).onCancellation.invoke(th);
        }
    }

    public final CancellableContinuationImpl<T> claimReusableCancellableContinuation() {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            if (obj == null) {
                this._reusableCancellableContinuation = DispatchedContinuationKt.REUSABLE_CLAIMED;
                return null;
            } else if (obj instanceof CancellableContinuationImpl) {
                if (_reusableCancellableContinuation$FU.compareAndSet(this, obj, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    return (CancellableContinuationImpl) obj;
                }
            } else if (obj != DispatchedContinuationKt.REUSABLE_CLAIMED && !(obj instanceof Throwable)) {
                throw new IllegalStateException(Intrinsics.stringPlus("Inconsistent state ", obj).toString());
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.continuation;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this;
    }

    public final boolean isReusable(CancellableContinuationImpl<?> cancellableContinuationImpl) {
        Object obj = this._reusableCancellableContinuation;
        if (obj == null) {
            return false;
        }
        return !(obj instanceof CancellableContinuationImpl) || obj == cancellableContinuationImpl;
    }

    public final boolean postponeCancellation(Throwable th) {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            Symbol symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (Intrinsics.areEqual(obj, symbol)) {
                if (_reusableCancellableContinuation$FU.compareAndSet(this, symbol, th)) {
                    return true;
                }
            } else if (obj instanceof Throwable) {
                return true;
            } else {
                if (_reusableCancellableContinuation$FU.compareAndSet(this, obj, null)) {
                    return false;
                }
            }
        }
    }

    public final void release() {
        do {
        } while (this._reusableCancellableContinuation == DispatchedContinuationKt.REUSABLE_CLAIMED);
        Object obj = this._reusableCancellableContinuation;
        CancellableContinuationImpl cancellableContinuationImpl = obj instanceof CancellableContinuationImpl ? (CancellableContinuationImpl) obj : null;
        if (cancellableContinuationImpl != null) {
            cancellableContinuationImpl.detachChild$kotlinx_coroutines_core();
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        CoroutineContext context = this.continuation.getContext();
        Object state$default = InputKt.toState$default(obj, null, 1);
        if (this.dispatcher.isDispatchNeeded(context)) {
            this._state = state$default;
            this.resumeMode = 0;
            this.dispatcher.dispatch(context, this);
            return;
        }
        ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            this._state = state$default;
            this.resumeMode = 0;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(this);
            return;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            CoroutineContext context2 = getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context2, this.countOrElement);
            this.continuation.resumeWith(obj);
            Unit unit = Unit.INSTANCE;
            ThreadContextKt.restoreThreadContext(context2, updateThreadContext);
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public Object takeState$kotlinx_coroutines_core() {
        Object obj = this._state;
        this._state = DispatchedContinuationKt.UNDEFINED;
        return obj;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("DispatchedContinuation[");
        outline13.append(this.dispatcher);
        outline13.append(", ");
        outline13.append(InputKt.toDebugString(this.continuation));
        outline13.append(']');
        return outline13.toString();
    }

    public final Throwable tryReleaseClaimedContinuation(CancellableContinuation<?> cancellableContinuation) {
        Symbol symbol;
        do {
            Object obj = this._reusableCancellableContinuation;
            symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (obj != symbol) {
                if (!(obj instanceof Throwable)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Inconsistent state ", obj).toString());
                } else if (_reusableCancellableContinuation$FU.compareAndSet(this, obj, null)) {
                    return (Throwable) obj;
                } else {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
        } while (!_reusableCancellableContinuation$FU.compareAndSet(this, symbol, cancellableContinuation));
        return null;
    }
}
