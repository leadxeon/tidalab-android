package androidx.webkit.internal;

import org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface;
/* loaded from: classes.dex */
public class IncompatibleApkWebViewProviderFactory implements WebViewProviderFactory {
    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    @Override // androidx.webkit.internal.WebViewProviderFactory
    public String[] getWebViewFeatures() {
        return EMPTY_STRING_ARRAY;
    }

    @Override // androidx.webkit.internal.WebViewProviderFactory
    public WebkitToCompatConverterBoundaryInterface getWebkitToCompatConverter() {
        throw new UnsupportedOperationException("This should never happen, if this method was called it means we're trying to reach into WebView APK code on an incompatible device. This most likely means the current method is being called too early, or is being called on start-up rather than lazily");
    }
}
