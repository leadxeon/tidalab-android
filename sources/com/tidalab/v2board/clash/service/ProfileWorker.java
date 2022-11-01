package com.tidalab.v2board.clash.service;

import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat$Builder;
import androidx.core.app.NotificationManagerCompat;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
/* compiled from: ProfileWorker.kt */
/* loaded from: classes.dex */
public final class ProfileWorker extends BaseService {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final List<Job> jobs = new ArrayList();

    /* JADX WARN: Can't wrap try/catch for region: R(9:2|(2:4|(6:6|8|61|(1:(1:(1:(1:(2:14|15)(3:16|17|48))(6:18|19|40|55|62|56))(7:21|59|22|37|(3:39|40|55)|62|56))(1:24))(2:25|(1:67))|27|(2:29|66)(6:64|30|57|31|(1:33)(1:34)|(1:68)(5:36|37|(0)|62|56))))|7|8|61|(0)(0)|27|(0)(0)|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0066, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0111, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x013e, code lost:
        r0 = "Unknown";
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x013e  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00a6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v13, types: [com.tidalab.v2board.clash.service.ProfileReceiver$Companion] */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v16, types: [android.content.Context, android.app.Service, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v17, types: [com.tidalab.v2board.clash.service.data.ImportedDao] */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v3, types: [com.tidalab.v2board.clash.service.data.Imported] */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.core.app.NotificationCompat$Builder] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r3v1, types: [kotlin.coroutines.Continuation, com.tidalab.v2board.clash.service.ProfileWorker$run$1] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v2, types: [android.content.Context, android.app.Service, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r5v0, types: [int] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v15, types: [com.tidalab.v2board.clash.service.data.Imported, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v27 */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Unknown variable types count: 4 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0140 -> B:55:0x01ac). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$run(com.tidalab.v2board.clash.service.ProfileWorker r17, java.util.UUID r18, kotlin.coroutines.Continuation r19) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileWorker.access$run(com.tidalab.v2board.clash.service.ProfileWorker, java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            NotificationManagerCompat notificationManagerCompat = new NotificationManagerCompat(this);
            List<NotificationChannel> listOf = ArraysKt___ArraysKt.listOf(new NotificationChannel("profile_service_channel", getString(R.string.profile_service_status), 2), new NotificationChannel("profile_status_channel", getString(R.string.profile_process_status), 2), new NotificationChannel("profile_result_channel", getString(R.string.profile_process_result), 3));
            if (i >= 26) {
                notificationManagerCompat.mNotificationManager.createNotificationChannels(listOf);
            }
        }
        NotificationCompat$Builder notificationCompat$Builder = new NotificationCompat$Builder(this, "profile_service_channel");
        notificationCompat$Builder.setContentTitle(getString(R.string.profile_updater));
        notificationCompat$Builder.setContentText(getString(R.string.running));
        notificationCompat$Builder.mColor = PathParser.getColorCompat(this, R.color.color_clash);
        notificationCompat$Builder.mNotification.icon = R.drawable.ic_logo_service;
        notificationCompat$Builder.setFlag(2, true);
        notificationCompat$Builder.setFlag(8, true);
        startForeground(R.id.nf_profile_worker, notificationCompat$Builder.build());
        InputKt.launch$default(this, null, null, new ProfileWorker$onCreate$1(this, null), 3, null);
    }

    @Override // com.tidalab.v2board.clash.service.BaseService, android.app.Service
    public void onDestroy() {
        stopForeground(true);
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        String action = intent == null ? null : intent.getAction();
        Intents intents = Intents.INSTANCE;
        if (Intrinsics.areEqual(action, Intents.ACTION_PROFILE_REQUEST_UPDATE)) {
            UUID uuid = PathParser.getUuid(intent);
            if (uuid == null) {
                return 2;
            }
            this.jobs.add(InputKt.launch$default(this, null, null, new ProfileWorker$onStartCommand$1$job$1(this, uuid, null), 3, null));
            return 2;
        } else if (!Intrinsics.areEqual(action, Intents.ACTION_PROFILE_SCHEDULE_UPDATES)) {
            return 2;
        } else {
            this.jobs.add(InputKt.launch$default(this, null, null, new ProfileWorker$onStartCommand$job$1(this, null), 3, null));
            return 2;
        }
    }
}
