package com.facebook.imagepipeline.image;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imageutils.BitmapUtil;
import java.util.Objects;
/* loaded from: classes.dex */
public class CloseableStaticBitmap extends CloseableBitmap {
    public volatile Bitmap mBitmap;
    public CloseableReference<Bitmap> mBitmapReference;
    public final int mExifOrientation;
    public final QualityInfo mQualityInfo;
    public final int mRotationAngle;

    public CloseableStaticBitmap(Bitmap bitmap, ResourceReleaser<Bitmap> resourceReleaser, QualityInfo qualityInfo, int i) {
        this.mBitmap = bitmap;
        Bitmap bitmap2 = this.mBitmap;
        Objects.requireNonNull(resourceReleaser);
        this.mBitmapReference = CloseableReference.of(bitmap2, resourceReleaser);
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i;
        this.mExifOrientation = 0;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference<Bitmap> closeableReference;
        synchronized (this) {
            closeableReference = this.mBitmapReference;
            this.mBitmapReference = null;
            this.mBitmap = null;
        }
        if (closeableReference != null) {
            closeableReference.close();
        }
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getHeight() {
        int i;
        if (this.mRotationAngle % 180 != 0 || (i = this.mExifOrientation) == 5 || i == 7) {
            Bitmap bitmap = this.mBitmap;
            if (bitmap == null) {
                return 0;
            }
            return bitmap.getWidth();
        }
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 == null) {
            return 0;
        }
        return bitmap2.getHeight();
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public QualityInfo getQualityInfo() {
        return this.mQualityInfo;
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public int getSizeInBytes() {
        return BitmapUtil.getSizeInBytes(this.mBitmap);
    }

    @Override // com.facebook.imagepipeline.image.CloseableBitmap
    public Bitmap getUnderlyingBitmap() {
        return this.mBitmap;
    }

    @Override // com.facebook.imagepipeline.image.ImageInfo
    public int getWidth() {
        int i;
        if (this.mRotationAngle % 180 != 0 || (i = this.mExifOrientation) == 5 || i == 7) {
            Bitmap bitmap = this.mBitmap;
            if (bitmap == null) {
                return 0;
            }
            return bitmap.getHeight();
        }
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 == null) {
            return 0;
        }
        return bitmap2.getWidth();
    }

    @Override // com.facebook.imagepipeline.image.CloseableImage
    public synchronized boolean isClosed() {
        return this.mBitmapReference == null;
    }

    public CloseableStaticBitmap(CloseableReference<Bitmap> closeableReference, QualityInfo qualityInfo, int i, int i2) {
        CloseableReference<Bitmap> cloneOrNull = closeableReference.cloneOrNull();
        Objects.requireNonNull(cloneOrNull);
        this.mBitmapReference = cloneOrNull;
        this.mBitmap = cloneOrNull.get();
        this.mQualityInfo = qualityInfo;
        this.mRotationAngle = i;
        this.mExifOrientation = i2;
    }
}
