package androidx.core.content.res;

import java.util.List;
/* loaded from: classes.dex */
public final class GradientColorInflaterCompat$ColorStops {
    public final int[] mColors;
    public final float[] mOffsets;

    public GradientColorInflaterCompat$ColorStops(List<Integer> list, List<Float> list2) {
        int size = list.size();
        this.mColors = new int[size];
        this.mOffsets = new float[size];
        for (int i = 0; i < size; i++) {
            this.mColors[i] = list.get(i).intValue();
            this.mOffsets[i] = list2.get(i).floatValue();
        }
    }

    public GradientColorInflaterCompat$ColorStops(int i, int i2) {
        this.mColors = new int[]{i, i2};
        this.mOffsets = new float[]{0.0f, 1.0f};
    }

    public GradientColorInflaterCompat$ColorStops(int i, int i2, int i3) {
        this.mColors = new int[]{i, i2, i3};
        this.mOffsets = new float[]{0.0f, 0.5f, 1.0f};
    }
}
