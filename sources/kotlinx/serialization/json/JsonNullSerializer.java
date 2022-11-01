package kotlinx.serialization.json;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt$buildSerialDescriptor$1;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.json.internal.JsonDecodingException;
/* compiled from: JsonElementSerializers.kt */
/* loaded from: classes.dex */
public final class JsonNullSerializer implements KSerializer<JsonNull> {
    public static final JsonNullSerializer INSTANCE = new JsonNullSerializer();
    public static final SerialDescriptor descriptor;

    static {
        SerialDescriptor buildSerialDescriptor;
        buildSerialDescriptor = InputKt.buildSerialDescriptor("kotlinx.serialization.json.JsonNull", SerialKind.ENUM.INSTANCE, new SerialDescriptor[0], (r4 & 8) != 0 ? SerialDescriptorsKt$buildSerialDescriptor$1.INSTANCE : null);
        descriptor = buildSerialDescriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        InputKt.asJsonDecoder(decoder);
        if (!decoder.decodeNotNullMark()) {
            decoder.decodeNull();
            return JsonNull.INSTANCE;
        }
        throw new JsonDecodingException("Expected 'null' literal");
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        JsonNull jsonNull = (JsonNull) obj;
        InputKt.access$verify(encoder);
        encoder.encodeNull();
    }
}
