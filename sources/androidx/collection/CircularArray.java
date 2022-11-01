package androidx.collection;
/* loaded from: classes.dex */
public final class CircularArray<E> {
    public int mCapacityBitmask;
    public E[] mElements;
    public int mHead;
    public int mTail;

    public CircularArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        } else if (i <= 1073741824) {
            i = Integer.bitCount(i) != 1 ? Integer.highestOneBit(i - 1) << 1 : i;
            this.mCapacityBitmask = i - 1;
            this.mElements = (E[]) new Object[i];
        } else {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
    }

    public void addLast(E e) {
        E[] eArr = this.mElements;
        int i = this.mTail;
        eArr[i] = e;
        int i2 = this.mCapacityBitmask & (i + 1);
        this.mTail = i2;
        int i3 = this.mHead;
        if (i2 == i3) {
            int length = eArr.length;
            int i4 = length - i3;
            int i5 = length << 1;
            if (i5 >= 0) {
                E[] eArr2 = (E[]) new Object[i5];
                System.arraycopy(eArr, i3, eArr2, 0, i4);
                System.arraycopy(this.mElements, 0, eArr2, i4, this.mHead);
                this.mElements = eArr2;
                this.mHead = 0;
                this.mTail = length;
                this.mCapacityBitmask = i5 - 1;
                return;
            }
            throw new RuntimeException("Max array capacity exceeded");
        }
    }

    public void removeFromStart(int i) {
        if (i > 0) {
            if (i <= size()) {
                int length = this.mElements.length;
                int i2 = this.mHead;
                if (i < length - i2) {
                    length = i2 + i;
                }
                while (i2 < length) {
                    this.mElements[i2] = null;
                    i2++;
                }
                int i3 = this.mHead;
                int i4 = length - i3;
                int i5 = i - i4;
                this.mHead = this.mCapacityBitmask & (i3 + i4);
                if (i5 > 0) {
                    for (int i6 = 0; i6 < i5; i6++) {
                        this.mElements[i6] = null;
                    }
                    this.mHead = i5;
                    return;
                }
                return;
            }
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int size() {
        return (this.mTail - this.mHead) & this.mCapacityBitmask;
    }
}
