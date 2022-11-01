package kotlinx.serialization.descriptors;

import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;
/* compiled from: Iterables.kt */
/* loaded from: classes.dex */
public final class SerialDescriptorKt$special$$inlined$Iterable$2 implements Iterable<String>, KMappedMarker {
    public final /* synthetic */ SerialDescriptor $this_elementNames$inlined;

    public SerialDescriptorKt$special$$inlined$Iterable$2(SerialDescriptor serialDescriptor) {
        this.$this_elementNames$inlined = serialDescriptor;
    }

    @Override // java.lang.Iterable
    public Iterator<String> iterator() {
        return new SerialDescriptorKt$elementNames$1$1(this.$this_elementNames$inlined);
    }
}
