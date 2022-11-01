package okio;
/* loaded from: classes.dex */
public final class SegmentPool {
    public static long byteCount;
    public static Segment next;

    public static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        } else if (!segment.shared) {
            synchronized (SegmentPool.class) {
                long j = byteCount + 8192;
                if (j <= 65536) {
                    byteCount = j;
                    segment.next = next;
                    segment.limit = 0;
                    segment.pos = 0;
                    next = segment;
                }
            }
        }
    }

    public static Segment take() {
        synchronized (SegmentPool.class) {
            Segment segment = next;
            if (segment == null) {
                return new Segment();
            }
            next = segment.next;
            segment.next = null;
            byteCount -= 8192;
            return segment;
        }
    }
}
