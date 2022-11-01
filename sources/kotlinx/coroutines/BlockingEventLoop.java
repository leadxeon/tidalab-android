package kotlinx.coroutines;
/* compiled from: EventLoop.kt */
/* loaded from: classes.dex */
public final class BlockingEventLoop extends EventLoopImplBase {
    public final Thread thread;

    public BlockingEventLoop(Thread thread) {
        this.thread = thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplPlatform
    public Thread getThread() {
        return this.thread;
    }
}
