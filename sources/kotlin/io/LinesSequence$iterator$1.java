package kotlin.io;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: ReadWrite.kt */
/* loaded from: classes.dex */
public final class LinesSequence$iterator$1 implements Iterator<String>, KMappedMarker {
    public boolean done;
    public String nextValue;
    public final /* synthetic */ LinesSequence this$0;

    public LinesSequence$iterator$1(LinesSequence linesSequence) {
        this.this$0 = linesSequence;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextValue == null && !this.done) {
            String readLine = this.this$0.reader.readLine();
            this.nextValue = readLine;
            if (readLine == null) {
                this.done = true;
            }
        }
        return this.nextValue != null;
    }

    @Override // java.util.Iterator
    public String next() {
        if (hasNext()) {
            String str = this.nextValue;
            this.nextValue = null;
            return str;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
