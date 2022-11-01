package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.KSerializer;
/* compiled from: PluginGeneratedSerialDescriptor.kt */
/* loaded from: classes.dex */
public final class PluginGeneratedSerialDescriptor$childSerializers$2 extends Lambda implements Function0<KSerializer<?>[]> {
    public final /* synthetic */ PluginGeneratedSerialDescriptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginGeneratedSerialDescriptor$childSerializers$2(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        super(0);
        this.this$0 = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    public KSerializer<?>[] invoke() {
        GeneratedSerializer<?> generatedSerializer = this.this$0.generatedSerializer;
        KSerializer<?>[] childSerializers = generatedSerializer == null ? null : generatedSerializer.childSerializers();
        return childSerializers == null ? new KSerializer[0] : childSerializers;
    }
}
