package kotlinx.serialization.json;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.internal.StringOpsKt;
/* compiled from: JsonElement.kt */
/* loaded from: classes.dex */
public final class JsonObject$toString$1 extends Lambda implements Function1<Map.Entry<? extends String, ? extends JsonElement>, CharSequence> {
    public static final JsonObject$toString$1 INSTANCE = new JsonObject$toString$1();

    public JsonObject$toString$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    public CharSequence invoke(Map.Entry<? extends String, ? extends JsonElement> entry) {
        Map.Entry<? extends String, ? extends JsonElement> entry2 = entry;
        StringBuilder sb = new StringBuilder();
        StringOpsKt.printQuoted(sb, (String) entry2.getKey());
        sb.append(':');
        sb.append((JsonElement) entry2.getValue());
        return sb.toString();
    }
}
