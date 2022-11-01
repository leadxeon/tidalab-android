package okhttp3.internal.http2;

import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import okhttp3.Headers;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Header;
import okio.AsyncTimeout;
import okio.Buffer;
import okio.BufferedSource;
import okio.Sink;
import okio.Source;
import okio.Timeout;
/* loaded from: classes.dex */
public final class Http2Stream {
    public static final /* synthetic */ boolean $assertionsDisabled = false;
    public long bytesLeftInWriteWindow;
    public final Http2Connection connection;
    private boolean hasResponseHeaders;
    private Header.Listener headersListener;
    private final Deque<Headers> headersQueue;
    public final int id;
    public final FramingSink sink;
    private final FramingSource source;
    public long unacknowledgedBytesRead = 0;
    public final StreamTimeout readTimeout = new StreamTimeout();
    public final StreamTimeout writeTimeout = new StreamTimeout();
    public ErrorCode errorCode = null;

    /* loaded from: classes.dex */
    public final class FramingSink implements Sink {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long EMIT_BUFFER_SIZE = 16384;
        public boolean closed;
        public boolean finished;
        private final Buffer sendBuffer = new Buffer();

        public FramingSink() {
        }

        private void emitFrame(boolean z) throws IOException {
            Http2Stream http2Stream;
            long min;
            Http2Stream http2Stream2;
            synchronized (Http2Stream.this) {
                Http2Stream.this.writeTimeout.enter();
                while (true) {
                    http2Stream = Http2Stream.this;
                    if (http2Stream.bytesLeftInWriteWindow > 0 || this.finished || this.closed || http2Stream.errorCode != null) {
                        break;
                    }
                    http2Stream.waitForIo();
                }
                http2Stream.writeTimeout.exitAndThrowIfTimedOut();
                Http2Stream.this.checkOutNotClosed();
                min = Math.min(Http2Stream.this.bytesLeftInWriteWindow, this.sendBuffer.size);
                http2Stream2 = Http2Stream.this;
                http2Stream2.bytesLeftInWriteWindow -= min;
            }
            http2Stream2.writeTimeout.enter();
            try {
                Http2Stream http2Stream3 = Http2Stream.this;
                http2Stream3.connection.writeData(http2Stream3.id, z && min == this.sendBuffer.size, this.sendBuffer, min);
            } finally {
                Http2Stream.this.writeTimeout.exitAndThrowIfTimedOut();
            }
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Http2Stream.this) {
                if (!this.closed) {
                    Http2Stream http2Stream = Http2Stream.this;
                    if (!http2Stream.sink.finished) {
                        if (this.sendBuffer.size > 0) {
                            while (this.sendBuffer.size > 0) {
                                emitFrame(true);
                            }
                        } else {
                            http2Stream.connection.writeData(http2Stream.id, true, null, 0L);
                        }
                    }
                    synchronized (Http2Stream.this) {
                        this.closed = true;
                    }
                    Http2Stream.this.connection.flush();
                    Http2Stream.this.cancelStreamIfNecessary();
                }
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (Http2Stream.this) {
                Http2Stream.this.checkOutNotClosed();
            }
            while (this.sendBuffer.size > 0) {
                emitFrame(false);
                Http2Stream.this.connection.flush();
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return Http2Stream.this.writeTimeout;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            this.sendBuffer.write(buffer, j);
            while (this.sendBuffer.size >= EMIT_BUFFER_SIZE) {
                emitFrame(false);
            }
        }
    }

    /* loaded from: classes.dex */
    public final class FramingSource implements Source {
        public static final /* synthetic */ boolean $assertionsDisabled = false;
        public boolean closed;
        public boolean finished;
        private final long maxByteCount;
        private final Buffer receiveBuffer = new Buffer();
        private final Buffer readBuffer = new Buffer();

        public FramingSource(long j) {
            this.maxByteCount = j;
        }

