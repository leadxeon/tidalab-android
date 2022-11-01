package kotlinx.serialization.json.internal;

import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonPrimitive;
/* compiled from: TreeJsonDecoder.kt */
/* loaded from: classes.dex */
public final class JsonPrimitiveDecoder extends AbstractJsonTreeDecoder {
    public final JsonPrimitive value;

    public JsonPrimitiveDecoder(Json json, JsonPrimitive jsonPrimitive) {
        super(json, jsonPrimitive, null);
        this.value = jsonPrimitive;
        this.tagStack.add("primitive");
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonElement currentElement(String str) {
        if (str == "primitive") {
            return this.value;
        }
        throw new IllegalArgumentException("This input can only handle primitives with 'primitive' tag".toString());
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        return 0;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    /* renamed from: getValue */
    public JsonElement mo15getValue() {
        return this.value;
    }
}
