package kotlinx.serialization.json;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
/* compiled from: JsonElement.kt */
@Serializable(with = JsonPrimitiveSerializer.class)
/* loaded from: classes.dex */
public abstract class JsonPrimitive extends JsonElement {
    public static final Companion Companion = new Companion(null);

    /* compiled from: JsonElement.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public final KSerializer<JsonPrimitive> serializer() {
            return JsonPrimitiveSerializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public JsonPrimitive() {
        super(null);
    }

    public abstract String getContent();

    public abstract boolean isString();

    public String toString() {
        return getContent();
    }

    public JsonPrimitive(DefaultConstructorMarker defaultConstructorMarker) {
        super(null);
    }
}
