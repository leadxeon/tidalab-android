package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.ArrayQueue;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadSafeHeap;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
/* compiled from: EventLoop.common.kt */
/* loaded from: classes.dex */
public abstract class EventLoopImplBase extends EventLoopImplPlatform implements Delay {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _queue$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_queue");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _delayed$FU = AtomicReferenceFieldUpdater.newUpdater(EventLoopImplBase.class, Object.class, "_delayed");
    private volatile /* synthetic */ Object _queue = null;
    private volatile /* synthetic */ Object _delayed = null;
    private volatile /* synthetic */ int _isCompleted = 0;

    /* compiled from: EventLoop.common.kt */
    /* loaded from: classes.dex */
    public final class DelayedResumeTask extends DelayedTask {
        public final CancellableContinuation<Unit> cont;

        /* JADX WARN: Multi-variable type inference failed */
        public DelayedResumeTask(long j, CancellableContinuation<? super Unit> cancellableContinuation) {
            super(j);
            this.cont = cancellableContinuation;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.cont.resumeUndispatched(EventLoopImplBase.this, Unit.INSTANCE);
        }

        @Override // kotlinx.coroutines.EventLoopImplBase.DelayedTask
        public String toString() {
            return Intrinsics.stringPlus(super.toString(), this.cont);
        }
    }

    /* compiled from: EventLoop.common.kt */
    /* loaded from: classes.dex */
    public static abstract class DelayedTask implements Runnable, Comparable<DelayedTask>, DisposableHandle, ThreadSafeHeapNode {
        public Object _heap;
        public int index = -1;
        public long nanoTime;

        public DelayedTask(long j) {
            this.nanoTime = j;
        }

