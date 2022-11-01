package kotlinx.serialization.json;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
/* compiled from: JsonElement.kt */
@Serializable(with = JsonElementSerializer.class)
/* loaded from: classes.dex */
public abstract class JsonElement {
    public static final Companion Companion = new Companion(null);

    /* compiled from: JsonElement.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion() {
        }

        public final KSerializer<JsonElement> serializer() {
            return JsonElementSerializer.INSTANCE;
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }
    }

    public JsonElement() {
    }

    public JsonElement(DefaultConstructorMarker defaultConstructorMarker) {
    }
}
