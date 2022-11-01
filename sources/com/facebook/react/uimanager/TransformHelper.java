package com.facebook.react.uimanager;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
/* loaded from: classes.dex */
public class TransformHelper {
    public static ThreadLocal<double[]> sHelperMatrix = new ThreadLocal<double[]>() { // from class: com.facebook.react.uimanager.TransformHelper.1
        @Override // java.lang.ThreadLocal
        public double[] initialValue() {
            return new double[16];
        }
    };

    public static double convertToRadians(ReadableMap readableMap, String str) {
        double d;
        boolean z = true;
        if (readableMap.getType(str) == ReadableType.String) {
            String string = readableMap.getString(str);
            if (string.endsWith("rad")) {
                string = string.substring(0, string.length() - 3);
            } else if (string.endsWith("deg")) {
                string = string.substring(0, string.length() - 3);
                z = false;
            }
            d = Float.parseFloat(string);
        } else {
            d = readableMap.getDouble(str);
        }
        return z ? d : (d * 3.141592653589793d) / 180.0d;
    }

    public static void processTransform(ReadableArray readableArray, double[] dArr) {
        char c;
        double[] dArr2 = sHelperMatrix.get();
        R$style.resetIdentityMatrix(dArr);
        int size = readableArray.size();
        for (int i = 0; i < size; i++) {
            ReadableMap map = readableArray.getMap(i);
            String nextKey = map.keySetIterator().nextKey();
            R$style.resetIdentityMatrix(dArr2);
            if ("matrix".equals(nextKey)) {
                ReadableArray array = map.getArray(nextKey);
                for (int i2 = 0; i2 < 16; i2++) {
                    dArr2[i2] = array.getDouble(i2);
                }
            } else if ("perspective".equals(nextKey)) {
                dArr2[11] = (-1.0d) / map.getDouble(nextKey);
            } else if ("rotateX".equals(nextKey)) {
                double convertToRadians = convertToRadians(map, nextKey);
                dArr2[5] = Math.cos(convertToRadians);
                dArr2[6] = Math.sin(convertToRadians);
                dArr2[9] = -Math.sin(convertToRadians);
                dArr2[10] = Math.cos(convertToRadians);
            } else if ("rotateY".equals(nextKey)) {
                double convertToRadians2 = convertToRadians(map, nextKey);
                dArr2[0] = Math.cos(convertToRadians2);
                dArr2[2] = -Math.sin(convertToRadians2);
                dArr2[8] = Math.sin(convertToRadians2);
                dArr2[10] = Math.cos(convertToRadians2);
            } else {
                if ("rotate".equals(nextKey) || "rotateZ".equals(nextKey)) {
                    double convertToRadians3 = convertToRadians(map, nextKey);
                    dArr2[0] = Math.cos(convertToRadians3);
                    dArr2[1] = Math.sin(convertToRadians3);
                    c = 4;
                    dArr2[4] = -Math.sin(convertToRadians3);
                    dArr2[5] = Math.cos(convertToRadians3);
                } else if ("scale".equals(nextKey)) {
                    double d = map.getDouble(nextKey);
                    dArr2[0] = d;
                    dArr2[5] = d;
                } else if ("scaleX".equals(nextKey)) {
                    dArr2[0] = map.getDouble(nextKey);
                } else if ("scaleY".equals(nextKey)) {
                    dArr2[5] = map.getDouble(nextKey);
                } else {
                    double d2 = 0.0d;
                    if ("translate".equals(nextKey)) {
                        ReadableArray array2 = map.getArray(nextKey);
                        double d3 = array2.getDouble(0);
                        double d4 = array2.getDouble(1);
                        if (array2.size() > 2) {
                            d2 = array2.getDouble(2);
                        }
                        dArr2[12] = d3;
                        dArr2[13] = d4;
                        dArr2[14] = d2;
                    } else if ("translateX".equals(nextKey)) {
                        dArr2[12] = map.getDouble(nextKey);
                        dArr2[13] = 0.0d;
                    } else if ("translateY".equals(nextKey)) {
                        double d5 = map.getDouble(nextKey);
                        dArr2[12] = 0.0d;
                        dArr2[13] = d5;
                    } else if ("skewX".equals(nextKey)) {
                        c = 4;
                        dArr2[4] = Math.tan(convertToRadians(map, nextKey));
                    } else if ("skewY".equals(nextKey)) {
                        dArr2[1] = Math.tan(convertToRadians(map, nextKey));
                    } else {
                        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport.outline8("Unsupported transform type: ", nextKey));
                    }
                }
                double d6 = dArr[0];
                double d7 = dArr[1];
                double d8 = dArr[2];
                double d9 = dArr[3];
                double d10 = dArr[c];
                double d11 = dArr[5];
                double d12 = dArr[6];
                double d13 = dArr[7];
                double d14 = dArr[8];
                double d15 = dArr[9];
                double d16 = dArr[10];
                double d17 = dArr[11];
                double d18 = dArr[12];
                double d19 = dArr[13];
                double d20 = dArr[14];
                double d21 = dArr[15];
                double d22 = dArr2[0];
                double d23 = dArr2[1];
                double d24 = dArr2[2];
                double d25 = dArr2[3];
                dArr[0] = (d25 * d18) + (d24 * d14) + (d23 * d10) + (d22 * d6);
                dArr[1] = (d25 * d19) + (d24 * d15) + (d23 * d11) + (d22 * d7);
                dArr[2] = (d25 * d20) + (d24 * d16) + (d23 * d12) + (d22 * d8);
                double d26 = d24 * d17;
                double d27 = d25 * d21;
                dArr[3] = d27 + d26 + (d23 * d13) + (d22 * d9);
                double d28 = dArr2[4];
                double d29 = dArr2[5];
                double d30 = dArr2[6];
                double d31 = dArr2[7];
                dArr[4] = (d31 * d18) + (d30 * d14) + (d29 * d10) + (d28 * d6);
                dArr[5] = (d31 * d19) + (d30 * d15) + (d29 * d11) + (d28 * d7);
                dArr[6] = (d31 * d20) + (d30 * d16) + (d29 * d12) + (d28 * d8);
                double d32 = d30 * d17;
                double d33 = d31 * d21;
                dArr[7] = d33 + d32 + (d29 * d13) + (d28 * d9);
                double d34 = dArr2[8];
                double d35 = dArr2[9];
                double d36 = dArr2[10];
                double d37 = dArr2[11];
                dArr[8] = (d37 * d18) + (d36 * d14) + (d35 * d10) + (d34 * d6);
                dArr[9] = (d37 * d19) + (d36 * d15) + (d35 * d11) + (d34 * d7);
                dArr[10] = (d37 * d20) + (d36 * d16) + (d35 * d12) + (d34 * d8);
                double d38 = d36 * d17;
                double d39 = d37 * d21;
                dArr[11] = d39 + d38 + (d35 * d13) + (d34 * d9);
                double d40 = dArr2[12];
                double d41 = dArr2[13];
                double d42 = dArr2[14];
                double d43 = dArr2[15];
                double d44 = d14 * d42;
                double d45 = d18 * d43;
                dArr[12] = d45 + d44 + (d10 * d41) + (d6 * d40);
                double d46 = d15 * d42;
                double d47 = d19 * d43;
                dArr[13] = d47 + d46 + (d11 * d41) + (d7 * d40);
                double d48 = d16 * d42;
                double d49 = d20 * d43;
                dArr[14] = d49 + d48 + (d12 * d41) + (d8 * d40);
                double d50 = d42 * d17;
                double d51 = d43 * d21;
                dArr[15] = d51 + d50 + (d41 * d13) + (d40 * d9);
            }
            c = 4;
            double d62 = dArr[0];
            double d72 = dArr[1];
            double d82 = dArr[2];
            double d92 = dArr[3];
            double d102 = dArr[c];
            double d112 = dArr[5];
            double d122 = dArr[6];
            double d132 = dArr[7];
            double d142 = dArr[8];
            double d152 = dArr[9];
            double d162 = dArr[10];
            double d172 = dArr[11];
            double d182 = dArr[12];
            double d192 = dArr[13];
            double d202 = dArr[14];
            double d212 = dArr[15];
            double d222 = dArr2[0];
            double d232 = dArr2[1];
            double d242 = dArr2[2];
            double d252 = dArr2[3];
            dArr[0] = (d252 * d182) + (d242 * d142) + (d232 * d102) + (d222 * d62);
            dArr[1] = (d252 * d192) + (d242 * d152) + (d232 * d112) + (d222 * d72);
            dArr[2] = (d252 * d202) + (d242 * d162) + (d232 * d122) + (d222 * d82);
            double d262 = d242 * d172;
            double d272 = d252 * d212;
            dArr[3] = d272 + d262 + (d232 * d132) + (d222 * d92);
            double d282 = dArr2[4];
            double d292 = dArr2[5];
            double d302 = dArr2[6];
            double d312 = dArr2[7];
            dArr[4] = (d312 * d182) + (d302 * d142) + (d292 * d102) + (d282 * d62);
            dArr[5] = (d312 * d192) + (d302 * d152) + (d292 * d112) + (d282 * d72);
            dArr[6] = (d312 * d202) + (d302 * d162) + (d292 * d122) + (d282 * d82);
            double d322 = d302 * d172;
            double d332 = d312 * d212;
            dArr[7] = d332 + d322 + (d292 * d132) + (d282 * d92);
            double d342 = dArr2[8];
            double d352 = dArr2[9];
            double d362 = dArr2[10];
            double d372 = dArr2[11];
            dArr[8] = (d372 * d182) + (d362 * d142) + (d352 * d102) + (d342 * d62);
            dArr[9] = (d372 * d192) + (d362 * d152) + (d352 * d112) + (d342 * d72);
            dArr[10] = (d372 * d202) + (d362 * d162) + (d352 * d122) + (d342 * d82);
            double d382 = d362 * d172;
            double d392 = d372 * d212;
            dArr[11] = d392 + d382 + (d352 * d132) + (d342 * d92);
            double d402 = dArr2[12];
            double d412 = dArr2[13];
            double d422 = dArr2[14];
            double d432 = dArr2[15];
            double d442 = d142 * d422;
            double d452 = d182 * d432;
            dArr[12] = d452 + d442 + (d102 * d412) + (d62 * d402);
            double d462 = d152 * d422;
            double d472 = d192 * d432;
            dArr[13] = d472 + d462 + (d112 * d412) + (d72 * d402);
            double d482 = d162 * d422;
            double d492 = d202 * d432;
            dArr[14] = d492 + d482 + (d122 * d412) + (d82 * d402);
            double d502 = d422 * d172;
            double d512 = d432 * d212;
            dArr[15] = d512 + d502 + (d412 * d132) + (d402 * d92);
        }
    }
}
