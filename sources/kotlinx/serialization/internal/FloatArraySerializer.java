package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class FloatArraySerializer extends PrimitiveArraySerializer<Float, float[], FloatArrayBuilder> implements KSerializer<float[]> {
    public static final FloatArraySerializer INSTANCE = new FloatArraySerializer();

    public FloatArraySerializer() {
        super(FloatSerializer.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((float[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public float[] empty() {
        return new float[0];
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        FloatArrayBuilder floatArrayBuilder = (FloatArrayBuilder) obj;
        float decodeFloatElement = compositeDecoder.decodeFloatElement(this.descriptor, i);
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(floatArrayBuilder, 0, 1, null);
        float[] fArr = floatArrayBuilder.buffer;
        int i2 = floatArrayBuilder.position;
        floatArrayBuilder.position = i2 + 1;
        fArr[i2] = decodeFloatElement;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new FloatArrayBuilder((float[]) obj);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(CompositeEncoder compositeEncoder, float[] fArr, int i) {
        float[] fArr2 = fArr;
        if (i > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                compositeEncoder.encodeFloatElement(this.descriptor, i2, fArr2[i2]);
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }
}
