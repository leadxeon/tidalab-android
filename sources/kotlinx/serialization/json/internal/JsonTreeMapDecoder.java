package kotlinx.serialization.json.internal;

import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonObject;
/* compiled from: TreeJsonDecoder.kt */
/* loaded from: classes.dex */
public final class JsonTreeMapDecoder extends JsonTreeDecoder {
    public final List<String> keys;
    public int position = -1;
    public final int size;
    public final JsonObject value;

    public JsonTreeMapDecoder(Json json, JsonObject jsonObject) {
        super(json, jsonObject, null, null, 12);
        this.value = jsonObject;
        List<String> list = ArraysKt___ArraysKt.toList(jsonObject.keySet());
        this.keys = list;
        this.size = list.size() * 2;
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeDecoder, kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonElement currentElement(String str) {
        if (this.position % 2 == 0) {
            return new JsonLiteral(str, true);
        }
        return (JsonElement) ArraysKt___ArraysKt.getValue(this.value, str);
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        int i = this.position;
        if (i >= this.size - 1) {
            return -1;
        }
        int i2 = i + 1;
        this.position = i2;
        return i2;
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeDecoder, kotlinx.serialization.internal.NamedValueDecoder
    public String elementName(SerialDescriptor serialDescriptor, int i) {
        return this.keys.get(i / 2);
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeDecoder, kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor serialDescriptor) {
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeDecoder, kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    /* renamed from: getValue */
    public JsonElement mo15getValue() {
        return this.value;
    }

    @Override // kotlinx.serialization.json.internal.JsonTreeDecoder, kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    /* renamed from: getValue  reason: collision with other method in class */
    public JsonObject mo15getValue() {
        return this.value;
    }
}
