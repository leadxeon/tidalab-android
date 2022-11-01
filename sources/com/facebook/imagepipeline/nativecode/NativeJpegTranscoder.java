package com.facebook.imagepipeline.nativecode;

import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.ImmutableList;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.transcoder.ImageTranscodeResult;
import com.facebook.imagepipeline.transcoder.ImageTranscoder;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
@DoNotStrip
/* loaded from: classes.dex */
public class NativeJpegTranscoder implements ImageTranscoder {
    public int mMaxBitmapSize;
    public boolean mResizingEnabled;
    public boolean mUseDownsamplingRatio;

    static {
        R$dimen.ensure();
    }

    public NativeJpegTranscoder(boolean z, int i, boolean z2) {
        this.mResizingEnabled = z;
        this.mMaxBitmapSize = i;
        this.mUseDownsamplingRatio = z2;
    }

    @DoNotStrip
    private static native void nativeTranscodeJpeg(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @DoNotStrip
    private static native void nativeTranscodeJpegWithExifOrientation(InputStream inputStream, OutputStream outputStream, int i, int i2, int i3) throws IOException;

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public boolean canResize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.ROTATION_OPTIONS_AUTO_ROTATE;
        }
        return JpegTranscoderUtils.getSoftwareNumerator(rotationOptions, resizeOptions, encodedImage, this.mResizingEnabled) < 8;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public boolean canTranscode(ImageFormat imageFormat) {
        return imageFormat == DefaultImageFormats.JPEG;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public String getIdentifier() {
        return "NativeJpegTranscoder";
    }

    /* JADX WARN: Finally extract failed */
    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public ImageTranscodeResult transcode(EncodedImage encodedImage, OutputStream outputStream, RotationOptions rotationOptions, ResizeOptions resizeOptions, ImageFormat imageFormat, Integer num) throws IOException {
        boolean z;
        boolean z2;
        boolean z3;
        if (num == null) {
            num = 85;
        }
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.ROTATION_OPTIONS_AUTO_ROTATE;
        }
        int determineSampleSize = R$dimen.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.mMaxBitmapSize);
        try {
            int softwareNumerator = JpegTranscoderUtils.getSoftwareNumerator(rotationOptions, resizeOptions, encodedImage, this.mResizingEnabled);
            int i = 1;
            softwareNumerator = Math.max(1, 8 / determineSampleSize);
            if (this.mUseDownsamplingRatio) {
            }
            InputStream inputStream = encodedImage.getInputStream();
            ImmutableList<Integer> immutableList = JpegTranscoderUtils.INVERTED_EXIF_ORIENTATIONS;
            encodedImage.parseMetaDataIfNeeded();
            if (immutableList.contains(Integer.valueOf(encodedImage.mExifOrientation))) {
                int forceRotatedInvertedExifOrientation = JpegTranscoderUtils.getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage);
                int intValue = num.intValue();
                R$dimen.ensure();
                R$dimen.checkArgument(softwareNumerator >= 1);
                R$dimen.checkArgument(softwareNumerator <= 16);
                R$dimen.checkArgument(intValue >= 0);
                R$dimen.checkArgument(intValue <= 100);
                ImmutableList<Integer> immutableList2 = JpegTranscoderUtils.INVERTED_EXIF_ORIENTATIONS;
                switch (forceRotatedInvertedExifOrientation) {
                    case 1:
                    case 2:
                    case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                        z2 = true;
                        break;
                    default:
                        z2 = false;
                        break;
                }
                R$dimen.checkArgument(z2);
                if (softwareNumerator == 8 && forceRotatedInvertedExifOrientation == 1) {
                    z3 = false;
                    R$dimen.checkArgument(z3, "no transformation requested");
                    Objects.requireNonNull(inputStream);
                    Objects.requireNonNull(outputStream);
                    nativeTranscodeJpegWithExifOrientation(inputStream, outputStream, forceRotatedInvertedExifOrientation, softwareNumerator, intValue);
                }
                z3 = true;
                R$dimen.checkArgument(z3, "no transformation requested");
                Objects.requireNonNull(inputStream);
                Objects.requireNonNull(outputStream);
                nativeTranscodeJpegWithExifOrientation(inputStream, outputStream, forceRotatedInvertedExifOrientation, softwareNumerator, intValue);
            } else {
                int rotationAngle = JpegTranscoderUtils.getRotationAngle(rotationOptions, encodedImage);
                int intValue2 = num.intValue();
                R$dimen.ensure();
                R$dimen.checkArgument(softwareNumerator >= 1);
                R$dimen.checkArgument(softwareNumerator <= 16);
                R$dimen.checkArgument(intValue2 >= 0);
                R$dimen.checkArgument(intValue2 <= 100);
                ImmutableList<Integer> immutableList3 = JpegTranscoderUtils.INVERTED_EXIF_ORIENTATIONS;
                R$dimen.checkArgument(rotationAngle >= 0 && rotationAngle <= 270 && rotationAngle % 90 == 0);
                if (softwareNumerator == 8 && rotationAngle == 0) {
                    z = false;
                    R$dimen.checkArgument(z, "no transformation requested");
                    Objects.requireNonNull(inputStream);
                    Objects.requireNonNull(outputStream);
                    nativeTranscodeJpeg(inputStream, outputStream, rotationAngle, softwareNumerator, intValue2);
                }
                z = true;
                R$dimen.checkArgument(z, "no transformation requested");
                Objects.requireNonNull(inputStream);
                Objects.requireNonNull(outputStream);
                nativeTranscodeJpeg(inputStream, outputStream, rotationAngle, softwareNumerator, intValue2);
            }
            Closeables.closeQuietly(inputStream);
            if (determineSampleSize != 1) {
                i = 0;
            }
            return new ImageTranscodeResult(i);
        } catch (Throwable th) {
            Closeables.closeQuietly(null);
            throw th;
        }
    }
}
