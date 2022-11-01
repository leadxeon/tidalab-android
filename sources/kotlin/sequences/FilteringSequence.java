package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
/* compiled from: Sequences.kt */
/* loaded from: classes.dex */
public final class FilteringSequence<T> implements Sequence<T> {
    public final Function1<T, Boolean> predicate;
    public final boolean sendWhen;
    public final Sequence<T> sequence;

    /* JADX WARN: Multi-variable type inference failed */
    public FilteringSequence(Sequence<? extends T> sequence, boolean z, Function1<? super T, Boolean> function1) {
        this.sequence = sequence;
        this.sendWhen = z;
        this.predicate = function1;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<T> iterator() {
        return new FilteringSequence$iterator$1(this);
    }
}
