package kotlinx.coroutines;

import kotlin.Unit;
import kotlinx.coroutines.internal.DispatchedContinuation;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class ChildContinuation extends JobCancellingNode {
    public final CancellableContinuationImpl<?> child;

    public ChildContinuation(CancellableContinuationImpl<?> cancellableContinuationImpl) {
        this.child = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        CancellableContinuationImpl<?> cancellableContinuationImpl = this.child;
        Throwable continuationCancellationCause = cancellableContinuationImpl.getContinuationCancellationCause(getJob());
        boolean z = false;
        if ((cancellableContinuationImpl.resumeMode == 2) && cancellableContinuationImpl.isReusable()) {
            z = ((DispatchedContinuation) cancellableContinuationImpl.delegate).postponeCancellation(continuationCancellationCause);
        }
        if (!z) {
            cancellableContinuationImpl.cancel(continuationCancellationCause);
            cancellableContinuationImpl.detachChildIfNonResuable();
        }
    }
}
