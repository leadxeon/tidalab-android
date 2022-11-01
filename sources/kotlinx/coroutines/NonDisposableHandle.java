package kotlinx.coroutines;
/* compiled from: Job.kt */
/* loaded from: classes.dex */
public final class NonDisposableHandle implements DisposableHandle, ChildHandle {
    public static final NonDisposableHandle INSTANCE = new NonDisposableHandle();

    @Override // kotlinx.coroutines.ChildHandle
    public boolean childCancelled(Throwable th) {
        return false;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
    }

    public String toString() {
        return "NonDisposableHandle";
    }
}
