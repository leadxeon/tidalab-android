package com.facebook.imagepipeline.producers;

import android.net.Uri;
import androidx.recyclerview.R$dimen;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.SimpleCacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.MemoryPooledByteBufferOutputStream;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.http2.Http2;
/* loaded from: classes.dex */
public class PartialDiskCacheProducer implements Producer<EncodedImage> {
    public final ByteArrayPool mByteArrayPool;
    public final CacheKeyFactory mCacheKeyFactory;
    public final BufferedDiskCache mDefaultBufferedDiskCache;
    public final Producer<EncodedImage> mInputProducer;
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    /* loaded from: classes.dex */
    public static class PartialDiskCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        public final ByteArrayPool mByteArrayPool;
        public final BufferedDiskCache mDefaultBufferedDiskCache;
        public final EncodedImage mPartialEncodedImageFromCache;
        public final CacheKey mPartialImageCacheKey;
        public final PooledByteBufferFactory mPooledByteBufferFactory;

        public PartialDiskCacheConsumer(Consumer consumer, BufferedDiskCache bufferedDiskCache, CacheKey cacheKey, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, EncodedImage encodedImage, AnonymousClass1 r7) {
            super(consumer);
            this.mDefaultBufferedDiskCache = bufferedDiskCache;
            this.mPartialImageCacheKey = cacheKey;
            this.mPooledByteBufferFactory = pooledByteBufferFactory;
            this.mByteArrayPool = byteArrayPool;
            this.mPartialEncodedImageFromCache = encodedImage;
        }

        public final void copy(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
            byte[] bArr = this.mByteArrayPool.get(Http2.INITIAL_MAX_FRAME_SIZE);
            int i2 = i;
            while (i2 > 0) {
                try {
                    int read = inputStream.read(bArr, 0, Math.min((int) Http2.INITIAL_MAX_FRAME_SIZE, i2));
                    if (read < 0) {
                        break;
                    } else if (read > 0) {
                        outputStream.write(bArr, 0, read);
                        i2 -= read;
                    }
                } finally {
                    this.mByteArrayPool.release(bArr);
                }
            }
            if (i2 > 0) {
                throw new IOException(String.format(null, "Failed to read %d bytes - finished %d short", Integer.valueOf(i), Integer.valueOf(i2)));
            }
        }

        public final PooledByteBufferOutputStream merge(EncodedImage encodedImage, EncodedImage encodedImage2) throws IOException {
            PooledByteBufferOutputStream newOutputStream = this.mPooledByteBufferFactory.newOutputStream(encodedImage2.getSize() + encodedImage2.mBytesRange.from);
            copy(encodedImage.getInputStream(), newOutputStream, encodedImage2.mBytesRange.from);
            copy(encodedImage2.getInputStream(), newOutputStream, encodedImage2.getSize());
            return newOutputStream;
        }

