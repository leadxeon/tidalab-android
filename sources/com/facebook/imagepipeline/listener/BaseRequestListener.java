package com.facebook.imagepipeline.listener;

import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
/* loaded from: classes.dex */
public class BaseRequestListener implements RequestListener {
    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onProducerEvent(String str, String str2, String str3) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onProducerFinishWithCancellation(String str, String str2, Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onProducerFinishWithFailure(String str, String str2, Throwable th, Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onProducerFinishWithSuccess(String str, String str2, Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onProducerStart(String str, String str2) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onRequestCancellation(String str) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public void onUltimateProducerReached(String str, String str2, boolean z) {
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public boolean requiresExtraMap(String str) {
        return false;
    }
}
