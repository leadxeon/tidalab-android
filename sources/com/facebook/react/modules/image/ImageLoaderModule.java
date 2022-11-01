package com.facebook.react.modules.image;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.SparseArray;
import androidx.recyclerview.R$dimen;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.AbstractDataSource;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import java.util.Objects;
@ReactModule(name = ImageLoaderModule.NAME)
/* loaded from: classes.dex */
public class ImageLoaderModule extends NativeImageLoaderAndroidSpec implements LifecycleEventListener {
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    public static final String NAME = "ImageLoader";
    private final Object mCallerContext;
    private final Object mEnqueuedRequestMonitor;
    private final SparseArray<DataSource<Void>> mEnqueuedRequests;

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mCallerContext = this;
    }

    private void registerRequest(int i, DataSource<Void> dataSource) {
        synchronized (this.mEnqueuedRequestMonitor) {
            this.mEnqueuedRequests.put(i, dataSource);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DataSource<Void> removeRequest(int i) {
        DataSource<Void> dataSource;
        synchronized (this.mEnqueuedRequestMonitor) {
            dataSource = this.mEnqueuedRequests.get(i);
            this.mEnqueuedRequests.remove(i);
        }
        return dataSource;
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    public void abortRequest(double d) {
        DataSource<Void> removeRequest = removeRequest((int) d);
        if (removeRequest != null) {
            removeRequest.close();
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void getSize(String str, final Promise promise) {
        Uri uri;
        if (str == null || str.isEmpty()) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        try {
            uri = Uri.parse(str);
            if (uri.getScheme() == null) {
                uri = ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(reactApplicationContext, str);
            }
        } catch (Exception unused) {
            uri = ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(reactApplicationContext, str);
        }
        R$dimen.assertNotNull(uri);
        ((AbstractDataSource) Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(uri).build(), this.mCallerContext, ImageRequest.RequestLevel.FULL_FETCH, null)).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>(this) { // from class: com.facebook.react.modules.image.ImageLoaderModule.1
            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.getFailureCause());
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (dataSource.isFinished()) {
                    CloseableReference<CloseableImage> result = dataSource.getResult();
                    try {
                        if (result != null) {
                            try {
                                WritableMap createMap = Arguments.createMap();
                                CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) result.get();
                                createMap.putInt("width", closeableStaticBitmap.getWidth());
                                createMap.putInt("height", closeableStaticBitmap.getHeight());
                                promise.resolve(createMap);
                            } catch (Exception e) {
                                promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, e);
                            }
                            return;
                        }
                        promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE);
                    } finally {
                        result.close();
                    }
                }
            }
        }, CallerThreadExecutor.sInstance);
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void getSizeWithHeaders(String str, ReadableMap readableMap, final Promise promise) {
        Uri uri;
        if (str == null || str.isEmpty()) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        try {
            uri = Uri.parse(str);
            if (uri.getScheme() == null) {
                uri = ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(reactApplicationContext, str);
            }
        } catch (Exception unused) {
            uri = ResourceDrawableIdHelper.getInstance().getResourceDrawableUri(reactApplicationContext, str);
        }
        R$dimen.assertNotNull(uri);
        ((AbstractDataSource) Fresco.getImagePipeline().fetchDecodedImage(new ReactNetworkImageRequest(ImageRequestBuilder.newBuilderWithSource(uri), readableMap), this.mCallerContext, ImageRequest.RequestLevel.FULL_FETCH, null)).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>(this) { // from class: com.facebook.react.modules.image.ImageLoaderModule.2
            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, dataSource.getFailureCause());
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                if (dataSource.isFinished()) {
                    CloseableReference<CloseableImage> result = dataSource.getResult();
                    try {
                        if (result != null) {
                            try {
                                WritableMap createMap = Arguments.createMap();
                                CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) result.get();
                                createMap.putInt("width", closeableStaticBitmap.getWidth());
                                createMap.putInt("height", closeableStaticBitmap.getHeight());
                                promise.resolve(createMap);
                            } catch (Exception e) {
                                promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE, e);
                            }
                            return;
                        }
                        promise.reject(ImageLoaderModule.ERROR_GET_SIZE_FAILURE);
                    } finally {
                        result.close();
                    }
                }
            }
        }, CallerThreadExecutor.sInstance);
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        synchronized (this.mEnqueuedRequestMonitor) {
            int size = this.mEnqueuedRequests.size();
            for (int i = 0; i < size; i++) {
                DataSource<Void> valueAt = this.mEnqueuedRequests.valueAt(i);
                if (valueAt != null) {
                    valueAt.close();
                }
            }
            this.mEnqueuedRequests.clear();
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    public void prefetchImage(String str, double d, final Promise promise) {
        DataSource<Void> immediateFailedDataSource;
        final int i = (int) d;
        if (str == null || str.isEmpty()) {
            promise.reject(ERROR_INVALID_URI, "Cannot prefetch an image for an empty URI");
            return;
        }
        ImageRequest build = ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        Object obj = this.mCallerContext;
        Priority priority = Priority.MEDIUM;
        if (!imagePipeline.mIsPrefetchEnabledSupplier.get().booleanValue()) {
            immediateFailedDataSource = R$dimen.immediateFailedDataSource(ImagePipeline.PREFETCH_EXCEPTION);
        } else {
            try {
                Producer<Void> encodedImagePrefetchProducerSequence = imagePipeline.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(build);
                ImageRequest.RequestLevel requestLevel = ImageRequest.RequestLevel.FULL_FETCH;
                RequestListener requestListenerForRequest = imagePipeline.getRequestListenerForRequest(build, null);
                CallerContextVerifier callerContextVerifier = imagePipeline.mCallerContextVerifier;
                if (callerContextVerifier != null) {
                    callerContextVerifier.verifyCallerContext(obj);
                }
                try {
                    ImageRequest.RequestLevel requestLevel2 = build.mLowestPermittedRequestLevel;
                    immediateFailedDataSource = new ProducerToDataSourceAdapter<>(encodedImagePrefetchProducerSequence, new SettableProducerContext(build, String.valueOf(imagePipeline.mIdCounter.getAndIncrement()), requestListenerForRequest, obj, requestLevel2.mValue > 1 ? requestLevel2 : requestLevel, true, false, priority), requestListenerForRequest);
                } catch (Exception e) {
                    immediateFailedDataSource = R$dimen.immediateFailedDataSource(e);
                }
            } catch (Exception e2) {
                immediateFailedDataSource = R$dimen.immediateFailedDataSource(e2);
            }
        }
        BaseDataSubscriber<Void> baseDataSubscriber = new BaseDataSubscriber<Void>() { // from class: com.facebook.react.modules.image.ImageLoaderModule.3
            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onFailureImpl(DataSource<Void> dataSource) {
                try {
                    ImageLoaderModule.this.removeRequest(i);
                    promise.reject(ImageLoaderModule.ERROR_PREFETCH_FAILURE, dataSource.getFailureCause());
                } finally {
                    dataSource.close();
                }
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            public void onNewResultImpl(DataSource<Void> dataSource) {
                if (dataSource.isFinished()) {
                    try {
                        ImageLoaderModule.this.removeRequest(i);
                        promise.resolve(Boolean.TRUE);
                    } finally {
                        dataSource.close();
                    }
                }
            }
        };
        registerRequest(i, immediateFailedDataSource);
        ((AbstractDataSource) immediateFailedDataSource).subscribe(baseDataSubscriber, CallerThreadExecutor.sInstance);
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void queryCache(final ReadableArray readableArray, final Promise promise) {
        new GuardedAsyncTask<Void, Void>(this, getReactApplicationContext()) { // from class: com.facebook.react.modules.image.ImageLoaderModule.4
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void[] voidArr) {
                WritableMap createMap = Arguments.createMap();
                ImagePipeline imagePipeline = Fresco.getImagePipeline();
                for (int i = 0; i < readableArray.size(); i++) {
                    String string = readableArray.getString(i);
                    Uri parse = Uri.parse(string);
                    Objects.requireNonNull(imagePipeline);
                    if (parse == null ? false : imagePipeline.mBitmapMemoryCache.contains(new ImagePipeline.AnonymousClass7(imagePipeline, parse))) {
                        createMap.putString(string, "memory");
                    } else if (imagePipeline.isInDiskCacheSync(parse)) {
                        createMap.putString(string, "disk");
                    }
                }
                promise.resolve(createMap);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public ImageLoaderModule(ReactApplicationContext reactApplicationContext, Object obj) {
        super(reactApplicationContext);
        this.mEnqueuedRequestMonitor = new Object();
        this.mEnqueuedRequests = new SparseArray<>();
        this.mCallerContext = obj;
    }
}
