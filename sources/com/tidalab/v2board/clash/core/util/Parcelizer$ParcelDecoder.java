package com.tidalab.v2board.clash.core.util;

import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
/* compiled from: Parcelizer.kt */
/* loaded from: classes.dex */
public final class Parcelizer$ParcelDecoder implements Decoder, CompositeDecoder {
    public final Parcel parcel;
    public final SerializersModule serializersModule = SerializersModuleKt.EmptySerializersModule;

    public Parcelizer$ParcelDecoder(Parcel parcel) {
        this.parcel = parcel;
        SerializersModule serializersModule = SerializersModuleKt.EmptySerializersModule;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        return this;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        return decodeByte() != 0;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public boolean decodeBooleanElement(SerialDescriptor serialDescriptor, int i) {
        return decodeBoolean();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        return this.parcel.readByte();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public byte decodeByteElement(SerialDescriptor serialDescriptor, int i) {
        return decodeByte();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        return (char) decodeInt();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public char decodeCharElement(SerialDescriptor serialDescriptor, int i) {
        return (char) decodeInt();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeCollectionSize(SerialDescriptor serialDescriptor) {
        return decodeInt();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        return this.parcel.readDouble();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public double decodeDoubleElement(SerialDescriptor serialDescriptor, int i) {
        return this.parcel.readDouble();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        return decodeInt();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor serialDescriptor) {
        return decodeInt();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        return this.parcel.readFloat();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public float decodeFloatElement(SerialDescriptor serialDescriptor, int i) {
        return this.parcel.readFloat();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor serialDescriptor) {
        return this;
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        return this.parcel.readInt();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeIntElement(SerialDescriptor serialDescriptor, int i) {
        return decodeInt();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.parcel.readLong();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public long decodeLongElement(SerialDescriptor serialDescriptor, int i) {
        return decodeLong();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return decodeBoolean();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public <T> T decodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<T> deserializationStrategy, T t) {
        if (deserializationStrategy.getDescriptor().isNullable() || decodeBoolean()) {
            return (T) InputKt.decodeSerializableValue(this, deserializationStrategy);
        }
        return null;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public boolean decodeSequentially() {
        return true;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public <T> T decodeSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<T> deserializationStrategy, T t) {
        return (T) InputKt.decodeSerializableValue(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<T> deserializationStrategy) {
        return (T) InputKt.decodeSerializableValue(this, deserializationStrategy);
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        return (short) decodeInt();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public short decodeShortElement(SerialDescriptor serialDescriptor, int i) {
        return decodeShort();
    }

    @Override // kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        return this.parcel.readString();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public String decodeStringElement(SerialDescriptor serialDescriptor, int i) {
        return decodeString();
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor serialDescriptor) {
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }
}
