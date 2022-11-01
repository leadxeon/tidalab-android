package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* compiled from: LockFreeTaskQueue.kt */
/* loaded from: classes.dex */
public class LockFreeTaskQueue<E> {
    public static final /* synthetic */ AtomicReferenceFieldUpdater _cur$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueue.class, Object.class, "_cur");
    private volatile /* synthetic */ Object _cur;

    public LockFreeTaskQueue(boolean z) {
        this._cur = new LockFreeTaskQueueCore(8, z);
    }

    public final boolean addLast(E e) {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            int addLast = lockFreeTaskQueueCore.addLast(e);
            if (addLast == 0) {
                return true;
            }
            if (addLast == 1) {
                _cur$FU.compareAndSet(this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
            } else if (addLast == 2) {
                return false;
            }
        }
    }

    public final void close() {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            if (!lockFreeTaskQueueCore.close()) {
                _cur$FU.compareAndSet(this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
            } else {
                return;
            }
        }
    }

    public final int getSize() {
        return ((LockFreeTaskQueueCore) this._cur).getSize();
    }

    public final E removeFirstOrNull() {
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._cur;
            E e = (E) lockFreeTaskQueueCore.removeFirstOrNull();
            if (e != LockFreeTaskQueueCore.REMOVE_FROZEN) {
                return e;
            }
            _cur$FU.compareAndSet(this, lockFreeTaskQueueCore, lockFreeTaskQueueCore.next());
        }
    }
}
