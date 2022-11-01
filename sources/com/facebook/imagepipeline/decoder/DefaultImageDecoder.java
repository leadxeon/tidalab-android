package com.facebook.imagepipeline.decoder;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.transformation.BitmapTransformation;
import java.util.Objects;
/* loaded from: classes.dex */
public class DefaultImageDecoder implements ImageDecoder {
    public final ImageDecoder mAnimatedGifDecoder;
    public final ImageDecoder mAnimatedWebPDecoder;
    public final ImageDecoder mDefaultDecoder = new ImageDecoder() { // from class: com.facebook.imagepipeline.decoder.DefaultImageDecoder.1
        @Override // com.facebook.imagepipeline.decoder.ImageDecoder
        public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
            encodedImage.parseMetaDataIfNeeded();
            ImageFormat imageFormat = encodedImage.mImageFormat;
            if (imageFormat == DefaultImageFormats.JPEG) {
                DefaultImageDecoder defaultImageDecoder = DefaultImageDecoder.this;
                CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace = defaultImageDecoder.mPlatformDecoder.decodeJPEGFromEncodedImageWithColorSpace(encodedImage, imageDecodeOptions.bitmapConfig, null, i, null);
                try {
                    defaultImageDecoder.maybeApplyTransformation(null, decodeJPEGFromEncodedImageWithColorSpace);
                    encodedImage.parseMetaDataIfNeeded();
                    int i2 = encodedImage.mRotationAngle;
                    encodedImage.parseMetaDataIfNeeded();
                    return new CloseableStaticBitmap(decodeJPEGFromEncodedImageWithColorSpace, qualityInfo, i2, encodedImage.mExifOrientation);
                } finally {
                    decodeJPEGFromEncodedImageWithColorSpace.close();
                }
            } else if (imageFormat == DefaultImageFormats.GIF) {
                DefaultImageDecoder defaultImageDecoder2 = DefaultImageDecoder.this;
                Objects.requireNonNull(defaultImageDecoder2);
                encodedImage.parseMetaDataIfNeeded();
                if (encodedImage.mWidth != -1) {
                    encodedImage.parseMetaDataIfNeeded();
                    if (encodedImage.mHeight != -1) {
                        Objects.requireNonNull(imageDecodeOptions);
                        ImageDecoder imageDecoder = defaultImageDecoder2.mAnimatedGifDecoder;
                        if (imageDecoder != null) {
                            return imageDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
                        }
                        return defaultImageDecoder2.decodeStaticImage(encodedImage, imageDecodeOptions);
                    }
                }
                throw new DecodeException("image width or height is incorrect", encodedImage);
            } else if (imageFormat == DefaultImageFormats.WEBP_ANIMATED) {
                return DefaultImageDecoder.this.mAnimatedWebPDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
            } else {
                if (imageFormat != ImageFormat.UNKNOWN) {
                    return DefaultImageDecoder.this.decodeStaticImage(encodedImage, imageDecodeOptions);
                }
                throw new DecodeException("unknown image format", encodedImage);
            }
        }
    };
    public final PlatformDecoder mPlatformDecoder;

    public DefaultImageDecoder(ImageDecoder imageDecoder, ImageDecoder imageDecoder2, PlatformDecoder platformDecoder) {
        this.mAnimatedGifDecoder = imageDecoder;
        this.mAnimatedWebPDecoder = imageDecoder2;
        this.mPlatformDecoder = platformDecoder;
    }

    @Override // com.facebook.imagepipeline.decoder.ImageDecoder
    public CloseableImage decode(EncodedImage encodedImage, int i, QualityInfo qualityInfo, ImageDecodeOptions imageDecodeOptions) {
        Objects.requireNonNull(imageDecodeOptions);
        encodedImage.parseMetaDataIfNeeded();
        ImageFormat imageFormat = encodedImage.mImageFormat;
        if (imageFormat == null || imageFormat == ImageFormat.UNKNOWN) {
            encodedImage.mImageFormat = ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
        }
        return this.mDefaultDecoder.decode(encodedImage, i, qualityInfo, imageDecodeOptions);
    }

    public CloseableStaticBitmap decodeStaticImage(EncodedImage encodedImage, ImageDecodeOptions imageDecodeOptions) {
        CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace = this.mPlatformDecoder.decodeFromEncodedImageWithColorSpace(encodedImage, imageDecodeOptions.bitmapConfig, null, null);
        try {
            maybeApplyTransformation(null, decodeFromEncodedImageWithColorSpace);
            QualityInfo qualityInfo = ImmutableQualityInfo.FULL_QUALITY;
            encodedImage.parseMetaDataIfNeeded();
            int i = encodedImage.mRotationAngle;
            encodedImage.parseMetaDataIfNeeded();
            return new CloseableStaticBitmap(decodeFromEncodedImageWithColorSpace, qualityInfo, i, encodedImage.mExifOrientation);
        } finally {
            decodeFromEncodedImageWithColorSpace.close();
        }
    }

    public final void maybeApplyTransformation(BitmapTransformation bitmapTransformation, CloseableReference<Bitmap> closeableReference) {
    }
}
