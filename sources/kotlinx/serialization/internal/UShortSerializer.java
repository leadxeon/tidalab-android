package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.UShort;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: InlineClasses.kt */
/* loaded from: classes.dex */
public final class UShortSerializer implements KSerializer<UShort> {
    public static final UShortSerializer INSTANCE = new UShortSerializer();
    public static final SerialDescriptor descriptor = InputKt.InlinePrimitiveDescriptor("kotlin.UShort", ShortSerializer.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return new UShort(decoder.decodeInline(descriptor).decodeShort());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        short s = ((UShort) obj).data;
        Encoder encodeInline = encoder.encodeInline(descriptor);
        if (encodeInline != null) {
            encodeInline.encodeShort(s);
        }
    }
}
