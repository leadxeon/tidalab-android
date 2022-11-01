package kotlinx.coroutines.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.AbstractCoroutine;
/* compiled from: Scopes.kt */
/* loaded from: classes.dex */
public class ScopeCoroutine<T> extends AbstractCoroutine<T> implements CoroutineStackFrame {
    public final Continuation<T> uCont;

    /* JADX WARN: Multi-variable type inference failed */
    public ScopeCoroutine(CoroutineContext coroutineContext, Continuation<? super T> continuation) {
        super(coroutineContext, true, true);
        this.uCont = continuation;
    }

    @Override // kotlinx.coroutines.JobSupport
    public void afterCompletion(Object obj) {
        DispatchedContinuationKt.resumeCancellableWith$default(InputKt.intercepted(this.uCont), InputKt.recoverResult(obj, this.uCont), null, 2);
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    public void afterResume(Object obj) {
        Continuation<T> continuation = this.uCont;
        continuation.resumeWith(InputKt.recoverResult(obj, continuation));
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.uCont;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final boolean isScopedCoroutine() {
        return true;
    }
}
