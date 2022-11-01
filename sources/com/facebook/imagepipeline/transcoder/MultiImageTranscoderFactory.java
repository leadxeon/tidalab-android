package com.facebook.imagepipeline.transcoder;

import com.facebook.imageformat.ImageFormat;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes.dex */
public class MultiImageTranscoderFactory implements ImageTranscoderFactory {
    public final int mMaxBitmapSize;

    public MultiImageTranscoderFactory(int i, boolean z, ImageTranscoderFactory imageTranscoderFactory, Integer num) {
        this.mMaxBitmapSize = i;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoderFactory
    public ImageTranscoder createImageTranscoder(ImageFormat imageFormat, boolean z) {
        ImageTranscoder nativeImageTranscoder = getNativeImageTranscoder(imageFormat, z);
        return nativeImageTranscoder == null ? new SimpleImageTranscoder(z, this.mMaxBitmapSize) : nativeImageTranscoder;
    }

    public final ImageTranscoder getNativeImageTranscoder(ImageFormat imageFormat, boolean z) {
        try {
            return ((ImageTranscoderFactory) Class.forName("com.facebook.imagepipeline.nativecode.NativeJpegTranscoderFactory").getConstructor(Integer.TYPE, Boolean.TYPE).newInstance(Integer.valueOf(this.mMaxBitmapSize), Boolean.FALSE)).createImageTranscoder(imageFormat, z);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
            throw new RuntimeException("Dependency ':native-imagetranscoder' is needed to use the default native image transcoder.", e);
        }
    }
}
