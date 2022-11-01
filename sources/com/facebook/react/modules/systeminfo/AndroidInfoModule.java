package com.facebook.react.modules.systeminfo;

import android.annotation.SuppressLint;
import android.app.UiModeManager;
import android.os.Build;
import android.provider.Settings;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.HashMap;
import java.util.Map;
@ReactModule(name = AndroidInfoModule.NAME)
@SuppressLint({"HardwareIds"})
/* loaded from: classes.dex */
public class AndroidInfoModule extends ReactContextBaseJavaModule implements TurboModule {
    private static final String IS_TESTING = "IS_TESTING";
    public static final String NAME = "PlatformConstants";

    public AndroidInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x007d, code lost:
        if (r3 == null) goto L_0x0082;
     */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cb A[Catch: all -> 0x00cf, TRY_ENTER, TryCatch #9 {, blocks: (B:4:0x001e, B:15:0x0056, B:26:0x0078, B:29:0x007f, B:30:0x0082, B:46:0x00c4, B:49:0x00cb, B:50:0x00ce), top: B:63:0x001e }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00c4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String getServerHost() {
        /*
            Method dump skipped, instructions count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoModule.getServerHost():java.lang.String");
    }

    private Boolean isRunningScreenshotTest() {
        try {
            Class.forName("com.facebook.testing.react.screenshots.ReactAppScreenshotTestActivity");
            return Boolean.TRUE;
        } catch (ClassNotFoundException unused) {
            return Boolean.FALSE;
        }
    }

    private String uiMode() {
        int currentModeType = ((UiModeManager) getReactApplicationContext().getSystemService("uimode")).getCurrentModeType();
        return currentModeType != 1 ? currentModeType != 2 ? currentModeType != 3 ? currentModeType != 4 ? currentModeType != 6 ? "unknown" : "watch" : "tv" : "car" : "desk" : "normal";
    }

    @ReactMethod(isBlockingSynchronousMethod = true)
    public String getAndroidID() {
        return Settings.Secure.getString(getReactApplicationContext().getContentResolver(), "android_id");
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap hashMap = new HashMap();
        hashMap.put("Version", Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("Release", Build.VERSION.RELEASE);
        hashMap.put("Serial", Build.SERIAL);
        hashMap.put("Fingerprint", Build.FINGERPRINT);
        hashMap.put("Model", Build.MODEL);
        hashMap.put("isTesting", Boolean.valueOf("true".equals(System.getProperty(IS_TESTING)) || isRunningScreenshotTest().booleanValue()));
        hashMap.put("reactNativeVersion", ReactNativeVersion.VERSION);
        hashMap.put("uiMode", uiMode());
        return hashMap;
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public void invalidate() {
    }
}
