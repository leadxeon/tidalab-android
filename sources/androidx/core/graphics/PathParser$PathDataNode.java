package androidx.core.graphics;

import android.graphics.Path;
import android.util.Log;
import androidx.core.app.AppOpsManagerCompat;
/* loaded from: classes.dex */
public class PathParser$PathDataNode {
    public float[] mParams;
    public char mType;

    public PathParser$PathDataNode(char c, float[] fArr) {
        this.mType = c;
        this.mParams = fArr;
    }

    public static void drawArc(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
        double d;
        double d2;
        double radians = Math.toRadians(f7);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d3 = f;
        double d4 = f2;
        double d5 = (d4 * sin) + (d3 * cos);
        double d6 = d3;
        double d7 = f5;
        double d8 = d5 / d7;
        double d9 = f6;
        double d10 = ((d4 * cos) + ((-f) * sin)) / d9;
        double d11 = d4;
        double d12 = f4;
        double d13 = ((d12 * sin) + (f3 * cos)) / d7;
        double d14 = ((d12 * cos) + ((-f3) * sin)) / d9;
        double d15 = d8 - d13;
        double d16 = d10 - d14;
        double d17 = (d8 + d13) / 2.0d;
        double d18 = (d10 + d14) / 2.0d;
        double d19 = (d16 * d16) + (d15 * d15);
        if (d19 == 0.0d) {
            Log.w("PathParser", " Points are coincident");
            return;
        }
        double d20 = (1.0d / d19) - 0.25d;
        if (d20 < 0.0d) {
            Log.w("PathParser", "Points are too far apart " + d19);
            float sqrt = (float) (Math.sqrt(d19) / 1.99999d);
            drawArc(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
            return;
        }
        double sqrt2 = Math.sqrt(d20);
        double d21 = d15 * sqrt2;
        double d22 = sqrt2 * d16;
        if (z == z2) {
            d2 = d17 - d22;
            d = d18 + d21;
        } else {
            d2 = d17 + d22;
            d = d18 - d21;
        }
        double atan2 = Math.atan2(d10 - d, d8 - d2);
        double atan22 = Math.atan2(d14 - d, d13 - d2) - atan2;
        int i = 0;
        int i2 = (atan22 > 0.0d ? 1 : (atan22 == 0.0d ? 0 : -1));
        if (z2 != (i2 >= 0)) {
            atan22 = i2 > 0 ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
        }
        double d23 = d2 * d7;
        double d24 = d * d9;
        double d25 = (d23 * cos) - (d24 * sin);
        double d26 = (d24 * cos) + (d23 * sin);
        int ceil = (int) Math.ceil(Math.abs((atan22 * 4.0d) / 3.141592653589793d));
        double cos2 = Math.cos(radians);
        double sin2 = Math.sin(radians);
        double cos3 = Math.cos(atan2);
        double sin3 = Math.sin(atan2);
        double d27 = -d7;
        double d28 = d27 * cos2;
        double d29 = d9 * sin2;
        double d30 = (d28 * sin3) - (d29 * cos3);
        double d31 = d27 * sin2;
        double d32 = d9 * cos2;
        double d33 = (cos3 * d32) + (sin3 * d31);
        double d34 = atan22 / ceil;
        double d35 = atan2;
        while (i < ceil) {
            double d36 = d35 + d34;
            double sin4 = Math.sin(d36);
            double cos4 = Math.cos(d36);
            double d37 = (((d7 * cos2) * cos4) + d25) - (d29 * sin4);
            double d38 = (d32 * sin4) + (d7 * sin2 * cos4) + d26;
            double d39 = (d28 * sin4) - (d29 * cos4);
            double d40 = (cos4 * d32) + (sin4 * d31);
            double d41 = d36 - d35;
            double tan = Math.tan(d41 / 2.0d);
            double sqrt3 = ((Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d) * Math.sin(d41)) / 3.0d;
            path.rLineTo(0.0f, 0.0f);
            path.cubicTo((float) ((d30 * sqrt3) + d6), (float) ((d33 * sqrt3) + d11), (float) (d37 - (sqrt3 * d39)), (float) (d38 - (sqrt3 * d40)), (float) d37, (float) d38);
            i++;
            d32 = d32;
            d31 = d31;
            ceil = ceil;
            cos2 = cos2;
            d35 = d36;
            d7 = d7;
            d33 = d40;
            d30 = d39;
            d6 = d37;
            d11 = d38;
            d34 = d34;
            d25 = d25;
        }
    }

    public static void nodesToPath(PathParser$PathDataNode[] pathParser$PathDataNodeArr, Path path) {
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        PathParser$PathDataNode[] pathParser$PathDataNodeArr2 = pathParser$PathDataNodeArr;
        float[] fArr = new float[6];
        char c = 'm';
        char c2 = 0;
        char c3 = 'm';
        int i3 = 0;
        while (i3 < pathParser$PathDataNodeArr2.length) {
            char c4 = pathParser$PathDataNodeArr2[i3].mType;
            float[] fArr2 = pathParser$PathDataNodeArr2[i3].mParams;
            float f13 = fArr[c2];
            float f14 = fArr[1];
            float f15 = fArr[2];
            float f16 = fArr[3];
            float f17 = fArr[4];
            float f18 = fArr[5];
            switch (c4) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    path.moveTo(f17, f18);
                    f13 = f17;
                    f15 = f13;
                    f14 = f18;
                    f16 = f14;
                default:
                    i = 2;
                    break;
            }
            float f19 = f17;
            float f20 = f18;
            int i4 = 0;
            while (i4 < fArr2.length) {
                if (c4 != 'A') {
                    if (c4 != 'C') {
                        if (c4 == 'H') {
                            i2 = i4;
                            fArr2 = fArr2;
                            c3 = c4;
                            i3 = i3;
                            int i5 = i2 + 0;
                            path.lineTo(fArr2[i5], f14);
                            f13 = fArr2[i5];
                        } else if (c4 == 'Q') {
                            i2 = i4;
                            fArr2 = fArr2;
                            c3 = c4;
                            i3 = i3;
                            int i6 = i2 + 0;
                            int i7 = i2 + 1;
                            int i8 = i2 + 2;
                            int i9 = i2 + 3;
                            path.quadTo(fArr2[i6], fArr2[i7], fArr2[i8], fArr2[i9]);
                            f15 = fArr2[i6];
                            f16 = fArr2[i7];
                            f13 = fArr2[i8];
                            f14 = fArr2[i9];
                        } else if (c4 == 'V') {
                            i2 = i4;
                            fArr2 = fArr2;
                            c3 = c4;
                            i3 = i3;
                            int i10 = i2 + 0;
                            path.lineTo(f13, fArr2[i10]);
                            f14 = fArr2[i10];
                        } else if (c4 != 'a') {
                            if (c4 != 'c') {
                                if (c4 == 'h') {
                                    i2 = i4;
                                    int i11 = i2 + 0;
                                    path.rLineTo(fArr2[i11], 0.0f);
                                    f13 += fArr2[i11];
                                } else if (c4 != 'q') {
                                    if (c4 == 'v') {
                                        i2 = i4;
                                        f3 = f14;
                                        int i12 = i2 + 0;
                                        path.rLineTo(0.0f, fArr2[i12]);
                                        f4 = fArr2[i12];
                                    } else if (c4 == 'L') {
                                        i2 = i4;
                                        int i13 = i2 + 0;
                                        int i14 = i2 + 1;
                                        path.lineTo(fArr2[i13], fArr2[i14]);
                                        f13 = fArr2[i13];
                                        f14 = fArr2[i14];
                                    } else if (c4 == 'M') {
                                        i2 = i4;
                                        int i15 = i2 + 0;
                                        float f21 = fArr2[i15];
                                        int i16 = i2 + 1;
                                        float f22 = fArr2[i16];
                                        if (i2 > 0) {
                                            path.lineTo(fArr2[i15], fArr2[i16]);
                                            f13 = f21;
                                            f14 = f22;
                                        } else {
                                            path.moveTo(fArr2[i15], fArr2[i16]);
                                            f19 = f21;
                                            f20 = f22;
                                            f13 = f19;
                                            f14 = f20;
                                        }
                                    } else if (c4 == 'S') {
                                        i2 = i4;
                                        if (c3 == 'c' || c3 == 's' || c3 == 'C' || c3 == 'S') {
                                            f6 = (f13 * 2.0f) - f15;
                                            f5 = (f14 * 2.0f) - f16;
                                        } else {
                                            f6 = f13;
                                            f5 = f14;
                                        }
                                        int i17 = i2 + 0;
                                        int i18 = i2 + 1;
                                        int i19 = i2 + 2;
                                        int i20 = i2 + 3;
                                        path.cubicTo(f6, f5, fArr2[i17], fArr2[i18], fArr2[i19], fArr2[i20]);
                                        float f23 = fArr2[i17];
                                        float f24 = fArr2[i18];
                                        f13 = fArr2[i19];
                                        f14 = fArr2[i20];
                                        f15 = f23;
                                        f16 = f24;
                                    } else if (c4 == 'T') {
                                        i2 = i4;
                                        if (c3 == 'q' || c3 == 't' || c3 == 'Q' || c3 == 'T') {
                                            f7 = (f13 * 2.0f) - f15;
                                            f8 = (f14 * 2.0f) - f16;
                                        } else {
                                            f7 = f13;
                                            f8 = f14;
                                        }
                                        int i21 = i2 + 0;
                                        int i22 = i2 + 1;
                                        path.quadTo(f7, f8, fArr2[i21], fArr2[i22]);
                                        f16 = f8;
                                        f15 = f7;
                                        fArr2 = fArr2;
                                        c3 = c4;
                                        i3 = i3;
                                        f13 = fArr2[i21];
                                        f14 = fArr2[i22];
                                    } else if (c4 == 'l') {
                                        i2 = i4;
                                        f3 = f14;
                                        int i23 = i2 + 0;
                                        int i24 = i2 + 1;
                                        path.rLineTo(fArr2[i23], fArr2[i24]);
                                        f13 += fArr2[i23];
                                        f4 = fArr2[i24];
                                    } else if (c4 == c) {
                                        i2 = i4;
                                        int i25 = i2 + 0;
                                        f13 += fArr2[i25];
                                        int i26 = i2 + 1;
                                        f14 += fArr2[i26];
                                        if (i2 > 0) {
                                            path.rLineTo(fArr2[i25], fArr2[i26]);
                                        } else {
                                            path.rMoveTo(fArr2[i25], fArr2[i26]);
                                            f20 = f14;
                                            f19 = f13;
                                            f13 = f19;
                                            f14 = f20;
                                        }
                                    } else if (c4 != 's') {
                                        if (c4 == 't') {
                                            if (c3 == 'q' || c3 == 't' || c3 == 'Q' || c3 == 'T') {
                                                f11 = f13 - f15;
                                                f12 = f14 - f16;
                                            } else {
                                                f12 = 0.0f;
                                                f11 = 0.0f;
                                            }
                                            int i27 = i4 + 0;
                                            int i28 = i4 + 1;
                                            path.rQuadTo(f11, f12, fArr2[i27], fArr2[i28]);
                                            f15 = f11 + f13;
                                            f16 = f12 + f14;
                                            f13 += fArr2[i27];
                                            f14 += fArr2[i28];
                                        }
                                        i2 = i4;
                                    } else {
                                        if (c3 == 'c' || c3 == 's' || c3 == 'C' || c3 == 'S') {
                                            f10 = f13 - f15;
                                            f9 = f14 - f16;
                                        } else {
                                            f10 = 0.0f;
                                            f9 = 0.0f;
                                        }
                                        int i29 = i4 + 0;
                                        int i30 = i4 + 1;
                                        int i31 = i4 + 2;
                                        int i32 = i4 + 3;
                                        i2 = i4;
                                        f = f14;
                                        path.rCubicTo(f10, f9, fArr2[i29], fArr2[i30], fArr2[i31], fArr2[i32]);
                                        f15 = fArr2[i29] + f13;
                                        f16 = fArr2[i30] + f;
                                        f13 += fArr2[i31];
                                        f2 = fArr2[i32];
                                    }
                                    f14 = f3 + f4;
                                } else {
                                    i2 = i4;
                                    f = f14;
                                    int i33 = i2 + 0;
                                    int i34 = i2 + 1;
                                    int i35 = i2 + 2;
                                    int i36 = i2 + 3;
                                    path.rQuadTo(fArr2[i33], fArr2[i34], fArr2[i35], fArr2[i36]);
                                    f15 = fArr2[i33] + f13;
                                    f16 = fArr2[i34] + f;
                                    f13 += fArr2[i35];
                                    f2 = fArr2[i36];
                                }
                                fArr2 = fArr2;
                                c3 = c4;
                                i3 = i3;
                            } else {
                                i2 = i4;
                                f = f14;
                                int i37 = i2 + 2;
                                int i38 = i2 + 3;
                                int i39 = i2 + 4;
                                int i40 = i2 + 5;
                                path.rCubicTo(fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i37], fArr2[i38], fArr2[i39], fArr2[i40]);
                                f15 = fArr2[i37] + f13;
                                f16 = fArr2[i38] + f;
                                f13 += fArr2[i39];
                                f2 = fArr2[i40];
                            }
                            f14 = f + f2;
                            fArr2 = fArr2;
                            c3 = c4;
                            i3 = i3;
                        } else {
                            i2 = i4;
                            int i41 = i2 + 5;
                            int i42 = i2 + 6;
                            fArr2 = fArr2;
                            c3 = c4;
                            i3 = i3;
                            drawArc(path, f13, f14, fArr2[i41] + f13, fArr2[i42] + f14, fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                            f13 += fArr2[i41];
                            f14 += fArr2[i42];
                        }
                        i4 = i2 + i;
                        c4 = c3;
                        c = 'm';
                    } else {
                        i2 = i4;
                        fArr2 = fArr2;
                        c3 = c4;
                        i3 = i3;
                        int i43 = i2 + 2;
                        int i44 = i2 + 3;
                        int i45 = i2 + 4;
                        int i46 = i2 + 5;
                        path.cubicTo(fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i43], fArr2[i44], fArr2[i45], fArr2[i46]);
                        float f25 = fArr2[i45];
                        float f26 = fArr2[i46];
                        f15 = fArr2[i43];
                        f13 = f25;
                        f14 = f26;
                        f16 = fArr2[i44];
                    }
                    i4 = i2 + i;
                    c4 = c3;
                    c = 'm';
                } else {
                    i2 = i4;
                    fArr2 = fArr2;
                    c3 = c4;
                    i3 = i3;
                    int i47 = i2 + 5;
                    int i48 = i2 + 6;
                    drawArc(path, f13, f14, fArr2[i47], fArr2[i48], fArr2[i2 + 0], fArr2[i2 + 1], fArr2[i2 + 2], fArr2[i2 + 3] != 0.0f, fArr2[i2 + 4] != 0.0f);
                    f13 = fArr2[i47];
                    f14 = fArr2[i48];
                }
                f16 = f14;
                f15 = f13;
                i4 = i2 + i;
                c4 = c3;
                c = 'm';
            }
            fArr[0] = f13;
            fArr[1] = f14;
            fArr[2] = f15;
            fArr[3] = f16;
            fArr[4] = f19;
            fArr[5] = f20;
            c3 = pathParser$PathDataNodeArr[i3].mType;
            i3++;
            c = 'm';
            c2 = 0;
            pathParser$PathDataNodeArr2 = pathParser$PathDataNodeArr;
        }
    }

    public PathParser$PathDataNode(PathParser$PathDataNode pathParser$PathDataNode) {
        this.mType = pathParser$PathDataNode.mType;
        float[] fArr = pathParser$PathDataNode.mParams;
        this.mParams = AppOpsManagerCompat.copyOfRange(fArr, 0, fArr.length);
    }
}
