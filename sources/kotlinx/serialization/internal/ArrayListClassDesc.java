package kotlinx.serialization.internal;

import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionDescriptors.kt */
/* loaded from: classes.dex */
public final class ArrayListClassDesc extends ListLikeDescriptor {
    public ArrayListClassDesc(SerialDescriptor serialDescriptor) {
        super(serialDescriptor, null);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return "kotlin.collections.ArrayList";
    }
}
