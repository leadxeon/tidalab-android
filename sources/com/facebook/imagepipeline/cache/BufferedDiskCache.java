package com.facebook.imagepipeline.cache;

import androidx.recyclerview.R$dimen;
import bolts.Task;
import com.facebook.binaryresource.FileBinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorageCache;
import com.facebook.cache.disk.FileCache;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteStreams;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class BufferedDiskCache {
    public final FileCache mFileCache;
    public final ImageCacheStatsTracker mImageCacheStatsTracker;
    public final PooledByteBufferFactory mPooledByteBufferFactory;
    public final PooledByteStreams mPooledByteStreams;
    public final Executor mReadExecutor;
    public final StagingArea mStagingArea = new StagingArea();
    public final Executor mWriteExecutor;

    /* renamed from: com.facebook.imagepipeline.cache.BufferedDiskCache$6  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass6 implements WriterCallback {
        public final /* synthetic */ EncodedImage val$encodedImage;

        public AnonymousClass6(EncodedImage encodedImage) {
            this.val$encodedImage = encodedImage;
        }
    }

    public BufferedDiskCache(FileCache fileCache, PooledByteBufferFactory pooledByteBufferFactory, PooledByteStreams pooledByteStreams, Executor executor, Executor executor2, ImageCacheStatsTracker imageCacheStatsTracker) {
        this.mFileCache = fileCache;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mPooledByteStreams = pooledByteStreams;
        this.mReadExecutor = executor;
        this.mWriteExecutor = executor2;
        this.mImageCacheStatsTracker = imageCacheStatsTracker;
    }

    public static PooledByteBuffer access$400(BufferedDiskCache bufferedDiskCache, CacheKey cacheKey) throws IOException {
        Objects.requireNonNull(bufferedDiskCache);
        try {
            cacheKey.getUriString();
            int i = FLog.$r8$clinit;
            FileBinaryResource resource = ((DiskStorageCache) bufferedDiskCache.mFileCache).getResource(cacheKey);
            if (resource == null) {
                cacheKey.getUriString();
                Objects.requireNonNull((NoOpImageCacheStatsTracker) bufferedDiskCache.mImageCacheStatsTracker);
                return null;
            }
            cacheKey.getUriString();
            Objects.requireNonNull((NoOpImageCacheStatsTracker) bufferedDiskCache.mImageCacheStatsTracker);
            FileInputStream fileInputStream = new FileInputStream(resource.mFile);
            PooledByteBuffer newByteBuffer = bufferedDiskCache.mPooledByteBufferFactory.newByteBuffer(fileInputStream, (int) resource.size());
            fileInputStream.close();
            cacheKey.getUriString();
            return newByteBuffer;
        } catch (IOException e) {
            FLog.w(BufferedDiskCache.class, e, "Exception reading from cache for %s", cacheKey.getUriString());
            Objects.requireNonNull((NoOpImageCacheStatsTracker) bufferedDiskCache.mImageCacheStatsTracker);
            throw e;
        }
    }

    public static void access$500(BufferedDiskCache bufferedDiskCache, CacheKey cacheKey, EncodedImage encodedImage) {
        Objects.requireNonNull(bufferedDiskCache);
        cacheKey.getUriString();
        int i = FLog.$r8$clinit;
        try {
            ((DiskStorageCache) bufferedDiskCache.mFileCache).insert(cacheKey, new AnonymousClass6(encodedImage));
            cacheKey.getUriString();
        } catch (IOException e) {
            FLog.w(BufferedDiskCache.class, e, "Failed to write to disk-cache for key %s", cacheKey.getUriString());
        }
    }

    public Task<Void> clearAll() {
        this.mStagingArea.clearAll();
        try {
            return Task.call(new Callable<Void>() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache.5
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    BufferedDiskCache.this.mStagingArea.clearAll();
                    DiskStorageCache diskStorageCache = (DiskStorageCache) BufferedDiskCache.this.mFileCache;
                    synchronized (diskStorageCache.mLock) {
                        try {
                            diskStorageCache.mStorage.clearAll();
                            diskStorageCache.mResourceIndex.clear();
                            Objects.requireNonNull((NoOpCacheEventListener) diskStorageCache.mCacheEventListener);
                        } catch (IOException | NullPointerException e) {
                            CacheErrorLogger cacheErrorLogger = diskStorageCache.mCacheErrorLogger;
                            e.getMessage();
                            Objects.requireNonNull((NoOpCacheErrorLogger) cacheErrorLogger);
                        }
                        diskStorageCache.mCacheStats.reset();
                    }
                    return null;
                }
            }, this.mWriteExecutor);
        } catch (Exception e) {
            FLog.w(BufferedDiskCache.class, e, "Failed to schedule disk-cache clear", new Object[0]);
            return Task.forError(e);
        }
    }

    public boolean diskCheckSync(CacheKey cacheKey) {
        boolean z;
        StagingArea stagingArea = this.mStagingArea;
        synchronized (stagingArea) {
            if (stagingArea.mMap.containsKey(cacheKey)) {
                EncodedImage encodedImage = stagingArea.mMap.get(cacheKey);
                synchronized (encodedImage) {
                    if (!EncodedImage.isValid(encodedImage)) {
                        stagingArea.mMap.remove(cacheKey);
                        FLog.w(StagingArea.class, "Found closed reference %d for key %s (%d)", Integer.valueOf(System.identityHashCode(encodedImage)), ((SimpleCacheKey) cacheKey).mKey, Integer.valueOf(System.identityHashCode(cacheKey)));
                    } else {
                        z = true;
                    }
                }
            }
            z = false;
        }
        if (z || ((DiskStorageCache) this.mFileCache).hasKeySync(cacheKey)) {
            return true;
        }
        EncodedImage encodedImage2 = this.mStagingArea.get(cacheKey);
        if (encodedImage2 != null) {
            encodedImage2.close();
            int i = FLog.$r8$clinit;
            Objects.requireNonNull((NoOpImageCacheStatsTracker) this.mImageCacheStatsTracker);
            return true;
        }
        int i2 = FLog.$r8$clinit;
        Objects.requireNonNull((NoOpImageCacheStatsTracker) this.mImageCacheStatsTracker);
        try {
            return ((DiskStorageCache) this.mFileCache).hasKey(cacheKey);
        } catch (Exception unused) {
            return false;
        }
    }

    public final Task<EncodedImage> foundPinnedImage(CacheKey cacheKey, EncodedImage encodedImage) {
        cacheKey.getUriString();
        int i = FLog.$r8$clinit;
        Objects.requireNonNull((NoOpImageCacheStatsTracker) this.mImageCacheStatsTracker);
        Executor executor = Task.IMMEDIATE_EXECUTOR;
        if (encodedImage instanceof Boolean) {
            return ((Boolean) encodedImage).booleanValue() ? Task.TASK_TRUE : Task.TASK_FALSE;
        }
        Task<EncodedImage> task = new Task<>();
        if (task.trySetResult(encodedImage)) {
            return task;
        }
        throw new IllegalStateException("Cannot set the result of a completed task.");
    }

    public Task<EncodedImage> get(final CacheKey cacheKey, final AtomicBoolean atomicBoolean) {
        Task<EncodedImage> task;
        try {
            FrescoSystrace.isTracing();
            EncodedImage encodedImage = this.mStagingArea.get(cacheKey);
            if (encodedImage != null) {
                return foundPinnedImage(cacheKey, encodedImage);
            }
            try {
                task = Task.call(new Callable<EncodedImage>() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache.2
                    @Override // java.util.concurrent.Callable
                    public EncodedImage call() throws Exception {
                        PooledByteBuffer access$400;
                        try {
                            FrescoSystrace.isTracing();
                            if (!atomicBoolean.get()) {
                                EncodedImage encodedImage2 = BufferedDiskCache.this.mStagingArea.get(cacheKey);
                                if (encodedImage2 != null) {
                                    cacheKey.getUriString();
                                    int i = FLog.$r8$clinit;
                                    Objects.requireNonNull((NoOpImageCacheStatsTracker) BufferedDiskCache.this.mImageCacheStatsTracker);
                                } else {
                                    cacheKey.getUriString();
                                    int i2 = FLog.$r8$clinit;
                                    Objects.requireNonNull((NoOpImageCacheStatsTracker) BufferedDiskCache.this.mImageCacheStatsTracker);
                                    encodedImage2 = null;
                                    try {
                                        access$400 = BufferedDiskCache.access$400(BufferedDiskCache.this, cacheKey);
                                    } catch (Exception unused) {
                                    }
                                    if (access$400 == null) {
                                        return encodedImage2;
                                    }
                                    CloseableReference of = CloseableReference.of(access$400);
                                    try {
                                        encodedImage2 = new EncodedImage(of);
                                    } finally {
                                        if (of != null) {
                                            of.close();
                                        }
                                    }
                                }
                                if (!Thread.interrupted()) {
                                    return encodedImage2;
                                }
                                FLog.v(BufferedDiskCache.class, "Host thread was interrupted, decreasing reference count");
                                encodedImage2.close();
                                throw new InterruptedException();
                            }
                            throw new CancellationException();
                        } finally {
                            FrescoSystrace.isTracing();
                        }
                    }
                }, this.mReadExecutor);
            } catch (Exception e) {
                FLog.w(BufferedDiskCache.class, e, "Failed to schedule disk-cache read for %s", ((SimpleCacheKey) cacheKey).mKey);
                task = Task.forError(e);
            }
            return task;
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    public void put(final CacheKey cacheKey, EncodedImage encodedImage) {
        try {
            FrescoSystrace.isTracing();
            Objects.requireNonNull(cacheKey);
            R$dimen.checkArgument(EncodedImage.isValid(encodedImage));
            this.mStagingArea.put(cacheKey, encodedImage);
            final EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
            try {
                this.mWriteExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.cache.BufferedDiskCache.3
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            FrescoSystrace.isTracing();
                            BufferedDiskCache.access$500(BufferedDiskCache.this, cacheKey, cloneOrNull);
                        } finally {
                            BufferedDiskCache.this.mStagingArea.remove(cacheKey, cloneOrNull);
                            EncodedImage encodedImage2 = cloneOrNull;
                            if (encodedImage2 != null) {
                                encodedImage2.close();
                            }
                            FrescoSystrace.isTracing();
                        }
                    }
                });
            } catch (Exception e) {
                FLog.w(BufferedDiskCache.class, e, "Failed to schedule disk-cache write for %s", cacheKey.getUriString());
                this.mStagingArea.remove(cacheKey, encodedImage);
                if (cloneOrNull != null) {
                    cloneOrNull.close();
                }
            }
        } finally {
            FrescoSystrace.isTracing();
        }
    }
}
