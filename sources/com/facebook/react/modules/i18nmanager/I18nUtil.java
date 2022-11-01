package com.facebook.react.modules.i18nmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.core.text.TextUtilsCompat;
import java.util.Locale;
/* loaded from: classes.dex */
public class I18nUtil {
    public static I18nUtil sharedI18nUtilInstance;

    public static I18nUtil getInstance() {
        if (sharedI18nUtilInstance == null) {
            sharedI18nUtilInstance = new I18nUtil();
        }
        return sharedI18nUtilInstance;
    }

    public boolean doLeftAndRightSwapInRTL(Context context) {
        return context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).getBoolean("RCTI18nUtil_makeRTLFlipLeftAndRightStyles", true);
    }

    public boolean isRTL(Context context) {
        if (context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).getBoolean("RCTI18nUtil_forceRTL", false)) {
            return true;
        }
        if (!context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).getBoolean("RCTI18nUtil_allowRTL", true)) {
            return false;
        }
        Locale locale = Locale.getDefault();
        Locale locale2 = TextUtilsCompat.ROOT;
        return TextUtils.getLayoutDirectionFromLocale(locale) == 1;
    }

    public final void setPref(Context context, String str, boolean z) {
        SharedPreferences.Editor edit = context.getSharedPreferences("com.facebook.react.modules.i18nmanager.I18nUtil", 0).edit();
        edit.putBoolean(str, z);
        edit.apply();
    }
}
