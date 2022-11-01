package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class ShortArraySerializer extends PrimitiveArraySerializer<Short, short[], ShortArrayBuilder> implements KSerializer<short[]> {
    public static final ShortArraySerializer INSTANCE = new ShortArraySerializer();

    public ShortArraySerializer() {
        super(ShortSerializer.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((short[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public short[] empty() {
        return new short[0];
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        ShortArrayBuilder shortArrayBuilder = (ShortArrayBuilder) obj;
        short decodeShortElement = compositeDecoder.decodeShortElement(this.descriptor, i);
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(shortArrayBuilder, 0, 1, null);
        short[] sArr = shortArrayBuilder.buffer;
        int i2 = shortArrayBuilder.position;
        shortArrayBuilder.position = i2 + 1;
        sArr[i2] = decodeShortElement;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new ShortArrayBuilder((short[]) obj);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(CompositeEncoder compositeEncoder, short[] sArr, int i) {
        short[] sArr2 = sArr;
        if (i > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                compositeEncoder.encodeShortElement(this.descriptor, i2, sArr2[i2]);
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }
}
