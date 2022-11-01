package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: Primitives.kt */
/* loaded from: classes.dex */
public final class ByteSerializer implements KSerializer<Byte> {
    public static final ByteSerializer INSTANCE = new ByteSerializer();
    public static final SerialDescriptor descriptor = new PrimitiveSerialDescriptor("kotlin.Byte", PrimitiveKind.BYTE.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return Byte.valueOf(decoder.decodeByte());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        encoder.encodeByte(((Number) obj).byteValue());
    }
}
