package okhttp3.internal.huc;

import java.io.IOException;
import okhttp3.Request;
import okio.Buffer;
import okio.BufferedSink;
/* loaded from: classes.dex */
public final class BufferedRequestBody extends OutputStreamRequestBody {
    public final Buffer buffer;
    public long contentLength = -1;

    public BufferedRequestBody(long j) {
        Buffer buffer = new Buffer();
        this.buffer = buffer;
        initOutputStream(buffer, j);
    }

    @Override // okhttp3.internal.huc.OutputStreamRequestBody, okhttp3.RequestBody
    public long contentLength() throws IOException {
        return this.contentLength;
    }

    @Override // okhttp3.internal.huc.OutputStreamRequestBody
    public Request prepareToSendRequest(Request request) throws IOException {
        if (request.header("Content-Length") != null) {
            return request;
        }
        outputStream().close();
        this.contentLength = this.buffer.size;
        return request.newBuilder().removeHeader("Transfer-Encoding").header("Content-Length", Long.toString(this.buffer.size)).build();
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        this.buffer.copyTo(bufferedSink.buffer(), 0L, this.buffer.size);
    }
}
