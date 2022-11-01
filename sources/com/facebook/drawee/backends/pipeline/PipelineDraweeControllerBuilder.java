package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import com.facebook.common.references.CloseableReference;
import com.facebook.drawee.backends.pipeline.info.ImagePerfDataListener;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Set;
/* loaded from: classes.dex */
public class PipelineDraweeControllerBuilder extends AbstractDraweeControllerBuilder<PipelineDraweeControllerBuilder, ImageRequest, CloseableReference<CloseableImage>, ImageInfo> {
    public ImagePerfDataListener mImagePerfDataListener;
    public final ImagePipeline mImagePipeline;
    public final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public PipelineDraweeControllerBuilder(Context context, PipelineDraweeControllerFactory pipelineDraweeControllerFactory, ImagePipeline imagePipeline, Set<ControllerListener> set) {
        super(context, set);
        this.mImagePipeline = imagePipeline;
        this.mPipelineDraweeControllerFactory = pipelineDraweeControllerFactory;
    }
}
