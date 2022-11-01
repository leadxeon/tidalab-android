package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
/* compiled from: ConcurrentLinkedList.kt */
/* loaded from: classes.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");
    private volatile /* synthetic */ Object _next = null;
    private volatile /* synthetic */ Object _prev;

    public ConcurrentLinkedListNode(N n) {
        this._prev = n;
    }

    public final N getNext() {
        Object obj = this._next;
        if (obj == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (N) ((ConcurrentLinkedListNode) obj);
    }

    public abstract boolean getRemoved();

    public final boolean isTail() {
        return getNext() == null;
    }

    public final void remove() {
        while (true) {
            ConcurrentLinkedListNode concurrentLinkedListNode = (ConcurrentLinkedListNode) this._prev;
            while (concurrentLinkedListNode != null && concurrentLinkedListNode.getRemoved()) {
                concurrentLinkedListNode = (ConcurrentLinkedListNode) concurrentLinkedListNode._prev;
            }
            ConcurrentLinkedListNode next = getNext();
            while (next.getRemoved()) {
                next = next.getNext();
            }
            next._prev = concurrentLinkedListNode;
            if (concurrentLinkedListNode != null) {
                concurrentLinkedListNode._next = next;
            }
            if (!next.getRemoved() && (concurrentLinkedListNode == null || !concurrentLinkedListNode.getRemoved())) {
                return;
            }
        }
    }
}
