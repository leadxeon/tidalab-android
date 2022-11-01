package com.facebook.imagepipeline.producers;

import com.facebook.common.time.MonotonicClock;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* loaded from: classes.dex */
public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<HttpUrlConnectionNetworkFetchState> {
    public final ExecutorService mExecutorService = Executors.newFixedThreadPool(3);
    public int mHttpConnectionTimeout;
    public final MonotonicClock mMonotonicClock;

    /* loaded from: classes.dex */
    public static class HttpUrlConnectionNetworkFetchState extends FetchState {
        public long fetchCompleteTime;
        public long responseTime;
        public long submitTime;

        public HttpUrlConnectionNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
        }
    }

    public HttpUrlConnectionNetworkFetcher(int i) {
        RealtimeSinceBootClock realtimeSinceBootClock = RealtimeSinceBootClock.get();
        this.mMonotonicClock = realtimeSinceBootClock;
        this.mHttpConnectionTimeout = i;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public FetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        return new HttpUrlConnectionNetworkFetchState(consumer, producerContext);
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.net.HttpURLConnection downloadFrom(android.net.Uri r8, int r9) throws java.io.IOException {
        /*
            r7 = this;
            android.net.Uri r0 = com.facebook.common.util.UriUtil.LOCAL_CONTACT_IMAGE_URI
            r0 = 0
            if (r8 != 0) goto L_0x0007
            r1 = r0
            goto L_0x0010
        L_0x0007:
            java.net.URL r1 = new java.net.URL     // Catch: MalformedURLException -> 0x00b8
            java.lang.String r2 = r8.toString()     // Catch: MalformedURLException -> 0x00b8
            r1.<init>(r2)     // Catch: MalformedURLException -> 0x00b8
        L_0x0010:
            java.net.URLConnection r1 = r1.openConnection()
            java.net.HttpURLConnection r1 = (java.net.HttpURLConnection) r1
            int r2 = r7.mHttpConnectionTimeout
            r1.setConnectTimeout(r2)
            int r2 = r1.getResponseCode()
            r3 = 200(0xc8, float:2.8E-43)
            r4 = 1
            r5 = 0
            if (r2 < r3) goto L_0x002b
            r3 = 300(0x12c, float:4.2E-43)
            if (r2 >= r3) goto L_0x002b
            r3 = 1
            goto L_0x002c
        L_0x002b:
            r3 = 0
        L_0x002c:
            if (r3 == 0) goto L_0x002f
            return r1
        L_0x002f:
            r3 = 307(0x133, float:4.3E-43)
            if (r2 == r3) goto L_0x003c
            r3 = 308(0x134, float:4.32E-43)
            if (r2 == r3) goto L_0x003c
            switch(r2) {
                case 300: goto L_0x003c;
                case 301: goto L_0x003c;
                case 302: goto L_0x003c;
                case 303: goto L_0x003c;
                default: goto L_0x003a;
            }
        L_0x003a:
            r3 = 0
            goto L_0x003d
        L_0x003c:
            r3 = 1
        L_0x003d:
            r6 = 2
            if (r3 == 0) goto L_0x009b
            java.lang.String r3 = "Location"
            java.lang.String r3 = r1.getHeaderField(r3)
            r1.disconnect()
            if (r3 != 0) goto L_0x004c
            goto L_0x0050
        L_0x004c:
            android.net.Uri r0 = android.net.Uri.parse(r3)
        L_0x0050:
            java.lang.String r1 = r8.getScheme()
            if (r9 <= 0) goto L_0x0068
            if (r0 == 0) goto L_0x0068
            java.lang.String r3 = r0.getScheme()
            boolean r1 = r3.equals(r1)
            if (r1 != 0) goto L_0x0068
            int r9 = r9 - r4
            java.net.HttpURLConnection r8 = r7.downloadFrom(r0, r9)
            return r8
        L_0x0068:
            if (r9 != 0) goto L_0x007d
            java.lang.Object[] r9 = new java.lang.Object[r4]
            java.lang.String r8 = r8.toString()
            r9[r5] = r8
            java.util.Locale r8 = java.util.Locale.getDefault()
            java.lang.String r0 = "URL %s follows too many redirects"
            java.lang.String r8 = java.lang.String.format(r8, r0, r9)
            goto L_0x0095
        L_0x007d:
            java.lang.Object[] r9 = new java.lang.Object[r6]
            java.lang.String r8 = r8.toString()
            r9[r5] = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            r9[r4] = r8
            java.util.Locale r8 = java.util.Locale.getDefault()
            java.lang.String r0 = "URL %s returned %d without a valid redirect"
            java.lang.String r8 = java.lang.String.format(r8, r0, r9)
        L_0x0095:
            java.io.IOException r9 = new java.io.IOException
            r9.<init>(r8)
            throw r9
        L_0x009b:
            r1.disconnect()
            java.io.IOException r9 = new java.io.IOException
            java.lang.Object[] r0 = new java.lang.Object[r6]
            java.lang.String r8 = r8.toString()
            r0[r5] = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            r0[r4] = r8
            java.lang.String r8 = "Image URL %s returned HTTP code %d"
            java.lang.String r8 = java.lang.String.format(r8, r0)
            r9.<init>(r8)
            throw r9
        L_0x00b8:
            r8 = move-exception
            java.lang.RuntimeException r9 = new java.lang.RuntimeException
            r9.<init>(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.downloadFrom(android.net.Uri, int):java.net.HttpURLConnection");
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void fetch(FetchState fetchState, final NetworkFetcher.Callback callback) {
        final HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState = (HttpUrlConnectionNetworkFetchState) fetchState;
        httpUrlConnectionNetworkFetchState.submitTime = this.mMonotonicClock.now();
        final Future<?> submit = this.mExecutorService.submit(new Runnable() { // from class: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:28:0x0056  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Type inference failed for: r1v2, types: [com.facebook.imagepipeline.producers.NetworkFetchProducer$1] */
            /* JADX WARN: Type inference failed for: r3v0, types: [java.net.HttpURLConnection, java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r3v1, types: [java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r3v2 */
            /* JADX WARN: Type inference failed for: r3v4, types: [java.io.InputStream] */
            /* JADX WARN: Type inference failed for: r3v5 */
            /* JADX WARN: Unknown variable types count: 1 */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r7 = this;
                    com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher r0 = com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.this
                    com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher$HttpUrlConnectionNetworkFetchState r1 = r2
                    com.facebook.imagepipeline.producers.NetworkFetcher$Callback r2 = r3
                    java.util.Objects.requireNonNull(r0)
                    r3 = 0
                    android.net.Uri r4 = r1.getUri()     // Catch: all -> 0x0036, IOException -> 0x0039
                    r5 = 5
                    java.net.HttpURLConnection r4 = r0.downloadFrom(r4, r5)     // Catch: all -> 0x0036, IOException -> 0x0039
                    com.facebook.common.time.MonotonicClock r0 = r0.mMonotonicClock     // Catch: all -> 0x0032, IOException -> 0x0034
                    long r5 = r0.now()     // Catch: all -> 0x0032, IOException -> 0x0034
                    r1.responseTime = r5     // Catch: all -> 0x0032, IOException -> 0x0034
                    if (r4 == 0) goto L_0x0028
                    java.io.InputStream r3 = r4.getInputStream()     // Catch: all -> 0x0032, IOException -> 0x0034
                    r0 = -1
                    r1 = r2
                    com.facebook.imagepipeline.producers.NetworkFetchProducer$1 r1 = (com.facebook.imagepipeline.producers.NetworkFetchProducer.AnonymousClass1) r1     // Catch: all -> 0x0032, IOException -> 0x0034
                    r1.onResponse(r3, r0)     // Catch: all -> 0x0032, IOException -> 0x0034
                L_0x0028:
                    if (r3 == 0) goto L_0x002f
                    r3.close()     // Catch: IOException -> 0x002e
                    goto L_0x002f
                L_0x002e:
                L_0x002f:
                    if (r4 == 0) goto L_0x004c
                    goto L_0x0049
                L_0x0032:
                    r0 = move-exception
                    goto L_0x004d
                L_0x0034:
                    r0 = move-exception
                    goto L_0x003b
                L_0x0036:
                    r0 = move-exception
                    r4 = r3
                    goto L_0x004d
                L_0x0039:
                    r0 = move-exception
                    r4 = r3
                L_0x003b:
                    com.facebook.imagepipeline.producers.NetworkFetchProducer$1 r2 = (com.facebook.imagepipeline.producers.NetworkFetchProducer.AnonymousClass1) r2     // Catch: all -> 0x0032
                    r2.onFailure(r0)     // Catch: all -> 0x0032
                    if (r3 == 0) goto L_0x0047
                    r3.close()     // Catch: IOException -> 0x0046
                    goto L_0x0047
                L_0x0046:
                L_0x0047:
                    if (r4 == 0) goto L_0x004c
                L_0x0049:
                    r4.disconnect()
                L_0x004c:
                    return
                L_0x004d:
                    if (r3 == 0) goto L_0x0054
                    r3.close()     // Catch: IOException -> 0x0053
                    goto L_0x0054
                L_0x0053:
                L_0x0054:
                    if (r4 == 0) goto L_0x0059
                    r4.disconnect()
                L_0x0059:
                    throw r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.AnonymousClass1.run():void");
            }
        });
        httpUrlConnectionNetworkFetchState.mContext.addCallbacks(new BaseProducerContextCallbacks(this) { // from class: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.2
            @Override // com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                if (submit.cancel(false)) {
                    ((NetworkFetchProducer.AnonymousClass1) callback).onCancellation();
                }
            }
        });
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public Map getExtraMap(FetchState fetchState, int i) {
        HttpUrlConnectionNetworkFetchState httpUrlConnectionNetworkFetchState = (HttpUrlConnectionNetworkFetchState) fetchState;
        HashMap hashMap = new HashMap(4);
        hashMap.put("queue_time", Long.toString(httpUrlConnectionNetworkFetchState.responseTime - httpUrlConnectionNetworkFetchState.submitTime));
        hashMap.put("fetch_time", Long.toString(httpUrlConnectionNetworkFetchState.fetchCompleteTime - httpUrlConnectionNetworkFetchState.responseTime));
        hashMap.put("total_time", Long.toString(httpUrlConnectionNetworkFetchState.fetchCompleteTime - httpUrlConnectionNetworkFetchState.submitTime));
        hashMap.put("image_size", Integer.toString(i));
        return hashMap;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void onFetchCompletion(FetchState fetchState, int i) {
        ((HttpUrlConnectionNetworkFetchState) fetchState).fetchCompleteTime = this.mMonotonicClock.now();
    }
}
