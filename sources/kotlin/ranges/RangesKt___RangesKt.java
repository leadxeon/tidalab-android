package kotlin.ranges;
/* compiled from: _Ranges.kt */
/* loaded from: classes.dex */
public class RangesKt___RangesKt {
    public static final float coerceAtLeast(float f, float f2) {
        return f < f2 ? f2 : f;
    }

    public static final int coerceIn(int i, int i2, int i3) {
        if (i2 <= i3) {
            return i < i2 ? i2 : i > i3 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i3 + " is less than minimum " + i2 + '.');
    }

    public static final IntRange until(int i, int i2) {
        if (i2 > Integer.MIN_VALUE) {
            return new IntRange(i, i2 - 1);
        }
        IntRange intRange = IntRange.Companion;
        return IntRange.EMPTY;
    }
}
