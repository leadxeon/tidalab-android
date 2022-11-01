package okhttp3.internal.http2;

import java.util.Arrays;
/* loaded from: classes.dex */
public final class Settings {
    public static final int COUNT = 10;
    public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
    public static final int ENABLE_PUSH = 2;
    public static final int HEADER_TABLE_SIZE = 1;
    public static final int INITIAL_WINDOW_SIZE = 7;
    public static final int MAX_CONCURRENT_STREAMS = 4;
    public static final int MAX_FRAME_SIZE = 5;
    public static final int MAX_HEADER_LIST_SIZE = 6;
    private int set;
    private final int[] values = new int[10];

    public void clear() {
        this.set = 0;
        Arrays.fill(this.values, 0);
    }

    public int get(int i) {
        return this.values[i];
    }

    public boolean getEnablePush(boolean z) {
        return ((this.set & 4) != 0 ? this.values[2] : z ? 1 : 0) == 1;
    }

    public int getHeaderTableSize() {
        if ((this.set & 2) != 0) {
            return this.values[1];
        }
        return -1;
    }

    public int getInitialWindowSize() {
        return (this.set & 128) != 0 ? this.values[7] : DEFAULT_INITIAL_WINDOW_SIZE;
    }

    public int getMaxConcurrentStreams(int i) {
        return (this.set & 16) != 0 ? this.values[4] : i;
    }

    public int getMaxFrameSize(int i) {
        return (this.set & 32) != 0 ? this.values[5] : i;
    }

    public int getMaxHeaderListSize(int i) {
        return (this.set & 64) != 0 ? this.values[6] : i;
    }

    public boolean isSet(int i) {
        return ((1 << i) & this.set) != 0;
    }

    public void merge(Settings settings) {
        for (int i = 0; i < 10; i++) {
            if (settings.isSet(i)) {
                set(i, settings.get(i));
            }
        }
    }

    public Settings set(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.values;
            if (i < iArr.length) {
                this.set = (1 << i) | this.set;
                iArr[i] = i2;
            }
        }
        return this;
    }

    public int size() {
        return Integer.bitCount(this.set);
    }
}
