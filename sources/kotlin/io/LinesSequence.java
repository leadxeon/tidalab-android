package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import kotlin.sequences.Sequence;
/* compiled from: ReadWrite.kt */
/* loaded from: classes.dex */
public final class LinesSequence implements Sequence<String> {
    public final BufferedReader reader;

    public LinesSequence(BufferedReader bufferedReader) {
        this.reader = bufferedReader;
    }

    @Override // kotlin.sequences.Sequence
    public Iterator<String> iterator() {
        return new LinesSequence$iterator$1(this);
    }
}
