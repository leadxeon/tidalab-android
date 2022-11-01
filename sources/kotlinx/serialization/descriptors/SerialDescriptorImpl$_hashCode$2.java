package kotlinx.serialization.descriptors;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: SerialDescriptors.kt */
/* loaded from: classes.dex */
public final class SerialDescriptorImpl$_hashCode$2 extends Lambda implements Function0<Integer> {
    public final /* synthetic */ SerialDescriptorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SerialDescriptorImpl$_hashCode$2(SerialDescriptorImpl serialDescriptorImpl) {
        super(0);
        this.this$0 = serialDescriptorImpl;
    }

    @Override // kotlin.jvm.functions.Function0
    public Integer invoke() {
        SerialDescriptorImpl serialDescriptorImpl = this.this$0;
        return Integer.valueOf(InputKt.hashCodeImpl(serialDescriptorImpl, serialDescriptorImpl.typeParametersDescriptors));
    }
}
