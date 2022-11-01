package kotlin.sequences;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: _Sequences.kt */
/* loaded from: classes.dex */
public final class SequencesKt___SequencesKt$filterNotNull$1 extends Lambda implements Function1<T, Boolean> {
    public static final SequencesKt___SequencesKt$filterNotNull$1 INSTANCE = new SequencesKt___SequencesKt$filterNotNull$1();

    public SequencesKt___SequencesKt$filterNotNull$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public Boolean invoke(Object obj) {
        return Boolean.valueOf(obj == null);
    }
}
