package okhttp3.internal.http;

import com.reactnativecommunity.webview.RNCWebViewManager;
/* loaded from: classes.dex */
public final class HttpMethod {
    private HttpMethod() {
    }

    public static boolean invalidatesCache(String str) {
        return str.equals(RNCWebViewManager.HTTP_METHOD_POST) || str.equals("PATCH") || str.equals("PUT") || str.equals("DELETE") || str.equals("MOVE");
    }

    public static boolean permitsRequestBody(String str) {
        return !str.equals("GET") && !str.equals("HEAD");
    }

    public static boolean redirectsToGet(String str) {
        return !str.equals("PROPFIND");
    }

    public static boolean redirectsWithBody(String str) {
        return str.equals("PROPFIND");
    }

    public static boolean requiresRequestBody(String str) {
        return str.equals(RNCWebViewManager.HTTP_METHOD_POST) || str.equals("PUT") || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }
}
