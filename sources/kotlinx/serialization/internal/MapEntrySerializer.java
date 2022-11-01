package kotlinx.serialization.internal;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;
/* compiled from: Tuples.kt */
/* loaded from: classes.dex */
public final class MapEntrySerializer<K, V> extends KeyValueSerializer<K, V, Map.Entry<? extends K, ? extends V>> {
    public final SerialDescriptor descriptor;

    /* compiled from: Tuples.kt */
    /* loaded from: classes.dex */
    public static final class MapEntry<K, V> implements Map.Entry<K, V>, KMappedMarker {
        public final K key;
        public final V value;

        public MapEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MapEntry)) {
                return false;
            }
            MapEntry mapEntry = (MapEntry) obj;
            return Intrinsics.areEqual(this.key, mapEntry.key) && Intrinsics.areEqual(this.value, mapEntry.value);
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            K k = this.key;
            int i = 0;
            int hashCode = (k == null ? 0 : k.hashCode()) * 31;
            V v = this.value;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode + i;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public String toString() {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("MapEntry(key=");
            outline13.append(this.key);
            outline13.append(", value=");
            outline13.append(this.value);
            outline13.append(')');
            return outline13.toString();
        }
    }

    public MapEntrySerializer(KSerializer<K> kSerializer, KSerializer<V> kSerializer2) {
        super(kSerializer, kSerializer2, null);
        this.descriptor = InputKt.buildSerialDescriptor("kotlin.collections.Map.Entry", StructureKind.MAP.INSTANCE, new SerialDescriptor[0], new MapEntrySerializer$descriptor$1(kSerializer, kSerializer2));
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public Object getKey(Object obj) {
        return ((Map.Entry) obj).getKey();
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public Object getValue(Object obj) {
        return ((Map.Entry) obj).getValue();
    }

    @Override // kotlinx.serialization.internal.KeyValueSerializer
    public Object toResult(Object obj, Object obj2) {
        return new MapEntry(obj, obj2);
    }
}
