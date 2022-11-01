package kotlinx.serialization.internal;

import java.util.Arrays;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class ShortArrayBuilder extends PrimitiveArrayBuilder<short[]> {
    public short[] buffer;
    public int position;

    public ShortArrayBuilder(short[] sArr) {
        this.buffer = sArr;
        this.position = sArr.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public short[] build$kotlinx_serialization_core() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        short[] sArr = this.buffer;
        if (sArr.length < i) {
            int length = sArr.length * 2;
            if (i < length) {
                i = length;
            }
            this.buffer = Arrays.copyOf(sArr, i);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }
}
