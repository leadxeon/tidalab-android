package okhttp3;

import com.android.tools.r8.GeneratedOutlineSupport;
import com.reactnativecommunity.webview.RNCWebViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes.dex */
public enum TlsVersion {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");
    
    public final String javaName;

    TlsVersion(String str) {
        this.javaName = str;
    }

    public static TlsVersion forJavaName(String str) {
        str.hashCode();
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -503070503:
                if (str.equals("TLSv1.1")) {
                    c = 0;
                    break;
                }
                break;
            case -503070502:
                if (str.equals("TLSv1.2")) {
                    c = 1;
                    break;
                }
                break;
            case -503070501:
                if (str.equals("TLSv1.3")) {
                    c = 2;
                    break;
                }
                break;
            case 79201641:
                if (str.equals("SSLv3")) {
                    c = 3;
                    break;
                }
                break;
            case 79923350:
                if (str.equals("TLSv1")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return TLS_1_1;
            case 1:
                return TLS_1_2;
            case 2:
                return TLS_1_3;
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                return SSL_3_0;
            case 4:
                return TLS_1_0;
            default:
                throw new IllegalArgumentException(GeneratedOutlineSupport.outline8("Unexpected TLS version: ", str));
        }
    }

    public static List<TlsVersion> forJavaNames(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(forJavaName(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String javaName() {
        return this.javaName;
    }
}
