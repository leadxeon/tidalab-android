package kotlin.collections;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ArrayDeque.kt */
/* loaded from: classes.dex */
public final class ArrayDeque<E> extends AbstractMutableList<E> {
    public static final Object[] emptyElementData = new Object[0];
    public Object[] elementData = emptyElementData;
    public int head;
    public int size;

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        ensureCapacity(collection.size() + size());
        int size = this.head + size();
        Object[] objArr = this.elementData;
        if (size >= objArr.length) {
            size -= objArr.length;
        }
        copyCollectionElements(size, collection);
        return true;
    }

    public final void addLast(E e) {
        ensureCapacity(size() + 1);
        Object[] objArr = this.elementData;
        int size = this.head + size();
        Object[] objArr2 = this.elementData;
        if (size >= objArr2.length) {
            size -= objArr2.length;
        }
        objArr[size] = e;
        this.size = size() + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        int size = size();
        int i = this.head;
        int i2 = size + i;
        Object[] objArr = this.elementData;
        if (i2 >= objArr.length) {
            i2 -= objArr.length;
        }
        if (i < i2) {
            Arrays.fill(objArr, i, i2, (Object) null);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.elementData;
            Arrays.fill(objArr2, this.head, objArr2.length, (Object) null);
            Arrays.fill(this.elementData, 0, i2, (Object) null);
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void copyCollectionElements(int i, Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        int length = this.elementData.length;
        while (i < length && it.hasNext()) {
            this.elementData[i] = it.next();
            i++;
        }
        int i2 = this.head;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.elementData[i3] = it.next();
        }
        this.size = collection.size() + size();
    }

    public final int decremented(int i) {
        return i == 0 ? this.elementData.length - 1 : i - 1;
    }

    public final void ensureCapacity(int i) {
        if (i >= 0) {
            Object[] objArr = this.elementData;
            if (i > objArr.length) {
                if (objArr == emptyElementData) {
                    if (i < 10) {
                        i = 10;
                    }
                    this.elementData = new Object[i];
                    return;
                }
                int length = objArr.length;
                int i2 = length + (length >> 1);
                if (i2 - i < 0) {
                    i2 = i;
                }
                if (i2 - 2147483639 > 0) {
                    i2 = i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
                }
                Object[] objArr2 = new Object[i2];
                int i3 = this.head;
                System.arraycopy(objArr, i3, objArr2, 0, objArr.length - i3);
                Object[] objArr3 = this.elementData;
                int length2 = objArr3.length;
                int i4 = this.head;
                System.arraycopy(objArr3, 0, objArr2, length2 - i4, i4 - 0);
                this.head = 0;
                this.elementData = objArr2;
                return;
            }
            return;
        }
        throw new IllegalStateException("Deque is too big.");
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        int size = size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline5("index: ", i, ", size: ", size));
        }
        int i2 = this.head + i;
        Object[] objArr = this.elementData;
        if (i2 >= objArr.length) {
            i2 -= objArr.length;
        }
        return (E) objArr[i2];
    }

    public final int incremented(int i) {
        if (i == this.elementData.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        int i;
        int size = size();
        int i2 = this.head;
        int i3 = size + i2;
        Object[] objArr = this.elementData;
        if (i3 >= objArr.length) {
            i3 -= objArr.length;
        }
        if (i2 < i3) {
            while (i2 < i3) {
                if (Intrinsics.areEqual(obj, this.elementData[i2])) {
                    i = this.head;
                } else {
                    i2++;
                }
            }
            return -1;
        } else if (i2 < i3) {
            return -1;
        } else {
            int length = objArr.length;
            while (true) {
                if (i2 >= length) {
                    for (int i4 = 0; i4 < i3; i4++) {
                        if (Intrinsics.areEqual(obj, this.elementData[i4])) {
                            i2 = i4 + this.elementData.length;
                            i = this.head;
                        }
                    }
                    return -1;
                } else if (Intrinsics.areEqual(obj, this.elementData[i2])) {
                    i = this.head;
                    break;
                } else {
                    i2++;
                }
            }
        }
        return i2 - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public int lastIndexOf(Object obj) {
        int length;
        int i;
        int size = size();
        int i2 = this.head;
        int i3 = size + i2;
        Object[] objArr = this.elementData;
        if (i3 >= objArr.length) {
            i3 -= objArr.length;
        }
        if (i2 < i3) {
            length = i3 - 1;
            if (length >= i2) {
                while (!Intrinsics.areEqual(obj, this.elementData[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.head;
                return length - i;
            }
            return -1;
        }
        if (i2 > i3) {
            int i4 = i3 - 1;
            while (true) {
                if (i4 < 0) {
                    length = this.elementData.length - 1;
                    int i5 = this.head;
                    if (length >= i5) {
                        while (!Intrinsics.areEqual(obj, this.elementData[length])) {
                            if (length != i5) {
                                length--;
                            }
                        }
                        i = this.head;
                    }
                } else if (Intrinsics.areEqual(obj, this.elementData[i4])) {
                    length = i4 + this.elementData.length;
                    i = this.head;
                    break;
                } else {
                    i4--;
                }
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean removeAll(Collection<? extends Object> collection) {
        int i;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int size = size();
                int i2 = this.head;
                int i3 = size + i2;
                Object[] objArr = this.elementData;
                if (i3 >= objArr.length) {
                    i3 -= objArr.length;
                }
                if (i2 < i3) {
                    i = i2;
                    while (i2 < i3) {
                        Object obj = this.elementData[i2];
                        if (!collection.contains(obj)) {
                            i++;
                            this.elementData[i] = obj;
                        } else {
                            z = true;
                        }
                        i2++;
                    }
                    Arrays.fill(this.elementData, i, i3, (Object) null);
                } else {
                    int length = objArr.length;
                    i = i2;
                    boolean z2 = false;
                    while (i2 < length) {
                        Object[] objArr2 = this.elementData;
                        Object obj2 = objArr2[i2];
                        objArr2[i2] = null;
                        if (!collection.contains(obj2)) {
                            i++;
                            this.elementData[i] = obj2;
                        } else {
                            z2 = true;
                        }
                        i2++;
                    }
                    Object[] objArr3 = this.elementData;
                    if (i >= objArr3.length) {
                        i -= objArr3.length;
                    }
                    for (int i4 = 0; i4 < i3; i4++) {
                        Object[] objArr4 = this.elementData;
                        Object obj3 = objArr4[i4];
                        objArr4[i4] = null;
                        if (!collection.contains(obj3)) {
                            this.elementData[i] = obj3;
                            i = incremented(i);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    int i5 = i - this.head;
                    if (i5 < 0) {
                        i5 += this.elementData.length;
                    }
                    this.size = i5;
                }
            }
        }
        return z;
    }

    public final E removeLast() {
        if (!isEmpty()) {
            int lastIndex = this.head + ArraysKt___ArraysKt.getLastIndex(this);
            Object[] objArr = this.elementData;
            if (lastIndex >= objArr.length) {
                lastIndex -= objArr.length;
            }
            E e = (E) objArr[lastIndex];
            objArr[lastIndex] = null;
            this.size = size() - 1;
            return e;
        }
        throw new NoSuchElementException("ArrayDeque is empty.");
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean retainAll(Collection<? extends Object> collection) {
        int i;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty()) {
            if (!(this.elementData.length == 0)) {
                int size = size();
                int i2 = this.head;
                int i3 = size + i2;
                Object[] objArr = this.elementData;
                if (i3 >= objArr.length) {
                    i3 -= objArr.length;
                }
                if (i2 < i3) {
                    i = i2;
                    while (i2 < i3) {
                        Object obj = this.elementData[i2];
                        if (collection.contains(obj)) {
                            i++;
                            this.elementData[i] = obj;
                        } else {
                            z = true;
                        }
                        i2++;
                    }
                    Arrays.fill(this.elementData, i, i3, (Object) null);
                } else {
                    int length = objArr.length;
                    i = i2;
                    boolean z2 = false;
                    while (i2 < length) {
                        Object[] objArr2 = this.elementData;
                        Object obj2 = objArr2[i2];
                        objArr2[i2] = null;
                        if (collection.contains(obj2)) {
                            i++;
                            this.elementData[i] = obj2;
                        } else {
                            z2 = true;
                        }
                        i2++;
                    }
                    Object[] objArr3 = this.elementData;
                    if (i >= objArr3.length) {
                        i -= objArr3.length;
                    }
                    for (int i4 = 0; i4 < i3; i4++) {
                        Object[] objArr4 = this.elementData;
                        Object obj3 = objArr4[i4];
                        objArr4[i4] = null;
                        if (collection.contains(obj3)) {
                            this.elementData[i] = obj3;
                            i = incremented(i);
                        } else {
                            z2 = true;
                        }
                    }
                    z = z2;
                }
                if (z) {
                    int i5 = i - this.head;
                    if (i5 < 0) {
                        i5 += this.elementData.length;
                    }
                    this.size = i5;
                }
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        int size = size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline5("index: ", i, ", size: ", size));
        }
        int i2 = this.head + i;
        Object[] objArr = this.elementData;
        if (i2 >= objArr.length) {
            i2 -= objArr.length;
        }
        E e2 = (E) objArr[i2];
        objArr[i2] = e;
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < size()) {
            Object newInstance = Array.newInstance(tArr.getClass().getComponentType(), size());
            Objects.requireNonNull(newInstance, "null cannot be cast to non-null type kotlin.Array<T>");
            tArr = (T[]) ((Object[]) newInstance);
        }
        int size = size();
        int i = this.head;
        int i2 = size + i;
        Object[] objArr = this.elementData;
        if (i2 >= objArr.length) {
            i2 -= objArr.length;
        }
        if (i < i2) {
            InputKt.copyInto$default(objArr, tArr, 0, i, i2, 2);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.elementData;
            int i3 = this.head;
            System.arraycopy(objArr2, i3, tArr, 0, objArr2.length - i3);
            Object[] objArr3 = this.elementData;
            System.arraycopy(objArr3, 0, tArr, objArr3.length - this.head, i2 - 0);
        }
        if (tArr.length > size()) {
            tArr[size()] = null;
        }
        return tArr;
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        int size = size();
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline5("index: ", i, ", size: ", size));
        } else if (i == size()) {
            addLast(e);
        } else if (i == 0) {
            ensureCapacity(size() + 1);
            int decremented = decremented(this.head);
            this.head = decremented;
            this.elementData[decremented] = e;
            this.size = size() + 1;
        } else {
            ensureCapacity(size() + 1);
            int i2 = this.head + i;
            Object[] objArr = this.elementData;
            if (i2 >= objArr.length) {
                i2 -= objArr.length;
            }
            if (i < ((size() + 1) >> 1)) {
                int decremented2 = decremented(i2);
                int decremented3 = decremented(this.head);
                int i3 = this.head;
                if (decremented2 >= i3) {
                    Object[] objArr2 = this.elementData;
                    objArr2[decremented3] = objArr2[i3];
                    int i4 = i3 + 1;
                    System.arraycopy(objArr2, i4, objArr2, i3, (decremented2 + 1) - i4);
                } else {
                    Object[] objArr3 = this.elementData;
                    System.arraycopy(objArr3, i3, objArr3, i3 - 1, objArr3.length - i3);
                    Object[] objArr4 = this.elementData;
                    objArr4[objArr4.length - 1] = objArr4[0];
                    System.arraycopy(objArr4, 1, objArr4, 0, (decremented2 + 1) - 1);
                }
                this.elementData[decremented2] = e;
                this.head = decremented3;
            } else {
                int size2 = this.head + size();
                Object[] objArr5 = this.elementData;
                if (size2 >= objArr5.length) {
                    size2 -= objArr5.length;
                }
                if (i2 < size2) {
                    System.arraycopy(objArr5, i2, objArr5, i2 + 1, size2 - i2);
                } else {
                    System.arraycopy(objArr5, 0, objArr5, 1, size2 - 0);
                    Object[] objArr6 = this.elementData;
                    objArr6[0] = objArr6[objArr6.length - 1];
                    System.arraycopy(objArr6, i2, objArr6, i2 + 1, (objArr6.length - 1) - i2);
                }
                this.elementData[i2] = e;
            }
            this.size = size() + 1;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        int size = size();
        if (i < 0 || i > size) {
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline5("index: ", i, ", size: ", size));
        } else if (collection.isEmpty()) {
            return false;
        } else {
            if (i == size()) {
                return addAll(collection);
            }
            ensureCapacity(collection.size() + size());
            int size2 = size();
            int i2 = this.head;
            int i3 = size2 + i2;
            Object[] objArr = this.elementData;
            if (i3 >= objArr.length) {
                i3 -= objArr.length;
            }
            int i4 = i2 + i;
            if (i4 >= objArr.length) {
                i4 -= objArr.length;
            }
            int size3 = collection.size();
            if (i < ((size() + 1) >> 1)) {
                int i5 = this.head;
                int i6 = i5 - size3;
                if (i4 < i5) {
                    Object[] objArr2 = this.elementData;
                    System.arraycopy(objArr2, i5, objArr2, i6, objArr2.length - i5);
                    if (size3 >= i4) {
                        Object[] objArr3 = this.elementData;
                        System.arraycopy(objArr3, 0, objArr3, objArr3.length - size3, i4 + 0);
                    } else {
                        Object[] objArr4 = this.elementData;
                        System.arraycopy(objArr4, 0, objArr4, objArr4.length - size3, size3 + 0);
                        Object[] objArr5 = this.elementData;
                        System.arraycopy(objArr5, size3, objArr5, 0, i4 - size3);
                    }
                } else if (i6 >= 0) {
                    Object[] objArr6 = this.elementData;
                    System.arraycopy(objArr6, i5, objArr6, i6, i4 - i5);
                } else {
                    Object[] objArr7 = this.elementData;
                    i6 += objArr7.length;
                    int i7 = i4 - i5;
                    int length = objArr7.length - i6;
                    if (length >= i7) {
                        System.arraycopy(objArr7, i5, objArr7, i6, i7);
                    } else {
                        System.arraycopy(objArr7, i5, objArr7, i6, (i5 + length) - i5);
                        Object[] objArr8 = this.elementData;
                        int i8 = this.head + length;
                        System.arraycopy(objArr8, i8, objArr8, 0, i4 - i8);
                    }
                }
                this.head = i6;
                int i9 = i4 - size3;
                if (i9 < 0) {
                    i9 += this.elementData.length;
                }
                copyCollectionElements(i9, collection);
            } else {
                int i10 = i4 + size3;
                if (i4 < i3) {
                    int i11 = size3 + i3;
                    Object[] objArr9 = this.elementData;
                    if (i11 <= objArr9.length) {
                        System.arraycopy(objArr9, i4, objArr9, i10, i3 - i4);
                    } else if (i10 >= objArr9.length) {
                        System.arraycopy(objArr9, i4, objArr9, i10 - objArr9.length, i3 - i4);
                    } else {
                        int length2 = i3 - (i11 - objArr9.length);
                        System.arraycopy(objArr9, length2, objArr9, 0, i3 - length2);
                        Object[] objArr10 = this.elementData;
                        System.arraycopy(objArr10, i4, objArr10, i10, length2 - i4);
                    }
                } else {
                    Object[] objArr11 = this.elementData;
                    System.arraycopy(objArr11, 0, objArr11, size3, i3 - 0);
                    Object[] objArr12 = this.elementData;
                    if (i10 >= objArr12.length) {
                        System.arraycopy(objArr12, i4, objArr12, i10 - objArr12.length, objArr12.length - i4);
                    } else {
                        int length3 = objArr12.length - size3;
                        System.arraycopy(objArr12, length3, objArr12, 0, objArr12.length - length3);
                        Object[] objArr13 = this.elementData;
                        System.arraycopy(objArr13, i4, objArr13, i10, (objArr13.length - size3) - i4);
                    }
                }
                copyCollectionElements(i4, collection);
            }
            return true;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }
}
