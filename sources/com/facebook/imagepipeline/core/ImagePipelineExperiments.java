package com.facebook.imagepipeline.core;

import com.facebook.imagepipeline.core.ImagePipelineConfig;
import java.util.Objects;
/* loaded from: classes.dex */
public class ImagePipelineExperiments {
    public final int mMaxBitmapSize = 2048;
    public final ProducerFactoryMethod mProducerFactoryMethod = new DefaultProducerFactoryMethod();

    /* loaded from: classes.dex */
    public static class Builder {
        public Builder(ImagePipelineConfig.Builder builder) {
        }
    }

    /* loaded from: classes.dex */
    public static class DefaultProducerFactoryMethod implements ProducerFactoryMethod {
    }

    /* loaded from: classes.dex */
    public interface ProducerFactoryMethod {
    }

    public ImagePipelineExperiments(Builder builder, AnonymousClass1 r2) {
        Objects.requireNonNull(builder);
    }
}
