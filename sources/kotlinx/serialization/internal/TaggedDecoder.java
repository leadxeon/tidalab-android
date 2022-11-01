package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
/* compiled from: Tagged.kt */
/* loaded from: classes.dex */
public abstract class TaggedDecoder<Tag> implements Decoder, CompositeDecoder {
    public boolean flag;
    public final ArrayList<Tag> tagStack = new ArrayList<>();

    @Override // kotlinx.serialization.encoding.Decoder
    public final boolean decodeBoolean() {
        return decodeTaggedBoolean(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final boolean decodeBooleanElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedBoolean(getTag(serialDescriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final byte decodeByte() {
        return decodeTaggedByte(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final byte decodeByteElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedByte(getTag(serialDescriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final char decodeChar() {
        return decodeTaggedChar(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final char decodeCharElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedChar(getTag(serialDescriptor, i));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        return -1;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final double decodeDouble() {
        return decodeTaggedDouble(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final double decodeDoubleElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedDouble(getTag(serialDescriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final int decodeEnum(SerialDescriptor serialDescriptor) {
        return decodeTaggedEnum(popTag(), serialDescriptor);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final float decodeFloat() {
        return decodeTaggedFloat(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final float decodeFloatElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedFloat(getTag(serialDescriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final Decoder decodeInline(SerialDescriptor serialDescriptor) {
        return decodeTaggedInline(popTag(), serialDescriptor);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final int decodeInt() {
        return decodeTaggedInt(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final int decodeIntElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedInt(getTag(serialDescriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final long decodeLong() {
        return decodeTaggedLong(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final long decodeLongElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedLong(getTag(serialDescriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract boolean decodeNotNullMark();

    @Override // kotlinx.serialization.encoding.Decoder
    public final Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final <T> T decodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<T> deserializationStrategy, T t) {
        Tag tag = getTag(serialDescriptor, i);
        TaggedDecoder$decodeNullableSerializableElement$1 taggedDecoder$decodeNullableSerializableElement$1 = new TaggedDecoder$decodeNullableSerializableElement$1(this, deserializationStrategy, t);
        this.tagStack.add(tag);
        T t2 = (T) taggedDecoder$decodeNullableSerializableElement$1.invoke();
        if (!this.flag) {
            popTag();
        }
        this.flag = false;
        return t2;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public boolean decodeSequentially() {
        return false;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final <T> T decodeSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<T> deserializationStrategy, T t) {
        this.tagStack.add(getTag(serialDescriptor, i));
        T t2 = (T) decodeSerializableValue(deserializationStrategy);
        if (!this.flag) {
            popTag();
        }
        this.flag = false;
        return t2;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public abstract <T> T decodeSerializableValue(DeserializationStrategy<T> deserializationStrategy);

    @Override // kotlinx.serialization.encoding.Decoder
    public final short decodeShort() {
        return decodeTaggedShort(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final short decodeShortElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedShort(getTag(serialDescriptor, i));
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public final String decodeString() {
        return decodeTaggedString(popTag());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public final String decodeStringElement(SerialDescriptor serialDescriptor, int i) {
        return decodeTaggedString(getTag(serialDescriptor, i));
    }

    public abstract boolean decodeTaggedBoolean(Tag tag);

    public abstract byte decodeTaggedByte(Tag tag);

    public abstract char decodeTaggedChar(Tag tag);

    public abstract double decodeTaggedDouble(Tag tag);

    public abstract int decodeTaggedEnum(Tag tag, SerialDescriptor serialDescriptor);

    public abstract float decodeTaggedFloat(Tag tag);

    public abstract Decoder decodeTaggedInline(Tag tag, SerialDescriptor serialDescriptor);

    public abstract int decodeTaggedInt(Tag tag);

    public abstract long decodeTaggedLong(Tag tag);

    public abstract short decodeTaggedShort(Tag tag);

    public abstract String decodeTaggedString(Tag tag);

    public final Tag getCurrentTagOrNull() {
        return (Tag) ArraysKt___ArraysKt.lastOrNull(this.tagStack);
    }

    public abstract Tag getTag(SerialDescriptor serialDescriptor, int i);

    public final Tag popTag() {
        ArrayList<Tag> arrayList = this.tagStack;
        Tag remove = arrayList.remove(ArraysKt___ArraysKt.getLastIndex(arrayList));
        this.flag = true;
        return remove;
    }
}
