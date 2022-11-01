package kotlinx.serialization.internal;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: CollectionSerializers.kt */
/* loaded from: classes.dex */
public final class LinkedHashMapSerializer<K, V> extends MapLikeSerializer<K, V, Map<K, ? extends V>, LinkedHashMap<K, V>> {
    public final SerialDescriptor descriptor;

    public LinkedHashMapSerializer(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        super(kSerializer, kSerializer2, null);
        this.descriptor = new LinkedHashMapClassDesc(kSerializer.getDescriptor(), kSerializer2.getDescriptor());
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object builder() {
        return new LinkedHashMap();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public int builderSize(Object obj) {
        return ((LinkedHashMap) obj).size();
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public void checkCapacity(Object obj, int i) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) obj;
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
        LinkedHashMap linkedHashMap = map instanceof LinkedHashMap ? (LinkedHashMap) map : null;
        return linkedHashMap == null ? new LinkedHashMap(map) : linkedHashMap;
    }

    @Override // kotlinx.serialization.internal.AbstractCollectionSerializer
    public Object toResult(Object obj) {
        return (LinkedHashMap) obj;
    }
}
