package kotlinx.coroutines.scheduling;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.LockSupport;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.internal.Symbol;
/* compiled from: CoroutineScheduler.kt */
/* loaded from: classes.dex */
public final class CoroutineScheduler implements Executor, Closeable {
    private volatile /* synthetic */ int _isTerminated;
    public volatile /* synthetic */ long controlState;
    public final int corePoolSize;
    public final GlobalQueue globalBlockingQueue;
    public final GlobalQueue globalCpuQueue;
    public final long idleWorkerKeepAliveNs;
    public final int maxPoolSize;
    private volatile /* synthetic */ long parkedWorkersStack;
    public final String schedulerName;
    public final AtomicReferenceArray<Worker> workers;
    public static final Symbol NOT_IN_STACK = new Symbol("NOT_IN_STACK");
    public static final /* synthetic */ AtomicLongFieldUpdater parkedWorkersStack$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "parkedWorkersStack");
    public static final /* synthetic */ AtomicLongFieldUpdater controlState$FU = AtomicLongFieldUpdater.newUpdater(CoroutineScheduler.class, "controlState");
    public static final /* synthetic */ AtomicIntegerFieldUpdater _isTerminated$FU = AtomicIntegerFieldUpdater.newUpdater(CoroutineScheduler.class, "_isTerminated");

    /* compiled from: CoroutineScheduler.kt */
    /* loaded from: classes.dex */
    public final class Worker extends Thread {
        public static final /* synthetic */ AtomicIntegerFieldUpdater workerCtl$FU = AtomicIntegerFieldUpdater.newUpdater(Worker.class, "workerCtl");
        private volatile int indexInArray;
        public boolean mayHaveLocalTasks;
        public long minDelayUntilStealableTaskNs;
        public long terminationDeadline;
        public final WorkQueue localQueue = new WorkQueue();
        public WorkerState state = WorkerState.DORMANT;
        public volatile /* synthetic */ int workerCtl = 0;
        private volatile Object nextParkedWorker = CoroutineScheduler.NOT_IN_STACK;
        public int rngState = Random.defaultRandom.nextInt();

        public Worker(int i) {
            CoroutineScheduler.this = CoroutineScheduler.this;
            setDaemon(true);
            Random.Default r1 = Random.Default;
            setIndexInArray(i);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0035  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x0069  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final kotlinx.coroutines.scheduling.Task findTask(boolean r11) {
            /*
                r10 = this;
                kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r0 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.CPU_ACQUIRED
                kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r1 = r10.state
                r2 = 1
                r3 = 0
                if (r1 != r0) goto L_0x0009
                goto L_0x0030
            L_0x0009:
                kotlinx.coroutines.scheduling.CoroutineScheduler r1 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
            L_0x000b:
                long r6 = r1.controlState
                r4 = 9223367638808264704(0x7ffffc0000000000, double:NaN)
                long r4 = r4 & r6
                r8 = 42
                long r4 = r4 >> r8
                int r5 = (int) r4
                if (r5 != 0) goto L_0x001b
                r1 = 0
                goto L_0x002c
            L_0x001b:
                r4 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
                long r8 = r6 - r4
                java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = kotlinx.coroutines.scheduling.CoroutineScheduler.controlState$FU
                r5 = r1
                boolean r4 = r4.compareAndSet(r5, r6, r8)
                if (r4 == 0) goto L_0x000b
                r1 = 1
            L_0x002c:
                if (r1 == 0) goto L_0x0032
                r10.state = r0
            L_0x0030:
                r0 = 1
                goto L_0x0033
            L_0x0032:
                r0 = 0
            L_0x0033:
                if (r0 == 0) goto L_0x0069
                if (r11 == 0) goto L_0x005e
                kotlinx.coroutines.scheduling.CoroutineScheduler r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                int r11 = r11.corePoolSize
                int r11 = r11 * 2
                int r11 = r10.nextInt(r11)
                if (r11 != 0) goto L_0x0044
                goto L_0x0045
            L_0x0044:
                r2 = 0
            L_0x0045:
                if (r2 == 0) goto L_0x004d
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 != 0) goto L_0x0068
            L_0x004d:
                kotlinx.coroutines.scheduling.WorkQueue r11 = r10.localQueue
                kotlinx.coroutines.scheduling.Task r11 = r11.poll()
                if (r11 != 0) goto L_0x0068
                if (r2 != 0) goto L_0x0064
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 != 0) goto L_0x0068
                goto L_0x0064
            L_0x005e:
                kotlinx.coroutines.scheduling.Task r11 = r10.pollGlobalQueues()
                if (r11 != 0) goto L_0x0068
            L_0x0064:
                kotlinx.coroutines.scheduling.Task r11 = r10.trySteal(r3)
            L_0x0068:
                return r11
            L_0x0069:
                if (r11 == 0) goto L_0x007e
                kotlinx.coroutines.scheduling.WorkQueue r11 = r10.localQueue
                kotlinx.coroutines.scheduling.Task r11 = r11.poll()
                if (r11 != 0) goto L_0x0088
                kotlinx.coroutines.scheduling.CoroutineScheduler r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                kotlinx.coroutines.scheduling.GlobalQueue r11 = r11.globalBlockingQueue
                java.lang.Object r11 = r11.removeFirstOrNull()
                kotlinx.coroutines.scheduling.Task r11 = (kotlinx.coroutines.scheduling.Task) r11
                goto L_0x0088
            L_0x007e:
                kotlinx.coroutines.scheduling.CoroutineScheduler r11 = kotlinx.coroutines.scheduling.CoroutineScheduler.this
                kotlinx.coroutines.scheduling.GlobalQueue r11 = r11.globalBlockingQueue
                java.lang.Object r11 = r11.removeFirstOrNull()
                kotlinx.coroutines.scheduling.Task r11 = (kotlinx.coroutines.scheduling.Task) r11
            L_0x0088:
                if (r11 != 0) goto L_0x008e
                kotlinx.coroutines.scheduling.Task r11 = r10.trySteal(r2)
            L_0x008e:
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.Worker.findTask(boolean):kotlinx.coroutines.scheduling.Task");
        }

        public final int getIndexInArray() {
            return this.indexInArray;
        }

        public final Object getNextParkedWorker() {
            return this.nextParkedWorker;
        }

        public final int nextInt(int i) {
            int i2 = this.rngState;
            int i3 = i2 ^ (i2 << 13);
            int i4 = i3 ^ (i3 >> 17);
            int i5 = i4 ^ (i4 << 5);
            this.rngState = i5;
            int i6 = i - 1;
            return (i6 & i) == 0 ? i5 & i6 : (i5 & Integer.MAX_VALUE) % i;
        }

        public final Task pollGlobalQueues() {
            if (nextInt(2) == 0) {
                Task removeFirstOrNull = CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull();
                return removeFirstOrNull == null ? CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull() : removeFirstOrNull;
            }
            Task removeFirstOrNull2 = CoroutineScheduler.this.globalBlockingQueue.removeFirstOrNull();
            return removeFirstOrNull2 == null ? CoroutineScheduler.this.globalCpuQueue.removeFirstOrNull() : removeFirstOrNull2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            WorkerState workerState = WorkerState.PARKING;
            WorkerState workerState2 = WorkerState.TERMINATED;
            loop0: while (true) {
                boolean z = false;
                while (!CoroutineScheduler.this.isTerminated() && this.state != workerState2) {
                    Task findTask = findTask(this.mayHaveLocalTasks);
                    if (findTask != null) {
                        this.minDelayUntilStealableTaskNs = 0L;
                        WorkerState workerState3 = WorkerState.BLOCKING;
                        int taskMode = findTask.taskContext.getTaskMode();
                        this.terminationDeadline = 0L;
                        if (this.state == workerState) {
                            this.state = workerState3;
                        }
                        if (taskMode != 0 && tryReleaseCpu(workerState3)) {
                            CoroutineScheduler.this.signalCpuWork();
                        }
                        CoroutineScheduler.this.runSafely(findTask);
                        if (taskMode != 0) {
                            CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, -2097152L);
                            if (this.state != workerState2) {
                                this.state = WorkerState.DORMANT;
                            }
                        }
                    } else {
                        this.mayHaveLocalTasks = false;
                        if (this.minDelayUntilStealableTaskNs == 0) {
                            if (!(this.nextParkedWorker != CoroutineScheduler.NOT_IN_STACK)) {
                                CoroutineScheduler.this.parkedWorkersStackPush(this);
                            } else {
                                this.workerCtl = -1;
                                while (true) {
                                    if ((this.nextParkedWorker != CoroutineScheduler.NOT_IN_STACK) && this.workerCtl == -1 && !CoroutineScheduler.this.isTerminated() && this.state != workerState2) {
                                        tryReleaseCpu(workerState);
                                        Thread.interrupted();
                                        if (this.terminationDeadline == 0) {
                                            this.terminationDeadline = System.nanoTime() + CoroutineScheduler.this.idleWorkerKeepAliveNs;
                                        }
                                        LockSupport.parkNanos(CoroutineScheduler.this.idleWorkerKeepAliveNs);
                                        if (System.nanoTime() - this.terminationDeadline >= 0) {
                                            this.terminationDeadline = 0L;
                                            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
                                            synchronized (coroutineScheduler.workers) {
                                                if (!coroutineScheduler.isTerminated()) {
                                                    if (((int) (coroutineScheduler.controlState & 2097151)) > coroutineScheduler.corePoolSize) {
                                                        if (workerCtl$FU.compareAndSet(this, -1, 1)) {
                                                            int i = this.indexInArray;
                                                            setIndexInArray(0);
                                                            coroutineScheduler.parkedWorkersStackTopUpdate(this, i, 0);
                                                            int andDecrement = (int) (CoroutineScheduler.controlState$FU.getAndDecrement(coroutineScheduler) & 2097151);
                                                            if (andDecrement != i) {
                                                                Worker worker = coroutineScheduler.workers.get(andDecrement);
                                                                coroutineScheduler.workers.set(i, worker);
                                                                worker.setIndexInArray(i);
                                                                coroutineScheduler.parkedWorkersStackTopUpdate(worker, andDecrement, i);
                                                            }
                                                            coroutineScheduler.workers.set(andDecrement, null);
                                                            Unit unit = Unit.INSTANCE;
                                                            this.state = workerState2;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (!z) {
                            z = true;
                        } else {
                            tryReleaseCpu(workerState);
                            Thread.interrupted();
                            LockSupport.parkNanos(this.minDelayUntilStealableTaskNs);
                            this.minDelayUntilStealableTaskNs = 0L;
                        }
                    }
                }
            }
            tryReleaseCpu(workerState2);
        }

        public final void setIndexInArray(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append(CoroutineScheduler.this.schedulerName);
            sb.append("-worker-");
            sb.append(i == 0 ? "TERMINATED" : String.valueOf(i));
            setName(sb.toString());
            this.indexInArray = i;
        }

        public final void setNextParkedWorker(Object obj) {
            this.nextParkedWorker = obj;
        }

        public final boolean tryReleaseCpu(WorkerState workerState) {
            WorkerState workerState2 = this.state;
            boolean z = workerState2 == WorkerState.CPU_ACQUIRED;
            if (z) {
                CoroutineScheduler.controlState$FU.addAndGet(CoroutineScheduler.this, 4398046511104L);
            }
            if (workerState2 != workerState) {
                this.state = workerState;
            }
            return z;
        }

        public final Task trySteal(boolean z) {
            long j;
            int i = (int) (CoroutineScheduler.this.controlState & 2097151);
            if (i < 2) {
                return null;
            }
            int nextInt = nextInt(i);
            CoroutineScheduler coroutineScheduler = CoroutineScheduler.this;
            long j2 = Long.MAX_VALUE;
            for (int i2 = 0; i2 < i; i2++) {
                nextInt++;
                if (nextInt > i) {
                    nextInt = 1;
                }
                Worker worker = coroutineScheduler.workers.get(nextInt);
                if (!(worker == null || worker == this)) {
                    if (z) {
                        j = this.localQueue.tryStealBlockingFrom(worker.localQueue);
                    } else {
                        WorkQueue workQueue = this.localQueue;
                        WorkQueue workQueue2 = worker.localQueue;
                        Objects.requireNonNull(workQueue);
                        Task pollBuffer = workQueue2.pollBuffer();
                        if (pollBuffer != null) {
                            workQueue.add(pollBuffer, false);
                            j = -1;
                        } else {
                            j = workQueue.tryStealLastScheduled(workQueue2, false);
                        }
                    }
                    if (j == -1) {
                        return this.localQueue.poll();
                    }
                    if (j > 0) {
                        j2 = Math.min(j2, j);
                    }
                }
            }
            if (j2 == Long.MAX_VALUE) {
                j2 = 0;
            }
            this.minDelayUntilStealableTaskNs = j2;
            return null;
        }
    }

    /* compiled from: CoroutineScheduler.kt */
    /* loaded from: classes.dex */
    public enum WorkerState {
        CPU_ACQUIRED,
        BLOCKING,
        PARKING,
        DORMANT,
        TERMINATED
    }

    public CoroutineScheduler(int i, int i2, long j, String str) {
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        if (i >= 1) {
            if (i2 >= i) {
                if (i2 <= 2097150) {
                    if (j > 0) {
                        this.globalCpuQueue = new GlobalQueue();
                        this.globalBlockingQueue = new GlobalQueue();
                        this.parkedWorkersStack = 0L;
                        this.workers = new AtomicReferenceArray<>(i2 + 1);
                        this.controlState = i << 42;
                        this._isTerminated = 0;
                        return;
                    }
                    throw new IllegalArgumentException(("Idle worker keep alive time " + j + " must be positive").toString());
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline4("Max pool size ", i2, " should not exceed maximal supported number of threads 2097150").toString());
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("Max pool size ", i2, " should be greater than or equals to core pool size ", i).toString());
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline4("Core pool size ", i, " should be at least 1").toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0061 A[LOOP:0: B:12:0x001f->B:26:0x0061, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0063 A[EDGE_INSN: B:45:0x0063->B:27:0x0063 ?: BREAK  , SYNTHETIC] */
    @Override // java.io.Closeable, java.lang.AutoCloseable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void close() {
        /*
            r10 = this;
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = kotlinx.coroutines.scheduling.CoroutineScheduler._isTerminated$FU
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r10, r1, r2)
            if (r0 != 0) goto L_0x000c
            goto L_0x0099
        L_0x000c:
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r0 = r10.currentWorker()
            java.util.concurrent.atomic.AtomicReferenceArray<kotlinx.coroutines.scheduling.CoroutineScheduler$Worker> r3 = r10.workers
            monitor-enter(r3)
            long r4 = r10.controlState     // Catch: all -> 0x009e
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r4 = r4 & r6
            int r5 = (int) r4
            monitor-exit(r3)
            r4 = 0
            if (r2 > r5) goto L_0x0063
            r3 = 1
        L_0x001f:
            int r6 = r3 + 1
            java.util.concurrent.atomic.AtomicReferenceArray<kotlinx.coroutines.scheduling.CoroutineScheduler$Worker> r7 = r10.workers
            java.lang.Object r7 = r7.get(r3)
            kotlinx.coroutines.scheduling.CoroutineScheduler$Worker r7 = (kotlinx.coroutines.scheduling.CoroutineScheduler.Worker) r7
            if (r7 == r0) goto L_0x005e
        L_0x002b:
            boolean r8 = r7.isAlive()
            if (r8 == 0) goto L_0x003a
            java.util.concurrent.locks.LockSupport.unpark(r7)
            r8 = 10000(0x2710, double:4.9407E-320)
            r7.join(r8)
            goto L_0x002b
        L_0x003a:
            kotlinx.coroutines.scheduling.WorkQueue r7 = r7.localQueue
            kotlinx.coroutines.scheduling.GlobalQueue r8 = r10.globalBlockingQueue
            java.util.Objects.requireNonNull(r7)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r9 = kotlinx.coroutines.scheduling.WorkQueue.lastScheduledTask$FU
            java.lang.Object r9 = r9.getAndSet(r7, r4)
            kotlinx.coroutines.scheduling.Task r9 = (kotlinx.coroutines.scheduling.Task) r9
            if (r9 != 0) goto L_0x004c
            goto L_0x004f
        L_0x004c:
            r8.addLast(r9)
        L_0x004f:
            kotlinx.coroutines.scheduling.Task r9 = r7.pollBuffer()
            if (r9 != 0) goto L_0x0057
            r9 = 0
            goto L_0x005b
        L_0x0057:
            r8.addLast(r9)
            r9 = 1
        L_0x005b:
            if (r9 == 0) goto L_0x005e
            goto L_0x004f
        L_0x005e:
            if (r3 != r5) goto L_0x0061
            goto L_0x0063
        L_0x0061:
            r3 = r6
            goto L_0x001f
        L_0x0063:
            kotlinx.coroutines.scheduling.GlobalQueue r1 = r10.globalBlockingQueue
            r1.close()
            kotlinx.coroutines.scheduling.GlobalQueue r1 = r10.globalCpuQueue
            r1.close()
        L_0x006d:
            if (r0 != 0) goto L_0x0071
            r1 = r4
            goto L_0x0075
        L_0x0071:
            kotlinx.coroutines.scheduling.Task r1 = r0.findTask(r2)
        L_0x0075:
            if (r1 != 0) goto L_0x007f
            kotlinx.coroutines.scheduling.GlobalQueue r1 = r10.globalCpuQueue
            java.lang.Object r1 = r1.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r1 = (kotlinx.coroutines.scheduling.Task) r1
        L_0x007f:
            if (r1 != 0) goto L_0x009a
            kotlinx.coroutines.scheduling.GlobalQueue r1 = r10.globalBlockingQueue
            java.lang.Object r1 = r1.removeFirstOrNull()
            kotlinx.coroutines.scheduling.Task r1 = (kotlinx.coroutines.scheduling.Task) r1
            if (r1 != 0) goto L_0x009a
            if (r0 != 0) goto L_0x008e
            goto L_0x0093
        L_0x008e:
            kotlinx.coroutines.scheduling.CoroutineScheduler$WorkerState r1 = kotlinx.coroutines.scheduling.CoroutineScheduler.WorkerState.TERMINATED
            r0.tryReleaseCpu(r1)
        L_0x0093:
            r0 = 0
            r10.parkedWorkersStack = r0
            r10.controlState = r0
        L_0x0099:
            return
        L_0x009a:
            r10.runSafely(r1)
            goto L_0x006d
        L_0x009e:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.CoroutineScheduler.close():void");
    }

    public final int createNewWorker() {
        synchronized (this.workers) {
            if (this._isTerminated != 0) {
                return -1;
            }
            long j = this.controlState;
            int i = (int) (j & 2097151);
            int i2 = i - ((int) ((j & 4398044413952L) >> 21));
            boolean z = false;
            if (i2 < 0) {
                i2 = 0;
            }
            if (i2 >= this.corePoolSize) {
                return 0;
            }
            if (i >= this.maxPoolSize) {
                return 0;
            }
            int i3 = ((int) (this.controlState & 2097151)) + 1;
            if (i3 > 0 && this.workers.get(i3) == null) {
                Worker worker = new Worker(i3);
                this.workers.set(i3, worker);
                if (i3 == ((int) (2097151 & controlState$FU.incrementAndGet(this)))) {
                    z = true;
                }
                if (z) {
                    worker.start();
                    return i2 + 1;
                }
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public final Task createTask(Runnable runnable, TaskContext taskContext) {
        long nanoTime = TasksKt.schedulerTimeSource.nanoTime();
        if (!(runnable instanceof Task)) {
            return new TaskImpl(runnable, nanoTime, taskContext);
        }
        Task task = (Task) runnable;
        task.submissionTime = nanoTime;
        task.taskContext = taskContext;
        return task;
    }

    public final Worker currentWorker() {
        Thread currentThread = Thread.currentThread();
        Worker worker = currentThread instanceof Worker ? (Worker) currentThread : null;
        if (worker != null && Intrinsics.areEqual(CoroutineScheduler.this, this)) {
            return worker;
        }
        return null;
    }

    public final void dispatch(Runnable runnable, TaskContext taskContext, boolean z) {
        Task task;
        boolean z2;
        Task createTask = createTask(runnable, taskContext);
        Worker currentWorker = currentWorker();
        boolean z3 = true;
        if (currentWorker == null || currentWorker.state == WorkerState.TERMINATED || (createTask.taskContext.getTaskMode() == 0 && currentWorker.state == WorkerState.BLOCKING)) {
            task = createTask;
        } else {
            currentWorker.mayHaveLocalTasks = true;
            task = currentWorker.localQueue.add(createTask, z);
        }
        if (task != null) {
            if (task.taskContext.getTaskMode() == 1) {
                z2 = this.globalBlockingQueue.addLast(task);
            } else {
                z2 = this.globalCpuQueue.addLast(task);
            }
            if (!z2) {
                throw new RejectedExecutionException(Intrinsics.stringPlus(this.schedulerName, " was terminated"));
            }
        }
        if (!z || currentWorker == null) {
            z3 = false;
        }
        if (createTask.taskContext.getTaskMode() != 0) {
            long addAndGet = controlState$FU.addAndGet(this, 2097152L);
            if (!z3 && !tryUnpark() && !tryCreateWorker(addAndGet)) {
                tryUnpark();
            }
        } else if (!z3) {
            signalCpuWork();
        }
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        dispatch(runnable, NonBlockingContext.INSTANCE, false);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [int, boolean] */
    public final boolean isTerminated() {
        return this._isTerminated;
    }

    public final int parkedWorkersStackNextIndex(Worker worker) {
        Object nextParkedWorker = worker.getNextParkedWorker();
        while (nextParkedWorker != NOT_IN_STACK) {
            if (nextParkedWorker == null) {
                return 0;
            }
            Worker worker2 = (Worker) nextParkedWorker;
            int indexInArray = worker2.getIndexInArray();
            if (indexInArray != 0) {
                return indexInArray;
            }
            nextParkedWorker = worker2.getNextParkedWorker();
        }
        return -1;
    }

    public final boolean parkedWorkersStackPush(Worker worker) {
        long j;
        int indexInArray;
        if (worker.getNextParkedWorker() != NOT_IN_STACK) {
            return false;
        }
        do {
            j = this.parkedWorkersStack;
            indexInArray = worker.getIndexInArray();
            worker.setNextParkedWorker(this.workers.get((int) (2097151 & j)));
        } while (!parkedWorkersStack$FU.compareAndSet(this, j, ((2097152 + j) & (-2097152)) | indexInArray));
        return true;
    }

    public final void parkedWorkersStackTopUpdate(Worker worker, int i, int i2) {
        while (true) {
            long j = this.parkedWorkersStack;
            int i3 = (int) (2097151 & j);
            long j2 = (2097152 + j) & (-2097152);
            if (i3 == i) {
                i3 = i2 == 0 ? parkedWorkersStackNextIndex(worker) : i2;
            }
            if (i3 >= 0 && parkedWorkersStack$FU.compareAndSet(this, j, j2 | i3)) {
                return;
            }
        }
    }

    public final void runSafely(Task task) {
        try {
            task.run();
        } finally {
        }
    }

    public final void signalCpuWork() {
        if (!tryUnpark() && !tryCreateWorker(this.controlState)) {
            tryUnpark();
        }
    }

    public String toString() {
        int i;
        int i2;
        int i3;
        int i4;
        ArrayList arrayList = new ArrayList();
        int length = this.workers.length();
        int i5 = 0;
        if (1 < length) {
            i3 = 0;
            int i6 = 0;
            i2 = 0;
            i = 0;
            int i7 = 1;
            while (true) {
                int i8 = i7 + 1;
                Worker worker = this.workers.get(i7);
                if (worker != null) {
                    int size$kotlinx_coroutines_core = worker.localQueue.getSize$kotlinx_coroutines_core();
                    int ordinal = worker.state.ordinal();
                    if (ordinal == 0) {
                        i6++;
                        StringBuilder sb = new StringBuilder();
                        sb.append(size$kotlinx_coroutines_core);
                        sb.append('c');
                        arrayList.add(sb.toString());
                    } else if (ordinal == 1) {
                        i3++;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(size$kotlinx_coroutines_core);
                        sb2.append('b');
                        arrayList.add(sb2.toString());
                    } else if (ordinal == 2) {
                        i5++;
                    } else if (ordinal == 3) {
                        i2++;
                        if (size$kotlinx_coroutines_core > 0) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(size$kotlinx_coroutines_core);
                            sb3.append('d');
                            arrayList.add(sb3.toString());
                        }
                    } else if (ordinal == 4) {
                        i++;
                    }
                }
                if (i8 >= length) {
                    break;
                }
                i7 = i8;
            }
            i4 = i5;
            i5 = i6;
        } else {
            i4 = 0;
            i3 = 0;
            i2 = 0;
            i = 0;
        }
        long j = this.controlState;
        return this.schedulerName + '@' + InputKt.getHexAddress(this) + "[Pool Size {core = " + this.corePoolSize + ", max = " + this.maxPoolSize + "}, Worker States {CPU = " + i5 + ", blocking = " + i3 + ", parked = " + i4 + ", dormant = " + i2 + ", terminated = " + i + "}, running workers queues = " + arrayList + ", global CPU queue size = " + this.globalCpuQueue.getSize() + ", global blocking queue size = " + this.globalBlockingQueue.getSize() + ", Control State {created workers= " + ((int) (2097151 & j)) + ", blocking tasks = " + ((int) ((4398044413952L & j) >> 21)) + ", CPUs acquired = " + (this.corePoolSize - ((int) ((9223367638808264704L & j) >> 42))) + "}]";
    }

    public final boolean tryCreateWorker(long j) {
        int i = ((int) (2097151 & j)) - ((int) ((j & 4398044413952L) >> 21));
        if (i < 0) {
            i = 0;
        }
        if (i < this.corePoolSize) {
            int createNewWorker = createNewWorker();
            if (createNewWorker == 1 && this.corePoolSize > 1) {
                createNewWorker();
            }
            if (createNewWorker > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean tryUnpark() {
        while (true) {
            long j = this.parkedWorkersStack;
            Worker worker = this.workers.get((int) (2097151 & j));
            if (worker == null) {
                worker = null;
            } else {
                long j2 = (2097152 + j) & (-2097152);
                int parkedWorkersStackNextIndex = parkedWorkersStackNextIndex(worker);
                if (parkedWorkersStackNextIndex >= 0 && parkedWorkersStack$FU.compareAndSet(this, j, parkedWorkersStackNextIndex | j2)) {
                    worker.setNextParkedWorker(NOT_IN_STACK);
                }
            }
            if (worker == null) {
                return false;
            }
            if (Worker.workerCtl$FU.compareAndSet(worker, -1, 0)) {
                LockSupport.unpark(worker);
                return true;
            }
        }
    }
}
