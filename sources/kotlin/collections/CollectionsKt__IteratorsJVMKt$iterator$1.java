package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: IteratorsJVM.kt */
/* loaded from: classes.dex */
public final class CollectionsKt__IteratorsJVMKt$iterator$1 implements Iterator<T>, KMappedMarker {
    public final /* synthetic */ Enumeration $this_iterator;

    public CollectionsKt__IteratorsJVMKt$iterator$1(Enumeration<T> enumeration) {
        this.$this_iterator = enumeration;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.$this_iterator.hasMoreElements();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
    @Override // java.util.Iterator
    public T next() {
        return this.$this_iterator.nextElement();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
