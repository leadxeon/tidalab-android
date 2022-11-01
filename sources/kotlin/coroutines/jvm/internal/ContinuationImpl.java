package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
/* compiled from: ContinuationImpl.kt */
/* loaded from: classes.dex */
public abstract class ContinuationImpl extends BaseContinuationImpl {
    public final CoroutineContext _context;
    public transient Continuation<Object> intercepted;

    public ContinuationImpl(Continuation<Object> continuation, CoroutineContext coroutineContext) {
        super(continuation);
        this._context = coroutineContext;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this._context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        Continuation<?> continuation = this.intercepted;
        if (!(continuation == null || continuation == this)) {
            CoroutineContext coroutineContext = this._context;
            int i = ContinuationInterceptor.$r8$clinit;
            ((ContinuationInterceptor) coroutineContext.get(ContinuationInterceptor.Key.$$INSTANCE)).releaseInterceptedContinuation(continuation);
        }
        this.intercepted = CompletedContinuation.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContinuationImpl(Continuation<Object> continuation) {
        super(continuation);
        CoroutineContext context = continuation != null ? continuation.getContext() : null;
        this._context = context;
    }
}
