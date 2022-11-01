package com.facebook.react.views.image;

import android.graphics.PorterDuff;
import android.graphics.Shader;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilderSupplier;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.drawable.ScalingUtils$ScaleTypeCenterCrop;
import com.facebook.drawee.drawable.ScalingUtils$ScaleTypeCenterInside;
import com.facebook.drawee.drawable.ScalingUtils$ScaleTypeFitCenter;
import com.facebook.drawee.drawable.ScalingUtils$ScaleTypeFitXY;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
@ReactModule(name = ReactImageManager.REACT_CLASS)
/* loaded from: classes.dex */
public class ReactImageManager extends SimpleViewManager<ReactImageView> {
    public static final String REACT_CLASS = "RCTImageView";
    private final Object mCallerContext;
    private final ReactCallerContextFactory mCallerContextFactory;
    private AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private GlobalImageLoadListener mGlobalImageLoadListener;

    @Deprecated
    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this(abstractDraweeControllerBuilder, (GlobalImageLoadListener) null, obj);
    }

    @Deprecated
    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            PipelineDraweeControllerBuilderSupplier pipelineDraweeControllerBuilderSupplier = Fresco.sDraweeControllerBuilderSupplier;
            Objects.requireNonNull(pipelineDraweeControllerBuilderSupplier);
            PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder = new PipelineDraweeControllerBuilder(pipelineDraweeControllerBuilderSupplier.mContext, pipelineDraweeControllerBuilderSupplier.mPipelineDraweeControllerFactory, pipelineDraweeControllerBuilderSupplier.mImagePipeline, null);
            pipelineDraweeControllerBuilder.mImagePerfDataListener = null;
            this.mDraweeControllerBuilder = pipelineDraweeControllerBuilder;
        }
        return this.mDraweeControllerBuilder;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return R$style.of(ImageLoadEvent.eventNameForType(4), R$style.of("registrationName", "onLoadStart"), ImageLoadEvent.eventNameForType(2), R$style.of("registrationName", "onLoad"), ImageLoadEvent.eventNameForType(1), R$style.of("registrationName", "onError"), ImageLoadEvent.eventNameForType(3), R$style.of("registrationName", "onLoadEnd"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @ReactProp(name = "blurRadius")
    public void setBlurRadius(ReactImageView reactImageView, float f) {
        reactImageView.setBlurRadius(f);
    }

    @ReactProp(customType = "Color", name = "borderColor")
    public void setBorderColor(ReactImageView reactImageView, Integer num) {
        if (num == null) {
            reactImageView.setBorderColor(0);
        } else {
            reactImageView.setBorderColor(num.intValue());
        }
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactImageView reactImageView, int i, float f) {
        if (!R$style.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactImageView.setBorderRadius(f);
            return;
        }
        int i2 = i - 1;
        if (reactImageView.mBorderCornerRadii == null) {
            float[] fArr = new float[4];
            reactImageView.mBorderCornerRadii = fArr;
            Arrays.fill(fArr, Float.NaN);
        }
        if (!R$style.floatsEqual(reactImageView.mBorderCornerRadii[i2], f)) {
            reactImageView.mBorderCornerRadii[i2] = f;
            reactImageView.mIsDirty = true;
        }
    }

    @ReactProp(name = "borderWidth")
    public void setBorderWidth(ReactImageView reactImageView, float f) {
        reactImageView.setBorderWidth(f);
    }

    @ReactProp(name = "defaultSrc")
    public void setDefaultSource(ReactImageView reactImageView, String str) {
        reactImageView.setDefaultSource(str);
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(ReactImageView reactImageView, int i) {
        reactImageView.setFadeDuration(i);
    }

    @ReactProp(name = "headers")
    public void setHeaders(ReactImageView reactImageView, ReadableMap readableMap) {
        reactImageView.setHeaders(readableMap);
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(ReactImageView reactImageView, boolean z) {
        reactImageView.setShouldNotifyLoadEvents(z);
    }

    @ReactProp(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(ReactImageView reactImageView, String str) {
        reactImageView.setLoadingIndicatorSource(str);
    }

    @ReactProp(customType = "Color", name = "overlayColor")
    public void setOverlayColor(ReactImageView reactImageView, Integer num) {
        if (num == null) {
            reactImageView.setOverlayColor(0);
        } else {
            reactImageView.setOverlayColor(num.intValue());
        }
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(ReactImageView reactImageView, boolean z) {
        reactImageView.setProgressiveRenderingEnabled(z);
    }

    @ReactProp(name = "resizeMethod")
    public void setResizeMethod(ReactImageView reactImageView, String str) {
        if (str == null || "auto".equals(str)) {
            reactImageView.setResizeMethod(ImageResizeMethod.AUTO);
        } else if ("resize".equals(str)) {
            reactImageView.setResizeMethod(ImageResizeMethod.RESIZE);
        } else if ("scale".equals(str)) {
            reactImageView.setResizeMethod(ImageResizeMethod.SCALE);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline9("Invalid resize method: '", str, "'"));
        }
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(ReactImageView reactImageView, String str) {
        ScalingUtils$ScaleType scalingUtils$ScaleType;
        Shader.TileMode tileMode;
        if ("contain".equals(str)) {
            scalingUtils$ScaleType = ScalingUtils$ScaleTypeFitCenter.INSTANCE;
        } else if ("cover".equals(str)) {
            scalingUtils$ScaleType = ScalingUtils$ScaleTypeCenterCrop.INSTANCE;
        } else if ("stretch".equals(str)) {
            scalingUtils$ScaleType = ScalingUtils$ScaleTypeFitXY.INSTANCE;
        } else if ("center".equals(str)) {
            scalingUtils$ScaleType = ScalingUtils$ScaleTypeCenterInside.INSTANCE;
        } else if ("repeat".equals(str)) {
            scalingUtils$ScaleType = ScaleTypeStartInside.INSTANCE;
        } else if (str == null) {
            scalingUtils$ScaleType = ScalingUtils$ScaleTypeCenterCrop.INSTANCE;
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline9("Invalid resize mode: '", str, "'"));
        }
        reactImageView.setScaleType(scalingUtils$ScaleType);
        if ("contain".equals(str) || "cover".equals(str) || "stretch".equals(str) || "center".equals(str)) {
            tileMode = Shader.TileMode.CLAMP;
        } else if ("repeat".equals(str)) {
            tileMode = Shader.TileMode.REPEAT;
        } else if (str == null) {
            tileMode = Shader.TileMode.CLAMP;
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline9("Invalid resize mode: '", str, "'"));
        }
        reactImageView.setTileMode(tileMode);
    }

    @ReactProp(name = "src")
    public void setSource(ReactImageView reactImageView, ReadableArray readableArray) {
        reactImageView.setSource(readableArray);
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(ReactImageView reactImageView, Integer num) {
        if (num == null) {
            reactImageView.clearColorFilter();
        } else {
            reactImageView.setColorFilter(num.intValue(), PorterDuff.Mode.SRC_IN);
        }
    }

    @Deprecated
    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener, Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mGlobalImageLoadListener = globalImageLoadListener;
        this.mCallerContext = obj;
        this.mCallerContextFactory = null;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactImageView createViewInstance(ThemedReactContext themedReactContext) {
        Object obj;
        ReactCallerContextFactory reactCallerContextFactory = this.mCallerContextFactory;
        if (reactCallerContextFactory != null) {
            obj = reactCallerContextFactory.getOrCreateCallerContext(themedReactContext);
        } else {
            obj = getCallerContext();
        }
        return new ReactImageView(themedReactContext, getDraweeControllerBuilder(), this.mGlobalImageLoadListener, obj);
    }

    public void onAfterUpdateTransaction(ReactImageView reactImageView) {
        super.onAfterUpdateTransaction((ReactImageManager) reactImageView);
        reactImageView.maybeUpdateView();
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, ReactCallerContextFactory reactCallerContextFactory) {
        this(abstractDraweeControllerBuilder, (GlobalImageLoadListener) null, reactCallerContextFactory);
    }

    public ReactImageManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener, ReactCallerContextFactory reactCallerContextFactory) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mGlobalImageLoadListener = globalImageLoadListener;
        this.mCallerContextFactory = reactCallerContextFactory;
        this.mCallerContext = null;
    }

    public ReactImageManager() {
        this.mDraweeControllerBuilder = null;
        this.mCallerContext = null;
        this.mCallerContextFactory = null;
    }
}
