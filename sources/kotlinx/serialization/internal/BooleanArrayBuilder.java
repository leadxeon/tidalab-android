package kotlinx.serialization.internal;

import java.util.Arrays;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class BooleanArrayBuilder extends PrimitiveArrayBuilder<boolean[]> {
    public boolean[] buffer;
    public int position;

    public BooleanArrayBuilder(boolean[] zArr) {
        this.buffer = zArr;
        this.position = zArr.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public boolean[] build$kotlinx_serialization_core() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        boolean[] zArr = this.buffer;
        if (zArr.length < i) {
            int length = zArr.length * 2;
            if (i < length) {
                i = length;
            }
            this.buffer = Arrays.copyOf(zArr, i);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }
}
