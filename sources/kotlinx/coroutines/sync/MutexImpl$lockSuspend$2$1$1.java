package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: Mutex.kt */
/* loaded from: classes.dex */
public final class MutexImpl$lockSuspend$2$1$1 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ Object $owner;
    public final /* synthetic */ MutexImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutexImpl$lockSuspend$2$1$1(MutexImpl mutexImpl, Object obj) {
        super(1);
        this.this$0 = mutexImpl;
        this.$owner = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.this$0.unlock(this.$owner);
        return Unit.INSTANCE;
    }
}
