package com.google.android.gms.common;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import androidx.collection.SimpleArrayMap;
import androidx.core.app.NotificationCompat$Action;
import androidx.core.app.NotificationCompat$BigTextStyle;
import androidx.core.app.NotificationCompat$Builder;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.react.R$style;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.internal.zac;
import com.google.android.gms.internal.base.zap;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public class GoogleApiAvailability extends GoogleApiAvailabilityLight {
    public static final Object mLock = new Object();
    public static final GoogleApiAvailability zaao = new GoogleApiAvailability();

    @SuppressLint({"HandlerLeak"})
    /* loaded from: classes.dex */
    public class zaa extends zap {
        public final Context zaaq;

        public zaa(Context context) {
            super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
            this.zaaq = context.getApplicationContext();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            int i = message.what;
            boolean z = true;
            if (i != 1) {
                StringBuilder sb = new StringBuilder(50);
                sb.append("Don't know how to handle this message: ");
                sb.append(i);
                Log.w("GoogleApiAvailability", sb.toString());
                return;
            }
            int isGooglePlayServicesAvailable = GoogleApiAvailability.this.isGooglePlayServicesAvailable(this.zaaq, GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            Objects.requireNonNull(GoogleApiAvailability.this);
            AtomicBoolean atomicBoolean = GooglePlayServicesUtilLight.sCanceledAvailabilityNotification;
            if (!(isGooglePlayServicesAvailable == 1 || isGooglePlayServicesAvailable == 2 || isGooglePlayServicesAvailable == 3 || isGooglePlayServicesAvailable == 9)) {
                z = false;
            }
            if (z) {
                GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.this;
                Context context = this.zaaq;
                Intent errorResolutionIntent = googleApiAvailability.getErrorResolutionIntent(context, isGooglePlayServicesAvailable, "n");
                googleApiAvailability.zaa(context, isGooglePlayServicesAvailable, errorResolutionIntent == null ? null : PendingIntent.getActivity(context, 0, errorResolutionIntent, 134217728));
            }
        }
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public Intent getErrorResolutionIntent(Context context, int i, String str) {
        return super.getErrorResolutionIntent(context, i, str);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public int isGooglePlayServicesAvailable(Context context, int i) {
        return super.isGooglePlayServicesAvailable(context, i);
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2, DialogInterface.OnCancelListener onCancelListener) {
        AlertDialog alertDialog;
        String str;
        zac zacVar = new zac(super.getErrorResolutionIntent(activity, i, "d"), activity, i2);
        if (i == 0) {
            alertDialog = null;
        } else {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            AlertDialog.Builder builder = "Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId)) ? new AlertDialog.Builder(activity, 5) : null;
            if (builder == null) {
                builder = new AlertDialog.Builder(activity);
            }
            builder.setMessage(ConnectionErrorMessages.getErrorMessage(activity, i));
            builder.setOnCancelListener(onCancelListener);
            Resources resources = activity.getResources();
            if (i == 1) {
                str = resources.getString(R.string.common_google_play_services_install_button);
            } else if (i == 2) {
                str = resources.getString(R.string.common_google_play_services_update_button);
            } else if (i != 3) {
                str = resources.getString(17039370);
            } else {
                str = resources.getString(R.string.common_google_play_services_enable_button);
            }
            if (str != null) {
                builder.setPositiveButton(str, zacVar);
            }
            String errorTitle = ConnectionErrorMessages.getErrorTitle(activity, i);
            if (errorTitle != null) {
                builder.setTitle(errorTitle);
            }
            alertDialog = builder.create();
        }
        if (alertDialog == null) {
            return false;
        }
        if (activity instanceof FragmentActivity) {
            FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            SupportErrorDialogFragment supportErrorDialogFragment = new SupportErrorDialogFragment();
            R$style.checkNotNull(alertDialog, "Cannot display null dialog");
            alertDialog.setOnCancelListener(null);
            alertDialog.setOnDismissListener(null);
            supportErrorDialogFragment.mDialog = alertDialog;
            supportErrorDialogFragment.zaan = onCancelListener;
            supportErrorDialogFragment.show(supportFragmentManager, "GooglePlayServicesErrorDialog");
        } else {
            android.app.FragmentManager fragmentManager = activity.getFragmentManager();
            ErrorDialogFragment errorDialogFragment = new ErrorDialogFragment();
            R$style.checkNotNull(alertDialog, "Cannot display null dialog");
            alertDialog.setOnCancelListener(null);
            alertDialog.setOnDismissListener(null);
            errorDialogFragment.mDialog = alertDialog;
            errorDialogFragment.zaan = onCancelListener;
            errorDialogFragment.show(fragmentManager, "GooglePlayServicesErrorDialog");
        }
        return true;
    }

    @TargetApi(20)
    public final void zaa(Context context, int i, PendingIntent pendingIntent) {
        String str;
        String str2;
        int i2;
        if (i == 18) {
            new zaa(context).sendEmptyMessageDelayed(1, 120000L);
        } else if (pendingIntent != null) {
            if (i == 6) {
                str = ConnectionErrorMessages.zaa(context, "common_google_play_services_resolution_required_title");
            } else {
                str = ConnectionErrorMessages.getErrorTitle(context, i);
            }
            if (str == null) {
                str = context.getResources().getString(R.string.common_google_play_services_notification_ticker);
            }
            if (i == 6) {
                str2 = ConnectionErrorMessages.zaa(context, "common_google_play_services_resolution_required_text", ConnectionErrorMessages.getAppName(context));
            } else {
                str2 = ConnectionErrorMessages.getErrorMessage(context, i);
            }
            Resources resources = context.getResources();
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(context, null);
            notificationCompat$Builder.mLocalOnly = true;
            notificationCompat$Builder.setFlag(16, true);
            notificationCompat$Builder.setContentTitle(str);
            NotificationCompat$BigTextStyle notificationCompat$BigTextStyle = new NotificationCompat$BigTextStyle();
            notificationCompat$BigTextStyle.mBigText = NotificationCompat$Builder.limitCharSequenceLength(str2);
            notificationCompat$Builder.setStyle(notificationCompat$BigTextStyle);
            if (R$style.isWearable(context)) {
                notificationCompat$Builder.mNotification.icon = context.getApplicationInfo().icon;
                notificationCompat$Builder.mPriority = 2;
                if (R$style.isWearableWithoutPlayStore(context)) {
                    notificationCompat$Builder.mActions.add(new NotificationCompat$Action(R.drawable.common_full_open_on_phone, resources.getString(R.string.common_open_on_phone), pendingIntent));
                } else {
                    notificationCompat$Builder.mContentIntent = pendingIntent;
                }
            } else {
                notificationCompat$Builder.mNotification.icon = 17301642;
                String string = resources.getString(R.string.common_google_play_services_notification_ticker);
                notificationCompat$Builder.mNotification.tickerText = NotificationCompat$Builder.limitCharSequenceLength(string);
                notificationCompat$Builder.mNotification.when = System.currentTimeMillis();
                notificationCompat$Builder.mContentIntent = pendingIntent;
                notificationCompat$Builder.setContentText(str2);
            }
            if (R$style.isAtLeastO()) {
                if (R$style.isAtLeastO()) {
                    synchronized (mLock) {
                    }
                    NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
                    SimpleArrayMap<String, String> simpleArrayMap = ConnectionErrorMessages.zaog;
                    String string2 = context.getResources().getString(R.string.common_google_play_services_notification_channel_name);
                    if (notificationChannel == null) {
                        notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", string2, 4));
                    } else if (!string2.contentEquals(notificationChannel.getName())) {
                        notificationChannel.setName(string2);
                        notificationManager.createNotificationChannel(notificationChannel);
                    }
                    notificationCompat$Builder.mChannelId = "com.google.android.gms.availability";
                } else {
                    throw new IllegalStateException();
                }
            }
            Notification build = notificationCompat$Builder.build();
            if (i == 1 || i == 2 || i == 3) {
                i2 = 10436;
                GooglePlayServicesUtilLight.sCanceledAvailabilityNotification.set(false);
            } else {
                i2 = 39789;
            }
            notificationManager.notify(i2, build);
        } else if (i == 6) {
            Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
        }
    }
}
