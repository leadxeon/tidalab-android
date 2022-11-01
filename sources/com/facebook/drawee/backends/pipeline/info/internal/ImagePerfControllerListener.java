package com.facebook.drawee.backends.pipeline.info.internal;

import android.graphics.drawable.Animatable;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.backends.pipeline.info.ImagePerfState;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
/* loaded from: classes.dex */
public class ImagePerfControllerListener extends BaseControllerListener<ImageInfo> {
    public final MonotonicClock mClock;
    public final ImagePerfMonitor mImagePerfMonitor;
    public final ImagePerfState mImagePerfState;

    public ImagePerfControllerListener(MonotonicClock monotonicClock, ImagePerfState imagePerfState, ImagePerfMonitor imagePerfMonitor) {
        this.mClock = monotonicClock;
        this.mImagePerfState = imagePerfState;
        this.mImagePerfMonitor = imagePerfMonitor;
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onFailure(String str, Throwable th) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mControllerFailureTimeMs = now;
        imagePerfState.mControllerId = str;
        this.mImagePerfMonitor.notifyStatusUpdated(imagePerfState, 5);
        ImagePerfState imagePerfState2 = this.mImagePerfState;
        imagePerfState2.mVisibilityState = 2;
        imagePerfState2.mInvisibilityEventTimeMs = now;
        this.mImagePerfMonitor.notifyListenersOfVisibilityStateUpdate(imagePerfState2, 2);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onFinalImageSet(String str, Object obj, Animatable animatable) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mControllerFinalImageSetTimeMs = now;
        imagePerfState.mImageRequestEndTimeMs = now;
        imagePerfState.mControllerId = str;
        imagePerfState.mImageInfo = (ImageInfo) obj;
        this.mImagePerfMonitor.notifyStatusUpdated(imagePerfState, 3);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onIntermediateImageSet(String str, Object obj) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mControllerIntermediateImageSetTimeMs = now;
        imagePerfState.mControllerId = str;
        imagePerfState.mImageInfo = (ImageInfo) obj;
        this.mImagePerfMonitor.notifyStatusUpdated(imagePerfState, 2);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onRelease(String str) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        int i = imagePerfState.mImageLoadStatus;
        if (!(i == 3 || i == 5)) {
            imagePerfState.mControllerCancelTimeMs = now;
            imagePerfState.mControllerId = str;
            this.mImagePerfMonitor.notifyStatusUpdated(imagePerfState, 4);
        }
        ImagePerfState imagePerfState2 = this.mImagePerfState;
        imagePerfState2.mVisibilityState = 2;
        imagePerfState2.mInvisibilityEventTimeMs = now;
        this.mImagePerfMonitor.notifyListenersOfVisibilityStateUpdate(imagePerfState2, 2);
    }

    @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
    public void onSubmit(String str, Object obj) {
        long now = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mControllerSubmitTimeMs = now;
        imagePerfState.mControllerId = str;
        imagePerfState.mCallerContext = obj;
        this.mImagePerfMonitor.notifyStatusUpdated(imagePerfState, 0);
        ImagePerfState imagePerfState2 = this.mImagePerfState;
        imagePerfState2.mVisibilityState = 1;
        imagePerfState2.mVisibilityEventTimeMs = now;
        this.mImagePerfMonitor.notifyListenersOfVisibilityStateUpdate(imagePerfState2, 1);
    }
}
