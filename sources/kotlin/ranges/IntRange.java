package kotlin.ranges;
/* compiled from: Ranges.kt */
/* loaded from: classes.dex */
public final class IntRange extends IntProgression {
    public static final IntRange Companion = null;
    public static final IntRange EMPTY = new IntRange(1, 0);

    public IntRange(int i, int i2) {
        super(i, i2, 1);
    }

    @Override // kotlin.ranges.IntProgression
    public boolean equals(Object obj) {
        if (obj instanceof IntRange) {
            if (!isEmpty() || !((IntRange) obj).isEmpty()) {
                IntRange intRange = (IntRange) obj;
                if (!(this.first == intRange.first && this.last == intRange.last)) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // kotlin.ranges.IntProgression
    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        return (this.first * 31) + this.last;
    }

    @Override // kotlin.ranges.IntProgression
    public boolean isEmpty() {
        return this.first > this.last;
    }

    @Override // kotlin.ranges.IntProgression
    public String toString() {
        return this.first + ".." + this.last;
    }
}
