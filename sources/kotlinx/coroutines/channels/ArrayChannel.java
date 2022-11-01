package kotlinx.coroutines.channels;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.internal.UndeliveredElementException;
/* compiled from: ArrayChannel.kt */
/* loaded from: classes.dex */
public class ArrayChannel<E> extends AbstractChannel<E> {
    public Object[] buffer;
    public final int capacity;
    public int head;
    public final ReentrantLock lock;
    public final BufferOverflow onBufferOverflow;
    private volatile /* synthetic */ int size;

    public ArrayChannel(int i, BufferOverflow bufferOverflow, Function1<? super E, Unit> function1) {
        super(function1);
        this.capacity = i;
        this.onBufferOverflow = bufferOverflow;
        if (i < 1 ? false : true) {
            this.lock = new ReentrantLock();
            int min = Math.min(i, 8);
            Object[] objArr = new Object[min];
            Arrays.fill(objArr, 0, min, AbstractChannelKt.EMPTY);
            Unit unit = Unit.INSTANCE;
            this.buffer = objArr;
            this.size = 0;
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline4("ArrayChannel capacity must be at least 1, but ", i, " was specified").toString());
    }

    public final void enqueueElement(int i, E e) {
        int i2 = this.capacity;
        if (i < i2) {
            Object[] objArr = this.buffer;
            if (i >= objArr.length) {
                int min = Math.min(objArr.length * 2, i2);
                Object[] objArr2 = new Object[min];
                if (i > 0) {
                    int i3 = 0;
                    while (true) {
                        int i4 = i3 + 1;
                        Object[] objArr3 = this.buffer;
                        objArr2[i3] = objArr3[(this.head + i3) % objArr3.length];
                        if (i4 >= i) {
                            break;
                        }
                        i3 = i4;
                    }
                }
                Arrays.fill(objArr2, i, min, AbstractChannelKt.EMPTY);
                this.buffer = objArr2;
                this.head = 0;
            }
            Object[] objArr4 = this.buffer;
            objArr4[(this.head + i) % objArr4.length] = e;
            return;
        }
        Object[] objArr5 = this.buffer;
        int i5 = this.head;
        objArr5[i5 % objArr5.length] = null;
        objArr5[(i + i5) % objArr5.length] = e;
        this.head = (i5 + 1) % objArr5.length;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean enqueueReceiveInternal(Receive<? super E> receive) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueReceiveInternal(receive);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public Object enqueueSend(Send send) {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.enqueueSend(send);
        } finally {
            reentrantLock.unlock();
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public String getBufferDebugString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("(buffer:capacity=");
        outline13.append(this.capacity);
        outline13.append(",size=");
        outline13.append(this.size);
        outline13.append(')');
        return outline13.toString();
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferAlwaysEmpty() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferAlwaysFull() {
        return false;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public final boolean isBufferEmpty() {
        return this.size == 0;
    }

    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    public final boolean isBufferFull() {
        return this.size == this.capacity && this.onBufferOverflow == BufferOverflow.SUSPEND;
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public boolean isClosedForReceive() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            return super.isClosedForReceive();
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0065 A[DONT_GENERATE] */
    @Override // kotlinx.coroutines.channels.AbstractSendChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object offerInternal(E r6) {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r5.lock
            r0.lock()
            int r1 = r5.size     // Catch: all -> 0x006d
            kotlinx.coroutines.channels.Closed r2 = r5.getClosedForSend()     // Catch: all -> 0x006d
            if (r2 != 0) goto L_0x0069
            int r2 = r5.capacity     // Catch: all -> 0x006d
            r3 = 1
            r4 = 0
            if (r1 >= r2) goto L_0x0018
            int r2 = r1 + 1
            r5.size = r2     // Catch: all -> 0x006d
            goto L_0x002e
        L_0x0018:
            kotlinx.coroutines.channels.BufferOverflow r2 = r5.onBufferOverflow     // Catch: all -> 0x006d
            int r2 = r2.ordinal()     // Catch: all -> 0x006d
            if (r2 == 0) goto L_0x0030
            if (r2 == r3) goto L_0x002e
            r3 = 2
            if (r2 != r3) goto L_0x0028
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: all -> 0x006d
            goto L_0x0032
        L_0x0028:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException     // Catch: all -> 0x006d
            r6.<init>()     // Catch: all -> 0x006d
            throw r6     // Catch: all -> 0x006d
        L_0x002e:
            r2 = r4
            goto L_0x0032
        L_0x0030:
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_FAILED     // Catch: all -> 0x006d
        L_0x0032:
            if (r2 != 0) goto L_0x0065
            if (r1 != 0) goto L_0x005c
        L_0x0036:
            kotlinx.coroutines.channels.ReceiveOrClosed r2 = r5.takeFirstReceiveOrPeekClosed()     // Catch: all -> 0x006d
            if (r2 != 0) goto L_0x003d
            goto L_0x005c
        L_0x003d:
            boolean r3 = r2 instanceof kotlinx.coroutines.channels.Closed     // Catch: all -> 0x006d
            if (r3 == 0) goto L_0x0047
            r5.size = r1     // Catch: all -> 0x006d
            r0.unlock()
            return r2
        L_0x0047:
            kotlinx.coroutines.internal.Symbol r3 = r2.tryResumeReceive(r6, r4)     // Catch: all -> 0x006d
            if (r3 == 0) goto L_0x0036
            r5.size = r1     // Catch: all -> 0x006d
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: all -> 0x006d
            r0.unlock()
            r2.completeResumeReceive(r6)
            java.lang.Object r6 = r2.getOfferResult()
            return r6
        L_0x005c:
            r5.enqueueElement(r1, r6)     // Catch: all -> 0x006d
            kotlinx.coroutines.internal.Symbol r6 = kotlinx.coroutines.channels.AbstractChannelKt.OFFER_SUCCESS     // Catch: all -> 0x006d
            r0.unlock()
            return r6
        L_0x0065:
            r0.unlock()
            return r2
        L_0x0069:
            r0.unlock()
            return r2
        L_0x006d:
            r6 = move-exception
            r0.unlock()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.offerInternal(java.lang.Object):java.lang.Object");
    }

    /* JADX WARN: Finally extract failed */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    public void onCancelIdempotent(boolean z) {
        Function1<E, Unit> function1 = this.onUndeliveredElement;
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            UndeliveredElementException undeliveredElementException = null;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = this.buffer[this.head];
                if (!(function1 == null || obj == AbstractChannelKt.EMPTY)) {
                    undeliveredElementException = InputKt.callUndeliveredElementCatchingException(function1, obj, undeliveredElementException);
                }
                Object[] objArr = this.buffer;
                int i3 = this.head;
                objArr[i3] = AbstractChannelKt.EMPTY;
                this.head = (i3 + 1) % objArr.length;
            }
            this.size = 0;
            Unit unit = Unit.INSTANCE;
            reentrantLock.unlock();
            super.onCancelIdempotent(z);
            if (undeliveredElementException != null) {
                throw undeliveredElementException;
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    @Override // kotlinx.coroutines.channels.AbstractChannel
    public Object pollInternal() {
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            int i = this.size;
            if (i == 0) {
                Object closedForSend = getClosedForSend();
                if (closedForSend == null) {
                    closedForSend = AbstractChannelKt.POLL_FAILED;
                }
                return closedForSend;
            }
            Object[] objArr = this.buffer;
            int i2 = this.head;
            Object obj = objArr[i2];
            Send send = null;
            objArr[i2] = null;
            this.size = i - 1;
            Object obj2 = AbstractChannelKt.POLL_FAILED;
            boolean z = false;
            if (i == this.capacity) {
                send = null;
                while (true) {
                    Send takeFirstSendOrPeekClosed = takeFirstSendOrPeekClosed();
                    if (takeFirstSendOrPeekClosed == null) {
                        break;
                    } else if (takeFirstSendOrPeekClosed.tryResumeSend(null) != null) {
                        obj2 = takeFirstSendOrPeekClosed.getPollResult();
                        send = takeFirstSendOrPeekClosed;
                        z = true;
                        break;
                    } else {
                        takeFirstSendOrPeekClosed.undeliveredElement();
                        send = takeFirstSendOrPeekClosed;
                    }
                }
            }
            if (obj2 != AbstractChannelKt.POLL_FAILED && !(obj2 instanceof Closed)) {
                this.size = i;
                Object[] objArr2 = this.buffer;
                objArr2[(this.head + i) % objArr2.length] = obj2;
            }
            this.head = (this.head + 1) % this.buffer.length;
            Unit unit = Unit.INSTANCE;
            if (z) {
                send.completeResumeSend();
            }
            return obj;
        } finally {
            reentrantLock.unlock();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0093 A[Catch: all -> 0x00b9, TRY_LEAVE, TryCatch #0 {all -> 0x00b9, blocks: (B:3:0x0005, B:5:0x0009, B:7:0x000f, B:10:0x0015, B:12:0x0029, B:14:0x0036, B:15:0x0044, B:18:0x0049, B:21:0x004e, B:23:0x0054, B:26:0x0060, B:30:0x0068, B:31:0x0077, B:33:0x0079, B:35:0x007d, B:37:0x0081, B:38:0x008d, B:40:0x0093, B:43:0x00a3), top: B:51:0x0005 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b3  */
    @Override // kotlinx.coroutines.channels.AbstractChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object pollSelectInternal(kotlinx.coroutines.selects.SelectInstance<?> r9) {
        /*
            r8 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r8.lock
            r0.lock()
            int r1 = r8.size     // Catch: all -> 0x00b9
            if (r1 != 0) goto L_0x0015
            kotlinx.coroutines.channels.Closed r9 = r8.getClosedForSend()     // Catch: all -> 0x00b9
            if (r9 != 0) goto L_0x0011
            kotlinx.coroutines.internal.Symbol r9 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: all -> 0x00b9
        L_0x0011:
            r0.unlock()
            return r9
        L_0x0015:
            java.lang.Object[] r2 = r8.buffer     // Catch: all -> 0x00b9
            int r3 = r8.head     // Catch: all -> 0x00b9
            r4 = r2[r3]     // Catch: all -> 0x00b9
            r5 = 0
            r2[r3] = r5     // Catch: all -> 0x00b9
            int r2 = r1 + (-1)
            r8.size = r2     // Catch: all -> 0x00b9
            kotlinx.coroutines.internal.Symbol r2 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: all -> 0x00b9
            int r3 = r8.capacity     // Catch: all -> 0x00b9
            r6 = 1
            if (r1 != r3) goto L_0x0078
        L_0x0029:
            kotlinx.coroutines.channels.AbstractChannel$TryPollDesc r3 = new kotlinx.coroutines.channels.AbstractChannel$TryPollDesc     // Catch: all -> 0x00b9
            kotlinx.coroutines.internal.LockFreeLinkedListHead r7 = r8.queue     // Catch: all -> 0x00b9
            r3.<init>(r7)     // Catch: all -> 0x00b9
            java.lang.Object r7 = r9.performAtomicTrySelect(r3)     // Catch: all -> 0x00b9
            if (r7 != 0) goto L_0x0044
            java.lang.Object r2 = r3.getResult()     // Catch: all -> 0x00b9
            r3 = r2
            kotlinx.coroutines.channels.Send r3 = (kotlinx.coroutines.channels.Send) r3     // Catch: all -> 0x00b9
            java.lang.Object r3 = r3.getPollResult()     // Catch: all -> 0x00b9
            r5 = r2
            r2 = r3
            goto L_0x0066
        L_0x0044:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: all -> 0x00b9
            if (r7 != r3) goto L_0x0049
            goto L_0x0078
        L_0x0049:
            java.lang.Object r3 = kotlinx.coroutines.internal.AtomicKt.RETRY_ATOMIC     // Catch: all -> 0x00b9
            if (r7 != r3) goto L_0x004e
            goto L_0x0029
        L_0x004e:
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.NOT_SELECTED     // Catch: all -> 0x00b9
            java.lang.Object r2 = kotlinx.coroutines.selects.SelectKt.ALREADY_SELECTED     // Catch: all -> 0x00b9
            if (r7 != r2) goto L_0x0060
            r8.size = r1     // Catch: all -> 0x00b9
            java.lang.Object[] r9 = r8.buffer     // Catch: all -> 0x00b9
            int r1 = r8.head     // Catch: all -> 0x00b9
            r9[r1] = r4     // Catch: all -> 0x00b9
            r0.unlock()
            return r7
        L_0x0060:
            boolean r2 = r7 instanceof kotlinx.coroutines.channels.Closed     // Catch: all -> 0x00b9
            if (r2 == 0) goto L_0x0068
            r2 = r7
            r5 = r2
        L_0x0066:
            r3 = 1
            goto L_0x0079
        L_0x0068:
            java.lang.String r9 = "performAtomicTrySelect(describeTryOffer) returned "
            java.lang.String r9 = kotlin.jvm.internal.Intrinsics.stringPlus(r9, r7)     // Catch: all -> 0x00b9
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: all -> 0x00b9
            java.lang.String r9 = r9.toString()     // Catch: all -> 0x00b9
            r1.<init>(r9)     // Catch: all -> 0x00b9
            throw r1     // Catch: all -> 0x00b9
        L_0x0078:
            r3 = 0
        L_0x0079:
            kotlinx.coroutines.internal.Symbol r7 = kotlinx.coroutines.channels.AbstractChannelKt.POLL_FAILED     // Catch: all -> 0x00b9
            if (r2 == r7) goto L_0x008d
            boolean r7 = r2 instanceof kotlinx.coroutines.channels.Closed     // Catch: all -> 0x00b9
            if (r7 != 0) goto L_0x008d
            r8.size = r1     // Catch: all -> 0x00b9
            java.lang.Object[] r9 = r8.buffer     // Catch: all -> 0x00b9
            int r7 = r8.head     // Catch: all -> 0x00b9
            int r7 = r7 + r1
            int r1 = r9.length     // Catch: all -> 0x00b9
            int r7 = r7 % r1
            r9[r7] = r2     // Catch: all -> 0x00b9
            goto L_0x00a3
        L_0x008d:
            boolean r9 = r9.trySelect()     // Catch: all -> 0x00b9
            if (r9 != 0) goto L_0x00a3
            r8.size = r1     // Catch: all -> 0x00b9
            java.lang.Object[] r9 = r8.buffer     // Catch: all -> 0x00b9
            int r1 = r8.head     // Catch: all -> 0x00b9
            r9[r1] = r4     // Catch: all -> 0x00b9
            java.lang.Object r9 = kotlinx.coroutines.selects.SelectKt.NOT_SELECTED     // Catch: all -> 0x00b9
            java.lang.Object r9 = kotlinx.coroutines.selects.SelectKt.ALREADY_SELECTED     // Catch: all -> 0x00b9
            r0.unlock()
            return r9
        L_0x00a3:
            int r9 = r8.head     // Catch: all -> 0x00b9
            int r9 = r9 + r6
            java.lang.Object[] r1 = r8.buffer     // Catch: all -> 0x00b9
            int r1 = r1.length     // Catch: all -> 0x00b9
            int r9 = r9 % r1
            r8.head = r9     // Catch: all -> 0x00b9
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: all -> 0x00b9
            r0.unlock()
            if (r3 == 0) goto L_0x00b8
            kotlinx.coroutines.channels.Send r5 = (kotlinx.coroutines.channels.Send) r5
            r5.completeResumeSend()
        L_0x00b8:
            return r4
        L_0x00b9:
            r9 = move-exception
            r0.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayChannel.pollSelectInternal(kotlinx.coroutines.selects.SelectInstance):java.lang.Object");
    }
}
