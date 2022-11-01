package kotlin.sequences;

import java.util.Iterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: _Sequences.kt */
/* loaded from: classes.dex */
public final /* synthetic */ class SequencesKt___SequencesKt$flatMap$2 extends FunctionReferenceImpl implements Function1<Sequence<? extends R>, Iterator<? extends R>> {
    public static final SequencesKt___SequencesKt$flatMap$2 INSTANCE = new SequencesKt___SequencesKt$flatMap$2();

    public SequencesKt___SequencesKt$flatMap$2() {
        super(1, Sequence.class, "iterator", "iterator()Ljava/util/Iterator;", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public Object invoke(Object obj) {
        return ((Sequence) obj).iterator();
    }
}
