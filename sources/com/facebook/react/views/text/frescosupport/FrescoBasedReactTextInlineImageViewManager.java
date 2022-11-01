package com.facebook.react.views.text.frescosupport;

import android.view.View;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilderSupplier;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import java.util.Objects;
@ReactModule(name = FrescoBasedReactTextInlineImageViewManager.REACT_CLASS)
/* loaded from: classes.dex */
public class FrescoBasedReactTextInlineImageViewManager extends ViewManager<View, FrescoBasedReactTextInlineImageShadowNode> {
    public static final String REACT_CLASS = "RCTTextInlineImage";
    private final Object mCallerContext;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;

    public FrescoBasedReactTextInlineImageViewManager() {
        this(null, null);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public View createViewInstance(ThemedReactContext themedReactContext) {
        throw new IllegalStateException("RCTTextInlineImage doesn't map into a native view");
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Class<? extends FrescoBasedReactTextInlineImageShadowNode> getShadowNodeClass() {
        return FrescoBasedReactTextInlineImageShadowNode.class;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void updateExtraData(View view, Object obj) {
    }

    public FrescoBasedReactTextInlineImageViewManager(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mCallerContext = obj;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public FrescoBasedReactTextInlineImageShadowNode createShadowNodeInstance() {
        AbstractDraweeControllerBuilder abstractDraweeControllerBuilder = this.mDraweeControllerBuilder;
        if (abstractDraweeControllerBuilder == null) {
            PipelineDraweeControllerBuilderSupplier pipelineDraweeControllerBuilderSupplier = Fresco.sDraweeControllerBuilderSupplier;
            Objects.requireNonNull(pipelineDraweeControllerBuilderSupplier);
            PipelineDraweeControllerBuilder pipelineDraweeControllerBuilder = new PipelineDraweeControllerBuilder(pipelineDraweeControllerBuilderSupplier.mContext, pipelineDraweeControllerBuilderSupplier.mPipelineDraweeControllerFactory, pipelineDraweeControllerBuilderSupplier.mImagePipeline, null);
            pipelineDraweeControllerBuilder.mImagePerfDataListener = null;
            abstractDraweeControllerBuilder = pipelineDraweeControllerBuilder;
        }
        return new FrescoBasedReactTextInlineImageShadowNode(abstractDraweeControllerBuilder, this.mCallerContext);
    }
}
