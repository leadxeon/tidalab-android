package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Supplier;
import java.io.File;
import java.util.Objects;
/* loaded from: classes.dex */
public class DiskCacheConfig {
    public final Supplier<File> mBaseDirectoryPathSupplier;
    public final CacheErrorLogger mCacheErrorLogger;
    public final CacheEventListener mCacheEventListener;
    public final Context mContext;
    public final DiskTrimmableRegistry mDiskTrimmableRegistry;
    public final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    public final int mVersion = 1;
    public final String mBaseDirectoryName = "image_cache";
    public final long mDefaultSizeLimit = 41943040;
    public final long mLowDiskSpaceSizeLimit = 10485760;
    public final long mMinimumSizeLimit = 2097152;

    /* loaded from: classes.dex */
    public static class Builder {
        public Supplier<File> mBaseDirectoryPathSupplier;
        public final Context mContext;
        public EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier = new DefaultEntryEvictionComparatorSupplier();

        public Builder(Context context, AnonymousClass1 r2) {
            this.mContext = context;
        }
    }

    public DiskCacheConfig(Builder builder, AnonymousClass1 r4) {
        NoOpCacheErrorLogger noOpCacheErrorLogger;
        NoOpCacheEventListener noOpCacheEventListener;
        NoOpDiskTrimmableRegistry noOpDiskTrimmableRegistry;
        Supplier<File> supplier = builder.mBaseDirectoryPathSupplier;
        Objects.requireNonNull(supplier);
        this.mBaseDirectoryPathSupplier = supplier;
        EntryEvictionComparatorSupplier entryEvictionComparatorSupplier = builder.mEntryEvictionComparatorSupplier;
        Objects.requireNonNull(entryEvictionComparatorSupplier);
        this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
        synchronized (NoOpCacheErrorLogger.class) {
            if (NoOpCacheErrorLogger.sInstance == null) {
                NoOpCacheErrorLogger.sInstance = new NoOpCacheErrorLogger();
            }
            noOpCacheErrorLogger = NoOpCacheErrorLogger.sInstance;
        }
        this.mCacheErrorLogger = noOpCacheErrorLogger;
        synchronized (NoOpCacheEventListener.class) {
            if (NoOpCacheEventListener.sInstance == null) {
                NoOpCacheEventListener.sInstance = new NoOpCacheEventListener();
            }
            noOpCacheEventListener = NoOpCacheEventListener.sInstance;
        }
        this.mCacheEventListener = noOpCacheEventListener;
        synchronized (NoOpDiskTrimmableRegistry.class) {
            if (NoOpDiskTrimmableRegistry.sInstance == null) {
                NoOpDiskTrimmableRegistry.sInstance = new NoOpDiskTrimmableRegistry();
            }
            noOpDiskTrimmableRegistry = NoOpDiskTrimmableRegistry.sInstance;
        }
        this.mDiskTrimmableRegistry = noOpDiskTrimmableRegistry;
        this.mContext = builder.mContext;
    }
}
