package okio;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes.dex */
public final class RealBufferedSource implements BufferedSource {
    public final Buffer buffer = new Buffer();
    public boolean closed;
    public final Source source;

    public RealBufferedSource(Source source) {
        Objects.requireNonNull(source, "source == null");
        this.source = source;
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            this.source.close();
            this.buffer.clear();
        }
    }

    @Override // okio.BufferedSource
    public boolean exhausted() throws IOException {
        if (!this.closed) {
            return this.buffer.exhausted() && this.source.read(this.buffer, 8192L) == -1;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) throws IOException {
        return indexOf(b, 0L, Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: okio.RealBufferedSource.1
            @Override // java.io.InputStream
            public int available() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.closed) {
                    return (int) Math.min(realBufferedSource.buffer.size, 2147483647L);
                }
                throw new IOException("closed");
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                RealBufferedSource.this.close();
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.closed) {
                    Buffer buffer = realBufferedSource.buffer;
                    if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192L) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.readByte() & 255;
                }
                throw new IOException("closed");
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (!RealBufferedSource.this.closed) {
                    Util.checkOffsetAndCount(bArr.length, i, i2);
                    RealBufferedSource realBufferedSource = RealBufferedSource.this;
                    Buffer buffer = realBufferedSource.buffer;
                    if (buffer.size == 0 && realBufferedSource.source.read(buffer, 8192L) == -1) {
                        return -1;
                    }
                    return RealBufferedSource.this.buffer.read(bArr, i, i2);
                }
                throw new IOException("closed");
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString) throws IOException {
        int size = byteString.size();
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || size < 0 || byteString.size() - 0 < size) {
            return false;
        } else {
            for (int i = 0; i < size; i++) {
                long j2 = i + j;
                if (!(request(1 + j2) && this.buffer.getByte(j2) == byteString.getByte(0 + i))) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (!this.closed) {
            Buffer buffer2 = this.buffer;
            if (buffer2.size == 0 && this.source.read(buffer2, 8192L) == -1) {
                return -1L;
            }
            return this.buffer.read(buffer, Math.min(j, this.buffer.size));
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // okio.BufferedSource
    public byte readByte() throws IOException {
        require(1L);
        return this.buffer.readByte();
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j) throws IOException {
        if (request(j)) {
            return this.buffer.readByteArray(j);
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j) throws IOException {
        if (request(j)) {
            return this.buffer.readByteString(j);
        }
        throw new EOFException();
    }

    @Override // okio.BufferedSource
    public long readDecimalLong() throws IOException {
        byte b;
        require(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request(i2)) {
                break;
            }
            b = this.buffer.getByte(i);
            if ((b < 48 || b > 57) && !(i == 0 && b == 45)) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(b)));
        }
        return this.buffer.readDecimalLong();
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer, long j) throws IOException {
        try {
            if (request(j)) {
                Buffer buffer2 = this.buffer;
                long j2 = buffer2.size;
                if (j2 >= j) {
                    buffer.write(buffer2, j);
                } else {
                    buffer.write(buffer2, j2);
                    throw new EOFException();
                }
            } else {
                throw new EOFException();
            }
        } catch (EOFException e) {
            buffer.writeAll(this.buffer);
            throw e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0032, code lost:
        if (r1 == 0) goto L_0x0035;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0049, code lost:
        throw new java.lang.NumberFormatException(java.lang.String.format("Expected leading [0-9a-fA-F] character but was %#x", java.lang.Byte.valueOf(r3)));
     */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readHexadecimalUnsignedLong() throws java.io.IOException {
        /*
            r6 = this;
            r0 = 1
            r6.require(r0)
            r0 = 0
            r1 = 0
        L_0x0007:
            int r2 = r1 + 1
            long r3 = (long) r2
            boolean r3 = r6.request(r3)
            if (r3 == 0) goto L_0x004a
            okio.Buffer r3 = r6.buffer
            long r4 = (long) r1
            byte r3 = r3.getByte(r4)
            r4 = 48
            if (r3 < r4) goto L_0x001f
            r4 = 57
            if (r3 <= r4) goto L_0x0030
        L_0x001f:
            r4 = 97
            if (r3 < r4) goto L_0x0027
            r4 = 102(0x66, float:1.43E-43)
            if (r3 <= r4) goto L_0x0030
        L_0x0027:
            r4 = 65
            if (r3 < r4) goto L_0x0032
            r4 = 70
            if (r3 <= r4) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            r1 = r2
            goto L_0x0007
        L_0x0032:
            if (r1 == 0) goto L_0x0035
            goto L_0x004a
        L_0x0035:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.Byte r3 = java.lang.Byte.valueOf(r3)
            r2[r0] = r3
            java.lang.String r0 = "Expected leading [0-9a-fA-F] character but was %#x"
            java.lang.String r0 = java.lang.String.format(r0, r2)
            r1.<init>(r0)
            throw r1
        L_0x004a:
            okio.Buffer r0 = r6.buffer
            long r0 = r0.readHexadecimalUnsignedLong()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.RealBufferedSource.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    public int readInt() throws IOException {
        require(4L);
        return this.buffer.readInt();
    }

    @Override // okio.BufferedSource
    public int readIntLe() throws IOException {
        require(4L);
        return this.buffer.readIntLe();
    }

    @Override // okio.BufferedSource
    public long readLong() throws IOException {
        require(8L);
        return this.buffer.readLong();
    }

    @Override // okio.BufferedSource
    public short readShort() throws IOException {
        require(2L);
        return this.buffer.readShort();
    }

    @Override // okio.BufferedSource
    public short readShortLe() throws IOException {
        require(2L);
        return this.buffer.readShortLe();
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) throws IOException {
        if (charset != null) {
            this.buffer.writeAll(this.source);
            Buffer buffer = this.buffer;
            Objects.requireNonNull(buffer);
            try {
                return buffer.readString(buffer.size, charset);
            } catch (EOFException e) {
                throw new AssertionError(e);
            }
        } else {
            throw new IllegalArgumentException("charset == null");
        }
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws IOException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public boolean request(long j) throws IOException {
        Buffer buffer;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (!this.closed) {
            do {
                buffer = this.buffer;
                if (buffer.size >= j) {
                    return true;
                }
            } while (this.source.read(buffer, 8192L) != -1);
            return false;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    @Override // okio.BufferedSource
    public void require(long j) throws IOException {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public void skip(long j) throws IOException {
        if (!this.closed) {
            while (j > 0) {
                Buffer buffer = this.buffer;
                if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j, this.buffer.size);
                this.buffer.skip(min);
                j -= min;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("buffer(");
        outline13.append(this.source);
        outline13.append(")");
        return outline13.toString();
    }

    public long indexOf(byte b, long j, long j2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        } else {
            while (j < j2) {
                long indexOf = this.buffer.indexOf(b, j, j2);
                if (indexOf == -1) {
                    Buffer buffer = this.buffer;
                    long j3 = buffer.size;
                    if (j3 >= j2 || this.source.read(buffer, 8192L) == -1) {
                        break;
                    }
                    j = Math.max(j, j3);
                } else {
                    return indexOf;
                }
            }
            return -1L;
        }
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j) throws IOException {
        if (j >= 0) {
            long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
            long indexOf = indexOf((byte) 10, 0L, j2);
            if (indexOf != -1) {
                return this.buffer.readUtf8Line(indexOf);
            }
            if (j2 < Long.MAX_VALUE && request(j2) && this.buffer.getByte(j2 - 1) == 13 && request(1 + j2) && this.buffer.getByte(j2) == 10) {
                return this.buffer.readUtf8Line(j2);
            }
            Buffer buffer = new Buffer();
            Buffer buffer2 = this.buffer;
            buffer2.copyTo(buffer, 0L, Math.min(32L, buffer2.size));
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("\\n not found: limit=");
            outline13.append(Math.min(this.buffer.size, j));
            outline13.append(" content=");
            outline13.append(buffer.readByteString().hex());
            outline13.append((char) 8230);
            throw new EOFException(outline13.toString());
        }
        throw new IllegalArgumentException("limit < 0: " + j);
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() throws IOException {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        Buffer buffer = this.buffer;
        if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
            return -1;
        }
        return this.buffer.read(byteBuffer);
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws IOException {
        try {
            require(bArr.length);
            this.buffer.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (true) {
                Buffer buffer = this.buffer;
                long j = buffer.size;
                if (j > 0) {
                    int read = buffer.read(bArr, i, (int) j);
                    if (read != -1) {
                        i += read;
                    } else {
                        throw new AssertionError();
                    }
                } else {
                    throw e;
                }
            }
        }
    }
}
