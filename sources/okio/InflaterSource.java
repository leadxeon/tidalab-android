package okio;

import java.io.IOException;
import java.util.zip.Inflater;
/* loaded from: classes.dex */
public final class InflaterSource implements Source {
    public int bufferBytesHeldByInflater;
    public boolean closed;
    public final Inflater inflater;
    public final BufferedSource source;

    public InflaterSource(BufferedSource bufferedSource, Inflater inflater) {
        this.source = bufferedSource;
        this.inflater = inflater;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.inflater.end();
            this.closed = true;
            this.source.close();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x0085, code lost:
        releaseInflatedBytes();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008c, code lost:
        if (r0.pos != r0.limit) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008e, code lost:
        r7.head = r0.pop();
        okio.SegmentPool.recycle(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0097, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:?, code lost:
        return -1;
     */
    @Override // okio.Source
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long read(okio.Buffer r7, long r8) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
            int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x00b1
            boolean r3 = r6.closed
            if (r3 != 0) goto L_0x00a9
            if (r2 != 0) goto L_0x000d
            return r0
        L_0x000d:
            java.util.zip.Inflater r0 = r6.inflater
            boolean r0 = r0.needsInput()
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0018
            goto L_0x0043
        L_0x0018:
            r6.releaseInflatedBytes()
            java.util.zip.Inflater r0 = r6.inflater
            int r0 = r0.getRemaining()
            if (r0 != 0) goto L_0x00a1
            okio.BufferedSource r0 = r6.source
            boolean r0 = r0.exhausted()
            if (r0 == 0) goto L_0x002d
            r1 = 1
            goto L_0x0043
        L_0x002d:
            okio.BufferedSource r0 = r6.source
            okio.Buffer r0 = r0.buffer()
            okio.Segment r0 = r0.head
            int r3 = r0.limit
            int r4 = r0.pos
            int r3 = r3 - r4
            r6.bufferBytesHeldByInflater = r3
            java.util.zip.Inflater r5 = r6.inflater
            byte[] r0 = r0.data
            r5.setInput(r0, r4, r3)
        L_0x0043:
            okio.Segment r0 = r7.writableSegment(r2)     // Catch: DataFormatException -> 0x009a
            int r2 = r0.limit     // Catch: DataFormatException -> 0x009a
            int r2 = 8192 - r2
            long r2 = (long) r2     // Catch: DataFormatException -> 0x009a
            long r2 = java.lang.Math.min(r8, r2)     // Catch: DataFormatException -> 0x009a
            int r3 = (int) r2     // Catch: DataFormatException -> 0x009a
            java.util.zip.Inflater r2 = r6.inflater     // Catch: DataFormatException -> 0x009a
            byte[] r4 = r0.data     // Catch: DataFormatException -> 0x009a
            int r5 = r0.limit     // Catch: DataFormatException -> 0x009a
            int r2 = r2.inflate(r4, r5, r3)     // Catch: DataFormatException -> 0x009a
            if (r2 <= 0) goto L_0x0069
            int r8 = r0.limit     // Catch: DataFormatException -> 0x009a
            int r8 = r8 + r2
            r0.limit = r8     // Catch: DataFormatException -> 0x009a
            long r8 = r7.size     // Catch: DataFormatException -> 0x009a
            long r0 = (long) r2     // Catch: DataFormatException -> 0x009a
            long r8 = r8 + r0
            r7.size = r8     // Catch: DataFormatException -> 0x009a
            return r0
        L_0x0069:
            java.util.zip.Inflater r2 = r6.inflater     // Catch: DataFormatException -> 0x009a
            boolean r2 = r2.finished()     // Catch: DataFormatException -> 0x009a
            if (r2 != 0) goto L_0x0085
            java.util.zip.Inflater r2 = r6.inflater     // Catch: DataFormatException -> 0x009a
            boolean r2 = r2.needsDictionary()     // Catch: DataFormatException -> 0x009a
            if (r2 == 0) goto L_0x007a
            goto L_0x0085
        L_0x007a:
            if (r1 != 0) goto L_0x007d
            goto L_0x000d
        L_0x007d:
            java.io.EOFException r7 = new java.io.EOFException     // Catch: DataFormatException -> 0x009a
            java.lang.String r8 = "source exhausted prematurely"
            r7.<init>(r8)     // Catch: DataFormatException -> 0x009a
            throw r7     // Catch: DataFormatException -> 0x009a
        L_0x0085:
            r6.releaseInflatedBytes()     // Catch: DataFormatException -> 0x009a
            int r8 = r0.pos     // Catch: DataFormatException -> 0x009a
            int r9 = r0.limit     // Catch: DataFormatException -> 0x009a
            if (r8 != r9) goto L_0x0097
            okio.Segment r8 = r0.pop()     // Catch: DataFormatException -> 0x009a
            r7.head = r8     // Catch: DataFormatException -> 0x009a
            okio.SegmentPool.recycle(r0)     // Catch: DataFormatException -> 0x009a
        L_0x0097:
            r7 = -1
            return r7
        L_0x009a:
            r7 = move-exception
            java.io.IOException r8 = new java.io.IOException
            r8.<init>(r7)
            throw r8
        L_0x00a1:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "?"
            r7.<init>(r8)
            throw r7
        L_0x00a9:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "closed"
            r7.<init>(r8)
            throw r7
        L_0x00b1:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "byteCount < 0: "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r8 = r0.toString()
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.InflaterSource.read(okio.Buffer, long):long");
    }

    public final void releaseInflatedBytes() throws IOException {
        int i = this.bufferBytesHeldByInflater;
        if (i != 0) {
            int remaining = i - this.inflater.getRemaining();
            this.bufferBytesHeldByInflater -= remaining;
            this.source.skip(remaining);
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }
}