        private void updateConnectionFlowControl(long j) {
            Http2Stream.this.connection.updateConnectionFlowControl(j);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            long j;
            Header.Listener listener;
            ArrayList<Headers> arrayList;
            synchronized (Http2Stream.this) {
                this.closed = true;
                Buffer buffer = this.readBuffer;
                j = buffer.size;
                buffer.clear();
                listener = null;
                if (Http2Stream.this.headersQueue.isEmpty() || Http2Stream.this.headersListener == null) {
                    arrayList = null;
                } else {
                    ArrayList arrayList2 = new ArrayList(Http2Stream.this.headersQueue);
                    Http2Stream.this.headersQueue.clear();
                    listener = Http2Stream.this.headersListener;
                    arrayList = arrayList2;
                }
                Http2Stream.this.notifyAll();
            }
            if (j > 0) {
                updateConnectionFlowControl(j);
            }
            Http2Stream.this.cancelStreamIfNecessary();
            if (listener != null) {
                for (Headers headers : arrayList) {
                    listener.onHeaders(headers);
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:49:0x00cd, code lost:
            throw new java.io.IOException("stream closed");
         */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long read(okio.Buffer r17, long r18) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 240
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http2.Http2Stream.FramingSource.read(okio.Buffer, long):long");
        }

        public void receive(BufferedSource bufferedSource, long j) throws IOException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (Http2Stream.this) {
                    z = this.finished;
                    z2 = true;
                    z3 = this.readBuffer.size + j > this.maxByteCount;
                }
                if (z3) {
                    bufferedSource.skip(j);
                    Http2Stream.this.closeLater(ErrorCode.FLOW_CONTROL_ERROR);
                    return;
                } else if (z) {
                    bufferedSource.skip(j);
                    return;
                } else {
                    long read = bufferedSource.read(this.receiveBuffer, j);
                    if (read != -1) {
                        j -= read;
                        synchronized (Http2Stream.this) {
                            Buffer buffer = this.readBuffer;
                            if (buffer.size != 0) {
                                z2 = false;
                            }
                            buffer.writeAll(this.receiveBuffer);
                            if (z2) {
                                Http2Stream.this.notifyAll();
                            }
                        }
                    } else {
                        throw new EOFException();
                    }
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return Http2Stream.this.readTimeout;
        }
    }

    /* loaded from: classes.dex */
    public class StreamTimeout extends AsyncTimeout {
        public StreamTimeout() {
        }

        public void exitAndThrowIfTimedOut() throws IOException {
            if (exit()) {
                throw newTimeoutException(null);
            }
        }

        @Override // okio.AsyncTimeout
        public IOException newTimeoutException(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // okio.AsyncTimeout
        public void timedOut() {
            Http2Stream.this.closeLater(ErrorCode.CANCEL);
        }
    }

    public Http2Stream(int i, Http2Connection http2Connection, boolean z, boolean z2, Headers headers) {
        ArrayDeque arrayDeque = new ArrayDeque();
        this.headersQueue = arrayDeque;
        Objects.requireNonNull(http2Connection, "connection == null");
        this.id = i;
        this.connection = http2Connection;
        this.bytesLeftInWriteWindow = http2Connection.peerSettings.getInitialWindowSize();
        FramingSource framingSource = new FramingSource(http2Connection.okHttpSettings.getInitialWindowSize());
        this.source = framingSource;
        FramingSink framingSink = new FramingSink();
        this.sink = framingSink;
        framingSource.finished = z2;
        framingSink.finished = z;
        if (headers != null) {
            arrayDeque.add(headers);
        }
        if (isLocallyInitiated() && headers != null) {
            throw new IllegalStateException("locally-initiated streams shouldn't have headers yet");
        } else if (!isLocallyInitiated() && headers == null) {
            throw new IllegalStateException("remotely-initiated streams should have headers");
        }
    }

    private boolean closeInternal(ErrorCode errorCode) {
        synchronized (this) {
            if (this.errorCode != null) {
                return false;
            }
            if (this.source.finished && this.sink.finished) {
                return false;
            }
            this.errorCode = errorCode;
            notifyAll();
            this.connection.removeStream(this.id);
            return true;
        }
    }

    public void addBytesToWriteWindow(long j) {
        this.bytesLeftInWriteWindow += j;
        if (j > 0) {
            notifyAll();
        }
    }

    public void cancelStreamIfNecessary() throws IOException {
        boolean z;
        boolean isOpen;
        synchronized (this) {
            FramingSource framingSource = this.source;
            if (!framingSource.finished && framingSource.closed) {
                FramingSink framingSink = this.sink;
                if (framingSink.finished || framingSink.closed) {
                    z = true;
                    isOpen = isOpen();
                }
            }
            z = false;
            isOpen = isOpen();
        }
        if (z) {
            close(ErrorCode.CANCEL);
        } else if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    public void checkOutNotClosed() throws IOException {
        FramingSink framingSink = this.sink;
        if (framingSink.closed) {
            throw new IOException("stream closed");
        } else if (framingSink.finished) {
            throw new IOException("stream finished");
        } else if (this.errorCode != null) {
            throw new StreamResetException(this.errorCode);
        }
    }

    public void close(ErrorCode errorCode) throws IOException {
        if (closeInternal(errorCode)) {
            this.connection.writeSynReset(this.id, errorCode);
        }
    }

    public void closeLater(ErrorCode errorCode) {
        if (closeInternal(errorCode)) {
            this.connection.writeSynResetLater(this.id, errorCode);
        }
    }

    public Http2Connection getConnection() {
        return this.connection;
    }

    public synchronized ErrorCode getErrorCode() {
        return this.errorCode;
    }

    public int getId() {
        return this.id;
    }

    public Sink getSink() {
        synchronized (this) {
            if (!this.hasResponseHeaders && !isLocallyInitiated()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.sink;
    }

    public Source getSource() {
        return this.source;
    }

    public boolean isLocallyInitiated() {
        return this.connection.client == ((this.id & 1) == 1);
    }

    public synchronized boolean isOpen() {
        if (this.errorCode != null) {
            return false;
        }
        FramingSource framingSource = this.source;
        if (framingSource.finished || framingSource.closed) {
            FramingSink framingSink = this.sink;
            if (framingSink.finished || framingSink.closed) {
                if (this.hasResponseHeaders) {
                    return false;
                }
            }
        }
        return true;
    }

    public Timeout readTimeout() {
        return this.readTimeout;
    }

    public void receiveData(BufferedSource bufferedSource, int i) throws IOException {
        this.source.receive(bufferedSource, i);
    }

    public void receiveFin() {
        boolean isOpen;
        synchronized (this) {
            this.source.finished = true;
            isOpen = isOpen();
            notifyAll();
        }
        if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    public void receiveHeaders(List<Header> list) {
        boolean isOpen;
        synchronized (this) {
            this.hasResponseHeaders = true;
            this.headersQueue.add(Util.toHeaders(list));
            isOpen = isOpen();
            notifyAll();
        }
        if (!isOpen) {
            this.connection.removeStream(this.id);
        }
    }

    public synchronized void receiveRstStream(ErrorCode errorCode) {
        if (this.errorCode == null) {
            this.errorCode = errorCode;
            notifyAll();
        }
    }

    public synchronized void setHeadersListener(Header.Listener listener) {
        this.headersListener = listener;
        if (!this.headersQueue.isEmpty() && listener != null) {
            notifyAll();
        }
    }

    public synchronized Headers takeHeaders() throws IOException {
        this.readTimeout.enter();
        while (this.headersQueue.isEmpty() && this.errorCode == null) {
            waitForIo();
        }
        this.readTimeout.exitAndThrowIfTimedOut();
        if (!this.headersQueue.isEmpty()) {
        } else {
            throw new StreamResetException(this.errorCode);
        }
        return this.headersQueue.removeFirst();
    }

    public void waitForIo() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public void writeHeaders(List<Header> list, boolean z) throws IOException {
        boolean z2;
        boolean z3;
        Objects.requireNonNull(list, "headers == null");
        synchronized (this) {
            z2 = true;
            this.hasResponseHeaders = true;
            if (!z) {
                this.sink.finished = true;
                z2 = true;
                z3 = true;
            } else {
                z2 = false;
                z3 = false;
            }
        }
        if (!z2) {
            synchronized (this.connection) {
                if (this.connection.bytesLeftInWriteWindow != 0) {
                    z2 = false;
                }
            }
        }
        this.connection.writeSynReply(this.id, z3, list);
        if (z2) {
            this.connection.flush();
        }
    }

    public Timeout writeTimeout() {
        return this.writeTimeout;
    }
}
