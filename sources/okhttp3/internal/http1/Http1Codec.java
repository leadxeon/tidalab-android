package okhttp3.internal.http1;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ForwardingTimeout;
import okio.Okio;
import okio.RealBufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
/* loaded from: classes.dex */
public final class Http1Codec implements HttpCodec {
    private static final int HEADER_LIMIT = 262144;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    public final OkHttpClient client;
    public final BufferedSink sink;
    public final BufferedSource source;
    public final StreamAllocation streamAllocation;
    public int state = 0;
    private long headerLimit = 262144;

    /* loaded from: classes.dex */
    public abstract class AbstractSource implements Source {
        public long bytesRead;
        public boolean closed;
        public final ForwardingTimeout timeout;

        private AbstractSource() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.source.timeout());
            this.bytesRead = 0L;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public abstract /* synthetic */ void close() throws IOException;

        public final void endOfInput(boolean z, IOException iOException) throws IOException {
            Http1Codec http1Codec = Http1Codec.this;
            int i = http1Codec.state;
            if (i != 6) {
                if (i == 5) {
                    http1Codec.detachTimeout(this.timeout);
                    Http1Codec http1Codec2 = Http1Codec.this;
                    http1Codec2.state = 6;
                    StreamAllocation streamAllocation = http1Codec2.streamAllocation;
                    if (streamAllocation != null) {
                        streamAllocation.streamFinished(!z, http1Codec2, this.bytesRead, iOException);
                        return;
                    }
                    return;
                }
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("state: ");
                outline13.append(Http1Codec.this.state);
                throw new IllegalStateException(outline13.toString());
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            try {
                long read = Http1Codec.this.source.read(buffer, j);
                if (read > 0) {
                    this.bytesRead += read;
                }
                return read;
            } catch (IOException e) {
                endOfInput(false, e);
                throw e;
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    /* loaded from: classes.dex */
    public final class ChunkedSink implements Sink {
        private boolean closed;
        private final ForwardingTimeout timeout;

        public ChunkedSink() {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public synchronized void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                Http1Codec.this.sink.writeUtf8("0\r\n\r\n");
                Http1Codec.this.detachTimeout(this.timeout);
                Http1Codec.this.state = 3;
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public synchronized void flush() throws IOException {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                Http1Codec.this.sink.writeHexadecimalUnsignedLong(j);
                Http1Codec.this.sink.writeUtf8("\r\n");
                Http1Codec.this.sink.write(buffer, j);
                Http1Codec.this.sink.writeUtf8("\r\n");
            }
        }
    }

    /* loaded from: classes.dex */
    public class ChunkedSource extends AbstractSource {
        private static final long NO_CHUNK_YET = -1;
        private long bytesRemainingInChunk = -1;
        private boolean hasMoreChunks = true;
        private final HttpUrl url;

        public ChunkedSource(HttpUrl httpUrl) {
            super();
            this.url = httpUrl;
        }

        private void readChunkSize() throws IOException {
            if (this.bytesRemainingInChunk != -1) {
                Http1Codec.this.source.readUtf8LineStrict();
            }
            try {
                this.bytesRemainingInChunk = Http1Codec.this.source.readHexadecimalUnsignedLong();
                String trim = Http1Codec.this.source.readUtf8LineStrict().trim();
                if (this.bytesRemainingInChunk < 0 || (!trim.isEmpty() && !trim.startsWith(";"))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.bytesRemainingInChunk + trim + "\"");
                } else if (this.bytesRemainingInChunk == 0) {
                    this.hasMoreChunks = false;
                    HttpHeaders.receiveHeaders(Http1Codec.this.client.cookieJar(), this.url, Http1Codec.this.readHeaders());
                    endOfInput(true, null);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                if (this.hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (!this.hasMoreChunks) {
                return -1L;
            } else {
                long j2 = this.bytesRemainingInChunk;
                if (j2 == 0 || j2 == -1) {
                    readChunkSize();
                    if (!this.hasMoreChunks) {
                        return -1L;
                    }
                }
                long read = super.read(buffer, Math.min(j, this.bytesRemainingInChunk));
                if (read != -1) {
                    this.bytesRemainingInChunk -= read;
                    return read;
                }
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                endOfInput(false, protocolException);
                throw protocolException;
            }
        }
    }

    /* loaded from: classes.dex */
    public final class FixedLengthSink implements Sink {
        private long bytesRemaining;
        private boolean closed;
        private final ForwardingTimeout timeout;

        public FixedLengthSink(long j) {
            this.timeout = new ForwardingTimeout(Http1Codec.this.sink.timeout());
            this.bytesRemaining = j;
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                if (this.bytesRemaining <= 0) {
                    Http1Codec.this.detachTimeout(this.timeout);
                    Http1Codec.this.state = 3;
                    return;
                }
                throw new ProtocolException("unexpected end of stream");
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                Http1Codec.this.sink.flush();
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            if (!this.closed) {
                Util.checkOffsetAndCount(buffer.size, 0L, j);
                if (j <= this.bytesRemaining) {
                    Http1Codec.this.sink.write(buffer, j);
                    this.bytesRemaining -= j;
                    return;
                }
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("expected ");
                outline13.append(this.bytesRemaining);
                outline13.append(" bytes but received ");
                outline13.append(j);
                throw new ProtocolException(outline13.toString());
            }
            throw new IllegalStateException("closed");
        }
    }

    /* loaded from: classes.dex */
    public class FixedLengthSource extends AbstractSource {
        private long bytesRemaining;

        public FixedLengthSource(long j) throws IOException {
            super();
            this.bytesRemaining = j;
            if (j == 0) {
                endOfInput(true, null);
            }
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                if (this.bytesRemaining != 0 && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (!this.closed) {
                long j2 = this.bytesRemaining;
                if (j2 == 0) {
                    return -1L;
                }
                long read = super.read(buffer, Math.min(j2, j));
                if (read != -1) {
                    long j3 = this.bytesRemaining - read;
                    this.bytesRemaining = j3;
                    if (j3 == 0) {
                        endOfInput(true, null);
                    }
                    return read;
                }
                ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                endOfInput(false, protocolException);
                throw protocolException;
            } else {
                throw new IllegalStateException("closed");
            }
        }
    }

    /* loaded from: classes.dex */
    public class UnknownLengthSource extends AbstractSource {
        private boolean inputExhausted;

        public UnknownLengthSource() {
            super();
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                if (!this.inputExhausted) {
                    endOfInput(false, null);
                }
                this.closed = true;
            }
        }

        @Override // okhttp3.internal.http1.Http1Codec.AbstractSource, okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else if (this.inputExhausted) {
                return -1L;
            } else {
                long read = super.read(buffer, j);
                if (read != -1) {
                    return read;
                }
                this.inputExhausted = true;
                endOfInput(true, null);
                return -1L;
            }
        }
    }

    public Http1Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation, BufferedSource bufferedSource, BufferedSink bufferedSink) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation;
        this.source = bufferedSource;
        this.sink = bufferedSink;
    }

    private String readHeaderLine() throws IOException {
        String readUtf8LineStrict = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= readUtf8LineStrict.length();
        return readUtf8LineStrict;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        RealConnection connection = this.streamAllocation.connection();
        if (connection != null) {
            connection.cancel();
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Sink createRequestBody(Request request, long j) {
        if ("chunked".equalsIgnoreCase(request.header("Transfer-Encoding"))) {
            return newChunkedSink();
        }
        if (j != -1) {
            return newFixedLengthSink(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void detachTimeout(ForwardingTimeout forwardingTimeout) {
        Timeout timeout = forwardingTimeout.delegate;
        Timeout timeout2 = Timeout.NONE;
        if (timeout2 != null) {
            forwardingTimeout.delegate = timeout2;
            timeout.clearDeadline();
            timeout.clearTimeout();
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void finishRequest() throws IOException {
        this.sink.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void flushRequest() throws IOException {
        this.sink.flush();
    }

    public boolean isClosed() {
        return this.state == 6;
    }

    public Sink newChunkedSink() {
        if (this.state == 1) {
            this.state = 2;
            return new ChunkedSink();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("state: ");
        outline13.append(this.state);
        throw new IllegalStateException(outline13.toString());
    }

    public Source newChunkedSource(HttpUrl httpUrl) throws IOException {
        if (this.state == 4) {
            this.state = 5;
            return new ChunkedSource(httpUrl);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("state: ");
        outline13.append(this.state);
        throw new IllegalStateException(outline13.toString());
    }

    public Sink newFixedLengthSink(long j) {
        if (this.state == 1) {
            this.state = 2;
            return new FixedLengthSink(j);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("state: ");
        outline13.append(this.state);
        throw new IllegalStateException(outline13.toString());
    }

    public Source newFixedLengthSource(long j) throws IOException {
        if (this.state == 4) {
            this.state = 5;
            return new FixedLengthSource(j);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("state: ");
        outline13.append(this.state);
        throw new IllegalStateException(outline13.toString());
    }

    public Source newUnknownLengthSource() throws IOException {
        if (this.state == 4) {
            StreamAllocation streamAllocation = this.streamAllocation;
            if (streamAllocation != null) {
                this.state = 5;
                streamAllocation.noNewStreams();
                return new UnknownLengthSource();
            }
            throw new IllegalStateException("streamAllocation == null");
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("state: ");
        outline13.append(this.state);
        throw new IllegalStateException(outline13.toString());
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody openResponseBody(Response response) throws IOException {
        StreamAllocation streamAllocation = this.streamAllocation;
        streamAllocation.eventListener.responseBodyStart(streamAllocation.call);
        String header = response.header("Content-Type");
        if (!HttpHeaders.hasBody(response)) {
            Source newFixedLengthSource = newFixedLengthSource(0L);
            Logger logger = Okio.logger;
            return new RealResponseBody(header, 0L, new RealBufferedSource(newFixedLengthSource));
        } else if ("chunked".equalsIgnoreCase(response.header("Transfer-Encoding"))) {
            Source newChunkedSource = newChunkedSource(response.request().url());
            Logger logger2 = Okio.logger;
            return new RealResponseBody(header, -1L, new RealBufferedSource(newChunkedSource));
        } else {
            long contentLength = HttpHeaders.contentLength(response);
            if (contentLength != -1) {
                Source newFixedLengthSource2 = newFixedLengthSource(contentLength);
                Logger logger3 = Okio.logger;
                return new RealResponseBody(header, contentLength, new RealBufferedSource(newFixedLengthSource2));
            }
            Source newUnknownLengthSource = newUnknownLengthSource();
            Logger logger4 = Okio.logger;
            return new RealResponseBody(header, -1L, new RealBufferedSource(newUnknownLengthSource));
        }
    }

    public Headers readHeaders() throws IOException {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String readHeaderLine = readHeaderLine();
            if (readHeaderLine.length() == 0) {
                return builder.build();
            }
            Internal.instance.addLenient(builder, readHeaderLine);
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder readResponseHeaders(boolean z) throws IOException {
        int i = this.state;
        if (i == 1 || i == 3) {
            try {
                StatusLine parse = StatusLine.parse(readHeaderLine());
                Response.Builder headers = new Response.Builder().protocol(parse.protocol).code(parse.code).message(parse.message).headers(readHeaders());
                if (z && parse.code == 100) {
                    return null;
                }
                if (parse.code == 100) {
                    this.state = 3;
                    return headers;
                }
                this.state = 4;
                return headers;
            } catch (EOFException e) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("unexpected end of stream on ");
                outline13.append(this.streamAllocation);
                IOException iOException = new IOException(outline13.toString());
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("state: ");
            outline132.append(this.state);
            throw new IllegalStateException(outline132.toString());
        }
    }

    public void writeRequest(Headers headers, String str) throws IOException {
        if (this.state == 0) {
            this.sink.writeUtf8(str).writeUtf8("\r\n");
            int size = headers.size();
            for (int i = 0; i < size; i++) {
                this.sink.writeUtf8(headers.name(i)).writeUtf8(": ").writeUtf8(headers.value(i)).writeUtf8("\r\n");
            }
            this.sink.writeUtf8("\r\n");
            this.state = 1;
            return;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("state: ");
        outline13.append(this.state);
        throw new IllegalStateException(outline13.toString());
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void writeRequestHeaders(Request request) throws IOException {
        writeRequest(request.headers(), RequestLine.get(request, this.streamAllocation.connection().route().proxy().type()));
    }
}
