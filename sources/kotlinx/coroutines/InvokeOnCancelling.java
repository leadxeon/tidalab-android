package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class InvokeOnCancelling extends JobCancellingNode {
    public static final /* synthetic */ AtomicIntegerFieldUpdater _invoked$FU = AtomicIntegerFieldUpdater.newUpdater(InvokeOnCancelling.class, "_invoked");
    private volatile /* synthetic */ int _invoked = 0;
    public final Function1<Throwable, Unit> handler;

    /* JADX WARN: Multi-variable type inference failed */
    public InvokeOnCancelling(Function1<? super Throwable, Unit> function1) {
        this.handler = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        if (_invoked$FU.compareAndSet(this, 0, 1)) {
            this.handler.invoke(th);
        }
    }
}
