package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: Semaphore.kt */
/* loaded from: classes.dex */
public interface Semaphore {
    Object acquire(Continuation<? super Unit> continuation);

    void release();
}
