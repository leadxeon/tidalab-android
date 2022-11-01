package com.facebook.common.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class ImmutableMap<K, V> extends HashMap<K, V> {
    public ImmutableMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    public static <K, V> Map<K, V> of(K k, V v) {
        HashMap hashMap = new HashMap(1);
        hashMap.put(k, v);
        return Collections.unmodifiableMap(hashMap);
    }

    public static <K, V> Map<K, V> of(K k, V v, K k2, V v2) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(k, v);
        hashMap.put(k2, v2);
        return Collections.unmodifiableMap(hashMap);
    }
}
