package kotlinx.coroutines.selects;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellingNode;
import kotlinx.coroutines.internal.AtomicDesc;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
/* compiled from: Select.kt */
/* loaded from: classes.dex */
public final class SelectBuilderImpl<R> extends LockFreeLinkedListHead implements SelectInstance<R>, Continuation<R>, CoroutineStackFrame {
    public final Continuation<R> uCont;
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _result$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");
    public volatile /* synthetic */ Object _state = SelectKt.NOT_SELECTED;
    public volatile /* synthetic */ Object _result = SelectKt.UNDECIDED;
    private volatile /* synthetic */ Object _parentHandle = null;

    /* compiled from: Select.kt */
    /* loaded from: classes.dex */
    public static final class AtomicSelectOp extends AtomicOp<Object> {
        public final AtomicDesc desc;
        public final SelectBuilderImpl<?> impl;
        public final long opSequence;

        public AtomicSelectOp(SelectBuilderImpl<?> selectBuilderImpl, AtomicDesc atomicDesc) {
            this.impl = selectBuilderImpl;
            this.desc = atomicDesc;
            SeqNumber seqNumber = SelectKt.selectOpSequenceNumber;
            Objects.requireNonNull(seqNumber);
            this.opSequence = SeqNumber.number$FU.incrementAndGet(seqNumber);
            atomicDesc.atomicOp = this;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(Object obj, Object obj2) {
            Object obj3;
            boolean z = obj2 == null;
            if (z) {
                obj3 = null;
            } else {
                Object obj4 = SelectKt.NOT_SELECTED;
                obj3 = SelectKt.NOT_SELECTED;
            }
            if (SelectBuilderImpl._state$FU.compareAndSet(this.impl, this, obj3) && z) {
                this.impl.doAfterSelect();
            }
            this.desc.complete(this, obj2);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public long getOpSequence() {
            return this.opSequence;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public Object prepare(Object obj) {
            Object obj2;
            if (obj == null) {
                SelectBuilderImpl<?> selectBuilderImpl = this.impl;
                while (true) {
                    Object obj3 = selectBuilderImpl._state;
                    obj2 = null;
                    if (obj3 != this) {
                        if (!(obj3 instanceof OpDescriptor)) {
                            Object obj4 = SelectKt.NOT_SELECTED;
                            Object obj5 = SelectKt.NOT_SELECTED;
                            if (obj3 != obj5) {
                                obj2 = SelectKt.ALREADY_SELECTED;
                                break;
                            }
                            if (SelectBuilderImpl._state$FU.compareAndSet(this.impl, obj5, this)) {
                                break;
                            }
                        } else {
                            ((OpDescriptor) obj3).perform(this.impl);
                        }
                    } else {
                        break;
                    }
                }
                if (obj2 != null) {
                    return obj2;
                }
            }
            try {
                return this.desc.prepare(this);
            } catch (Throwable th) {
                if (obj == null) {
                    SelectBuilderImpl<?> selectBuilderImpl2 = this.impl;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = SelectBuilderImpl._state$FU;
                    Object obj6 = SelectKt.NOT_SELECTED;
                    atomicReferenceFieldUpdater.compareAndSet(selectBuilderImpl2, this, SelectKt.NOT_SELECTED);
                }
                throw th;
            }
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("AtomicSelectOp(sequence=");
            outline13.append(this.opSequence);
            outline13.append(')');
            return outline13.toString();
        }
    }

    /* compiled from: Select.kt */
    /* loaded from: classes.dex */
    public static final class DisposeNode extends LockFreeLinkedListNode {
        public final DisposableHandle handle;

        public DisposeNode(DisposableHandle disposableHandle) {
            this.handle = disposableHandle;
        }
    }

    /* compiled from: Select.kt */
    /* loaded from: classes.dex */
    public static final class PairSelectOp extends OpDescriptor {
        public final LockFreeLinkedListNode.PrepareOp otherOp;

        public PairSelectOp(LockFreeLinkedListNode.PrepareOp prepareOp) {
            this.otherOp = prepareOp;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public AtomicOp<?> getAtomicOp() {
            return this.otherOp.getAtomicOp();
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public Object perform(Object obj) {
            Object obj2;
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectBuilderImpl<*>");
            SelectBuilderImpl selectBuilderImpl = (SelectBuilderImpl) obj;
            LockFreeLinkedListNode.PrepareOp prepareOp = this.otherOp;
            prepareOp.desc.finishPrepare(prepareOp);
            Object decide = this.otherOp.getAtomicOp().decide(null);
            if (decide == null) {
                obj2 = this.otherOp.desc;
            } else {
                Object obj3 = SelectKt.NOT_SELECTED;
                obj2 = SelectKt.NOT_SELECTED;
            }
            SelectBuilderImpl._state$FU.compareAndSet(selectBuilderImpl, this, obj2);
            return decide;
        }
    }

    /* compiled from: Select.kt */
    /* loaded from: classes.dex */
    public final class SelectOnCancelling extends JobCancellingNode {
        public SelectOnCancelling() {
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(Throwable th) {
            if (SelectBuilderImpl.this.trySelect()) {
                SelectBuilderImpl.this.resumeSelectWithException(getJob().getCancellationException());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SelectBuilderImpl(Continuation<? super R> continuation) {
        this.uCont = continuation;
        Object obj = SelectKt.NOT_SELECTED;
    }

    public void disposeOnSelect(DisposableHandle disposableHandle) {
        DisposeNode disposeNode = new DisposeNode(disposableHandle);
        if (!isSelected()) {
            do {
            } while (!getPrevNode().addNext(disposeNode, this));
            if (!isSelected()) {
                return;
            }
        }
        disposableHandle.dispose();
    }

    public final void doAfterSelect() {
        DisposableHandle disposableHandle = (DisposableHandle) this._parentHandle;
        if (disposableHandle != null) {
            disposableHandle.dispose();
        }
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) getNext(); !Intrinsics.areEqual(lockFreeLinkedListNode, this); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof DisposeNode) {
                ((DisposeNode) lockFreeLinkedListNode).handle.dispose();
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation<R> continuation = this.uCont;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public Continuation<R> getCompletion() {
        return this;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.uCont.getContext();
    }

    public final Object getResult() {
        if (!isSelected()) {
            CoroutineContext context = getContext();
            int i = Job.$r8$clinit;
            Job job = (Job) context.get(Job.Key.$$INSTANCE);
            if (job != null) {
                DisposableHandle invokeOnCompletion$default = InputKt.invokeOnCompletion$default(job, true, false, new SelectOnCancelling(), 2, null);
                this._parentHandle = invokeOnCompletion$default;
                if (isSelected()) {
                    invokeOnCompletion$default.dispose();
                }
            }
        }
        Object obj = this._result;
        Object obj2 = SelectKt.NOT_SELECTED;
        Object obj3 = SelectKt.UNDECIDED;
        if (obj == obj3) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _result$FU;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (atomicReferenceFieldUpdater.compareAndSet(this, obj3, coroutineSingletons)) {
                return coroutineSingletons;
            }
            obj = this._result;
        }
        if (obj == SelectKt.RESUMED) {
            throw new IllegalStateException("Already resumed");
        } else if (!(obj instanceof CompletedExceptionally)) {
            return obj;
        } else {
            throw ((CompletedExceptionally) obj).cause;
        }
    }

    public final void handleBuilderException(Throwable th) {
        if (trySelect()) {
            resumeWith(new Result.Failure(th));
        } else if (!(th instanceof CancellationException)) {
            Object result = getResult();
            if (!(result instanceof CompletedExceptionally) || ((CompletedExceptionally) result).cause != th) {
                InputKt.handleCoroutineException(getContext(), th);
            }
        }
    }

    public boolean isSelected() {
        while (true) {
            Object obj = this._state;
            Object obj2 = SelectKt.NOT_SELECTED;
            if (obj == SelectKt.NOT_SELECTED) {
                return false;
            }
            if (!(obj instanceof OpDescriptor)) {
                return true;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public Object performAtomicTrySelect(AtomicDesc atomicDesc) {
        return new AtomicSelectOp(this, atomicDesc).perform(null);
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public void resumeSelectWithException(Throwable th) {
        while (true) {
            Object obj = this._result;
            Object obj2 = SelectKt.NOT_SELECTED;
            Object obj3 = SelectKt.UNDECIDED;
            if (obj == obj3) {
                if (_result$FU.compareAndSet(this, obj3, new CompletedExceptionally(th, false, 2))) {
                    return;
                }
            } else {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (obj != coroutineSingletons) {
                    throw new IllegalStateException("Already resumed");
                } else if (_result$FU.compareAndSet(this, coroutineSingletons, SelectKt.RESUMED)) {
                    InputKt.intercepted(this.uCont).resumeWith(new Result.Failure(th));
                    return;
                }
            }
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object obj) {
        while (true) {
            Object obj2 = this._result;
            Object obj3 = SelectKt.NOT_SELECTED;
            Object obj4 = SelectKt.UNDECIDED;
            if (obj2 == obj4) {
                if (_result$FU.compareAndSet(this, obj4, InputKt.toState$default(obj, null, 1))) {
                    return;
                }
            } else {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (obj2 != coroutineSingletons) {
                    throw new IllegalStateException("Already resumed");
                } else if (_result$FU.compareAndSet(this, coroutineSingletons, SelectKt.RESUMED)) {
                    if (obj instanceof Result.Failure) {
                        this.uCont.resumeWith(new Result.Failure(Result.m11exceptionOrNullimpl(obj)));
                        return;
                    } else {
                        this.uCont.resumeWith(obj);
                        return;
                    }
                }
            }
        }
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SelectInstance(state=");
        outline13.append(this._state);
        outline13.append(", result=");
        outline13.append(this._result);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // kotlinx.coroutines.selects.SelectInstance
    public boolean trySelect() {
        Object trySelectOther = trySelectOther(null);
        if (trySelectOther == CancellableContinuationImplKt.RESUME_TOKEN) {
            return true;
        }
        if (trySelectOther == null) {
            return false;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Unexpected trySelectIdempotent result ", trySelectOther).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0029, code lost:
        doAfterSelect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        return kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN;
     */
    @Override // kotlinx.coroutines.selects.SelectInstance
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object trySelectOther(kotlinx.coroutines.internal.LockFreeLinkedListNode.PrepareOp r4) {
        /*
            r3 = this;
        L_0x0000:
            java.lang.Object r0 = r3._state
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.NOT_SELECTED
            java.lang.Object r1 = kotlinx.coroutines.selects.SelectKt.NOT_SELECTED
            r2 = 0
            if (r0 != r1) goto L_0x002f
            if (r4 != 0) goto L_0x0014
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = kotlinx.coroutines.selects.SelectBuilderImpl._state$FU
            boolean r0 = r0.compareAndSet(r3, r1, r2)
            if (r0 != 0) goto L_0x0029
            goto L_0x0000
        L_0x0014:
            kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp r0 = new kotlinx.coroutines.selects.SelectBuilderImpl$PairSelectOp
            r0.<init>(r4)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r2 = kotlinx.coroutines.selects.SelectBuilderImpl._state$FU
            boolean r1 = r2.compareAndSet(r3, r1, r0)
            if (r1 != 0) goto L_0x0022
            goto L_0x0000
        L_0x0022:
            java.lang.Object r4 = r0.perform(r3)
            if (r4 == 0) goto L_0x0029
            return r4
        L_0x0029:
            r3.doAfterSelect()
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN
            return r4
        L_0x002f:
            boolean r1 = r0 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r1 == 0) goto L_0x0063
            if (r4 == 0) goto L_0x005d
            kotlinx.coroutines.internal.AtomicOp r1 = r4.getAtomicOp()
            boolean r2 = r1 instanceof kotlinx.coroutines.selects.SelectBuilderImpl.AtomicSelectOp
            if (r2 == 0) goto L_0x0051
            r2 = r1
            kotlinx.coroutines.selects.SelectBuilderImpl$AtomicSelectOp r2 = (kotlinx.coroutines.selects.SelectBuilderImpl.AtomicSelectOp) r2
            kotlinx.coroutines.selects.SelectBuilderImpl<?> r2 = r2.impl
            if (r2 == r3) goto L_0x0045
            goto L_0x0051
        L_0x0045:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r0 = "Cannot use matching select clauses on the same object"
            java.lang.String r0 = r0.toString()
            r4.<init>(r0)
            throw r4
        L_0x0051:
            r2 = r0
            kotlinx.coroutines.internal.OpDescriptor r2 = (kotlinx.coroutines.internal.OpDescriptor) r2
            boolean r1 = r1.isEarlierThan(r2)
            if (r1 == 0) goto L_0x005d
            java.lang.Object r4 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC
            return r4
        L_0x005d:
            kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
            r0.perform(r3)
            goto L_0x0000
        L_0x0063:
            if (r4 != 0) goto L_0x0066
            return r2
        L_0x0066:
            kotlinx.coroutines.internal.LockFreeLinkedListNode$AbstractAtomicDesc r4 = r4.desc
            if (r0 != r4) goto L_0x006d
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN
            return r4
        L_0x006d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.selects.SelectBuilderImpl.trySelectOther(kotlinx.coroutines.internal.LockFreeLinkedListNode$PrepareOp):java.lang.Object");
    }
}
