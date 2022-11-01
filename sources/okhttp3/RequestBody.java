package okhttp3;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
/* loaded from: classes.dex */
public abstract class RequestBody {
    public static RequestBody create(MediaType mediaType, String str) {
        Charset charset = Util.UTF_8;
        if (mediaType != null) {
            Charset charset2 = mediaType.charset();
            if (charset2 == null) {
                mediaType = MediaType.parse(mediaType + "; charset=utf-8");
            } else {
                charset = charset2;
            }
        }
        return create(mediaType, str.getBytes(charset));
    }

    public long contentLength() throws IOException {
        return -1L;
    }

    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public static RequestBody create(final MediaType mediaType, final ByteString byteString) {
        return new RequestBody() { // from class: okhttp3.RequestBody.1
            @Override // okhttp3.RequestBody
            public long contentLength() throws IOException {
                return byteString.size();
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(byteString);
            }
        };
    }

    public static RequestBody create(MediaType mediaType, byte[] bArr) {
        return create(mediaType, bArr, 0, bArr.length);
    }

    public static RequestBody create(final MediaType mediaType, final byte[] bArr, final int i, final int i2) {
        Objects.requireNonNull(bArr, "content == null");
        Util.checkOffsetAndCount(bArr.length, i, i2);
        return new RequestBody() { // from class: okhttp3.RequestBody.2
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return i2;
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                bufferedSink.write(bArr, i, i2);
            }
        };
    }

    public static RequestBody create(final MediaType mediaType, final File file) {
        Objects.requireNonNull(file, "file == null");
        return new RequestBody() { // from class: okhttp3.RequestBody.3
            @Override // okhttp3.RequestBody
            public long contentLength() {
                return file.length();
            }

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Closeable closeable = null;
                try {
                    File file2 = file;
                    Logger logger = Okio.logger;
                    if (file2 != null) {
                        bufferedSink.writeAll(Okio.source(new FileInputStream(file2)));
                        return;
                    }
                    throw new IllegalArgumentException("file == null");
                } finally {
                    Util.closeQuietly(closeable);
                }
            }
        };
    }
}
