package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: ArrayIterator.kt */
/* loaded from: classes.dex */
public final class ArrayIterator<T> implements Iterator<T>, KMappedMarker {
    public final T[] array;
    public int index;

    public ArrayIterator(T[] tArr) {
        this.array = tArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.index < this.array.length;
    }

    @Override // java.util.Iterator
    public T next() {
        try {
            T[] tArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return tArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
