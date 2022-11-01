package androidx.collection;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes.dex */
public class LruCache<K, V> {
    public int evictionCount;
    public int hitCount;
    public final LinkedHashMap<K, V> map;
    public int maxSize;
    public int missCount;
    public int putCount;
    public int size;

    public LruCache(int i) {
        if (i > 0) {
            this.maxSize = i;
            this.map = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public final V get(K k) {
        Objects.requireNonNull(k, "key == null");
        synchronized (this) {
            V v = this.map.get(k);
            if (v != null) {
                this.hitCount++;
                return v;
            }
            this.missCount++;
            return null;
        }
    }

    public final V put(K k, V v) {
        V put;
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size++;
            put = this.map.put(k, v);
            if (put != null) {
                this.size--;
            }
        }
        trimToSize(this.maxSize);
        return put;
    }

    public final synchronized String toString() {
        int i;
        int i2;
        i = this.hitCount;
        i2 = this.missCount + i;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i2 != 0 ? (i * 100) / i2 : 0));
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0068, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void trimToSize(int r3) {
        /*
            r2 = this;
        L_0x0000:
            monitor-enter(r2)
            int r0 = r2.size     // Catch: all -> 0x0069
            if (r0 < 0) goto L_0x004a
            java.util.LinkedHashMap<K, V> r0 = r2.map     // Catch: all -> 0x0069
            boolean r0 = r0.isEmpty()     // Catch: all -> 0x0069
            if (r0 == 0) goto L_0x0011
            int r0 = r2.size     // Catch: all -> 0x0069
            if (r0 != 0) goto L_0x004a
        L_0x0011:
            int r0 = r2.size     // Catch: all -> 0x0069
            if (r0 <= r3) goto L_0x0048
            java.util.LinkedHashMap<K, V> r0 = r2.map     // Catch: all -> 0x0069
            boolean r0 = r0.isEmpty()     // Catch: all -> 0x0069
            if (r0 == 0) goto L_0x001e
            goto L_0x0048
        L_0x001e:
            java.util.LinkedHashMap<K, V> r0 = r2.map     // Catch: all -> 0x0069
            java.util.Set r0 = r0.entrySet()     // Catch: all -> 0x0069
            java.util.Iterator r0 = r0.iterator()     // Catch: all -> 0x0069
            java.lang.Object r0 = r0.next()     // Catch: all -> 0x0069
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: all -> 0x0069
            java.lang.Object r1 = r0.getKey()     // Catch: all -> 0x0069
            r0.getValue()     // Catch: all -> 0x0069
            java.util.LinkedHashMap<K, V> r0 = r2.map     // Catch: all -> 0x0069
            r0.remove(r1)     // Catch: all -> 0x0069
            int r0 = r2.size     // Catch: all -> 0x0069
            int r0 = r0 + (-1)
            r2.size = r0     // Catch: all -> 0x0069
            int r0 = r2.evictionCount     // Catch: all -> 0x0069
            int r0 = r0 + 1
            r2.evictionCount = r0     // Catch: all -> 0x0069
            monitor-exit(r2)     // Catch: all -> 0x0069
            goto L_0x0000
        L_0x0048:
            monitor-exit(r2)     // Catch: all -> 0x0069
            return
        L_0x004a:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException     // Catch: all -> 0x0069
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: all -> 0x0069
            r0.<init>()     // Catch: all -> 0x0069
            java.lang.Class r1 = r2.getClass()     // Catch: all -> 0x0069
            java.lang.String r1 = r1.getName()     // Catch: all -> 0x0069
            r0.append(r1)     // Catch: all -> 0x0069
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: all -> 0x0069
            java.lang.String r0 = r0.toString()     // Catch: all -> 0x0069
            r3.<init>(r0)     // Catch: all -> 0x0069
            throw r3     // Catch: all -> 0x0069
        L_0x0069:
            r3 = move-exception
            monitor-exit(r2)     // Catch: all -> 0x0069
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.trimToSize(int):void");
    }
}
