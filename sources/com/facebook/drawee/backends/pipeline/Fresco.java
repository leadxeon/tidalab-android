package com.facebook.drawee.backends.pipeline;

import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
/* loaded from: classes.dex */
public class Fresco {
    public static PipelineDraweeControllerBuilderSupplier sDraweeControllerBuilderSupplier = null;
    public static volatile boolean sIsInitialized = false;

    public static ImagePipeline getImagePipeline() {
        return ImagePipelineFactory.getInstance().getImagePipeline();
    }
}
