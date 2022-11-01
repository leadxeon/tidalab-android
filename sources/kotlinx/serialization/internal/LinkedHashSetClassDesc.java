package kotlinx.serialization.internal;

import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionDescriptors.kt */
/* loaded from: classes.dex */
public final class LinkedHashSetClassDesc extends ListLikeDescriptor {
    public LinkedHashSetClassDesc(SerialDescriptor serialDescriptor) {
        super(serialDescriptor, null);
    }

    @Override // kotlinx.serialization.descriptors.SerialDescriptor
    public String getSerialName() {
        return "kotlin.collections.LinkedHashSet";
    }
}
