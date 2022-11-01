package com.facebook.drawee.backends.pipeline.info.internal;

import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.info.ImagePerfState;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
/* loaded from: classes.dex */
public class ImagePerfRequestListener extends BaseRequestListener {
    public final MonotonicClock mClock;
    public final ImagePerfState mImagePerfState;

    public ImagePerfRequestListener(MonotonicClock monotonicClock, ImagePerfState imagePerfState) {
        this.mClock = monotonicClock;
        this.mImagePerfState = imagePerfState;
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestCancellation(String str) {
        this.mImagePerfState.mImageRequestEndTimeMs = this.mClock.now();
        this.mImagePerfState.mRequestId = str;
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
        this.mImagePerfState.mImageRequestEndTimeMs = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mImageRequest = imageRequest;
        imagePerfState.mRequestId = str;
        imagePerfState.mIsPrefetch = z;
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) {
        this.mImagePerfState.mImageRequestStartTimeMs = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mImageRequest = imageRequest;
        imagePerfState.mCallerContext = obj;
        imagePerfState.mRequestId = str;
        imagePerfState.mIsPrefetch = z;
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) {
        this.mImagePerfState.mImageRequestEndTimeMs = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mImageRequest = imageRequest;
        imagePerfState.mRequestId = str;
        imagePerfState.mIsPrefetch = z;
    }
}
