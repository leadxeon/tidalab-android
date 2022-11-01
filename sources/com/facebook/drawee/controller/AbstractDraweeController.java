package com.facebook.drawee.controller;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Objects$ToStringHelper;
import com.facebook.common.logging.FLog;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.components.DraweeEventTracker;
import com.facebook.drawee.components.RetryManager;
import com.facebook.drawee.gestures.GestureDetector;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Objects;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class AbstractDraweeController<T, INFO> implements DraweeController, DeferredReleaser.Releasable, GestureDetector.ClickListener {
    public Object mCallerContext;
    public String mContentDescription;
    public ControllerListener<INFO> mControllerListener;
    public Drawable mControllerOverlay;
    public DataSource<T> mDataSource;
    public final DeferredReleaser mDeferredReleaser;
    public Drawable mDrawable;
    public final DraweeEventTracker mEventTracker;
    public T mFetchedImage;
    public GestureDetector mGestureDetector;
    public boolean mHasFetchFailed;
    public String mId;
    public boolean mIsAttached;
    public boolean mIsRequestSubmitted;
    public boolean mJustConstructed;
    public boolean mRetainImageOnFailure;
    public RetryManager mRetryManager;
    public SettableDraweeHierarchy mSettableDraweeHierarchy;
    public final Executor mUiThreadImmediateExecutor;

    /* loaded from: classes.dex */
    public static class InternalForwardingListener<INFO> extends ForwardingControllerListener<INFO> {
    }

    public AbstractDraweeController(DeferredReleaser deferredReleaser, Executor executor, String str, Object obj) {
        this.mEventTracker = DraweeEventTracker.sEnabled ? new DraweeEventTracker() : DraweeEventTracker.sInstance;
        this.mJustConstructed = true;
        this.mDeferredReleaser = deferredReleaser;
        this.mUiThreadImmediateExecutor = executor;
        init(null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addControllerListener(ControllerListener<? super INFO> controllerListener) {
        Objects.requireNonNull(controllerListener);
        ControllerListener<INFO> controllerListener2 = this.mControllerListener;
        if (controllerListener2 instanceof InternalForwardingListener) {
            ((InternalForwardingListener) controllerListener2).addListener(controllerListener);
        } else if (controllerListener2 != null) {
            FrescoSystrace.isTracing();
            InternalForwardingListener internalForwardingListener = new InternalForwardingListener();
            internalForwardingListener.addListener(controllerListener2);
            internalForwardingListener.addListener(controllerListener);
            FrescoSystrace.isTracing();
            this.mControllerListener = internalForwardingListener;
        } else {
            this.mControllerListener = controllerListener;
        }
    }

    public abstract Drawable createDrawable(T t);

    public T getCachedImage() {
        return null;
    }

    public ControllerListener<INFO> getControllerListener() {
        ControllerListener<INFO> controllerListener = this.mControllerListener;
        return controllerListener == null ? (ControllerListener<INFO>) BaseControllerListener.NO_OP_LISTENER : controllerListener;
    }

    public abstract DataSource<T> getDataSource();

    public int getImageHash(T t) {
        return System.identityHashCode(t);
    }

    public abstract INFO getImageInfo(T t);

    public final synchronized void init(String str, Object obj) {
        DeferredReleaser deferredReleaser;
        FrescoSystrace.isTracing();
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_INIT_CONTROLLER);
        if (!this.mJustConstructed && (deferredReleaser = this.mDeferredReleaser) != null) {
            deferredReleaser.cancelDeferredRelease(this);
        }
        this.mIsAttached = false;
        releaseFetch();
        this.mRetainImageOnFailure = false;
        RetryManager retryManager = this.mRetryManager;
        if (retryManager != null) {
            retryManager.mTapToRetryEnabled = false;
            retryManager.mMaxTapToRetryAttempts = 4;
            retryManager.mTapToRetryAttempts = 0;
        }
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.mClickListener = null;
            gestureDetector.mIsCapturingGesture = false;
            gestureDetector.mIsClickCandidate = false;
            gestureDetector.mClickListener = this;
        }
        ControllerListener<INFO> controllerListener = this.mControllerListener;
        if (controllerListener instanceof InternalForwardingListener) {
            InternalForwardingListener internalForwardingListener = (InternalForwardingListener) controllerListener;
            synchronized (internalForwardingListener) {
                internalForwardingListener.mListeners.clear();
            }
        } else {
            this.mControllerListener = null;
        }
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy != null) {
            settableDraweeHierarchy.reset();
            this.mSettableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        this.mControllerOverlay = null;
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.class, "controller %x %s -> %s: initialize", Integer.valueOf(System.identityHashCode(this)), this.mId, str);
        }
        this.mId = str;
        this.mCallerContext = obj;
        FrescoSystrace.isTracing();
    }

    public final boolean isExpectedDataSource(String str, DataSource<T> dataSource) {
        if (dataSource == null && this.mDataSource == null) {
            return true;
        }
        return str.equals(this.mId) && dataSource == this.mDataSource && this.mIsRequestSubmitted;
    }

    public final void logMessageAndFailure(String str, Throwable th) {
        if (FLog.isLoggable(2)) {
            System.identityHashCode(this);
        }
    }

    public final void logMessageAndImage(String str, T t) {
        if (FLog.isLoggable(2)) {
            System.identityHashCode(this);
            if (t != null) {
                t.getClass().getSimpleName();
            }
            getImageHash(t);
        }
    }

    public final void onFailureInternal(String str, DataSource<T> dataSource, Throwable th, boolean z) {
        Drawable drawable;
        FrescoSystrace.isTracing();
        if (!isExpectedDataSource(str, dataSource)) {
            logMessageAndFailure("ignore_old_datasource @ onFailure", th);
            dataSource.close();
            FrescoSystrace.isTracing();
            return;
        }
        this.mEventTracker.recordEvent(z ? DraweeEventTracker.Event.ON_DATASOURCE_FAILURE : DraweeEventTracker.Event.ON_DATASOURCE_FAILURE_INT);
        if (z) {
            logMessageAndFailure("final_failed @ onFailure", th);
            this.mDataSource = null;
            this.mHasFetchFailed = true;
            if (this.mRetainImageOnFailure && (drawable = this.mDrawable) != null) {
                this.mSettableDraweeHierarchy.setImage(drawable, 1.0f, true);
            } else if (shouldRetryOnTap()) {
                this.mSettableDraweeHierarchy.setRetry(th);
            } else {
                this.mSettableDraweeHierarchy.setFailure(th);
            }
            getControllerListener().onFailure(this.mId, th);
        } else {
            logMessageAndFailure("intermediate_failed @ onFailure", th);
            getControllerListener().onIntermediateImageFailed(this.mId, th);
        }
        FrescoSystrace.isTracing();
    }

    public void onImageLoadedFromCacheImmediately(String str, T t) {
    }

    public final void onNewResultInternal(String str, DataSource<T> dataSource, T t, float f, boolean z, boolean z2, boolean z3) {
        try {
            FrescoSystrace.isTracing();
            if (!isExpectedDataSource(str, dataSource)) {
                logMessageAndImage("ignore_old_datasource @ onNewResult", t);
                releaseImage(t);
                dataSource.close();
                return;
            }
            this.mEventTracker.recordEvent(z ? DraweeEventTracker.Event.ON_DATASOURCE_RESULT : DraweeEventTracker.Event.ON_DATASOURCE_RESULT_INT);
            try {
                Drawable createDrawable = createDrawable(t);
                T t2 = this.mFetchedImage;
                Drawable drawable = this.mDrawable;
                this.mFetchedImage = t;
                this.mDrawable = createDrawable;
                Animatable animatable = null;
                if (z) {
                    logMessageAndImage("set_final_result @ onNewResult", t);
                    this.mDataSource = null;
                    this.mSettableDraweeHierarchy.setImage(createDrawable, 1.0f, z2);
                    ControllerListener<INFO> controllerListener = getControllerListener();
                    INFO imageInfo = getImageInfo(t);
                    Drawable drawable2 = this.mDrawable;
                    if (drawable2 instanceof Animatable) {
                        animatable = (Animatable) drawable2;
                    }
                    controllerListener.onFinalImageSet(str, imageInfo, animatable);
                } else if (z3) {
                    logMessageAndImage("set_temporary_result @ onNewResult", t);
                    this.mSettableDraweeHierarchy.setImage(createDrawable, 1.0f, z2);
                    ControllerListener<INFO> controllerListener2 = getControllerListener();
                    INFO imageInfo2 = getImageInfo(t);
                    Drawable drawable3 = this.mDrawable;
                    if (drawable3 instanceof Animatable) {
                        animatable = (Animatable) drawable3;
                    }
                    controllerListener2.onFinalImageSet(str, imageInfo2, animatable);
                } else {
                    logMessageAndImage("set_intermediate_result @ onNewResult", t);
                    this.mSettableDraweeHierarchy.setImage(createDrawable, f, z2);
                    getControllerListener().onIntermediateImageSet(str, getImageInfo(t));
                }
                if (!(drawable == null || drawable == createDrawable)) {
                    releaseDrawable(drawable);
                }
                if (!(t2 == null || t2 == t)) {
                    logMessageAndImage("release_previous_result @ onNewResult", t2);
                    releaseImage(t2);
                }
            } catch (Exception e) {
                logMessageAndImage("drawable_failed @ onNewResult", t);
                releaseImage(t);
                onFailureInternal(str, dataSource, e, z);
            }
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    @Override // com.facebook.drawee.components.DeferredReleaser.Releasable
    public void release() {
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_RELEASE_CONTROLLER);
        RetryManager retryManager = this.mRetryManager;
        if (retryManager != null) {
            retryManager.mTapToRetryAttempts = 0;
        }
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector != null) {
            gestureDetector.mIsCapturingGesture = false;
            gestureDetector.mIsClickCandidate = false;
        }
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy != null) {
            settableDraweeHierarchy.reset();
        }
        releaseFetch();
    }

    public abstract void releaseDrawable(Drawable drawable);

    public final void releaseFetch() {
        boolean z = this.mIsRequestSubmitted;
        this.mIsRequestSubmitted = false;
        this.mHasFetchFailed = false;
        DataSource<T> dataSource = this.mDataSource;
        if (dataSource != null) {
            dataSource.close();
            this.mDataSource = null;
        }
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            releaseDrawable(drawable);
        }
        if (this.mContentDescription != null) {
            this.mContentDescription = null;
        }
        this.mDrawable = null;
        T t = this.mFetchedImage;
        if (t != null) {
            logMessageAndImage("release", t);
            releaseImage(this.mFetchedImage);
            this.mFetchedImage = null;
        }
        if (z) {
            getControllerListener().onRelease(this.mId);
        }
    }

    public abstract void releaseImage(T t);

    @Override // com.facebook.drawee.interfaces.DraweeController
    public void setHierarchy(DraweeHierarchy draweeHierarchy) {
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.class, "controller %x %s: setHierarchy: %s", Integer.valueOf(System.identityHashCode(this)), this.mId, draweeHierarchy);
        }
        this.mEventTracker.recordEvent(draweeHierarchy != null ? DraweeEventTracker.Event.ON_SET_HIERARCHY : DraweeEventTracker.Event.ON_CLEAR_HIERARCHY);
        if (this.mIsRequestSubmitted) {
            this.mDeferredReleaser.cancelDeferredRelease(this);
            release();
        }
        SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
        if (settableDraweeHierarchy != null) {
            settableDraweeHierarchy.setControllerOverlay(null);
            this.mSettableDraweeHierarchy = null;
        }
        if (draweeHierarchy != null) {
            R$dimen.checkArgument(draweeHierarchy instanceof SettableDraweeHierarchy);
            SettableDraweeHierarchy settableDraweeHierarchy2 = (SettableDraweeHierarchy) draweeHierarchy;
            this.mSettableDraweeHierarchy = settableDraweeHierarchy2;
            settableDraweeHierarchy2.setControllerOverlay(this.mControllerOverlay);
        }
    }

    public final boolean shouldRetryOnTap() {
        RetryManager retryManager;
        if (this.mHasFetchFailed && (retryManager = this.mRetryManager) != null) {
            if (retryManager.mTapToRetryEnabled && retryManager.mTapToRetryAttempts < retryManager.mMaxTapToRetryAttempts) {
                return true;
            }
        }
        return false;
    }

    public void submitRequest() {
        FrescoSystrace.isTracing();
        T cachedImage = getCachedImage();
        if (cachedImage != null) {
            FrescoSystrace.isTracing();
            this.mDataSource = null;
            this.mIsRequestSubmitted = true;
            this.mHasFetchFailed = false;
            this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_SUBMIT_CACHE_HIT);
            getControllerListener().onSubmit(this.mId, this.mCallerContext);
            onImageLoadedFromCacheImmediately(this.mId, cachedImage);
            onNewResultInternal(this.mId, this.mDataSource, cachedImage, 1.0f, true, true, true);
            FrescoSystrace.isTracing();
            FrescoSystrace.isTracing();
            return;
        }
        this.mEventTracker.recordEvent(DraweeEventTracker.Event.ON_DATASOURCE_SUBMIT);
        getControllerListener().onSubmit(this.mId, this.mCallerContext);
        this.mSettableDraweeHierarchy.setProgress(0.0f, true);
        this.mIsRequestSubmitted = true;
        this.mHasFetchFailed = false;
        this.mDataSource = getDataSource();
        if (FLog.isLoggable(2)) {
            FLog.v(AbstractDraweeController.class, "controller %x %s: submitRequest: dataSource: %x", Integer.valueOf(System.identityHashCode(this)), this.mId, Integer.valueOf(System.identityHashCode(this.mDataSource)));
        }
        final String str = this.mId;
        final boolean hasResult = this.mDataSource.hasResult();
        this.mDataSource.subscribe(new BaseDataSubscriber<T>() { // from class: com.facebook.drawee.controller.AbstractDraweeController.1
            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onFailureImpl(DataSource<T> dataSource) {
                AbstractDraweeController.this.onFailureInternal(str, dataSource, dataSource.getFailureCause(), true);
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onNewResultImpl(DataSource<T> dataSource) {
                boolean isFinished = dataSource.isFinished();
                boolean hasMultipleResults = dataSource.hasMultipleResults();
                float progress = dataSource.getProgress();
                T result = dataSource.getResult();
                if (result != null) {
                    AbstractDraweeController.this.onNewResultInternal(str, dataSource, result, progress, isFinished, hasResult, hasMultipleResults);
                } else if (isFinished) {
                    AbstractDraweeController.this.onFailureInternal(str, dataSource, new NullPointerException(), true);
                }
            }

            @Override // com.facebook.datasource.BaseDataSubscriber, com.facebook.datasource.DataSubscriber
            public void onProgressUpdate(DataSource<T> dataSource) {
                AbstractDataSource abstractDataSource = (AbstractDataSource) dataSource;
                boolean isFinished = abstractDataSource.isFinished();
                float progress = abstractDataSource.getProgress();
                AbstractDraweeController abstractDraweeController = AbstractDraweeController.this;
                if (!abstractDraweeController.isExpectedDataSource(str, abstractDataSource)) {
                    abstractDraweeController.logMessageAndFailure("ignore_old_datasource @ onProgress", null);
                    abstractDataSource.close();
                } else if (!isFinished) {
                    abstractDraweeController.mSettableDraweeHierarchy.setProgress(progress, false);
                }
            }
        }, this.mUiThreadImmediateExecutor);
        FrescoSystrace.isTracing();
    }

    public String toString() {
        Objects$ToStringHelper stringHelper = R$dimen.toStringHelper(this);
        stringHelper.add("isAttached", this.mIsAttached);
        stringHelper.add("isRequestSubmitted", this.mIsRequestSubmitted);
        stringHelper.add("hasFetchFailed", this.mHasFetchFailed);
        stringHelper.addHolder("fetchedImage", String.valueOf(getImageHash(this.mFetchedImage)));
        stringHelper.addHolder("events", this.mEventTracker.toString());
        return stringHelper.toString();
    }
}
