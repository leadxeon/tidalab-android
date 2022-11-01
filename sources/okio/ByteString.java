package okio;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
/* loaded from: classes.dex */
public class ByteString implements Serializable, Comparable<ByteString> {
    public final byte[] data;
    public transient int hashCode;
    public transient String utf8;
    public static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final ByteString EMPTY = of(new byte[0]);

    public ByteString(byte[] bArr) {
        this.data = bArr;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a3, code lost:
        r5 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static okio.ByteString decodeBase64(java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.decodeBase64(java.lang.String):okio.ByteString");
    }

    public static ByteString decodeHex(String str) {
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = (byte) (decodeHexDigit(str.charAt(i2 + 1)) + (decodeHexDigit(str.charAt(i2)) << 4));
            }
            return of(bArr);
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Unexpected hex string: ", str));
    }

    public static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        char c2 = 'a';
        if (c < 'a' || c > 'f') {
            c2 = 'A';
            if (c < 'A' || c > 'F') {
                throw new IllegalArgumentException("Unexpected hex digit: " + c);
            }
        }
        return (c - c2) + 10;
    }

    public static ByteString encodeUtf8(String str) {
        if (str != null) {
            ByteString byteString = new ByteString(str.getBytes(Util.UTF_8));
            byteString.utf8 = str;
            return byteString;
        }
        throw new IllegalArgumentException("s == null");
    }

    public static ByteString of(byte... bArr) {
        if (bArr != null) {
            return new ByteString((byte[]) bArr.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public String base64() {
        byte[] bArr = this.data;
        byte[] bArr2 = Base64.MAP;
        byte[] bArr3 = new byte[((bArr.length + 2) / 3) * 4];
        int length = bArr.length - (bArr.length % 3);
        int i = 0;
        for (int i2 = 0; i2 < length; i2 += 3) {
            int i3 = i + 1;
            bArr3[i] = bArr2[(bArr[i2] & 255) >> 2];
            int i4 = i3 + 1;
            int i5 = i2 + 1;
            bArr3[i3] = bArr2[((bArr[i2] & 3) << 4) | ((bArr[i5] & 255) >> 4)];
            int i6 = i4 + 1;
            int i7 = i2 + 2;
            bArr3[i4] = bArr2[((bArr[i5] & 15) << 2) | ((bArr[i7] & 255) >> 6)];
            i = i6 + 1;
            bArr3[i6] = bArr2[bArr[i7] & 63];
        }
        int length2 = bArr.length % 3;
        if (length2 == 1) {
            int i8 = i + 1;
            bArr3[i] = bArr2[(bArr[length] & 255) >> 2];
            int i9 = i8 + 1;
            bArr3[i8] = bArr2[(bArr[length] & 3) << 4];
            bArr3[i9] = 61;
            bArr3[i9 + 1] = 61;
        } else if (length2 == 2) {
            int i10 = i + 1;
            bArr3[i] = bArr2[(bArr[length] & 255) >> 2];
            int i11 = i10 + 1;
            int i12 = length + 1;
            bArr3[i10] = bArr2[((bArr[i12] & 255) >> 4) | ((bArr[length] & 3) << 4)];
            bArr3[i11] = bArr2[(bArr[i12] & 15) << 2];
            bArr3[i11 + 1] = 61;
        }
        try {
            return new String(bArr3, "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0029 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027 A[ORIG_RETURN, RETURN] */
    @Override // java.lang.Comparable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int compareTo(okio.ByteString r10) {
        /*
            r9 = this;
            okio.ByteString r10 = (okio.ByteString) r10
            int r0 = r9.size()
            int r1 = r10.size()
            int r2 = java.lang.Math.min(r0, r1)
            r3 = 0
            r4 = 0
        L_0x0010:
            r5 = -1
            r6 = 1
            if (r4 >= r2) goto L_0x002b
            byte r7 = r9.getByte(r4)
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r8 = r10.getByte(r4)
            r8 = r8 & 255(0xff, float:3.57E-43)
            if (r7 != r8) goto L_0x0025
            int r4 = r4 + 1
            goto L_0x0010
        L_0x0025:
            if (r7 >= r8) goto L_0x0029
        L_0x0027:
            r3 = -1
            goto L_0x0031
        L_0x0029:
            r3 = 1
            goto L_0x0031
        L_0x002b:
            if (r0 != r1) goto L_0x002e
            goto L_0x0031
        L_0x002e:
            if (r0 >= r1) goto L_0x0029
            goto L_0x0027
        L_0x0031:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ByteString.compareTo(java.lang.Object):int");
    }

    public final ByteString digest(String str) {
        try {
            return of(MessageDigest.getInstance(str).digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            int size = byteString.size();
            byte[] bArr = this.data;
            if (size == bArr.length && byteString.rangeEquals(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public byte getByte(int i) {
        return this.data[i];
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int hashCode = Arrays.hashCode(this.data);
        this.hashCode = hashCode;
        return hashCode;
    }

    public String hex() {
        byte[] bArr = this.data;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(b >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b & 15];
        }
        return new String(cArr);
    }

    public byte[] internalArray() {
        return this.data;
    }

    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        return byteString.rangeEquals(i2, this.data, i, i3);
    }

    public ByteString sha1() {
        return digest("SHA-1");
    }

    public int size() {
        return this.data.length;
    }

    public ByteString substring(int i, int i2) {
        if (i >= 0) {
            byte[] bArr = this.data;
            if (i2 <= bArr.length) {
                int i3 = i2 - i;
                if (i3 < 0) {
                    throw new IllegalArgumentException("endIndex < beginIndex");
                } else if (i == 0 && i2 == bArr.length) {
                    return this;
                } else {
                    byte[] bArr2 = new byte[i3];
                    System.arraycopy(bArr, i, bArr2, 0, i3);
                    return new ByteString(bArr2);
                }
            } else {
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline10(GeneratedOutlineSupport.outline13("endIndex > length("), this.data.length, ")"));
            }
        } else {
            throw new IllegalArgumentException("beginIndex < 0");
        }
    }

    public ByteString toAsciiLowercase() {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            byte b = bArr[i];
            if (b < 65 || b > 90) {
                i++;
            } else {
                byte[] bArr2 = (byte[]) bArr.clone();
                bArr2[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArr2.length; i2++) {
                    byte b2 = bArr2[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArr2[i2] = (byte) (b2 + 32);
                    }
                }
                return new ByteString(bArr2);
            }
        }
    }

    public byte[] toByteArray() {
        return (byte[]) this.data.clone();
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String utf8 = utf8();
        int length = utf8.length();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= length) {
                i = utf8.length();
                break;
            } else if (i2 == 64) {
                break;
            } else {
                int codePointAt = utf8.codePointAt(i);
                if ((!Character.isISOControl(codePointAt) || codePointAt == 10 || codePointAt == 13) && codePointAt != 65533) {
                    i2++;
                    i += Character.charCount(codePointAt);
                }
            }
        }
        i = -1;
        if (i != -1) {
            String replace = utf8.substring(0, i).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (i >= utf8.length()) {
                return GeneratedOutlineSupport.outline9("[text=", replace, "]");
            }
            StringBuilder outline13 = GeneratedOutlineSupport.outline13("[size=");
            outline13.append(this.data.length);
            outline13.append(" text=");
            outline13.append(replace);
            outline13.append("…]");
            return outline13.toString();
        } else if (this.data.length <= 64) {
            StringBuilder outline132 = GeneratedOutlineSupport.outline13("[hex=");
            outline132.append(hex());
            outline132.append("]");
            return outline132.toString();
        } else {
            StringBuilder outline133 = GeneratedOutlineSupport.outline13("[size=");
            outline133.append(this.data.length);
            outline133.append(" hex=");
            outline133.append(substring(0, 64).hex());
            outline133.append("…]");
            return outline133.toString();
        }
    }

    public String utf8() {
        String str = this.utf8;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.data, Util.UTF_8);
        this.utf8 = str2;
        return str2;
    }

    public void write(Buffer buffer) {
        byte[] bArr = this.data;
        buffer.write(bArr, 0, bArr.length);
    }

    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        if (i >= 0) {
            byte[] bArr2 = this.data;
            if (i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && Util.arrayRangeEquals(bArr2, i, bArr, i2, i3)) {
                return true;
            }
        }
        return false;
    }
}
