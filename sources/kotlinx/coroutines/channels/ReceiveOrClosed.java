package kotlinx.coroutines.channels;

import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
/* compiled from: AbstractChannel.kt */
/* loaded from: classes.dex */
public interface ReceiveOrClosed<E> {
    void completeResumeReceive(E e);

    Object getOfferResult();

    Symbol tryResumeReceive(E e, LockFreeLinkedListNode.PrepareOp prepareOp);
}
