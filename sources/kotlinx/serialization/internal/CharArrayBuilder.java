package kotlinx.serialization.internal;

import java.util.Arrays;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class CharArrayBuilder extends PrimitiveArrayBuilder<char[]> {
    public char[] buffer;
    public int position;

    public CharArrayBuilder(char[] cArr) {
        this.buffer = cArr;
        this.position = cArr.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public char[] build$kotlinx_serialization_core() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        char[] cArr = this.buffer;
        if (cArr.length < i) {
            int length = cArr.length * 2;
            if (i < length) {
                i = length;
            }
            this.buffer = Arrays.copyOf(cArr, i);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }
}
