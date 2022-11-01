package com.facebook.react.uimanager;
/* loaded from: classes.dex */
public class MatrixMathHelper$MatrixDecompositionContext {
    public double[] perspective = new double[4];
    public double[] scale = new double[3];
    public double[] skew = new double[3];
    public double[] translation = new double[3];
    public double[] rotationDegrees = new double[3];

    public static void resetArray(double[] dArr) {
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = 0.0d;
        }
    }
}
