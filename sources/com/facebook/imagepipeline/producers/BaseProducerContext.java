package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class BaseProducerContext implements ProducerContext {
    public final Object mCallerContext;
    public final String mId;
    public final ImageRequest mImageRequest;
    public boolean mIsIntermediateResultExpected;
    public boolean mIsPrefetch;
    public final ImageRequest.RequestLevel mLowestPermittedRequestLevel;
    public Priority mPriority;
    public final RequestListener mProducerListener;
    public boolean mIsCancelled = false;
    public final List<ProducerContextCallbacks> mCallbacks = new ArrayList();

    public BaseProducerContext(ImageRequest imageRequest, String str, RequestListener requestListener, Object obj, ImageRequest.RequestLevel requestLevel, boolean z, boolean z2, Priority priority) {
        this.mImageRequest = imageRequest;
        this.mId = str;
        this.mProducerListener = requestListener;
        this.mCallerContext = obj;
        this.mLowestPermittedRequestLevel = requestLevel;
        this.mIsPrefetch = z;
        this.mPriority = priority;
        this.mIsIntermediateResultExpected = z2;
    }

    public static void callOnIsIntermediateResultExpectedChanged(List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (ProducerContextCallbacks producerContextCallbacks : list) {
                producerContextCallbacks.onIsIntermediateResultExpectedChanged();
            }
        }
    }

    public static void callOnIsPrefetchChanged(List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (ProducerContextCallbacks producerContextCallbacks : list) {
                producerContextCallbacks.onIsPrefetchChanged();
            }
        }
    }

    public static void callOnPriorityChanged(List<ProducerContextCallbacks> list) {
        if (list != null) {
            for (ProducerContextCallbacks producerContextCallbacks : list) {
                producerContextCallbacks.onPriorityChanged();
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public void addCallbacks(ProducerContextCallbacks producerContextCallbacks) {
        boolean z;
        synchronized (this) {
            this.mCallbacks.add(producerContextCallbacks);
            z = this.mIsCancelled;
        }
        if (z) {
            producerContextCallbacks.onCancellationRequested();
        }
    }

    public void cancel() {
        ArrayList<ProducerContextCallbacks> arrayList;
        synchronized (this) {
            if (this.mIsCancelled) {
                arrayList = null;
            } else {
                this.mIsCancelled = true;
                arrayList = new ArrayList(this.mCallbacks);
            }
        }
        if (arrayList != null) {
            for (ProducerContextCallbacks producerContextCallbacks : arrayList) {
                producerContextCallbacks.onCancellationRequested();
            }
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public String getId() {
        return this.mId;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ImageRequest getImageRequest() {
        return this.mImageRequest;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public RequestListener getListener() {
        return this.mProducerListener;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public ImageRequest.RequestLevel getLowestPermittedRequestLevel() {
        return this.mLowestPermittedRequestLevel;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized Priority getPriority() {
        return this.mPriority;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized boolean isIntermediateResultExpected() {
        return this.mIsIntermediateResultExpected;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerContext
    public synchronized boolean isPrefetch() {
        return this.mIsPrefetch;
    }
}
