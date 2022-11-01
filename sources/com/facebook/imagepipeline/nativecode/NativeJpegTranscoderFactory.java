package com.facebook.imagepipeline.nativecode;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.transcoder.ImageTranscoder;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
@DoNotStrip
/* loaded from: classes.dex */
public class NativeJpegTranscoderFactory implements ImageTranscoderFactory {
    public final int mMaxBitmapSize;
    public final boolean mUseDownSamplingRatio;

    @DoNotStrip
    public NativeJpegTranscoderFactory(int i, boolean z) {
        this.mMaxBitmapSize = i;
        this.mUseDownSamplingRatio = z;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoderFactory
    @DoNotStrip
    public ImageTranscoder createImageTranscoder(ImageFormat imageFormat, boolean z) {
        if (imageFormat != DefaultImageFormats.JPEG) {
            return null;
        }
        return new NativeJpegTranscoder(z, this.mMaxBitmapSize, this.mUseDownSamplingRatio);
    }
}
