package kotlinx.serialization.json.internal;

import java.util.Map;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: TreeJsonDecoder.kt */
/* loaded from: classes.dex */
public /* synthetic */ class JsonTreeDecoder$elementName$alternativeNamesMap$1 extends FunctionReferenceImpl implements Function0<Map<String, ? extends Integer>> {
    public JsonTreeDecoder$elementName$alternativeNamesMap$1(SerialDescriptor serialDescriptor) {
        super(0, serialDescriptor, JsonNamesMapKt.class, "buildAlternativeNamesMap", "buildAlternativeNamesMap(Lkotlinx/serialization/descriptors/SerialDescriptor;)Ljava/util/Map;", 1);
    }

    @Override // kotlin.jvm.functions.Function0
    public Map<String, ? extends Integer> invoke() {
        return JsonNamesMapKt.buildAlternativeNamesMap((SerialDescriptor) this.receiver);
    }
}
