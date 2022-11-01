package kotlinx.serialization.encoding;

import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: Decoding.kt */
/* loaded from: classes.dex */
public interface Decoder {
    CompositeDecoder beginStructure(SerialDescriptor serialDescriptor);

    boolean decodeBoolean();

    byte decodeByte();

    char decodeChar();

    double decodeDouble();

    int decodeEnum(SerialDescriptor serialDescriptor);

    float decodeFloat();

    Decoder decodeInline(SerialDescriptor serialDescriptor);

    int decodeInt();

    long decodeLong();

    boolean decodeNotNullMark();

    Void decodeNull();

    <T> T decodeSerializableValue(DeserializationStrategy<T> deserializationStrategy);

    short decodeShort();

    String decodeString();
}
