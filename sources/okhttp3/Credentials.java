package okhttp3;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.nio.charset.Charset;
import okhttp3.internal.Util;
import okio.ByteString;
/* loaded from: classes.dex */
public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        return basic(str, str2, Util.ISO_8859_1);
    }

    public static String basic(String str, String str2, Charset charset) {
        String outline9 = GeneratedOutlineSupport.outline9(str, ":", str2);
        char[] cArr = ByteString.HEX_DIGITS;
        if (outline9 == null) {
            throw new IllegalArgumentException("s == null");
        } else if (charset != null) {
            return GeneratedOutlineSupport.outline8("Basic ", new ByteString(outline9.getBytes(charset)).base64());
        } else {
            throw new IllegalArgumentException("charset == null");
        }
    }
}
