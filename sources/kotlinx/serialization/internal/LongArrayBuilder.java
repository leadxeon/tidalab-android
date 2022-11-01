package kotlinx.serialization.internal;

import java.util.Arrays;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class LongArrayBuilder extends PrimitiveArrayBuilder<long[]> {
    public long[] buffer;
    public int position;

    public LongArrayBuilder(long[] jArr) {
        this.buffer = jArr;
        this.position = jArr.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public long[] build$kotlinx_serialization_core() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        long[] jArr = this.buffer;
        if (jArr.length < i) {
            int length = jArr.length * 2;
            if (i < length) {
                i = length;
            }
            this.buffer = Arrays.copyOf(jArr, i);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }
}
