package kotlinx.coroutines;
/* compiled from: EventLoop.common.kt */
/* loaded from: classes.dex */
public final class ThreadLocalEventLoop {
    public static final ThreadLocalEventLoop INSTANCE = null;
    public static final ThreadLocal<EventLoop> ref = new ThreadLocal<>();

    public static final EventLoop getEventLoop$kotlinx_coroutines_core() {
        ThreadLocal<EventLoop> threadLocal = ref;
        EventLoop eventLoop = threadLocal.get();
        if (eventLoop != null) {
            return eventLoop;
        }
        BlockingEventLoop blockingEventLoop = new BlockingEventLoop(Thread.currentThread());
        threadLocal.set(blockingEventLoop);
        return blockingEventLoop;
    }
}
