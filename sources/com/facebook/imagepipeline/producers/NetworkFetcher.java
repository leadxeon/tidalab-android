package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.FetchState;
import java.util.Map;
/* loaded from: classes.dex */
public interface NetworkFetcher<FETCH_STATE extends FetchState> {

    /* loaded from: classes.dex */
    public interface Callback {
    }

    FETCH_STATE createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext);

    void fetch(FETCH_STATE fetch_state, Callback callback);

    Map<String, String> getExtraMap(FETCH_STATE fetch_state, int i);

    void onFetchCompletion(FETCH_STATE fetch_state, int i);
}
