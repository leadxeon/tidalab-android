package kotlinx.serialization.internal;

import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionDescriptors.kt */
/* loaded from: classes.dex */
public final class ArrayClassDesc extends ListLikeDescriptor {
    public ArrayClassDesc(SerialDescriptor serialDescriptor) {
        super(serialDescriptor, null);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return "kotlin.Array";
    }
}
