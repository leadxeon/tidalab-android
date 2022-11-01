package kotlinx.serialization.internal;

import java.util.Arrays;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class FloatArrayBuilder extends PrimitiveArrayBuilder<float[]> {
    public float[] buffer;
    public int position;

    public FloatArrayBuilder(float[] fArr) {
        this.buffer = fArr;
        this.position = fArr.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public float[] build$kotlinx_serialization_core() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        float[] fArr = this.buffer;
        if (fArr.length < i) {
            int length = fArr.length * 2;
            if (i < length) {
                i = length;
            }
            this.buffer = Arrays.copyOf(fArr, i);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }
}
