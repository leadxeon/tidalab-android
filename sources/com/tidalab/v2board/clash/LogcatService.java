package com.tidalab.v2board.clash;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.app.NotificationManagerCompat;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import com.tidalab.v2board.clash.log.LogcatCache;
import com.tidalab.v2board.clash.service.RemoteService;
import java.util.Objects;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
/* compiled from: LogcatService.kt */
/* loaded from: classes.dex */
public final class LogcatService extends Service implements CoroutineScope, IInterface {
    public static boolean running;
    public final /* synthetic */ CoroutineScope $$delegate_0 = InputKt.CoroutineScope(Dispatchers.Default);
    public final LogcatCache cache = new LogcatCache();
    public final LogcatService$connection$1 connection = new ServiceConnection() { // from class: com.tidalab.v2board.clash.LogcatService$connection$1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LogcatService logcatService = LogcatService.this;
            if (iBinder == null) {
                logcatService.stopSelf();
                return;
            }
            boolean z = LogcatService.running;
            Objects.requireNonNull(logcatService);
            if (!iBinder.isBinderAlive()) {
                logcatService.stopSelf();
                return;
            }
            Dispatchers dispatchers = Dispatchers.INSTANCE;
            InputKt.launch$default(logcatService, Dispatchers.IO, null, new LogcatService$startObserver$1(iBinder, logcatService, null), 2, null);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            LogcatService.this.stopSelf();
        }
    };

    /* JADX WARN: Type inference failed for: r0v4, types: [com.tidalab.v2board.clash.LogcatService$connection$1] */
    public LogcatService() {
        Dispatchers dispatchers = Dispatchers.INSTANCE;
    }

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return new LogcatService$asBinder$1(this);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new LogcatService$asBinder$1(this);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        running = true;
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            NotificationManagerCompat notificationManagerCompat = new NotificationManagerCompat(this);
            NotificationChannel notificationChannel = new NotificationChannel("clash_logcat_channel", getString(R.string.clash_logcat), 3);
            if (i >= 26) {
                notificationManagerCompat.mNotificationManager.createNotificationChannel(notificationChannel);
            }
        }
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(this, "clash_logcat_channel");
        notificationCompat$Builder.mNotification.icon = R.drawable.ic_logo_service;
        notificationCompat$Builder.mColor = PathParser.getColorCompat(this, R.color.color_clash_light);
        notificationCompat$Builder.setContentTitle(getString(R.string.clash_logcat));
        notificationCompat$Builder.setContentText(getString(R.string.running));
        notificationCompat$Builder.mContentIntent = PendingIntent.getActivity(this, R.id.nf_logcat_status, PathParser.getIntent(Reflection.getOrCreateKotlinClass(LogcatActivity.class)).setFlags(872415232), 134217728);
        startForeground(R.id.nf_logcat_status, notificationCompat$Builder.build());
        bindService(PathParser.getIntent(Reflection.getOrCreateKotlinClass(RemoteService.class)), this.connection, 1);
    }

    @Override // android.app.Service
    public void onDestroy() {
        InputKt.cancel$default(this, null, 1);
        unbindService(this.connection);
        stopForeground(true);
        running = false;
        super.onDestroy();
    }
}
