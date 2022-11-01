package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
/* compiled from: Sequences.kt */
/* loaded from: classes.dex */
public final class FlatteningSequence<T, R, E> implements Sequence<E> {
    public final Sequence<T> sequence;
    public final Function1<T, R> transformer;

    /* JADX WARN: Multi-variable type inference failed */
    public FlatteningSequence(Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1, Function1<? super R, ? extends Iterator<? extends E>> function12) {
        this.sequence = sequence;
        this.transformer = function1;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<E> iterator() {
        return new FlatteningSequence$iterator$1(this);
    }
}
