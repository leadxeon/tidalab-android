package kotlinx.serialization.json;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.json.internal.StringOpsKt;
/* compiled from: JsonElement.kt */
/* loaded from: classes.dex */
public final class JsonLiteral extends JsonPrimitive {
    public final String content;
    public final boolean isString;

    public JsonLiteral(Object obj, boolean z) {
        super(null);
        this.isString = z;
        this.content = obj.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(JsonLiteral.class), Reflection.getOrCreateKotlinClass(obj.getClass()))) {
            return false;
        }
        JsonLiteral jsonLiteral = (JsonLiteral) obj;
        return this.isString == jsonLiteral.isString && Intrinsics.areEqual(this.content, jsonLiteral.content);
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String getContent() {
        return this.content;
    }

    public int hashCode() {
        return this.content.hashCode() + (Boolean.valueOf(this.isString).hashCode() * 31);
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public boolean isString() {
        return this.isString;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String toString() {
        if (!this.isString) {
            return this.content;
        }
        StringBuilder sb = new StringBuilder();
        StringOpsKt.printQuoted(sb, this.content);
        return sb.toString();
    }
}
