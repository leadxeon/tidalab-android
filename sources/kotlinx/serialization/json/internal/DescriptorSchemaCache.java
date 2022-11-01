package kotlinx.serialization.json.internal;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.functions.Function0;
import kotlinx.serialization.descriptors.SerialDescriptor;
/* compiled from: SchemaCache.kt */
/* loaded from: classes.dex */
public final class DescriptorSchemaCache {
    public final Map<SerialDescriptor, Map<Key<Object>, Object>> map = new ConcurrentHashMap(1);

    /* compiled from: SchemaCache.kt */
    /* loaded from: classes.dex */
    public static final class Key<T> {
    }

    public final <T> T get(SerialDescriptor serialDescriptor, Key<T> key) {
        Map<Key<Object>, Object> map = this.map.get(serialDescriptor);
        T t = map == null ? null : (T) map.get(key);
        if (t != null) {
            return t;
        }
        return null;
    }

    public final <T> T getOrPut(SerialDescriptor serialDescriptor, Key<T> key, Function0<? extends T> function0) {
        T t = (T) get(serialDescriptor, key);
        if (t != null) {
            return t;
        }
        T t2 = (T) function0.invoke();
        Map<SerialDescriptor, Map<Key<Object>, Object>> map = this.map;
        Map<Key<Object>, Object> map2 = map.get(serialDescriptor);
        if (map2 == null) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(1);
            map.put(serialDescriptor, concurrentHashMap);
            map2 = concurrentHashMap;
        }
        map2.put(key, t2);
        return t2;
    }
}
