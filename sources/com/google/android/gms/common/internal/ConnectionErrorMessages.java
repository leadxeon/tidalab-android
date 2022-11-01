package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.facebook.react.R$style;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.reactnativecommunity.webview.RNCWebViewManager;
import com.tidalab.v2board.clash.foss.R;
import okhttp3.internal.ws.WebSocketProtocol;
/* loaded from: classes.dex */
public final class ConnectionErrorMessages {
    public static final SimpleArrayMap<String, String> zaog = new SimpleArrayMap<>();

    public static String getAppName(Context context) {
        String packageName = context.getPackageName();
        try {
            PackageManagerWrapper packageManager = Wrappers.packageManager(context);
            return packageManager.zzhx.getPackageManager().getApplicationLabel(packageManager.zzhx.getPackageManager().getApplicationInfo(packageName, 0)).toString();
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            String str = context.getApplicationInfo().name;
            return TextUtils.isEmpty(str) ? packageName : str;
        }
    }

    public static String getErrorMessage(Context context, int i) {
        Resources resources = context.getResources();
        String appName = getAppName(context);
        if (i == 1) {
            return resources.getString(R.string.common_google_play_services_install_text, appName);
        }
        if (i != 2) {
            if (i == 3) {
                return resources.getString(R.string.common_google_play_services_enable_text, appName);
            }
            if (i == 5) {
                return zaa(context, "common_google_play_services_invalid_account_text", appName);
            }
            if (i == 7) {
                return zaa(context, "common_google_play_services_network_error_text", appName);
            }
            if (i == 9) {
                return resources.getString(R.string.common_google_play_services_unsupported_text, appName);
            }
            if (i == 20) {
                return zaa(context, "common_google_play_services_restricted_profile_text", appName);
            }
            switch (i) {
                case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                    return zaa(context, "common_google_play_services_api_unavailable_text", appName);
                case 17:
                    return zaa(context, "common_google_play_services_sign_in_failed_text", appName);
                case 18:
                    return resources.getString(R.string.common_google_play_services_updating_text, appName);
                default:
                    return resources.getString(R.string.common_google_play_services_unknown_issue, appName);
            }
        } else if (R$style.isWearableWithoutPlayStore(context)) {
            return resources.getString(R.string.common_google_play_services_wear_update_text);
        } else {
            return resources.getString(R.string.common_google_play_services_update_text, appName);
        }
    }

    public static String getErrorTitle(Context context, int i) {
        Resources resources = context.getResources();
        switch (i) {
            case 1:
                return resources.getString(R.string.common_google_play_services_install_title);
            case 2:
                return resources.getString(R.string.common_google_play_services_update_title);
            case RNCWebViewManager.COMMAND_RELOAD /* 3 */:
                return resources.getString(R.string.common_google_play_services_enable_title);
            case 4:
            case 6:
            case 18:
                return null;
            case 5:
                Log.e("GoogleApiAvailability", "An invalid account was specified when connecting. Please provide a valid account.");
                return zaa(context, "common_google_play_services_invalid_account_title");
            case 7:
                Log.e("GoogleApiAvailability", "Network error occurred. Please retry request later.");
                return zaa(context, "common_google_play_services_network_error_title");
            case 8:
                Log.e("GoogleApiAvailability", "Internal error occurred. Please see logs for detailed information");
                return null;
            case WebSocketProtocol.OPCODE_CONTROL_PING /* 9 */:
                Log.e("GoogleApiAvailability", "Google Play services is invalid. Cannot recover.");
                return null;
            case 10:
                Log.e("GoogleApiAvailability", "Developer error occurred. Please see logs for detailed information");
                return null;
            case 11:
                Log.e("GoogleApiAvailability", "The application is not licensed to the user.");
                return null;
            case 12:
            case 13:
            case 14:
            case WebSocketProtocol.B0_MASK_OPCODE /* 15 */:
            case 19:
            default:
                StringBuilder sb = new StringBuilder(33);
                sb.append("Unexpected error code ");
                sb.append(i);
                Log.e("GoogleApiAvailability", sb.toString());
                return null;
            case WebSocketProtocol.B0_FLAG_RSV3 /* 16 */:
                Log.e("GoogleApiAvailability", "One of the API components you attempted to connect to is not available.");
                return null;
            case 17:
                Log.e("GoogleApiAvailability", "The specified account could not be signed in.");
                return zaa(context, "common_google_play_services_sign_in_failed_title");
            case 20:
                Log.e("GoogleApiAvailability", "The current user profile is restricted and could not use authenticated features.");
                return zaa(context, "common_google_play_services_restricted_profile_title");
        }
    }

    public static String zaa(Context context, String str, String str2) {
        Resources resources = context.getResources();
        String zaa = zaa(context, str);
        if (zaa == null) {
            zaa = resources.getString(R.string.common_google_play_services_unknown_issue);
        }
        return String.format(resources.getConfiguration().locale, zaa, str2);
    }

    public static String zaa(Context context, String str) {
        Resources resources;
        SimpleArrayMap<String, String> simpleArrayMap = zaog;
        synchronized (simpleArrayMap) {
            String orDefault = simpleArrayMap.getOrDefault(str, null);
            if (orDefault != null) {
                return orDefault;
            }
            int i = GooglePlayServicesUtil.$r8$clinit;
            try {
                resources = context.getPackageManager().getResourcesForApplication("com.google.android.gms");
            } catch (PackageManager.NameNotFoundException unused) {
                resources = null;
            }
            if (resources == null) {
                return null;
            }
            int identifier = resources.getIdentifier(str, "string", "com.google.android.gms");
            if (identifier == 0) {
                String valueOf = String.valueOf(str);
                Log.w("GoogleApiAvailability", valueOf.length() != 0 ? "Missing resource: ".concat(valueOf) : new String("Missing resource: "));
                return null;
            }
            String string = resources.getString(identifier);
            if (TextUtils.isEmpty(string)) {
                String valueOf2 = String.valueOf(str);
                Log.w("GoogleApiAvailability", valueOf2.length() != 0 ? "Got empty resource: ".concat(valueOf2) : new String("Got empty resource: "));
                return null;
            }
            zaog.put(str, string);
            return string;
        }
    }
}
