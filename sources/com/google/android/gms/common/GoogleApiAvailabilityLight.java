package com.google.android.gms.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.R$style;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class GoogleApiAvailabilityLight {
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;

    static {
        AtomicBoolean atomicBoolean = GooglePlayServicesUtilLight.sCanceledAvailabilityNotification;
    }

    public Intent getErrorResolutionIntent(Context context, int i, String str) {
        if (i == 1 || i == 2) {
            if (context == null || !R$style.isWearableWithoutPlayStore(context)) {
                StringBuilder outline13 = GeneratedOutlineSupport.outline13("gcore_");
                outline13.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
                outline13.append("-");
                if (!TextUtils.isEmpty(str)) {
                    outline13.append(str);
                }
                outline13.append("-");
                if (context != null) {
                    outline13.append(context.getPackageName());
                }
                outline13.append("-");
                if (context != null) {
                    try {
                        PackageManagerWrapper packageManager = Wrappers.packageManager(context);
                        outline13.append(packageManager.zzhx.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                String sb = outline13.toString();
                int i2 = zzg.$r8$clinit;
                Intent intent = new Intent("android.intent.action.VIEW");
                Uri.Builder appendQueryParameter = Uri.parse("market://details").buildUpon().appendQueryParameter("id", "com.google.android.gms");
                if (!TextUtils.isEmpty(sb)) {
                    appendQueryParameter.appendQueryParameter("pcampaignid", sb);
                }
                intent.setData(appendQueryParameter.build());
                intent.setPackage("com.android.vending");
                intent.addFlags(524288);
                return intent;
            }
            int i3 = zzg.$r8$clinit;
            Intent intent2 = new Intent("com.google.android.clockwork.home.UPDATE_ANDROID_WEAR_ACTION");
            intent2.setPackage("com.google.android.wearable.app");
            return intent2;
        } else if (i != 3) {
            return null;
        } else {
            int i4 = zzg.$r8$clinit;
            Uri fromParts = Uri.fromParts("package", "com.google.android.gms", null);
            Intent intent3 = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent3.setData(fromParts);
            return intent3;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:2|(2:130|3)|5|(4:9|2b|24|(2:26|(2:28|29))(2:30|31))|35|(4:37|(3:39|(1:44)(1:43)|45)|46|(14:48|(1:51)(1:52)|53|(2:125|55)|119|57|58|102|72|(1:74)(1:(7:82|(1:84)(1:85)|(1:87)|(1:89)(4:90|(2:121|92)|95|(1:97)(1:98))|104|(2:(5:107|127|108|109|(2:110|(2:112|(1:134))(2:135|114)))|115)(0)|(1:117)(1:136))(1:80))|81|104|(0)(0)|(0)(0)))|49|(0)(0)|53|(0)|119|57|58|102) */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x01a9, code lost:
        android.util.Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
     */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01b6  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01f1 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0103 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x00e7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:136:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00dc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int isGooglePlayServicesAvailable(android.content.Context r11, int r12) {
        /*
            Method dump skipped, instructions count: 500
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.GoogleApiAvailabilityLight.isGooglePlayServicesAvailable(android.content.Context, int):int");
    }
}
