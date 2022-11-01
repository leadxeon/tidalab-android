package kotlinx.coroutines.sync;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: Mutex.kt */
/* loaded from: classes.dex */
public interface Mutex {
    Object lock(Object obj, Continuation<? super Unit> continuation);

    void unlock(Object obj);
}
