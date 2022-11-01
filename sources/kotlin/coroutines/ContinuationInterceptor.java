package kotlin.coroutines;

import kotlin.coroutines.CoroutineContext;
/* compiled from: ContinuationInterceptor.kt */
/* loaded from: classes.dex */
public interface ContinuationInterceptor extends CoroutineContext.Element {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* compiled from: ContinuationInterceptor.kt */
    /* loaded from: classes.dex */
    public static final class Key implements CoroutineContext.Key<ContinuationInterceptor> {
        public static final /* synthetic */ Key $$INSTANCE = new Key();
    }

    <T> Continuation<T> interceptContinuation(Continuation<? super T> continuation);

    void releaseInterceptedContinuation(Continuation<?> continuation);
}
