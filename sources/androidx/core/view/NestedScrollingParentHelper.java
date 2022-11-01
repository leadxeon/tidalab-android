package androidx.core.view;
/* loaded from: classes.dex */
public class NestedScrollingParentHelper {
    public int mNestedScrollAxesNonTouch;
    public int mNestedScrollAxesTouch;

    public int getNestedScrollAxes() {
        return this.mNestedScrollAxesTouch | this.mNestedScrollAxesNonTouch;
    }

    public void onStopNestedScroll(int i) {
        if (i == 1) {
            this.mNestedScrollAxesNonTouch = 0;
        } else {
            this.mNestedScrollAxesTouch = 0;
        }
    }
}
