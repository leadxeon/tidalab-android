package com.facebook.imagepipeline.cache;

import com.facebook.common.internal.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class CountingLruMap<K, V> {
    public final LinkedHashMap<K, V> mMap = new LinkedHashMap<>();
    public int mSizeInBytes = 0;
    public final ValueDescriptor<V> mValueDescriptor;

    public CountingLruMap(ValueDescriptor<V> valueDescriptor) {
        this.mValueDescriptor = valueDescriptor;
    }

    public synchronized int getCount() {
        return this.mMap.size();
    }

    public synchronized ArrayList<Map.Entry<K, V>> getMatchingEntries(Predicate<K> predicate) {
        ArrayList<Map.Entry<K, V>> arrayList;
        arrayList = new ArrayList<>(this.mMap.entrySet().size());
        for (Map.Entry<K, V> entry : this.mMap.entrySet()) {
            if (predicate == null || predicate.apply(entry.getKey())) {
                arrayList.add(entry);
            }
        }
        return arrayList;
    }

    public synchronized int getSizeInBytes() {
        return this.mSizeInBytes;
    }

    public final int getValueSizeInBytes(V v) {
        if (v == null) {
            return 0;
        }
        return this.mValueDescriptor.getSizeInBytes(v);
    }

    public synchronized V put(K k, V v) {
        V remove;
        remove = this.mMap.remove(k);
        this.mSizeInBytes -= getValueSizeInBytes(remove);
        this.mMap.put(k, v);
        this.mSizeInBytes += getValueSizeInBytes(v);
        return remove;
    }

    public synchronized V remove(K k) {
        V remove;
        remove = this.mMap.remove(k);
        this.mSizeInBytes -= getValueSizeInBytes(remove);
        return remove;
    }

    public synchronized ArrayList<V> removeAll(Predicate<K> predicate) {
        ArrayList<V> arrayList;
        arrayList = new ArrayList<>();
        Iterator<Map.Entry<K, V>> it = this.mMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (predicate == null || predicate.apply(next.getKey())) {
                arrayList.add(next.getValue());
                this.mSizeInBytes -= getValueSizeInBytes(next.getValue());
                it.remove();
            }
        }
        return arrayList;
    }
}
