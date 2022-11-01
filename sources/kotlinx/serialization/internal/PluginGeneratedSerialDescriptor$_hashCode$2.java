package kotlinx.serialization.internal;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: PluginGeneratedSerialDescriptor.kt */
/* loaded from: classes.dex */
public final class PluginGeneratedSerialDescriptor$_hashCode$2 extends Lambda implements Function0<Integer> {
    public final /* synthetic */ PluginGeneratedSerialDescriptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginGeneratedSerialDescriptor$_hashCode$2(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        super(0);
        this.this$0 = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlin.jvm.functions.Function0
    public Integer invoke() {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = this.this$0;
        return Integer.valueOf(InputKt.hashCodeImpl(pluginGeneratedSerialDescriptor, pluginGeneratedSerialDescriptor.getTypeParameterDescriptors$kotlinx_serialization_core()));
    }
}
