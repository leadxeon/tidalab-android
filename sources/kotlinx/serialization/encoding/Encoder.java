package kotlinx.serialization.encoding;

import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.modules.SerializersModule;
/* compiled from: Encoding.kt */
/* loaded from: classes.dex */
public interface Encoder {
    CompositeEncoder beginCollection(SerialDescriptor serialDescriptor, int i);

    CompositeEncoder beginStructure(SerialDescriptor serialDescriptor);

    void encodeBoolean(boolean z);

    void encodeByte(byte b);

    void encodeChar(char c);

    void encodeDouble(double d);

    void encodeEnum(SerialDescriptor serialDescriptor, int i);

    void encodeFloat(float f);

    Encoder encodeInline(SerialDescriptor serialDescriptor);

    void encodeInt(int i);

    void encodeLong(long j);

    void encodeNotNullMark();

    void encodeNull();

    <T> void encodeSerializableValue(SerializationStrategy<? super T> serializationStrategy, T t);

    void encodeShort(short s);

    void encodeString(String str);

    SerializersModule getSerializersModule();
}
