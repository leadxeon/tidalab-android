package kotlinx.coroutines;

import kotlin.Result;
import kotlin.Unit;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class ResumeAwaitOnCompletion<T> extends JobNode {
    public final CancellableContinuationImpl<T> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeAwaitOnCompletion(CancellableContinuationImpl<? super T> cancellableContinuationImpl) {
        this.continuation = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        Object state$kotlinx_coroutines_core = getJob().getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            this.continuation.resumeWith(new Result.Failure(((CompletedExceptionally) state$kotlinx_coroutines_core).cause));
        } else {
            this.continuation.resumeWith(JobSupportKt.unboxState(state$kotlinx_coroutines_core));
        }
    }
}
