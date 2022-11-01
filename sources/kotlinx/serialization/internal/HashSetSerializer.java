package kotlinx.serialization.internal;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public final class HashSetSerializer<E> extends ListLikeSerializer<E, Set<? extends E>, HashSet<E>> {
    public final SerialDescriptor descriptor;

    public HashSetSerializer(KSerializer<E> kSerializer) {
        super(kSerializer, null);
        this.descriptor = new HashSetClassDesc(kSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new HashSet();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(Object obj) {
        return ((HashSet) obj).size();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(Object obj, int i) {
        HashSet hashSet = (HashSet) obj;
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
        ((HashSet) obj).add(obj2);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        Set set = (Set) obj;
        HashSet hashSet = set instanceof HashSet ? (HashSet) set : null;
        return hashSet == null ? new HashSet(set) : hashSet;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        return (HashSet) obj;
    }
}
