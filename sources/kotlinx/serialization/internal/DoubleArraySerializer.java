package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class DoubleArraySerializer extends PrimitiveArraySerializer<Double, double[], DoubleArrayBuilder> implements KSerializer<double[]> {
    public static final DoubleArraySerializer INSTANCE = new DoubleArraySerializer();

    public DoubleArraySerializer() {
        super(DoubleSerializer.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((double[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public double[] empty() {
        return new double[0];
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        DoubleArrayBuilder doubleArrayBuilder = (DoubleArrayBuilder) obj;
        double decodeDoubleElement = compositeDecoder.decodeDoubleElement(this.descriptor, i);
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(doubleArrayBuilder, 0, 1, null);
        double[] dArr = doubleArrayBuilder.buffer;
        int i2 = doubleArrayBuilder.position;
        doubleArrayBuilder.position = i2 + 1;
        dArr[i2] = decodeDoubleElement;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new DoubleArrayBuilder((double[]) obj);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(CompositeEncoder compositeEncoder, double[] dArr, int i) {
        double[] dArr2 = dArr;
        if (i > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                compositeEncoder.encodeDoubleElement(this.descriptor, i2, dArr2[i2]);
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }
}
