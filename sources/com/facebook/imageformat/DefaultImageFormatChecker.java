package com.facebook.imageformat;

import androidx.recyclerview.R$dimen;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imageformat.ImageFormat;
import java.util.Objects;
/* loaded from: classes.dex */
public class DefaultImageFormatChecker implements ImageFormat.FormatChecker {
    public static final byte[] BMP_HEADER;
    public static final int BMP_HEADER_LENGTH;
    public static final byte[] GIF_HEADER_87A = R$dimen.asciiBytes("GIF87a");
    public static final byte[] GIF_HEADER_89A = R$dimen.asciiBytes("GIF89a");
    public static final int HEIF_HEADER_LENGTH;
    public static final String[] HEIF_HEADER_SUFFIXES;
    public static final byte[] ICO_HEADER;
    public static final int ICO_HEADER_LENGTH;
    public static final byte[] JPEG_HEADER;
    public static final int JPEG_HEADER_LENGTH;
    public static final byte[] PNG_HEADER;
    public static final int PNG_HEADER_LENGTH;
    public final int MAX_HEADER_LENGTH;

    static {
        byte[] bArr = {-1, -40, -1};
        JPEG_HEADER = bArr;
        JPEG_HEADER_LENGTH = bArr.length;
        byte[] bArr2 = {-119, 80, 78, 71, 13, 10, 26, 10};
        PNG_HEADER = bArr2;
        PNG_HEADER_LENGTH = bArr2.length;
        byte[] asciiBytes = R$dimen.asciiBytes("BM");
        BMP_HEADER = asciiBytes;
        BMP_HEADER_LENGTH = asciiBytes.length;
        byte[] bArr3 = {0, 0, 1, 0};
        ICO_HEADER = bArr3;
        ICO_HEADER_LENGTH = bArr3.length;
        String[] strArr = {"heic", "heix", "hevc", "hevx", "mif1", "msf1"};
        HEIF_HEADER_SUFFIXES = strArr;
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("ftyp");
        outline13.append(strArr[0]);
        HEIF_HEADER_LENGTH = R$dimen.asciiBytes(outline13.toString()).length;
    }

    public DefaultImageFormatChecker() {
        int[] iArr = {21, 20, JPEG_HEADER_LENGTH, PNG_HEADER_LENGTH, 6, BMP_HEADER_LENGTH, ICO_HEADER_LENGTH, HEIF_HEADER_LENGTH};
        R$dimen.checkArgument(true);
        int i = iArr[0];
        for (int i2 = 1; i2 < 8; i2++) {
            if (iArr[i2] > i) {
                i = iArr[i2];
            }
        }
        this.MAX_HEADER_LENGTH = i;
    }

    @Override // com.facebook.imageformat.ImageFormat.FormatChecker
    public final ImageFormat determineFormat(byte[] bArr, int i) {
        int i2;
        char c = 0;
        if (WebpSupportStatus.isWebpHeader(bArr, 0, i)) {
            R$dimen.checkArgument(WebpSupportStatus.isWebpHeader(bArr, 0, i));
            if (WebpSupportStatus.matchBytePattern(bArr, 12, WebpSupportStatus.WEBP_VP8_BYTES)) {
                return DefaultImageFormats.WEBP_SIMPLE;
            }
            if (WebpSupportStatus.matchBytePattern(bArr, 12, WebpSupportStatus.WEBP_VP8L_BYTES)) {
                return DefaultImageFormats.WEBP_LOSSLESS;
            }
            if (!(i >= 21 && WebpSupportStatus.matchBytePattern(bArr, 12, WebpSupportStatus.WEBP_VP8X_BYTES))) {
                return ImageFormat.UNKNOWN;
            }
            byte[] bArr2 = WebpSupportStatus.WEBP_VP8X_BYTES;
            if (WebpSupportStatus.matchBytePattern(bArr, 12, bArr2) && ((bArr[20] & 2) == 2)) {
                return DefaultImageFormats.WEBP_ANIMATED;
            }
            boolean matchBytePattern = WebpSupportStatus.matchBytePattern(bArr, 12, bArr2);
            boolean z = (bArr[20] & 16) == 16;
            if (matchBytePattern && z) {
                c = 1;
            }
            if (c != 0) {
                return DefaultImageFormats.WEBP_EXTENDED_WITH_ALPHA;
            }
            return DefaultImageFormats.WEBP_EXTENDED;
        }
        byte[] bArr3 = JPEG_HEADER;
        if (i >= bArr3.length && R$dimen.startsWithPattern(bArr, bArr3)) {
            return DefaultImageFormats.JPEG;
        }
        byte[] bArr4 = PNG_HEADER;
        if (i >= bArr4.length && R$dimen.startsWithPattern(bArr, bArr4)) {
            return DefaultImageFormats.PNG;
        }
        if (i >= 6 && (R$dimen.startsWithPattern(bArr, GIF_HEADER_87A) || R$dimen.startsWithPattern(bArr, GIF_HEADER_89A))) {
            return DefaultImageFormats.GIF;
        }
        byte[] bArr5 = BMP_HEADER;
        if (i < bArr5.length ? false : R$dimen.startsWithPattern(bArr, bArr5)) {
            return DefaultImageFormats.BMP;
        }
        byte[] bArr6 = ICO_HEADER;
        if (i < bArr6.length ? false : R$dimen.startsWithPattern(bArr, bArr6)) {
            return DefaultImageFormats.ICO;
        }
        if (i >= HEIF_HEADER_LENGTH && bArr[3] >= 8) {
            String[] strArr = HEIF_HEADER_SUFFIXES;
            int length = strArr.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    c = 0;
                    break;
                }
                String str = strArr[i3];
                int length2 = bArr.length;
                byte[] asciiBytes = R$dimen.asciiBytes("ftyp" + str);
                int i4 = HEIF_HEADER_LENGTH;
                Objects.requireNonNull(asciiBytes);
                if (i4 <= length2) {
                    byte b = asciiBytes[c];
                    int i5 = length2 - i4;
                    i2 = 0;
                    while (i2 <= i5) {
                        if (bArr[i2] != b) {
                            do {
                                i2++;
                                if (i2 > i5) {
                                    break;
                                }
                            } while (bArr[i2] != b);
                        }
                        if (i2 <= i5) {
                            int i6 = i2 + 1;
                            int i7 = (i6 + i4) - 1;
                            for (int i8 = 1; i6 < i7 && bArr[i6] == asciiBytes[i8]; i8++) {
                                i6++;
                            }
                            if (i6 == i7) {
                                break;
                            }
                        }
                        i2++;
                    }
                }
                i2 = -1;
                if (i2 > -1) {
                    c = 1;
                    break;
                }
                i3++;
                c = 0;
            }
        }
        if (c != 0) {
            return DefaultImageFormats.HEIF;
        }
        return ImageFormat.UNKNOWN;
    }

    @Override // com.facebook.imageformat.ImageFormat.FormatChecker
    public int getHeaderSize() {
        return this.MAX_HEADER_LENGTH;
    }
}
