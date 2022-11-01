package com.facebook.imagepipeline.cache;

import android.os.SystemClock;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.WeakHashMap;
/* loaded from: classes.dex */
public class CountingMemoryCache<K, V> implements MemoryCache<K, V>, MemoryTrimmable {
    public final CountingLruMap<K, Entry<K, V>> mCachedEntries;
    public final CountingLruMap<K, Entry<K, V>> mExclusiveEntries;
    public long mLastCacheParamsCheck = SystemClock.uptimeMillis();
    public MemoryCacheParams mMemoryCacheParams;
    public final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    public final ValueDescriptor<V> mValueDescriptor;

    /* loaded from: classes.dex */
    public interface CacheTrimStrategy {
    }

    /* loaded from: classes.dex */
    public static class Entry<K, V> {
        public int clientCount = 0;
        public boolean isOrphan = false;
        public final K key;
        public final EntryStateObserver<K> observer;
        public final CloseableReference<V> valueRef;

        public Entry(K k, CloseableReference<V> closeableReference, EntryStateObserver<K> entryStateObserver) {
            Objects.requireNonNull(k);
            this.key = k;
            CloseableReference<V> cloneOrNull = CloseableReference.cloneOrNull(closeableReference);
            Objects.requireNonNull(cloneOrNull);
            this.valueRef = cloneOrNull;
            this.observer = entryStateObserver;
        }
    }

    /* loaded from: classes.dex */
    public interface EntryStateObserver<K> {
        void onExclusivityChanged(K k, boolean z);
    }

