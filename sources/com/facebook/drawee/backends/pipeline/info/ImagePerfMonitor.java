package com.facebook.drawee.backends.pipeline.info;

import android.graphics.Rect;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfControllerListener;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfRequestListener;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import java.util.List;
import java.util.Objects;
import java.util.Set;
/* loaded from: classes.dex */
public class ImagePerfMonitor {
    public boolean mEnabled;
    public ForwardingRequestListener mForwardingRequestListener;
    public ImageOriginListener mImageOriginListener;
    public ImageOriginRequestListener mImageOriginRequestListener;
    public ImagePerfControllerListener mImagePerfControllerListener;
    public List<ImagePerfDataListener> mImagePerfDataListeners;
    public ImagePerfRequestListener mImagePerfRequestListener;
    public final ImagePerfState mImagePerfState = new ImagePerfState();
    public final MonotonicClock mMonotonicClock;
    public final PipelineDraweeController mPipelineDraweeController;

    public ImagePerfMonitor(MonotonicClock monotonicClock, PipelineDraweeController pipelineDraweeController) {
        this.mMonotonicClock = monotonicClock;
        this.mPipelineDraweeController = pipelineDraweeController;
    }

    public void notifyListenersOfVisibilityStateUpdate(ImagePerfState imagePerfState, int i) {
        List<ImagePerfDataListener> list;
        if (!(!this.mEnabled || (list = this.mImagePerfDataListeners) == null || list.isEmpty())) {
            ImagePerfData snapshot = imagePerfState.snapshot();
            for (ImagePerfDataListener imagePerfDataListener : this.mImagePerfDataListeners) {
                imagePerfDataListener.onImageVisibilityUpdated(snapshot, i);
            }
        }
    }

    public void notifyStatusUpdated(ImagePerfState imagePerfState, int i) {
        List<ImagePerfDataListener> list;
        SettableDraweeHierarchy settableDraweeHierarchy;
        imagePerfState.mImageLoadStatus = i;
        if (!(!this.mEnabled || (list = this.mImagePerfDataListeners) == null || list.isEmpty())) {
            if (!(i != 3 || (settableDraweeHierarchy = this.mPipelineDraweeController.mSettableDraweeHierarchy) == null || settableDraweeHierarchy.getTopLevelDrawable() == null)) {
                Rect bounds = settableDraweeHierarchy.getTopLevelDrawable().getBounds();
                this.mImagePerfState.mOnScreenWidthPx = bounds.width();
                this.mImagePerfState.mOnScreenHeightPx = bounds.height();
            }
            ImagePerfData snapshot = imagePerfState.snapshot();
            for (ImagePerfDataListener imagePerfDataListener : this.mImagePerfDataListeners) {
                imagePerfDataListener.onImageLoadStatusUpdated(snapshot, i);
            }
        }
    }

