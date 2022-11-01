package okhttp3.internal.ws;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;
/* loaded from: classes.dex */
public final class WebSocketWriter {
    public boolean activeWriter;
    public final Buffer buffer = new Buffer();
    public final FrameSink frameSink = new FrameSink();
    public final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    public final Random random;
    public final BufferedSink sink;
    public final Buffer sinkBuffer;
    public boolean writerClosed;

    /* loaded from: classes.dex */
    public final class FrameSink implements Sink {
        public boolean closed;
        public long contentLength;
        public int formatOpcode;
        public boolean isFirstFrame;

        public FrameSink() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size, this.isFirstFrame, true);
                this.closed = true;
                WebSocketWriter.this.activeWriter = false;
                return;
            }
            throw new IOException("closed");
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size, this.isFirstFrame, false);
                this.isFirstFrame = false;
                return;
            }
            throw new IOException("closed");
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            boolean z;
            long completeSegmentByteCount;
            if (!this.closed) {
                WebSocketWriter.this.buffer.write(buffer, j);
                if (this.isFirstFrame) {
                    long j2 = this.contentLength;
                    if (j2 != -1 && WebSocketWriter.this.buffer.size > j2 - 8192) {
                        z = true;
                        completeSegmentByteCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
                        if (completeSegmentByteCount > 0 && !z) {
                            WebSocketWriter.this.writeMessageFrame(this.formatOpcode, completeSegmentByteCount, this.isFirstFrame, false);
                            this.isFirstFrame = false;
                            return;
                        }
                        return;
                    }
                }
                z = false;
                completeSegmentByteCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
                if (completeSegmentByteCount > 0) {
                    return;
                }
                return;
            }
            throw new IOException("closed");
        }
    }

    public WebSocketWriter(boolean z, BufferedSink bufferedSink, Random random) {
        Objects.requireNonNull(bufferedSink, "sink == null");
        Objects.requireNonNull(random, "random == null");
        this.isClient = z;
        this.sink = bufferedSink;
        this.sinkBuffer = bufferedSink.buffer();
        this.random = random;
        Buffer.UnsafeCursor unsafeCursor = null;
        this.maskKey = z ? new byte[4] : null;
        this.maskCursor = z ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    private void writeControlFrame(int i, ByteString byteString) throws IOException {
        if (!this.writerClosed) {
            int size = byteString.size();
            if (size <= 125) {
                this.sinkBuffer.writeByte(i | 128);
                if (this.isClient) {
                    this.sinkBuffer.writeByte(size | 128);
                    this.random.nextBytes(this.maskKey);
                    this.sinkBuffer.write(this.maskKey);
                    if (size > 0) {
                        Buffer buffer = this.sinkBuffer;
                        long j = buffer.size;
                        buffer.write(byteString);
                        this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                        this.maskCursor.seek(j);
                        WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                } else {
                    this.sinkBuffer.writeByte(size);
                    this.sinkBuffer.write(byteString);
                }
                this.sink.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        throw new IOException("closed");
    }

    public Sink newMessageSink(int i, long j) {
        if (!this.activeWriter) {
            this.activeWriter = true;
            FrameSink frameSink = this.frameSink;
            frameSink.formatOpcode = i;
            frameSink.contentLength = j;
            frameSink.isFirstFrame = true;
            frameSink.closed = false;
            return frameSink;
        }
        throw new IllegalStateException("Another message writer is active. Did you call close()?");
    }

    public void writeClose(int i, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (!(i == 0 && byteString == null)) {
            if (i != 0) {
                WebSocketProtocol.validateCloseCode(i);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(i);
            if (byteString != null) {
                buffer.write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        try {
            writeControlFrame(8, byteString2);
        } finally {
            this.writerClosed = true;
        }
    }

    public void writeMessageFrame(int i, long j, boolean z, boolean z2) throws IOException {
        if (!this.writerClosed) {
            int i2 = 0;
            if (!z) {
                i = 0;
            }
            if (z2) {
                i |= 128;
            }
            this.sinkBuffer.writeByte(i);
            if (this.isClient) {
                i2 = 128;
            }
            if (j <= 125) {
                this.sinkBuffer.writeByte(((int) j) | i2);
            } else if (j <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                this.sinkBuffer.writeByte(i2 | WebSocketProtocol.PAYLOAD_SHORT);
                this.sinkBuffer.writeShort((int) j);
            } else {
                this.sinkBuffer.writeByte(i2 | 127);
                this.sinkBuffer.writeLong(j);
            }
            if (this.isClient) {
                this.random.nextBytes(this.maskKey);
                this.sinkBuffer.write(this.maskKey);
                if (j > 0) {
                    Buffer buffer = this.sinkBuffer;
                    long j2 = buffer.size;
                    buffer.write(this.buffer, j);
                    this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(j2);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            } else {
                this.sinkBuffer.write(this.buffer, j);
            }
            this.sink.emit();
            return;
        }
        throw new IOException("closed");
    }

    public void writePing(ByteString byteString) throws IOException {
        writeControlFrame(9, byteString);
    }

    public void writePong(ByteString byteString) throws IOException {
        writeControlFrame(10, byteString);
    }
}
