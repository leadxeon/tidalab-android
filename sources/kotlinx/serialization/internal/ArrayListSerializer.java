package kotlinx.serialization.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public final class ArrayListSerializer<E> extends ListLikeSerializer<E, List<? extends E>, ArrayList<E>> {
    public final SerialDescriptor descriptor;

    public ArrayListSerializer(KSerializer<E> kSerializer) {
        super(kSerializer, null);
        this.descriptor = new ArrayListClassDesc(kSerializer.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new ArrayList();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(Object obj) {
        return ((ArrayList) obj).size();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(Object obj, int i) {
        ((ArrayList) obj).ensureCapacity(i);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Iterator collectionIterator(Object obj) {
        return ((List) obj).iterator();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((List) obj).size();
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.ListLikeSerializer
    public void insert(Object obj, int i, Object obj2) {
        ((ArrayList) obj).add(i, obj2);
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        List list = (List) obj;
        ArrayList arrayList = list instanceof ArrayList ? (ArrayList) list : null;
        return arrayList == null ? new ArrayList(list) : arrayList;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        return (ArrayList) obj;
    }
}
