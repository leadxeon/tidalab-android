package kotlin.collections;

import java.util.Iterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: Iterables.kt */
/* loaded from: classes.dex */
public final class IndexingIterable<T> implements Iterable<IndexedValue<? extends T>>, KMappedMarker {
    public final Function0<Iterator<T>> iteratorFactory;

    /* JADX WARN: Multi-variable type inference failed */
    public IndexingIterable(Function0<? extends Iterator<? extends T>> function0) {
        this.iteratorFactory = function0;
    }

    @Override // java.lang.Iterable
    public Iterator<IndexedValue<T>> iterator() {
        return new IndexingIterator(this.iteratorFactory.invoke());
    }
}
