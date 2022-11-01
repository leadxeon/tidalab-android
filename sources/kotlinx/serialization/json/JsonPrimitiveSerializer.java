package kotlinx.serialization.json;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt$buildSerialDescriptor$1;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: JsonElementSerializers.kt */
/* loaded from: classes.dex */
public final class JsonPrimitiveSerializer implements KSerializer<JsonPrimitive> {
    public static final JsonPrimitiveSerializer INSTANCE = new JsonPrimitiveSerializer();
    public static final SerialDescriptor descriptor;

    static {
        SerialDescriptor buildSerialDescriptor;
        buildSerialDescriptor = InputKt.buildSerialDescriptor("kotlinx.serialization.json.JsonPrimitive", PrimitiveKind.STRING.INSTANCE, new SerialDescriptor[0], (r4 & 8) != 0 ? SerialDescriptorsKt$buildSerialDescriptor$1.INSTANCE : null);
        descriptor = buildSerialDescriptor;
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        JsonElement decodeJsonElement = InputKt.asJsonDecoder(decoder).decodeJsonElement();
        if (decodeJsonElement instanceof JsonPrimitive) {
            return (JsonPrimitive) decodeJsonElement;
        }
        throw InputKt.JsonDecodingException(-1, Intrinsics.stringPlus("Unexpected JSON element, expected JsonPrimitive, had ", Reflection.getOrCreateKotlinClass(decodeJsonElement.getClass())), decodeJsonElement.toString());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        JsonPrimitive jsonPrimitive = (JsonPrimitive) obj;
        InputKt.access$verify(encoder);
        if (jsonPrimitive instanceof JsonNull) {
            encoder.encodeSerializableValue(JsonNullSerializer.INSTANCE, JsonNull.INSTANCE);
        } else {
            encoder.encodeSerializableValue(JsonLiteralSerializer.INSTANCE, (JsonLiteral) jsonPrimitive);
        }
    }
}
