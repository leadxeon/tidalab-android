package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.sync.MutexImpl;
/* compiled from: Mutex.kt */
/* loaded from: classes.dex */
public final class MutexImpl$LockCont$tryResumeLockWaiter$1 extends Lambda implements Function1<Throwable, Unit> {
    public final /* synthetic */ MutexImpl this$0;
    public final /* synthetic */ MutexImpl.LockCont this$1;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutexImpl$LockCont$tryResumeLockWaiter$1(MutexImpl mutexImpl, MutexImpl.LockCont lockCont) {
        super(1);
        this.this$0 = mutexImpl;
        this.this$1 = lockCont;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.this$0.unlock(this.this$1.owner);
        return Unit.INSTANCE;
    }
}
