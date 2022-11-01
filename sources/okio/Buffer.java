package okio;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.util.Objects;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    public static final byte[] DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    public Segment head;
    public long size;

    /* renamed from: okio.Buffer$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass2 extends InputStream {
        public AnonymousClass2() {
        }

        @Override // java.io.InputStream
        public int available() {
            return (int) Math.min(Buffer.this.size, 2147483647L);
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // java.io.InputStream
        public int read() {
            Buffer buffer = Buffer.this;
            if (buffer.size > 0) {
                return buffer.readByte() & 255;
            }
            return -1;
        }

        public String toString() {
            return Buffer.this + ".inputStream()";
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) {
            return Buffer.this.read(bArr, i, i2);
        }
    }

    /* loaded from: classes.dex */
    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public boolean readWrite;
        public Segment segment;
        public long offset = -1;
        public int start = -1;
        public int end = -1;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.buffer != null) {
                this.buffer = null;
                this.segment = null;
                this.offset = -1L;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return;
            }
            throw new IllegalStateException("not attached to a buffer");
        }

        public final int seek(long j) {
            int i = (j > (-1L) ? 1 : (j == (-1L) ? 0 : -1));
            if (i >= 0) {
                Buffer buffer = this.buffer;
                long j2 = buffer.size;
                if (j <= j2) {
                    if (i == 0 || j == j2) {
                        this.segment = null;
                        this.offset = j;
                        this.data = null;
                        this.start = -1;
                        this.end = -1;
                        return -1;
                    }
                    long j3 = 0;
                    Segment segment = buffer.head;
                    Segment segment2 = this.segment;
                    if (segment2 != null) {
                        long j4 = this.offset - (this.start - segment2.pos);
                        if (j4 > j) {
                            j2 = j4;
                            segment2 = segment;
                            segment = segment2;
                        } else {
                            j3 = j4;
                        }
                    } else {
                        segment2 = segment;
                    }
                    if (j2 - j > j - j3) {
                        while (true) {
                            int i2 = segment2.limit;
                            int i3 = segment2.pos;
                            if (j < (i2 - i3) + j3) {
                                break;
                            }
                            j3 += i2 - i3;
                            segment2 = segment2.next;
                        }
                    } else {
                        while (j2 > j) {
                            segment = segment.prev;
                            j2 -= segment.limit - segment.pos;
                        }
                        segment2 = segment;
                        j3 = j2;
                    }
                    if (this.readWrite && segment2.shared) {
                        Segment segment3 = new Segment((byte[]) segment2.data.clone(), segment2.pos, segment2.limit, false, true);
                        Buffer buffer2 = this.buffer;
                        if (buffer2.head == segment2) {
                            buffer2.head = segment3;
                        }
                        segment2.push(segment3);
                        segment3.prev.pop();
                        segment2 = segment3;
                    }
                    this.segment = segment2;
                    this.offset = j;
                    this.data = segment2.data;
                    int i4 = segment2.pos + ((int) (j - j3));
                    this.start = i4;
                    int i5 = segment2.limit;
                    this.end = i5;
                    return i5 - i4;
                }
            }
            throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j), Long.valueOf(this.buffer.size)));
        }
    }

    @Override // okio.BufferedSource, okio.BufferedSink
    public Buffer buffer() {
        return this;
    }

    public final void clear() {
        try {
            skip(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public final long completeSegmentByteCount() {
        long j = this.size;
        if (j == 0) {
            return 0L;
        }
        Segment segment = this.head.prev;
        int i = segment.limit;
        return (i >= 8192 || !segment.owner) ? j : j - (i - segment.pos);
    }

    public final Buffer copyTo(Buffer buffer, long j, long j2) {
        if (buffer != null) {
            Util.checkOffsetAndCount(this.size, j, j2);
            if (j2 == 0) {
                return this;
            }
            buffer.size += j2;
            Segment segment = this.head;
            while (true) {
                int i = segment.limit;
                int i2 = segment.pos;
                if (j >= i - i2) {
                    j -= i - i2;
                    segment = segment.next;
                }
            }
            while (j2 > 0) {
                Segment sharedCopy = segment.sharedCopy();
                int i3 = (int) (sharedCopy.pos + j);
                sharedCopy.pos = i3;
                sharedCopy.limit = Math.min(i3 + ((int) j2), sharedCopy.limit);
                Segment segment2 = buffer.head;
                if (segment2 == null) {
                    sharedCopy.prev = sharedCopy;
                    sharedCopy.next = sharedCopy;
                    buffer.head = sharedCopy;
                } else {
                    segment2.prev.push(sharedCopy);
                }
                j2 -= sharedCopy.limit - sharedCopy.pos;
                segment = segment.next;
                j = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // okio.BufferedSink
    public BufferedSink emit() {
        return this;
    }

    @Override // okio.BufferedSink
    public BufferedSink emitCompleteSegments() throws IOException {
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Buffer)) {
            return false;
        }
        Buffer buffer = (Buffer) obj;
        long j = this.size;
        if (j != buffer.size) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        Segment segment = this.head;
        Segment segment2 = buffer.head;
        int i = segment.pos;
        int i2 = segment2.pos;
        while (j2 < this.size) {
            long min = Math.min(segment.limit - i, segment2.limit - i2);
            for (int i3 = 0; i3 < min; i3++) {
                i++;
                i2++;
                if (segment.data[i] != segment2.data[i2]) {
                    return false;
                }
            }
            if (i == segment.limit) {
                segment = segment.next;
                i = segment.pos;
            }
            if (i2 == segment2.limit) {
                segment2 = segment2.next;
                i2 = segment2.pos;
            }
            j2 += min;
        }
        return true;
    }

    @Override // okio.BufferedSource
    public boolean exhausted() {
        return this.size == 0;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
    }

    public final byte getByte(long j) {
        int i;
        Util.checkOffsetAndCount(this.size, j, 1L);
        long j2 = this.size;
        if (j2 - j > j) {
            Segment segment = this.head;
            while (true) {
                int i2 = segment.limit;
                int i3 = segment.pos;
                long j3 = i2 - i3;
                if (j < j3) {
                    return segment.data[i3 + ((int) j)];
                }
                j -= j3;
                segment = segment.next;
            }
        } else {
            long j4 = j - j2;
            Segment segment2 = this.head;
            do {
                segment2 = segment2.prev;
                int i4 = segment2.limit;
                i = segment2.pos;
                j4 += i4 - i;
            } while (j4 < 0);
            return segment2.data[i + ((int) j4)];
        }
    }

    public int hashCode() {
        Segment segment = this.head;
        if (segment == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = segment.limit;
            for (int i3 = segment.pos; i3 < i2; i3++) {
                i = (i * 31) + segment.data[i3];
            }
            segment = segment.next;
        } while (segment != this.head);
        return i;
    }

    @Override // okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0L, Long.MAX_VALUE);
    }

    public long indexOfElement(ByteString byteString) {
        int i;
        int i2;
        Segment segment = this.head;
        if (segment == null) {
            return -1L;
        }
        long j = this.size;
        long j2 = 0;
        if (j - 0 >= 0) {
            j = 0;
            while (true) {
                long j3 = (segment.limit - segment.pos) + j;
                if (j3 >= 0) {
                    break;
                }
                segment = segment.next;
                j = j3;
            }
        } else {
            while (j > 0) {
                segment = segment.prev;
                j -= segment.limit - segment.pos;
            }
        }
        if (byteString.size() == 2) {
            byte b = byteString.getByte(0);
            byte b2 = byteString.getByte(1);
            while (j < this.size) {
                byte[] bArr = segment.data;
                i2 = (int) ((segment.pos + j2) - j);
                int i3 = segment.limit;
                while (i2 < i3) {
                    byte b3 = bArr[i2];
                    if (b3 == b || b3 == b2) {
                        i = segment.pos;
                    } else {
                        i2++;
                    }
                }
                j2 = (segment.limit - segment.pos) + j;
                segment = segment.next;
                j = j2;
            }
            return -1L;
        }
        byte[] internalArray = byteString.internalArray();
        while (j < this.size) {
            byte[] bArr2 = segment.data;
            i2 = (int) ((segment.pos + j2) - j);
            int i4 = segment.limit;
            while (i2 < i4) {
                byte b4 = bArr2[i2];
                for (byte b5 : internalArray) {
                    if (b4 == b5) {
                        i = segment.pos;
                    }
                }
                i2++;
            }
            j2 = (segment.limit - segment.pos) + j;
            segment = segment.next;
            j = j2;
        }
        return -1L;
        return (i2 - i) + j;
    }

    @Override // okio.BufferedSource
    public InputStream inputStream() {
        return new AnonymousClass2();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new OutputStream() { // from class: okio.Buffer.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                Buffer.this.writeByte((int) ((byte) i));
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                Buffer.this.write(bArr, i, i2);
            }
        };
    }

    @Override // okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString) {
        int size = byteString.size();
        if (j < 0 || size < 0 || this.size - j < size || byteString.size() - 0 < size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (getByte(i + j) != byteString.getByte(0 + i)) {
                return false;
            }
        }
        return true;
    }

    public int read(byte[] bArr, int i, int i2) {
        Util.checkOffsetAndCount(bArr.length, i, i2);
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(i2, segment.limit - segment.pos);
        System.arraycopy(segment.data, segment.pos, bArr, i, min);
        int i3 = segment.pos + min;
        segment.pos = i3;
        this.size -= min;
        if (i3 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    public final UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        if (unsafeCursor.buffer == null) {
            unsafeCursor.buffer = this;
            unsafeCursor.readWrite = true;
            return unsafeCursor;
        }
        throw new IllegalStateException("already attached to a buffer");
    }

    @Override // okio.BufferedSource
    public byte readByte() {
        long j = this.size;
        if (j != 0) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            int i3 = i + 1;
            byte b = segment.data[i];
            this.size = j - 1;
            if (i3 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i3;
            }
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray() {
        try {
            return readByteArray(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public ByteString readByteString() {
        return new ByteString(readByteArray());
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
        r1 = new okio.Buffer().writeDecimalLong(r3);
        r1.writeByte((int) r14);
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0049, code lost:
        if (r8 != false) goto L_0x004e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x004b, code lost:
        r1.readByte();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x004e, code lost:
        r3 = com.android.tools.r8.GeneratedOutlineSupport.outline13("Number too large: ");
        r3.append(r1.readUtf8());
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0064, code lost:
        throw new java.lang.NumberFormatException(r3.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b1, code lost:
        r17.size -= r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b7, code lost:
        if (r8 == false) goto L_0x00ba;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00bb, code lost:
        return -r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:?, code lost:
        return r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a1  */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readDecimalLong() {
        /*
            r17 = this;
            r0 = r17
            long r1 = r0.size
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x00bc
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            r5 = -7
            r7 = 0
            r8 = 0
            r9 = 0
        L_0x0014:
            okio.Segment r10 = r0.head
            byte[] r11 = r10.data
            int r12 = r10.pos
            int r13 = r10.limit
        L_0x001c:
            if (r12 >= r13) goto L_0x0095
            byte r14 = r11[r12]
            r15 = 48
            if (r14 < r15) goto L_0x0065
            r15 = 57
            if (r14 > r15) goto L_0x0065
            int r15 = 48 - r14
            int r16 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r16 < 0) goto L_0x003d
            if (r16 != 0) goto L_0x0036
            long r1 = (long) r15
            int r16 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r16 >= 0) goto L_0x0036
            goto L_0x003d
        L_0x0036:
            r1 = 10
            long r3 = r3 * r1
            long r1 = (long) r15
            long r3 = r3 + r1
            goto L_0x006f
        L_0x003d:
            okio.Buffer r1 = new okio.Buffer
            r1.<init>()
            okio.Buffer r1 = r1.writeDecimalLong(r3)
            r1.writeByte(r14)
            if (r8 != 0) goto L_0x004e
            r1.readByte()
        L_0x004e:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.String r3 = "Number too large: "
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r3)
            java.lang.String r1 = r1.readUtf8()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x0065:
            r1 = 45
            if (r14 != r1) goto L_0x0079
            if (r7 != 0) goto L_0x0079
            r1 = 1
            long r5 = r5 - r1
            r8 = 1
        L_0x006f:
            int r12 = r12 + 1
            int r7 = r7 + 1
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            goto L_0x001c
        L_0x0079:
            if (r7 == 0) goto L_0x007e
            r1 = 1
            r9 = 1
            goto L_0x0095
        L_0x007e:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = "Expected leading [0-9] or '-' character but was 0x"
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r2)
            java.lang.String r3 = java.lang.Integer.toHexString(r14)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0095:
            if (r12 != r13) goto L_0x00a1
            okio.Segment r1 = r10.pop()
            r0.head = r1
            okio.SegmentPool.recycle(r10)
            goto L_0x00a3
        L_0x00a1:
            r10.pos = r12
        L_0x00a3:
            if (r9 != 0) goto L_0x00b1
            okio.Segment r1 = r0.head
            if (r1 != 0) goto L_0x00aa
            goto L_0x00b1
        L_0x00aa:
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            goto L_0x0014
        L_0x00b1:
            long r1 = r0.size
            long r5 = (long) r7
            long r1 = r1 - r5
            r0.size = r1
            if (r8 == 0) goto L_0x00ba
            goto L_0x00bb
        L_0x00ba:
            long r3 = -r3
        L_0x00bb:
            return r3
        L_0x00bc:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "size == 0"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readDecimalLong():long");
    }

    @Override // okio.BufferedSource
    public void readFully(Buffer buffer, long j) throws EOFException {
        long j2 = this.size;
        if (j2 >= j) {
            buffer.write(this, j);
        } else {
            buffer.write(this, j2);
            throw new EOFException();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x009b A[EDGE_INSN: B:42:0x009b->B:38:0x009b ?: BREAK  , SYNTHETIC] */
    @Override // okio.BufferedSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long readHexadecimalUnsignedLong() {
        /*
            r15 = this;
            long r0 = r15.size
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x00a2
            r0 = 0
            r1 = 0
            r4 = r2
        L_0x000b:
            okio.Segment r6 = r15.head
            byte[] r7 = r6.data
            int r8 = r6.pos
            int r9 = r6.limit
        L_0x0013:
            if (r8 >= r9) goto L_0x0087
            byte r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L_0x0022
            r11 = 57
            if (r10 > r11) goto L_0x0022
            int r11 = r10 + (-48)
            goto L_0x0039
        L_0x0022:
            r11 = 97
            if (r10 < r11) goto L_0x002d
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L_0x002d
            int r11 = r10 + (-97)
            goto L_0x0037
        L_0x002d:
            r11 = 65
            if (r10 < r11) goto L_0x006c
            r11 = 70
            if (r10 > r11) goto L_0x006c
            int r11 = r10 + (-65)
        L_0x0037:
            int r11 = r11 + 10
        L_0x0039:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r14 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r14 != 0) goto L_0x0049
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L_0x0013
        L_0x0049:
            okio.Buffer r0 = new okio.Buffer
            r0.<init>()
            okio.Buffer r0 = r0.writeHexadecimalUnsignedLong(r4)
            r0.writeByte(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = "Number too large: "
            java.lang.StringBuilder r2 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r2)
            java.lang.String r0 = r0.readUtf8()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x006c:
            if (r0 == 0) goto L_0x0070
            r1 = 1
            goto L_0x0087
        L_0x0070:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = "Expected leading [0-9a-fA-F] character but was 0x"
            java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport.outline13(r1)
            java.lang.String r2 = java.lang.Integer.toHexString(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0087:
            if (r8 != r9) goto L_0x0093
            okio.Segment r7 = r6.pop()
            r15.head = r7
            okio.SegmentPool.recycle(r6)
            goto L_0x0095
        L_0x0093:
            r6.pos = r8
        L_0x0095:
            if (r1 != 0) goto L_0x009b
            okio.Segment r6 = r15.head
            if (r6 != 0) goto L_0x000b
        L_0x009b:
            long r1 = r15.size
            long r6 = (long) r0
            long r1 = r1 - r6
            r15.size = r1
            return r4
        L_0x00a2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Buffer.readHexadecimalUnsignedLong():long");
    }

    @Override // okio.BufferedSource
    public int readInt() {
        long j = this.size;
        if (j >= 4) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 4) {
                return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
            int i6 = i4 + 1;
            int i7 = i5 | ((bArr[i4] & 255) << 8);
            int i8 = i6 + 1;
            int i9 = i7 | (bArr[i6] & 255);
            this.size = j - 4;
            if (i8 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i8;
            }
            return i9;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("size < 4: ");
        outline13.append(this.size);
        throw new IllegalStateException(outline13.toString());
    }

    @Override // okio.BufferedSource
    public int readIntLe() {
        int readInt = readInt();
        Charset charset = Util.UTF_8;
        return ((readInt & 255) << 24) | (((-16777216) & readInt) >>> 24) | ((16711680 & readInt) >>> 8) | ((65280 & readInt) << 8);
    }

    @Override // okio.BufferedSource
    public long readLong() {
        long j = this.size;
        if (j >= 8) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 8) {
                return ((readInt() & 4294967295L) << 32) | (4294967295L & readInt());
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            long j2 = (bArr[i3] & 255) << 48;
            int i5 = i4 + 1;
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int i8 = i7 + 1;
            int i9 = i8 + 1;
            int i10 = i9 + 1;
            long j3 = (bArr[i9] & 255) | j2 | ((bArr[i] & 255) << 56) | ((bArr[i4] & 255) << 40) | ((bArr[i5] & 255) << 32) | ((bArr[i6] & 255) << 24) | ((bArr[i7] & 255) << 16) | ((bArr[i8] & 255) << 8);
            this.size = j - 8;
            if (i10 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i10;
            }
            return j3;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("size < 8: ");
        outline13.append(this.size);
        throw new IllegalStateException(outline13.toString());
    }

    @Override // okio.BufferedSource
    public short readShort() {
        long j = this.size;
        if (j >= 2) {
            Segment segment = this.head;
            int i = segment.pos;
            int i2 = segment.limit;
            if (i2 - i < 2) {
                return (short) (((readByte() & 255) << 8) | (readByte() & 255));
            }
            byte[] bArr = segment.data;
            int i3 = i + 1;
            int i4 = i3 + 1;
            int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
            this.size = j - 2;
            if (i4 == i2) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i4;
            }
            return (short) i5;
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("size < 2: ");
        outline13.append(this.size);
        throw new IllegalStateException(outline13.toString());
    }

    @Override // okio.BufferedSource
    public short readShortLe() {
        short readShort = readShort();
        Charset charset = Util.UTF_8;
        int i = readShort & 65535;
        return (short) (((i & 255) << 8) | ((65280 & i) >>> 8));
    }

    @Override // okio.BufferedSource
    public String readString(Charset charset) {
        try {
            return readString(this.size, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String readUtf8() {
        try {
            return readString(this.size, Util.UTF_8);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String readUtf8Line(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (getByte(j2) == 13) {
                String readUtf8 = readUtf8(j2);
                skip(2L);
                return readUtf8;
            }
        }
        String readUtf82 = readUtf8(j);
        skip(1L);
        return readUtf82;
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // okio.BufferedSource
    public boolean request(long j) {
        return this.size >= j;
    }

    @Override // okio.BufferedSource
    public void require(long j) throws EOFException {
        if (this.size < j) {
            throw new EOFException();
        }
    }

    @Override // okio.BufferedSource
    public void skip(long j) throws EOFException {
        Segment segment;
        while (j > 0) {
            if (this.head != null) {
                int min = (int) Math.min(j, segment.limit - segment.pos);
                long j2 = min;
                this.size -= j2;
                j -= j2;
                Segment segment2 = this.head;
                int i = segment2.pos + min;
                segment2.pos = i;
                if (i == segment2.limit) {
                    this.head = segment2.pop();
                    SegmentPool.recycle(segment2);
                }
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return Timeout.NONE;
    }

    public String toString() {
        ByteString byteString;
        long j = this.size;
        if (j <= 2147483647L) {
            int i = (int) j;
            if (i == 0) {
                byteString = ByteString.EMPTY;
            } else {
                byteString = new SegmentedByteString(this, i);
            }
            return byteString.toString();
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("size > Integer.MAX_VALUE: ");
        outline13.append(this.size);
        throw new IllegalArgumentException(outline13.toString());
    }

    public Segment writableSegment(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        Segment segment = this.head;
        if (segment == null) {
            Segment take = SegmentPool.take();
            this.head = take;
            take.prev = take;
            take.next = take;
            return take;
        }
        Segment segment2 = segment.prev;
        if (segment2.limit + i <= 8192 && segment2.owner) {
            return segment2;
        }
        Segment take2 = SegmentPool.take();
        segment2.push(take2);
        return take2;
    }

    @Override // okio.BufferedSink
    public long writeAll(Source source) throws IOException {
        if (source != null) {
            long j = 0;
            while (true) {
                long read = source.read(this, 8192L);
                if (read == -1) {
                    return j;
                }
                j += read;
            }
        } else {
            throw new IllegalArgumentException("source == null");
        }
    }

    public Buffer writeLong(long j) {
        Segment writableSegment = writableSegment(8);
        byte[] bArr = writableSegment.data;
        int i = writableSegment.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >>> 56) & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j >>> 48) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j >>> 40) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j >>> 32) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((j >>> 24) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((j >>> 16) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j >>> 8) & 255);
        bArr[i8] = (byte) (j & 255);
        writableSegment.limit = i8 + 1;
        this.size += 8;
        return this;
    }

    public Buffer writeString(String str, int i, int i2, Charset charset) {
        if (i < 0) {
            throw new IllegalAccessError(GeneratedOutlineSupport.outline3("beginIndex < 0: ", i));
        } else if (i2 < i) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("endIndex < beginIndex: ", i2, " < ", i));
        } else if (i2 > str.length()) {
            StringBuilder outline15 = GeneratedOutlineSupport.outline15("endIndex > string.length: ", i2, " > ");
            outline15.append(str.length());
            throw new IllegalArgumentException(outline15.toString());
        } else if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (charset.equals(Util.UTF_8)) {
            writeUtf8(str, i, i2);
            return this;
        } else {
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            write(bytes, 0, bytes.length);
            return this;
        }
    }

    public Buffer writeUtf8CodePoint(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            writeByte((i >> 6) | 192);
            writeByte((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                writeByte((i >> 12) | 224);
                writeByte(((i >> 6) & 63) | 128);
                writeByte((i & 63) | 128);
            } else {
                writeByte(63);
            }
        } else if (i <= 1114111) {
            writeByte((i >> 18) | 240);
            writeByte(((i >> 12) & 63) | 128);
            writeByte(((i >> 6) & 63) | 128);
            writeByte((i & 63) | 128);
        } else {
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("Unexpected code point: ");
            outline13.append(Integer.toHexString(i));
            throw new IllegalArgumentException(outline13.toString());
        }
        return this;
    }

    public Buffer clone() {
        Buffer buffer = new Buffer();
        if (this.size == 0) {
            return buffer;
        }
        Segment sharedCopy = this.head.sharedCopy();
        buffer.head = sharedCopy;
        sharedCopy.prev = sharedCopy;
        sharedCopy.next = sharedCopy;
        Segment segment = this.head;
        while (true) {
            segment = segment.next;
            if (segment != this.head) {
                buffer.head.prev.push(segment.sharedCopy());
            } else {
                buffer.size = this.size;
                return buffer;
            }
        }
    }

    public long indexOf(byte b, long j, long j2) {
        Segment segment;
        long j3 = 0;
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.size), Long.valueOf(j), Long.valueOf(j2)));
        }
        long j4 = this.size;
        long j5 = j2 > j4 ? j4 : j2;
        if (j == j5 || (segment = this.head) == null) {
            return -1L;
        }
        if (j4 - j < j) {
            while (j4 > j) {
                segment = segment.prev;
                j4 -= segment.limit - segment.pos;
            }
        } else {
            while (true) {
                long j6 = (segment.limit - segment.pos) + j3;
                if (j6 >= j) {
                    break;
                }
                segment = segment.next;
                j3 = j6;
            }
            j4 = j3;
        }
        long j7 = j;
        while (j4 < j5) {
            byte[] bArr = segment.data;
            int min = (int) Math.min(segment.limit, (segment.pos + j5) - j4);
            for (int i = (int) ((segment.pos + j7) - j4); i < min; i++) {
                if (bArr[i] == b) {
                    return (i - segment.pos) + j4;
                }
            }
            j4 += segment.limit - segment.pos;
            segment = segment.next;
            j7 = j4;
        }
        return -1L;
    }

    @Override // okio.BufferedSource
    public ByteString readByteString(long j) throws EOFException {
        return new ByteString(readByteArray(j));
    }

    @Override // okio.BufferedSource
    public String readUtf8LineStrict(long j) throws EOFException {
        if (j >= 0) {
            long j2 = Long.MAX_VALUE;
            if (j != Long.MAX_VALUE) {
                j2 = j + 1;
            }
            long indexOf = indexOf((byte) 10, 0L, j2);
            if (indexOf != -1) {
                return readUtf8Line(indexOf);
            }
            if (j2 < this.size && getByte(j2 - 1) == 13 && getByte(j2) == 10) {
                return readUtf8Line(j2);
            }
            Buffer buffer = new Buffer();
            copyTo(buffer, 0L, Math.min(32L, this.size));
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("\\n not found: limit=");
            outline13.append(Math.min(this.size, j));
            outline13.append(" content=");
            outline13.append(buffer.readByteString().hex());
            outline13.append((char) 8230);
            throw new EOFException(outline13.toString());
        }
        throw new IllegalArgumentException("limit < 0: " + j);
    }

    @Override // okio.BufferedSink
    public Buffer writeByte(int i) {
        Segment writableSegment = writableSegment(1);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        writableSegment.limit = i2 + 1;
        bArr[i2] = (byte) i;
        this.size++;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeDecimalLong(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i == 0) {
            writeByte(48);
            return this;
        }
        boolean z = false;
        int i2 = 1;
        if (i < 0) {
            j = -j;
            if (j < 0) {
                writeUtf8("-9223372036854775808");
                return this;
            }
            z = true;
        }
        if (j >= 100000000) {
            i2 = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i2 = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i2 = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i2 = 2;
        }
        if (z) {
            i2++;
        }
        Segment writableSegment = writableSegment(i2);
        byte[] bArr = writableSegment.data;
        int i3 = writableSegment.limit + i2;
        while (j != 0) {
            i3--;
            bArr[i3] = DIGITS[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        writableSegment.limit += i2;
        this.size += i2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeHexadecimalUnsignedLong(long j) {
        if (j == 0) {
            writeByte(48);
            return this;
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        Segment writableSegment = writableSegment(numberOfTrailingZeros);
        byte[] bArr = writableSegment.data;
        int i = writableSegment.limit;
        for (int i2 = (i + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = DIGITS[(int) (15 & j)];
            j >>>= 4;
        }
        writableSegment.limit += numberOfTrailingZeros;
        this.size += numberOfTrailingZeros;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeInt(int i) {
        Segment writableSegment = writableSegment(4);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        writableSegment.limit = i5 + 1;
        this.size += 4;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeShort(int i) {
        Segment writableSegment = writableSegment(2);
        byte[] bArr = writableSegment.data;
        int i2 = writableSegment.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        writableSegment.limit = i3 + 1;
        this.size += 2;
        return this;
    }

    @Override // okio.BufferedSink
    public Buffer writeUtf8(String str) {
        writeUtf8(str, 0, str.length());
        return this;
    }

    @Override // okio.BufferedSource
    public byte[] readByteArray(long j) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    public String readString(long j, Charset charset) throws EOFException {
        Util.checkOffsetAndCount(this.size, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        } else if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        } else if (j == 0) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        } else {
            Segment segment = this.head;
            if (segment.pos + j > segment.limit) {
                return new String(readByteArray(j), charset);
            }
            String str = new String(segment.data, segment.pos, (int) j, charset);
            int i = (int) (segment.pos + j);
            segment.pos = i;
            this.size -= j;
            if (i == segment.limit) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            return str;
        }
    }

    public String readUtf8(long j) throws EOFException {
        return readString(j, Util.UTF_8);
    }

    public Buffer writeUtf8(String str, int i, int i2) {
        char charAt;
        if (i < 0) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline3("beginIndex < 0: ", i));
        } else if (i2 < i) {
            throw new IllegalArgumentException(GeneratedOutlineSupport.outline5("endIndex < beginIndex: ", i2, " < ", i));
        } else if (i2 <= str.length()) {
            while (i < i2) {
                char charAt2 = str.charAt(i);
                if (charAt2 < 128) {
                    Segment writableSegment = writableSegment(1);
                    byte[] bArr = writableSegment.data;
                    int i3 = writableSegment.limit - i;
                    int min = Math.min(i2, 8192 - i3);
                    i++;
                    bArr[i + i3] = (byte) charAt2;
                    while (i < min && (charAt = str.charAt(i)) < 128) {
                        i++;
                        bArr[i + i3] = (byte) charAt;
                    }
                    int i4 = writableSegment.limit;
                    int i5 = (i3 + i) - i4;
                    writableSegment.limit = i4 + i5;
                    this.size += i5;
                } else {
                    if (charAt2 < 2048) {
                        writeByte((charAt2 >> 6) | 192);
                        writeByte((charAt2 & '?') | 128);
                    } else if (charAt2 < 55296 || charAt2 > 57343) {
                        writeByte((charAt2 >> '\f') | 224);
                        writeByte(((charAt2 >> 6) & 63) | 128);
                        writeByte((charAt2 & '?') | 128);
                    } else {
                        int i6 = i + 1;
                        char charAt3 = i6 < i2 ? str.charAt(i6) : (char) 0;
                        if (charAt2 > 56319 || charAt3 < 56320 || charAt3 > 57343) {
                            writeByte(63);
                            i = i6;
                        } else {
                            int i7 = (((charAt2 & 10239) << 10) | (9215 & charAt3)) + 65536;
                            writeByte((i7 >> 18) | 240);
                            writeByte(((i7 >> 12) & 63) | 128);
                            writeByte(((i7 >> 6) & 63) | 128);
                            writeByte((i7 & 63) | 128);
                            i += 2;
                        }
                    }
                    i++;
                }
            }
            return this;
        } else {
            StringBuilder outline15 = GeneratedOutlineSupport.outline15("endIndex > string.length: ", i2, " > ");
            outline15.append(str.length());
            throw new IllegalArgumentException(outline15.toString());
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(ByteString byteString) {
        if (byteString != null) {
            byteString.write(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    @Override // okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int read = read(bArr, i, bArr.length - i);
            if (read != -1) {
                i += read;
            } else {
                throw new EOFException();
            }
        }
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr) {
        if (bArr != null) {
            write(bArr, 0, bArr.length);
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.BufferedSink
    public Buffer write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = i2;
            Util.checkOffsetAndCount(bArr.length, i, j);
            int i3 = i2 + i;
            while (i < i3) {
                Segment writableSegment = writableSegment(1);
                int min = Math.min(i3 - i, 8192 - writableSegment.limit);
                System.arraycopy(bArr, i, writableSegment.data, writableSegment.limit, min);
                i += min;
                writableSegment.limit += min;
            }
            this.size += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        Segment segment = this.head;
        if (segment == null) {
            return -1;
        }
        int min = Math.min(byteBuffer.remaining(), segment.limit - segment.pos);
        byteBuffer.put(segment.data, segment.pos, min);
        int i = segment.pos + min;
        segment.pos = i;
        this.size -= min;
        if (i == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return min;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer != null) {
            int remaining = byteBuffer.remaining();
            int i = remaining;
            while (i > 0) {
                Segment writableSegment = writableSegment(1);
                int min = Math.min(i, 8192 - writableSegment.limit);
                byteBuffer.get(writableSegment.data, writableSegment.limit, min);
                i -= min;
                writableSegment.limit += min;
            }
            this.size += remaining;
            return remaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j >= 0) {
            long j2 = this.size;
            if (j2 == 0) {
                return -1L;
            }
            if (j > j2) {
                j = j2;
            }
            buffer.write(this, j);
            return j;
        } else {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) {
        Segment segment;
        if (buffer == null) {
            throw new IllegalArgumentException("source == null");
        } else if (buffer != this) {
            Util.checkOffsetAndCount(buffer.size, 0L, j);
            while (j > 0) {
                Segment segment2 = buffer.head;
                int i = 0;
                if (j < segment2.limit - segment2.pos) {
                    Segment segment3 = this.head;
                    Segment segment4 = segment3 != null ? segment3.prev : null;
                    if (segment4 != null && segment4.owner) {
                        if ((segment4.limit + j) - (segment4.shared ? 0 : segment4.pos) <= 8192) {
                            segment2.writeTo(segment4, (int) j);
                            buffer.size -= j;
                            this.size += j;
                            return;
                        }
                    }
                    int i2 = (int) j;
                    Objects.requireNonNull(segment2);
                    if (i2 <= 0 || i2 > segment2.limit - segment2.pos) {
                        throw new IllegalArgumentException();
                    }
                    if (i2 >= 1024) {
                        segment = segment2.sharedCopy();
                    } else {
                        segment = SegmentPool.take();
                        System.arraycopy(segment2.data, segment2.pos, segment.data, 0, i2);
                    }
                    segment.limit = segment.pos + i2;
                    segment2.pos += i2;
                    segment2.prev.push(segment);
                    buffer.head = segment;
                }
                Segment segment5 = buffer.head;
                long j2 = segment5.limit - segment5.pos;
                buffer.head = segment5.pop();
                Segment segment6 = this.head;
                if (segment6 == null) {
                    this.head = segment5;
                    segment5.prev = segment5;
                    segment5.next = segment5;
                } else {
                    segment6.prev.push(segment5);
                    Segment segment7 = segment5.prev;
                    if (segment7 == segment5) {
                        throw new IllegalStateException();
                    } else if (segment7.owner) {
                        int i3 = segment5.limit - segment5.pos;
                        int i4 = 8192 - segment7.limit;
                        if (!segment7.shared) {
                            i = segment7.pos;
                        }
                        if (i3 <= i4 + i) {
                            segment5.writeTo(segment7, i3);
                            segment5.pop();
                            SegmentPool.recycle(segment5);
                        }
                    }
                }
                buffer.size -= j2;
                this.size += j2;
                j -= j2;
            }
        } else {
            throw new IllegalArgumentException("source == this");
        }
    }
}
