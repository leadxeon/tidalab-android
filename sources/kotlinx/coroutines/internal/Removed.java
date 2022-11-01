package kotlinx.coroutines.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
/* compiled from: LockFreeLinkedList.kt */
/* loaded from: classes.dex */
public final class Removed {
    public final LockFreeLinkedListNode ref;

    public Removed(LockFreeLinkedListNode lockFreeLinkedListNode) {
        this.ref = lockFreeLinkedListNode;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Removed[");
        outline13.append(this.ref);
        outline13.append(']');
        return outline13.toString();
    }
}
