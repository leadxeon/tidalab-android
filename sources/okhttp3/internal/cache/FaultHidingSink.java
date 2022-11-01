package okhttp3.internal.cache;

import java.io.IOException;
import okio.Buffer;
import okio.ForwardingSink;
import okio.Sink;
/* loaded from: classes.dex */
public class FaultHidingSink extends ForwardingSink {
    private boolean hasErrors;

    public FaultHidingSink(Sink sink) {
        super(sink);
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.hasErrors) {
            try {
                super.close();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }

    @Override // okio.ForwardingSink, okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        if (!this.hasErrors) {
            try {
                super.flush();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }

    public void onException(IOException iOException) {
    }

    @Override // okio.ForwardingSink, okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (this.hasErrors) {
            buffer.skip(j);
            return;
        }
        try {
            super.write(buffer, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }
}
