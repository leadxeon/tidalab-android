package kotlinx.serialization.json.internal;

import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
/* compiled from: TreeJsonDecoder.kt */
/* loaded from: classes.dex */
public final class JsonTreeListDecoder extends AbstractJsonTreeDecoder {
    public int currentIndex = -1;
    public final int size;
    public final JsonArray value;

    public JsonTreeListDecoder(Json json, JsonArray jsonArray) {
        super(json, jsonArray, null);
        this.value = jsonArray;
        this.size = jsonArray.size();
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonElement currentElement(String str) {
        return this.value.get2(Integer.parseInt(str));
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        int i = this.currentIndex;
        if (i >= this.size - 1) {
            return -1;
        }
        int i2 = i + 1;
        this.currentIndex = i2;
        return i2;
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    public String elementName(SerialDescriptor serialDescriptor, int i) {
        return String.valueOf(i);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    /* renamed from: getValue */
    public JsonElement mo15getValue() {
        return this.value;
    }
}
