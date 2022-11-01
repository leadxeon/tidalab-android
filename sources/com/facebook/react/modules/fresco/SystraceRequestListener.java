package com.facebook.react.modules.fresco;

import android.util.Pair;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public class SystraceRequestListener extends BaseRequestListener {
    public Map<String, Pair<Integer, String>> mProducerID = new HashMap();
    public Map<String, Pair<Integer, String>> mRequestsID = new HashMap();

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onProducerEvent(String str, String str2, String str3) {
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onProducerFinishWithCancellation(String str, String str2, Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onProducerFinishWithFailure(String str, String str2, Throwable th, Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onProducerFinishWithSuccess(String str, String str2, Map<String, String> map) {
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onProducerStart(String str, String str2) {
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestCancellation(String str) {
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) {
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) {
    }
}
