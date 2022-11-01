package okio;

import java.io.IOException;
/* loaded from: classes.dex */
public final class Pipe {
    public final long maxBufferSize;
    public boolean sinkClosed;
    public boolean sourceClosed;
    public final Buffer buffer = new Buffer();
    public final Sink sink = new PipeSink();
    public final Source source = new PipeSource();

    /* loaded from: classes.dex */
    public final class PipeSink implements Sink {
        public final Timeout timeout = new Timeout();

        public PipeSink() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe pipe = Pipe.this;
                if (!pipe.sinkClosed) {
                    if (pipe.sourceClosed && pipe.buffer.size > 0) {
                        throw new IOException("source is closed");
                    }
                    pipe.sinkClosed = true;
                    pipe.buffer.notifyAll();
                }
            }
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe pipe = Pipe.this;
                if (pipe.sinkClosed) {
                    throw new IllegalStateException("closed");
                } else if (pipe.sourceClosed && pipe.buffer.size > 0) {
                    throw new IOException("source is closed");
                }
            }
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return this.timeout;
        }

        @Override // okio.Sink
        public void write(Buffer buffer, long j) throws IOException {
            synchronized (Pipe.this.buffer) {
                if (!Pipe.this.sinkClosed) {
                    while (j > 0) {
                        Pipe pipe = Pipe.this;
                        if (!pipe.sourceClosed) {
                            long j2 = pipe.maxBufferSize;
                            Buffer buffer2 = pipe.buffer;
                            long j3 = j2 - buffer2.size;
                            if (j3 == 0) {
                                this.timeout.waitUntilNotified(buffer2);
                            } else {
                                long min = Math.min(j3, j);
                                Pipe.this.buffer.write(buffer, min);
                                j -= min;
                                Pipe.this.buffer.notifyAll();
                            }
                        } else {
                            throw new IOException("source is closed");
                        }
                    }
                } else {
                    throw new IllegalStateException("closed");
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public final class PipeSource implements Source {
        public final Timeout timeout = new Timeout();

        public PipeSource() {
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe pipe = Pipe.this;
                pipe.sourceClosed = true;
                pipe.buffer.notifyAll();
            }
        }

        @Override // okio.Source
        public long read(Buffer buffer, long j) throws IOException {
            synchronized (Pipe.this.buffer) {
                if (!Pipe.this.sourceClosed) {
                    while (true) {
                        Pipe pipe = Pipe.this;
                        Buffer buffer2 = pipe.buffer;
                        if (buffer2.size != 0) {
                            long read = buffer2.read(buffer, j);
                            Pipe.this.buffer.notifyAll();
                            return read;
                        } else if (pipe.sinkClosed) {
                            return -1L;
                        } else {
                            this.timeout.waitUntilNotified(buffer2);
                        }
                    }
                } else {
                    throw new IllegalStateException("closed");
                }
            }
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    public Pipe(long j) {
        if (j >= 1) {
            this.maxBufferSize = j;
            return;
        }
        throw new IllegalArgumentException("maxBufferSize < 1: " + j);
    }
}
