package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlinx.coroutines.internal.Segment;
/* compiled from: ConcurrentLinkedList.kt */
/* loaded from: classes.dex */
public abstract class Segment<S extends Segment<S>> extends ConcurrentLinkedListNode<S> {
    public static final /* synthetic */ AtomicIntegerFieldUpdater cleanedAndPointers$FU = AtomicIntegerFieldUpdater.newUpdater(Segment.class, "cleanedAndPointers");
    private volatile /* synthetic */ int cleanedAndPointers;
    public final long id;

    public Segment(long j, S s, int i) {
        super(s);
        this.id = j;
        this.cleanedAndPointers = i << 16;
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        return cleanedAndPointers$FU.addAndGet(this, -65536) == getMaxSlots() && !isTail();
    }

    public abstract int getMaxSlots();

    @Override // kotlinx.coroutines.internal.ConcurrentLinkedListNode
    public boolean getRemoved() {
        return this.cleanedAndPointers == getMaxSlots() && !isTail();
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        int i;
        do {
            i = this.cleanedAndPointers;
            if (!(i != getMaxSlots() || isTail())) {
                return false;
            }
        } while (!cleanedAndPointers$FU.compareAndSet(this, i, 65536 + i));
        return true;
    }
}
