package kotlinx.coroutines;
/* compiled from: Supervisor.kt */
/* loaded from: classes.dex */
public final class SupervisorJobImpl extends JobImpl {
    public SupervisorJobImpl(Job job) {
        super(job);
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean childCancelled(Throwable th) {
        return false;
    }
}
