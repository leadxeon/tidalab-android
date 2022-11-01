package kotlin.collections;

import java.util.Iterator;
import kotlin.sequences.Sequence;
/* compiled from: Sequences.kt */
/* loaded from: classes.dex */
public final class CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1 implements Sequence<T> {
    public final /* synthetic */ Iterable $this_asSequence$inlined;

    public CollectionsKt___CollectionsKt$asSequence$$inlined$Sequence$1(Iterable iterable) {
        this.$this_asSequence$inlined = iterable;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return this.$this_asSequence$inlined.iterator();
    }
}
