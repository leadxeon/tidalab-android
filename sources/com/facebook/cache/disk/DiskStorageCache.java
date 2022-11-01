package com.facebook.cache.disk;

import android.os.StatFs;
import androidx.recyclerview.R$dimen;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.logging.FLog;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
/* loaded from: classes.dex */
public class DiskStorageCache implements FileCache {
    public final CacheErrorLogger mCacheErrorLogger;
    public final CacheEventListener mCacheEventListener;
    public long mCacheSizeLastUpdateTime;
    public long mCacheSizeLimit;
    public final CacheStats mCacheStats;
    public final Clock mClock;
    public final CountDownLatch mCountDownLatch;
    public final long mDefaultCacheSizeLimit;
    public final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    public final boolean mIndexPopulateAtStartupEnabled;
    public final Object mLock = new Object();
    public final long mLowDiskSpaceCacheSizeLimit;
    public final Set<String> mResourceIndex;
    public final StatFsHelper mStatFsHelper;
    public final DiskStorage mStorage;
    public static final long FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2);
    public static final long FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30);

    /* loaded from: classes.dex */
    public static class CacheStats {
        public boolean mInitialized = false;
        public long mSize = -1;
        public long mCount = -1;

        public synchronized long getSize() {
            return this.mSize;
        }

        public synchronized void increment(long j, long j2) {
            if (this.mInitialized) {
                this.mSize += j;
                this.mCount += j2;
            }
        }

        public synchronized void reset() {
            this.mInitialized = false;
            this.mCount = -1L;
            this.mSize = -1L;
        }
    }

    /* loaded from: classes.dex */
    public static class Params {
        public final long mCacheSizeLimitMinimum;
        public final long mDefaultCacheSizeLimit;
        public final long mLowDiskSpaceCacheSizeLimit;

        public Params(long j, long j2, long j3) {
            this.mCacheSizeLimitMinimum = j;
            this.mLowDiskSpaceCacheSizeLimit = j2;
            this.mDefaultCacheSizeLimit = j3;
        }
    }

    public DiskStorageCache(DiskStorage diskStorage, EntryEvictionComparatorSupplier entryEvictionComparatorSupplier, Params params, CacheEventListener cacheEventListener, CacheErrorLogger cacheErrorLogger, DiskTrimmableRegistry diskTrimmableRegistry, Executor executor, boolean z) {
        StatFsHelper statFsHelper;
        this.mLowDiskSpaceCacheSizeLimit = params.mLowDiskSpaceCacheSizeLimit;
        long j = params.mDefaultCacheSizeLimit;
        this.mDefaultCacheSizeLimit = j;
        this.mCacheSizeLimit = j;
        StatFsHelper statFsHelper2 = StatFsHelper.sStatsFsHelper;
        synchronized (StatFsHelper.class) {
            if (StatFsHelper.sStatsFsHelper == null) {
                StatFsHelper.sStatsFsHelper = new StatFsHelper();
            }
            statFsHelper = StatFsHelper.sStatsFsHelper;
        }
        this.mStatFsHelper = statFsHelper;
        this.mStorage = diskStorage;
        this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
        this.mCacheSizeLastUpdateTime = -1L;
        this.mCacheEventListener = cacheEventListener;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mCacheStats = new CacheStats();
        this.mClock = SystemClock.INSTANCE;
        this.mIndexPopulateAtStartupEnabled = z;
        this.mResourceIndex = new HashSet();
        if (z) {
            this.mCountDownLatch = new CountDownLatch(1);
            executor.execute(new Runnable() { // from class: com.facebook.cache.disk.DiskStorageCache.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (DiskStorageCache.this.mLock) {
                        DiskStorageCache.this.maybeUpdateFileCacheSize();
                    }
                    Objects.requireNonNull(DiskStorageCache.this);
                    DiskStorageCache.this.mCountDownLatch.countDown();
                }
            });
            return;
        }
        this.mCountDownLatch = new CountDownLatch(0);
    }

    public final void evictAboveSize$enumunboxing$(long j, int i) throws IOException {
        try {
            Collection<DiskStorage.Entry> sortedEntries = getSortedEntries(this.mStorage.getEntries());
            long size = this.mCacheStats.getSize() - j;
            int i2 = 0;
            Iterator it = ((ArrayList) sortedEntries).iterator();
            long j2 = 0;
            while (it.hasNext()) {
                DiskStorage.Entry entry = (DiskStorage.Entry) it.next();
                if (j2 > size) {
                    break;
                }
                long remove = this.mStorage.remove(entry);
                this.mResourceIndex.remove(entry.getId());
                if (remove > 0) {
                    i2++;
                    j2 += remove;
                    SettableCacheEvent obtain = SettableCacheEvent.obtain();
                    entry.getId();
                    Objects.requireNonNull((NoOpCacheEventListener) this.mCacheEventListener);
                    obtain.recycle();
                }
            }
            this.mCacheStats.increment(-j2, -i2);
            this.mStorage.purgeUnexpectedResources();
        } catch (IOException e) {
            CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
            e.getMessage();
            Objects.requireNonNull((NoOpCacheErrorLogger) cacheErrorLogger);
            throw e;
        }
    }

    public FileBinaryResource getResource(CacheKey cacheKey) {
        FileBinaryResource fileBinaryResource;
        SettableCacheEvent obtain = SettableCacheEvent.obtain();
        obtain.mCacheKey = cacheKey;
        try {
            synchronized (this.mLock) {
                List<String> resourceIds = R$dimen.getResourceIds(cacheKey);
                String str = null;
                fileBinaryResource = null;
                for (int i = 0; i < resourceIds.size() && (fileBinaryResource = this.mStorage.getResource((str = resourceIds.get(i)), cacheKey)) == null; i++) {
                }
                if (fileBinaryResource == null) {
                    Objects.requireNonNull((NoOpCacheEventListener) this.mCacheEventListener);
                    this.mResourceIndex.remove(str);
                } else {
                    Objects.requireNonNull((NoOpCacheEventListener) this.mCacheEventListener);
                    this.mResourceIndex.add(str);
                }
            }
            return fileBinaryResource;
        } catch (IOException unused) {
            Objects.requireNonNull((NoOpCacheErrorLogger) this.mCacheErrorLogger);
            Objects.requireNonNull((NoOpCacheEventListener) this.mCacheEventListener);
            return null;
        } finally {
            obtain.recycle();
        }
    }

    public final Collection<DiskStorage.Entry> getSortedEntries(Collection<DiskStorage.Entry> collection) {
        Objects.requireNonNull((SystemClock) this.mClock);
        long currentTimeMillis = System.currentTimeMillis() + FUTURE_TIMESTAMP_THRESHOLD_MS;
        ArrayList arrayList = new ArrayList(collection.size());
        ArrayList arrayList2 = new ArrayList(collection.size());
        for (DiskStorage.Entry entry : collection) {
            if (entry.getTimestamp() > currentTimeMillis) {
                arrayList.add(entry);
            } else {
                arrayList2.add(entry);
            }
        }
        Collections.sort(arrayList2, this.mEntryEvictionComparatorSupplier.get());
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    public boolean hasKey(CacheKey cacheKey) {
        synchronized (this.mLock) {
            if (hasKeySync(cacheKey)) {
                return true;
            }
            try {
                List<String> resourceIds = R$dimen.getResourceIds(cacheKey);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String str = resourceIds.get(i);
                    if (this.mStorage.contains(str, cacheKey)) {
                        this.mResourceIndex.add(str);
                        return true;
                    }
                }
                return false;
            } catch (IOException unused) {
                return false;
            }
        }
    }

    public boolean hasKeySync(CacheKey cacheKey) {
        synchronized (this.mLock) {
            List<String> resourceIds = R$dimen.getResourceIds(cacheKey);
            for (int i = 0; i < resourceIds.size(); i++) {
                if (this.mResourceIndex.contains(resourceIds.get(i))) {
                    return true;
                }
            }
            return false;
        }
    }

    public FileBinaryResource insert(CacheKey cacheKey, WriterCallback writerCallback) throws IOException {
        String str;
        FileBinaryResource commit;
        SettableCacheEvent obtain = SettableCacheEvent.obtain();
        obtain.mCacheKey = cacheKey;
        Objects.requireNonNull((NoOpCacheEventListener) this.mCacheEventListener);
        synchronized (this.mLock) {
            try {
                if (cacheKey instanceof MultiCacheKey) {
                    str = R$dimen.secureHashKey(((MultiCacheKey) cacheKey).mCacheKeys.get(0));
                } else {
                    str = R$dimen.secureHashKey(cacheKey);
                }
                try {
                } finally {
                    obtain.recycle();
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            DiskStorage.Inserter startInsert = startInsert(str, cacheKey);
            try {
                DefaultDiskStorage.InserterImpl inserterImpl = (DefaultDiskStorage.InserterImpl) startInsert;
                inserterImpl.writeData(writerCallback, cacheKey);
                synchronized (this.mLock) {
                    commit = inserterImpl.commit(cacheKey);
                    this.mResourceIndex.add(str);
                    this.mCacheStats.increment(commit.size(), 1L);
                }
                commit.size();
                this.mCacheStats.getSize();
                Objects.requireNonNull((NoOpCacheEventListener) this.mCacheEventListener);
                if (!inserterImpl.cleanUp()) {
                    FLog.e(DiskStorageCache.class, "Failed to delete temp file");
                }
                return commit;
            } catch (Throwable th) {
                if (!((DefaultDiskStorage.InserterImpl) startInsert).cleanUp()) {
                    FLog.e(DiskStorageCache.class, "Failed to delete temp file");
                }
                throw th;
            }
        } catch (IOException e2) {
            Objects.requireNonNull((NoOpCacheEventListener) this.mCacheEventListener);
            FLog.e(DiskStorageCache.class, "Failed inserting a file into the cache", e2);
            throw e2;
        }
    }

    public final boolean maybeUpdateFileCacheSize() {
        boolean z;
        Set<String> set;
        long j;
        Set<String> set2;
        Objects.requireNonNull((SystemClock) this.mClock);
        long currentTimeMillis = System.currentTimeMillis();
        CacheStats cacheStats = this.mCacheStats;
        synchronized (cacheStats) {
            z = cacheStats.mInitialized;
        }
        long j2 = -1;
        if (z) {
            long j3 = this.mCacheSizeLastUpdateTime;
            if (j3 != -1 && currentTimeMillis - j3 <= FILECACHE_SIZE_UPDATE_PERIOD_MS) {
                return false;
            }
        }
        Objects.requireNonNull((SystemClock) this.mClock);
        long currentTimeMillis2 = System.currentTimeMillis();
        long j4 = FUTURE_TIMESTAMP_THRESHOLD_MS + currentTimeMillis2;
        if (!this.mIndexPopulateAtStartupEnabled || !this.mResourceIndex.isEmpty()) {
            set = this.mIndexPopulateAtStartupEnabled ? new HashSet<>() : null;
        } else {
            set = this.mResourceIndex;
        }
        try {
            long j5 = 0;
            boolean z2 = false;
            int i = 0;
            for (DiskStorage.Entry entry : this.mStorage.getEntries()) {
                i++;
                j5 += entry.getSize();
                if (entry.getTimestamp() > j4) {
                    entry.getSize();
                    j4 = j4;
                    j2 = Math.max(entry.getTimestamp() - currentTimeMillis2, j2);
                    z2 = true;
                } else {
                    j4 = j4;
                    if (this.mIndexPopulateAtStartupEnabled) {
                        set.add(entry.getId());
                    }
                }
            }
            if (z2) {
                Objects.requireNonNull((NoOpCacheErrorLogger) this.mCacheErrorLogger);
            }
            CacheStats cacheStats2 = this.mCacheStats;
            synchronized (cacheStats2) {
                j = cacheStats2.mCount;
            }
            long j6 = i;
            if (!(j == j6 && this.mCacheStats.getSize() == j5)) {
                if (this.mIndexPopulateAtStartupEnabled && (set2 = this.mResourceIndex) != set) {
                    set2.clear();
                    this.mResourceIndex.addAll(set);
                }
                CacheStats cacheStats3 = this.mCacheStats;
                synchronized (cacheStats3) {
                    cacheStats3.mCount = j6;
                    cacheStats3.mSize = j5;
                    cacheStats3.mInitialized = true;
                }
            }
            this.mCacheSizeLastUpdateTime = currentTimeMillis2;
            return true;
        } catch (IOException e) {
            CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
            e.getMessage();
            Objects.requireNonNull((NoOpCacheErrorLogger) cacheErrorLogger);
            return false;
        }
    }

    public void remove(CacheKey cacheKey) {
        synchronized (this.mLock) {
            try {
                List<String> resourceIds = R$dimen.getResourceIds(cacheKey);
                for (int i = 0; i < resourceIds.size(); i++) {
                    String str = resourceIds.get(i);
                    this.mStorage.remove(str);
                    this.mResourceIndex.remove(str);
                }
            } catch (IOException e) {
                CacheErrorLogger cacheErrorLogger = this.mCacheErrorLogger;
                e.getMessage();
                Objects.requireNonNull((NoOpCacheErrorLogger) cacheErrorLogger);
            }
        }
    }

    public final DiskStorage.Inserter startInsert(String str, CacheKey cacheKey) throws IOException {
        synchronized (this.mLock) {
            boolean maybeUpdateFileCacheSize = maybeUpdateFileCacheSize();
            updateFileCacheSizeLimit();
            long size = this.mCacheStats.getSize();
            if (size > this.mCacheSizeLimit && !maybeUpdateFileCacheSize) {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
            }
            long j = this.mCacheSizeLimit;
            if (size > j) {
                evictAboveSize$enumunboxing$((j * 9) / 10, 1);
            }
        }
        return this.mStorage.insert(str, cacheKey);
    }

    public final void updateFileCacheSizeLimit() {
        boolean z = true;
        char c = this.mStorage.isExternal() ? (char) 2 : (char) 1;
        StatFsHelper statFsHelper = this.mStatFsHelper;
        long size = this.mDefaultCacheSizeLimit - this.mCacheStats.getSize();
        statFsHelper.ensureInitialized();
        statFsHelper.ensureInitialized();
        if (statFsHelper.lock.tryLock()) {
            try {
                if (android.os.SystemClock.uptimeMillis() - statFsHelper.mLastRestatTime > StatFsHelper.RESTAT_INTERVAL_MS) {
                    statFsHelper.updateStats();
                }
            } finally {
                statFsHelper.lock.unlock();
            }
        }
        StatFs statFs = c == 1 ? statFsHelper.mInternalStatFs : statFsHelper.mExternalStatFs;
        long availableBlocksLong = statFs != null ? statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong() : 0L;
        if (availableBlocksLong > 0 && availableBlocksLong >= size) {
            z = false;
        }
        if (z) {
            this.mCacheSizeLimit = this.mLowDiskSpaceCacheSizeLimit;
        } else {
            this.mCacheSizeLimit = this.mDefaultCacheSizeLimit;
        }
    }
}
