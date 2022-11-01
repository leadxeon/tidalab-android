package kotlin.text;

import com.reactnativecommunity.webview.RNCWebViewManager;
import java.nio.charset.Charset;
/* compiled from: Charsets.kt */
/* loaded from: classes.dex */
public final class Charsets {
    public static final Charset UTF_8 = Charset.forName(RNCWebViewManager.HTML_ENCODING);

    static {
        Charset.forName("UTF-16");
        Charset.forName("UTF-16BE");
        Charset.forName("UTF-16LE");
        Charset.forName("US-ASCII");
        Charset.forName("ISO-8859-1");
    }
}
