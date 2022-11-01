package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.UByte;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: InlineClasses.kt */
/* loaded from: classes.dex */
public final class UByteSerializer implements KSerializer<UByte> {
    public static final UByteSerializer INSTANCE = new UByteSerializer();
    public static final SerialDescriptor descriptor = InputKt.InlinePrimitiveDescriptor("kotlin.UByte", ByteSerializer.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return new UByte(decoder.decodeInline(descriptor).decodeByte());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        byte b = ((UByte) obj).data;
        Encoder encodeInline = encoder.encodeInline(descriptor);
        if (encodeInline != null) {
            encodeInline.encodeByte(b);
        }
    }
}
