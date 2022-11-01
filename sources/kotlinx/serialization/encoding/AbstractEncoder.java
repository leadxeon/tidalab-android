package kotlinx.serialization.encoding;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.internal.StreamingJsonEncoder;
/* compiled from: AbstractEncoder.kt */
/* loaded from: classes.dex */
public abstract class AbstractEncoder implements Encoder, CompositeEncoder {
    @Override // kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginCollection(SerialDescriptor serialDescriptor, int i) {
        return ((StreamingJsonEncoder) this).beginStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeBoolean(boolean z);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeBooleanElement(SerialDescriptor serialDescriptor, int i, boolean z) {
        encodeElement(serialDescriptor, i);
        encodeBoolean(z);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeByte(byte b);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeByteElement(SerialDescriptor serialDescriptor, int i, byte b) {
        encodeElement(serialDescriptor, i);
        encodeByte(b);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeCharElement(SerialDescriptor serialDescriptor, int i, char c) {
        encodeElement(serialDescriptor, i);
        ((StreamingJsonEncoder) this).encodeString(String.valueOf(c));
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeDouble(double d);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeDoubleElement(SerialDescriptor serialDescriptor, int i, double d) {
        encodeElement(serialDescriptor, i);
        encodeDouble(d);
    }

    public abstract boolean encodeElement(SerialDescriptor serialDescriptor, int i);

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeFloat(float f);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeFloatElement(SerialDescriptor serialDescriptor, int i, float f) {
        encodeElement(serialDescriptor, i);
        encodeFloat(f);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeInt(int i);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeIntElement(SerialDescriptor serialDescriptor, int i, int i2) {
        encodeElement(serialDescriptor, i);
        encodeInt(i2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeLong(long j);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeLongElement(SerialDescriptor serialDescriptor, int i, long j) {
        encodeElement(serialDescriptor, i);
        encodeLong(j);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNotNullMark() {
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final <T> void encodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, SerializationStrategy<? super T> serializationStrategy, T t) {
        encodeElement(serialDescriptor, i);
        InputKt.encodeNullableSerializableValue(this, serializationStrategy, t);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final <T> void encodeSerializableElement(SerialDescriptor serialDescriptor, int i, SerializationStrategy<? super T> serializationStrategy, T t) {
        encodeElement(serialDescriptor, i);
        encodeSerializableValue(serializationStrategy, t);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract <T> void encodeSerializableValue(SerializationStrategy<? super T> serializationStrategy, T t);

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeShort(short s);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeShortElement(SerialDescriptor serialDescriptor, int i, short s) {
        encodeElement(serialDescriptor, i);
        encodeShort(s);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public abstract void encodeString(String str);

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public final void encodeStringElement(SerialDescriptor serialDescriptor, int i, String str) {
        encodeElement(serialDescriptor, i);
        encodeString(str);
    }
}
