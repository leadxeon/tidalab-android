package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class InvokeOnCompletion extends JobNode {
    public final Function1<Throwable, Unit> handler;

    /* JADX WARN: Multi-variable type inference failed */
    public InvokeOnCompletion(Function1<? super Throwable, Unit> function1) {
        this.handler = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.handler.invoke(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        this.handler.invoke(th);
    }
}
