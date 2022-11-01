package kotlinx.serialization.encoding;

import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: Decoding.kt */
/* loaded from: classes.dex */
public interface CompositeDecoder {
    boolean decodeBooleanElement(SerialDescriptor serialDescriptor, int i);

    byte decodeByteElement(SerialDescriptor serialDescriptor, int i);

    char decodeCharElement(SerialDescriptor serialDescriptor, int i);

    int decodeCollectionSize(SerialDescriptor serialDescriptor);

    double decodeDoubleElement(SerialDescriptor serialDescriptor, int i);

    int decodeElementIndex(SerialDescriptor serialDescriptor);

    float decodeFloatElement(SerialDescriptor serialDescriptor, int i);

    int decodeIntElement(SerialDescriptor serialDescriptor, int i);

    long decodeLongElement(SerialDescriptor serialDescriptor, int i);

    <T> T decodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<T> deserializationStrategy, T t);

    boolean decodeSequentially();

    <T> T decodeSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<T> deserializationStrategy, T t);

    short decodeShortElement(SerialDescriptor serialDescriptor, int i);

    String decodeStringElement(SerialDescriptor serialDescriptor, int i);

    void endStructure(SerialDescriptor serialDescriptor);

    SerializersModule getSerializersModule();
}
