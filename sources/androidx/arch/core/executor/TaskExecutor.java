package androidx.arch.core.executor;
/* loaded from: classes.dex */
public abstract class TaskExecutor {
    public abstract void executeOnDiskIO(Runnable runnable);

    public abstract boolean isMainThread();
}
