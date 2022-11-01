package kotlinx.coroutines.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LockFreeLinkedList.kt */
/* loaded from: classes.dex */
public class LockFreeLinkedListNode {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _removedRef$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef");
    public volatile /* synthetic */ Object _next = this;
    public volatile /* synthetic */ Object _prev = this;
    private volatile /* synthetic */ Object _removedRef = null;

    /* compiled from: LockFreeLinkedList.kt */
    /* loaded from: classes.dex */
    public static abstract class AbstractAtomicDesc extends AtomicDesc {
        @Override // kotlinx.coroutines.internal.AtomicDesc
        public final void complete(AtomicOp<?> atomicOp, Object obj) {
            LockFreeLinkedListNode originalNext;
            boolean z = obj == null;
            LockFreeLinkedListNode affectedNode = getAffectedNode();
            if (affectedNode != null && (originalNext = getOriginalNext()) != null) {
                if (LockFreeLinkedListNode._next$FU.compareAndSet(affectedNode, atomicOp, z ? updatedNext(affectedNode, originalNext) : originalNext) && z) {
                    finishOnSuccess(affectedNode, originalNext);
                }
            }
        }

        public abstract Object failure(LockFreeLinkedListNode lockFreeLinkedListNode);

        public abstract void finishOnSuccess(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2);

        public abstract void finishPrepare(PrepareOp prepareOp);

        public abstract LockFreeLinkedListNode getAffectedNode();

        public abstract LockFreeLinkedListNode getOriginalNext();

        public Object onPrepare(PrepareOp prepareOp) {
            finishPrepare(prepareOp);
            return null;
        }

        public void onRemoved(LockFreeLinkedListNode lockFreeLinkedListNode) {
        }

