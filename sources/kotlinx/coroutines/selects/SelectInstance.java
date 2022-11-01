package kotlinx.coroutines.selects;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
/* compiled from: Select.kt */
/* loaded from: classes.dex */
public interface SelectInstance<R> {
    Continuation<R> getCompletion();

    Object performAtomicTrySelect(AtomicDesc atomicDesc);

    void resumeSelectWithException(Throwable th);

    boolean trySelect();

    Object trySelectOther(LockFreeLinkedListNode.PrepareOp prepareOp);
}
