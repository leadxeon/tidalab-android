package kotlinx.serialization.json;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.ScreenFloatValueRegEx;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.ULongSerializer;
/* compiled from: JsonElementSerializers.kt */
/* loaded from: classes.dex */
public final class JsonLiteralSerializer implements KSerializer<JsonLiteral> {
    public static final JsonLiteralSerializer INSTANCE = new JsonLiteralSerializer();
    public static final SerialDescriptor descriptor = InputKt.PrimitiveSerialDescriptor("kotlinx.serialization.json.JsonLiteral", PrimitiveKind.STRING.INSTANCE);

    @Override // kotlinx.serialization.DeserializationStrategy
    public Object deserialize(Decoder decoder) {
        JsonElement decodeJsonElement = InputKt.asJsonDecoder(decoder).decodeJsonElement();
        if (decodeJsonElement instanceof JsonLiteral) {
            return (JsonLiteral) decodeJsonElement;
        }
        throw InputKt.JsonDecodingException(-1, Intrinsics.stringPlus("Unexpected JSON element, expected JsonLiteral, had ", Reflection.getOrCreateKotlinClass(decodeJsonElement.getClass())), decodeJsonElement.toString());
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, Object obj) {
        JsonLiteral jsonLiteral = (JsonLiteral) obj;
        InputKt.access$verify(encoder);
        if (jsonLiteral.isString) {
            encoder.encodeString(jsonLiteral.content);
            return;
        }
        Long longOrNull = StringsKt__IndentKt.toLongOrNull(jsonLiteral.getContent());
        if (longOrNull == null) {
            ULong uLongOrNull = StringsKt__IndentKt.toULongOrNull(jsonLiteral.content);
            if (uLongOrNull == null) {
                String content = jsonLiteral.getContent();
                Double d = null;
                try {
                    if (ScreenFloatValueRegEx.value.nativePattern.matcher(content).matches()) {
                        d = Double.valueOf(Double.parseDouble(content));
                    }
                } catch (NumberFormatException unused) {
                }
                if (d == null) {
                    Boolean booleanOrNull = InputKt.getBooleanOrNull(jsonLiteral);
                    if (booleanOrNull == null) {
                        encoder.encodeString(jsonLiteral.content);
                    } else {
                        encoder.encodeBoolean(booleanOrNull.booleanValue());
                    }
                } else {
                    encoder.encodeDouble(d.doubleValue());
                }
            } else {
                long j = uLongOrNull.data;
                ULongSerializer uLongSerializer = ULongSerializer.INSTANCE;
                Encoder encodeInline = encoder.encodeInline(ULongSerializer.descriptor);
                if (encodeInline != null) {
                    encodeInline.encodeLong(j);
                }
            }
        } else {
            encoder.encodeLong(longOrNull.longValue());
        }
    }
}
