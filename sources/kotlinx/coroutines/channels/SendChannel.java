package kotlinx.coroutines.channels;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
/* compiled from: Channel.kt */
/* loaded from: classes.dex */
public interface SendChannel<E> {
    boolean close(Throwable th);

    boolean offer(E e);

    Object send(E e, Continuation<? super Unit> continuation);

    /* renamed from: trySend-JP2dKIU */
    Object mo14trySendJP2dKIU(E e);
}
