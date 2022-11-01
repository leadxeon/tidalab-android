package com.facebook.imageutils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.os.Build;
import androidx.core.util.Pools$SynchronizedPool;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Objects;
import okhttp3.internal.http2.Http2;
/* loaded from: classes.dex */
public final class BitmapUtil {
    public static final Pools$SynchronizedPool<ByteBuffer> DECODE_BUFFERS = new Pools$SynchronizedPool<>(12);

    /* renamed from: com.facebook.imageutils.BitmapUtil$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$android$graphics$Bitmap$Config;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            $SwitchMap$android$graphics$Bitmap$Config = iArr;
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ALPHA_8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGB_565.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$android$graphics$Bitmap$Config[Bitmap.Config.RGBA_F16.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static ImageMetaData decodeDimensionsAndColorSpace(InputStream inputStream) {
        Objects.requireNonNull(inputStream);
        Pools$SynchronizedPool<ByteBuffer> pools$SynchronizedPool = DECODE_BUFFERS;
        ByteBuffer acquire = pools$SynchronizedPool.acquire();
        if (acquire == null) {
            acquire = ByteBuffer.allocate(Http2.INITIAL_MAX_FRAME_SIZE);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            options.inTempStorage = acquire.array();
            ColorSpace colorSpace = null;
            BitmapFactory.decodeStream(inputStream, null, options);
            if (Build.VERSION.SDK_INT >= 26) {
                colorSpace = options.outColorSpace;
            }
            ImageMetaData imageMetaData = new ImageMetaData(options.outWidth, options.outHeight, colorSpace);
            pools$SynchronizedPool.release(acquire);
            return imageMetaData;
        } catch (Throwable th) {
            DECODE_BUFFERS.release(acquire);
            throw th;
        }
    }

    public static int getPixelSizeForBitmapConfig(Bitmap.Config config) {
        int i = AnonymousClass1.$SwitchMap$android$graphics$Bitmap$Config[config.ordinal()];
        if (i == 1) {
            return 4;
        }
        if (i == 2) {
            return 1;
        }
        if (i == 3 || i == 4) {
            return 2;
        }
        if (i == 5) {
            return 8;
        }
        throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
    }

    public static int getSizeInByteForBitmap(int i, int i2, Bitmap.Config config) {
        return getPixelSizeForBitmapConfig(config) * i * i2;
    }

    @SuppressLint({"NewApi"})
    public static int getSizeInBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        try {
            return bitmap.getAllocationByteCount();
        } catch (NullPointerException unused) {
            return bitmap.getByteCount();
        }
    }
}
