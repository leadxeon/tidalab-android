package com.facebook.imagepipeline.transcoder;

import android.graphics.Bitmap;
import androidx.recyclerview.R$dimen;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
/* loaded from: classes.dex */
public class SimpleImageTranscoder implements ImageTranscoder {
    public final int mMaxBitmapSize;
    public final boolean mResizingEnabled;

    public SimpleImageTranscoder(boolean z, int i) {
        this.mResizingEnabled = z;
        this.mMaxBitmapSize = i;
    }

    public static Bitmap.CompressFormat getOutputFormat(ImageFormat imageFormat) {
        if (imageFormat == null) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (imageFormat == DefaultImageFormats.JPEG) {
            return Bitmap.CompressFormat.JPEG;
        }
        if (imageFormat == DefaultImageFormats.PNG) {
            return Bitmap.CompressFormat.PNG;
        }
        if (DefaultImageFormats.isStaticWebpFormat(imageFormat)) {
            return Bitmap.CompressFormat.WEBP;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public boolean canResize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.ROTATION_OPTIONS_AUTO_ROTATE;
        }
        return this.mResizingEnabled && R$dimen.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.mMaxBitmapSize) > 1;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public boolean canTranscode(ImageFormat imageFormat) {
        return imageFormat == DefaultImageFormats.HEIF || imageFormat == DefaultImageFormats.JPEG;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public String getIdentifier() {
        return "SimpleImageTranscoder";
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d0  */
    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.imagepipeline.transcoder.ImageTranscodeResult transcode(com.facebook.imagepipeline.image.EncodedImage r20, java.io.OutputStream r21, com.facebook.imagepipeline.common.RotationOptions r22, com.facebook.imagepipeline.common.ResizeOptions r23, com.facebook.imageformat.ImageFormat r24, java.lang.Integer r25) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.transcoder.SimpleImageTranscoder.transcode(com.facebook.imagepipeline.image.EncodedImage, java.io.OutputStream, com.facebook.imagepipeline.common.RotationOptions, com.facebook.imagepipeline.common.ResizeOptions, com.facebook.imageformat.ImageFormat, java.lang.Integer):com.facebook.imagepipeline.transcoder.ImageTranscodeResult");
    }
}
