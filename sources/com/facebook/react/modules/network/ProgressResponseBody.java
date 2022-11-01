package com.facebook.react.modules.network;

import java.io.IOException;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.RealBufferedSource;
/* loaded from: classes.dex */
public class ProgressResponseBody extends ResponseBody {
    public BufferedSource mBufferedSource;
    public final ProgressListener mProgressListener;
    public final ResponseBody mResponseBody;
    public long mTotalBytesRead = 0;

    public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
        this.mResponseBody = responseBody;
        this.mProgressListener = progressListener;
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return this.mResponseBody.contentLength();
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.mResponseBody.contentType();
    }

    @Override // okhttp3.ResponseBody
    public BufferedSource source() {
        if (this.mBufferedSource == null) {
            ForwardingSource forwardingSource = new ForwardingSource(this.mResponseBody.source()) { // from class: com.facebook.react.modules.network.ProgressResponseBody.1
                @Override // okio.ForwardingSource, okio.Source
                public long read(Buffer buffer, long j) throws IOException {
                    long read = super.read(buffer, j);
                    ProgressResponseBody progressResponseBody = ProgressResponseBody.this;
                    int i = (read > (-1L) ? 1 : (read == (-1L) ? 0 : -1));
                    long j2 = progressResponseBody.mTotalBytesRead + (i != 0 ? read : 0L);
                    progressResponseBody.mTotalBytesRead = j2;
                    progressResponseBody.mProgressListener.onProgress(j2, progressResponseBody.mResponseBody.contentLength(), i == 0);
                    return read;
                }
            };
            Logger logger = Okio.logger;
            this.mBufferedSource = new RealBufferedSource(forwardingSource);
        }
        return this.mBufferedSource;
    }
}
