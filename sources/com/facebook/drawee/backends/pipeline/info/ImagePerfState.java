package com.facebook.drawee.backends.pipeline.info;

import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
/* loaded from: classes.dex */
public class ImagePerfState {
    public Object mCallerContext;
    public String mControllerId;
    public ImageInfo mImageInfo;
    public ImageRequest mImageRequest;
    public boolean mIsPrefetch;
    public String mRequestId;
    public String mUltimateProducerName;
    public long mControllerSubmitTimeMs = -1;
    public long mControllerIntermediateImageSetTimeMs = -1;
    public long mControllerFinalImageSetTimeMs = -1;
    public long mControllerFailureTimeMs = -1;
    public long mControllerCancelTimeMs = -1;
    public long mImageRequestStartTimeMs = -1;
    public long mImageRequestEndTimeMs = -1;
    public int mImageOrigin = -1;
    public int mOnScreenWidthPx = -1;
    public int mOnScreenHeightPx = -1;
    public int mImageLoadStatus = -1;
    public int mVisibilityState = -1;
    public long mVisibilityEventTimeMs = -1;
    public long mInvisibilityEventTimeMs = -1;

    public ImagePerfData snapshot() {
        return new ImagePerfData(this.mControllerId, this.mRequestId, this.mImageRequest, this.mCallerContext, this.mImageInfo, this.mControllerSubmitTimeMs, this.mControllerIntermediateImageSetTimeMs, this.mControllerFinalImageSetTimeMs, this.mControllerFailureTimeMs, this.mControllerCancelTimeMs, this.mImageRequestStartTimeMs, this.mImageRequestEndTimeMs, this.mImageOrigin, this.mUltimateProducerName, this.mIsPrefetch, this.mOnScreenWidthPx, this.mOnScreenHeightPx, this.mVisibilityState, this.mVisibilityEventTimeMs, this.mInvisibilityEventTimeMs, null);
    }
}
