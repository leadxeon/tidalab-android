package com.facebook.react.modules.fresco;

import android.net.Uri;
import android.os.SystemClock;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
/* loaded from: classes.dex */
public class ReactOkHttpNetworkFetcher extends OkHttpNetworkFetcher {
    public ReactOkHttpNetworkFetcher(OkHttpClient okHttpClient) {
        super(okHttpClient);
        okHttpClient.dispatcher().executorService();
    }

    @Override // com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher
    public void fetch(OkHttpNetworkFetcher.OkHttpNetworkFetchState okHttpNetworkFetchState, NetworkFetcher.Callback callback) {
        ReadableMap readableMap;
        okHttpNetworkFetchState.submitTime = SystemClock.elapsedRealtime();
        Uri uri = okHttpNetworkFetchState.getUri();
        Map map = null;
        if ((okHttpNetworkFetchState.mContext.getImageRequest() instanceof ReactNetworkImageRequest) && (readableMap = ((ReactNetworkImageRequest) okHttpNetworkFetchState.mContext.getImageRequest()).mHeaders) != null) {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            HashMap hashMap = new HashMap();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                hashMap.put(nextKey, readableMap.getString(nextKey));
            }
            map = hashMap;
        }
        if (map == null) {
            map = Collections.emptyMap();
        }
        fetchWithRequest(okHttpNetworkFetchState, callback, new Request.Builder().cacheControl(new CacheControl.Builder().noStore().build()).url(uri.toString()).headers(Headers.of(map)).get().build());
    }
}
