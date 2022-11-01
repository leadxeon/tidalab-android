package kotlinx.coroutines;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
import kotlinx.coroutines.selects.SelectClause0;
/* compiled from: JobSupport.kt */
/* loaded from: classes.dex */
public class JobSupport implements Job, ChildJob, ParentJob, SelectClause0 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
    private volatile /* synthetic */ Object _parentHandle;
    private volatile /* synthetic */ Object _state;

    /* compiled from: JobSupport.kt */
    /* loaded from: classes.dex */
    public static final class AwaitContinuation<T> extends CancellableContinuationImpl<T> {
        public final JobSupport job;

        public AwaitContinuation(Continuation<? super T> continuation, JobSupport jobSupport) {
            super(continuation, 1);
            this.job = jobSupport;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public Throwable getContinuationCancellationCause(Job job) {
            Throwable rootCause;
            Object state$kotlinx_coroutines_core = this.job.getState$kotlinx_coroutines_core();
            return (!(state$kotlinx_coroutines_core instanceof Finishing) || (rootCause = ((Finishing) state$kotlinx_coroutines_core).getRootCause()) == null) ? state$kotlinx_coroutines_core instanceof CompletedExceptionally ? ((CompletedExceptionally) state$kotlinx_coroutines_core).cause : ((JobSupport) job).getCancellationException() : rootCause;
        }

        @Override // kotlinx.coroutines.CancellableContinuationImpl
        public String nameString() {
            return "AwaitContinuation";
        }
    }

    /* compiled from: JobSupport.kt */
    /* loaded from: classes.dex */
    public static final class ChildCompletion extends JobNode {
        public final ChildHandleNode child;
        public final JobSupport parent;
        public final Object proposedUpdate;
        public final Finishing state;

        public ChildCompletion(JobSupport jobSupport, Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
            this.parent = jobSupport;
            this.state = finishing;
            this.child = childHandleNode;
            this.proposedUpdate = obj;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        @Override // kotlinx.coroutines.CompletionHandlerBase
        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public void invoke2(Throwable th) {
            JobSupport jobSupport = this.parent;
            Finishing finishing = this.state;
            ChildHandleNode childHandleNode = this.child;
            Object obj = this.proposedUpdate;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = JobSupport._state$FU;
            ChildHandleNode nextChild = jobSupport.nextChild(childHandleNode);
            if (nextChild == null || !jobSupport.tryWaitForChild(finishing, nextChild, obj)) {
                jobSupport.afterCompletion(jobSupport.finalizeFinishingState(finishing, obj));
            }
        }
    }

    /* compiled from: JobSupport.kt */
    /* loaded from: classes.dex */
    public static final class Finishing implements Incomplete {
        private volatile /* synthetic */ Object _exceptionsHolder = null;
        private volatile /* synthetic */ int _isCompleting;
        private volatile /* synthetic */ Object _rootCause;
        public final NodeList list;

        public Finishing(NodeList nodeList, boolean z, Throwable th) {
            this.list = nodeList;
            this._isCompleting = z ? 1 : 0;
            this._rootCause = th;
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object] */
        /* JADX WARN: Unknown variable types count: 1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void addExceptionLocked(java.lang.Throwable r3) {
            /*
                r2 = this;
                java.lang.Object r0 = r2._rootCause
                java.lang.Throwable r0 = (java.lang.Throwable) r0
                if (r0 != 0) goto L_0x0009
                r2._rootCause = r3
                return
            L_0x0009:
                if (r3 != r0) goto L_0x000c
                return
            L_0x000c:
                java.lang.Object r0 = r2._exceptionsHolder
                if (r0 != 0) goto L_0x0013
                r2._exceptionsHolder = r3
                goto L_0x0032
            L_0x0013:
                boolean r1 = r0 instanceof java.lang.Throwable
                if (r1 == 0) goto L_0x0029
                if (r3 != r0) goto L_0x001a
                return
            L_0x001a:
                java.util.ArrayList r1 = r2.allocateList()
                r1.add(r0)
                r1.add(r3)
                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                r2._exceptionsHolder = r1
                goto L_0x0032
            L_0x0029:
                boolean r1 = r0 instanceof java.util.ArrayList
                if (r1 == 0) goto L_0x0033
                java.util.ArrayList r0 = (java.util.ArrayList) r0
                r0.add(r3)
            L_0x0032:
                return
            L_0x0033:
                java.lang.String r3 = "State is "
                java.lang.String r3 = kotlin.jvm.internal.Intrinsics.stringPlus(r3, r0)
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r3 = r3.toString()
                r0.<init>(r3)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.Finishing.addExceptionLocked(java.lang.Throwable):void");
        }

        public final ArrayList<Throwable> allocateList() {
            return new ArrayList<>(4);
        }

        @Override // kotlinx.coroutines.Incomplete
        public NodeList getList() {
            return this.list;
        }

        public final Throwable getRootCause() {
            return (Throwable) this._rootCause;
        }

        @Override // kotlinx.coroutines.Incomplete
        public boolean isActive() {
            return ((Throwable) this._rootCause) == null;
        }

        public final boolean isCancelling() {
            return ((Throwable) this._rootCause) != null;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [int, boolean] */
        public final boolean isCompleting() {
            return this._isCompleting;
        }

        public final boolean isSealed() {
            return this._exceptionsHolder == JobSupportKt.SEALED;
        }

        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object] */
        /* JADX WARN: Unknown variable types count: 1 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.util.List<java.lang.Throwable> sealLocked(java.lang.Throwable r4) {
            /*
                r3 = this;
                java.lang.Object r0 = r3._exceptionsHolder
                if (r0 != 0) goto L_0x0009
                java.util.ArrayList r0 = r3.allocateList()
                goto L_0x001c
            L_0x0009:
                boolean r1 = r0 instanceof java.lang.Throwable
                if (r1 == 0) goto L_0x0016
                java.util.ArrayList r1 = r3.allocateList()
                r1.add(r0)
                r0 = r1
                goto L_0x001c
            L_0x0016:
                boolean r1 = r0 instanceof java.util.ArrayList
                if (r1 == 0) goto L_0x0037
                java.util.ArrayList r0 = (java.util.ArrayList) r0
            L_0x001c:
                java.lang.Object r1 = r3._rootCause
                java.lang.Throwable r1 = (java.lang.Throwable) r1
                if (r1 != 0) goto L_0x0023
                goto L_0x0027
            L_0x0023:
                r2 = 0
                r0.add(r2, r1)
            L_0x0027:
                if (r4 == 0) goto L_0x0032
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r1)
                if (r1 != 0) goto L_0x0032
                r0.add(r4)
            L_0x0032:
                kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.JobSupportKt.SEALED
                r3._exceptionsHolder = r4
                return r0
            L_0x0037:
                java.lang.String r4 = "State is "
                java.lang.String r4 = kotlin.jvm.internal.Intrinsics.stringPlus(r4, r0)
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r4 = r4.toString()
                r0.<init>(r4)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.Finishing.sealLocked(java.lang.Throwable):java.util.List");
        }

        public final void setCompleting(boolean z) {
            this._isCompleting = z ? 1 : 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v2, types: [int, boolean] */
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Finishing[cancelling=");
            outline13.append(isCancelling());
            outline13.append(", completing=");
            outline13.append((boolean) this._isCompleting);
            outline13.append(", rootCause=");
            outline13.append((Throwable) this._rootCause);
            outline13.append(", exceptions=");
            outline13.append(this._exceptionsHolder);
            outline13.append(", list=");
            outline13.append(this.list);
            outline13.append(']');
            return outline13.toString();
        }
    }

    public JobSupport(boolean z) {
        this._state = z ? JobSupportKt.EMPTY_ACTIVE : JobSupportKt.EMPTY_NEW;
        this._parentHandle = null;
    }

    public static /* synthetic */ CancellationException toCancellationException$default(JobSupport jobSupport, Throwable th, String str, int i, Object obj) {
        int i2 = i & 1;
        return jobSupport.toCancellationException(th, null);
    }

    public final boolean addLastAtomic(final Object obj, NodeList nodeList, final JobNode jobNode) {
        int tryCondAddNext;
        LockFreeLinkedListNode.CondAddOp jobSupport$addLastAtomic$$inlined$addLastIf$1 = new LockFreeLinkedListNode.CondAddOp(jobNode) { // from class: kotlinx.coroutines.JobSupport$addLastAtomic$$inlined$addLastIf$1
            @Override // kotlinx.coroutines.internal.AtomicOp
            public Object prepare(LockFreeLinkedListNode lockFreeLinkedListNode) {
                if (this.getState$kotlinx_coroutines_core() == obj) {
                    return null;
                }
                return LockFreeLinkedListKt.CONDITION_FALSE;
            }
        };
        do {
            tryCondAddNext = nodeList.getPrevNode().tryCondAddNext(jobNode, nodeList, jobSupport$addLastAtomic$$inlined$addLastIf$1);
            if (tryCondAddNext == 1) {
                return true;
            }
        } while (tryCondAddNext != 2);
        return false;
    }

    public void afterCompletion(Object obj) {
    }

    @Override // kotlinx.coroutines.Job
    public final ChildHandle attachChild(ChildJob childJob) {
        return (ChildHandle) InputKt.invokeOnCompletion$default(this, true, false, new ChildHandleNode(childJob), 2, null);
    }

    public /* synthetic */ void cancel() {
        cancel((CancellationException) null);
    }

    /* JADX WARN: Removed duplicated region for block: B:79:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0040 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean cancelImpl$kotlinx_coroutines_core(java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobSupport.cancelImpl$kotlinx_coroutines_core(java.lang.Object):boolean");
    }

    public final boolean cancelParent(Throwable th) {
        if (isScopedCoroutine()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        ChildHandle childHandle = (ChildHandle) this._parentHandle;
        return (childHandle == null || childHandle == NonDisposableHandle.INSTANCE) ? z : childHandle.childCancelled(th) || z;
    }

    public String cancellationExceptionMessage() {
        return "Job was cancelled";
    }

    public boolean childCancelled(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        return cancelImpl$kotlinx_coroutines_core(th) && getHandlesException$kotlinx_coroutines_core();
    }

    public final void completeStateFinalization(Incomplete incomplete, Object obj) {
        CompletionHandlerException completionHandlerException;
        ChildHandle childHandle = (ChildHandle) this._parentHandle;
        if (childHandle != null) {
            childHandle.dispose();
            this._parentHandle = NonDisposableHandle.INSTANCE;
        }
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th = completedExceptionally == null ? null : completedExceptionally.cause;
        if (incomplete instanceof JobNode) {
            try {
                ((JobNode) incomplete).invoke(th);
            } catch (Throwable th2) {
                handleOnCompletionException$kotlinx_coroutines_core(new CompletionHandlerException("Exception in completion handler " + incomplete + " for " + this, th2));
            }
        } else {
            NodeList list = incomplete.getList();
            if (list != null) {
                CompletionHandlerException completionHandlerException2 = null;
                for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) list.getNext(); !Intrinsics.areEqual(lockFreeLinkedListNode, list); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
                    if (lockFreeLinkedListNode instanceof JobNode) {
                        JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                        try {
                            jobNode.invoke(th);
                        } catch (Throwable th3) {
                            if (completionHandlerException2 == null) {
                                completionHandlerException = null;
                            } else {
                                InputKt.addSuppressed(completionHandlerException2, th3);
                                completionHandlerException = completionHandlerException2;
                            }
                            if (completionHandlerException == null) {
                                completionHandlerException2 = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th3);
                            }
                        }
                    }
                }
                if (completionHandlerException2 != null) {
                    handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException2);
                }
            }
        }
    }

    public final Throwable createCauseException(Object obj) {
        if (obj == null ? true : obj instanceof Throwable) {
            Throwable th = (Throwable) obj;
            return th == null ? new JobCancellationException(cancellationExceptionMessage(), null, this) : th;
        }
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.ParentJob");
        return ((ParentJob) obj).getChildJobCancellationCause();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object finalizeFinishingState(Finishing finishing, Object obj) {
        Throwable th = null;
        CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
        Throwable th2 = completedExceptionally == null ? null : completedExceptionally.cause;
        synchronized (finishing) {
            finishing.isCancelling();
            List<Throwable> sealLocked = finishing.sealLocked(th2);
            if (!sealLocked.isEmpty()) {
                Iterator<T> it = sealLocked.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object next = it.next();
                    if (!(((Throwable) next) instanceof CancellationException)) {
                        th = next;
                        break;
                    }
                }
                th = th;
                if (th == null) {
                    th = sealLocked.get(0);
                }
            } else if (finishing.isCancelling()) {
                th = new JobCancellationException(cancellationExceptionMessage(), null, this);
            }
            if (th != null && sealLocked.size() > 1) {
                Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap(sealLocked.size()));
                for (Throwable th3 : sealLocked) {
                    if (th3 != th && th3 != th && !(th3 instanceof CancellationException) && newSetFromMap.add(th3)) {
                        InputKt.addSuppressed(th, th3);
                    }
                }
            }
        }
        if (!(th == null || th == th2)) {
            obj = new CompletedExceptionally(th, false, 2);
        }
        if (th != null) {
            if (cancelParent(th) || handleJobException(th)) {
                Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally");
                CompletedExceptionally._handled$FU.compareAndSet((CompletedExceptionally) obj, 0, 1);
            }
        }
        onCompletionInternal(obj);
        _state$FU.compareAndSet(this, finishing, obj instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj) : obj);
        completeStateFinalization(finishing, obj);
        return obj;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) function2.invoke(r, this);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) CoroutineContext.Element.DefaultImpls.get(this, key);
    }

    @Override // kotlinx.coroutines.Job
    public final CancellationException getCancellationException() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            Throwable rootCause = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
            if (rootCause != null) {
                return toCancellationException(rootCause, Intrinsics.stringPlus(getClass().getSimpleName(), " is cancelling"));
            }
            throw new IllegalStateException(Intrinsics.stringPlus("Job is still new or active: ", this).toString());
        } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            return state$kotlinx_coroutines_core instanceof CompletedExceptionally ? toCancellationException$default(this, ((CompletedExceptionally) state$kotlinx_coroutines_core).cause, null, 1, null) : new JobCancellationException(Intrinsics.stringPlus(getClass().getSimpleName(), " has completed normally"), null, this);
        } else {
            throw new IllegalStateException(Intrinsics.stringPlus("Job is still new or active: ", this).toString());
        }
    }

    @Override // kotlinx.coroutines.ParentJob
    public CancellationException getChildJobCancellationCause() {
        Throwable th;
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        CancellationException cancellationException = null;
        if (state$kotlinx_coroutines_core instanceof Finishing) {
            th = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
        } else if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
        } else if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            th = null;
        } else {
            throw new IllegalStateException(Intrinsics.stringPlus("Cannot be cancelling child in this state: ", state$kotlinx_coroutines_core).toString());
        }
        if (th instanceof CancellationException) {
            cancellationException = (CancellationException) th;
        }
        return cancellationException == null ? new JobCancellationException(Intrinsics.stringPlus("Parent job is ", stateString(state$kotlinx_coroutines_core)), th, this) : cancellationException;
    }

    public final Sequence<Job> getChildren() {
        final JobSupport$children$1 jobSupport$children$1 = new JobSupport$children$1(this, null);
        return new Sequence<T>() { // from class: kotlin.sequences.SequencesKt__SequenceBuilderKt$sequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<T> iterator() {
                Function2 function2 = Function2.this;
                SequenceBuilderIterator sequenceBuilderIterator = new SequenceBuilderIterator();
                sequenceBuilderIterator.nextStep = InputKt.createCoroutineUnintercepted(function2, sequenceBuilderIterator, sequenceBuilderIterator);
                return sequenceBuilderIterator;
            }
        };
    }

    public final Throwable getCompletionExceptionOrNull() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (!(state$kotlinx_coroutines_core instanceof Incomplete)) {
            CompletedExceptionally completedExceptionally = state$kotlinx_coroutines_core instanceof CompletedExceptionally ? (CompletedExceptionally) state$kotlinx_coroutines_core : null;
            if (completedExceptionally == null) {
                return null;
            }
            return completedExceptionally.cause;
        }
        throw new IllegalStateException("This job has not completed yet".toString());
    }

    public boolean getHandlesException$kotlinx_coroutines_core() {
        return true;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public final CoroutineContext.Key<?> getKey() {
        return Job.Key.$$INSTANCE;
    }

    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return this instanceof CompletableDeferredImpl;
    }

    public final SelectClause0 getOnJoin() {
        return this;
    }

    public final NodeList getOrPromoteCancellingList(Incomplete incomplete) {
        NodeList list = incomplete.getList();
        if (list != null) {
            return list;
        }
        if (incomplete instanceof Empty) {
            return new NodeList();
        }
        if (incomplete instanceof JobNode) {
            promoteSingleToNodeList((JobNode) incomplete);
            return null;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("State should have list: ", incomplete).toString());
    }

    public final ChildHandle getParentHandle$kotlinx_coroutines_core() {
        return (ChildHandle) this._parentHandle;
    }

    public final Object getState$kotlinx_coroutines_core() {
        while (true) {
            Object obj = this._state;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    public boolean handleJobException(Throwable th) {
        return false;
    }

    public void handleOnCompletionException$kotlinx_coroutines_core(Throwable th) {
        throw th;
    }

    public final void initParentJob(Job job) {
        if (job == null) {
            this._parentHandle = NonDisposableHandle.INSTANCE;
            return;
        }
        job.start();
        ChildHandle attachChild = job.attachChild(this);
        this._parentHandle = attachChild;
        if (!(getState$kotlinx_coroutines_core() instanceof Incomplete)) {
            attachChild.dispose();
            this._parentHandle = NonDisposableHandle.INSTANCE;
        }
    }

    public final DisposableHandle invokeOnCompletion(Function1<? super Throwable, Unit> function1) {
        return invokeOnCompletion(false, true, function1);
    }

    @Override // kotlinx.coroutines.Job
    public boolean isActive() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof Incomplete) && ((Incomplete) state$kotlinx_coroutines_core).isActive();
    }

    public final boolean isCancelled() {
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        return (state$kotlinx_coroutines_core instanceof CompletedExceptionally) || ((state$kotlinx_coroutines_core instanceof Finishing) && ((Finishing) state$kotlinx_coroutines_core).isCancelling());
    }

    public final boolean isCompleted() {
        return !(getState$kotlinx_coroutines_core() instanceof Incomplete);
    }

    public boolean isScopedCoroutine() {
        return this instanceof BlockingCoroutine;
    }

    @Override // kotlinx.coroutines.Job
    public final Object join(Continuation<? super Unit> continuation) {
        boolean z;
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Incomplete) {
                if (startInternal(state$kotlinx_coroutines_core) >= 0) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (!z) {
            InputKt.ensureActive(((ContinuationImpl) continuation)._context);
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(InputKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        cancellableContinuationImpl.invokeOnCancellation(new DisposeOnCancel(invokeOnCompletion(false, true, new ResumeOnCompletion(cancellableContinuationImpl))));
        Object result = cancellableContinuationImpl.getResult();
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (result != coroutineSingletons) {
            result = Unit.INSTANCE;
        }
        return result == coroutineSingletons ? result : Unit.INSTANCE;
    }

    public final boolean makeCompleting$kotlinx_coroutines_core(Object obj) {
        Object tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj);
            if (tryMakeCompleting == JobSupportKt.COMPLETING_ALREADY) {
                return false;
            }
            if (tryMakeCompleting == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
                return true;
            }
        } while (tryMakeCompleting == JobSupportKt.COMPLETING_RETRY);
        return true;
    }

    public final Object makeCompletingOnce$kotlinx_coroutines_core(Object obj) {
        Object tryMakeCompleting;
        do {
            tryMakeCompleting = tryMakeCompleting(getState$kotlinx_coroutines_core(), obj);
            if (tryMakeCompleting == JobSupportKt.COMPLETING_ALREADY) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                Throwable th = null;
                CompletedExceptionally completedExceptionally = obj instanceof CompletedExceptionally ? (CompletedExceptionally) obj : null;
                if (completedExceptionally != null) {
                    th = completedExceptionally.cause;
                }
                throw new IllegalStateException(str, th);
            }
        } while (tryMakeCompleting == JobSupportKt.COMPLETING_RETRY);
        return tryMakeCompleting;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return CoroutineContext.Element.DefaultImpls.minusKey(this, key);
    }

    public String nameString$kotlinx_coroutines_core() {
        return getClass().getSimpleName();
    }

    public final ChildHandleNode nextChild(LockFreeLinkedListNode lockFreeLinkedListNode) {
        while (lockFreeLinkedListNode.isRemoved()) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getPrevNode();
        }
        while (true) {
            lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode();
            if (!lockFreeLinkedListNode.isRemoved()) {
                if (lockFreeLinkedListNode instanceof ChildHandleNode) {
                    return (ChildHandleNode) lockFreeLinkedListNode;
                }
                if (lockFreeLinkedListNode instanceof NodeList) {
                    return null;
                }
            }
        }
    }

    public final void notifyCancelling(NodeList nodeList, Throwable th) {
        CompletionHandlerException completionHandlerException;
        CompletionHandlerException completionHandlerException2 = null;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) nodeList.getNext(); !Intrinsics.areEqual(lockFreeLinkedListNode, nodeList); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobCancellingNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                try {
                    jobNode.invoke(th);
                } catch (Throwable th2) {
                    if (completionHandlerException2 == null) {
                        completionHandlerException = null;
                    } else {
                        InputKt.addSuppressed(completionHandlerException2, th2);
                        completionHandlerException = completionHandlerException2;
                    }
                    if (completionHandlerException == null) {
                        completionHandlerException2 = new CompletionHandlerException("Exception in completion handler " + jobNode + " for " + this, th2);
                    }
                }
            }
        }
        if (completionHandlerException2 != null) {
            handleOnCompletionException$kotlinx_coroutines_core(completionHandlerException2);
        }
        cancelParent(th);
    }

    public void onCompletionInternal(Object obj) {
    }

    public void onStart() {
    }

    @Override // kotlinx.coroutines.ChildJob
    public final void parentCancelled(ParentJob parentJob) {
        cancelImpl$kotlinx_coroutines_core(parentJob);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return CoroutineContext.Element.DefaultImpls.plus(this, coroutineContext);
    }

    public Job plus(Job job) {
        return job;
    }

    public final void promoteSingleToNodeList(JobNode jobNode) {
        NodeList nodeList = new NodeList();
        LockFreeLinkedListNode._prev$FU.lazySet(nodeList, jobNode);
        LockFreeLinkedListNode._next$FU.lazySet(nodeList, jobNode);
        while (true) {
            if (jobNode.getNext() == jobNode) {
                if (LockFreeLinkedListNode._next$FU.compareAndSet(jobNode, jobNode, nodeList)) {
                    nodeList.finishAdd(jobNode);
                    break;
                }
            } else {
                break;
            }
        }
        _state$FU.compareAndSet(this, jobNode, jobNode.getNextNode());
    }

    @Override // kotlinx.coroutines.Job
    public final boolean start() {
        int startInternal;
        do {
            startInternal = startInternal(getState$kotlinx_coroutines_core());
            if (startInternal == 0) {
                return false;
            }
        } while (startInternal != 1);
        return true;
    }

    public final int startInternal(Object obj) {
        if (obj instanceof Empty) {
            if (((Empty) obj).isActive) {
                return 0;
            }
            if (!_state$FU.compareAndSet(this, obj, JobSupportKt.EMPTY_ACTIVE)) {
                return -1;
            }
            onStart();
            return 1;
        } else if (!(obj instanceof InactiveNodeList)) {
            return 0;
        } else {
            if (!_state$FU.compareAndSet(this, obj, ((InactiveNodeList) obj).list)) {
                return -1;
            }
            onStart();
            return 1;
        }
    }

    public final String stateString(Object obj) {
        if (!(obj instanceof Finishing)) {
            return obj instanceof Incomplete ? ((Incomplete) obj).isActive() ? "Active" : "New" : obj instanceof CompletedExceptionally ? "Cancelled" : "Completed";
        }
        Finishing finishing = (Finishing) obj;
        return finishing.isCancelling() ? "Cancelling" : finishing.isCompleting() ? "Completing" : "Active";
    }

    public final CancellationException toCancellationException(Throwable th, String str) {
        CancellationException cancellationException = th instanceof CancellationException ? (CancellationException) th : null;
        if (cancellationException == null) {
            if (str == null) {
                str = cancellationExceptionMessage();
            }
            cancellationException = new JobCancellationException(str, th, this);
        }
        return cancellationException;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nameString$kotlinx_coroutines_core() + '{' + stateString(getState$kotlinx_coroutines_core()) + '}');
        sb.append('@');
        sb.append(InputKt.getHexAddress(this));
        return sb.toString();
    }

    public final Object tryMakeCompleting(Object obj, Object obj2) {
        if (!(obj instanceof Incomplete)) {
            return JobSupportKt.COMPLETING_ALREADY;
        }
        boolean z = true;
        if (((obj instanceof Empty) || (obj instanceof JobNode)) && !(obj instanceof ChildHandleNode) && !(obj2 instanceof CompletedExceptionally)) {
            Incomplete incomplete = (Incomplete) obj;
            if (!_state$FU.compareAndSet(this, incomplete, obj2 instanceof Incomplete ? new IncompleteStateBox((Incomplete) obj2) : obj2)) {
                z = false;
            } else {
                onCompletionInternal(obj2);
                completeStateFinalization(incomplete, obj2);
            }
            return z ? obj2 : JobSupportKt.COMPLETING_RETRY;
        }
        Incomplete incomplete2 = (Incomplete) obj;
        NodeList orPromoteCancellingList = getOrPromoteCancellingList(incomplete2);
        if (orPromoteCancellingList == null) {
            return JobSupportKt.COMPLETING_RETRY;
        }
        ChildHandleNode childHandleNode = null;
        Finishing finishing = incomplete2 instanceof Finishing ? (Finishing) incomplete2 : null;
        if (finishing == null) {
            finishing = new Finishing(orPromoteCancellingList, false, null);
        }
        synchronized (finishing) {
            if (finishing.isCompleting()) {
                return JobSupportKt.COMPLETING_ALREADY;
            }
            finishing.setCompleting(true);
            if (finishing == incomplete2 || _state$FU.compareAndSet(this, incomplete2, finishing)) {
                boolean isCancelling = finishing.isCancelling();
                CompletedExceptionally completedExceptionally = obj2 instanceof CompletedExceptionally ? (CompletedExceptionally) obj2 : null;
                if (completedExceptionally != null) {
                    finishing.addExceptionLocked(completedExceptionally.cause);
                }
                Throwable rootCause = finishing.getRootCause();
                if (!(true ^ isCancelling)) {
                    rootCause = null;
                }
                Unit unit = Unit.INSTANCE;
                if (rootCause != null) {
                    notifyCancelling(orPromoteCancellingList, rootCause);
                }
                ChildHandleNode childHandleNode2 = incomplete2 instanceof ChildHandleNode ? (ChildHandleNode) incomplete2 : null;
                if (childHandleNode2 == null) {
                    NodeList list = incomplete2.getList();
                    if (list != null) {
                        childHandleNode = nextChild(list);
                    }
                } else {
                    childHandleNode = childHandleNode2;
                }
                if (childHandleNode == null || !tryWaitForChild(finishing, childHandleNode, obj2)) {
                    return finalizeFinishingState(finishing, obj2);
                }
                return JobSupportKt.COMPLETING_WAITING_CHILDREN;
            }
            return JobSupportKt.COMPLETING_RETRY;
        }
    }

    public final boolean tryWaitForChild(Finishing finishing, ChildHandleNode childHandleNode, Object obj) {
        while (InputKt.invokeOnCompletion$default(childHandleNode.childJob, false, false, new ChildCompletion(this, finishing, childHandleNode, obj), 1, null) == NonDisposableHandle.INSTANCE) {
            childHandleNode = nextChild(childHandleNode);
            if (childHandleNode == null) {
                return false;
            }
        }
        return true;
    }

    @Override // kotlinx.coroutines.Job
    public void cancel(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new JobCancellationException(cancellationExceptionMessage(), null, this);
        }
        cancelImpl$kotlinx_coroutines_core(cancellationException);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [kotlinx.coroutines.InactiveNodeList] */
    @Override // kotlinx.coroutines.Job
    public final DisposableHandle invokeOnCompletion(boolean z, boolean z2, Function1<? super Throwable, Unit> function1) {
        JobNode jobNode;
        Throwable th;
        Throwable th2 = null;
        if (z) {
            jobNode = function1 instanceof JobCancellingNode ? (JobCancellingNode) function1 : null;
            if (jobNode == null) {
                jobNode = new InvokeOnCancelling(function1);
            }
        } else {
            jobNode = function1 instanceof JobNode ? (JobNode) function1 : null;
            if (jobNode == null) {
                jobNode = null;
            }
            if (jobNode == null) {
                jobNode = new InvokeOnCompletion(function1);
            }
        }
        jobNode.job = this;
        while (true) {
            Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
            if (state$kotlinx_coroutines_core instanceof Empty) {
                Empty empty = (Empty) state$kotlinx_coroutines_core;
                if (!empty.isActive) {
                    NodeList nodeList = new NodeList();
                    if (!empty.isActive) {
                        nodeList = new InactiveNodeList(nodeList);
                    }
                    _state$FU.compareAndSet(this, empty, nodeList);
                } else if (_state$FU.compareAndSet(this, state$kotlinx_coroutines_core, jobNode)) {
                    return jobNode;
                }
            } else if (state$kotlinx_coroutines_core instanceof Incomplete) {
                NodeList list = ((Incomplete) state$kotlinx_coroutines_core).getList();
                if (list == null) {
                    Objects.requireNonNull(state$kotlinx_coroutines_core, "null cannot be cast to non-null type kotlinx.coroutines.JobNode");
                    promoteSingleToNodeList((JobNode) state$kotlinx_coroutines_core);
                } else {
                    DisposableHandle disposableHandle = NonDisposableHandle.INSTANCE;
                    if (!z || !(state$kotlinx_coroutines_core instanceof Finishing)) {
                        th = null;
                    } else {
                        synchronized (state$kotlinx_coroutines_core) {
                            th = ((Finishing) state$kotlinx_coroutines_core).getRootCause();
                            if (th == null || ((function1 instanceof ChildHandleNode) && !((Finishing) state$kotlinx_coroutines_core).isCompleting())) {
                                if (addLastAtomic(state$kotlinx_coroutines_core, list, jobNode)) {
                                    if (th == null) {
                                        return jobNode;
                                    }
                                    disposableHandle = jobNode;
                                }
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                    if (th != null) {
                        if (z2) {
                            function1.invoke(th);
                        }
                        return disposableHandle;
                    } else if (addLastAtomic(state$kotlinx_coroutines_core, list, jobNode)) {
                        return jobNode;
                    }
                }
            } else {
                if (z2) {
                    CompletedExceptionally completedExceptionally = state$kotlinx_coroutines_core instanceof CompletedExceptionally ? (CompletedExceptionally) state$kotlinx_coroutines_core : null;
                    if (completedExceptionally != null) {
                        th2 = completedExceptionally.cause;
                    }
                    function1.invoke(th2);
                }
                return NonDisposableHandle.INSTANCE;
            }
        }
    }

    public boolean cancel(Throwable th) {
        Object obj;
        if (th == null) {
            obj = new JobCancellationException(cancellationExceptionMessage(), null, this);
        } else {
            obj = toCancellationException$default(this, th, null, 1, null);
        }
        cancelImpl$kotlinx_coroutines_core(obj);
        return true;
    }
}
