package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
/* loaded from: classes.dex */
public final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    public static final ByteString PREFIX_CLEAN = ByteString.encodeUtf8("OkHttp cache v1\n");
    public static final ByteString PREFIX_DIRTY = ByteString.encodeUtf8("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    public final long bufferMaxSize;
    public boolean complete;
    public RandomAccessFile file;
    private final ByteString metadata;
    public int sourceCount;
    public Source upstream;
    public long upstreamPos;
    public Thread upstreamReader;
    public final Buffer upstreamBuffer = new Buffer();
    public final Buffer buffer = new Buffer();

    /* loaded from: classes.dex */
    public class RelaySource implements Source {
        private FileOperator fileOperator;
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        public RelaySource() {
            this.fileOperator = new FileOperator(Relay.this.file.getChannel());
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.fileOperator != null) {
                RandomAccessFile randomAccessFile = null;
                this.fileOperator = null;
                synchronized (Relay.this) {
                    Relay relay = Relay.this;
                    int i = relay.sourceCount - 1;
                    relay.sourceCount = i;
                    if (i == 0) {
                        RandomAccessFile randomAccessFile2 = relay.file;
                        relay.file = null;
                        randomAccessFile = randomAccessFile2;
                    }
                }
                if (randomAccessFile != null) {
                    Util.closeQuietly(randomAccessFile);
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:23:0x003f, code lost:
            if (r0 != 2) goto L_0x005a;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0041, code lost:
            r2 = java.lang.Math.min(r23, r7 - r21.sourcePos);
            r21.fileOperator.read(r21.sourcePos + okhttp3.internal.cache2.Relay.FILE_HEADER_SIZE, r22, r2);
            r21.sourcePos += r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0059, code lost:
            return r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x005b, code lost:
            r0 = r21.this$0;
            r12 = r0.upstream.read(r0.upstreamBuffer, r0.bufferMaxSize);
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0069, code lost:
            if (r12 != (-1)) goto L_0x007f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x006b, code lost:
            r21.this$0.commit(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0070, code lost:
            r2 = r21.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0072, code lost:
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0073, code lost:
            r0 = r21.this$0;
            r0.upstreamReader = null;
            r0.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x007a, code lost:
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x007b, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x007f, code lost:
            r2 = java.lang.Math.min(r12, r23);
            r21.this$0.upstreamBuffer.copyTo(r22, 0, r2);
            r21.sourcePos += r2;
            r21.fileOperator.write(r7 + okhttp3.internal.cache2.Relay.FILE_HEADER_SIZE, r21.this$0.upstreamBuffer.clone(), r12);
            r4 = r21.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00a8, code lost:
            monitor-enter(r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00a9, code lost:
            r0 = r21.this$0;
            r0.buffer.write(r0.upstreamBuffer, r12);
            r0 = r21.this$0;
            r5 = r0.buffer;
            r7 = r5.size;
            r9 = r0.bufferMaxSize;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00bc, code lost:
            if (r7 <= r9) goto L_0x00c2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00be, code lost:
            r5.skip(r7 - r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00c2, code lost:
            r5 = r21.this$0;
            r5.upstreamPos += r12;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00c9, code lost:
            monitor-exit(r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00ca, code lost:
            monitor-enter(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00cb, code lost:
            r0 = r21.this$0;
            r0.upstreamReader = null;
            r0.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00d2, code lost:
            monitor-exit(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00d3, code lost:
            return r2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00da, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00dd, code lost:
            monitor-enter(r21.this$0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00de, code lost:
            r3 = r21.this$0;
            r3.upstreamReader = null;
            r3.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00e6, code lost:
            throw r0;
         */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long read(okio.Buffer r22, long r23) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 272
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }
    }

    private Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2) {
        this.file = randomAccessFile;
        this.upstream = source;
        this.complete = source == null;
        this.upstreamPos = j;
        this.metadata = byteString;
        this.bufferMaxSize = j2;
    }

    public static Relay edit(File file, Source source, ByteString byteString, long j) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Relay relay = new Relay(randomAccessFile, source, 0L, byteString, j);
        randomAccessFile.setLength(0L);
        relay.writeHeader(PREFIX_DIRTY, -1L, -1L);
        return relay;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
        Buffer buffer = new Buffer();
        fileOperator.read(0L, buffer, FILE_HEADER_SIZE);
        ByteString byteString = PREFIX_CLEAN;
        if (buffer.readByteString(byteString.size()).equals(byteString)) {
            long readLong = buffer.readLong();
            long readLong2 = buffer.readLong();
            Buffer buffer2 = new Buffer();
            fileOperator.read(readLong + FILE_HEADER_SIZE, buffer2, readLong2);
            return new Relay(randomAccessFile, null, readLong, buffer2.readByteString(), 0L);
        }
        throw new IOException("unreadable cache file");
    }

    private void writeHeader(ByteString byteString, long j, long j2) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(byteString);
        buffer.writeLong(j);
        buffer.writeLong(j2);
        if (buffer.size == FILE_HEADER_SIZE) {
            new FileOperator(this.file.getChannel()).write(0L, buffer, FILE_HEADER_SIZE);
            return;
        }
        throw new IllegalArgumentException();
    }

    private void writeMetadata(long j) throws IOException {
        Buffer buffer = new Buffer();
        buffer.write(this.metadata);
        new FileOperator(this.file.getChannel()).write(FILE_HEADER_SIZE + j, buffer, this.metadata.size());
    }

    public void commit(long j) throws IOException {
        writeMetadata(j);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j, this.metadata.size());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    public boolean isClosed() {
        return this.file == null;
    }

    public ByteString metadata() {
        return this.metadata;
    }

    public Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }
}
