package kotlin.collections;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.AbstractList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMutableCollection;
/* compiled from: AbstractMutableList.kt */
/* loaded from: classes.dex */
public abstract class AbstractMutableList<E> extends AbstractList<E> implements List<E>, KMutableCollection {
    @Override // java.util.AbstractList, java.util.List
    public final E remove(int i) {
        E e;
        ArrayDeque arrayDeque = (ArrayDeque) this;
        int size = arrayDeque.size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport.outline5("index: ", i, ", size: ", size));
        } else if (i == ArraysKt___ArraysKt.getLastIndex(arrayDeque)) {
            return (E) arrayDeque.removeLast();
        } else {
            if (i != 0) {
                int i2 = arrayDeque.head + i;
                Object[] objArr = arrayDeque.elementData;
                if (i2 >= objArr.length) {
                    i2 -= objArr.length;
                }
                e = (E) objArr[i2];
                if (i < (arrayDeque.size() >> 1)) {
                    int i3 = arrayDeque.head;
                    if (i2 >= i3) {
                        Object[] objArr2 = arrayDeque.elementData;
                        System.arraycopy(objArr2, i3, objArr2, i3 + 1, i2 - i3);
                    } else {
                        Object[] objArr3 = arrayDeque.elementData;
                        System.arraycopy(objArr3, 0, objArr3, 1, i2 - 0);
                        Object[] objArr4 = arrayDeque.elementData;
                        objArr4[0] = objArr4[objArr4.length - 1];
                        int i4 = arrayDeque.head;
                        System.arraycopy(objArr4, i4, objArr4, i4 + 1, (objArr4.length - 1) - i4);
                    }
                    Object[] objArr5 = arrayDeque.elementData;
                    int i5 = arrayDeque.head;
                    objArr5[i5] = null;
                    arrayDeque.head = arrayDeque.incremented(i5);
                } else {
                    int lastIndex = arrayDeque.head + ArraysKt___ArraysKt.getLastIndex(arrayDeque);
                    Object[] objArr6 = arrayDeque.elementData;
                    if (lastIndex >= objArr6.length) {
                        lastIndex -= objArr6.length;
                    }
                    if (i2 <= lastIndex) {
                        int i6 = i2 + 1;
                        System.arraycopy(objArr6, i6, objArr6, i2, (lastIndex + 1) - i6);
                    } else {
                        int i7 = i2 + 1;
                        System.arraycopy(objArr6, i7, objArr6, i2, objArr6.length - i7);
                        Object[] objArr7 = arrayDeque.elementData;
                        objArr7[objArr7.length - 1] = objArr7[0];
                        System.arraycopy(objArr7, 1, objArr7, 0, (lastIndex + 1) - 1);
                    }
                    arrayDeque.elementData[lastIndex] = null;
                }
                arrayDeque.size = arrayDeque.size() - 1;
            } else if (!arrayDeque.isEmpty()) {
                int i8 = arrayDeque.head;
                Object[] objArr8 = arrayDeque.elementData;
                e = (E) objArr8[i8];
                objArr8[i8] = null;
                arrayDeque.head = arrayDeque.incremented(i8);
                arrayDeque.size = arrayDeque.size() - 1;
            } else {
                throw new NoSuchElementException("ArrayDeque is empty.");
            }
            return e;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return ((ArrayDeque) this).size;
    }
}
