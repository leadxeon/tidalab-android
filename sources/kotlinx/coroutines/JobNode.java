package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public abstract class JobNode extends CompletionHandlerBase implements DisposableHandle, Incomplete {
    public JobSupport job;

    @Override // kotlinx.coroutines.DisposableHandle
    public void dispose() {
        Object state$kotlinx_coroutines_core;
        JobSupport job = getJob();
        do {
            state$kotlinx_coroutines_core = job.getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof JobNode) {
                if (state$kotlinx_coroutines_core != this) {
                    return;
                }
            } else if ((state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).getList() != null) {
                remove();
                return;
            } else {
                return;
            }
        } while (!JobSupport._state$FU.compareAndSet(job, state$kotlinx_coroutines_core, JobSupportKt.EMPTY_ACTIVE));
    }

    public final JobSupport getJob() {
        JobSupport jobSupport = this.job;
        if (jobSupport != null) {
            return jobSupport;
        }
        Intrinsics.throwUninitializedPropertyAccessException("job");
        throw null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public NodeList getList() {
        return null;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return getClass().getSimpleName() + '@' + InputKt.getHexAddress(this) + "[job@" + InputKt.getHexAddress(getJob()) + ']';
    }
}
