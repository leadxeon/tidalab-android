package kotlin.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
/* compiled from: SafeContinuationJvm.kt */
/* loaded from: classes.dex */
public final class SafeContinuation<T> implements Continuation<T>, CoroutineStackFrame {
    @Deprecated
    public static final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> RESULT = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, "result");
    public final Continuation<T> delegate;
    private volatile Object result;

    /* JADX WARN: Multi-variable type inference failed */
    public SafeContinuation(Continuation<? super T> continuation) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.UNDECIDED;
        this.delegate = continuation;
        this.result = coroutineSingletons;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        return (CoroutineStackFrame) continuation;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.delegate.getContext();
    }

    public final Object getOrThrow() {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Object obj = this.result;
        CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.UNDECIDED;
        if (obj == coroutineSingletons2) {
            if (RESULT.compareAndSet(this, coroutineSingletons2, coroutineSingletons)) {
                return coroutineSingletons;
            }
            obj = this.result;
        }
        if (obj == CoroutineSingletons.RESUMED) {
            return coroutineSingletons;
        }
        if (!(obj instanceof Result.Failure)) {
            return obj;
        }
        throw ((Result.Failure) obj).exception;
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        while (true) {
            Object obj2 = this.result;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.UNDECIDED;
            if (obj2 != coroutineSingletons) {
                CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (obj2 != coroutineSingletons2) {
                    throw new IllegalStateException("Already resumed");
                } else if (RESULT.compareAndSet(this, coroutineSingletons2, CoroutineSingletons.RESUMED)) {
                    this.delegate.resumeWith(obj);
                    return;
                }
            } else if (RESULT.compareAndSet(this, coroutineSingletons, obj)) {
                return;
            }
        }
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SafeContinuation for ");
        outline13.append(this.delegate);
        return outline13.toString();
    }
}
