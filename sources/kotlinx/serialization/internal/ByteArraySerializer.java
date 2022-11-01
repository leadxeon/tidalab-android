package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
/* compiled from: PrimitiveArraysSerializers.kt */
/* loaded from: classes.dex */
public final class ByteArraySerializer extends PrimitiveArraySerializer<Byte, byte[], ByteArrayBuilder> implements KSerializer<byte[]> {
    public static final ByteArraySerializer INSTANCE = new ByteArraySerializer();

    public ByteArraySerializer() {
        super(ByteSerializer.INSTANCE);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((byte[]) obj).length;
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public byte[] empty() {
        return new byte[0];
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.internal.AbstractCollectionSerializer
    public void readElement(CompositeDecoder compositeDecoder, int i, Object obj, boolean z) {
        ByteArrayBuilder byteArrayBuilder = (ByteArrayBuilder) obj;
        byte decodeByteElement = compositeDecoder.decodeByteElement(this.descriptor, i);
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(byteArrayBuilder, 0, 1, null);
        byte[] bArr = byteArrayBuilder.buffer;
        int i2 = byteArrayBuilder.position;
        byteArrayBuilder.position = i2 + 1;
        bArr[i2] = decodeByteElement;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        return new ByteArrayBuilder((byte[]) obj);
    }

    @Override // kotlinx.serialization.internal.PrimitiveArraySerializer
    public void writeContent(CompositeEncoder compositeEncoder, byte[] bArr, int i) {
        byte[] bArr2 = bArr;
        if (i > 0) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                compositeEncoder.encodeByteElement(this.descriptor, i2, bArr2[i2]);
                if (i3 < i) {
                    i2 = i3;
                } else {
                    return;
                }
            }
        }
    }
}
