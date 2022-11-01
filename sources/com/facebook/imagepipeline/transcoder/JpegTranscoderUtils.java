package com.facebook.imagepipeline.transcoder;

import com.facebook.common.internal.ImmutableList;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.Collections;
/* loaded from: classes.dex */
public class JpegTranscoderUtils {
    public static final ImmutableList<Integer> INVERTED_EXIF_ORIENTATIONS;

    static {
        ImmutableList<Integer> immutableList = new ImmutableList<>(4);
        Collections.addAll(immutableList, 2, 7, 4, 5);
        INVERTED_EXIF_ORIENTATIONS = immutableList;
    }

    public static int getForceRotatedInvertedExifOrientation(RotationOptions rotationOptions, EncodedImage encodedImage) {
        encodedImage.parseMetaDataIfNeeded();
        int i = encodedImage.mExifOrientation;
        ImmutableList<Integer> immutableList = INVERTED_EXIF_ORIENTATIONS;
        int indexOf = immutableList.indexOf(Integer.valueOf(i));
        if (indexOf >= 0) {
            int i2 = 0;
            if (!rotationOptions.useImageMetadata()) {
                i2 = rotationOptions.getForcedAngle();
            }
            return immutableList.get(((i2 / 90) + indexOf) % immutableList.size()).intValue();
        }
        throw new IllegalArgumentException("Only accepts inverted exif orientations");
    }

    public static int getRotationAngle(RotationOptions rotationOptions, EncodedImage encodedImage) {
        int i = 0;
        if (!rotationOptions.rotationEnabled()) {
            return 0;
        }
        encodedImage.parseMetaDataIfNeeded();
        int i2 = encodedImage.mRotationAngle;
        if (i2 == 90 || i2 == 180 || i2 == 270) {
            encodedImage.parseMetaDataIfNeeded();
            i = encodedImage.mRotationAngle;
        }
        return rotationOptions.useImageMetadata() ? i : (rotationOptions.getForcedAngle() + i) % 360;
    }

    public static int getSoftwareNumerator(RotationOptions rotationOptions, ResizeOptions resizeOptions, EncodedImage encodedImage, boolean z) {
        int i;
        int i2;
        if (!z || resizeOptions == null) {
            return 8;
        }
        int rotationAngle = getRotationAngle(rotationOptions, encodedImage);
        ImmutableList<Integer> immutableList = INVERTED_EXIF_ORIENTATIONS;
        encodedImage.parseMetaDataIfNeeded();
        boolean z2 = false;
        int forceRotatedInvertedExifOrientation = immutableList.contains(Integer.valueOf(encodedImage.mExifOrientation)) ? getForceRotatedInvertedExifOrientation(rotationOptions, encodedImage) : 0;
        if (rotationAngle == 90 || rotationAngle == 270 || forceRotatedInvertedExifOrientation == 5 || forceRotatedInvertedExifOrientation == 7) {
            z2 = true;
        }
        if (z2) {
            encodedImage.parseMetaDataIfNeeded();
            i = encodedImage.mHeight;
        } else {
            encodedImage.parseMetaDataIfNeeded();
            i = encodedImage.mWidth;
        }
        if (z2) {
            encodedImage.parseMetaDataIfNeeded();
            i2 = encodedImage.mWidth;
        } else {
            encodedImage.parseMetaDataIfNeeded();
            i2 = encodedImage.mHeight;
        }
        float f = i;
        float f2 = i2;
        float max = Math.max(resizeOptions.width / f, resizeOptions.height / f2);
        float f3 = resizeOptions.maxBitmapSize;
        if (f * max > f3) {
            max = f3 / f;
        }
        if (f2 * max > f3) {
            max = f3 / f2;
        }
        int i3 = (int) ((max * 8.0f) + resizeOptions.roundUpFraction);
        if (i3 > 8) {
            return 8;
        }
        if (i3 < 1) {
            return 1;
        }
        return i3;
    }
}
