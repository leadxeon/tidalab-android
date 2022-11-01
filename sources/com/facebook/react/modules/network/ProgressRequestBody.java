package com.facebook.react.modules.network;

import java.io.FilterOutputStream;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Okio;
import okio.RealBufferedSink;
/* loaded from: classes.dex */
public class ProgressRequestBody extends RequestBody {
    public long mContentLength = 0;
    public final ProgressListener mProgressListener;
    public final RequestBody mRequestBody;

    public ProgressRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        if (this.mContentLength == 0) {
            this.mContentLength = this.mRequestBody.contentLength();
        }
        return this.mContentLength;
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.mRequestBody.contentType();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        RealBufferedSink realBufferedSink = new RealBufferedSink(Okio.sink(new CountingOutputStream(bufferedSink.outputStream()) { // from class: com.facebook.react.modules.network.ProgressRequestBody.1
            public final void sendProgressUpdate() throws IOException {
                long j = this.mCount;
                long contentLength = ProgressRequestBody.this.contentLength();
                ProgressRequestBody.this.mProgressListener.onProgress(j, contentLength, j == contentLength);
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                ((FilterOutputStream) this).out.write(bArr, i, i2);
                this.mCount += i2;
                sendProgressUpdate();
            }

            @Override // java.io.FilterOutputStream, java.io.OutputStream
            public void write(int i) throws IOException {
                ((FilterOutputStream) this).out.write(i);
                this.mCount++;
                sendProgressUpdate();
            }
        }));
        contentLength();
        this.mRequestBody.writeTo(realBufferedSink);
        realBufferedSink.flush();
    }
}
