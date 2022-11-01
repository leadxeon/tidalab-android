package kotlinx.serialization.internal;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public final class LinkedHashSetSerializer<E> extends ListLikeSerializer<E, Set<? extends E>, LinkedHashSet<E>> {
    public final SerialDescriptor descriptor;

    public LinkedHashSetSerializer(KSerializer<E> kSerializer) {
        super(kSerializer, null);
        this.descriptor = new LinkedHashSetClassDesc(kSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new LinkedHashSet();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(Object obj) {
        return ((LinkedHashSet) obj).size();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(Object obj, int i) {
        LinkedHashSet linkedHashSet = (LinkedHashSet) obj;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Iterator collectionIterator(Object obj) {
        return ((Set) obj).iterator();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((Set) obj).size();
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer
    public void insert(Object obj, int i, Object obj2) {
        ((LinkedHashSet) obj).add(obj2);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        Set set = (Set) obj;
        LinkedHashSet linkedHashSet = set instanceof LinkedHashSet ? (LinkedHashSet) set : null;
        return linkedHashSet == null ? new LinkedHashSet(set) : linkedHashSet;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        return (LinkedHashSet) obj;
    }
}
