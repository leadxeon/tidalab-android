package okhttp3.internal.http;

import com.android.tools.r8.GeneratedOutlineSupport;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Response;
/* loaded from: classes.dex */
public final class StatusLine {
    public static final int HTTP_CONTINUE = 100;
    public static final int HTTP_PERM_REDIRECT = 308;
    public static final int HTTP_TEMP_REDIRECT = 307;
    public final int code;
    public final String message;
    public final Protocol protocol;

    public StatusLine(Protocol protocol, int i, String str) {
        this.protocol = protocol;
        this.code = i;
        this.message = str;
    }

    public static StatusLine get(Response response) {
        return new StatusLine(response.protocol(), response.code(), response.message());
    }

    public static StatusLine parse(String str) throws IOException {
        Protocol protocol;
        String str2;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException(GeneratedOutlineSupport.outline8("Unexpected status line: ", str));
            }
            int charAt = str.charAt(7) - '0';
            if (charAt == 0) {
                protocol = Protocol.HTTP_1_0;
            } else if (charAt == 1) {
                protocol = Protocol.HTTP_1_1;
            } else {
                throw new ProtocolException(GeneratedOutlineSupport.outline8("Unexpected status line: ", str));
            }
        } else if (str.startsWith("ICY ")) {
            protocol = Protocol.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException(GeneratedOutlineSupport.outline8("Unexpected status line: ", str));
        }
        int i2 = i + 3;
        if (str.length() >= i2) {
            try {
                int parseInt = Integer.parseInt(str.substring(i, i2));
                if (str.length() <= i2) {
                    str2 = HttpUrl.FRAGMENT_ENCODE_SET;
                } else if (str.charAt(i2) == ' ') {
                    str2 = str.substring(i + 4);
                } else {
                    throw new ProtocolException(GeneratedOutlineSupport.outline8("Unexpected status line: ", str));
                }
                return new StatusLine(protocol, parseInt, str2);
            } catch (NumberFormatException unused) {
                throw new ProtocolException(GeneratedOutlineSupport.outline8("Unexpected status line: ", str));
            }
        } else {
            throw new ProtocolException(GeneratedOutlineSupport.outline8("Unexpected status line: ", str));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.code);
        if (this.message != null) {
            sb.append(' ');
            sb.append(this.message);
        }
        return sb.toString();
    }
}
