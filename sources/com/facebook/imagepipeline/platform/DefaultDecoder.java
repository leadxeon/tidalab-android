package com.facebook.imagepipeline.platform;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Rect;
import android.os.Build;
import androidx.core.util.Pools$SynchronizedPool;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.BitmapPool;
import java.nio.ByteBuffer;
import okhttp3.internal.http2.Http2;
@TargetApi(21)
/* loaded from: classes.dex */
public abstract class DefaultDecoder implements PlatformDecoder {
    public static final byte[] EOI_TAIL = {-1, -39};
    public final BitmapPool mBitmapPool;
    public final Pools$SynchronizedPool<ByteBuffer> mDecodeBuffers;
    public final PreverificationHelper mPreverificationHelper;

    public DefaultDecoder(BitmapPool bitmapPool, int i, Pools$SynchronizedPool pools$SynchronizedPool) {
        this.mPreverificationHelper = Build.VERSION.SDK_INT >= 26 ? new PreverificationHelper() : null;
        this.mBitmapPool = bitmapPool;
        this.mDecodeBuffers = pools$SynchronizedPool;
        for (int i2 = 0; i2 < i; i2++) {
            this.mDecodeBuffers.release(ByteBuffer.allocate(Http2.INITIAL_MAX_FRAME_SIZE));
        }
    }

    public static BitmapFactory.Options getDecodeOptionsForStream(EncodedImage encodedImage, Bitmap.Config config) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = encodedImage.mSampleSize;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        options.inJustDecodeBounds = false;
        options.inDither = true;
        options.inPreferredConfig = config;
        options.inMutable = true;
        return options;
    }

    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    public CloseableReference<Bitmap> decodeFromEncodedImageWithColorSpace(EncodedImage encodedImage, Bitmap.Config config, Rect rect, ColorSpace colorSpace) {
        BitmapFactory.Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, config);
        boolean z = decodeOptionsForStream.inPreferredConfig != Bitmap.Config.ARGB_8888;
        try {
            return decodeFromStream(encodedImage.getInputStream(), decodeOptionsForStream, rect, colorSpace);
        } catch (RuntimeException e) {
            if (z) {
                return decodeFromEncodedImageWithColorSpace(encodedImage, Bitmap.Config.ARGB_8888, rect, colorSpace);
            }
            throw e;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a8 A[Catch: all -> 0x00cb, RuntimeException -> 0x00cd, IllegalArgumentException -> 0x00d6, TRY_LEAVE, TryCatch #7 {IllegalArgumentException -> 0x00d6, RuntimeException -> 0x00cd, blocks: (B:29:0x006e, B:34:0x0085, B:39:0x0099, B:43:0x00a1, B:44:0x00a4, B:47:0x00a8), top: B:74:0x006e, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00b3 A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.facebook.common.references.CloseableReference<android.graphics.Bitmap> decodeFromStream(java.io.InputStream r10, android.graphics.BitmapFactory.Options r11, android.graphics.Rect r12, android.graphics.ColorSpace r13) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.platform.DefaultDecoder.decodeFromStream(java.io.InputStream, android.graphics.BitmapFactory$Options, android.graphics.Rect, android.graphics.ColorSpace):com.facebook.common.references.CloseableReference");
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0058  */
    @Override // com.facebook.imagepipeline.platform.PlatformDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.facebook.common.references.CloseableReference<android.graphics.Bitmap> decodeJPEGFromEncodedImageWithColorSpace(com.facebook.imagepipeline.image.EncodedImage r10, android.graphics.Bitmap.Config r11, android.graphics.Rect r12, int r13, android.graphics.ColorSpace r14) {
        /*
            r9 = this;
            com.facebook.imageformat.ImageFormat r0 = r10.mImageFormat
            com.facebook.imageformat.ImageFormat r1 = com.facebook.imageformat.DefaultImageFormats.JPEG
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x0009
            goto L_0x002e
        L_0x0009:
            com.facebook.common.internal.Supplier<java.io.FileInputStream> r0 = r10.mInputStreamSupplier
            if (r0 == 0) goto L_0x000e
            goto L_0x002e
        L_0x000e:
            com.facebook.common.references.CloseableReference<com.facebook.common.memory.PooledByteBuffer> r0 = r10.mPooledByteBufferRef
            java.util.Objects.requireNonNull(r0)
            com.facebook.common.references.CloseableReference<com.facebook.common.memory.PooledByteBuffer> r0 = r10.mPooledByteBufferRef
            java.lang.Object r0 = r0.get()
            com.facebook.common.memory.PooledByteBuffer r0 = (com.facebook.common.memory.PooledByteBuffer) r0
            int r1 = r13 + (-2)
            byte r1 = r0.read(r1)
            r4 = -1
            if (r1 != r4) goto L_0x0030
            int r1 = r13 + (-1)
            byte r0 = r0.read(r1)
            r1 = -39
            if (r0 != r1) goto L_0x0030
        L_0x002e:
            r0 = 1
            goto L_0x0031
        L_0x0030:
            r0 = 0
        L_0x0031:
            android.graphics.BitmapFactory$Options r11 = getDecodeOptionsForStream(r10, r11)
            java.io.InputStream r1 = r10.getInputStream()
            java.util.Objects.requireNonNull(r1)
            int r4 = r10.getSize()
            if (r4 <= r13) goto L_0x0048
            com.facebook.common.streams.LimitedInputStream r4 = new com.facebook.common.streams.LimitedInputStream
            r4.<init>(r1, r13)
            r1 = r4
        L_0x0048:
            if (r0 != 0) goto L_0x0052
            com.facebook.common.streams.TailAppendingInputStream r0 = new com.facebook.common.streams.TailAppendingInputStream
            byte[] r4 = com.facebook.imagepipeline.platform.DefaultDecoder.EOI_TAIL
            r0.<init>(r1, r4)
            r1 = r0
        L_0x0052:
            android.graphics.Bitmap$Config r0 = r11.inPreferredConfig
            android.graphics.Bitmap$Config r4 = android.graphics.Bitmap.Config.ARGB_8888
            if (r0 == r4) goto L_0x0059
            r2 = 1
        L_0x0059:
            com.facebook.common.references.CloseableReference r10 = r9.decodeFromStream(r1, r11, r12, r14)     // Catch: RuntimeException -> 0x005e
            return r10
        L_0x005e:
            r11 = move-exception
            if (r2 == 0) goto L_0x006d
            android.graphics.Bitmap$Config r5 = android.graphics.Bitmap.Config.ARGB_8888
            r3 = r9
            r4 = r10
            r6 = r12
            r7 = r13
            r8 = r14
            com.facebook.common.references.CloseableReference r10 = r3.decodeJPEGFromEncodedImageWithColorSpace(r4, r5, r6, r7, r8)
            return r10
        L_0x006d:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.platform.DefaultDecoder.decodeJPEGFromEncodedImageWithColorSpace(com.facebook.imagepipeline.image.EncodedImage, android.graphics.Bitmap$Config, android.graphics.Rect, int, android.graphics.ColorSpace):com.facebook.common.references.CloseableReference");
    }

    public abstract int getBitmapSize(int i, int i2, BitmapFactory.Options options);
}
