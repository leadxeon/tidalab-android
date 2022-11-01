package kotlin.collections;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: AbstractList.kt */
/* loaded from: classes.dex */
public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>, KMappedMarker {

    /* compiled from: AbstractList.kt */
    /* loaded from: classes.dex */
    public class IteratorImpl implements Iterator<E>, KMappedMarker {
        public int index;

        public IteratorImpl() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < AbstractList.this.getSize();
        }

        @Override // java.util.Iterator
        public E next() {
            if (hasNext()) {
                AbstractList abstractList = AbstractList.this;
                int i = this.index;
                this.index = i + 1;
                return (E) abstractList.get(i);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* compiled from: AbstractList.kt */
    /* loaded from: classes.dex */
    public class ListIteratorImpl extends AbstractList<E>.IteratorImpl implements ListIterator<E>, KMappedMarker {
        public ListIteratorImpl(int i) {
            super();
            int size = AbstractList.this.getSize();
            if (i < 0 || i > size) {
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline5("index: ", i, ", size: ", size));
            }
            this.index = i;
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override // java.util.ListIterator
        public boolean hasPrevious() {
            return this.index > 0;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public E previous() {
            if (hasPrevious()) {
                AbstractList abstractList = AbstractList.this;
                int i = this.index - 1;
                this.index = i;
                return (E) abstractList.get(i);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    /* compiled from: AbstractList.kt */
    /* loaded from: classes.dex */
    public static final class SubList<E> extends AbstractList<E> implements RandomAccess {
        public int _size;
        public final int fromIndex;
        public final AbstractList<E> list;

        /* JADX WARN: Multi-variable type inference failed */
        public SubList(AbstractList<? extends E> abstractList, int i, int i2) {
            this.list = abstractList;
            this.fromIndex = i;
            int size = abstractList.getSize();
            if (i < 0 || i2 > size) {
                throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + size);
            } else if (i <= i2) {
                this._size = i2 - i;
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("fromIndex: ", i, " > toIndex: ", i2));
            }
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public E get(int i) {
            int i2 = this._size;
            if (i >= 0 && i < i2) {
                return this.list.get(this.fromIndex + i);
            }
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline5("index: ", i, ", size: ", i2));
        }

        @Override // kotlin.collections.AbstractCollection
        public int getSize() {
            return this._size;
        }
    }

    @Override // java.util.List
    public void add(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Collection collection = (Collection) obj;
        if (size() != collection.size()) {
            return false;
        }
        Iterator<E> it = collection.iterator();
        for (E e : this) {
            if (!Intrinsics.areEqual(e, it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.List
    public abstract E get(int i);

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        Iterator<E> it = iterator();
        int i = 1;
        while (it.hasNext()) {
            E next = it.next();
            i = (i * 31) + (next != null ? next.hashCode() : 0);
        }
        return i;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Iterator<E> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next(), obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator<E> listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (Intrinsics.areEqual(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(0);
    }

    @Override // java.util.List
    public E remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public E set(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return new SubList(this, i, i2);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return new ListIteratorImpl(i);
    }
}
