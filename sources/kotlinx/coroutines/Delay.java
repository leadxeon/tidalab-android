package kotlinx.coroutines;

import kotlin.Unit;
/* compiled from: Delay.kt */
/* loaded from: classes.dex */
public interface Delay {
    void scheduleResumeAfterDelay(long j, CancellableContinuation<? super Unit> cancellableContinuation);
}
