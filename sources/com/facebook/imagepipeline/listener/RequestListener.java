package com.facebook.imagepipeline.listener;

import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
/* loaded from: classes.dex */
public interface RequestListener {
    void onProducerEvent(String str, String str2, String str3);

    void onProducerFinishWithCancellation(String str, String str2, Map<String, String> map);

    void onProducerFinishWithFailure(String str, String str2, Throwable th, Map<String, String> map);

    void onProducerFinishWithSuccess(String str, String str2, Map<String, String> map);

    void onProducerStart(String str, String str2);

    void onRequestCancellation(String str);

    void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z);

    void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z);

    void onRequestSuccess(ImageRequest imageRequest, String str, boolean z);

    void onUltimateProducerReached(String str, String str2, boolean z);

    boolean requiresExtraMap(String str);
}
