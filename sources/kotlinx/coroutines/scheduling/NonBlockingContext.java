package kotlinx.coroutines.scheduling;
/* compiled from: Tasks.kt */
/* loaded from: classes.dex */
public final class NonBlockingContext implements TaskContext {
    public static final NonBlockingContext INSTANCE = new NonBlockingContext();

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public void afterTask() {
    }

    @Override // kotlinx.coroutines.scheduling.TaskContext
    public int getTaskMode() {
        return 0;
    }
}
