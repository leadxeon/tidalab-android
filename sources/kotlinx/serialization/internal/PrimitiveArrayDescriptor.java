package kotlinx.serialization.internal;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionDescriptors.kt */
/* loaded from: classes.dex */
public final class PrimitiveArrayDescriptor extends ListLikeDescriptor {
    public final String serialName;

    public PrimitiveArrayDescriptor(SerialDescriptor serialDescriptor) {
        super(serialDescriptor, null);
        this.serialName = Intrinsics.stringPlus(serialDescriptor.getSerialName(), "Array");
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return this.serialName;
    }
}
