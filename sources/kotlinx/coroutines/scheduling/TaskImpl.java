package kotlinx.coroutines.scheduling;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
/* compiled from: Tasks.kt */
/* loaded from: classes.dex */
public final class TaskImpl extends Task {
    public final Runnable block;

    public TaskImpl(Runnable runnable, long j, TaskContext taskContext) {
        super(j, taskContext);
        this.block = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.block.run();
        } finally {
            this.taskContext.afterTask();
        }
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Task[");
        outline13.append(InputKt.getClassSimpleName(this.block));
        outline13.append('@');
        outline13.append(InputKt.getHexAddress(this.block));
        outline13.append(", ");
        outline13.append(this.submissionTime);
        outline13.append(", ");
        outline13.append(this.taskContext);
        outline13.append(']');
        return outline13.toString();
    }
}
