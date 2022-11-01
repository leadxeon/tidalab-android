package kotlinx.coroutines;

import kotlinx.coroutines.internal.LockFreeLinkedListHead;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
    @Override // kotlinx.coroutines.Incomplete
    public NodeList getList() {
        return this;
    }

    @Override // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return super.toString();
    }
}
