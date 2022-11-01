package kotlinx.serialization.json.internal;

import java.util.Objects;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: JsonLexer.kt */
/* loaded from: classes.dex */
public final class CharMappings {
    public static final CharMappings INSTANCE;
    public static final char[] ESCAPE_2_CHAR = new char[117];
    public static final byte[] CHAR_TO_TOKEN = new byte[WebSocketProtocol.PAYLOAD_SHORT];

    static {
        CharMappings charMappings = new CharMappings();
        INSTANCE = charMappings;
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            charMappings.initC2ESC(i2, 'u');
            if (i3 > 31) {
                break;
            }
            i2 = i3;
        }
        charMappings.initC2ESC(8, 'b');
        charMappings.initC2ESC(9, 't');
        charMappings.initC2ESC(10, 'n');
        charMappings.initC2ESC(12, 'f');
        charMappings.initC2ESC(13, 'r');
        charMappings.initC2ESC(47, '/');
        charMappings.initC2ESC(34, '\"');
        charMappings.initC2ESC(92, '\\');
        Objects.requireNonNull(INSTANCE);
        while (true) {
            int i4 = i + 1;
            byte[] bArr = CHAR_TO_TOKEN;
            bArr[i] = Byte.MAX_VALUE;
            if (i4 > 32) {
                bArr[9] = 3;
                bArr[10] = 3;
                bArr[13] = 3;
                bArr[32] = 3;
                bArr[44] = 4;
                bArr[58] = 5;
                bArr[123] = 6;
                bArr[125] = 7;
                bArr[91] = 8;
                bArr[93] = 9;
                bArr[34] = 1;
                bArr[92] = 2;
                return;
            }
            i = i4;
        }
    }

    public final void initC2ESC(int i, char c) {
        if (c != 'u') {
            ESCAPE_2_CHAR[c] = (char) i;
        }
    }
}
