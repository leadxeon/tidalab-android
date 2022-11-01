package kotlinx.coroutines.scheduling;

import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.DefaultExecutor;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
/* compiled from: Dispatcher.kt */
/* loaded from: classes.dex */
public final class LimitingDispatcher extends ExecutorCoroutineDispatcher implements TaskContext, Executor {
    public static final /* synthetic */ AtomicIntegerFieldUpdater inFlightTasks$FU = AtomicIntegerFieldUpdater.newUpdater(LimitingDispatcher.class, "inFlightTasks");
    public final ExperimentalCoroutineDispatcher dispatcher;
    public final String name;
    public final int parallelism;
    public final int taskMode;
    public final ConcurrentLinkedQueue<Runnable> queue = new ConcurrentLinkedQueue<>();
    private volatile /* synthetic */ int inFlightTasks = 0;

    public LimitingDispatcher(ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher, int i, String str, int i2) {
        this.dispatcher = experimentalCoroutineDispatcher;
        this.parallelism = i;
        this.name = str;
        this.taskMode = i2;
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
        Runnable poll = this.queue.poll();
        if (poll != null) {
            ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher = this.dispatcher;
            Objects.requireNonNull(experimentalCoroutineDispatcher);
            try {
                experimentalCoroutineDispatcher.coroutineScheduler.dispatch(poll, this, true);
            } catch (RejectedExecutionException unused) {
                DefaultExecutor.INSTANCE.enqueue(experimentalCoroutineDispatcher.coroutineScheduler.createTask(poll, this));
            }
        } else {
            inFlightTasks$FU.decrementAndGet(this);
            Runnable poll2 = this.queue.poll();
            if (poll2 != null) {
                dispatch(poll2, true);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        throw new IllegalStateException("Close cannot be invoked on LimitingBlockingDispatcher".toString());
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        dispatch(runnable, false);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        dispatch(runnable, false);
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return this.taskMode;
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public String toString() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        return super.toString() + "[dispatcher = " + this.dispatcher + ']';
    }

    public final void dispatch(Runnable runnable, boolean z) {
        do {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = inFlightTasks$FU;
            if (atomicIntegerFieldUpdater.incrementAndGet(this) <= this.parallelism) {
                ExperimentalCoroutineDispatcher experimentalCoroutineDispatcher = this.dispatcher;
                Objects.requireNonNull(experimentalCoroutineDispatcher);
                try {
                    experimentalCoroutineDispatcher.coroutineScheduler.dispatch(runnable, this, z);
                    return;
                } catch (RejectedExecutionException unused) {
                    DefaultExecutor.INSTANCE.enqueue(experimentalCoroutineDispatcher.coroutineScheduler.createTask(runnable, this));
                    return;
                }
            } else {
                this.queue.add(runnable);
                if (atomicIntegerFieldUpdater.decrementAndGet(this) < this.parallelism) {
                    runnable = this.queue.poll();
                } else {
                    return;
                }
            }
        } while (runnable != null);
    }
}
