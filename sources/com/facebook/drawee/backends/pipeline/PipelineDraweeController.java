package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.recyclerview.R$dimen;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Objects$ToStringHelper;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.datasource.DataSource;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.backends.pipeline.debug.DebugOverlayImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ForwardingImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.debug.DebugControllerOverlayDrawable;
import com.facebook.drawee.debug.listener.ImageLoadingTimeControllerListener;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.drawee.interfaces.SettableDraweeHierarchy;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class PipelineDraweeController extends AbstractDraweeController<CloseableReference<CloseableImage>, ImageInfo> {
    public CacheKey mCacheKey;
    public ImmutableList<DrawableFactory> mCustomDrawableFactories;
    public Supplier<DataSource<CloseableReference<CloseableImage>>> mDataSourceSupplier;
    public DebugOverlayImageOriginListener mDebugOverlayImageOriginListener;
    public final DrawableFactory mDefaultDrawableFactory;
    public boolean mDrawDebugOverlay;
    public final ImmutableList<DrawableFactory> mGlobalDrawableFactories;
    public ImageOriginListener mImageOriginListener;
    public ImagePerfMonitor mImagePerfMonitor;
    public final MemoryCache<CacheKey, CloseableImage> mMemoryCache;
    public Set<RequestListener> mRequestListeners;

    public PipelineDraweeController(Resources resources, DeferredReleaser deferredReleaser, DrawableFactory drawableFactory, Executor executor, MemoryCache<CacheKey, CloseableImage> memoryCache, ImmutableList<DrawableFactory> immutableList) {
        super(deferredReleaser, executor, null, null);
        this.mDefaultDrawableFactory = new DefaultDrawableFactory(resources, drawableFactory);
        this.mGlobalDrawableFactories = immutableList;
        this.mMemoryCache = memoryCache;
    }

    public synchronized void addImageOriginListener(ImageOriginListener imageOriginListener) {
        ImageOriginListener imageOriginListener2 = this.mImageOriginListener;
        if (imageOriginListener2 instanceof ForwardingImageOriginListener) {
            ForwardingImageOriginListener forwardingImageOriginListener = (ForwardingImageOriginListener) imageOriginListener2;
            synchronized (forwardingImageOriginListener) {
                forwardingImageOriginListener.mImageOriginListeners.add(imageOriginListener);
            }
        } else if (imageOriginListener2 != null) {
            this.mImageOriginListener = new ForwardingImageOriginListener(imageOriginListener2, imageOriginListener);
        } else {
            this.mImageOriginListener = imageOriginListener;
        }
    }

    public synchronized void addRequestListener(RequestListener requestListener) {
        if (this.mRequestListeners == null) {
            this.mRequestListeners = new HashSet();
        }
        this.mRequestListeners.add(requestListener);
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public Drawable createDrawable(CloseableReference<CloseableImage> closeableReference) {
        CloseableReference<CloseableImage> closeableReference2 = closeableReference;
        try {
            FrescoSystrace.isTracing();
            R$dimen.checkState(CloseableReference.isValid(closeableReference2));
            CloseableImage closeableImage = closeableReference2.get();
            maybeUpdateDebugOverlay(closeableImage);
            Drawable maybeCreateDrawableFromFactories = maybeCreateDrawableFromFactories(this.mCustomDrawableFactories, closeableImage);
            if (maybeCreateDrawableFromFactories == null && (maybeCreateDrawableFromFactories = maybeCreateDrawableFromFactories(this.mGlobalDrawableFactories, closeableImage)) == null && (maybeCreateDrawableFromFactories = this.mDefaultDrawableFactory.createDrawable(closeableImage)) == null) {
                throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
            }
            return maybeCreateDrawableFromFactories;
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public CloseableReference<CloseableImage> getCachedImage() {
        CacheKey cacheKey;
        FrescoSystrace.isTracing();
        try {
            MemoryCache<CacheKey, CloseableImage> memoryCache = this.mMemoryCache;
            if (!(memoryCache == null || (cacheKey = this.mCacheKey) == null)) {
                CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
                if (closeableReference == null || ((ImmutableQualityInfo) closeableReference.get().getQualityInfo()).mIsOfFullQuality) {
                    return closeableReference;
                }
                closeableReference.close();
            }
            return null;
        } finally {
            FrescoSystrace.isTracing();
        }
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public DataSource<CloseableReference<CloseableImage>> getDataSource() {
        FrescoSystrace.isTracing();
        if (FLog.isLoggable(2)) {
            System.identityHashCode(this);
        }
        DataSource<CloseableReference<CloseableImage>> dataSource = this.mDataSourceSupplier.get();
        FrescoSystrace.isTracing();
        return dataSource;
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public int getImageHash(CloseableReference<CloseableImage> closeableReference) {
        CloseableReference<CloseableImage> closeableReference2 = closeableReference;
        if (closeableReference2 == null || !closeableReference2.isValid()) {
            return 0;
        }
        return System.identityHashCode(closeableReference2.mSharedReference.get());
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public ImageInfo getImageInfo(CloseableReference<CloseableImage> closeableReference) {
        CloseableReference<CloseableImage> closeableReference2 = closeableReference;
        R$dimen.checkState(CloseableReference.isValid(closeableReference2));
        return closeableReference2.get();
    }

    public void initialize(Supplier<DataSource<CloseableReference<CloseableImage>>> supplier, String str, CacheKey cacheKey, Object obj, ImmutableList<DrawableFactory> immutableList, ImageOriginListener imageOriginListener) {
        FrescoSystrace.isTracing();
        init(str, obj);
        this.mJustConstructed = false;
        this.mDataSourceSupplier = supplier;
        maybeUpdateDebugOverlay(null);
        this.mCacheKey = cacheKey;
        this.mCustomDrawableFactories = null;
        synchronized (this) {
            this.mImageOriginListener = null;
        }
        maybeUpdateDebugOverlay(null);
        addImageOriginListener(null);
        FrescoSystrace.isTracing();
    }

    public synchronized void initializePerformanceMonitoring(ImagePerfDataListener imagePerfDataListener) {
        ImagePerfMonitor imagePerfMonitor = this.mImagePerfMonitor;
        if (imagePerfMonitor != null) {
            imagePerfMonitor.reset();
        }
        if (imagePerfDataListener != null) {
            if (this.mImagePerfMonitor == null) {
                this.mImagePerfMonitor = new ImagePerfMonitor(AwakeTimeSinceBootClock.get(), this);
            }
            ImagePerfMonitor imagePerfMonitor2 = this.mImagePerfMonitor;
            Objects.requireNonNull(imagePerfMonitor2);
            if (imagePerfMonitor2.mImagePerfDataListeners == null) {
                imagePerfMonitor2.mImagePerfDataListeners = new LinkedList();
            }
            imagePerfMonitor2.mImagePerfDataListeners.add(imagePerfDataListener);
            this.mImagePerfMonitor.setEnabled(true);
        }
    }

    public final Drawable maybeCreateDrawableFromFactories(ImmutableList<DrawableFactory> immutableList, CloseableImage closeableImage) {
        Drawable createDrawable;
        if (immutableList == null) {
            return null;
        }
        Iterator<DrawableFactory> it = immutableList.iterator();
        while (it.hasNext()) {
            DrawableFactory next = it.next();
            if (next.supportsImageType(closeableImage) && (createDrawable = next.createDrawable(closeableImage)) != null) {
                return createDrawable;
            }
        }
        return null;
    }

    public final void maybeUpdateDebugOverlay(CloseableImage closeableImage) {
        ScaleTypeDrawable activeScaleTypeDrawable;
        if (this.mDrawDebugOverlay) {
            if (this.mControllerOverlay == null) {
                DebugControllerOverlayDrawable debugControllerOverlayDrawable = new DebugControllerOverlayDrawable();
                ImageLoadingTimeControllerListener imageLoadingTimeControllerListener = new ImageLoadingTimeControllerListener(debugControllerOverlayDrawable);
                this.mDebugOverlayImageOriginListener = new DebugOverlayImageOriginListener();
                addControllerListener(imageLoadingTimeControllerListener);
                this.mControllerOverlay = debugControllerOverlayDrawable;
                SettableDraweeHierarchy settableDraweeHierarchy = this.mSettableDraweeHierarchy;
                if (settableDraweeHierarchy != null) {
                    settableDraweeHierarchy.setControllerOverlay(debugControllerOverlayDrawable);
                }
            }
            if (this.mImageOriginListener == null) {
                addImageOriginListener(this.mDebugOverlayImageOriginListener);
            }
            Drawable drawable = this.mControllerOverlay;
            if (drawable instanceof DebugControllerOverlayDrawable) {
                DebugControllerOverlayDrawable debugControllerOverlayDrawable2 = (DebugControllerOverlayDrawable) drawable;
                String str = this.mId;
                if (str == null) {
                    str = "none";
                }
                debugControllerOverlayDrawable2.mControllerId = str;
                debugControllerOverlayDrawable2.invalidateSelf();
                SettableDraweeHierarchy settableDraweeHierarchy2 = this.mSettableDraweeHierarchy;
                ScalingUtils$ScaleType scalingUtils$ScaleType = null;
                if (!(settableDraweeHierarchy2 == null || (activeScaleTypeDrawable = R$dimen.getActiveScaleTypeDrawable(settableDraweeHierarchy2.getTopLevelDrawable())) == null)) {
                    scalingUtils$ScaleType = activeScaleTypeDrawable.mScaleType;
                }
                debugControllerOverlayDrawable2.mScaleType = scalingUtils$ScaleType;
                int i = this.mDebugOverlayImageOriginListener.mImageOrigin;
                debugControllerOverlayDrawable2.mOrigin = i != 2 ? i != 3 ? i != 4 ? i != 5 ? i != 6 ? "unknown" : "local" : "memory_bitmap" : "memory_encoded" : "disk" : "network";
                debugControllerOverlayDrawable2.invalidateSelf();
                if (closeableImage != null) {
                    CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
                    int width = closeableStaticBitmap.getWidth();
                    int height = closeableStaticBitmap.getHeight();
                    debugControllerOverlayDrawable2.mWidthPx = width;
                    debugControllerOverlayDrawable2.mHeightPx = height;
                    debugControllerOverlayDrawable2.invalidateSelf();
                    debugControllerOverlayDrawable2.mImageSizeBytes = closeableImage.getSizeInBytes();
                    return;
                }
                debugControllerOverlayDrawable2.reset();
            }
        }
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public void onImageLoadedFromCacheImmediately(String str, CloseableReference<CloseableImage> closeableReference) {
        synchronized (this) {
            ImageOriginListener imageOriginListener = this.mImageOriginListener;
            if (imageOriginListener != null) {
                imageOriginListener.onImageLoaded(str, 5, true, "PipelineDraweeController");
            }
        }
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public void releaseDrawable(Drawable drawable) {
        if (drawable instanceof DrawableWithCaches) {
            ((DrawableWithCaches) drawable).dropCaches();
        }
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public void releaseImage(CloseableReference<CloseableImage> closeableReference) {
        CloseableReference<CloseableImage> closeableReference2 = closeableReference;
        Class<CloseableReference> cls = CloseableReference.TAG;
        if (closeableReference2 != null) {
            closeableReference2.close();
        }
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController, com.facebook.drawee.interfaces.DraweeController
    public void setHierarchy(DraweeHierarchy draweeHierarchy) {
        super.setHierarchy(draweeHierarchy);
        maybeUpdateDebugOverlay(null);
    }

    @Override // com.facebook.drawee.controller.AbstractDraweeController
    public String toString() {
        Objects$ToStringHelper stringHelper = R$dimen.toStringHelper(this);
        stringHelper.addHolder("super", super.toString());
        stringHelper.addHolder("dataSourceSupplier", this.mDataSourceSupplier);
        return stringHelper.toString();
    }
}
