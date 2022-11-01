package kotlin.sequences;

import java.util.Iterator;
import kotlin.collections.EmptyIterator;
/* compiled from: Sequences.kt */
/* loaded from: classes.dex */
public final class EmptySequence implements Sequence {
    public static final EmptySequence INSTANCE = new EmptySequence();

    @Override // kotlin.sequences.Sequence
    public Iterator iterator() {
        return EmptyIterator.INSTANCE;
    }
}
