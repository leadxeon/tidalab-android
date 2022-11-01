package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import kotlin.Unit;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
/* compiled from: CancellableContinuation.kt */
/* loaded from: classes.dex */
public final class RemoveOnCancel extends BeforeResumeCancelHandler {
    public final LockFreeLinkedListNode node;

    public RemoveOnCancel(LockFreeLinkedListNode lockFreeLinkedListNode) {
        this.node = lockFreeLinkedListNode;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Throwable th) {
        this.node.remove();
        return Unit.INSTANCE;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("RemoveOnCancel[");
        outline13.append(this.node);
        outline13.append(']');
        return outline13.toString();
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        this.node.remove();
    }
}
