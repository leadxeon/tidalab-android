package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: Iterators.kt */
/* loaded from: classes.dex */
public final class IndexingIterator<T> implements Iterator<IndexedValue<? extends T>>, KMappedMarker {
    public int index;
    public final Iterator<T> iterator;

    /* JADX WARN: Multi-variable type inference failed */
    public IndexingIterator(Iterator<? extends T> it) {
        this.iterator = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        int i = this.index;
        this.index = i + 1;
        if (i >= 0) {
            return new IndexedValue(i, this.iterator.next());
        }
        ArraysKt___ArraysKt.throwIndexOverflow();
        throw null;
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
