package androidx.transition;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.view.View;
/* loaded from: classes.dex */
public class ViewUtilsApi21 extends ViewUtilsApi19 {
    public static boolean sTryHiddenTransformMatrixToGlobal = true;
    public static boolean sTryHiddenTransformMatrixToLocal = true;

    @Override // androidx.transition.ViewUtilsBase
    @SuppressLint({"NewApi"})
    public void transformMatrixToGlobal(View view, Matrix matrix) {
        if (sTryHiddenTransformMatrixToGlobal) {
            try {
                view.transformMatrixToGlobal(matrix);
            } catch (NoSuchMethodError unused) {
                sTryHiddenTransformMatrixToGlobal = false;
            }
        }
    }

    @Override // androidx.transition.ViewUtilsBase
    @SuppressLint({"NewApi"})
    public void transformMatrixToLocal(View view, Matrix matrix) {
        if (sTryHiddenTransformMatrixToLocal) {
            try {
                view.transformMatrixToLocal(matrix);
            } catch (NoSuchMethodError unused) {
                sTryHiddenTransformMatrixToLocal = false;
            }
        }
    }
}
