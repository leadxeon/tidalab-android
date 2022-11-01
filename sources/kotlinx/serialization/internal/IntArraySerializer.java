package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class IntArraySerializer extends PrimitiveArraySerializer<Integer, int[], IntArrayBuilder> implements KSerializer<int[]> {
    public static final IntArraySerializer INSTANCE = new IntArraySerializer();

    public IntArraySerializer() {
        super(IntSerializer.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((int[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public int[] empty() {
        return new int[0];
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        IntArrayBuilder intArrayBuilder = (IntArrayBuilder) obj;
        int decodeIntElement = compositeDecoder.decodeIntElement(this.descriptor, i);
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(intArrayBuilder, 0, 1, null);
        int[] iArr = intArrayBuilder.buffer;
        int i2 = intArrayBuilder.position;
        intArrayBuilder.position = i2 + 1;
        iArr[i2] = decodeIntElement;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new IntArrayBuilder((int[]) obj);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(CompositeEncoder compositeEncoder, int[] iArr, int i) {
        int[] iArr2 = iArr;
        if (i > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                compositeEncoder.encodeIntElement(this.descriptor, i2, iArr2[i2]);
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }
}
