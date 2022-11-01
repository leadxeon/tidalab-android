package kotlinx.coroutines;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public class JobImpl extends JobSupport implements Job {
    public final boolean handlesException;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JobImpl(Job job) {
        super(true);
        boolean z = true;
        initParentJob(job);
        ChildHandle parentHandle$kotlinx_coroutines_core = getParentHandle$kotlinx_coroutines_core();
        ChildHandleNode childHandleNode = parentHandle$kotlinx_coroutines_core instanceof ChildHandleNode ? (ChildHandleNode) parentHandle$kotlinx_coroutines_core : null;
        if (childHandleNode != null) {
            JobSupport job2 = childHandleNode.getJob();
            while (!job2.getHandlesException$kotlinx_coroutines_core()) {
                ChildHandle parentHandle$kotlinx_coroutines_core2 = job2.getParentHandle$kotlinx_coroutines_core();
                ChildHandleNode childHandleNode2 = parentHandle$kotlinx_coroutines_core2 instanceof ChildHandleNode ? (ChildHandleNode) parentHandle$kotlinx_coroutines_core2 : null;
                if (childHandleNode2 != null) {
                    job2 = childHandleNode2.getJob();
                }
            }
            this.handlesException = z;
        }
        z = false;
        this.handlesException = z;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean getHandlesException$kotlinx_coroutines_core() {
        return this.handlesException;
    }

    @Override // kotlinx.coroutines.JobSupport
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }
}
