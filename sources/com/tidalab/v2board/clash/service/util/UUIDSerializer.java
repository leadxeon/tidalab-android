package com.tidalab.v2board.clash.service.util;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.UUID;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: Serializers.kt */
/* loaded from: classes.dex */
public final class UUIDSerializer implements KSerializer<UUID> {
    public final SerialDescriptor descriptor = InputKt.PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return UUID.fromString(decoder.decodeString());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        encoder.encodeString(((UUID) obj).toString());
    }
}
