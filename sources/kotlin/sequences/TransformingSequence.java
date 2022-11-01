package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
/* compiled from: Sequences.kt */
/* loaded from: classes.dex */
public final class TransformingSequence<T, R> implements Sequence<R> {
    public final Sequence<T> sequence;
    public final Function1<T, R> transformer;

    /* JADX WARN: Multi-variable type inference failed */
    public TransformingSequence(Sequence<? extends T> sequence, Function1<? super T, ? extends R> function1) {
        this.sequence = sequence;
        this.transformer = function1;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<R> iterator() {
        return new TransformingSequence$iterator$1(this);
    }
}
