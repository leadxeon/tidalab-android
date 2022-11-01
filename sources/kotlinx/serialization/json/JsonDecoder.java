package kotlinx.serialization.json;

import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
/* compiled from: JsonDecoder.kt */
/* loaded from: classes.dex */
public interface JsonDecoder extends Decoder, CompositeDecoder {
    JsonElement decodeJsonElement();

    Json getJson();
}
