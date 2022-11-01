package com.facebook.imagepipeline.image;

import android.graphics.ColorSpace;
import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.memory.PooledByteBufferInputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.BytesRange;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public class EncodedImage implements Closeable {
    public BytesRange mBytesRange;
    public ColorSpace mColorSpace;
    public int mExifOrientation;
    public int mHeight;
    public ImageFormat mImageFormat;
    public final Supplier<FileInputStream> mInputStreamSupplier;
    public final CloseableReference<PooledByteBuffer> mPooledByteBufferRef;
    public int mRotationAngle;
    public int mSampleSize;
    public int mStreamSize;
    public int mWidth;

    public EncodedImage(CloseableReference<PooledByteBuffer> closeableReference) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mExifOrientation = 0;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        R$dimen.checkArgument(CloseableReference.isValid(closeableReference));
        this.mPooledByteBufferRef = closeableReference.clone();
        this.mInputStreamSupplier = null;
    }

    public static EncodedImage cloneOrNull(EncodedImage encodedImage) {
        EncodedImage encodedImage2 = null;
        if (encodedImage != null) {
            Supplier<FileInputStream> supplier = encodedImage.mInputStreamSupplier;
            if (supplier != null) {
                encodedImage2 = new EncodedImage(supplier, encodedImage.mStreamSize);
            } else {
                CloseableReference cloneOrNull = CloseableReference.cloneOrNull(encodedImage.mPooledByteBufferRef);
                if (cloneOrNull != null) {
                    try {
                        encodedImage2 = new EncodedImage(cloneOrNull);
                    } finally {
                        cloneOrNull.close();
                    }
                }
                if (cloneOrNull != null) {
                }
            }
            if (encodedImage2 != null) {
                encodedImage2.copyMetaDataFrom(encodedImage);
            }
        }
        return encodedImage2;
    }

    public static boolean isMetaDataAvailable(EncodedImage encodedImage) {
        return encodedImage.mRotationAngle >= 0 && encodedImage.mWidth >= 0 && encodedImage.mHeight >= 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        CloseableReference<PooledByteBuffer> closeableReference = this.mPooledByteBufferRef;
        Class<CloseableReference> cls = CloseableReference.TAG;
        if (closeableReference != null) {
            closeableReference.close();
        }
    }

    public void copyMetaDataFrom(EncodedImage encodedImage) {
        encodedImage.parseMetaDataIfNeeded();
        this.mImageFormat = encodedImage.mImageFormat;
        encodedImage.parseMetaDataIfNeeded();
        this.mWidth = encodedImage.mWidth;
        encodedImage.parseMetaDataIfNeeded();
        this.mHeight = encodedImage.mHeight;
        encodedImage.parseMetaDataIfNeeded();
        this.mRotationAngle = encodedImage.mRotationAngle;
        encodedImage.parseMetaDataIfNeeded();
        this.mExifOrientation = encodedImage.mExifOrientation;
        this.mSampleSize = encodedImage.mSampleSize;
        this.mStreamSize = encodedImage.getSize();
        this.mBytesRange = encodedImage.mBytesRange;
        encodedImage.parseMetaDataIfNeeded();
        this.mColorSpace = encodedImage.mColorSpace;
    }

    public CloseableReference<PooledByteBuffer> getByteBufferRef() {
        return CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
    }

    public String getFirstBytesAsHexString(int i) {
        CloseableReference<PooledByteBuffer> byteBufferRef = getByteBufferRef();
        if (byteBufferRef == null) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        int min = Math.min(getSize(), i);
        byte[] bArr = new byte[min];
        try {
            PooledByteBuffer pooledByteBuffer = byteBufferRef.get();
            if (pooledByteBuffer == null) {
                return HttpUrl.FRAGMENT_ENCODE_SET;
            }
            pooledByteBuffer.read(0, bArr, 0, min);
            byteBufferRef.close();
            StringBuilder sb = new StringBuilder(min * 2);
            for (int i2 = 0; i2 < min; i2++) {
                sb.append(String.format("%02X", Byte.valueOf(bArr[i2])));
            }
            return sb.toString();
        } finally {
            byteBufferRef.close();
        }
    }

    public InputStream getInputStream() {
        Supplier<FileInputStream> supplier = this.mInputStreamSupplier;
        if (supplier != null) {
            return supplier.get();
        }
        CloseableReference cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
        if (cloneOrNull == null) {
            return null;
        }
        try {
            return new PooledByteBufferInputStream((PooledByteBuffer) cloneOrNull.get());
        } finally {
            cloneOrNull.close();
        }
    }

    public int getSize() {
        CloseableReference<PooledByteBuffer> closeableReference = this.mPooledByteBufferRef;
        if (closeableReference == null || closeableReference.get() == null) {
            return this.mStreamSize;
        }
        return this.mPooledByteBufferRef.get().size();
    }

    public synchronized boolean isValid() {
        boolean z;
        if (!CloseableReference.isValid(this.mPooledByteBufferRef)) {
            if (this.mInputStreamSupplier == null) {
                z = false;
            }
        }
        z = true;
        return z;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:10|111|11|(1:13)(2:14|(1:16)(5:17|(1:19)|126|20|(1:22)(2:23|(1:25)(2:26|(5:28|112|29|32|(1:41))))))|124|37|(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a6, code lost:
        if (r1 == null) goto L_0x00b0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ac, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ad, code lost:
        r1.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0161 A[Catch: IOException -> 0x0165, TRY_LEAVE, TryCatch #5 {IOException -> 0x0165, blocks: (B:58:0x010a, B:59:0x010d, B:63:0x011a, B:76:0x0134, B:79:0x0141, B:81:0x0149, B:88:0x0161), top: B:118:0x010a }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void parseMetaData() {
        /*
            Method dump skipped, instructions count: 425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.image.EncodedImage.parseMetaData():void");
    }

    public final void parseMetaDataIfNeeded() {
        if (this.mWidth < 0 || this.mHeight < 0) {
            parseMetaData();
        }
    }

    public static boolean isValid(EncodedImage encodedImage) {
        return encodedImage != null && encodedImage.isValid();
    }

    public EncodedImage(Supplier<FileInputStream> supplier, int i) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mExifOrientation = 0;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Objects.requireNonNull(supplier);
        this.mPooledByteBufferRef = null;
        this.mInputStreamSupplier = supplier;
        this.mStreamSize = i;
    }
}
