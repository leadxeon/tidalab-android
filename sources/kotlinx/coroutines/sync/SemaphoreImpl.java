package kotlinx.coroutines.sync;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Semaphore.kt */
/* loaded from: classes.dex */
public final class SemaphoreImpl implements Semaphore {
    public volatile /* synthetic */ int _availablePermits;
    private volatile /* synthetic */ long deqIdx = 0;
    private volatile /* synthetic */ long enqIdx = 0;
    private volatile /* synthetic */ Object head;
    public final Function1<Throwable, Unit> onCancellationRelease;
    public final int permits;
    private volatile /* synthetic */ Object tail;
    public static final /* synthetic */ AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "head");
    public static final /* synthetic */ AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    public static final /* synthetic */ AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
    public static final /* synthetic */ AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    public static final /* synthetic */ AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    public SemaphoreImpl(int i, int i2) {
        this.permits = i;
        boolean z = false;
        if (i > 0) {
            if (i2 >= 0 && i2 <= i) {
                z = true;
            }
            if (z) {
                SemaphoreSegment semaphoreSegment = new SemaphoreSegment(0L, null, 2);
                this.head = semaphoreSegment;
                this.tail = semaphoreSegment;
                this._availablePermits = i - i2;
                this.onCancellationRelease = new SemaphoreImpl$onCancellationRelease$1(this);
                return;
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("The number of acquired permits should be in 0..", Integer.valueOf(i)).toString());
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Semaphore should have at least 1 permit, but had ", Integer.valueOf(i)).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x006e, code lost:
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x006f, code lost:
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00c3 A[EDGE_INSN: B:64:0x00c3->B:48:0x00c3 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v12, types: [kotlinx.coroutines.internal.Segment] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // kotlinx.coroutines.sync.Semaphore
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object acquire(kotlin.coroutines.Continuation<? super kotlin.Unit> r17) {
        /*
            Method dump skipped, instructions count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.acquire(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v13, types: [kotlinx.coroutines.internal.Segment] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // kotlinx.coroutines.sync.Semaphore
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void release() {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.sync.SemaphoreImpl.release():void");
    }
}
