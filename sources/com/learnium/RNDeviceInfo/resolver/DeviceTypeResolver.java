package com.learnium.RNDeviceInfo.resolver;

import android.app.UiModeManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
/* loaded from: classes.dex */
public class DeviceTypeResolver {
    public final Context context;

    public DeviceTypeResolver(Context context) {
        this.context = context;
    }

    public int getDeviceType$enumunboxing$() {
        if (this.context.getPackageManager().hasSystemFeature("amazon.hardware.fire_tv")) {
            return 3;
        }
        UiModeManager uiModeManager = (UiModeManager) this.context.getSystemService("uimode");
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
            return 3;
        }
        int i = this.context.getResources().getConfiguration().smallestScreenWidthDp;
        int i2 = i == 0 ? 4 : i >= 600 ? 2 : 1;
        if (i2 != 4) {
            return i2;
        }
        WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
        if (windowManager == null) {
            return 4;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        double sqrt = Math.sqrt(Math.pow(displayMetrics.heightPixels / displayMetrics.ydpi, 2.0d) + Math.pow(displayMetrics.widthPixels / displayMetrics.xdpi, 2.0d));
        if (sqrt < 3.0d || sqrt > 6.9d) {
            return (sqrt <= 6.9d || sqrt > 18.0d) ? 4 : 2;
        }
        return 1;
    }
}
