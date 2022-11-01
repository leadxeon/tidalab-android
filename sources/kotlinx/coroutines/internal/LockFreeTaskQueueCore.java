package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: LockFreeTaskQueue.kt */
/* loaded from: classes.dex */
public final class LockFreeTaskQueueCore<E> {
    public static final Companion Companion = new Companion(null);
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    public static final /* synthetic */ AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next");
    public static final /* synthetic */ AtomicLongFieldUpdater _state$FU = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state");
    private volatile /* synthetic */ Object _next = null;
    private volatile /* synthetic */ long _state = 0;
    public /* synthetic */ AtomicReferenceArray array;
    public final int capacity;
    public final int mask;
    public final boolean singleConsumer;

    /* compiled from: LockFreeTaskQueue.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    /* compiled from: LockFreeTaskQueue.kt */
    /* loaded from: classes.dex */
    public static final class Placeholder {
        public final int index;

        public Placeholder(int i) {
            this.index = i;
        }
    }

    public LockFreeTaskQueueCore(int i, boolean z) {
        this.capacity = i;
        this.singleConsumer = z;
        int i2 = i - 1;
        this.mask = i2;
        this.array = new AtomicReferenceArray(i);
        boolean z2 = false;
        if (i2 <= 1073741823) {
            if (!((i & i2) == 0 ? true : z2)) {
                throw new IllegalStateException("Check failed.".toString());
            }
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0051, code lost:
        return 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int addLast(E r16) {
        /*
            r15 = this;
            r6 = r15
            r7 = r16
        L_0x0003:
            long r2 = r6._state
            r0 = 3458764513820540928(0x3000000000000000, double:1.727233711018889E-77)
            long r0 = r0 & r2
            r8 = 0
            r4 = 1
            int r5 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r5 == 0) goto L_0x0018
            r0 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            long r0 = r0 & r2
            int r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r2 == 0) goto L_0x0017
            r4 = 2
        L_0x0017:
            return r4
        L_0x0018:
            r0 = 1073741823(0x3fffffff, double:5.304989472E-315)
            long r0 = r0 & r2
            r10 = 0
            long r0 = r0 >> r10
            int r1 = (int) r0
            r11 = 1152921503533105152(0xfffffffc0000000, double:1.2882296003504729E-231)
            long r11 = r11 & r2
            r0 = 30
            long r11 = r11 >> r0
            int r12 = (int) r11
            int r11 = r6.mask
            int r5 = r12 + 2
            r5 = r5 & r11
            r13 = r1 & r11
            if (r5 != r13) goto L_0x0033
            return r4
        L_0x0033:
            boolean r5 = r6.singleConsumer
            r13 = 1073741823(0x3fffffff, float:1.9999999)
            if (r5 != 0) goto L_0x0052
            java.util.concurrent.atomic.AtomicReferenceArray r5 = r6.array
            r14 = r12 & r11
            java.lang.Object r5 = r5.get(r14)
            if (r5 == 0) goto L_0x0052
            int r0 = r6.capacity
            r2 = 1024(0x400, float:1.435E-42)
            if (r0 < r2) goto L_0x0051
            int r12 = r12 - r1
            r1 = r12 & r13
            int r0 = r0 >> 1
            if (r1 <= r0) goto L_0x0003
        L_0x0051:
            return r4
        L_0x0052:
            int r1 = r12 + 1
            r1 = r1 & r13
            java.util.concurrent.atomic.AtomicLongFieldUpdater r4 = kotlinx.coroutines.internal.LockFreeTaskQueueCore._state$FU
            r13 = -1152921503533105153(0xf00000003fffffff, double:-3.1050369248997324E231)
            long r13 = r13 & r2
            long r8 = (long) r1
            long r0 = r8 << r0
            long r8 = r13 | r0
            r0 = r4
            r1 = r15
            r4 = r8
            boolean r0 = r0.compareAndSet(r1, r2, r4)
            if (r0 == 0) goto L_0x0003
            java.util.concurrent.atomic.AtomicReferenceArray r0 = r6.array
            r1 = r12 & r11
            r0.set(r1, r7)
            r0 = r6
        L_0x0073:
            long r1 = r0._state
            r3 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r1 = r1 & r3
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x007f
            goto L_0x00a2
        L_0x007f:
            kotlinx.coroutines.internal.LockFreeTaskQueueCore r0 = r0.next()
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r0.array
            int r2 = r0.mask
            r2 = r2 & r12
            java.lang.Object r1 = r1.get(r2)
            boolean r2 = r1 instanceof kotlinx.coroutines.internal.LockFreeTaskQueueCore.Placeholder
            if (r2 == 0) goto L_0x009f
            kotlinx.coroutines.internal.LockFreeTaskQueueCore$Placeholder r1 = (kotlinx.coroutines.internal.LockFreeTaskQueueCore.Placeholder) r1
            int r1 = r1.index
            if (r1 != r12) goto L_0x009f
            java.util.concurrent.atomic.AtomicReferenceArray r1 = r0.array
            int r2 = r0.mask
            r2 = r2 & r12
            r1.set(r2, r7)
            goto L_0x00a0
        L_0x009f:
            r0 = 0
        L_0x00a0:
            if (r0 != 0) goto L_0x0073
        L_0x00a2:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeTaskQueueCore.addLast(java.lang.Object):int");
    }

    public final boolean close() {
        long j;
        do {
            j = this._state;
            if ((j & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j) != 0) {
                return false;
            }
        } while (!_state$FU.compareAndSet(this, j, j | 2305843009213693952L));
        return true;
    }

    public final int getSize() {
        long j = this._state;
        return 1073741823 & (((int) ((j & 1152921503533105152L) >> 30)) - ((int) ((1073741823 & j) >> 0)));
    }

    public final boolean isEmpty() {
        long j = this._state;
        return ((int) ((1073741823 & j) >> 0)) == ((int) ((j & 1152921503533105152L) >> 30));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final LockFreeTaskQueueCore<E> next() {
        long j;
        while (true) {
            j = this._state;
            if ((j & 1152921504606846976L) == 0) {
                long j2 = j | 1152921504606846976L;
                if (_state$FU.compareAndSet(this, j, j2)) {
                    j = j2;
                    break;
                }
            } else {
                break;
            }
        }
        while (true) {
            LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = (LockFreeTaskQueueCore) this._next;
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
            LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
            int i = (int) ((1073741823 & j) >> 0);
            int i2 = (int) ((1152921503533105152L & j) >> 30);
            while (true) {
                int i3 = this.mask;
                int i4 = i & i3;
                if (i4 != (i3 & i2)) {
                    Object obj = this.array.get(i4);
                    if (obj == null) {
                        obj = new Placeholder(i);
                    }
                    lockFreeTaskQueueCore2.array.set(lockFreeTaskQueueCore2.mask & i, obj);
                    i++;
                }
            }
            lockFreeTaskQueueCore2._state = (-1152921504606846977L) & j;
            atomicReferenceFieldUpdater.compareAndSet(this, null, lockFreeTaskQueueCore2);
        }
    }

    public final Object removeFirstOrNull() {
        while (true) {
            long j = this._state;
            if ((j & 1152921504606846976L) != 0) {
                return REMOVE_FROZEN;
            }
            int i = (int) ((j & 1073741823) >> 0);
            int i2 = this.mask;
            int i3 = ((int) ((1152921503533105152L & j) >> 30)) & i2;
            int i4 = i2 & i;
            if (i3 == i4) {
                return null;
            }
            Object obj = this.array.get(i4);
            if (obj == null) {
                if (this.singleConsumer) {
                    return null;
                }
            } else if (obj instanceof Placeholder) {
                return null;
            } else {
                long j2 = ((i + 1) & 1073741823) << 0;
                if (_state$FU.compareAndSet(this, j, (j & (-1073741824)) | j2)) {
                    this.array.set(this.mask & i, null);
                    return obj;
                } else if (this.singleConsumer) {
                    LockFreeTaskQueueCore<E> lockFreeTaskQueueCore = this;
                    while (true) {
                        long j3 = lockFreeTaskQueueCore._state;
                        int i5 = (int) ((j3 & 1073741823) >> 0);
                        if ((j3 & 1152921504606846976L) != 0) {
                            lockFreeTaskQueueCore = lockFreeTaskQueueCore.next();
                        } else if (_state$FU.compareAndSet(lockFreeTaskQueueCore, j3, (j3 & (-1073741824)) | j2)) {
                            lockFreeTaskQueueCore.array.set(lockFreeTaskQueueCore.mask & i5, null);
                            lockFreeTaskQueueCore = null;
                        } else {
                            continue;
                        }
                        if (lockFreeTaskQueueCore == null) {
                            return obj;
                        }
                    }
                }
            }
        }
    }
}