    public void reset() {
        List<ImagePerfDataListener> list = this.mImagePerfDataListeners;
        if (list != null) {
            list.clear();
        }
        setEnabled(false);
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.mRequestId = null;
        imagePerfState.mImageRequest = null;
        imagePerfState.mCallerContext = null;
        imagePerfState.mImageInfo = null;
        imagePerfState.mControllerSubmitTimeMs = -1L;
        imagePerfState.mControllerFinalImageSetTimeMs = -1L;
        imagePerfState.mControllerFailureTimeMs = -1L;
        imagePerfState.mControllerCancelTimeMs = -1L;
        imagePerfState.mImageRequestStartTimeMs = -1L;
        imagePerfState.mImageRequestEndTimeMs = -1L;
        imagePerfState.mImageOrigin = 1;
        imagePerfState.mUltimateProducerName = null;
        imagePerfState.mIsPrefetch = false;
        imagePerfState.mOnScreenWidthPx = -1;
        imagePerfState.mOnScreenHeightPx = -1;
        imagePerfState.mImageLoadStatus = -1;
        imagePerfState.mVisibilityState = -1;
        imagePerfState.mVisibilityEventTimeMs = -1L;
        imagePerfState.mInvisibilityEventTimeMs = -1L;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        if (z) {
            if (this.mImagePerfControllerListener == null) {
                this.mImagePerfControllerListener = new ImagePerfControllerListener(this.mMonotonicClock, this.mImagePerfState, this);
            }
            if (this.mImagePerfRequestListener == null) {
                this.mImagePerfRequestListener = new ImagePerfRequestListener(this.mMonotonicClock, this.mImagePerfState);
            }
            if (this.mImageOriginListener == null) {
                this.mImageOriginListener = new ImagePerfImageOriginListener(this.mImagePerfState, this);
            }
            ImageOriginRequestListener imageOriginRequestListener = this.mImageOriginRequestListener;
            if (imageOriginRequestListener == null) {
                this.mImageOriginRequestListener = new ImageOriginRequestListener(this.mPipelineDraweeController.mId, this.mImageOriginListener);
            } else {
                imageOriginRequestListener.mControllerId = this.mPipelineDraweeController.mId;
            }
            if (this.mForwardingRequestListener == null) {
                this.mForwardingRequestListener = new ForwardingRequestListener(this.mImagePerfRequestListener, this.mImageOriginRequestListener);
            }
            ImageOriginListener imageOriginListener = this.mImageOriginListener;
            if (imageOriginListener != null) {
                this.mPipelineDraweeController.addImageOriginListener(imageOriginListener);
            }
            ImagePerfControllerListener imagePerfControllerListener = this.mImagePerfControllerListener;
            if (imagePerfControllerListener != null) {
                this.mPipelineDraweeController.addControllerListener(imagePerfControllerListener);
            }
            ForwardingRequestListener forwardingRequestListener = this.mForwardingRequestListener;
            if (forwardingRequestListener != null) {
                this.mPipelineDraweeController.addRequestListener(forwardingRequestListener);
                return;
            }
            return;
        }
        ImageOriginListener imageOriginListener2 = this.mImageOriginListener;
        if (imageOriginListener2 != null) {
            PipelineDraweeController pipelineDraweeController = this.mPipelineDraweeController;
            synchronized (pipelineDraweeController) {
                ImageOriginListener imageOriginListener3 = pipelineDraweeController.mImageOriginListener;
                if (imageOriginListener3 instanceof ForwardingImageOriginListener) {
                    ForwardingImageOriginListener forwardingImageOriginListener = (ForwardingImageOriginListener) imageOriginListener3;
                    synchronized (forwardingImageOriginListener) {
                        forwardingImageOriginListener.mImageOriginListeners.remove(imageOriginListener2);
                    }
                } else if (imageOriginListener3 != null) {
                    pipelineDraweeController.mImageOriginListener = new ForwardingImageOriginListener(imageOriginListener3, imageOriginListener2);
                } else {
                    pipelineDraweeController.mImageOriginListener = imageOriginListener2;
                }
            }
        }
        Object obj = this.mImagePerfControllerListener;
        if (obj != null) {
            PipelineDraweeController pipelineDraweeController2 = this.mPipelineDraweeController;
            Objects.requireNonNull(pipelineDraweeController2);
            Objects.requireNonNull(obj);
            Object obj2 = pipelineDraweeController2.mControllerListener;
            if (obj2 instanceof AbstractDraweeController.InternalForwardingListener) {
                AbstractDraweeController.InternalForwardingListener internalForwardingListener = (AbstractDraweeController.InternalForwardingListener) obj2;
                synchronized (internalForwardingListener) {
                    int indexOf = internalForwardingListener.mListeners.indexOf(obj);
                    if (indexOf != -1) {
                        internalForwardingListener.mListeners.set(indexOf, null);
                    }
                }
            } else if (obj2 == obj) {
                pipelineDraweeController2.mControllerListener = null;
            }
        }
        ForwardingRequestListener forwardingRequestListener2 = this.mForwardingRequestListener;
        if (forwardingRequestListener2 != null) {
            PipelineDraweeController pipelineDraweeController3 = this.mPipelineDraweeController;
            synchronized (pipelineDraweeController3) {
                Set<RequestListener> set = pipelineDraweeController3.mRequestListeners;
                if (set != null) {
                    set.remove(forwardingRequestListener2);
                }
            }
        }
    }
}
