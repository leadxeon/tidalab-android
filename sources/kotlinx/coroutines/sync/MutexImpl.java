package kotlinx.coroutines.sync;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.AtomicOp;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.OpDescriptor;
/* compiled from: Mutex.kt */
/* loaded from: classes.dex */
public final class MutexImpl implements Mutex {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
    public volatile /* synthetic */ Object _state;

    /* compiled from: Mutex.kt */
    /* loaded from: classes.dex */
    public final class LockCont extends LockWaiter {
        public final CancellableContinuation<Unit> cont;

        /* JADX WARN: Multi-variable type inference failed */
        public LockCont(Object obj, CancellableContinuation<? super Unit> cancellableContinuation) {
            super(MutexImpl.this, obj);
            this.cont = cancellableContinuation;
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public void completeResumeLockWaiter(Object obj) {
            this.cont.completeResume(obj);
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("LockCont[");
            outline13.append(this.owner);
            outline13.append(", ");
            outline13.append(this.cont);
            outline13.append("] for ");
            outline13.append(MutexImpl.this);
            return outline13.toString();
        }

        @Override // kotlinx.coroutines.sync.MutexImpl.LockWaiter
        public Object tryResumeLockWaiter() {
            return this.cont.tryResume(Unit.INSTANCE, null, new MutexImpl$LockCont$tryResumeLockWaiter$1(MutexImpl.this, this));
        }
    }

    /* compiled from: Mutex.kt */
    /* loaded from: classes.dex */
    public abstract class LockWaiter extends LockFreeLinkedListNode implements DisposableHandle {
        public final Object owner;

        public LockWaiter(MutexImpl mutexImpl, Object obj) {
            this.owner = obj;
        }

        public abstract void completeResumeLockWaiter(Object obj);

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            remove();
        }

        public abstract Object tryResumeLockWaiter();
    }

    /* compiled from: Mutex.kt */
    /* loaded from: classes.dex */
    public static final class LockedQueue extends LockFreeLinkedListHead {
        public Object owner;

        public LockedQueue(Object obj) {
            this.owner = obj;
        }

        @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("LockedQueue[");
            outline13.append(this.owner);
            outline13.append(']');
            return outline13.toString();
        }
    }

    /* compiled from: Mutex.kt */
    /* loaded from: classes.dex */
    public static final class UnlockOp extends AtomicOp<MutexImpl> {
        public final LockedQueue queue;

        public UnlockOp(LockedQueue lockedQueue) {
            this.queue = lockedQueue;
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public void complete(MutexImpl mutexImpl, Object obj) {
            MutexImpl._state$FU.compareAndSet(mutexImpl, this, obj == null ? MutexKt.EMPTY_UNLOCKED : this.queue);
        }

        @Override // kotlinx.coroutines.internal.AtomicOp
        public Object prepare(MutexImpl mutexImpl) {
            LockedQueue lockedQueue = this.queue;
            if (lockedQueue.getNext() == lockedQueue) {
                return null;
            }
            return MutexKt.UNLOCK_FAIL;
        }
    }

    public MutexImpl(boolean z) {
        this._state = z ? MutexKt.EMPTY_LOCKED : MutexKt.EMPTY_UNLOCKED;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0032, code lost:
        r10 = false;
     */
    @Override // kotlinx.coroutines.sync.Mutex
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object lock(java.lang.Object r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 266
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.MutexImpl.lock(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public String toString() {
        while (true) {
            Object obj = this._state;
            if (obj instanceof Empty) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Mutex[");
                outline13.append(((Empty) obj).locked);
                outline13.append(']');
                return outline13.toString();
            } else if (obj instanceof OpDescriptor) {
                ((OpDescriptor) obj).perform(this);
            } else if (obj instanceof LockedQueue) {
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Mutex[");
                outline132.append(((LockedQueue) obj).owner);
                outline132.append(']');
                return outline132.toString();
            } else {
                throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", obj).toString());
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public void unlock(Object obj) {
        LockFreeLinkedListNode lockFreeLinkedListNode;
        while (true) {
            Object obj2 = this._state;
            boolean z = true;
            if (obj2 instanceof Empty) {
                if (obj == null) {
                    if (((Empty) obj2).locked == MutexKt.UNLOCKED) {
                        z = false;
                    }
                    if (!z) {
                        throw new IllegalStateException("Mutex is not locked".toString());
                    }
                } else {
                    Empty empty = (Empty) obj2;
                    if (empty.locked != obj) {
                        z = false;
                    }
                    if (!z) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("Mutex is locked by ");
                        outline13.append(empty.locked);
                        outline13.append(" but expected ");
                        outline13.append(obj);
                        throw new IllegalStateException(outline13.toString().toString());
                    }
                }
                if (_state$FU.compareAndSet(this, obj2, MutexKt.EMPTY_UNLOCKED)) {
                    return;
                }
            } else if (obj2 instanceof OpDescriptor) {
                ((OpDescriptor) obj2).perform(this);
            } else if (obj2 instanceof LockedQueue) {
                if (obj != null) {
                    LockedQueue lockedQueue = (LockedQueue) obj2;
                    if (lockedQueue.owner != obj) {
                        z = false;
                    }
                    if (!z) {
                        StringBuilder outline132 = GeneratedOutlineSupport.outline13("Mutex is locked by ");
                        outline132.append(lockedQueue.owner);
                        outline132.append(" but expected ");
                        outline132.append(obj);
                        throw new IllegalStateException(outline132.toString().toString());
                    }
                }
                LockedQueue lockedQueue2 = (LockedQueue) obj2;
                while (true) {
                    lockFreeLinkedListNode = (LockFreeLinkedListNode) lockedQueue2.getNext();
                    if (lockFreeLinkedListNode == lockedQueue2) {
                        lockFreeLinkedListNode = null;
                        break;
                    } else if (lockFreeLinkedListNode.remove()) {
                        break;
                    } else {
                        lockFreeLinkedListNode.helpRemove();
                    }
                }
                if (lockFreeLinkedListNode == null) {
                    UnlockOp unlockOp = new UnlockOp(lockedQueue2);
                    if (_state$FU.compareAndSet(this, obj2, unlockOp) && unlockOp.perform(this) == null) {
                        return;
                    }
                } else {
                    LockWaiter lockWaiter = (LockWaiter) lockFreeLinkedListNode;
                    Object tryResumeLockWaiter = lockWaiter.tryResumeLockWaiter();
                    if (tryResumeLockWaiter != null) {
                        Object obj3 = lockWaiter.owner;
                        if (obj3 == null) {
                            obj3 = MutexKt.LOCKED;
                        }
                        lockedQueue2.owner = obj3;
                        lockWaiter.completeResumeLockWaiter(tryResumeLockWaiter);
                        return;
                    }
                }
            } else {
                throw new IllegalStateException(Intrinsics.stringPlus("Illegal state ", obj2).toString());
            }
        }
    }
}
