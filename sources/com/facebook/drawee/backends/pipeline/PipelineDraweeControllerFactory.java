package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class PipelineDraweeControllerFactory {
    public DrawableFactory mAnimatedDrawableFactory;
    public Supplier<Boolean> mDebugOverlayEnabledSupplier;
    public DeferredReleaser mDeferredReleaser;
    public ImmutableList<DrawableFactory> mDrawableFactories;
    public MemoryCache<CacheKey, CloseableImage> mMemoryCache;
    public Resources mResources;
    public Executor mUiThreadExecutor;
}
