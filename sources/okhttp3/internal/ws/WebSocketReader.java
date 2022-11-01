package okhttp3.internal.ws;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okhttp3.HttpUrl;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
/* loaded from: classes.dex */
public final class WebSocketReader {
    public boolean closed;
    public final FrameCallback frameCallback;
    public long frameLength;
    public final boolean isClient;
    public boolean isControlFrame;
    public boolean isFinalFrame;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    public int opcode;
    public final BufferedSource source;
    private final Buffer controlFrameBuffer = new Buffer();
    private final Buffer messageFrameBuffer = new Buffer();

    /* loaded from: classes.dex */
    public interface FrameCallback {
        void onReadClose(int i, String str);

        void onReadMessage(String str) throws IOException;

        void onReadMessage(ByteString byteString) throws IOException;

        void onReadPing(ByteString byteString);

        void onReadPong(ByteString byteString);
    }

    public WebSocketReader(boolean z, BufferedSource bufferedSource, FrameCallback frameCallback) {
        Objects.requireNonNull(bufferedSource, "source == null");
        Objects.requireNonNull(frameCallback, "frameCallback == null");
        this.isClient = z;
        this.source = bufferedSource;
        this.frameCallback = frameCallback;
        Buffer.UnsafeCursor unsafeCursor = null;
        this.maskKey = z ? null : new byte[4];
        this.maskCursor = !z ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    private void readControlFrame() throws IOException {
        String str;
        long j = this.frameLength;
        if (j > 0) {
            this.source.readFully(this.controlFrameBuffer, j);
            if (!this.isClient) {
                this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                this.maskCursor.seek(0L);
                WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                this.maskCursor.close();
            }
        }
        switch (this.opcode) {
            case 8:
                short s = 1005;
                Buffer buffer = this.controlFrameBuffer;
                long j2 = buffer.size;
                if (j2 != 1) {
                    if (j2 != 0) {
                        s = buffer.readShort();
                        str = this.controlFrameBuffer.readUtf8();
                        String closeCodeExceptionMessage = WebSocketProtocol.closeCodeExceptionMessage(s);
                        if (closeCodeExceptionMessage != null) {
                            throw new ProtocolException(closeCodeExceptionMessage);
                        }
                    } else {
                        str = HttpUrl.FRAGMENT_ENCODE_SET;
                    }
                    this.frameCallback.onReadClose(s, str);
                    this.closed = true;
                    return;
                }
                throw new ProtocolException("Malformed close payload length of 1.");
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
                return;
            case 10:
                this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
                return;
            default:
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown control opcode: ");
                outline13.append(Integer.toHexString(this.opcode));
                throw new ProtocolException(outline13.toString());
        }
    }

    /* JADX WARN: Finally extract failed */
    private void readHeader() throws IOException {
        if (!this.closed) {
            long timeoutNanos = this.source.timeout().timeoutNanos();
            this.source.timeout().clearTimeout();
            try {
                int readByte = this.source.readByte() & 255;
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                this.opcode = readByte & 15;
                boolean z = true;
                boolean z2 = (readByte & 128) != 0;
                this.isFinalFrame = z2;
                boolean z3 = (readByte & 8) != 0;
                this.isControlFrame = z3;
                if (!z3 || z2) {
                    boolean z4 = (readByte & 64) != 0;
                    boolean z5 = (readByte & 32) != 0;
                    boolean z6 = (readByte & 16) != 0;
                    if (z4 || z5 || z6) {
                        throw new ProtocolException("Reserved flags are unsupported.");
                    }
                    int readByte2 = this.source.readByte() & 255;
                    if ((readByte2 & 128) == 0) {
                        z = false;
                    }
                    if (z == this.isClient) {
                        throw new ProtocolException(this.isClient ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
                    }
                    long j = readByte2 & 127;
                    this.frameLength = j;
                    if (j == 126) {
                        this.frameLength = this.source.readShort() & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                    } else if (j == 127) {
                        long readLong = this.source.readLong();
                        this.frameLength = readLong;
                        if (readLong < 0) {
                            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Frame length 0x");
                            outline13.append(Long.toHexString(this.frameLength));
                            outline13.append(" > 0x7FFFFFFFFFFFFFFF");
                            throw new ProtocolException(outline13.toString());
                        }
                    }
                    if (this.isControlFrame && this.frameLength > 125) {
                        throw new ProtocolException("Control frame must be less than 125B.");
                    } else if (z) {
                        this.source.readFully(this.maskKey);
                    }
                } else {
                    throw new ProtocolException("Control frames must be final.");
                }
            } catch (Throwable th) {
                this.source.timeout().timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                throw th;
            }
        } else {
            throw new IOException("closed");
        }
    }

    private void readMessage() throws IOException {
        while (!this.closed) {
            long j = this.frameLength;
            if (j > 0) {
                this.source.readFully(this.messageFrameBuffer, j);
                if (!this.isClient) {
                    this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(this.messageFrameBuffer.size - this.frameLength);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            }
            if (!this.isFinalFrame) {
                readUntilNonControlFrame();
                if (this.opcode != 0) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Expected continuation opcode. Got: ");
                    outline13.append(Integer.toHexString(this.opcode));
                    throw new ProtocolException(outline13.toString());
                }
            } else {
                return;
            }
        }
        throw new IOException("closed");
    }

    private void readMessageFrame() throws IOException {
        int i = this.opcode;
        if (i == 1 || i == 2) {
            readMessage();
            if (i == 1) {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.readUtf8());
            } else {
                this.frameCallback.onReadMessage(this.messageFrameBuffer.readByteString());
            }
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unknown opcode: ");
            outline13.append(Integer.toHexString(i));
            throw new ProtocolException(outline13.toString());
        }
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }

    public void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }
}
