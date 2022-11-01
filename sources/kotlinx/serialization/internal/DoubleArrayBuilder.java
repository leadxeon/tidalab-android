package kotlinx.serialization.internal;

import java.util.Arrays;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class DoubleArrayBuilder extends PrimitiveArrayBuilder<double[]> {
    public double[] buffer;
    public int position;

    public DoubleArrayBuilder(double[] dArr) {
        this.buffer = dArr;
        this.position = dArr.length;
        ensureCapacity$kotlinx_serialization_core(10);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public double[] build$kotlinx_serialization_core() {
        return Arrays.copyOf(this.buffer, this.position);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public void ensureCapacity$kotlinx_serialization_core(int i) {
        double[] dArr = this.buffer;
        if (dArr.length < i) {
            int length = dArr.length * 2;
            if (i < length) {
                i = length;
            }
            this.buffer = Arrays.copyOf(dArr, i);
        }
    }

    @Override // kotlinx.serialization.internal.PrimitiveArrayBuilder
    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }
}
