package kotlinx.serialization.json.internal;

import java.util.Arrays;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
/* compiled from: JsonStringBuilder.kt */
/* loaded from: classes.dex */
public final class JsonStringBuilder {
    public char[] array;
    public int size;

    public JsonStringBuilder() {
        char[] cArr;
        synchronized (CharArrayPool.INSTANCE) {
            ArrayDeque<char[]> arrayDeque = CharArrayPool.arrays;
            cArr = null;
            char[] removeLast = arrayDeque.isEmpty() ? null : arrayDeque.removeLast();
            if (removeLast != null) {
                CharArrayPool.charsTotal -= removeLast.length;
                cArr = removeLast;
            }
        }
        this.array = cArr == null ? new char[128] : cArr;
    }

    public final void append(long j) {
        append(String.valueOf(j));
    }

    public final void ensureAdditionalCapacity(int i) {
        ensureTotalCapacity(this.size + i);
    }

    public final void ensureTotalCapacity(int i) {
        char[] cArr = this.array;
        if (cArr.length <= i) {
            int i2 = this.size * 2;
            if (i < i2) {
                i = i2;
            }
            this.array = Arrays.copyOf(cArr, i);
        }
    }

    public final void release() {
        CharArrayPool charArrayPool = CharArrayPool.INSTANCE;
        char[] cArr = this.array;
        synchronized (charArrayPool) {
            int i = CharArrayPool.charsTotal;
            if (cArr.length + i < CharArrayPool.MAX_CHARS_IN_POOL) {
                CharArrayPool.charsTotal = i + cArr.length;
                CharArrayPool.arrays.addLast(cArr);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    public String toString() {
        return new String(this.array, 0, this.size);
    }

    public final void append(String str) {
        int length = str.length();
        ensureAdditionalCapacity(length);
        str.getChars(0, str.length(), this.array, this.size);
        this.size += length;
    }
}
