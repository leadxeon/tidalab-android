package kotlinx.coroutines.sync;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Objects;
import kotlin.Unit;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.internal.Segment;
/* compiled from: Semaphore.kt */
/* loaded from: classes.dex */
public final class CancelSemaphoreAcquisitionHandler extends CancelHandler {
    public final int index;
    public final SemaphoreSegment segment;

    public CancelSemaphoreAcquisitionHandler(SemaphoreSegment semaphoreSegment, int i) {
        this.segment = semaphoreSegment;
        this.index = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("CancelSemaphoreAcquisitionHandler[");
        outline13.append(this.segment);
        outline13.append(", ");
        outline13.append(this.index);
        outline13.append(']');
        return outline13.toString();
    }

    @Override // kotlinx.coroutines.CancelHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(Throwable th) {
        SemaphoreSegment semaphoreSegment = this.segment;
        int i = this.index;
        Objects.requireNonNull(semaphoreSegment);
        semaphoreSegment.acquirers.set(i, SemaphoreKt.CANCELLED);
        if (Segment.cleanedAndPointers$FU.incrementAndGet(semaphoreSegment) == semaphoreSegment.getMaxSlots() && !semaphoreSegment.isTail()) {
            semaphoreSegment.remove();
        }
    }
}
