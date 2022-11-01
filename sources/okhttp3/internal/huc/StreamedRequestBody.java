package okhttp3.internal.huc;

import java.io.IOException;
import java.util.logging.Logger;
import okhttp3.internal.http.UnrepeatableRequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Pipe;
import okio.RealBufferedSink;
import okio.Sink;
/* loaded from: classes.dex */
public final class StreamedRequestBody extends OutputStreamRequestBody implements UnrepeatableRequestBody {
    private final Pipe pipe;

    public StreamedRequestBody(long j) {
        Pipe pipe = new Pipe(8192L);
        this.pipe = pipe;
        Sink sink = pipe.sink;
        Logger logger = Okio.logger;
        initOutputStream(new RealBufferedSink(sink), j);
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        Buffer buffer = new Buffer();
        while (this.pipe.source.read(buffer, 8192L) != -1) {
            bufferedSink.write(buffer, buffer.size);
        }
    }
}
