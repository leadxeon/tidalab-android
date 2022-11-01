package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
/* loaded from: classes.dex */
public interface BufferedSource extends Source, ReadableByteChannel {
    Buffer buffer();

    boolean exhausted() throws IOException;

    long indexOf(byte b) throws IOException;

    InputStream inputStream();

    boolean rangeEquals(long j, ByteString byteString) throws IOException;

    byte readByte() throws IOException;

    byte[] readByteArray() throws IOException;

    byte[] readByteArray(long j) throws IOException;

    ByteString readByteString(long j) throws IOException;

    long readDecimalLong() throws IOException;

    void readFully(Buffer buffer, long j) throws IOException;

    void readFully(byte[] bArr) throws IOException;

    long readHexadecimalUnsignedLong() throws IOException;

    int readInt() throws IOException;

    int readIntLe() throws IOException;

    long readLong() throws IOException;

    short readShort() throws IOException;

    short readShortLe() throws IOException;

    String readString(Charset charset) throws IOException;

    String readUtf8LineStrict() throws IOException;

    String readUtf8LineStrict(long j) throws IOException;

    boolean request(long j) throws IOException;

    void require(long j) throws IOException;

    void skip(long j) throws IOException;
}
