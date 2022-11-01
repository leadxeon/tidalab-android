package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
/* loaded from: classes.dex */
public interface PlatformDecoder {
    CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, Rect rect, ColorSpace colorSpace);

    CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, Rect rect, int i, ColorSpace colorSpace);
}
