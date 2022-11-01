package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: Iterables.kt */
/* loaded from: classes.dex */
public final class SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1 implements Iterable<T>, KMappedMarker {
    public final /* synthetic */ Sequence $this_asIterable$inlined;

    public SequencesKt___SequencesKt$asIterable$$inlined$Iterable$1(Sequence sequence) {
        this.$this_asIterable$inlined = sequence;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.$this_asIterable$inlined.iterator();
    }
}
