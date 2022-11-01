package kotlin.ranges;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: Progressions.kt */
/* loaded from: classes.dex */
public class IntProgression implements Iterable<Integer>, KMappedMarker {
    public final int first;
    public final int last;
    public final int step;

    public IntProgression(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        } else if (i3 != Integer.MIN_VALUE) {
            this.first = i;
            if (i3 > 0) {
                if (i < i2) {
                    i2 -= InputKt.mod(InputKt.mod(i2, i3) - InputKt.mod(i, i3), i3);
                }
            } else if (i3 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            } else if (i > i2) {
                int i4 = -i3;
                i2 += InputKt.mod(InputKt.mod(i, i4) - InputKt.mod(i2, i4), i4);
            }
            this.last = i2;
            this.step = i3;
        } else {
            throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof IntProgression) {
            if (!isEmpty() || !((IntProgression) obj).isEmpty()) {
                IntProgression intProgression = (IntProgression) obj;
                if (!(this.first == intProgression.first && this.last == intProgression.last && this.step == intProgression.step)) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (((this.first * 31) + this.last) * 31) + this.step;
    }

    public boolean isEmpty() {
        if (this.step > 0) {
            if (this.first > this.last) {
                return true;
            }
        } else if (this.first < this.last) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Iterable
    public Iterator<Integer> iterator() {
        return new IntProgressionIterator(this.first, this.last, this.step);
    }

    public String toString() {
        int i;
        StringBuilder sb;
        if (this.step > 0) {
            sb = new StringBuilder();
            sb.append(this.first);
            sb.append("..");
            sb.append(this.last);
            sb.append(" step ");
            i = this.step;
        } else {
            sb = new StringBuilder();
            sb.append(this.first);
            sb.append(" downTo ");
            sb.append(this.last);
            sb.append(" step ");
            i = -this.step;
        }
        sb.append(i);
        return sb.toString();
    }
}
