package kotlinx.serialization.json;

import kotlinx.serialization.Serializable;
/* compiled from: JsonElement.kt */
@Serializable(with = JsonNullSerializer.class)
/* loaded from: classes.dex */
public final class JsonNull extends JsonPrimitive {
    public static final JsonNull INSTANCE = new JsonNull();

    public JsonNull() {
        super(null);
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String getContent() {
        return "null";
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public boolean isString() {
        return false;
    }
}
