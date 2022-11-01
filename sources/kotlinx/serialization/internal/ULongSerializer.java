package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.ULong;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: InlineClasses.kt */
/* loaded from: classes.dex */
public final class ULongSerializer implements KSerializer<ULong> {
    public static final ULongSerializer INSTANCE = new ULongSerializer();
    public static final SerialDescriptor descriptor = InputKt.InlinePrimitiveDescriptor("kotlin.ULong", LongSerializer.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return new ULong(decoder.decodeInline(descriptor).decodeLong());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        long j = ((ULong) obj).data;
        Encoder encodeInline = encoder.encodeInline(descriptor);
        if (encodeInline != null) {
            encodeInline.encodeLong(j);
        }
    }
}
