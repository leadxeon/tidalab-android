package com.tidalab.v2board.clash.service;

import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.app.NotificationManagerCompat;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.service.clash.ClashRuntime;
import com.tidalab.v2board.clash.service.clash.ClashRuntimeKt;
import com.tidalab.v2board.clash.service.clash.ClashRuntimeKt$clashRuntime$1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.sync.Mutex;
/* compiled from: ClashService.kt */
/* loaded from: classes.dex */
public final class ClashService extends BaseService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public String reason;
    public final ClashRuntime runtime;

    public ClashService() {
        ClashService$runtime$1 clashService$runtime$1 = new ClashService$runtime$1(this, null);
        Mutex mutex = ClashRuntimeKt.globalLock;
        this.runtime = new ClashRuntimeKt$clashRuntime$1(this, clashService$runtime$1);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (StatusProvider.serviceRunning) {
            stopSelf();
            return;
        }
        StatusProvider.setServiceRunning(true);
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            NotificationManagerCompat notificationManagerCompat = new NotificationManagerCompat(this);
            NotificationChannel notificationChannel = new NotificationChannel("clash_status_channel", getText(R.string.clash_service_status_channel), 2);
            if (i >= 26) {
                notificationManagerCompat.mNotificationManager.createNotificationChannel(notificationChannel);
            }
        }
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(this, "clash_status_channel");
        notificationCompat$Builder.mNotification.icon = R.drawable.ic_logo_service;
        notificationCompat$Builder.setFlag(2, true);
        notificationCompat$Builder.mColor = PathParser.getColorCompat(this, R.color.color_clash);
        notificationCompat$Builder.setFlag(8, true);
        notificationCompat$Builder.mShowWhen = false;
        notificationCompat$Builder.setContentTitle(getText(R.string.loading));
        startForeground(R.id.nf_clash_status, notificationCompat$Builder.build());
        this.runtime.launch();
    }

    @Override // com.tidalab.v2board.clash.service.BaseService, android.app.Service
    public void onDestroy() {
        StatusProvider.setServiceRunning(false);
        String str = this.reason;
        Intents intents = Intents.INSTANCE;
        InputKt.sendBroadcastSelf(this, new Intent(Intents.ACTION_CLASH_STOPPED).putExtra("stop_reason", str));
        InputKt.cancelAndJoinBlocking(this);
        String str2 = this.reason;
        if (str2 == null) {
            str2 = "successfully";
        }
        Log.i("ClashForAndroid", Intrinsics.stringPlus("ClashService destroyed: ", str2), null);
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Intents intents = Intents.INSTANCE;
        InputKt.sendBroadcastSelf(this, new Intent(Intents.ACTION_CLASH_STARTED));
        return 1;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        this.runtime.requestGc();
    }
}
