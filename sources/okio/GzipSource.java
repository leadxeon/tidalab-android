package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
/* loaded from: classes.dex */
public final class GzipSource implements Source {
    public final Inflater inflater;
    public final InflaterSource inflaterSource;
    public final BufferedSource source;
    public int section = 0;
    public final CRC32 crc = new CRC32();

    public GzipSource(Source source) {
        if (source != null) {
            Inflater inflater = new Inflater(true);
            this.inflater = inflater;
            Logger logger = Okio.logger;
            RealBufferedSource realBufferedSource = new RealBufferedSource(source);
            this.source = realBufferedSource;
            this.inflaterSource = new InflaterSource(realBufferedSource, inflater);
            return;
        }
        throw new IllegalArgumentException("source == null");
    }

    public final void checkEqual(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inflaterSource.close();
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        long j2;
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (i == 0) {
            return 0L;
        } else {
            if (this.section == 0) {
                this.source.require(10L);
                byte b = this.source.buffer().getByte(3L);
                boolean z = ((b >> 1) & 1) == 1;
                if (z) {
                    updateCrc(this.source.buffer(), 0L, 10L);
                }
                checkEqual("ID1ID2", 8075, this.source.readShort());
                this.source.skip(8L);
                if (((b >> 2) & 1) == 1) {
                    this.source.require(2L);
                    if (z) {
                        updateCrc(this.source.buffer(), 0L, 2L);
                    }
                    long readShortLe = this.source.buffer().readShortLe();
                    this.source.require(readShortLe);
                    if (z) {
                        j2 = readShortLe;
                        updateCrc(this.source.buffer(), 0L, readShortLe);
                    } else {
                        j2 = readShortLe;
                    }
                    this.source.skip(j2);
                }
                if (((b >> 3) & 1) == 1) {
                    long indexOf = this.source.indexOf((byte) 0);
                    if (indexOf != -1) {
                        if (z) {
                            updateCrc(this.source.buffer(), 0L, indexOf + 1);
                        }
                        this.source.skip(indexOf + 1);
                    } else {
                        throw new EOFException();
                    }
                }
                if (((b >> 4) & 1) == 1) {
                    long indexOf2 = this.source.indexOf((byte) 0);
                    if (indexOf2 != -1) {
                        if (z) {
                            updateCrc(this.source.buffer(), 0L, indexOf2 + 1);
                        }
                        this.source.skip(indexOf2 + 1);
                    } else {
                        throw new EOFException();
                    }
                }
                if (z) {
                    checkEqual("FHCRC", this.source.readShortLe(), (short) this.crc.getValue());
                    this.crc.reset();
                }
                this.section = 1;
            }
            if (this.section == 1) {
                long j3 = buffer.size;
                long read = this.inflaterSource.read(buffer, j);
                if (read != -1) {
                    updateCrc(buffer, j3, read);
                    return read;
                }
                this.section = 2;
            }
            if (this.section == 2) {
                checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
                checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
                this.section = 3;
                if (!this.source.exhausted()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1L;
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public final void updateCrc(Buffer buffer, long j, long j2) {
        int i;
        Segment segment = buffer.head;
        while (true) {
            int i2 = segment.limit;
            int i3 = segment.pos;
            if (j >= i2 - i3) {
                j -= i2 - i3;
                segment = segment.next;
            }
        }
        while (j2 > 0) {
            int min = (int) Math.min(segment.limit - i, j2);
            this.crc.update(segment.data, (int) (segment.pos + j), min);
            j2 -= min;
            segment = segment.next;
            j = 0;
        }
    }
}
