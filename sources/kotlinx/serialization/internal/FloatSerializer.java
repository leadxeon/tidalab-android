package kotlinx.serialization.internal;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: Primitives.kt */
/* loaded from: classes.dex */
public final class FloatSerializer implements KSerializer<Float> {
    public static final FloatSerializer INSTANCE = new FloatSerializer();
    public static final SerialDescriptor descriptor = new PrimitiveSerialDescriptor("kotlin.Float", PrimitiveKind.FLOAT.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return Float.valueOf(decoder.decodeFloat());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        encoder.encodeFloat(((Number) obj).floatValue());
    }
}
