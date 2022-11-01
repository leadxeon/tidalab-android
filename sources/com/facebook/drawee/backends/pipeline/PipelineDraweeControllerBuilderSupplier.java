package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.content.res.Resources;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
/* loaded from: classes.dex */
public class PipelineDraweeControllerBuilderSupplier implements Supplier<PipelineDraweeControllerBuilder> {
    public final Context mContext;
    public final ImagePipeline mImagePipeline;
    public final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public PipelineDraweeControllerBuilderSupplier(Context context) {
        DeferredReleaser deferredReleaser;
        ImagePipelineFactory instance = ImagePipelineFactory.getInstance();
        this.mContext = context;
        ImagePipeline imagePipeline = instance.getImagePipeline();
        this.mImagePipeline = imagePipeline;
        PipelineDraweeControllerFactory pipelineDraweeControllerFactory = new PipelineDraweeControllerFactory();
        this.mPipelineDraweeControllerFactory = pipelineDraweeControllerFactory;
        Resources resources = context.getResources();
        synchronized (DeferredReleaser.class) {
            if (DeferredReleaser.sInstance == null) {
                DeferredReleaser.sInstance = new DeferredReleaser();
            }
            deferredReleaser = DeferredReleaser.sInstance;
        }
        AnimatedFactory animatedFactory = instance.getAnimatedFactory();
        DrawableFactory animatedDrawableFactory = animatedFactory == null ? null : animatedFactory.getAnimatedDrawableFactory(context);
        if (UiThreadImmediateExecutorService.sInstance == null) {
            UiThreadImmediateExecutorService.sInstance = new UiThreadImmediateExecutorService();
        }
        UiThreadImmediateExecutorService uiThreadImmediateExecutorService = UiThreadImmediateExecutorService.sInstance;
        MemoryCache<CacheKey, CloseableImage> memoryCache = imagePipeline.mBitmapMemoryCache;
        pipelineDraweeControllerFactory.mResources = resources;
        pipelineDraweeControllerFactory.mDeferredReleaser = deferredReleaser;
        pipelineDraweeControllerFactory.mAnimatedDrawableFactory = animatedDrawableFactory;
        pipelineDraweeControllerFactory.mUiThreadExecutor = uiThreadImmediateExecutorService;
        pipelineDraweeControllerFactory.mMemoryCache = memoryCache;
        pipelineDraweeControllerFactory.mDrawableFactories = null;
        pipelineDraweeControllerFactory.mDebugOverlayEnabledSupplier = null;
    }

    @Override // com.facebook.common.internal.Supplier
    public PipelineDraweeControllerBuilder get() {
        PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder = new PipelineDraweeControllerBuilder(this.mContext, this.mPipelineDraweeControllerFactory, this.mImagePipeline, null);
        pipelineDraweeControllerBuilder.mImagePerfDataListener = null;
        return pipelineDraweeControllerBuilder;
    }
}
