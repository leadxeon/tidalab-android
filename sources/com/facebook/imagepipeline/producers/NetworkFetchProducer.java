package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.MemoryPooledByteBufferOutputStream;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import okhttp3.internal.http2.Http2;
/* loaded from: classes.dex */
public class NetworkFetchProducer implements Producer<EncodedImage> {
    public final ByteArrayPool mByteArrayPool;
    public final NetworkFetcher mNetworkFetcher;
    public final PooledByteBufferFactory mPooledByteBufferFactory;

    /* renamed from: com.facebook.imagepipeline.producers.NetworkFetchProducer$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements NetworkFetcher.Callback {
        public final /* synthetic */ FetchState val$fetchState;

        public AnonymousClass1(FetchState fetchState) {
            this.val$fetchState = fetchState;
        }

        public void onCancellation() {
            NetworkFetchProducer networkFetchProducer = NetworkFetchProducer.this;
            FetchState fetchState = this.val$fetchState;
            Objects.requireNonNull(networkFetchProducer);
            fetchState.getListener().onProducerFinishWithCancellation(fetchState.getId(), "NetworkFetchProducer", null);
            fetchState.mConsumer.onCancellation();
        }

        public void onFailure(Throwable th) {
            NetworkFetchProducer networkFetchProducer = NetworkFetchProducer.this;
            FetchState fetchState = this.val$fetchState;
            Objects.requireNonNull(networkFetchProducer);
            fetchState.getListener().onProducerFinishWithFailure(fetchState.getId(), "NetworkFetchProducer", th, null);
            fetchState.getListener().onUltimateProducerReached(fetchState.getId(), "NetworkFetchProducer", false);
            fetchState.mConsumer.onFailure(th);
        }

        /* JADX WARN: Finally extract failed */
        public void onResponse(InputStream inputStream, int i) throws IOException {
            PooledByteBufferOutputStream pooledByteBufferOutputStream;
            FrescoSystrace.isTracing();
            NetworkFetchProducer networkFetchProducer = NetworkFetchProducer.this;
            FetchState fetchState = this.val$fetchState;
            if (i > 0) {
                pooledByteBufferOutputStream = networkFetchProducer.mPooledByteBufferFactory.newOutputStream(i);
            } else {
                pooledByteBufferOutputStream = networkFetchProducer.mPooledByteBufferFactory.newOutputStream();
            }
            byte[] bArr = networkFetchProducer.mByteArrayPool.get(Http2.INITIAL_MAX_FRAME_SIZE);
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read < 0) {
                        networkFetchProducer.mNetworkFetcher.onFetchCompletion(fetchState, ((MemoryPooledByteBufferOutputStream) pooledByteBufferOutputStream).mCount);
                        networkFetchProducer.handleFinalResult(pooledByteBufferOutputStream, fetchState);
                        networkFetchProducer.mByteArrayPool.release(bArr);
                        pooledByteBufferOutputStream.close();
                        FrescoSystrace.isTracing();
                        return;
                    } else if (read > 0) {
                        pooledByteBufferOutputStream.write(bArr, 0, read);
                        networkFetchProducer.maybeHandleIntermediateResult(pooledByteBufferOutputStream, fetchState);
                        int i2 = ((MemoryPooledByteBufferOutputStream) pooledByteBufferOutputStream).mCount;
                        fetchState.mConsumer.onProgressUpdate(i > 0 ? i2 / i : 1.0f - ((float) Math.exp((-i2) / 50000.0d)));
                    }
                } catch (Throwable th) {
                    networkFetchProducer.mByteArrayPool.release(bArr);
                    pooledByteBufferOutputStream.close();
                    throw th;
                }
            }
        }
    }

    public NetworkFetchProducer(PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, NetworkFetcher networkFetcher) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mNetworkFetcher = networkFetcher;
    }

    public static void notifyConsumer(PooledByteBufferOutputStream pooledByteBufferOutputStream, int i, BytesRange bytesRange, Consumer<EncodedImage> consumer) {
        Throwable th;
        EncodedImage encodedImage;
        CloseableReference of = CloseableReference.of(((MemoryPooledByteBufferOutputStream) pooledByteBufferOutputStream).toByteBuffer());
        try {
            encodedImage = new EncodedImage(of);
            try {
                encodedImage.mBytesRange = bytesRange;
                encodedImage.parseMetaData();
                consumer.onNewResult(encodedImage, i);
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
            encodedImage = null;
        }
    }

    public void handleFinalResult(PooledByteBufferOutputStream pooledByteBufferOutputStream, FetchState fetchState) {
        Map<String, String> extraMap = !fetchState.getListener().requiresExtraMap(fetchState.getId()) ? null : this.mNetworkFetcher.getExtraMap(fetchState, ((MemoryPooledByteBufferOutputStream) pooledByteBufferOutputStream).mCount);
        RequestListener listener = fetchState.getListener();
        listener.onProducerFinishWithSuccess(fetchState.getId(), "NetworkFetchProducer", extraMap);
        listener.onUltimateProducerReached(fetchState.getId(), "NetworkFetchProducer", true);
        notifyConsumer(pooledByteBufferOutputStream, fetchState.mOnNewResultStatusFlags | 1, fetchState.mResponseBytesRange, fetchState.mConsumer);
    }

    public void maybeHandleIntermediateResult(PooledByteBufferOutputStream pooledByteBufferOutputStream, FetchState fetchState) {
        boolean z;
        long uptimeMillis = SystemClock.uptimeMillis();
        if (!fetchState.mContext.isIntermediateResultExpected()) {
            z = false;
        } else {
            Objects.requireNonNull(this.mNetworkFetcher);
            z = true;
        }
        if (z && uptimeMillis - fetchState.mLastIntermediateResultTimeMs >= 100) {
            fetchState.mLastIntermediateResultTimeMs = uptimeMillis;
            fetchState.getListener().onProducerEvent(fetchState.getId(), "NetworkFetchProducer", "intermediate_result");
            notifyConsumer(pooledByteBufferOutputStream, fetchState.mOnNewResultStatusFlags, fetchState.mResponseBytesRange, fetchState.mConsumer);
        }
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        producerContext.getListener().onProducerStart(producerContext.getId(), "NetworkFetchProducer");
        FetchState createFetchState = this.mNetworkFetcher.createFetchState(consumer, producerContext);
        this.mNetworkFetcher.fetch(createFetchState, new AnonymousClass1(createFetchState));
    }
}
