package kotlinx.serialization.json.internal;
/* compiled from: StringOps.kt */
/* loaded from: classes.dex */
public final class StringOpsKt {
    public static final byte[] ESCAPE_MARKERS;
    public static final String[] ESCAPE_STRINGS;

    static {
        String[] strArr = new String[93];
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            char hexChar = toHexChar(i2 >> 12);
            char hexChar2 = toHexChar(i2 >> 8);
            char hexChar3 = toHexChar(i2 >> 4);
            char hexChar4 = toHexChar(i2);
            strArr[i2] = "\\u" + hexChar + hexChar2 + hexChar3 + hexChar4;
            if (i3 > 31) {
                break;
            }
            i2 = i3;
        }
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
        ESCAPE_STRINGS = strArr;
        byte[] bArr = new byte[93];
        while (true) {
            int i4 = i + 1;
            bArr[i] = 1;
            if (i4 > 31) {
                bArr[34] = (byte) 34;
                bArr[92] = (byte) 92;
                bArr[9] = (byte) 116;
                bArr[8] = (byte) 98;
                bArr[10] = (byte) 110;
                bArr[13] = (byte) 114;
                bArr[12] = (byte) 102;
                ESCAPE_MARKERS = bArr;
                return;
            }
            i = i4;
        }
    }

    public static final void printQuoted(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length() - 1;
        int i = 0;
        if (length >= 0) {
            int i2 = 0;
            while (true) {
                int i3 = i + 1;
                char charAt = str.charAt(i);
                String[] strArr = ESCAPE_STRINGS;
                if (charAt < strArr.length && strArr[charAt] != null) {
                    sb.append((CharSequence) str, i2, i);
                    sb.append(strArr[charAt]);
                    i2 = i3;
                }
                if (i3 > length) {
                    break;
                }
                i = i3;
            }
            i = i2;
        }
        if (i != 0) {
            sb.append((CharSequence) str, i, str.length());
        } else {
            sb.append(str);
        }
        sb.append('\"');
    }

    public static final char toHexChar(int i) {
        int i2 = i & 15;
        return (char) (i2 < 10 ? i2 + 48 : (i2 - 10) + 97);
    }
}
