package kotlinx.coroutines.channels;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.internal.UndeliveredElementException;
/* compiled from: AbstractChannel.kt */
/* loaded from: classes.dex */
public final class SendElementWithUndeliveredHandler<E> extends SendElement<E> {
    public final Function1<E, Unit> onUndeliveredElement;

    /* JADX WARN: Multi-variable type inference failed */
    public SendElementWithUndeliveredHandler(E e, CancellableContinuation<? super Unit> cancellableContinuation, Function1<? super E, Unit> function1) {
        super(e, cancellableContinuation);
        this.onUndeliveredElement = function1;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public boolean remove() {
        if (!super.remove()) {
            return false;
        }
        undeliveredElement();
        return true;
    }

    @Override // kotlinx.coroutines.channels.Send
    public void undeliveredElement() {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        E e = this.pollResult;
        CoroutineContext context = this.cont.getContext();
        UndeliveredElementException callUndeliveredElementCatchingException = InputKt.callUndeliveredElementCatchingException(function1, e, null);
        if (callUndeliveredElementCatchingException != null) {
            InputKt.handleCoroutineException(context, callUndeliveredElementCatchingException);
        }
    }
}
