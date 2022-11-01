package kotlinx.coroutines.internal;

import java.lang.Comparable;
import java.util.Arrays;
import kotlinx.coroutines.EventLoopImplBase;
import kotlinx.coroutines.internal.ThreadSafeHeapNode;
/* compiled from: ThreadSafeHeap.kt */
/* loaded from: classes.dex */
public class ThreadSafeHeap<T extends ThreadSafeHeapNode & Comparable<? super T>> {
    private volatile /* synthetic */ int _size = 0;
    public T[] a;

    public final void addImpl(T t) {
        EventLoopImplBase.DelayedTask delayedTask = (EventLoopImplBase.DelayedTask) t;
        delayedTask.setHeap(this);
        T[] tArr = this.a;
        if (tArr == null) {
            tArr = (T[]) new ThreadSafeHeapNode[4];
            this.a = tArr;
        } else if (this._size >= tArr.length) {
            tArr = (T[]) ((ThreadSafeHeapNode[]) Arrays.copyOf(tArr, this._size * 2));
            this.a = tArr;
        }
        int i = this._size;
        this._size = i + 1;
        tArr[i] = t;
        delayedTask.index = i;
        siftUpFrom(i);
    }

    public final T firstImpl() {
        T[] tArr = this.a;
        if (tArr == null) {
            return null;
        }
        return tArr[0];
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final T removeAtImpl(int i) {
        T[] tArr = this.a;
        this._size--;
        if (i < this._size) {
            swap(i, this._size);
            int i2 = (i - 1) / 2;
            if (i <= 0 || ((Comparable) tArr[i]).compareTo(tArr[i2]) >= 0) {
                while (true) {
                    int i3 = (i * 2) + 1;
                    if (i3 >= this._size) {
                        break;
                    }
                    T[] tArr2 = this.a;
                    int i4 = i3 + 1;
                    if (i4 < this._size && ((Comparable) tArr2[i4]).compareTo(tArr2[i3]) < 0) {
                        i3 = i4;
                    }
                    if (((Comparable) tArr2[i]).compareTo(tArr2[i3]) <= 0) {
                        break;
                    }
                    swap(i, i3);
                    i = i3;
                }
            } else {
                swap(i, i2);
                siftUpFrom(i2);
            }
        }
        T t = tArr[this._size];
        t.setHeap(null);
        t.setIndex(-1);
        tArr[this._size] = null;
        return t;
    }

    public final T removeFirstOrNull() {
        T removeAtImpl;
        synchronized (this) {
            removeAtImpl = this._size > 0 ? removeAtImpl(0) : null;
        }
        return removeAtImpl;
    }

    public final void siftUpFrom(int i) {
        while (i > 0) {
            T[] tArr = this.a;
            int i2 = (i - 1) / 2;
            if (((Comparable) tArr[i2]).compareTo(tArr[i]) > 0) {
                swap(i, i2);
                i = i2;
            } else {
                return;
            }
        }
    }

    public final void swap(int i, int i2) {
        T[] tArr = this.a;
        T t = tArr[i2];
        T t2 = tArr[i];
        tArr[i] = t;
        tArr[i2] = t2;
        t.setIndex(i);
        t2.setIndex(i2);
    }
}
