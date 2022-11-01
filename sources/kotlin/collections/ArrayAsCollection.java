package kotlin.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: Collections.kt */
/* loaded from: classes.dex */
public final class ArrayAsCollection<T> implements Collection<T>, KMappedMarker {
    public final boolean isVarargs;
    public final T[] values;

    public ArrayAsCollection(T[] tArr, boolean z) {
        this.values = tArr;
        this.isVarargs = z;
    }

    @Override // java.util.Collection
    public boolean add(T t) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return ArraysKt___ArraysKt.indexOf(this.values, obj) >= 0;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<? extends Object> collection) {
        if (collection.isEmpty()) {
            return true;
        }
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.values.length == 0;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<T> iterator() {
        return new ArrayIterator(this.values);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public final int size() {
        return this.values.length;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        T[] tArr = this.values;
        return (!this.isVarargs || !Intrinsics.areEqual(tArr.getClass(), Object[].class)) ? Arrays.copyOf(tArr, tArr.length, Object[].class) : tArr;
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }
}