    public CountingMemoryCache(final ValueDescriptor<V> valueDescriptor, CacheTrimStrategy cacheTrimStrategy, Supplier<MemoryCacheParams> supplier) {
        new WeakHashMap();
        this.mValueDescriptor = valueDescriptor;
        this.mExclusiveEntries = new CountingLruMap<>(new ValueDescriptor<Entry<K, V>>(this) { // from class: com.facebook.imagepipeline.cache.CountingMemoryCache.1
            @Override // com.facebook.imagepipeline.cache.ValueDescriptor
            public int getSizeInBytes(Object obj) {
                return valueDescriptor.getSizeInBytes(((Entry) obj).valueRef.get());
            }
        });
        this.mCachedEntries = new CountingLruMap<>(new ValueDescriptor<Entry<K, V>>(this) { // from class: com.facebook.imagepipeline.cache.CountingMemoryCache.1
            @Override // com.facebook.imagepipeline.cache.ValueDescriptor
            public int getSizeInBytes(Object obj) {
                return valueDescriptor.getSizeInBytes(((Entry) obj).valueRef.get());
            }
        });
        this.mMemoryCacheParamsSupplier = supplier;
        this.mMemoryCacheParams = supplier.get();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    @Override // com.facebook.imagepipeline.cache.MemoryCache
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.common.references.CloseableReference<V> cache(K r8, com.facebook.common.references.CloseableReference<V> r9) {
        /*
            r7 = this;
            java.util.Objects.requireNonNull(r8)
            java.util.Objects.requireNonNull(r9)
            r7.maybeUpdateCacheParams()
            monitor-enter(r7)
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r0 = r7.mExclusiveEntries     // Catch: all -> 0x0071
            java.lang.Object r0 = r0.remove(r8)     // Catch: all -> 0x0071
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r0 = (com.facebook.imagepipeline.cache.CountingMemoryCache.Entry) r0     // Catch: all -> 0x0071
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r1 = r7.mCachedEntries     // Catch: all -> 0x0071
            java.lang.Object r1 = r1.remove(r8)     // Catch: all -> 0x0071
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r1 = (com.facebook.imagepipeline.cache.CountingMemoryCache.Entry) r1     // Catch: all -> 0x0071
            r2 = 0
            if (r1 == 0) goto L_0x0025
            r7.makeOrphan(r1)     // Catch: all -> 0x0071
            com.facebook.common.references.CloseableReference r1 = r7.referenceToClose(r1)     // Catch: all -> 0x0071
            goto L_0x0026
        L_0x0025:
            r1 = r2
        L_0x0026:
            java.lang.Object r3 = r9.get()     // Catch: all -> 0x0071
            monitor-enter(r7)     // Catch: all -> 0x0071
            com.facebook.imagepipeline.cache.ValueDescriptor<V> r4 = r7.mValueDescriptor     // Catch: all -> 0x006e
            int r3 = r4.getSizeInBytes(r3)     // Catch: all -> 0x006e
            com.facebook.imagepipeline.cache.MemoryCacheParams r4 = r7.mMemoryCacheParams     // Catch: all -> 0x006e
            int r4 = r4.maxCacheEntrySize     // Catch: all -> 0x006e
            r5 = 1
            if (r3 > r4) goto L_0x004f
            int r4 = r7.getInUseCount()     // Catch: all -> 0x006e
            com.facebook.imagepipeline.cache.MemoryCacheParams r6 = r7.mMemoryCacheParams     // Catch: all -> 0x006e
            int r6 = r6.maxCacheEntries     // Catch: all -> 0x006e
            int r6 = r6 - r5
            if (r4 > r6) goto L_0x004f
            int r4 = r7.getInUseSizeInBytes()     // Catch: all -> 0x006e
            com.facebook.imagepipeline.cache.MemoryCacheParams r6 = r7.mMemoryCacheParams     // Catch: all -> 0x006e
            int r6 = r6.maxCacheSize     // Catch: all -> 0x006e
            int r6 = r6 - r3
            if (r4 > r6) goto L_0x004f
            goto L_0x0050
        L_0x004f:
            r5 = 0
        L_0x0050:
            monitor-exit(r7)     // Catch: all -> 0x0071
            if (r5 == 0) goto L_0x0061
            com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r3 = new com.facebook.imagepipeline.cache.CountingMemoryCache$Entry     // Catch: all -> 0x0071
            r3.<init>(r8, r9, r2)     // Catch: all -> 0x0071
            com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r9 = r7.mCachedEntries     // Catch: all -> 0x0071
            r9.put(r8, r3)     // Catch: all -> 0x0071
            com.facebook.common.references.CloseableReference r2 = r7.newClientReference(r3)     // Catch: all -> 0x0071
        L_0x0061:
            monitor-exit(r7)     // Catch: all -> 0x0071
            if (r1 == 0) goto L_0x0067
            r1.close()
        L_0x0067:
            maybeNotifyExclusiveEntryRemoval(r0)
            r7.maybeEvictEntries()
            return r2
        L_0x006e:
            r8 = move-exception
            monitor-exit(r7)     // Catch: all -> 0x0071
            throw r8     // Catch: all -> 0x0071
        L_0x0071:
            r8 = move-exception
            monitor-exit(r7)     // Catch: all -> 0x0071
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.CountingMemoryCache.cache(java.lang.Object, com.facebook.common.references.CloseableReference):com.facebook.common.references.CloseableReference");
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public synchronized boolean contains(Predicate<K> predicate) {
        return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public CloseableReference<V> get(K k) {
        Entry<K, V> remove;
        Entry<K, V> entry;
        Objects.requireNonNull(k);
        CloseableReference<V> closeableReference = null;
        synchronized (this) {
            remove = this.mExclusiveEntries.remove(k);
            CountingLruMap<K, Entry<K, V>> countingLruMap = this.mCachedEntries;
            synchronized (countingLruMap) {
                entry = countingLruMap.mMap.get(k);
            }
            Entry<K, V> entry2 = entry;
            if (entry2 != null) {
                closeableReference = newClientReference(entry2);
            }
        }
        maybeNotifyExclusiveEntryRemoval(remove);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return closeableReference;
    }

    public synchronized int getInUseCount() {
        return this.mCachedEntries.getCount() - this.mExclusiveEntries.getCount();
    }

    public synchronized int getInUseSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes() - this.mExclusiveEntries.getSizeInBytes();
    }

    public final synchronized void makeOrphan(Entry<K, V> entry) {
        Objects.requireNonNull(entry);
        R$dimen.checkState(!entry.isOrphan);
        entry.isOrphan = true;
    }

    public final synchronized void makeOrphans(ArrayList<Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<Entry<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                makeOrphan(it.next());
            }
        }
    }

    public final void maybeClose(ArrayList<Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<Entry<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                CloseableReference<V> referenceToClose = referenceToClose(it.next());
                Class<CloseableReference> cls = CloseableReference.TAG;
                if (referenceToClose != null) {
                    referenceToClose.close();
                }
            }
        }
    }

    public final void maybeEvictEntries() {
        ArrayList<Entry<K, V>> trimExclusivelyOwnedEntries;
        synchronized (this) {
            MemoryCacheParams memoryCacheParams = this.mMemoryCacheParams;
            int min = Math.min(memoryCacheParams.maxEvictionQueueEntries, memoryCacheParams.maxCacheEntries - getInUseCount());
            MemoryCacheParams memoryCacheParams2 = this.mMemoryCacheParams;
            trimExclusivelyOwnedEntries = trimExclusivelyOwnedEntries(min, Math.min(memoryCacheParams2.maxEvictionQueueSize, memoryCacheParams2.maxCacheSize - getInUseSizeInBytes()));
            makeOrphans(trimExclusivelyOwnedEntries);
        }
        maybeClose(trimExclusivelyOwnedEntries);
        maybeNotifyExclusiveEntryRemoval(trimExclusivelyOwnedEntries);
    }

    public final void maybeNotifyExclusiveEntryRemoval(ArrayList<Entry<K, V>> arrayList) {
        if (arrayList != null) {
            Iterator<Entry<K, V>> it = arrayList.iterator();
            while (it.hasNext()) {
                maybeNotifyExclusiveEntryRemoval(it.next());
            }
        }
    }

    public final synchronized void maybeUpdateCacheParams() {
        if (this.mLastCacheParamsCheck + this.mMemoryCacheParams.paramsCheckIntervalMs <= SystemClock.uptimeMillis()) {
            this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
            this.mMemoryCacheParams = this.mMemoryCacheParamsSupplier.get();
        }
    }

    public final synchronized CloseableReference<V> newClientReference(final Entry<K, V> entry) {
        synchronized (this) {
            R$dimen.checkState(!entry.isOrphan);
            entry.clientCount++;
        }
        return CloseableReference.of(entry.valueRef.get(), new ResourceReleaser<V>() { // from class: com.facebook.imagepipeline.cache.CountingMemoryCache.2
            /* JADX WARN: Removed duplicated region for block: B:24:0x003b  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x0041  */
            @Override // com.facebook.common.references.ResourceReleaser
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void release(V r6) {
                /*
                    r5 = this;
                    com.facebook.imagepipeline.cache.CountingMemoryCache r6 = com.facebook.imagepipeline.cache.CountingMemoryCache.this
                    com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r0 = r2
                    java.util.Objects.requireNonNull(r6)
                    java.util.Objects.requireNonNull(r0)
                    monitor-enter(r6)
                    monitor-enter(r6)     // Catch: all -> 0x005a
                    int r1 = r0.clientCount     // Catch: all -> 0x0057
                    r2 = 0
                    r3 = 1
                    if (r1 <= 0) goto L_0x0014
                    r1 = 1
                    goto L_0x0015
                L_0x0014:
                    r1 = 0
                L_0x0015:
                    androidx.recyclerview.R$dimen.checkState(r1)     // Catch: all -> 0x0057
                    int r1 = r0.clientCount     // Catch: all -> 0x0057
                    int r1 = r1 - r3
                    r0.clientCount = r1     // Catch: all -> 0x0057
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    monitor-enter(r6)     // Catch: all -> 0x005a
                    boolean r1 = r0.isOrphan     // Catch: all -> 0x0054
                    if (r1 != 0) goto L_0x0031
                    int r1 = r0.clientCount     // Catch: all -> 0x0054
                    if (r1 != 0) goto L_0x0031
                    com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r1 = r6.mExclusiveEntries     // Catch: all -> 0x0054
                    K r2 = r0.key     // Catch: all -> 0x0054
                    r1.put(r2, r0)     // Catch: all -> 0x0054
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    r2 = 1
                    goto L_0x0032
                L_0x0031:
                    monitor-exit(r6)     // Catch: all -> 0x005a
                L_0x0032:
                    com.facebook.common.references.CloseableReference r1 = r6.referenceToClose(r0)     // Catch: all -> 0x005a
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    java.lang.Class<com.facebook.common.references.CloseableReference> r4 = com.facebook.common.references.CloseableReference.TAG
                    if (r1 == 0) goto L_0x003e
                    r1.close()
                L_0x003e:
                    if (r2 == 0) goto L_0x0041
                    goto L_0x0042
                L_0x0041:
                    r0 = 0
                L_0x0042:
                    if (r0 == 0) goto L_0x004d
                    com.facebook.imagepipeline.cache.CountingMemoryCache$EntryStateObserver<K> r1 = r0.observer
                    if (r1 == 0) goto L_0x004d
                    K r0 = r0.key
                    r1.onExclusivityChanged(r0, r3)
                L_0x004d:
                    r6.maybeUpdateCacheParams()
                    r6.maybeEvictEntries()
                    return
                L_0x0054:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    throw r0     // Catch: all -> 0x005a
                L_0x0057:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    throw r0     // Catch: all -> 0x005a
                L_0x005a:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.CountingMemoryCache.AnonymousClass2.release(java.lang.Object):void");
            }
        });
        return CloseableReference.of(entry.valueRef.get(), new ResourceReleaser<V>() { // from class: com.facebook.imagepipeline.cache.CountingMemoryCache.2
            @Override // com.facebook.common.references.ResourceReleaser
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void release(V r6) {
                /*
                    r5 = this;
                    com.facebook.imagepipeline.cache.CountingMemoryCache r6 = com.facebook.imagepipeline.cache.CountingMemoryCache.this
                    com.facebook.imagepipeline.cache.CountingMemoryCache$Entry r0 = r2
                    java.util.Objects.requireNonNull(r6)
                    java.util.Objects.requireNonNull(r0)
                    monitor-enter(r6)
                    monitor-enter(r6)     // Catch: all -> 0x005a
                    int r1 = r0.clientCount     // Catch: all -> 0x0057
                    r2 = 0
                    r3 = 1
                    if (r1 <= 0) goto L_0x0014
                    r1 = 1
                    goto L_0x0015
                L_0x0014:
                    r1 = 0
                L_0x0015:
                    androidx.recyclerview.R$dimen.checkState(r1)     // Catch: all -> 0x0057
                    int r1 = r0.clientCount     // Catch: all -> 0x0057
                    int r1 = r1 - r3
                    r0.clientCount = r1     // Catch: all -> 0x0057
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    monitor-enter(r6)     // Catch: all -> 0x005a
                    boolean r1 = r0.isOrphan     // Catch: all -> 0x0054
                    if (r1 != 0) goto L_0x0031
                    int r1 = r0.clientCount     // Catch: all -> 0x0054
                    if (r1 != 0) goto L_0x0031
                    com.facebook.imagepipeline.cache.CountingLruMap<K, com.facebook.imagepipeline.cache.CountingMemoryCache$Entry<K, V>> r1 = r6.mExclusiveEntries     // Catch: all -> 0x0054
                    K r2 = r0.key     // Catch: all -> 0x0054
                    r1.put(r2, r0)     // Catch: all -> 0x0054
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    r2 = 1
                    goto L_0x0032
                L_0x0031:
                    monitor-exit(r6)     // Catch: all -> 0x005a
                L_0x0032:
                    com.facebook.common.references.CloseableReference r1 = r6.referenceToClose(r0)     // Catch: all -> 0x005a
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    java.lang.Class<com.facebook.common.references.CloseableReference> r4 = com.facebook.common.references.CloseableReference.TAG
                    if (r1 == 0) goto L_0x003e
                    r1.close()
                L_0x003e:
                    if (r2 == 0) goto L_0x0041
                    goto L_0x0042
                L_0x0041:
                    r0 = 0
                L_0x0042:
                    if (r0 == 0) goto L_0x004d
                    com.facebook.imagepipeline.cache.CountingMemoryCache$EntryStateObserver<K> r1 = r0.observer
                    if (r1 == 0) goto L_0x004d
                    K r0 = r0.key
                    r1.onExclusivityChanged(r0, r3)
                L_0x004d:
                    r6.maybeUpdateCacheParams()
                    r6.maybeEvictEntries()
                    return
                L_0x0054:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    throw r0     // Catch: all -> 0x005a
                L_0x0057:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    throw r0     // Catch: all -> 0x005a
                L_0x005a:
                    r0 = move-exception
                    monitor-exit(r6)     // Catch: all -> 0x005a
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.cache.CountingMemoryCache.AnonymousClass2.release(java.lang.Object):void");
            }
        });
    }

    public final synchronized CloseableReference<V> referenceToClose(Entry<K, V> entry) {
        Objects.requireNonNull(entry);
        return (!entry.isOrphan || entry.clientCount != 0) ? null : entry.valueRef;
    }

    @Override // com.facebook.imagepipeline.cache.MemoryCache
    public int removeAll(Predicate<K> predicate) {
        ArrayList<Entry<K, V>> removeAll;
        ArrayList<Entry<K, V>> removeAll2;
        synchronized (this) {
            removeAll = this.mExclusiveEntries.removeAll(predicate);
            removeAll2 = this.mCachedEntries.removeAll(predicate);
            makeOrphans(removeAll2);
        }
        maybeClose(removeAll2);
        maybeNotifyExclusiveEntryRemoval(removeAll);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return removeAll2.size();
    }

    public final synchronized ArrayList<Entry<K, V>> trimExclusivelyOwnedEntries(int i, int i2) {
        K next;
        int max = Math.max(i, 0);
        int max2 = Math.max(i2, 0);
        if (this.mExclusiveEntries.getCount() <= max && this.mExclusiveEntries.getSizeInBytes() <= max2) {
            return null;
        }
        ArrayList<Entry<K, V>> arrayList = new ArrayList<>();
        while (true) {
            if (this.mExclusiveEntries.getCount() <= max && this.mExclusiveEntries.getSizeInBytes() <= max2) {
                return arrayList;
            }
            CountingLruMap<K, Entry<K, V>> countingLruMap = this.mExclusiveEntries;
            synchronized (countingLruMap) {
                next = countingLruMap.mMap.isEmpty() ? null : countingLruMap.mMap.keySet().iterator().next();
            }
            this.mExclusiveEntries.remove(next);
            arrayList.add(this.mCachedEntries.remove(next));
        }
    }

    public static <K, V> void maybeNotifyExclusiveEntryRemoval(Entry<K, V> entry) {
        EntryStateObserver<K> entryStateObserver;
        if (entry != null && (entryStateObserver = entry.observer) != null) {
            entryStateObserver.onExclusivityChanged(entry.key, false);
        }
    }
}
