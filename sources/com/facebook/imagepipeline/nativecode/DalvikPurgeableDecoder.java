package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapCounter;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.soloader.DoNotOptimize;
import com.facebook.soloader.SoLoader;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
@DoNotStrip
/* loaded from: classes.dex */
public abstract class DalvikPurgeableDecoder implements PlatformDecoder {
    public static final byte[] EOI = {-1, -39};
    public final BitmapCounter mUnpooledBitmapsCounter;

    @DoNotOptimize
    /* loaded from: classes.dex */
    public static class OreoUtils {
        private OreoUtils() {
        }
    }

    static {
        List<String> list = ImagePipelineNativeLoader.DEPENDENCIES;
        SoLoader.loadLibrary("imagepipeline");
    }

    public DalvikPurgeableDecoder() {
        if (BitmapCounterProvider.sBitmapCounter == null) {
            synchronized (BitmapCounterProvider.class) {
                if (BitmapCounterProvider.sBitmapCounter == null) {
                    BitmapCounterProvider.sBitmapCounter = new BitmapCounter(BitmapCounterProvider.sMaxBitmapCount, BitmapCounterProvider.MAX_BITMAP_TOTAL_SIZE);
                }
            }
        }
        this.mUnpooledBitmapsCounter = BitmapCounterProvider.sBitmapCounter;
    }

    @DoNotStrip
    private static native void nativePinBitmap(Bitmap bitmap);

    public abstract Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, BitmapFactory.Options options);

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, Rect rect, ColorSpace colorSpace) {
        int i = encodedImage.mSampleSize;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        options.inMutable = true;
        if (Build.VERSION.SDK_INT >= 26) {
            options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        }
        CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
        Objects.requireNonNull(byteBufferRef);
        try {
            return pinBitmap(decodeByteArrayAsPurgeable(byteBufferRef, options));
        } finally {
            byteBufferRef.close();
        }
    }

    public abstract Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> closeableReference, int i, BitmapFactory.Options options);

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, Rect rect, int i, ColorSpace colorSpace) {
        int i2 = encodedImage.mSampleSize;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i2;
        options.inMutable = true;
        if (Build.VERSION.SDK_INT >= 26) {
            options.inPreferredColorSpace = ColorSpace.get(ColorSpace.Named.SRGB);
        }
        CloseableReference<PooledByteBuffer> byteBufferRef = encodedImage.getByteBufferRef();
        Objects.requireNonNull(byteBufferRef);
        try {
            return pinBitmap(decodeJPEGByteArrayAsPurgeable(byteBufferRef, i, options));
        } finally {
            byteBufferRef.close();
        }
    }

    public CloseableReference<Bitmap> pinBitmap(Bitmap bitmap) {
        boolean z;
        int i;
        long j;
        int i2;
        Objects.requireNonNull(bitmap);
        try {
            nativePinBitmap(bitmap);
            BitmapCounter bitmapCounter = this.mUnpooledBitmapsCounter;
            synchronized (bitmapCounter) {
                int sizeInBytes = BitmapUtil.getSizeInBytes(bitmap);
                int i3 = bitmapCounter.mCount;
                if (i3 < bitmapCounter.mMaxCount) {
                    long j2 = bitmapCounter.mSize + sizeInBytes;
                    if (j2 <= bitmapCounter.mMaxSize) {
                        bitmapCounter.mCount = i3 + 1;
                        bitmapCounter.mSize = j2;
                        z = true;
                    }
                }
                z = false;
            }
            if (z) {
                return CloseableReference.of(bitmap, this.mUnpooledBitmapsCounter.mUnpooledBitmapsReleaser);
            }
            int sizeInBytes2 = BitmapUtil.getSizeInBytes(bitmap);
            bitmap.recycle();
            Locale locale = Locale.US;
            Object[] objArr = new Object[5];
            objArr[0] = Integer.valueOf(sizeInBytes2);
            BitmapCounter bitmapCounter2 = this.mUnpooledBitmapsCounter;
            synchronized (bitmapCounter2) {
                i = bitmapCounter2.mCount;
            }
            objArr[1] = Integer.valueOf(i);
            BitmapCounter bitmapCounter3 = this.mUnpooledBitmapsCounter;
            synchronized (bitmapCounter3) {
                j = bitmapCounter3.mSize;
            }
            objArr[2] = Long.valueOf(j);
            BitmapCounter bitmapCounter4 = this.mUnpooledBitmapsCounter;
            synchronized (bitmapCounter4) {
                i2 = bitmapCounter4.mMaxCount;
            }
            objArr[3] = Integer.valueOf(i2);
            objArr[4] = Integer.valueOf(this.mUnpooledBitmapsCounter.getMaxSize());
            throw new TooManyBitmapsException(String.format(locale, "Attempted to pin a bitmap of size %d bytes. The current pool count is %d, the current pool size is %d bytes. The current pool max count is %d, the current pool max size is %d bytes.", objArr));
        } catch (Exception e) {
            bitmap.recycle();
            Throwables.propagateIfPossible(e);
            throw new RuntimeException(e);
        }
    }
}
