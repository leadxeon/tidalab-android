package androidx.webkit.internal;

import org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface;
/* loaded from: classes.dex */
public interface WebViewProviderFactory {
    String[] getWebViewFeatures();

    WebkitToCompatConverterBoundaryInterface getWebkitToCompatConverter();
}
