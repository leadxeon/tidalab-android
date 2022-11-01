package okio;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: classes.dex */
public final class Okio {
    public static final Logger logger = Logger.getLogger(Okio.class.getName());

    /* renamed from: okio.Okio$3  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass3 implements Sink {
        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return Timeout.NONE;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            buffer.skip(j);
        }
    }

    /* renamed from: okio.Okio$4  reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 extends AsyncTimeout {
        public final /* synthetic */ Socket val$socket;

        public AnonymousClass4(Socket socket) {
            this.val$socket = socket;
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
            try {
                this.val$socket.close();
            } catch (AssertionError e) {
                if (Okio.isAndroidGetsocknameError(e)) {
                    Logger logger = Okio.logger;
                    Level level = Level.WARNING;
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Failed to close timed out socket ");
                    outline13.append(this.val$socket);
                    logger.log(level, outline13.toString(), (Throwable) e);
                    return;
                }
                throw e;
            } catch (Exception e2) {
                Logger logger2 = Okio.logger;
                Level level2 = Level.WARNING;
                StringBuilder outline132 = GeneratedOutlineSupport.outline13("Failed to close timed out socket ");
                outline132.append(this.val$socket);
                logger2.log(level2, outline132.toString(), (Throwable) e2);
            }
        }
    }

    public static Sink appendingSink(File file) throws FileNotFoundException {
        if (file != null) {
            return sink(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static Sink sink(OutputStream outputStream) {
        return sink(outputStream, new Timeout());
    }

    public static Source source(InputStream inputStream) {
        return source(inputStream, new Timeout());
    }

    public static Sink sink(final OutputStream outputStream, final Timeout timeout) {
        if (outputStream != null) {
            return new Sink() { // from class: okio.Okio.1
                @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    outputStream.close();
                }

                @Override // okio.Sink, java.io.Flushable
                public void flush() throws IOException {
                    outputStream.flush();
                }

                @Override // okio.Sink
                public Timeout timeout() {
                    return Timeout.this;
                }

                public String toString() {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("sink(");
                    outline13.append(outputStream);
                    outline13.append(")");
                    return outline13.toString();
                }

                @Override // okio.Sink
                public void write(Buffer buffer, long j) throws IOException {
                    Util.checkOffsetAndCount(buffer.size, 0L, j);
                    while (j > 0) {
                        Timeout.this.throwIfReached();
                        Segment segment = buffer.head;
                        int min = (int) Math.min(j, segment.limit - segment.pos);
                        outputStream.write(segment.data, segment.pos, min);
                        int i = segment.pos + min;
                        segment.pos = i;
                        long j2 = min;
                        j -= j2;
                        buffer.size -= j2;
                        if (i == segment.limit) {
                            buffer.head = segment.pop();
                            SegmentPool.recycle(segment);
                        }
                    }
                }
            };
        }
        throw new IllegalArgumentException("out == null");
    }

    public static Source source(final InputStream inputStream, final Timeout timeout) {
        if (inputStream != null) {
            return new Source() { // from class: okio.Okio.2
                @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
                public void close() throws IOException {
                    inputStream.close();
                }

                @Override // okio.Source
                public long read(Buffer buffer, long j) throws IOException {
                    int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                    if (i < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (i == 0) {
                        return 0L;
                    } else {
                        try {
                            Timeout.this.throwIfReached();
                            Segment writableSegment = buffer.writableSegment(1);
                            int read = inputStream.read(writableSegment.data, writableSegment.limit, (int) Math.min(j, 8192 - writableSegment.limit));
                            if (read == -1) {
                                return -1L;
                            }
                            writableSegment.limit += read;
                            long j2 = read;
                            buffer.size += j2;
                            return j2;
                        } catch (AssertionError e) {
                            if (Okio.isAndroidGetsocknameError(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                @Override // okio.Source
                public Timeout timeout() {
                    return Timeout.this;
                }

                public String toString() {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("source(");
                    outline13.append(inputStream);
                    outline13.append(")");
                    return outline13.toString();
                }
            };
        }
        throw new IllegalArgumentException("in == null");
    }

    public static Sink sink(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getOutputStream() != null) {
            AnonymousClass4 r0 = new AnonymousClass4(socket);
            return r0.sink(sink(socket.getOutputStream(), r0));
        } else {
            throw new IOException("socket's output stream == null");
        }
    }

    public static Source source(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        } else if (socket.getInputStream() != null) {
            AnonymousClass4 r0 = new AnonymousClass4(socket);
            return r0.source(source(socket.getInputStream(), r0));
        } else {
            throw new IOException("socket's input stream == null");
        }
    }
}
