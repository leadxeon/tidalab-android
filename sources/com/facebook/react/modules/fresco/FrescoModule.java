package com.facebook.react.modules.fresco;

import android.content.Context;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilderSupplier;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.react.R$style;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.network.CookieJarContainer;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.soloader.SoLoader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
@ReactModule(name = FrescoModule.NAME, needsEagerInit = true)
/* loaded from: classes.dex */
public class FrescoModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    public static final String NAME = "FrescoModule";
    private static boolean sHasBeenInitialized = false;
    private final boolean mClearOnDestroy;
    private ImagePipelineConfig mConfig;

    public FrescoModule(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, true, null);
    }

    private static ImagePipelineConfig getDefaultConfig(ReactContext reactContext) {
        ImagePipelineConfig.Builder defaultConfigBuilder = getDefaultConfigBuilder(reactContext);
        Objects.requireNonNull(defaultConfigBuilder);
        return new ImagePipelineConfig(defaultConfigBuilder, null);
    }

    public static ImagePipelineConfig.Builder getDefaultConfigBuilder(ReactContext reactContext) {
        HashSet hashSet = new HashSet();
        hashSet.add(new SystraceRequestListener());
        OkHttpClient build = R$style.createClientBuilder().build();
        ((CookieJarContainer) build.cookieJar()).setCookieJar(new JavaNetCookieJar(new ForwardingCookieHandler(reactContext)));
        ImagePipelineConfig.Builder builder = new ImagePipelineConfig.Builder(reactContext.getApplicationContext(), null);
        builder.mNetworkFetcher = new OkHttpNetworkFetcher(build);
        builder.mNetworkFetcher = new ReactOkHttpNetworkFetcher(build);
        builder.mDownsampleEnabled = false;
        builder.mRequestListeners = hashSet;
        return builder;
    }

    public static boolean hasBeenInitialized() {
        return sHasBeenInitialized;
    }

    public void clearSensitiveData() {
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        ImagePipeline.AnonymousClass4 r1 = new ImagePipeline.AnonymousClass4(imagePipeline);
        imagePipeline.mBitmapMemoryCache.removeAll(r1);
        imagePipeline.mEncodedMemoryCache.removeAll(r1);
        imagePipeline.mMainBufferedDiskCache.clearAll();
        imagePipeline.mSmallImageBufferedDiskCache.clearAll();
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        super.initialize();
        getReactApplicationContext().addLifecycleEventListener(this);
        if (!hasBeenInitialized()) {
            if (this.mConfig == null) {
                this.mConfig = getDefaultConfig(getReactApplicationContext());
            }
            Context applicationContext = getReactApplicationContext().getApplicationContext();
            ImagePipelineConfig imagePipelineConfig = this.mConfig;
            FrescoSystrace.isTracing();
            if (Fresco.sIsInitialized) {
                FLog.w(Fresco.class, "Fresco has already been initialized! `Fresco.initialize(...)` should only be called 1 single time to avoid memory leaks!");
            } else {
                Fresco.sIsInitialized = true;
            }
            try {
                FrescoSystrace.isTracing();
                SoLoader.init(applicationContext, 0);
                FrescoSystrace.isTracing();
                Context applicationContext2 = applicationContext.getApplicationContext();
                if (imagePipelineConfig == null) {
                    synchronized (ImagePipelineFactory.class) {
                        FrescoSystrace.isTracing();
                        ImagePipelineFactory.initialize(new ImagePipelineConfig(new ImagePipelineConfig.Builder(applicationContext2, null), null));
                        FrescoSystrace.isTracing();
                    }
                } else {
                    ImagePipelineFactory.initialize(imagePipelineConfig);
                }
                FrescoSystrace.isTracing();
                Fresco.sDraweeControllerBuilderSupplier = new PipelineDraweeControllerBuilderSupplier(applicationContext2);
                int i = SimpleDraweeView.$r8$clinit;
                FrescoSystrace.isTracing();
                FrescoSystrace.isTracing();
                sHasBeenInitialized = true;
            } catch (IOException e) {
                FrescoSystrace.isTracing();
                throw new RuntimeException("Could not initialize SoLoader", e);
            }
        } else if (this.mConfig != null) {
            FLog.w("ReactNative", "Fresco has already been initialized with a different config. The new Fresco configuration will be ignored!");
        }
        this.mConfig = null;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        if (hasBeenInitialized() && this.mClearOnDestroy) {
            ImagePipeline imagePipeline = Fresco.getImagePipeline();
            ImagePipeline.AnonymousClass4 r1 = new ImagePipeline.AnonymousClass4(imagePipeline);
            imagePipeline.mBitmapMemoryCache.removeAll(r1);
            imagePipeline.mEncodedMemoryCache.removeAll(r1);
        }
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, boolean z) {
        this(reactApplicationContext, z, null);
    }

    public FrescoModule(ReactApplicationContext reactApplicationContext, boolean z, ImagePipelineConfig imagePipelineConfig) {
        super(reactApplicationContext);
        this.mClearOnDestroy = z;
        this.mConfig = imagePipelineConfig;
    }
}
