package kotlinx.serialization.json;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
/* compiled from: JsonElementSerializers.kt */
/* loaded from: classes.dex */
public final class JsonElementSerializer implements KSerializer<JsonElement> {
    public static final JsonElementSerializer INSTANCE = new JsonElementSerializer();
    public static final SerialDescriptor descriptor = InputKt.buildSerialDescriptor("kotlinx.serialization.json.JsonElement", PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], JsonElementSerializer$descriptor$1.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        return InputKt.asJsonDecoder(decoder).decodeJsonElement();
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        JsonElement jsonElement = (JsonElement) obj;
        InputKt.access$verify(encoder);
        if (jsonElement instanceof JsonPrimitive) {
            encoder.encodeSerializableValue(JsonPrimitiveSerializer.INSTANCE, jsonElement);
        } else if (jsonElement instanceof JsonObject) {
            encoder.encodeSerializableValue(JsonObjectSerializer.INSTANCE, jsonElement);
        } else if (jsonElement instanceof JsonArray) {
            encoder.encodeSerializableValue(JsonArraySerializer.INSTANCE, jsonElement);
        }
    }
}
