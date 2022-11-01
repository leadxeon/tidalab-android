package kotlinx.serialization.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public final class HashMapSerializer<K, V> extends MapLikeSerializer<K, V, Map<K, ? extends V>, HashMap<K, V>> {
    public final SerialDescriptor descriptor;

    public HashMapSerializer(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        super(kSerializer, kSerializer2, null);
        this.descriptor = new HashMapClassDesc(kSerializer.getDescriptor(), kSerializer2.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new HashMap();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(Object obj) {
        return ((HashMap) obj).size();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(Object obj, int i) {
        HashMap hashMap = (HashMap) obj;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Iterator collectionIterator(Object obj) {
        return ((Map) obj).entrySet().iterator();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int collectionSize(Object obj) {
        return ((Map) obj).size();
    }

    @Override // kotlinx.serialization.internal.MapLikeSerializer, kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toBuilder(Object obj) {
        Map map = (Map) obj;
        HashMap hashMap = map instanceof HashMap ? (HashMap) map : null;
        return hashMap == null ? new HashMap(map) : hashMap;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        return (HashMap) obj;
    }
}
