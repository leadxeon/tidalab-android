package com.facebook.imageformat;

import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Throwables;
import com.facebook.imageformat.ImageFormat;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
/* loaded from: classes.dex */
public class ImageFormatChecker {
    public static ImageFormatChecker sInstance;
    public List<ImageFormat.FormatChecker> mCustomImageFormatCheckers;
    public final ImageFormat.FormatChecker mDefaultFormatChecker = new DefaultImageFormatChecker();
    public int mMaxHeaderLength;

    public ImageFormatChecker() {
        updateMaxHeaderLength();
    }

    public static ImageFormat getImageFormat(InputStream inputStream) throws IOException {
        int read;
        ImageFormatChecker instance = getInstance();
        Objects.requireNonNull(instance);
        Objects.requireNonNull(inputStream);
        int i = instance.mMaxHeaderLength;
        byte[] bArr = new byte[i];
        R$dimen.checkArgument(i >= i);
        if (inputStream.markSupported()) {
            try {
                inputStream.mark(i);
                read = R$dimen.read(inputStream, bArr, 0, i);
            } finally {
                inputStream.reset();
            }
        } else {
            read = R$dimen.read(inputStream, bArr, 0, i);
        }
        ImageFormat determineFormat = instance.mDefaultFormatChecker.determineFormat(bArr, read);
        if (!(determineFormat == null || determineFormat == ImageFormat.UNKNOWN)) {
            return determineFormat;
        }
        List<ImageFormat.FormatChecker> list = instance.mCustomImageFormatCheckers;
        if (list != null) {
            for (ImageFormat.FormatChecker formatChecker : list) {
                ImageFormat determineFormat2 = formatChecker.determineFormat(bArr, read);
                if (!(determineFormat2 == null || determineFormat2 == ImageFormat.UNKNOWN)) {
                    return determineFormat2;
                }
            }
        }
        return ImageFormat.UNKNOWN;
    }

    public static ImageFormat getImageFormat_WrapIOException(InputStream inputStream) {
        try {
            return getImageFormat(inputStream);
        } catch (IOException e) {
            Throwables.propagateIfPossible(e);
            throw new RuntimeException(e);
        }
    }

    public static synchronized ImageFormatChecker getInstance() {
        ImageFormatChecker imageFormatChecker;
        synchronized (ImageFormatChecker.class) {
            if (sInstance == null) {
                sInstance = new ImageFormatChecker();
            }
            imageFormatChecker = sInstance;
        }
        return imageFormatChecker;
    }

    public final void updateMaxHeaderLength() {
        this.mMaxHeaderLength = this.mDefaultFormatChecker.getHeaderSize();
        List<ImageFormat.FormatChecker> list = this.mCustomImageFormatCheckers;
        if (list != null) {
            for (ImageFormat.FormatChecker formatChecker : list) {
                this.mMaxHeaderLength = Math.max(this.mMaxHeaderLength, formatChecker.getHeaderSize());
            }
        }
    }
}
