package kotlinx.coroutines.channels;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BeforeResumeCancelHandler;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.AbstractChannel;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.LockFreeLinkedList_commonKt;
import kotlinx.coroutines.internal.OnUndeliveredElementKt$bindCancellationFun$1;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectBuilderImpl;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;
/* compiled from: AbstractChannel.kt */
/* loaded from: classes.dex */
public abstract class AbstractChannel<E> extends AbstractSendChannel<E> implements Channel<E> {

    /* compiled from: AbstractChannel.kt */
    /* loaded from: classes.dex */
    public static class ReceiveElement<E> extends Receive<E> {
        public final CancellableContinuation<Object> cont;
        public final int receiveMode;

        public ReceiveElement(CancellableContinuation<Object> cancellableContinuation, int i) {
            this.cont = cancellableContinuation;
            this.receiveMode = i;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e) {
            this.cont.completeResume(CancellableContinuationImplKt.RESUME_TOKEN);
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(Closed<?> closed) {
            if (this.receiveMode == 1) {
                this.cont.resumeWith(new ChannelResult(new ChannelResult.Closed(closed.closeCause)));
            } else {
                this.cont.resumeWith(new Result.Failure(closed.getReceiveException()));
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("ReceiveElement@");
            outline13.append(InputKt.getHexAddress(this));
            outline13.append("[receiveMode=");
            outline13.append(this.receiveMode);
            outline13.append(']');
            return outline13.toString();
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public Symbol tryResumeReceive(E e, LockFreeLinkedListNode.PrepareOp prepareOp) {
            if (this.cont.tryResume(this.receiveMode == 1 ? new ChannelResult(e) : e, null, resumeOnCancellationFun(e)) == null) {
                return null;
            }
            return CancellableContinuationImplKt.RESUME_TOKEN;
        }
    }

    /* compiled from: AbstractChannel.kt */
    /* loaded from: classes.dex */
    public static final class ReceiveElementWithUndeliveredHandler<E> extends ReceiveElement<E> {
        public final Function1<E, Unit> onUndeliveredElement;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveElementWithUndeliveredHandler(CancellableContinuation<Object> cancellableContinuation, int i, Function1<? super E, Unit> function1) {
            super(cancellableContinuation, i);
            this.onUndeliveredElement = function1;
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
            return new OnUndeliveredElementKt$bindCancellationFun$1(this.onUndeliveredElement, e, this.cont.getContext());
        }
    }

    /* compiled from: AbstractChannel.kt */
    /* loaded from: classes.dex */
    public static final class ReceiveSelect<R, E> extends Receive<E> implements DisposableHandle {
        public final Function2<Object, Continuation<? super R>, Object> block;
        public final AbstractChannel<E> channel;
        public final int receiveMode;
        public final SelectInstance<R> select;

        /* JADX WARN: Multi-variable type inference failed */
        public ReceiveSelect(AbstractChannel<E> abstractChannel, SelectInstance<? super R> selectInstance, Function2<Object, ? super Continuation<? super R>, ? extends Object> function2, int i) {
            this.channel = abstractChannel;
            this.select = selectInstance;
            this.block = function2;
            this.receiveMode = i;
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public void completeResumeReceive(E e) {
            InputKt.startCoroutineCancellable(this.block, this.receiveMode == 1 ? new ChannelResult(e) : e, this.select.getCompletion(), resumeOnCancellationFun(e));
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            if (remove()) {
                Objects.requireNonNull(this.channel);
            }
        }

        @Override // kotlinx.coroutines.channels.Receive
        public Function1<Throwable, Unit> resumeOnCancellationFun(E e) {
            Function1<E, Unit> function1 = this.channel.onUndeliveredElement;
            if (function1 == null) {
                return null;
            }
            return new OnUndeliveredElementKt$bindCancellationFun$1(function1, e, this.select.getCompletion().getContext());
        }

        @Override // kotlinx.coroutines.channels.Receive
        public void resumeReceiveClosed(Closed<?> closed) {
            if (this.select.trySelect()) {
                int i = this.receiveMode;
                if (i == 0) {
                    this.select.resumeSelectWithException(closed.getReceiveException());
                } else if (i == 1) {
                    InputKt.startCoroutineCancellable$default(this.block, new ChannelResult(new ChannelResult.Closed(closed.closeCause)), this.select.getCompletion(), null, 4);
                }
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("ReceiveSelect@");
            outline13.append(InputKt.getHexAddress(this));
            outline13.append('[');
            outline13.append(this.select);
            outline13.append(",receiveMode=");
            outline13.append(this.receiveMode);
            outline13.append(']');
            return outline13.toString();
        }

        @Override // kotlinx.coroutines.channels.ReceiveOrClosed
        public Symbol tryResumeReceive(E e, LockFreeLinkedListNode.PrepareOp prepareOp) {
            return (Symbol) this.select.trySelectOther(null);
        }
    }

    /* compiled from: AbstractChannel.kt */
    /* loaded from: classes.dex */
    public static final class TryPollDesc<E> extends LockFreeLinkedListNode.RemoveFirstDesc<Send> {
        public TryPollDesc(LockFreeLinkedListHead lockFreeLinkedListHead) {
            super(lockFreeLinkedListHead);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.RemoveFirstDesc, kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object failure(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode instanceof Closed) {
                return lockFreeLinkedListNode;
            }
            if (!(lockFreeLinkedListNode instanceof Send)) {
                return AbstractChannelKt.POLL_FAILED;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object onPrepare(LockFreeLinkedListNode.PrepareOp prepareOp) {
            Symbol tryResumeSend = ((Send) prepareOp.affected).tryResumeSend(prepareOp);
            if (tryResumeSend == null) {
                return LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            }
            Object obj = AtomicKt.RETRY_ATOMIC;
            if (tryResumeSend == obj) {
                return obj;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void onRemoved(LockFreeLinkedListNode lockFreeLinkedListNode) {
            ((Send) lockFreeLinkedListNode).undeliveredElement();
        }
    }

    public AbstractChannel(Function1<? super E, Unit> function1) {
        super(function1);
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cancellationException) {
        if (!isClosedForReceive()) {
            if (cancellationException == null) {
                cancellationException = new CancellationException(Intrinsics.stringPlus(getClass().getSimpleName(), " was cancelled"));
            }
            onCancelIdempotent(close(cancellationException));
        }
    }

    public boolean enqueueReceiveInternal(final Receive<? super E> receive) {
        int tryCondAddNext;
        LockFreeLinkedListNode prevNode;
        if (isBufferAlwaysEmpty()) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            do {
                prevNode = lockFreeLinkedListNode.getPrevNode();
                if (!(!(prevNode instanceof Send))) {
                    return false;
                }
            } while (!prevNode.addNext(receive, lockFreeLinkedListNode));
        } else {
            LockFreeLinkedListNode lockFreeLinkedListNode2 = this.queue;
            LockFreeLinkedListNode.CondAddOp abstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1 = new LockFreeLinkedListNode.CondAddOp(receive) { // from class: kotlinx.coroutines.channels.AbstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1
                @Override // kotlinx.coroutines.internal.AtomicOp
                public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode3) {
                    if (this.isBufferEmpty()) {
                        return null;
                    }
                    return LockFreeLinkedListKt.CONDITION_FALSE;
                }
            };
            do {
                LockFreeLinkedListNode prevNode2 = lockFreeLinkedListNode2.getPrevNode();
                if (!(!(prevNode2 instanceof Send))) {
                    return false;
                }
                tryCondAddNext = prevNode2.tryCondAddNext(receive, lockFreeLinkedListNode2, abstractChannel$enqueueReceiveInternal$$inlined$addLastIfPrevAndIf$1);
                if (tryCondAddNext != 1) {
                }
            } while (tryCondAddNext != 2);
            return false;
        }
        return true;
    }

    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final SelectClause1<E> getOnReceive() {
        return new SelectClause1<E>(this) { // from class: kotlinx.coroutines.channels.AbstractChannel$onReceive$1
            public final /* synthetic */ AbstractChannel<E> this$0;

            {
                this.this$0 = this;
            }

            @Override // kotlinx.coroutines.selects.SelectClause1
            public <R> void registerSelectClause1(SelectInstance<? super R> selectInstance, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
                AbstractChannel<E> abstractChannel = this.this$0;
                Objects.requireNonNull(abstractChannel);
                while (true) {
                    SelectBuilderImpl selectBuilderImpl = (SelectBuilderImpl) selectInstance;
                    if (!selectBuilderImpl.isSelected()) {
                        if (!(abstractChannel.queue.getNextNode() instanceof Send) && abstractChannel.isBufferEmpty()) {
                            AbstractChannel.ReceiveSelect receiveSelect = new AbstractChannel.ReceiveSelect(abstractChannel, selectInstance, function2, 0);
                            boolean enqueueReceiveInternal = abstractChannel.enqueueReceiveInternal(receiveSelect);
                            if (enqueueReceiveInternal) {
                                selectBuilderImpl.disposeOnSelect(receiveSelect);
                            }
                            if (enqueueReceiveInternal) {
                                return;
                            }
                        } else {
                            Object pollSelectInternal = abstractChannel.pollSelectInternal(selectInstance);
                            Object obj = SelectKt.NOT_SELECTED;
                            if (pollSelectInternal != SelectKt.ALREADY_SELECTED) {
                                if (!(pollSelectInternal == AbstractChannelKt.POLL_FAILED || pollSelectInternal == AtomicKt.RETRY_ATOMIC)) {
                                    if (!(pollSelectInternal instanceof Closed)) {
                                        InputKt.startCoroutineUnintercepted(function2, pollSelectInternal, selectBuilderImpl);
                                    } else {
                                        Throwable receiveException = ((Closed) pollSelectInternal).getReceiveException();
                                        String str = StackTraceRecoveryKt.baseContinuationImplClassName;
                                        throw receiveException;
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        };
    }

    public abstract boolean isBufferAlwaysEmpty();

    public abstract boolean isBufferEmpty();

    public boolean isClosedForReceive() {
        LockFreeLinkedListNode nextNode = this.queue.getNextNode();
        Closed<?> closed = null;
        Closed<?> closed2 = nextNode instanceof Closed ? (Closed) nextNode : null;
        if (closed2 != null) {
            helpClose(closed2);
            closed = closed2;
        }
        return closed != null && isBufferEmpty();
    }

    public void onCancelIdempotent(boolean z) {
        Closed<?> closedForSend = getClosedForSend();
        if (closedForSend != null) {
            Object obj = null;
            while (true) {
                LockFreeLinkedListNode prevNode = closedForSend.getPrevNode();
                if (prevNode instanceof LockFreeLinkedListHead) {
                    mo13onCancelIdempotentListww6eGU(obj, closedForSend);
                    return;
                } else if (!prevNode.remove()) {
                    prevNode.helpRemove();
                } else {
                    obj = InputKt.m9plusFjFbRPM(obj, (Send) prevNode);
                }
            }
        } else {
            throw new IllegalStateException("Cannot happen".toString());
        }
    }

    /* renamed from: onCancelIdempotentList-w-w6eGU  reason: not valid java name */
    public void mo13onCancelIdempotentListww6eGU(Object obj, Closed<?> closed) {
        if (obj != null) {
            if (!(obj instanceof ArrayList)) {
                ((Send) obj).resumeSendClosed(closed);
                return;
            }
            ArrayList arrayList = (ArrayList) obj;
            int size = arrayList.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    ((Send) arrayList.get(size)).resumeSendClosed(closed);
                    if (i >= 0) {
                        size = i;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public Object pollInternal() {
        while (true) {
            Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
            if (takeFirstSendOrPeekClosed == null) {
                return AbstractChannelKt.POLL_FAILED;
            }
            if (takeFirstSendOrPeekClosed.tryResumeSend(null) != null) {
                takeFirstSendOrPeekClosed.completeResumeSend();
                return takeFirstSendOrPeekClosed.getPollResult();
            }
            takeFirstSendOrPeekClosed.undeliveredElement();
        }
    }

    public Object pollSelectInternal(SelectInstance<?> selectInstance) {
        TryPollDesc tryPollDesc = new TryPollDesc(this.queue);
        Object performAtomicTrySelect = selectInstance.performAtomicTrySelect(tryPollDesc);
        if (performAtomicTrySelect != null) {
            return performAtomicTrySelect;
        }
        tryPollDesc.getResult().completeResumeSend();
        return tryPollDesc.getResult().getPollResult();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [kotlinx.coroutines.channels.AbstractChannel$ReceiveElement] */
    /* JADX WARN: Type inference failed for: r4v0, types: [kotlinx.coroutines.channels.AbstractSendChannel, kotlinx.coroutines.channels.AbstractChannel<E>, kotlinx.coroutines.channels.AbstractChannel] */
    @Override // kotlinx.coroutines.channels.ReceiveChannel
    public final Object receive(Continuation<? super E> continuation) {
        ReceiveElementWithUndeliveredHandler receiveElementWithUndeliveredHandler;
        Object pollInternal = pollInternal();
        if (pollInternal != AbstractChannelKt.POLL_FAILED && !(pollInternal instanceof Closed)) {
            return pollInternal;
        }
        CancellableContinuationImpl orCreateCancellableContinuation = InputKt.getOrCreateCancellableContinuation(InputKt.intercepted(continuation));
        if (this.onUndeliveredElement == null) {
            receiveElementWithUndeliveredHandler = new ReceiveElement(orCreateCancellableContinuation, 0);
        } else {
            receiveElementWithUndeliveredHandler = new ReceiveElementWithUndeliveredHandler(orCreateCancellableContinuation, 0, this.onUndeliveredElement);
        }
        while (true) {
            if (enqueueReceiveInternal(receiveElementWithUndeliveredHandler)) {
                orCreateCancellableContinuation.invokeOnCancellation(new RemoveReceiveOnCancel(receiveElementWithUndeliveredHandler));
                break;
            }
            Object pollInternal2 = pollInternal();
            if (pollInternal2 instanceof Closed) {
                receiveElementWithUndeliveredHandler.resumeReceiveClosed((Closed) pollInternal2);
                break;
            } else if (pollInternal2 != AbstractChannelKt.POLL_FAILED) {
                orCreateCancellableContinuation.resumeImpl(receiveElementWithUndeliveredHandler.receiveMode == 1 ? new ChannelResult(pollInternal2) : pollInternal2, orCreateCancellableContinuation.resumeMode, receiveElementWithUndeliveredHandler.resumeOnCancellationFun(pollInternal2));
            }
        }
        return orCreateCancellableContinuation.getResult();
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed() {
        ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed = super.takeFirstReceiveOrPeekClosed();
        if (takeFirstReceiveOrPeekClosed != null) {
            boolean z = takeFirstReceiveOrPeekClosed instanceof Closed;
        }
        return takeFirstReceiveOrPeekClosed;
    }

    /* compiled from: AbstractChannel.kt */
    /* loaded from: classes.dex */
    public final class RemoveReceiveOnCancel extends BeforeResumeCancelHandler {
        public final Receive<?> receive;

        public RemoveReceiveOnCancel(Receive<?> receive) {
            this.receive = receive;
        }

        @Override // kotlin.jvm.functions.Function1
        public Unit invoke(Throwable th) {
            if (this.receive.remove()) {
                Objects.requireNonNull(AbstractChannel.this);
            }
            return Unit.INSTANCE;
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("RemoveReceiveOnCancel[");
            outline13.append(this.receive);
            outline13.append(']');
            return outline13.toString();
        }

        @Override // kotlinx.coroutines.CancelHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(Throwable th) {
            if (this.receive.remove()) {
                Objects.requireNonNull(AbstractChannel.this);
            }
        }
    }
}
