package kotlinx.serialization.internal;

import java.util.Arrays;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class IntArrayBuilder extends PrimitiveArrayBuilder<int[]> {
    public int[] buffer;
    public int position;

    public IntArrayBuilder(int[] iArr) {
        this.buffer = iArr;
        this.position = iArr.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int[] build$kotlinx_serialization_core() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        int[] iArr = this.buffer;
        if (iArr.length < i) {
            int length = iArr.length * 2;
            if (i < length) {
                i = length;
            }
            this.buffer = Arrays.copyOf(iArr, i);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }
}
