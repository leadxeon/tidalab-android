package kotlinx.serialization.descriptors;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
/* compiled from: SerialDescriptors.kt */
/* loaded from: classes.dex */
public final class SerialDescriptorImpl$toString$1 extends Lambda implements Function1<Integer, CharSequence> {
    public final /* synthetic */ SerialDescriptorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialDescriptorImpl$toString$1(SerialDescriptorImpl serialDescriptorImpl) {
        super(1);
        this.this$0 = serialDescriptorImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public CharSequence invoke(Integer num) {
        int intValue = num.intValue();
        return this.this$0.elementNames[intValue] + ": " + this.this$0.elementDescriptors[intValue].getSerialName();
    }
}
