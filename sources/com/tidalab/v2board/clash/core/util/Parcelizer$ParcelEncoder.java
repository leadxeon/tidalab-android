package com.tidalab.v2board.clash.core.util;

import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;
/* compiled from: Parcelizer.kt */
/* loaded from: classes.dex */
public final class Parcelizer$ParcelEncoder implements Encoder, CompositeEncoder {
    public final Parcel parcel;
    public final SerializersModule serializersModule = SerializersModuleKt.EmptySerializersModule;

    public Parcelizer$ParcelEncoder(Parcel parcel) {
        this.parcel = parcel;
        SerializersModule serializersModule = SerializersModuleKt.EmptySerializersModule;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginCollection(SerialDescriptor serialDescriptor, int i) {
        this.parcel.writeInt(i);
        return this;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginStructure(SerialDescriptor serialDescriptor) {
        return this;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeBoolean(boolean z) {
        this.parcel.writeByte(z ? (byte) 1 : (byte) 0);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeBooleanElement(SerialDescriptor serialDescriptor, int i, boolean z) {
        this.parcel.writeByte(z ? (byte) 1 : (byte) 0);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeByte(byte b) {
        this.parcel.writeByte(b);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeByteElement(SerialDescriptor serialDescriptor, int i, byte b) {
        this.parcel.writeByte(b);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeChar(char c) {
        this.parcel.writeInt(c);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeCharElement(SerialDescriptor serialDescriptor, int i, char c) {
        this.parcel.writeInt(c);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeDouble(double d) {
        this.parcel.writeDouble(d);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeDoubleElement(SerialDescriptor serialDescriptor, int i, double d) {
        this.parcel.writeDouble(d);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeEnum(SerialDescriptor serialDescriptor, int i) {
        this.parcel.writeInt(i);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeFloat(float f) {
        this.parcel.writeFloat(f);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeFloatElement(SerialDescriptor serialDescriptor, int i, float f) {
        this.parcel.writeFloat(f);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public Encoder encodeInline(SerialDescriptor serialDescriptor) {
        return this;
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeInt(int i) {
        this.parcel.writeInt(i);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeIntElement(SerialDescriptor serialDescriptor, int i, int i2) {
        this.parcel.writeInt(i2);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeLong(long j) {
        this.parcel.writeLong(j);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeLongElement(SerialDescriptor serialDescriptor, int i, long j) {
        this.parcel.writeLong(j);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNotNullMark() {
        this.parcel.writeByte((byte) 1);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        this.parcel.writeByte((byte) 0);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, SerializationStrategy<? super T> serializationStrategy, T t) {
        InputKt.encodeNullableSerializableValue(this, serializationStrategy, t);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public <T> void encodeSerializableElement(SerialDescriptor serialDescriptor, int i, SerializationStrategy<? super T> serializationStrategy, T t) {
        encodeSerializableValue(serializationStrategy, t);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.encoding.Encoder
    public <T> void encodeSerializableValue(SerializationStrategy<? super T> serializationStrategy, T t) {
        serializationStrategy.serialize(this, t);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeShort(short s) {
        this.parcel.writeInt(s);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeShortElement(SerialDescriptor serialDescriptor, int i, short s) {
        this.parcel.writeInt(s);
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public void encodeString(String str) {
        this.parcel.writeString(str);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void encodeStringElement(SerialDescriptor serialDescriptor, int i, String str) {
        this.parcel.writeString(str);
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public void endStructure(SerialDescriptor serialDescriptor) {
    }

    @Override // kotlinx.serialization.encoding.Encoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    @Override // kotlinx.serialization.encoding.CompositeEncoder
    public boolean shouldEncodeElementDefault(SerialDescriptor serialDescriptor, int i) {
        return true;
    }
}
