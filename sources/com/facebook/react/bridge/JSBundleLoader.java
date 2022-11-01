package com.facebook.react.bridge;

import android.content.Context;
import android.net.Uri;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.common.DebugServerException;
import okhttp3.HttpUrl;
/* loaded from: classes.dex */
public abstract class JSBundleLoader {
    public static JSBundleLoader createAssetLoader(final Context context, final String str, final boolean z) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.1
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.loadScriptFromAssets(context.getAssets(), str, z);
                return str;
            }
        };
    }

    public static JSBundleLoader createCachedBundleFromNetworkLoader(final String str, final String str2) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.3
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                try {
                    jSBundleLoaderDelegate.loadScriptFromFile(str2, str, false);
                    return str;
                } catch (Exception e) {
                    String str3 = str;
                    String message = e.getMessage();
                    int i = DebugServerException.$r8$clinit;
                    throw new DebugServerException(GeneratedOutlineSupport.outline9(message, "\n\nTry the following to fix the issue:\n• Ensure that the packager server is running\n• Ensure that your device/emulator is connected to your machine and has USB debugging enabled - run 'adb devices' to see a list of connected devices\n• Ensure Airplane Mode is disabled\n• If you're on a physical device connected to the same machine, run 'adb reverse tcp:<PORT> tcp:<PORT>' to forward requests from your device\n• If your device is on the same Wi-Fi network, set 'Debug server host & port for device' in 'Dev settings' to your machine's IP address and the port of the local dev server - e.g. 10.0.1.1:<PORT>\n\n".replace("<PORT>", String.valueOf(Uri.parse(str3).getPort())), HttpUrl.FRAGMENT_ENCODE_SET), e);
                }
            }
        };
    }

    public static JSBundleLoader createFileLoader(String str) {
        return createFileLoader(str, str, false);
    }

    public static JSBundleLoader createRemoteDebuggerBundleLoader(final String str, final String str2) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.4
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.setSourceURLs(str2, str);
                return str2;
            }
        };
    }

    public abstract String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate);

    public static JSBundleLoader createFileLoader(final String str, final String str2, final boolean z) {
        return new JSBundleLoader() { // from class: com.facebook.react.bridge.JSBundleLoader.2
            @Override // com.facebook.react.bridge.JSBundleLoader
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.loadScriptFromFile(str, str2, z);
                return str;
            }
        };
    }
}
