package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class ResumeOnCompletion extends JobNode {
    public final Continuation<Unit> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeOnCompletion(Continuation<? super Unit> continuation) {
        this.continuation = continuation;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        this.continuation.resumeWith(Unit.INSTANCE);
    }
}
