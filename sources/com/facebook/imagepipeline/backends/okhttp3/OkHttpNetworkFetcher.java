package com.facebook.imagepipeline.backends.okhttp3;

import android.os.Looper;
import android.os.SystemClock;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* loaded from: classes.dex */
public class OkHttpNetworkFetcher extends BaseNetworkFetcher<OkHttpNetworkFetchState> {
    public final CacheControl mCacheControl = new CacheControl.Builder().noStore().build();
    public final Call.Factory mCallFactory;
    public Executor mCancellationExecutor;

    /* loaded from: classes.dex */
    public static class OkHttpNetworkFetchState extends FetchState {
        public long fetchCompleteTime;
        public long responseTime;
        public long submitTime;

        public OkHttpNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    public OkHttpNetworkFetcher(OkHttpClient okHttpClient) {
        ExecutorService executorService = okHttpClient.dispatcher().executorService();
        this.mCallFactory = okHttpClient;
        this.mCancellationExecutor = executorService;
    }

    public static void access$100(OkHttpNetworkFetcher okHttpNetworkFetcher, Call call, Exception exc, NetworkFetcher.Callback callback) {
        Objects.requireNonNull(okHttpNetworkFetcher);
        if (call.isCanceled()) {
            ((NetworkFetchProducer.AnonymousClass1) callback).onCancellation();
        } else {
            ((NetworkFetchProducer.AnonymousClass1) callback).onFailure(exc);
        }
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public FetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        return new OkHttpNetworkFetchState(consumer, producerContext);
    }

    public void fetchWithRequest(final OkHttpNetworkFetchState okHttpNetworkFetchState, final NetworkFetcher.Callback callback, Request request) {
        final Call newCall = this.mCallFactory.newCall(request);
        okHttpNetworkFetchState.mContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.1
            @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    newCall.cancel();
                } else {
                    OkHttpNetworkFetcher.this.mCancellationExecutor.execute(new Runnable() { // from class: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            newCall.cancel();
                        }
                    });
                }
            }
        });
        newCall.enqueue(new Callback() { // from class: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.2
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                OkHttpNetworkFetcher.access$100(OkHttpNetworkFetcher.this, call, iOException, callback);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                okHttpNetworkFetchState.responseTime = SystemClock.elapsedRealtime();
                ResponseBody body = response.body();
                try {
                    try {
                    } catch (Exception e) {
                        OkHttpNetworkFetcher.access$100(OkHttpNetworkFetcher.this, call, e, callback);
                    }
                    if (!response.isSuccessful()) {
                        OkHttpNetworkFetcher.access$100(OkHttpNetworkFetcher.this, call, new IOException("Unexpected HTTP code " + response), callback);
                        return;
                    }
                    BytesRange fromContentRangeHeader = BytesRange.fromContentRangeHeader(response.header("Content-Range"));
                    if (!(fromContentRangeHeader == null || (fromContentRangeHeader.from == 0 && fromContentRangeHeader.to == Integer.MAX_VALUE))) {
                        OkHttpNetworkFetchState okHttpNetworkFetchState2 = okHttpNetworkFetchState;
                        okHttpNetworkFetchState2.mResponseBytesRange = fromContentRangeHeader;
                        okHttpNetworkFetchState2.mOnNewResultStatusFlags = 8;
                    }
                    long contentLength = body.contentLength();
                    if (contentLength < 0) {
                        contentLength = 0;
                    }
                    ((NetworkFetchProducer.AnonymousClass1) callback).onResponse(body.byteStream(), (int) contentLength);
                } finally {
                    body.close();
                }
            }
        });
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public Map getExtraMap(FetchState fetchState, int i) {
        OkHttpNetworkFetchState okHttpNetworkFetchState = (OkHttpNetworkFetchState) fetchState;
        HashMap hashMap = new HashMap(4);
        hashMap.put("queue_time", Long.toString(okHttpNetworkFetchState.responseTime - okHttpNetworkFetchState.submitTime));
        hashMap.put("fetch_time", Long.toString(okHttpNetworkFetchState.fetchCompleteTime - okHttpNetworkFetchState.responseTime));
        hashMap.put("total_time", Long.toString(okHttpNetworkFetchState.fetchCompleteTime - okHttpNetworkFetchState.submitTime));
        hashMap.put("image_size", Integer.toString(i));
        return hashMap;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void onFetchCompletion(FetchState fetchState, int i) {
        ((OkHttpNetworkFetchState) fetchState).fetchCompleteTime = SystemClock.elapsedRealtime();
    }

    public void fetch(OkHttpNetworkFetchState okHttpNetworkFetchState, NetworkFetcher.Callback callback) {
        okHttpNetworkFetchState.submitTime = SystemClock.elapsedRealtime();
        try {
            Request.Builder builder = new Request.Builder().url(okHttpNetworkFetchState.getUri().toString()).get();
            CacheControl cacheControl = this.mCacheControl;
            if (cacheControl != null) {
                builder.cacheControl(cacheControl);
            }
            BytesRange bytesRange = okHttpNetworkFetchState.mContext.getImageRequest().mBytesRange;
            if (bytesRange != null) {
                builder.addHeader("Range", String.format(null, "bytes=%s-%s", BytesRange.valueOrEmpty(bytesRange.from), BytesRange.valueOrEmpty(bytesRange.to)));
            }
            fetchWithRequest(okHttpNetworkFetchState, callback, builder.build());
        } catch (Exception e) {
            ((NetworkFetchProducer.AnonymousClass1) callback).onFailure(e);
        }
    }
}
