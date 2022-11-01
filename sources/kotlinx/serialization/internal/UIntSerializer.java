package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.UInt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: InlineClasses.kt */
/* loaded from: classes.dex */
public final class UIntSerializer implements KSerializer<UInt> {
    public static final UIntSerializer INSTANCE = new UIntSerializer();
    public static final SerialDescriptor descriptor = InputKt.InlinePrimitiveDescriptor("kotlin.UInt", IntSerializer.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return new UInt(decoder.decodeInline(descriptor).decodeInt());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        int i = ((UInt) obj).data;
        Encoder encodeInline = encoder.encodeInline(descriptor);
        if (encodeInline != null) {
            encodeInline.encodeInt(i);
        }
    }
}
