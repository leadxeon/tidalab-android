package bolts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
/* loaded from: classes.dex */
public class Task<TResult> {
    public static final Executor IMMEDIATE_EXECUTOR;
    public boolean cancelled;
    public boolean complete;
    public Exception error;
    public boolean errorHasBeenObserved;
    public TResult result;
    public static Task<?> TASK_NULL = new Task<>((Object) null);
    public static Task<Boolean> TASK_TRUE = new Task<>(Boolean.TRUE);
    public static Task<Boolean> TASK_FALSE = new Task<>(Boolean.FALSE);
    public final Object lock = new Object();
    public List<Continuation<TResult, Void>> continuations = new ArrayList();

    /* renamed from: bolts.Task$14  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass14 implements Runnable {
        public final /* synthetic */ Continuation val$continuation;
        public final /* synthetic */ Task val$task;
        public final /* synthetic */ TaskCompletionSource val$tcs;

        public AnonymousClass14(TaskCompletionSource taskCompletionSource, Continuation continuation, Task task) {
            this.val$tcs = taskCompletionSource;
            this.val$continuation = continuation;
            this.val$task = task;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            try {
                this.val$tcs.setResult(this.val$continuation.then(this.val$task));
            } catch (CancellationException unused) {
                this.val$tcs.setCancelled();
            } catch (Exception e) {
                this.val$tcs.setError(e);
            }
        }
    }

    static {
        BoltsExecutors boltsExecutors = BoltsExecutors.INSTANCE;
        ExecutorService executorService = boltsExecutors.background;
        IMMEDIATE_EXECUTOR = boltsExecutors.immediate;
        Executor executor = AndroidExecutors.INSTANCE.uiThread;
        new Task(true);
    }

    public Task() {
    }

    public static <TResult> Task<TResult> call(final Callable<TResult> callable, Executor executor) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        try {
            executor.execute(new Runnable() { // from class: bolts.Task.4
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        TaskCompletionSource.this.setResult(callable.call());
                    } catch (CancellationException unused) {
                        TaskCompletionSource.this.setCancelled();
                    } catch (Exception e) {
                        TaskCompletionSource.this.setError(e);
                    }
                }
            });
        } catch (Exception e) {
            taskCompletionSource.setError(new ExecutorException(e));
        }
        return taskCompletionSource.task;
    }

    public static <TResult> Task<TResult> forError(Exception exc) {
        boolean z;
        Task<TResult> task = new Task<>();
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
        if (z) {
            return task;
        }
        throw new IllegalStateException("Cannot set the error on a completed task.");
    }

    public <TContinuationResult> Task<TContinuationResult> continueWith(final Continuation<TResult, TContinuationResult> continuation) {
        boolean z;
        final Executor executor = IMMEDIATE_EXECUTOR;
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.lock) {
            synchronized (this.lock) {
                z = this.complete;
            }
            if (!z) {
                this.continuations.add(new Continuation<TResult, Void>(this) { // from class: bolts.Task.10
                    @Override // bolts.Continuation
                    public Void then(Task task) throws Exception {
                        TaskCompletionSource taskCompletionSource2 = taskCompletionSource;
                        Continuation continuation2 = continuation;
                        try {
                            executor.execute(new AnonymousClass14(taskCompletionSource2, continuation2, task));
                            return null;
                        } catch (Exception e) {
                            taskCompletionSource2.setError(new ExecutorException(e));
                            return null;
                        }
                    }
                });
            }
        }
        if (z) {
            try {
                executor.execute(new AnonymousClass14(taskCompletionSource, continuation, this));
            } catch (Exception e) {
                taskCompletionSource.setError(new ExecutorException(e));
            }
        }
        return (Task<TResult>) taskCompletionSource.task;
    }

    public Exception getError() {
        Exception exc;
        synchronized (this.lock) {
            exc = this.error;
            if (exc != null) {
                this.errorHasBeenObserved = true;
            }
        }
        return exc;
    }

    public boolean isFaulted() {
        boolean z;
        synchronized (this.lock) {
            z = getError() != null;
        }
        return z;
    }

    public final void runContinuations() {
        synchronized (this.lock) {
            for (Continuation<TResult, Void> continuation : this.continuations) {
                try {
                    continuation.then(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.continuations = null;
        }
    }

    public boolean trySetCancelled() {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.cancelled = true;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    public boolean trySetResult(TResult tresult) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = tresult;
            this.lock.notifyAll();
            runContinuations();
            return true;
        }
    }

    public Task(TResult tresult) {
        trySetResult(tresult);
    }

    public Task(boolean z) {
        if (z) {
            trySetCancelled();
        } else {
            trySetResult(null);
        }
    }
}
