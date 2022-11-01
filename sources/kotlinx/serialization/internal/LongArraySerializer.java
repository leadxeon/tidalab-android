package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class LongArraySerializer extends PrimitiveArraySerializer<Long, long[], LongArrayBuilder> implements KSerializer<long[]> {
    public static final LongArraySerializer INSTANCE = new LongArraySerializer();

    public LongArraySerializer() {
        super(LongSerializer.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((long[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public long[] empty() {
        return new long[0];
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        LongArrayBuilder longArrayBuilder = (LongArrayBuilder) obj;
        long decodeLongElement = compositeDecoder.decodeLongElement(this.descriptor, i);
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(longArrayBuilder, 0, 1, null);
        long[] jArr = longArrayBuilder.buffer;
        int i2 = longArrayBuilder.position;
        longArrayBuilder.position = i2 + 1;
        jArr[i2] = decodeLongElement;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new LongArrayBuilder((long[]) obj);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(CompositeEncoder compositeEncoder, long[] jArr, int i) {
        long[] jArr2 = jArr;
        if (i > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                compositeEncoder.encodeLongElement(this.descriptor, i2, jArr2[i2]);
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }
}
