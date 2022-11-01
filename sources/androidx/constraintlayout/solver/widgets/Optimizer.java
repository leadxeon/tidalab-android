package androidx.constraintlayout.solver.widgets;
/* loaded from: classes.dex */
public class Optimizer {
    public static boolean[] flags = new boolean[3];

    public static final boolean enabled(int i, int i2) {
        return (i & i2) == i2;
    }
}
