package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class BooleanArraySerializer extends PrimitiveArraySerializer<Boolean, boolean[], BooleanArrayBuilder> implements KSerializer<boolean[]> {
    public static final BooleanArraySerializer INSTANCE = new BooleanArraySerializer();

    public BooleanArraySerializer() {
        super(BooleanSerializer.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((boolean[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public boolean[] empty() {
        return new boolean[0];
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        BooleanArrayBuilder booleanArrayBuilder = (BooleanArrayBuilder) obj;
        boolean decodeBooleanElement = compositeDecoder.decodeBooleanElement(this.descriptor, i);
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(booleanArrayBuilder, 0, 1, null);
        boolean[] zArr = booleanArrayBuilder.buffer;
        int i2 = booleanArrayBuilder.position;
        booleanArrayBuilder.position = i2 + 1;
        zArr[i2] = decodeBooleanElement;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new BooleanArrayBuilder((boolean[]) obj);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(CompositeEncoder compositeEncoder, boolean[] zArr, int i) {
        boolean[] zArr2 = zArr;
        if (i > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                compositeEncoder.encodeBooleanElement(this.descriptor, i2, zArr2[i2]);
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }
}
