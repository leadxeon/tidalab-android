package kotlinx.coroutines.channels;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
/* compiled from: AbstractChannel.kt */
/* loaded from: classes.dex */
public final class Closed<E> extends Send implements ReceiveOrClosed<E> {
    public final Throwable closeCause;

    public Closed(Throwable th) {
        this.closeCause = th;
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public void completeResumeReceive(E e) {
    }

    @Override // kotlinx.coroutines.channels.Send
    public void completeResumeSend() {
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public Object getOfferResult() {
        return this;
    }

    @Override // kotlinx.coroutines.channels.Send
    public Object getPollResult() {
        return this;
    }

    public final Throwable getReceiveException() {
        Throwable th = this.closeCause;
        return th == null ? new ClosedReceiveChannelException("Channel was closed") : th;
    }

    public final Throwable getSendException() {
        Throwable th = this.closeCause;
        return th == null ? new ClosedSendChannelException("Channel was closed") : th;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void resumeSendClosed(Closed<?> closed) {
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Closed@");
        outline13.append(InputKt.getHexAddress(this));
        outline13.append('[');
        outline13.append(this.closeCause);
        outline13.append(']');
        return outline13.toString();
    }

    @Override // kotlinx.coroutines.channels.ReceiveOrClosed
    public Symbol tryResumeReceive(E e, LockFreeLinkedListNode.PrepareOp prepareOp) {
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    @Override // kotlinx.coroutines.channels.Send
    public Symbol tryResumeSend(LockFreeLinkedListNode.PrepareOp prepareOp) {
        Symbol symbol = CancellableContinuationImplKt.RESUME_TOKEN;
        if (prepareOp != null) {
            prepareOp.desc.finishPrepare(prepareOp);
        }
        return symbol;
    }
}
