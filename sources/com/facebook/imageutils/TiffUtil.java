package com.facebook.imageutils;

import androidx.recyclerview.R$dimen;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes.dex */
public class TiffUtil {
    public static int readOrientationFromTIFF(InputStream inputStream, int i) throws IOException {
        int i2;
        int i3;
        boolean z;
        int i4;
        if (i > 8) {
            int readPackedInt = R$dimen.readPackedInt(inputStream, 4, false);
            int i5 = i - 4;
            if (readPackedInt == 1229531648 || readPackedInt == 1296891946) {
                z = readPackedInt == 1229531648;
                i3 = R$dimen.readPackedInt(inputStream, 4, z);
                i2 = i5 - 4;
                if (i3 < 8 || i3 - 8 > i2) {
                    FLog.e(TiffUtil.class, "Invalid offset");
                    i2 = 0;
                }
                int i6 = i3 - 8;
                if (i2 == 0 && i6 <= i2) {
                    inputStream.skip(i6);
                    int i7 = i2 - i6;
                    if (i7 >= 14) {
                        int readPackedInt2 = R$dimen.readPackedInt(inputStream, 2, z);
                        int i8 = i7 - 2;
                        while (true) {
                            readPackedInt2--;
                            if (readPackedInt2 <= 0 || i8 < 12) {
                                break;
                            }
                            i4 = i8 - 2;
                            if (R$dimen.readPackedInt(inputStream, 2, z) == 274) {
                                break;
                            }
                            inputStream.skip(10L);
                            i8 = i4 - 10;
                        }
                    }
                    i4 = 0;
                    if (i4 < 10 || R$dimen.readPackedInt(inputStream, 2, z) != 3 || R$dimen.readPackedInt(inputStream, 4, z) != 1) {
                        return 0;
                    }
                    int readPackedInt3 = R$dimen.readPackedInt(inputStream, 2, z);
                    R$dimen.readPackedInt(inputStream, 2, z);
                    return readPackedInt3;
                }
            } else {
                FLog.e(TiffUtil.class, "Invalid TIFF header");
            }
        }
        i2 = 0;
        z = false;
        i3 = 0;
        int i62 = i3 - 8;
        return i2 == 0 ? 0 : 0;
    }
}