        @Override // kotlinx.coroutines.internal.AtomicDesc
        public final Object prepare(AtomicOp<?> atomicOp) {
            while (true) {
                LockFreeLinkedListNode takeAffectedNode = takeAffectedNode(atomicOp);
                if (takeAffectedNode == null) {
                    return AtomicKt.RETRY_ATOMIC;
                }
                PrepareOp prepareOp = takeAffectedNode._next;
                if (prepareOp == atomicOp || atomicOp.isDecided()) {
                    return null;
                }
                if (prepareOp instanceof OpDescriptor) {
                    OpDescriptor opDescriptor = (OpDescriptor) prepareOp;
                    if (atomicOp.isEarlierThan(opDescriptor)) {
                        return AtomicKt.RETRY_ATOMIC;
                    }
                    opDescriptor.perform(takeAffectedNode);
                } else {
                    Object failure = failure(takeAffectedNode);
                    if (failure != null) {
                        return failure;
                    }
                    if (retry(takeAffectedNode, prepareOp)) {
                        continue;
                    } else {
                        prepareOp = new PrepareOp(takeAffectedNode, (LockFreeLinkedListNode) prepareOp, this);
                        if (r4.compareAndSet(takeAffectedNode, prepareOp, prepareOp)) {
                            try {
                                if (prepareOp.perform(takeAffectedNode) != LockFreeLinkedList_commonKt.REMOVE_PREPARED) {
                                    return null;
                                }
                            } finally {
                                LockFreeLinkedListNode._next$FU.compareAndSet(takeAffectedNode, prepareOp, prepareOp);
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }

        public abstract boolean retry(LockFreeLinkedListNode lockFreeLinkedListNode, Object obj);

        public abstract LockFreeLinkedListNode takeAffectedNode(OpDescriptor opDescriptor);

        public abstract Object updatedNext(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2);
    }

    /* compiled from: LockFreeLinkedList.kt */
    /* loaded from: classes.dex */
    public static abstract class CondAddOp extends AtomicOp<LockFreeLinkedListNode> {
        public final LockFreeLinkedListNode newNode;
        public LockFreeLinkedListNode oldNext;

        public CondAddOp(LockFreeLinkedListNode lockFreeLinkedListNode) {
            this.newNode = lockFreeLinkedListNode;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(LockFreeLinkedListNode lockFreeLinkedListNode, Object obj) {
            LockFreeLinkedListNode lockFreeLinkedListNode2 = lockFreeLinkedListNode;
            boolean z = obj == null;
            LockFreeLinkedListNode lockFreeLinkedListNode3 = z ? this.newNode : this.oldNext;
            if (lockFreeLinkedListNode3 != null && LockFreeLinkedListNode._next$FU.compareAndSet(lockFreeLinkedListNode2, this, lockFreeLinkedListNode3) && z) {
                this.newNode.finishAdd(this.oldNext);
            }
        }
    }

    /* compiled from: LockFreeLinkedList.kt */
    /* loaded from: classes.dex */
    public static final class PrepareOp extends OpDescriptor {
        public final LockFreeLinkedListNode affected;
        public final AbstractAtomicDesc desc;
        public final LockFreeLinkedListNode next;

        public PrepareOp(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, AbstractAtomicDesc abstractAtomicDesc) {
            this.affected = lockFreeLinkedListNode;
            this.next = lockFreeLinkedListNode2;
            this.desc = abstractAtomicDesc;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public AtomicOp<?> getAtomicOp() {
            AtomicOp<?> atomicOp = this.desc.atomicOp;
            if (atomicOp != null) {
                return atomicOp;
            }
            Intrinsics.throwUninitializedPropertyAccessException("atomicOp");
            throw null;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public Object perform(Object obj) {
            Object obj2;
            Object obj3;
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode{ kotlinx.coroutines.internal.LockFreeLinkedListKt.Node }");
            LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
            Object onPrepare = this.desc.onPrepare(this);
            Object obj4 = LockFreeLinkedList_commonKt.REMOVE_PREPARED;
            if (onPrepare == obj4) {
                LockFreeLinkedListNode lockFreeLinkedListNode2 = this.next;
                if (LockFreeLinkedListNode._next$FU.compareAndSet(lockFreeLinkedListNode, this, LockFreeLinkedListNode.access$removed(lockFreeLinkedListNode2))) {
                    this.desc.onRemoved(lockFreeLinkedListNode);
                    lockFreeLinkedListNode2.correctPrev(null);
                }
                return obj4;
            }
            if (onPrepare != null) {
                obj2 = getAtomicOp().decide(onPrepare);
            } else {
                obj2 = getAtomicOp().getConsensus();
            }
            if (obj2 == AtomicKt.NO_DECISION) {
                obj3 = getAtomicOp();
            } else if (obj2 == null) {
                obj3 = this.desc.updatedNext(lockFreeLinkedListNode, this.next);
            } else {
                obj3 = this.next;
            }
            LockFreeLinkedListNode._next$FU.compareAndSet(lockFreeLinkedListNode, this, obj3);
            return null;
        }

        @Override // kotlinx.coroutines.internal.OpDescriptor
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("PrepareOp(op=");
            outline13.append(getAtomicOp());
            outline13.append(')');
            return outline13.toString();
        }
    }

    /* compiled from: LockFreeLinkedList.kt */
    /* loaded from: classes.dex */
    public static class RemoveFirstDesc<T> extends AbstractAtomicDesc {
        public static final /* synthetic */ AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_affectedNode");
        public static final /* synthetic */ AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_originalNext");
        private volatile /* synthetic */ Object _affectedNode = null;
        private volatile /* synthetic */ Object _originalNext = null;
        public final LockFreeLinkedListNode queue;

        public RemoveFirstDesc(LockFreeLinkedListNode lockFreeLinkedListNode) {
            this.queue = lockFreeLinkedListNode;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public Object failure(LockFreeLinkedListNode lockFreeLinkedListNode) {
            if (lockFreeLinkedListNode == this.queue) {
                return LockFreeLinkedListKt.LIST_EMPTY;
            }
            return null;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final void finishOnSuccess(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = LockFreeLinkedListNode._next$FU;
            lockFreeLinkedListNode2.correctPrev(null);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public void finishPrepare(PrepareOp prepareOp) {
            _affectedNode$FU.compareAndSet(this, null, prepareOp.affected);
            _originalNext$FU.compareAndSet(this, null, prepareOp.next);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final LockFreeLinkedListNode getAffectedNode() {
            return (LockFreeLinkedListNode) this._affectedNode;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final LockFreeLinkedListNode getOriginalNext() {
            return (LockFreeLinkedListNode) this._originalNext;
        }

        public final T getResult() {
            return (T) ((LockFreeLinkedListNode) this._affectedNode);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final boolean retry(LockFreeLinkedListNode lockFreeLinkedListNode, Object obj) {
            if (!(obj instanceof Removed)) {
                return false;
            }
            ((Removed) obj).ref.helpRemovePrev();
            return true;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final LockFreeLinkedListNode takeAffectedNode(OpDescriptor opDescriptor) {
            LockFreeLinkedListNode lockFreeLinkedListNode = this.queue;
            while (true) {
                Object obj = lockFreeLinkedListNode._next;
                if (!(obj instanceof OpDescriptor)) {
                    return (LockFreeLinkedListNode) obj;
                }
                OpDescriptor opDescriptor2 = (OpDescriptor) obj;
                if (opDescriptor.isEarlierThan(opDescriptor2)) {
                    return null;
                }
                opDescriptor2.perform(this.queue);
            }
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode.AbstractAtomicDesc
        public final Object updatedNext(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
            return LockFreeLinkedListNode.access$removed(lockFreeLinkedListNode2);
        }
    }

    public static final Removed access$removed(LockFreeLinkedListNode lockFreeLinkedListNode) {
        Removed removed = (Removed) lockFreeLinkedListNode._removedRef;
        if (removed != null) {
            return removed;
        }
        Removed removed2 = new Removed(lockFreeLinkedListNode);
        _removedRef$FU.lazySet(lockFreeLinkedListNode, removed2);
        return removed2;
    }

    public final boolean addNext(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2) {
        _prev$FU.lazySet(lockFreeLinkedListNode, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
        atomicReferenceFieldUpdater.lazySet(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        if (!atomicReferenceFieldUpdater.compareAndSet(this, lockFreeLinkedListNode2, lockFreeLinkedListNode)) {
            return false;
        }
        lockFreeLinkedListNode.finishAdd(lockFreeLinkedListNode2);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0048, code lost:
        if (kotlinx.coroutines.internal.LockFreeLinkedListNode._next$FU.compareAndSet(r3, r2, ((kotlinx.coroutines.internal.Removed) r4).ref) != false) goto L_0x004b;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlinx.coroutines.internal.LockFreeLinkedListNode correctPrev(kotlinx.coroutines.internal.OpDescriptor r8) {
        /*
            r7 = this;
        L_0x0000:
            java.lang.Object r0 = r7._prev
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            r1 = 0
            r2 = r0
        L_0x0006:
            r3 = r1
        L_0x0007:
            java.lang.Object r4 = r2._next
            if (r4 != r7) goto L_0x0018
            if (r0 != r2) goto L_0x000e
            return r2
        L_0x000e:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = kotlinx.coroutines.internal.LockFreeLinkedListNode._prev$FU
            boolean r0 = r1.compareAndSet(r7, r0, r2)
            if (r0 != 0) goto L_0x0017
            goto L_0x0000
        L_0x0017:
            return r2
        L_0x0018:
            boolean r5 = r7.isRemoved()
            if (r5 == 0) goto L_0x001f
            return r1
        L_0x001f:
            if (r4 != r8) goto L_0x0022
            return r2
        L_0x0022:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r5 == 0) goto L_0x0038
            if (r8 == 0) goto L_0x0032
            r0 = r4
            kotlinx.coroutines.internal.OpDescriptor r0 = (kotlinx.coroutines.internal.OpDescriptor) r0
            boolean r0 = r8.isEarlierThan(r0)
            if (r0 == 0) goto L_0x0032
            return r1
        L_0x0032:
            kotlinx.coroutines.internal.OpDescriptor r4 = (kotlinx.coroutines.internal.OpDescriptor) r4
            r4.perform(r2)
            goto L_0x0000
        L_0x0038:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.Removed
            if (r5 == 0) goto L_0x0052
            if (r3 == 0) goto L_0x004d
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = kotlinx.coroutines.internal.LockFreeLinkedListNode._next$FU
            kotlinx.coroutines.internal.Removed r4 = (kotlinx.coroutines.internal.Removed) r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = r4.ref
            boolean r2 = r5.compareAndSet(r3, r2, r4)
            if (r2 != 0) goto L_0x004b
            goto L_0x0000
        L_0x004b:
            r2 = r3
            goto L_0x0006
        L_0x004d:
            java.lang.Object r2 = r2._prev
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            goto L_0x0007
        L_0x0052:
            r3 = r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x0007
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.correctPrev(kotlinx.coroutines.internal.OpDescriptor):kotlinx.coroutines.internal.LockFreeLinkedListNode");
    }

    public final void finishAdd(LockFreeLinkedListNode lockFreeLinkedListNode) {
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        do {
            lockFreeLinkedListNode2 = (LockFreeLinkedListNode) lockFreeLinkedListNode._prev;
            if (getNext() != lockFreeLinkedListNode) {
                return;
            }
        } while (!_prev$FU.compareAndSet(lockFreeLinkedListNode, lockFreeLinkedListNode2, this));
        if (isRemoved()) {
            lockFreeLinkedListNode.correctPrev(null);
        }
    }

    public final Object getNext() {
        while (true) {
            Object obj = this._next;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    public final LockFreeLinkedListNode getNextNode() {
        Object next = getNext();
        LockFreeLinkedListNode lockFreeLinkedListNode = null;
        Removed removed = next instanceof Removed ? (Removed) next : null;
        if (removed != null) {
            lockFreeLinkedListNode = removed.ref;
        }
        return lockFreeLinkedListNode == null ? (LockFreeLinkedListNode) next : lockFreeLinkedListNode;
    }

    public final LockFreeLinkedListNode getPrevNode() {
        LockFreeLinkedListNode correctPrev = correctPrev(null);
        if (correctPrev == null) {
            Object obj = this._prev;
            while (true) {
                correctPrev = (LockFreeLinkedListNode) obj;
                if (!correctPrev.isRemoved()) {
                    break;
                }
                obj = correctPrev._prev;
            }
        }
        return correctPrev;
    }

    public final void helpRemove() {
        ((Removed) getNext()).ref.correctPrev(null);
    }

    public final void helpRemovePrev() {
        LockFreeLinkedListNode lockFreeLinkedListNode = this;
        while (true) {
            Object next = lockFreeLinkedListNode.getNext();
            if (!(next instanceof Removed)) {
                lockFreeLinkedListNode.correctPrev(null);
                return;
            }
            lockFreeLinkedListNode = ((Removed) next).ref;
        }
    }

    public boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    public boolean remove() {
        return removeOrNext() == null;
    }

    public final LockFreeLinkedListNode removeOrNext() {
        Object next;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        Removed removed;
        do {
            next = getNext();
            if (next instanceof Removed) {
                return ((Removed) next).ref;
            }
            if (next == this) {
                return (LockFreeLinkedListNode) next;
            }
            lockFreeLinkedListNode = (LockFreeLinkedListNode) next;
            removed = (Removed) lockFreeLinkedListNode._removedRef;
            if (removed == null) {
                removed = new Removed(lockFreeLinkedListNode);
                _removedRef$FU.lazySet(lockFreeLinkedListNode, removed);
            }
        } while (!_next$FU.compareAndSet(this, next, removed));
        lockFreeLinkedListNode.correctPrev(null);
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) getClass().getSimpleName());
        sb.append('@');
        sb.append((Object) Integer.toHexString(System.identityHashCode(this)));
        return sb.toString();
    }

    public final int tryCondAddNext(LockFreeLinkedListNode lockFreeLinkedListNode, LockFreeLinkedListNode lockFreeLinkedListNode2, CondAddOp condAddOp) {
        _prev$FU.lazySet(lockFreeLinkedListNode, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
        atomicReferenceFieldUpdater.lazySet(lockFreeLinkedListNode, lockFreeLinkedListNode2);
        condAddOp.oldNext = lockFreeLinkedListNode2;
        if (!atomicReferenceFieldUpdater.compareAndSet(this, lockFreeLinkedListNode2, condAddOp)) {
            return 0;
        }
        return condAddOp.perform(this) == null ? 1 : 2;
    }
}
