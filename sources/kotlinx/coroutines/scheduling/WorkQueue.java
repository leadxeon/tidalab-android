package kotlinx.coroutines.scheduling;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* compiled from: WorkQueue.kt */
/* loaded from: classes.dex */
public final class WorkQueue {
    public static final /* synthetic */ AtomicReferenceFieldUpdater lastScheduledTask$FU = AtomicReferenceFieldUpdater.newUpdater(WorkQueue.class, Object.class, "lastScheduledTask");
    public static final /* synthetic */ AtomicIntegerFieldUpdater producerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "producerIndex");
    public static final /* synthetic */ AtomicIntegerFieldUpdater consumerIndex$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "consumerIndex");
    public static final /* synthetic */ AtomicIntegerFieldUpdater blockingTasksInBuffer$FU = AtomicIntegerFieldUpdater.newUpdater(WorkQueue.class, "blockingTasksInBuffer");
    public final AtomicReferenceArray<Task> buffer = new AtomicReferenceArray<>(128);
    private volatile /* synthetic */ Object lastScheduledTask = null;
    private volatile /* synthetic */ int producerIndex = 0;
    private volatile /* synthetic */ int consumerIndex = 0;
    private volatile /* synthetic */ int blockingTasksInBuffer = 0;

    public final Task add(Task task, boolean z) {
        if (z) {
            return addLast(task);
        }
        Task task2 = (Task) lastScheduledTask$FU.getAndSet(this, task);
        if (task2 == null) {
            return null;
        }
        return addLast(task2);
    }

    public final Task addLast(Task task) {
        boolean z = true;
        if (task.taskContext.getTaskMode() != 1) {
            z = false;
        }
        if (z) {
            blockingTasksInBuffer$FU.incrementAndGet(this);
        }
        if (getBufferSize$kotlinx_coroutines_core() == 127) {
            return task;
        }
        int i = this.producerIndex & 127;
        while (this.buffer.get(i) != null) {
            Thread.yield();
        }
        this.buffer.lazySet(i, task);
        producerIndex$FU.incrementAndGet(this);
        return null;
    }

    public final int getBufferSize$kotlinx_coroutines_core() {
        return this.producerIndex - this.consumerIndex;
    }

    public final int getSize$kotlinx_coroutines_core() {
        return this.lastScheduledTask != null ? getBufferSize$kotlinx_coroutines_core() + 1 : getBufferSize$kotlinx_coroutines_core();
    }

    public final Task poll() {
        Task task = (Task) lastScheduledTask$FU.getAndSet(this, null);
        return task == null ? pollBuffer() : task;
    }

    public final Task pollBuffer() {
        Task andSet;
        while (true) {
            int i = this.consumerIndex;
            if (i - this.producerIndex == 0) {
                return null;
            }
            int i2 = i & 127;
            if (consumerIndex$FU.compareAndSet(this, i, i + 1) && (andSet = this.buffer.getAndSet(i2, null)) != null) {
                boolean z = true;
                if (andSet.taskContext.getTaskMode() != 1) {
                    z = false;
                }
                if (z) {
                    blockingTasksInBuffer$FU.decrementAndGet(this);
                }
                return andSet;
            }
        }
    }

    public final long tryStealBlockingFrom(WorkQueue workQueue) {
        int i = workQueue.consumerIndex;
        int i2 = workQueue.producerIndex;
        AtomicReferenceArray<Task> atomicReferenceArray = workQueue.buffer;
        while (true) {
            boolean z = true;
            if (i == i2) {
                break;
            }
            int i3 = i & 127;
            if (workQueue.blockingTasksInBuffer == 0) {
                break;
            }
            Task task = atomicReferenceArray.get(i3);
            if (task != null) {
                if (task.taskContext.getTaskMode() != 1) {
                    z = false;
                }
                if (z && atomicReferenceArray.compareAndSet(i3, task, null)) {
                    blockingTasksInBuffer$FU.decrementAndGet(workQueue);
                    add(task, false);
                    return -1L;
                }
            }
            i++;
        }
        return tryStealLastScheduled(workQueue, true);
    }

    public final long tryStealLastScheduled(WorkQueue workQueue, boolean z) {
        Task task;
        do {
            task = (Task) workQueue.lastScheduledTask;
            if (task == null) {
                return -2L;
            }
            if (z) {
                boolean z2 = true;
                if (task.taskContext.getTaskMode() != 1) {
                    z2 = false;
                }
                if (!z2) {
                    return -2L;
                }
            }
            long nanoTime = TasksKt.schedulerTimeSource.nanoTime() - task.submissionTime;
            long j = TasksKt.WORK_STEALING_TIME_RESOLUTION_NS;
            if (nanoTime < j) {
                return j - nanoTime;
            }
        } while (!lastScheduledTask$FU.compareAndSet(workQueue, task, null));
        add(task, false);
        return -1L;
    }
}
