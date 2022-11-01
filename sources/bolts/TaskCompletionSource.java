package bolts;
/* loaded from: classes.dex */
public class TaskCompletionSource<TResult> {
    public final Task<TResult> task = new Task<>();

    public void setCancelled() {
        if (!this.task.trySetCancelled()) {
            throw new IllegalStateException("Cannot cancel a completed task.");
        }
    }

    public void setError(Exception exc) {
        boolean z;
        Task<TResult> task = this.task;
        synchronized (task.lock) {
            z = false;
            if (!task.complete) {
                task.complete = true;
                task.error = exc;
                task.errorHasBeenObserved = false;
                task.lock.notifyAll();
                task.runContinuations();
                z = true;
            }
        }
        if (!z) {
            throw new IllegalStateException("Cannot set the error on a completed task.");
        }
    }

    public void setResult(TResult tresult) {
        if (!this.task.trySetResult(tresult)) {
            throw new IllegalStateException("Cannot set the result of a completed task.");
        }
    }
}
