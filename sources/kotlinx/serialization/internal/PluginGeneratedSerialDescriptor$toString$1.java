package kotlinx.serialization.internal;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: PluginGeneratedSerialDescriptor.kt */
/* loaded from: classes.dex */
public final class PluginGeneratedSerialDescriptor$toString$1 extends Lambda implements Function1<Integer, CharSequence> {
    public final /* synthetic */ PluginGeneratedSerialDescriptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PluginGeneratedSerialDescriptor$toString$1(PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor) {
        super(1);
        this.this$0 = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlin.jvm.functions.Function1
    public CharSequence invoke(Integer num) {
        int intValue = num.intValue();
        return this.this$0.names[intValue] + ": " + this.this$0.getElementDescriptor(intValue).getSerialName();
    }
}
