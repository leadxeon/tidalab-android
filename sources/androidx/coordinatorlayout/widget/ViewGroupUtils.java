package androidx.coordinatorlayout.widget;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewParent;
/* loaded from: classes.dex */
public class ViewGroupUtils {
    public static final ThreadLocal<Matrix> sMatrix = new ThreadLocal<>();
    public static final ThreadLocal<RectF> sRectF = new ThreadLocal<>();

    public static void offsetDescendantMatrix(ViewParent viewParent, View view, Matrix matrix) {
        ViewParent parent = view.getParent();
        if ((parent instanceof View) && parent != viewParent) {
            View view2 = (View) parent;
            offsetDescendantMatrix(viewParent, view2, matrix);
            matrix.preTranslate(-view2.getScrollX(), -view2.getScrollY());
        }
        matrix.preTranslate(view.getLeft(), view.getTop());
        if (!view.getMatrix().isIdentity()) {
            matrix.preConcat(view.getMatrix());
        }
    }
}
