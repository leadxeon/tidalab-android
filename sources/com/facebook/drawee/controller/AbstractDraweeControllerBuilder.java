package com.facebook.drawee.controller;

import android.content.Context;
import android.graphics.drawable.Animatable;
import androidx.recyclerview.R$dimen;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects$ToStringHelper;
import com.facebook.common.internal.Supplier;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.IncreasingQualityDataSourceSupplier;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerFactory;
import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImageOriginRequestListener;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public abstract class AbstractDraweeControllerBuilder<BUILDER extends AbstractDraweeControllerBuilder<BUILDER, REQUEST, IMAGE, INFO>, REQUEST, IMAGE, INFO> {
    public boolean mAutoPlayAnimations;
    public final Set<ControllerListener> mBoundControllerListeners;
    public Object mCallerContext;
    public final Context mContext;
    public ControllerListener<? super INFO> mControllerListener;
    public REQUEST mImageRequest;
    public REQUEST mLowResImageRequest;
    public DraweeController mOldController;
    public boolean mTryCacheOnlyFirst;
    public static final ControllerListener<Object> sAutoPlayAnimationsListener = new BaseControllerListener<Object>() { // from class: com.facebook.drawee.controller.AbstractDraweeControllerBuilder.1
        @Override // com.facebook.drawee.controller.BaseControllerListener, com.facebook.drawee.controller.ControllerListener
        public void onFinalImageSet(String str, Object obj, Animatable animatable) {
            if (animatable != null) {
                animatable.start();
            }
        }
    };
    public static final NullPointerException NO_REQUEST_EXCEPTION = new NullPointerException("No image request was specified!");
    public static final AtomicLong sIdCounter = new AtomicLong();

    /* loaded from: classes.dex */
    public enum CacheLevel {
        FULL_FETCH,
        DISK_CACHE,
        BITMAP_MEMORY_CACHE
    }

    public AbstractDraweeControllerBuilder(Context context, Set<ControllerListener> set) {
        this.mContext = context;
        this.mBoundControllerListeners = set;
        init();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AbstractDraweeController build() {
        PipelineDraweeController pipelineDraweeController;
        Supplier supplier;
        CacheKey cacheKey;
        REQUEST request;
        R$dimen.checkState(true, "Cannot specify both ImageRequest and FirstAvailableImageRequests!");
        R$dimen.checkState(true, "Cannot specify DataSourceSupplier with other ImageRequests! Use one or the other.");
        if (this.mImageRequest == null && (request = this.mLowResImageRequest) != null) {
            this.mImageRequest = request;
            this.mLowResImageRequest = null;
        }
        FrescoSystrace.isTracing();
        PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder = (PipelineDraweeControllerBuilder) this;
        FrescoSystrace.isTracing();
        try {
            DraweeController draweeController = pipelineDraweeControllerBuilder.mOldController;
            String valueOf = String.valueOf(sIdCounter.getAndIncrement());
            if (draweeController instanceof PipelineDraweeController) {
                pipelineDraweeController = (PipelineDraweeController) draweeController;
            } else {
                PipelineDraweeControllerFactory pipelineDraweeControllerFactory = pipelineDraweeControllerBuilder.mPipelineDraweeControllerFactory;
                PipelineDraweeController pipelineDraweeController2 = new PipelineDraweeController(pipelineDraweeControllerFactory.mResources, pipelineDraweeControllerFactory.mDeferredReleaser, pipelineDraweeControllerFactory.mAnimatedDrawableFactory, pipelineDraweeControllerFactory.mUiThreadExecutor, pipelineDraweeControllerFactory.mMemoryCache, pipelineDraweeControllerFactory.mDrawableFactories);
                Supplier<Boolean> supplier2 = pipelineDraweeControllerFactory.mDebugOverlayEnabledSupplier;
                if (supplier2 != null) {
                    pipelineDraweeController2.mDrawDebugOverlay = supplier2.get().booleanValue();
                }
                pipelineDraweeController = pipelineDraweeController2;
            }
            REQUEST request2 = pipelineDraweeControllerBuilder.mImageRequest;
            Supplier dataSourceSupplierForRequest = request2 != null ? pipelineDraweeControllerBuilder.getDataSourceSupplierForRequest(pipelineDraweeController, valueOf, request2) : null;
            if (!(dataSourceSupplierForRequest == null || pipelineDraweeControllerBuilder.mLowResImageRequest == null)) {
                ArrayList arrayList = new ArrayList(2);
                arrayList.add(dataSourceSupplierForRequest);
                arrayList.add(pipelineDraweeControllerBuilder.getDataSourceSupplierForRequest(pipelineDraweeController, valueOf, pipelineDraweeControllerBuilder.mLowResImageRequest));
                dataSourceSupplierForRequest = new IncreasingQualityDataSourceSupplier(arrayList, false);
            }
            if (dataSourceSupplierForRequest == null) {
                final NullPointerException nullPointerException = NO_REQUEST_EXCEPTION;
                supplier = new Supplier<DataSource<T>>() { // from class: com.facebook.datasource.DataSources$1
                    @Override // com.facebook.common.internal.Supplier
                    public Object get() {
                        return R$dimen.immediateFailedDataSource(nullPointerException);
                    }
                };
            } else {
                supplier = dataSourceSupplierForRequest;
            }
            ImageRequest imageRequest = (ImageRequest) pipelineDraweeControllerBuilder.mImageRequest;
            CacheKeyFactory cacheKeyFactory = pipelineDraweeControllerBuilder.mImagePipeline.mCacheKeyFactory;
            if (cacheKeyFactory == null || imageRequest == null) {
                cacheKey = null;
            } else if (imageRequest.mPostprocessor != null) {
                cacheKey = ((DefaultCacheKeyFactory) cacheKeyFactory).getPostprocessedBitmapCacheKey(imageRequest, pipelineDraweeControllerBuilder.mCallerContext);
            } else {
                cacheKey = ((DefaultCacheKeyFactory) cacheKeyFactory).getBitmapCacheKey(imageRequest, pipelineDraweeControllerBuilder.mCallerContext);
            }
            pipelineDraweeController.initialize(supplier, valueOf, cacheKey, pipelineDraweeControllerBuilder.mCallerContext, null, null);
            pipelineDraweeController.initializePerformanceMonitoring(pipelineDraweeControllerBuilder.mImagePerfDataListener);
            FrescoSystrace.isTracing();
            pipelineDraweeController.mRetainImageOnFailure = false;
            pipelineDraweeController.mContentDescription = null;
            Set<ControllerListener> set = this.mBoundControllerListeners;
            if (set != null) {
                for (ControllerListener controllerListener : set) {
                    pipelineDraweeController.addControllerListener(controllerListener);
                }
            }
            ControllerListener<? super INFO> controllerListener2 = this.mControllerListener;
            if (controllerListener2 != null) {
                pipelineDraweeController.addControllerListener(controllerListener2);
            }
            if (this.mAutoPlayAnimations) {
                pipelineDraweeController.addControllerListener(sAutoPlayAnimationsListener);
            }
            return pipelineDraweeController;
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    public Supplier<DataSource<IMAGE>> getDataSourceSupplierForRequest(final DraweeController draweeController, final String str, final REQUEST request) {
        final CacheLevel cacheLevel = CacheLevel.FULL_FETCH;
        final Object obj = this.mCallerContext;
        return new Supplier<DataSource<IMAGE>>() { // from class: com.facebook.drawee.controller.AbstractDraweeControllerBuilder.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.facebook.common.internal.Supplier
            public Object get() {
                ImageRequest.RequestLevel requestLevel;
                AbstractDraweeControllerBuilder abstractDraweeControllerBuilder = AbstractDraweeControllerBuilder.this;
                DraweeController draweeController2 = draweeController;
                Object obj2 = request;
                Object obj3 = obj;
                CacheLevel cacheLevel2 = cacheLevel;
                PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder = (PipelineDraweeControllerBuilder) abstractDraweeControllerBuilder;
                Objects.requireNonNull(pipelineDraweeControllerBuilder);
                ImageRequest imageRequest = (ImageRequest) obj2;
                ImagePipeline imagePipeline = pipelineDraweeControllerBuilder.mImagePipeline;
                int ordinal = cacheLevel2.ordinal();
                if (ordinal == 0) {
                    requestLevel = ImageRequest.RequestLevel.FULL_FETCH;
                } else if (ordinal == 1) {
                    requestLevel = ImageRequest.RequestLevel.DISK_CACHE;
                } else if (ordinal == 2) {
                    requestLevel = ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE;
                } else {
                    throw new RuntimeException("Cache level" + cacheLevel2 + "is not supported. ");
                }
                ImageOriginRequestListener imageOriginRequestListener = null;
                if (draweeController2 instanceof PipelineDraweeController) {
                    PipelineDraweeController pipelineDraweeController = (PipelineDraweeController) draweeController2;
                    synchronized (pipelineDraweeController) {
                        ImageOriginListener imageOriginListener = pipelineDraweeController.mImageOriginListener;
                        if (imageOriginListener != null) {
                            imageOriginRequestListener = new ImageOriginRequestListener(pipelineDraweeController.mId, imageOriginListener);
                        }
                        Set<RequestListener> set = pipelineDraweeController.mRequestListeners;
                        if (set != null) {
                            ForwardingRequestListener forwardingRequestListener = new ForwardingRequestListener(set);
                            if (imageOriginRequestListener != null) {
                                forwardingRequestListener.mRequestListeners.add(imageOriginRequestListener);
                            }
                            imageOriginRequestListener = forwardingRequestListener;
                        }
                    }
                }
                return imagePipeline.fetchDecodedImage(imageRequest, obj3, requestLevel, imageOriginRequestListener);
            }

            public String toString() {
                Objects$ToStringHelper stringHelper = R$dimen.toStringHelper(this);
                stringHelper.addHolder("request", request.toString());
                return stringHelper.toString();
            }
        };
    }

    public final void init() {
        this.mCallerContext = null;
        this.mImageRequest = null;
        this.mLowResImageRequest = null;
        this.mTryCacheOnlyFirst = true;
        this.mControllerListener = null;
        this.mAutoPlayAnimations = false;
        this.mOldController = null;
    }
}
