package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: PluginGeneratedSerialDescriptor.kt */
/* loaded from: classes.dex */
public final class PluginGeneratedSerialDescriptor$typeParameterDescriptors$2 extends Lambda implements Function0<SerialDescriptor[]> {
    public final /* synthetic */ PluginGeneratedSerialDescriptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginGeneratedSerialDescriptor$typeParameterDescriptors$2(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        super(0);
        this.this$0 = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    public SerialDescriptor[] invoke() {
        KSerializer<?>[] typeParametersSerializers;
        GeneratedSerializer<?> generatedSerializer = this.this$0.generatedSerializer;
        ArrayList arrayList = null;
        if (!(generatedSerializer == null || (typeParametersSerializers = generatedSerializer.typeParametersSerializers()) == null)) {
            arrayList = new ArrayList(typeParametersSerializers.length);
            for (KSerializer<?> kSerializer : typeParametersSerializers) {
                arrayList.add(kSerializer.getDescriptor());
            }
        }
        return Platform_commonKt.compactArray(arrayList);
    }
}
