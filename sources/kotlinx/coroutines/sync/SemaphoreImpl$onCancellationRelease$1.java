package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Semaphore.kt */
/* loaded from: classes.dex */
public final class SemaphoreImpl$onCancellationRelease$1 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ SemaphoreImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SemaphoreImpl$onCancellationRelease$1(SemaphoreImpl semaphoreImpl) {
        super(1);
        this.this$0 = semaphoreImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.this$0.release();
        return Unit.INSTANCE;
    }
}
