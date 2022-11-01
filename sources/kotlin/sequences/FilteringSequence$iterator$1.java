package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: Sequences.kt */
/* loaded from: classes.dex */
public final class FilteringSequence$iterator$1 implements Iterator<T>, KMappedMarker {
    public final Iterator<T> iterator;
    public T nextItem;
    public int nextState = -1;
    public final /* synthetic */ FilteringSequence this$0;

    public FilteringSequence$iterator$1(FilteringSequence filteringSequence) {
        this.this$0 = filteringSequence;
        this.iterator = filteringSequence.sequence.iterator();
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [T, java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void calcNext() {
        /*
            r3 = this;
        L_0x0000:
            java.util.Iterator<T> r0 = r3.iterator
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L_0x0028
            java.util.Iterator<T> r0 = r3.iterator
            java.lang.Object r0 = r0.next()
            kotlin.sequences.FilteringSequence r1 = r3.this$0
            kotlin.jvm.functions.Function1<T, java.lang.Boolean> r1 = r1.predicate
            java.lang.Object r1 = r1.invoke(r0)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            kotlin.sequences.FilteringSequence r2 = r3.this$0
            boolean r2 = r2.sendWhen
            if (r1 != r2) goto L_0x0000
            r3.nextItem = r0
            r0 = 1
            r3.nextState = r0
            return
        L_0x0028:
            r0 = 0
            r3.nextState = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.FilteringSequence$iterator$1.calcNext():void");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            T t = this.nextItem;
            this.nextItem = null;
            this.nextState = -1;
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
