package androidx.webkit.internal;

import androidx.recyclerview.R$dimen;
import java.lang.reflect.InvocationTargetException;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;
/* loaded from: classes.dex */
public class WebViewGlueCommunicator$LAZY_FACTORY_HOLDER {
    public static final WebViewProviderFactory INSTANCE;

    static {
        WebViewProviderFactory webViewProviderFactory;
        try {
            webViewProviderFactory = new WebViewProviderFactoryAdapter((WebViewProviderFactoryBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(WebViewProviderFactoryBoundaryInterface.class, R$dimen.fetchGlueProviderFactoryImpl()));
        } catch (ClassNotFoundException unused) {
            webViewProviderFactory = new IncompatibleApkWebViewProviderFactory();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
        INSTANCE = webViewProviderFactory;
    }
}
