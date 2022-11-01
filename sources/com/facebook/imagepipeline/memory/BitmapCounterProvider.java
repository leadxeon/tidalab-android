package com.facebook.imagepipeline.memory;
/* loaded from: classes.dex */
public class BitmapCounterProvider {
    public static final int MAX_BITMAP_TOTAL_SIZE;
    public static volatile BitmapCounter sBitmapCounter;
    public static int sMaxBitmapCount;

    static {
        int i;
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min > 16777216) {
            i = (min / 4) * 3;
        } else {
            i = min / 2;
        }
        MAX_BITMAP_TOTAL_SIZE = i;
        sMaxBitmapCount = 384;
    }
}