        @Override // java.lang.Comparable
        public int compareTo(DelayedTask delayedTask) {
            int i = ((this.nanoTime - delayedTask.nanoTime) > 0L ? 1 : ((this.nanoTime - delayedTask.nanoTime) == 0L ? 0 : -1));
            if (i > 0) {
                return 1;
            }
            return i < 0 ? -1 : 0;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final synchronized void dispose() {
            Object obj = this._heap;
            Symbol symbol = EventLoop_commonKt.DISPOSED_TASK;
            if (obj != symbol) {
                DelayedTaskQueue delayedTaskQueue = obj instanceof DelayedTaskQueue ? (DelayedTaskQueue) obj : null;
                if (delayedTaskQueue != null) {
                    synchronized (delayedTaskQueue) {
                        if (getHeap() != null) {
                            delayedTaskQueue.removeAtImpl(getIndex());
                        }
                    }
                }
                this._heap = symbol;
            }
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public ThreadSafeHeap<?> getHeap() {
            Object obj = this._heap;
            if (obj instanceof ThreadSafeHeap) {
                return (ThreadSafeHeap) obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public int getIndex() {
            return this.index;
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setHeap(ThreadSafeHeap<?> threadSafeHeap) {
            if (this._heap != EventLoop_commonKt.DISPOSED_TASK) {
                this._heap = threadSafeHeap;
                return;
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }

        @Override // kotlinx.coroutines.internal.ThreadSafeHeapNode
        public void setIndex(int i) {
            this.index = i;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Delayed[nanos=");
            outline13.append(this.nanoTime);
            outline13.append(']');
            return outline13.toString();
        }
    }

    /* compiled from: EventLoop.common.kt */
    /* loaded from: classes.dex */
    public static final class DelayedTaskQueue extends ThreadSafeHeap<DelayedTask> {
        public long timeNow;

        public DelayedTaskQueue(long j) {
            this.timeNow = j;
        }
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        enqueue(runnable);
    }

    public final void enqueue(Runnable runnable) {
        if (enqueueImpl(runnable)) {
            Thread thread = getThread();
            if (Thread.currentThread() != thread) {
                LockSupport.unpark(thread);
                return;
            }
            return;
        }
        DefaultExecutor.INSTANCE.enqueue(runnable);
    }

    public final boolean enqueueImpl(Runnable runnable) {
        while (true) {
            Object obj = this._queue;
            if (this._isCompleted != 0) {
                return false;
            }
            if (obj == null) {
                if (_queue$FU.compareAndSet(this, null, runnable)) {
                    return true;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) obj;
                int addLast = lockFreeTaskQueueCore.addLast(runnable);
                if (addLast == 0) {
                    return true;
                }
                if (addLast == 1) {
                    _queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore.next());
                } else if (addLast == 2) {
                    return false;
                }
            } else if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                return false;
            } else {
                LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore2.addLast((Runnable) obj);
                lockFreeTaskQueueCore2.addLast(runnable);
                if (_queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore2)) {
                    return true;
                }
            }
        }
    }

    public boolean isEmpty() {
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        if (!(arrayQueue == null || arrayQueue.head == arrayQueue.tail)) {
            return false;
        }
        DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
        if (delayedTaskQueue != null && !delayedTaskQueue.isEmpty()) {
            return false;
        }
        Object obj = this._queue;
        if (obj == null) {
            return true;
        }
        return obj instanceof LockFreeTaskQueueCore ? ((LockFreeTaskQueueCore) obj).isEmpty() : obj == EventLoop_commonKt.CLOSED_EMPTY;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0050, code lost:
        r7 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0050 A[EDGE_INSN: B:85:0x0050->B:32:0x0050 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:? A[RETURN, SYNTHETIC] */
    @Override // kotlinx.coroutines.EventLoop
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long processNextEvent() {
        /*
            Method dump skipped, instructions count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.processNextEvent():long");
    }

    public final void resetAll() {
        this._queue = null;
        this._delayed = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void schedule(long r13, kotlinx.coroutines.EventLoopImplBase.DelayedTask r15) {
        /*
            r12 = this;
            int r0 = r12._isCompleted
            r1 = 2
            r2 = 0
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L_0x0009
            goto L_0x0034
        L_0x0009:
            java.lang.Object r0 = r12._delayed
            kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue r0 = (kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue) r0
            if (r0 != 0) goto L_0x001d
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.EventLoopImplBase._delayed$FU
            kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue r5 = new kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue
            r5.<init>(r13)
            r0.compareAndSet(r12, r4, r5)
            java.lang.Object r0 = r12._delayed
            kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue r0 = (kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue) r0
        L_0x001d:
            monitor-enter(r15)
            java.lang.Object r5 = r15._heap     // Catch: all -> 0x00a3
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.EventLoop_commonKt.DISPOSED_TASK     // Catch: all -> 0x00a3
            if (r5 != r6) goto L_0x0027
            monitor-exit(r15)
            r0 = 2
            goto L_0x0061
        L_0x0027:
            monitor-enter(r0)     // Catch: all -> 0x00a3
            kotlinx.coroutines.internal.ThreadSafeHeapNode r5 = r0.firstImpl()     // Catch: all -> 0x00a0
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r5 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r5     // Catch: all -> 0x00a0
            int r6 = r12._isCompleted     // Catch: all -> 0x00a0
            if (r6 == 0) goto L_0x0036
            monitor-exit(r0)     // Catch: all -> 0x00a3
            monitor-exit(r15)
        L_0x0034:
            r0 = 1
            goto L_0x0061
        L_0x0036:
            r6 = 0
            if (r5 != 0) goto L_0x003d
            r0.timeNow = r13     // Catch: all -> 0x00a0
            goto L_0x0050
        L_0x003d:
            long r8 = r5.nanoTime     // Catch: all -> 0x00a0
            long r10 = r8 - r13
            int r5 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r5 < 0) goto L_0x0046
            r8 = r13
        L_0x0046:
            long r10 = r0.timeNow     // Catch: all -> 0x00a0
            long r10 = r8 - r10
            int r5 = (r10 > r6 ? 1 : (r10 == r6 ? 0 : -1))
            if (r5 <= 0) goto L_0x0050
            r0.timeNow = r8     // Catch: all -> 0x00a0
        L_0x0050:
            long r8 = r15.nanoTime     // Catch: all -> 0x00a0
            long r10 = r0.timeNow     // Catch: all -> 0x00a0
            long r8 = r8 - r10
            int r5 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r5 >= 0) goto L_0x005b
            r15.nanoTime = r10     // Catch: all -> 0x00a0
        L_0x005b:
            r0.addImpl(r15)     // Catch: all -> 0x00a0
            monitor-exit(r0)     // Catch: all -> 0x00a3
            monitor-exit(r15)
            r0 = 0
        L_0x0061:
            if (r0 == 0) goto L_0x007a
            if (r0 == r3) goto L_0x0074
            if (r0 != r1) goto L_0x0068
            goto L_0x009c
        L_0x0068:
            java.lang.String r13 = "unexpected result"
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r13 = r13.toString()
            r14.<init>(r13)
            throw r14
        L_0x0074:
            kotlinx.coroutines.DefaultExecutor r0 = kotlinx.coroutines.DefaultExecutor.INSTANCE
            r0.schedule(r13, r15)
            goto L_0x009c
        L_0x007a:
            java.lang.Object r13 = r12._delayed
            kotlinx.coroutines.EventLoopImplBase$DelayedTaskQueue r13 = (kotlinx.coroutines.EventLoopImplBase.DelayedTaskQueue) r13
            if (r13 != 0) goto L_0x0081
            goto L_0x008a
        L_0x0081:
            monitor-enter(r13)
            kotlinx.coroutines.internal.ThreadSafeHeapNode r14 = r13.firstImpl()     // Catch: all -> 0x009d
            monitor-exit(r13)
            r4 = r14
            kotlinx.coroutines.EventLoopImplBase$DelayedTask r4 = (kotlinx.coroutines.EventLoopImplBase.DelayedTask) r4
        L_0x008a:
            if (r4 != r15) goto L_0x008d
            r2 = 1
        L_0x008d:
            if (r2 == 0) goto L_0x009c
            java.lang.Thread r13 = r12.getThread()
            java.lang.Thread r14 = java.lang.Thread.currentThread()
            if (r14 == r13) goto L_0x009c
            java.util.concurrent.locks.LockSupport.unpark(r13)
        L_0x009c:
            return
        L_0x009d:
            r14 = move-exception
            monitor-exit(r13)
            throw r14
        L_0x00a0:
            r13 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x00a3
            throw r13     // Catch: all -> 0x00a3
        L_0x00a3:
            r13 = move-exception
            monitor-exit(r15)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoopImplBase.schedule(long, kotlinx.coroutines.EventLoopImplBase$DelayedTask):void");
    }

    @Override // kotlinx.coroutines.Delay
    public void scheduleResumeAfterDelay(long j, CancellableContinuation<? super Unit> cancellableContinuation) {
        long j2 = 0;
        if (j > 0) {
            j2 = j >= 9223372036854L ? Long.MAX_VALUE : 1000000 * j;
        }
        if (j2 < 4611686018427387903L) {
            long nanoTime = System.nanoTime();
            DelayedResumeTask delayedResumeTask = new DelayedResumeTask(j2 + nanoTime, cancellableContinuation);
            cancellableContinuation.invokeOnCancellation(new DisposeOnCancel(delayedResumeTask));
            schedule(nanoTime, delayedResumeTask);
        }
    }

    @Override // kotlinx.coroutines.EventLoop
    public void shutdown() {
        ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
        ThreadLocalEventLoop.ref.set(null);
        this._isCompleted = 1;
        while (true) {
            Object obj = this._queue;
            if (obj == null) {
                if (_queue$FU.compareAndSet(this, null, EventLoop_commonKt.CLOSED_EMPTY)) {
                    break;
                }
            } else if (obj instanceof LockFreeTaskQueueCore) {
                ((LockFreeTaskQueueCore) obj).close();
                break;
            } else if (obj == EventLoop_commonKt.CLOSED_EMPTY) {
                break;
            } else {
                LockFreeTaskQueueCore lockFreeTaskQueueCore = new LockFreeTaskQueueCore(8, true);
                lockFreeTaskQueueCore.addLast((Runnable) obj);
                if (_queue$FU.compareAndSet(this, obj, lockFreeTaskQueueCore)) {
                    break;
                }
            }
        }
        do {
        } while (processNextEvent() <= 0);
        long nanoTime = System.nanoTime();
        while (true) {
            DelayedTaskQueue delayedTaskQueue = (DelayedTaskQueue) this._delayed;
            DelayedTask removeFirstOrNull = delayedTaskQueue == null ? null : delayedTaskQueue.removeFirstOrNull();
            if (removeFirstOrNull != null) {
                DefaultExecutor.INSTANCE.schedule(nanoTime, removeFirstOrNull);
            } else {
                return;
            }
        }
    }
}
