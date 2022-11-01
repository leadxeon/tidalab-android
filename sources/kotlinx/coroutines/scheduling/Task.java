package kotlinx.coroutines.scheduling;
/* compiled from: Tasks.kt */
/* loaded from: classes.dex */
public abstract class Task implements Runnable {
    public long submissionTime;
    public TaskContext taskContext;

    public Task(long j, TaskContext taskContext) {
        this.submissionTime = j;
        this.taskContext = taskContext;
    }

    public Task() {
        NonBlockingContext nonBlockingContext = NonBlockingContext.INSTANCE;
        this.submissionTime = 0L;
        this.taskContext = nonBlockingContext;
    }
}
