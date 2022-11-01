package defpackage;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.JsonArraySerializer;
import kotlinx.serialization.json.JsonLiteralSerializer;
import kotlinx.serialization.json.JsonNullSerializer;
import kotlinx.serialization.json.JsonObjectSerializer;
import kotlinx.serialization.json.JsonPrimitiveSerializer;
/* compiled from: kotlin-style lambda group */
/* renamed from: -$$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0M-jgEc  reason: invalid class name and default package */
/* loaded from: classes.dex */
public final class $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc extends Lambda implements Function0<SerialDescriptor> {
    public static final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc INSTANCE$0 = new $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc(0);
    public static final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc INSTANCE$1 = new $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc(1);
    public static final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc INSTANCE$2 = new $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc(2);
    public static final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc INSTANCE$3 = new $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc(3);
    public static final $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc INSTANCE$4 = new $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc(4);
    public final /* synthetic */ int $id$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public $$LambdaGroup$ks$DBqFnWYOOEaWYXKeQ44m0MjgEc(int i) {
        super(0);
        this.$id$ = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final SerialDescriptor invoke() {
        int i = this.$id$;
        if (i == 0) {
            JsonPrimitiveSerializer jsonPrimitiveSerializer = JsonPrimitiveSerializer.INSTANCE;
            return JsonPrimitiveSerializer.descriptor;
        } else if (i == 1) {
            JsonNullSerializer jsonNullSerializer = JsonNullSerializer.INSTANCE;
            return JsonNullSerializer.descriptor;
        } else if (i == 2) {
            JsonLiteralSerializer jsonLiteralSerializer = JsonLiteralSerializer.INSTANCE;
            return JsonLiteralSerializer.descriptor;
        } else if (i == 3) {
            JsonObjectSerializer jsonObjectSerializer = JsonObjectSerializer.INSTANCE;
            return JsonObjectSerializer.descriptor;
        } else if (i == 4) {
            JsonArraySerializer jsonArraySerializer = JsonArraySerializer.INSTANCE;
            return JsonArraySerializer.descriptor;
        } else {
            throw null;
        }
    }
}
