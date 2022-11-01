package kotlinx.coroutines.sync;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.concurrent.atomic.AtomicReferenceArray;
import kotlinx.coroutines.internal.Segment;
/* compiled from: Semaphore.kt */
/* loaded from: classes.dex */
public final class SemaphoreSegment extends Segment<SemaphoreSegment> {
    public /* synthetic */ AtomicReferenceArray acquirers = new AtomicReferenceArray(SemaphoreKt.SEGMENT_SIZE);

    public SemaphoreSegment(long j, SemaphoreSegment semaphoreSegment, int i) {
        super(j, semaphoreSegment, i);
    }

    @Override // kotlinx.coroutines.internal.Segment
    public int getMaxSlots() {
        return SemaphoreKt.SEGMENT_SIZE;
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("SemaphoreSegment[id=");
        outline13.append(this.id);
        outline13.append(", hashCode=");
        outline13.append(hashCode());
        outline13.append(']');
        return outline13.toString();
    }
}
