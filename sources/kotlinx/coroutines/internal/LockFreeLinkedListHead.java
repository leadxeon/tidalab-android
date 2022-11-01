package kotlinx.coroutines.internal;
/* compiled from: LockFreeLinkedList.kt */
/* loaded from: classes.dex */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean isRemoved() {
        return false;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final boolean remove() {
        throw new IllegalStateException("head cannot be removed".toString());
    }
}
