package androidx.collection;

import androidx.collection.MapCollections;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    public MapCollections<K, V> mCollections;

    public ArrayMap() {
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        MapCollections<K, V> collection = getCollection();
        if (collection.mEntrySet == null) {
            collection.mEntrySet = new MapCollections.EntrySet();
        }
        return collection.mEntrySet;
    }

    public final MapCollections<K, V> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new MapCollections<K, V>() { // from class: androidx.collection.ArrayMap.1
                @Override // androidx.collection.MapCollections
                public void colClear() {
                    ArrayMap.this.clear();
                }

                @Override // androidx.collection.MapCollections
                public Object colGetEntry(int i, int i2) {
                    return ArrayMap.this.mArray[(i << 1) + i2];
                }

                @Override // androidx.collection.MapCollections
                public Map<K, V> colGetMap() {
                    return ArrayMap.this;
                }

                @Override // androidx.collection.MapCollections
                public int colGetSize() {
                    return ArrayMap.this.mSize;
                }

                @Override // androidx.collection.MapCollections
                public int colIndexOfKey(Object obj) {
                    return ArrayMap.this.indexOfKey(obj);
                }

                @Override // androidx.collection.MapCollections
                public int colIndexOfValue(Object obj) {
                    return ArrayMap.this.indexOfValue(obj);
                }

                @Override // androidx.collection.MapCollections
                public void colPut(K k, V v) {
                    ArrayMap.this.put(k, v);
                }

                @Override // androidx.collection.MapCollections
                public void colRemoveAt(int i) {
                    ArrayMap.this.removeAt(i);
                }

                @Override // androidx.collection.MapCollections
                public V colSetValue(int i, V v) {
                    int i2 = (i << 1) + 1;
                    Object[] objArr = ArrayMap.this.mArray;
                    V v2 = (V) objArr[i2];
                    objArr[i2] = v;
                    return v2;
                }
            };
        }
        return this.mCollections;
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        MapCollections<K, V> collection = getCollection();
        if (collection.mKeySet == null) {
            collection.mKeySet = new MapCollections.KeySet();
        }
        return collection.mKeySet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(map.size() + this.mSize);
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public Collection<V> values() {
        MapCollections<K, V> collection = getCollection();
        if (collection.mValues == null) {
            collection.mValues = new MapCollections.ValuesCollection();
        }
        return collection.mValues;
    }

    public ArrayMap(int i) {
        super(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap != null) {
            int i = simpleArrayMap.mSize;
            ensureCapacity(this.mSize + i);
            if (this.mSize != 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    put(simpleArrayMap.keyAt(i2), simpleArrayMap.valueAt(i2));
                }
            } else if (i > 0) {
                System.arraycopy(simpleArrayMap.mHashes, 0, this.mHashes, 0, i);
                System.arraycopy(simpleArrayMap.mArray, 0, this.mArray, 0, i << 1);
                this.mSize = i;
            }
        }
    }
}
