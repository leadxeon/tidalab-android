package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Builders.kt */
/* loaded from: classes.dex */
public final class BlockingCoroutine<T> extends AbstractCoroutine<T> {
    public final Thread blockedThread;
    public final EventLoop eventLoop;

    public BlockingCoroutine(CoroutineContext coroutineContext, Thread thread, EventLoop eventLoop) {
        super(coroutineContext, true, true);
        this.blockedThread = thread;
        this.eventLoop = eventLoop;
    }

    @Override // kotlinx.coroutines.JobSupport
    public void afterCompletion(Object obj) {
        if (!Intrinsics.areEqual(Thread.currentThread(), this.blockedThread)) {
            LockSupport.unpark(this.blockedThread);
        }
    }
}
