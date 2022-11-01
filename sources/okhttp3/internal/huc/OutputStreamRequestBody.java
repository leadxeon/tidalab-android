package okhttp3.internal.huc;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import okio.Timeout;
/* loaded from: classes.dex */
public abstract class OutputStreamRequestBody extends RequestBody {
    public boolean closed;
    private long expectedContentLength;
    private OutputStream outputStream;
    private Timeout timeout;

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        return this.expectedContentLength;
    }

    @Override // okhttp3.RequestBody
    public final MediaType contentType() {
        return null;
    }

    public void initOutputStream(final BufferedSink bufferedSink, final long j) {
        this.timeout = bufferedSink.timeout();
        this.expectedContentLength = j;
        this.outputStream = new OutputStream() { // from class: okhttp3.internal.huc.OutputStreamRequestBody.1
            private long bytesReceived;

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                OutputStreamRequestBody.this.closed = true;
                long j2 = j;
                if (j2 == -1 || this.bytesReceived >= j2) {
                    bufferedSink.close();
                    return;
                }
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("expected ");
                outline13.append(j);
                outline13.append(" bytes but received ");
                outline13.append(this.bytesReceived);
                throw new ProtocolException(outline13.toString());
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                if (!OutputStreamRequestBody.this.closed) {
                    bufferedSink.flush();
                }
            }

            @Override // java.io.OutputStream
            public void write(int i) throws IOException {
                write(new byte[]{(byte) i}, 0, 1);
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                if (!OutputStreamRequestBody.this.closed) {
                    long j2 = j;
                    if (j2 == -1 || this.bytesReceived + i2 <= j2) {
                        this.bytesReceived += i2;
                        try {
                            bufferedSink.write(bArr, i, i2);
                        } catch (InterruptedIOException e) {
                            throw new SocketTimeoutException(e.getMessage());
                        }
                    } else {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13("expected ");
                        outline13.append(j);
                        outline13.append(" bytes but received ");
                        outline13.append(this.bytesReceived);
                        outline13.append(i2);
                        throw new ProtocolException(outline13.toString());
                    }
                } else {
                    throw new IOException("closed");
                }
            }
        };
    }

    public final boolean isClosed() {
        return this.closed;
    }

    public final OutputStream outputStream() {
        return this.outputStream;
    }

    public Request prepareToSendRequest(Request request) throws IOException {
        return request;
    }

    public final Timeout timeout() {
        return this.timeout;
    }
}
