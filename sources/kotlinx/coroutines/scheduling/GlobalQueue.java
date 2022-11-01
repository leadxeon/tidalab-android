package kotlinx.coroutines.scheduling;

import kotlinx.coroutines.internal.LockFreeTaskQueue;
/* compiled from: Tasks.kt */
/* loaded from: classes.dex */
public final class GlobalQueue extends LockFreeTaskQueue<Task> {
    public GlobalQueue() {
        super(false);
    }
}
