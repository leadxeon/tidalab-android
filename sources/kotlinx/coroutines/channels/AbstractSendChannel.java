package kotlinx.coroutines.channels;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.RemoveOnCancel;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.UndeliveredElementException;
import okhttp3.HttpUrl;
/* compiled from: AbstractChannel.kt */
/* loaded from: classes.dex */
public abstract class AbstractSendChannel<E> implements SendChannel<E> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater onCloseHandler$FU = AtomicReferenceFieldUpdater.newUpdater(AbstractSendChannel.class, Object.class, "onCloseHandler");
    public final Function1<E, Unit> onUndeliveredElement;
    public final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
    private volatile /* synthetic */ Object onCloseHandler = null;

    /* compiled from: AbstractChannel.kt */
    /* loaded from: classes.dex */
    public static final class SendBuffered<E> extends Send {
        public final E element;

        public SendBuffered(E e) {
            this.element = e;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void completeResumeSend() {
        }

        @Override // kotlinx.coroutines.channels.Send
        public Object getPollResult() {
            return this.element;
        }

        @Override // kotlinx.coroutines.channels.Send
        public void resumeSendClosed(Closed<?> closed) {
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("SendBuffered@");
            outline13.append(InputKt.getHexAddress(this));
            outline13.append('(');
            outline13.append(this.element);
            outline13.append(')');
            return outline13.toString();
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

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractSendChannel(Function1<? super E, Unit> function1) {
        this.onUndeliveredElement = function1;
    }

    public static final void access$helpCloseAndResumeWithSendException(AbstractSendChannel abstractSendChannel, Continuation continuation, Object obj, Closed closed) {
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        abstractSendChannel.helpClose(closed);
        Throwable sendException = closed.getSendException();
        Function1<E, Unit> function1 = abstractSendChannel.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException$default = InputKt.callUndeliveredElementCatchingException$default(function1, obj, null, 2)) == null) {
            ((CancellableContinuationImpl) continuation).resumeWith(new Result.Failure(sendException));
            return;
        }
        InputKt.addSuppressed(callUndeliveredElementCatchingException$default, sendException);
        ((CancellableContinuationImpl) continuation).resumeWith(new Result.Failure(callUndeliveredElementCatchingException$default));
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable th) {
        boolean z;
        Object obj;
        Symbol symbol;
        Closed<?> closed = new Closed<>(th);
        LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
        while (true) {
            LockFreeLinkedListNode prevNode = lockFreeLinkedListNode.getPrevNode();
            if (!(prevNode instanceof Closed)) {
                if (prevNode.addNext(closed, lockFreeLinkedListNode)) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            closed = (Closed) this.queue.getPrevNode();
        }
        helpClose(closed);
        if (z && (obj = this.onCloseHandler) != null && obj != (symbol = AbstractChannelKt.HANDLER_INVOKED) && onCloseHandler$FU.compareAndSet(this, obj, symbol)) {
            TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 1);
            ((Function1) obj).invoke(th);
        }
        return z;
    }

    public Object enqueueSend(final Send send) {
        boolean z;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysFull()) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            do {
                prevNode = lockFreeLinkedListNode.getPrevNode();
                if (prevNode instanceof ReceiveOrClosed) {
                    return prevNode;
                }
            } while (!prevNode.addNext(send, lockFreeLinkedListNode));
            return null;
        }
        LockFreeLinkedListNode lockFreeLinkedListNode2 = this.queue;
        LockFreeLinkedListNode.CondAddOp abstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1 = new LockFreeLinkedListNode.CondAddOp(send) { // from class: kotlinx.coroutines.channels.AbstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode3) {
                if (this.isBufferFull()) {
                    return null;
                }
                return LockFreeLinkedListKt.CONDITION_FALSE;
            }
        };
        while (true) {
            LockFreeLinkedListNode prevNode2 = lockFreeLinkedListNode2.getPrevNode();
            if (!(prevNode2 instanceof ReceiveOrClosed)) {
                int tryCondAddNext = prevNode2.tryCondAddNext(send, lockFreeLinkedListNode2, abstractSendChannel$enqueueSend$$inlined$addLastIfPrevAndIf$1);
                z = true;
                if (tryCondAddNext != 1) {
                    if (tryCondAddNext == 2) {
                        z = false;
                        break;
                    }
                } else {
                    break;
                }
            } else {
                return prevNode2;
            }
        }
        if (!z) {
            return AbstractChannelKt.ENQUEUE_FAILED;
        }
        return null;
    }

    public String getBufferDebugString() {
        return HttpUrl.FRAGMENT_ENCODE_SET;
    }

    public final Closed<?> getClosedForSend() {
        LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
        Closed<?> closed = prevNode instanceof Closed ? (Closed) prevNode : null;
        if (closed == null) {
            return null;
        }
        helpClose(closed);
        return closed;
    }

    public final void helpClose(Closed<?> closed) {
        Object obj = null;
        while (true) {
            LockFreeLinkedListNode prevNode = closed.getPrevNode();
            Receive receive = prevNode instanceof Receive ? (Receive) prevNode : null;
            if (receive == null) {
                break;
            } else if (!receive.remove()) {
                receive.helpRemove();
            } else {
                obj = InputKt.m9plusFjFbRPM(obj, receive);
            }
        }
        if (obj != null) {
            if (!(obj instanceof ArrayList)) {
                ((Receive) obj).resumeReceiveClosed(closed);
                return;
            }
            ArrayList arrayList = (ArrayList) obj;
            int size = arrayList.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    ((Receive) arrayList.get(size)).resumeReceiveClosed(closed);
                    if (i >= 0) {
                        size = i;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public abstract boolean isBufferAlwaysFull();

    public abstract boolean isBufferFull();

    @Override // kotlinx.coroutines.channels.SendChannel
    public boolean offer(E e) {
        UndeliveredElementException callUndeliveredElementCatchingException$default;
        try {
            Object obj = mo14trySendJP2dKIU(e);
            if (!(obj instanceof ChannelResult.Failed)) {
                return true;
            }
            ChannelResult.Closed closed = obj instanceof ChannelResult.Closed ? (ChannelResult.Closed) obj : null;
            Throwable th = closed == null ? null : closed.cause;
            if (th == null) {
                return false;
            }
            String str = StackTraceRecoveryKt.baseContinuationImplClassName;
            throw th;
        } catch (Throwable th2) {
            Function1<E, Unit> function1 = this.onUndeliveredElement;
            if (function1 == null || (callUndeliveredElementCatchingException$default = InputKt.callUndeliveredElementCatchingException$default(function1, e, null, 2)) == null) {
                throw th2;
            }
            InputKt.addSuppressed(callUndeliveredElementCatchingException$default, th2);
            throw callUndeliveredElementCatchingException$default;
        }
    }

    public Object offerInternal(E e) {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed;
        do {
            takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
            if (takeFirstReceiveOrPeekClosed == null) {
                return AbstractChannelKt.OFFER_FAILED;
            }
        } while (takeFirstReceiveOrPeekClosed.tryResumeReceive(e, null) == null);
        takeFirstReceiveOrPeekClosed.completeResumeReceive(e);
        return takeFirstReceiveOrPeekClosed.getOfferResult();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    public final Object send(E e, Continuation<? super Unit> continuation) {
        Send send;
        if (offerInternal(e) == AbstractChannelKt.OFFER_SUCCESS) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl orCreateCancellableContinuation = InputKt.getOrCreateCancellableContinuation(InputKt.intercepted(continuation));
        while (true) {
            if (!(this.queue.getNextNode() instanceof ReceiveOrClosed) && isBufferFull()) {
                if (this.onUndeliveredElement == null) {
                    send = new SendElement(e, orCreateCancellableContinuation);
                } else {
                    send = new SendElementWithUndeliveredHandler(e, orCreateCancellableContinuation, this.onUndeliveredElement);
                }
                Object enqueueSend = enqueueSend(send);
                if (enqueueSend == null) {
                    orCreateCancellableContinuation.invokeOnCancellation(new RemoveOnCancel(send));
                    break;
                } else if (enqueueSend instanceof Closed) {
                    access$helpCloseAndResumeWithSendException(this, orCreateCancellableContinuation, e, (Closed) enqueueSend);
                    break;
                } else if (enqueueSend != AbstractChannelKt.ENQUEUE_FAILED && !(enqueueSend instanceof Receive)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("enqueueSend returned ", enqueueSend).toString());
                }
            }
            Object offerInternal = offerInternal(e);
            if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
                orCreateCancellableContinuation.resumeWith(Unit.INSTANCE);
                break;
            } else if (offerInternal != AbstractChannelKt.OFFER_FAILED) {
                if (offerInternal instanceof Closed) {
                    access$helpCloseAndResumeWithSendException(this, orCreateCancellableContinuation, e, (Closed) offerInternal);
                } else {
                    throw new IllegalStateException(Intrinsics.stringPlus("offerInternal returned ", offerInternal).toString());
                }
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (result != coroutineSingletons) {
            result = Unit.INSTANCE;
        }
        return result == coroutineSingletons ? result : Unit.INSTANCE;
    }

    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode removeOrNext;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            lockFreeLinkedListNode = (LockFreeLinkedListNode) lockFreeLinkedListHead.getNext();
            lockFreeLinkedListNode = null;
            if (lockFreeLinkedListNode != lockFreeLinkedListHead && (lockFreeLinkedListNode instanceof ReceiveOrClosed)) {
                if (((((ReceiveOrClosed) lockFreeLinkedListNode) instanceof Closed) && !lockFreeLinkedListNode.isRemoved()) || (removeOrNext = lockFreeLinkedListNode.removeOrNext()) == null) {
                    break;
                }
                removeOrNext.helpRemovePrev();
            }
        }
        return (ReceiveOrClosed) lockFreeLinkedListNode;
    }

    public final Send takeFirstSendOrPeekClosed() {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode removeOrNext;
        LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
        while (true) {
            lockFreeLinkedListNode = (LockFreeLinkedListNode) lockFreeLinkedListHead.getNext();
            lockFreeLinkedListNode = null;
            if (lockFreeLinkedListNode != lockFreeLinkedListHead && (lockFreeLinkedListNode instanceof Send)) {
                if (((((Send) lockFreeLinkedListNode) instanceof Closed) && !lockFreeLinkedListNode.isRemoved()) || (removeOrNext = lockFreeLinkedListNode.removeOrNext()) == null) {
                    break;
                }
                removeOrNext.helpRemovePrev();
            }
        }
        return (Send) lockFreeLinkedListNode;
    }

    public String toString() {
        String str;
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('@');
        sb.append(InputKt.getHexAddress(this));
        sb.append('{');
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        if (nextNode == this.queue) {
            str = "EmptyQueue";
        } else {
            if (nextNode instanceof Closed) {
                str2 = nextNode.toString();
            } else if (nextNode instanceof Receive) {
                str2 = "ReceiveQueued";
            } else {
                str2 = nextNode instanceof Send ? "SendQueued" : Intrinsics.stringPlus("UNEXPECTED:", nextNode);
            }
            LockFreeLinkedListNode prevNode = this.queue.getPrevNode();
            if (prevNode != nextNode) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(",queueSize=");
                LockFreeLinkedListHead lockFreeLinkedListHead = this.queue;
                int i = 0;
                for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) lockFreeLinkedListHead.getNext(); !Intrinsics.areEqual(lockFreeLinkedListNode, lockFreeLinkedListHead); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                    if (lockFreeLinkedListNode instanceof LockFreeLinkedListNode) {
                        i++;
                    }
                }
                sb2.append(i);
                str = sb2.toString();
                if (prevNode instanceof Closed) {
                    str = str + ",closedForSend=" + prevNode;
                }
            } else {
                str = str2;
            }
        }
        sb.append(str);
        sb.append('}');
        sb.append(getBufferDebugString());
        return sb.toString();
    }

    @Override // kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU  reason: not valid java name */
    public final Object mo14trySendJP2dKIU(E e) {
        ChannelResult.Closed closed;
        Object offerInternal = offerInternal(e);
        if (offerInternal == AbstractChannelKt.OFFER_SUCCESS) {
            return Unit.INSTANCE;
        }
        if (offerInternal == AbstractChannelKt.OFFER_FAILED) {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend == null) {
                return ChannelResult.failed;
            }
            helpClose(closedForSend);
            closed = new ChannelResult.Closed(closedForSend.getSendException());
        } else if (offerInternal instanceof Closed) {
            Closed<?> closed2 = (Closed) offerInternal;
            helpClose(closed2);
            closed = new ChannelResult.Closed(closed2.getSendException());
        } else {
            throw new IllegalStateException(Intrinsics.stringPlus("trySend returned ", offerInternal).toString());
        }
        return closed;
    }
}
