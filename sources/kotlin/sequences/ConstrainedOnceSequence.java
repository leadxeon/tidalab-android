package kotlin.sequences;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: SequencesJVM.kt */
/* loaded from: classes.dex */
public final class ConstrainedOnceSequence<T> implements Sequence<T> {
    public final AtomicReference<Sequence<T>> sequenceRef;

    public ConstrainedOnceSequence(Sequence<? extends T> sequence) {
        this.sequenceRef = new AtomicReference<>(sequence);
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        Sequence<T> andSet = this.sequenceRef.getAndSet(null);
        if (andSet != null) {
            return andSet.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}
