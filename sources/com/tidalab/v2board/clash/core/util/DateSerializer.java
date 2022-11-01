package com.tidalab.v2board.clash.core.util;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Date;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: Serializers.kt */
/* loaded from: classes.dex */
public final class DateSerializer implements KSerializer<Date> {
    public static final DateSerializer INSTANCE = new DateSerializer();

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return new Date(decoder.decodeLong());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return InputKt.PrimitiveSerialDescriptor("Date", PrimitiveKind.LONG.INSTANCE);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        encoder.encodeLong(((Date) obj).getTime());
    }
}
