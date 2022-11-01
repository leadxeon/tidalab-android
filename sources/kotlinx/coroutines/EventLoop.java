package kotlinx.coroutines;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.coroutines.internal.ArrayQueue;
/* compiled from: EventLoop.common.kt */
/* loaded from: classes.dex */
public abstract class EventLoop extends CoroutineDispatcher {
    public static final /* synthetic */ int $r8$clinit = 0;
    public boolean shared;
    public ArrayQueue<DispatchedTask<?>> unconfinedQueue;
    public long useCount;

    public final void decrementUseCount(boolean z) {
        long delta = this.useCount - delta(z);
        this.useCount = delta;
        if (delta <= 0 && this.shared) {
            shutdown();
        }
    }

    public final long delta(boolean z) {
        return z ? 4294967296L : 1L;
    }

    public final void dispatchUnconfined(DispatchedTask<?> dispatchedTask) {
        ArrayQueue<DispatchedTask<?>> arrayQueue = this.unconfinedQueue;
        if (arrayQueue == null) {
            arrayQueue = new ArrayQueue<>();
            this.unconfinedQueue = arrayQueue;
        }
        Object[] objArr = arrayQueue.elements;
        int i = arrayQueue.tail;
        objArr[i] = dispatchedTask;
        int length = (objArr.length - 1) & (i + 1);
        arrayQueue.tail = length;
        int i2 = arrayQueue.head;
        if (length == i2) {
            int length2 = objArr.length;
            Object[] objArr2 = new Object[length2 << 1];
            InputKt.copyInto$default(objArr, objArr2, 0, i2, 0, 10);
            Object[] objArr3 = arrayQueue.elements;
            int length3 = objArr3.length;
            int i3 = arrayQueue.head;
            InputKt.copyInto$default(objArr3, objArr2, length3 - i3, 0, i3, 4);
            arrayQueue.elements = objArr2;
            arrayQueue.head = 0;
            arrayQueue.tail = length2;
        }
    }

    public final void incrementUseCount(boolean z) {
        this.useCount = delta(z) + this.useCount;
        if (!z) {
            this.shared = true;
        }
    }

    public final boolean isUnconfinedLoopActive() {
        return this.useCount >= delta(true);
    }

    public long processNextEvent() {
        return !processUnconfinedEvent() ? Long.MAX_VALUE : 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean processUnconfinedEvent() {
        /*
            r7 = this;
            kotlinx.coroutines.internal.ArrayQueue<kotlinx.coroutines.DispatchedTask<?>> r0 = r7.unconfinedQueue
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r2 = r0.head
            int r3 = r0.tail
            r4 = 0
            r5 = 1
            if (r2 != r3) goto L_0x000f
            goto L_0x0022
        L_0x000f:
            java.lang.Object[] r3 = r0.elements
            r6 = r3[r2]
            r3[r2] = r4
            int r2 = r2 + r5
            int r3 = r3.length
            int r3 = r3 + (-1)
            r2 = r2 & r3
            r0.head = r2
            java.lang.String r0 = "null cannot be cast to non-null type T of kotlinx.coroutines.internal.ArrayQueue"
            java.util.Objects.requireNonNull(r6, r0)
            r4 = r6
        L_0x0022:
            kotlinx.coroutines.DispatchedTask r4 = (kotlinx.coroutines.DispatchedTask) r4
            if (r4 != 0) goto L_0x0027
            return r1
        L_0x0027:
            r4.run()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.EventLoop.processUnconfinedEvent():boolean");
    }

    public void shutdown() {
    }
}
