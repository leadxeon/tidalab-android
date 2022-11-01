package kotlinx.coroutines.channels;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Result;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
/* compiled from: AbstractChannel.kt */
/* loaded from: classes.dex */
public class SendElement<E> extends Send {
    public final CancellableContinuation<Unit> cont;
    public final E pollResult;

    /* JADX WARN: Multi-variable type inference failed */
    public SendElement(E e, CancellableContinuation<? super Unit> cancellableContinuation) {
        this.pollResult = e;
        this.cont = cancellableContinuation;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
        this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
    }

    @Override // kotlinx.coroutines.channels.Send
    public E getPollResult() {
        return this.pollResult;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(Closed<?> closed) {
        this.cont.resumeWith(new Result.Failure(closed.getSendException()));
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        return getClass().getSimpleName() + '@' + InputKt.getHexAddress(this) + '(' + this.pollResult + ')';
    }

    @Override // kotlinx.coroutines.channels.Send
    public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp prepareOp) {
        if (this.cont.tryResume(Unit.INSTANCE, prepareOp == null ? null : prepareOp.desc) == null) {
            return null;
        }
        if (prepareOp != null) {
            prepareOp.desc.finishPrepare(prepareOp);
        }
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }
}
