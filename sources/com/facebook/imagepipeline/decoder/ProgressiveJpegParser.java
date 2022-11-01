package com.facebook.imagepipeline.decoder;

import androidx.recyclerview.R$dimen;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.memory.PooledByteArrayBufferedInputStream;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.IOException;
import java.util.Objects;
import okhttp3.internal.http2.Http2;
/* loaded from: classes.dex */
public class ProgressiveJpegParser {
    public final ByteArrayPool mByteArrayPool;
    public boolean mEndMarkerRead;
    public int mBytesParsed = 0;
    public int mLastByteRead = 0;
    public int mNextFullScanNumber = 0;
    public int mBestScanEndOffset = 0;
    public int mBestScanNumber = 0;
    public int mParserState = 0;

    public ProgressiveJpegParser(ByteArrayPool byteArrayPool) {
        Objects.requireNonNull(byteArrayPool);
        this.mByteArrayPool = byteArrayPool;
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x0097, code lost:
        r11.mParserState = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b7, code lost:
        if (r11.mParserState == 6) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00bb, code lost:
        if (r11.mBestScanNumber == r0) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00bd, code lost:
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:?, code lost:
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:?, code lost:
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean doParseMoreData(java.io.InputStream r12) {
        /*
            Method dump skipped, instructions count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.decoder.ProgressiveJpegParser.doParseMoreData(java.io.InputStream):boolean");
    }

    public boolean parseMoreData(EncodedImage encodedImage) {
        if (this.mParserState == 6 || encodedImage.getSize() <= this.mBytesParsed) {
            return false;
        }
        PooledByteArrayBufferedInputStream pooledByteArrayBufferedInputStream = new PooledByteArrayBufferedInputStream(encodedImage.getInputStream(), this.mByteArrayPool.get(Http2.INITIAL_MAX_FRAME_SIZE), this.mByteArrayPool);
        try {
            try {
                R$dimen.skip(pooledByteArrayBufferedInputStream, this.mBytesParsed);
                return doParseMoreData(pooledByteArrayBufferedInputStream);
            } catch (IOException e) {
                Throwables.propagateIfPossible(e);
                throw new RuntimeException(e);
            }
        } finally {
            Closeables.closeQuietly(pooledByteArrayBufferedInputStream);
        }
    }
}
