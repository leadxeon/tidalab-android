package com.facebook.react.common;

import com.reactnativecommunity.webview.RNCWebViewManager;
import java.nio.charset.Charset;
/* loaded from: classes.dex */
public class StandardCharsets {
    public static final Charset UTF_8 = Charset.forName(RNCWebViewManager.HTML_ENCODING);

    static {
        Charset.forName("UTF-16");
        Charset.forName("UTF-16BE");
        Charset.forName("UTF-16LE");
    }
}