        /* JADX WARN: Finally extract failed */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v7, types: [com.facebook.imagepipeline.cache.BufferedDiskCache] */
        /* JADX WARN: Type inference failed for: r3v0, types: [com.facebook.imagepipeline.producers.PartialDiskCacheProducer$PartialDiskCacheConsumer, com.facebook.imagepipeline.producers.DelegatingConsumer] */
        /* JADX WARN: Type inference failed for: r4v1, types: [com.facebook.imagepipeline.image.EncodedImage, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r4v2, types: [com.facebook.imagepipeline.image.EncodedImage] */
        /* JADX WARN: Type inference failed for: r4v5, types: [com.facebook.imagepipeline.cache.BufferedDiskCache, java.lang.Object] */
        /* JADX WARN: Unknown variable types count: 1 */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onNewResultImpl(java.lang.Object r4, int r5) {
            /*
                r3 = this;
                com.facebook.imagepipeline.image.EncodedImage r4 = (com.facebook.imagepipeline.image.EncodedImage) r4
                boolean r0 = com.facebook.imagepipeline.producers.BaseConsumer.isNotLast(r5)
                if (r0 == 0) goto L_0x000a
                goto L_0x0093
            L_0x000a:
                com.facebook.imagepipeline.image.EncodedImage r0 = r3.mPartialEncodedImageFromCache
                if (r0 == 0) goto L_0x006a
                com.facebook.imagepipeline.common.BytesRange r1 = r4.mBytesRange
                if (r1 == 0) goto L_0x006a
                com.facebook.common.memory.PooledByteBufferOutputStream r5 = r3.merge(r0, r4)     // Catch: all -> 0x001a, IOException -> 0x001c
                r3.sendFinalResultToConsumer(r5)     // Catch: all -> 0x001a, IOException -> 0x001c
                goto L_0x0029
            L_0x001a:
                r5 = move-exception
                goto L_0x0061
            L_0x001c:
                r5 = move-exception
                java.lang.String r0 = "PartialDiskCacheProducer"
                java.lang.String r1 = "Error while merging image data"
                com.facebook.common.logging.FLog.e(r0, r1, r5)     // Catch: all -> 0x001a
                com.facebook.imagepipeline.producers.Consumer<O> r0 = r3.mConsumer     // Catch: all -> 0x001a
                r0.onFailure(r5)     // Catch: all -> 0x001a
            L_0x0029:
                r4.close()
                com.facebook.imagepipeline.image.EncodedImage r4 = r3.mPartialEncodedImageFromCache
                r4.close()
                com.facebook.imagepipeline.cache.BufferedDiskCache r4 = r3.mDefaultBufferedDiskCache
                com.facebook.cache.common.CacheKey r5 = r3.mPartialImageCacheKey
                java.util.Objects.requireNonNull(r4)
                java.util.Objects.requireNonNull(r5)
                com.facebook.imagepipeline.cache.StagingArea r0 = r4.mStagingArea
                r0.remove(r5)
                com.facebook.imagepipeline.cache.BufferedDiskCache$4 r0 = new com.facebook.imagepipeline.cache.BufferedDiskCache$4     // Catch: Exception -> 0x004b
                r0.<init>()     // Catch: Exception -> 0x004b
                java.util.concurrent.Executor r4 = r4.mWriteExecutor     // Catch: Exception -> 0x004b
                bolts.Task.call(r0, r4)     // Catch: Exception -> 0x004b
                goto L_0x0093
            L_0x004b:
                r4 = move-exception
                java.lang.Class<com.facebook.imagepipeline.cache.BufferedDiskCache> r0 = com.facebook.imagepipeline.cache.BufferedDiskCache.class
                r1 = 1
                java.lang.Object[] r1 = new java.lang.Object[r1]
                r2 = 0
                java.lang.String r5 = r5.getUriString()
                r1[r2] = r5
                java.lang.String r5 = "Failed to schedule disk-cache remove for %s"
                com.facebook.common.logging.FLog.w(r0, r4, r5, r1)
                bolts.Task.forError(r4)
                goto L_0x0093
            L_0x0061:
                r4.close()
                com.facebook.imagepipeline.image.EncodedImage r4 = r3.mPartialEncodedImageFromCache
                r4.close()
                throw r5
            L_0x006a:
                r0 = 8
                boolean r0 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r5, r0)
                if (r0 == 0) goto L_0x008e
                boolean r0 = com.facebook.imagepipeline.producers.BaseConsumer.isLast(r5)
                if (r0 == 0) goto L_0x008e
                r4.parseMetaDataIfNeeded()
                com.facebook.imageformat.ImageFormat r0 = r4.mImageFormat
                com.facebook.imageformat.ImageFormat r1 = com.facebook.imageformat.ImageFormat.UNKNOWN
                if (r0 == r1) goto L_0x008e
                com.facebook.imagepipeline.cache.BufferedDiskCache r0 = r3.mDefaultBufferedDiskCache
                com.facebook.cache.common.CacheKey r1 = r3.mPartialImageCacheKey
                r0.put(r1, r4)
                com.facebook.imagepipeline.producers.Consumer<O> r0 = r3.mConsumer
                r0.onNewResult(r4, r5)
                goto L_0x0093
            L_0x008e:
                com.facebook.imagepipeline.producers.Consumer<O> r0 = r3.mConsumer
                r0.onNewResult(r4, r5)
            L_0x0093:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.PartialDiskCacheConsumer.onNewResultImpl(java.lang.Object, int):void");
        }

        public final void sendFinalResultToConsumer(PooledByteBufferOutputStream pooledByteBufferOutputStream) {
            Throwable th;
            CloseableReference of = CloseableReference.of(((MemoryPooledByteBufferOutputStream) pooledByteBufferOutputStream).toByteBuffer());
            EncodedImage encodedImage = null;
            try {
                encodedImage = new EncodedImage(of);
                try {
                    encodedImage.parseMetaData();
                    this.mConsumer.onNewResult(encodedImage, 1);
                    encodedImage.close();
                    if (of != null) {
                        of.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (encodedImage != null) {
                        encodedImage.close();
                    }
                    if (of != null) {
                        of.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    public PartialDiskCacheProducer(BufferedDiskCache bufferedDiskCache, CacheKeyFactory cacheKeyFactory, PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, Producer<EncodedImage> producer) {
        this.mDefaultBufferedDiskCache = bufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mInputProducer = producer;
    }

    public static void access$100(PartialDiskCacheProducer partialDiskCacheProducer, Consumer consumer, ProducerContext producerContext, CacheKey cacheKey, EncodedImage encodedImage) {
        partialDiskCacheProducer.mInputProducer.produceResults(new PartialDiskCacheConsumer(consumer, partialDiskCacheProducer.mDefaultBufferedDiskCache, cacheKey, partialDiskCacheProducer.mPooledByteBufferFactory, partialDiskCacheProducer.mByteArrayPool, encodedImage, null), producerContext);
    }

    public static Map<String, String> getExtraMap(RequestListener requestListener, String str, boolean z, int i) {
        if (!requestListener.requiresExtraMap(str)) {
            return null;
        }
        if (z) {
            return ImmutableMap.of("cached_value_found", String.valueOf(z), "encodedImageSize", String.valueOf(i));
        }
        return ImmutableMap.of("cached_value_found", String.valueOf(z));
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        if (!imageRequest.mIsDiskCacheEnabled) {
            this.mInputProducer.produceResults(consumer, producerContext);
            return;
        }
        producerContext.getListener().onProducerStart(producerContext.getId(), "PartialDiskCacheProducer");
        Uri build = imageRequest.mSourceUri.buildUpon().appendQueryParameter("fresco_partial", "true").build();
        CacheKeyFactory cacheKeyFactory = this.mCacheKeyFactory;
        producerContext.getCallerContext();
        Objects.requireNonNull((DefaultCacheKeyFactory) cacheKeyFactory);
        final SimpleCacheKey simpleCacheKey = new SimpleCacheKey(build.toString());
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        Task<EncodedImage> task = this.mDefaultBufferedDiskCache.get(simpleCacheKey, atomicBoolean);
        final String id = producerContext.getId();
        final RequestListener listener = producerContext.getListener();
        task.continueWith(new Continuation<EncodedImage, Void>() { // from class: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.1
            @Override // bolts.Continuation
            public Void then(Task<EncodedImage> task2) throws Exception {
                boolean z;
                EncodedImage encodedImage;
                synchronized (task2.lock) {
                    z = task2.cancelled;
                }
                boolean z2 = false;
                if (z || (task2.isFaulted() && (task2.getError() instanceof CancellationException))) {
                    listener.onProducerFinishWithCancellation(id, "PartialDiskCacheProducer", null);
                    consumer.onCancellation();
                } else if (task2.isFaulted()) {
                    listener.onProducerFinishWithFailure(id, "PartialDiskCacheProducer", task2.getError(), null);
                    PartialDiskCacheProducer.access$100(PartialDiskCacheProducer.this, consumer, producerContext, simpleCacheKey, null);
                } else {
                    synchronized (task2.lock) {
                        encodedImage = task2.result;
                    }
                    EncodedImage encodedImage2 = encodedImage;
                    if (encodedImage2 != null) {
                        RequestListener requestListener = listener;
                        String str = id;
                        requestListener.onProducerFinishWithSuccess(str, "PartialDiskCacheProducer", PartialDiskCacheProducer.getExtraMap(requestListener, str, true, encodedImage2.getSize()));
                        int size = encodedImage2.getSize() - 1;
                        R$dimen.checkArgument(size > 0);
                        encodedImage2.mBytesRange = new BytesRange(0, size);
                        int size2 = encodedImage2.getSize();
                        ImageRequest imageRequest2 = producerContext.getImageRequest();
                        BytesRange bytesRange = imageRequest2.mBytesRange;
                        if (bytesRange != null && bytesRange.from >= 0 && size >= bytesRange.to) {
                            listener.onUltimateProducerReached(id, "PartialDiskCacheProducer", true);
                            consumer.onNewResult(encodedImage2, 9);
                        } else {
                            consumer.onNewResult(encodedImage2, 8);
                            ImageRequestBuilder newBuilderWithSource = ImageRequestBuilder.newBuilderWithSource(imageRequest2.mSourceUri);
                            newBuilderWithSource.mImageDecodeOptions = imageRequest2.mImageDecodeOptions;
                            newBuilderWithSource.mBytesRange = imageRequest2.mBytesRange;
                            newBuilderWithSource.mCacheChoice = imageRequest2.mCacheChoice;
                            newBuilderWithSource.mLocalThumbnailPreviewsEnabled = imageRequest2.mLocalThumbnailPreviewsEnabled;
                            newBuilderWithSource.mLowestPermittedRequestLevel = imageRequest2.mLowestPermittedRequestLevel;
                            newBuilderWithSource.mPostprocessor = imageRequest2.mPostprocessor;
                            newBuilderWithSource.mProgressiveRenderingEnabled = imageRequest2.mProgressiveRenderingEnabled;
                            newBuilderWithSource.mRequestPriority = imageRequest2.mRequestPriority;
                            newBuilderWithSource.mResizeOptions = imageRequest2.mResizeOptions;
                            newBuilderWithSource.mRequestListener = imageRequest2.mRequestListener;
                            newBuilderWithSource.mRotationOptions = imageRequest2.mRotationOptions;
                            newBuilderWithSource.mDecodePrefetches = imageRequest2.mDecodePrefetches;
                            int i = size2 - 1;
                            if (i >= 0) {
                                z2 = true;
                            }
                            R$dimen.checkArgument(z2);
                            newBuilderWithSource.mBytesRange = new BytesRange(i, Integer.MAX_VALUE);
                            PartialDiskCacheProducer.access$100(PartialDiskCacheProducer.this, consumer, new SettableProducerContext(newBuilderWithSource.build(), producerContext), simpleCacheKey, encodedImage2);
                        }
                    } else {
                        RequestListener requestListener2 = listener;
                        String str2 = id;
                        requestListener2.onProducerFinishWithSuccess(str2, "PartialDiskCacheProducer", PartialDiskCacheProducer.getExtraMap(requestListener2, str2, false, 0));
                        PartialDiskCacheProducer.access$100(PartialDiskCacheProducer.this, consumer, producerContext, simpleCacheKey, encodedImage2);
                    }
                }
                return null;
            }
        });
        producerContext.addCallbacks(new BaseProducerContextCallbacks(this) { // from class: com.facebook.imagepipeline.producers.PartialDiskCacheProducer.2
            @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                atomicBoolean.set(true);
            }
        });
    }
}
