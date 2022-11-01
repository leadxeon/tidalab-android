package com.facebook.react.uimanager;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.UiThreadUtil;
/* loaded from: classes.dex */
public class TouchTargetHelper {
    public static final float[] mEventCoords = new float[2];
    public static final PointF mTempPoint = new PointF();
    public static final float[] mMatrixTransformCoords = new float[2];
    public static final Matrix mInverseMatrix = new Matrix();

    public static int findTargetTagAndCoordinatesForTouch(float f, float f2, ViewGroup viewGroup, float[] fArr, int[] iArr) {
        int i;
        UiThreadUtil.assertOnUiThread();
        int id = viewGroup.getId();
        fArr[0] = f;
        fArr[1] = f2;
        View findTouchTargetView = findTouchTargetView(fArr, viewGroup);
        while (findTouchTargetView != null && findTouchTargetView.getId() <= 0) {
            findTouchTargetView = (View) findTouchTargetView.getParent();
        }
        if (findTouchTargetView == null) {
            return id;
        }
        float f3 = fArr[0];
        float f4 = fArr[1];
        if (findTouchTargetView instanceof ReactCompoundView) {
            i = ((ReactCompoundView) findTouchTargetView).reactTagForTouch(f3, f4);
        } else {
            i = findTouchTargetView.getId();
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:60:0x0125, code lost:
        if (((com.facebook.react.uimanager.ReactCompoundView) r4).reactTagForTouch(r14[0], r14[1]) != r4.getId()) goto L_0x0148;
     */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x014a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0167 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.view.View findTouchTargetView(float[] r14, android.view.ViewGroup r15) {
        /*
            Method dump skipped, instructions count: 364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.TouchTargetHelper.findTouchTargetView(float[], android.view.ViewGroup):android.view.View");
    }
}
