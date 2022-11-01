package kotlinx.serialization.encoding;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: AbstractDecoder.kt */
/* loaded from: classes.dex */
public abstract class AbstractDecoder implements Decoder, CompositeDecoder {
    @Override // kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        return this;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final boolean decodeBooleanElement(SerialDescriptor serialDescriptor, int i) {
        return decodeBoolean();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract byte decodeByte();

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final byte decodeByteElement(SerialDescriptor serialDescriptor, int i) {
        return decodeByte();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final char decodeCharElement(SerialDescriptor serialDescriptor, int i) {
        return decodeChar();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        return -1;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final double decodeDoubleElement(SerialDescriptor serialDescriptor, int i) {
        return decodeDouble();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor serialDescriptor) {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final float decodeFloatElement(SerialDescriptor serialDescriptor, int i) {
        return decodeFloat();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor serialDescriptor) {
        return this;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract int decodeInt();

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final int decodeIntElement(SerialDescriptor serialDescriptor, int i) {
        return decodeInt();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract long decodeLong();

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final long decodeLongElement(SerialDescriptor serialDescriptor, int i) {
        return decodeLong();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return true;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final <T> T decodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<T> deserializationStrategy, T t) {
        return (deserializationStrategy.getDescriptor().isNullable() || decodeNotNullMark()) ? (T) decodeSerializableValue(deserializationStrategy) : (T) decodeNull();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public boolean decodeSequentially() {
        return false;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final <T> T decodeSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<T> deserializationStrategy, T t) {
        return (T) decodeSerializableValue(deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<T> deserializationStrategy) {
        return (T) InputKt.decodeSerializableValue(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract short decodeShort();

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final short decodeShortElement(SerialDescriptor serialDescriptor, int i) {
        return decodeShort();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        decodeValue();
        throw null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final String decodeStringElement(SerialDescriptor serialDescriptor, int i) {
        return decodeString();
    }

    public Object decodeValue() {
        throw new SerializationException(Reflection.getOrCreateKotlinClass(getClass()) + " can't retrieve untyped values");
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor serialDescriptor) {
    }
}
