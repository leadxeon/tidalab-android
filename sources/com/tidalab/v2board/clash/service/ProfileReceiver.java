package com.tidalab.v2board.clash.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.content.ContextCompat;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.common.Global;
import com.tidalab.v2board.clash.common.constants.Intents;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.data.Imported;
import java.util.concurrent.TimeUnit;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
/* compiled from: ProfileReceiver.kt */
/* loaded from: classes.dex */
public final class ProfileReceiver extends BroadcastReceiver {
    public static boolean initialized;
    public static final Companion Companion = new Companion(null);
    public static final Mutex lock = MutexKt.Mutex$default(false, 1);

    /* compiled from: ProfileReceiver.kt */
    /* loaded from: classes.dex */
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
        /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static final java.lang.Object access$reset(com.tidalab.v2board.clash.service.ProfileReceiver.Companion r4, kotlin.coroutines.Continuation r5) {
            /*
                java.util.Objects.requireNonNull(r4)
                boolean r0 = r5 instanceof com.tidalab.v2board.clash.service.ProfileReceiver$Companion$reset$1
                if (r0 == 0) goto L_0x0016
                r0 = r5
                com.tidalab.v2board.clash.service.ProfileReceiver$Companion$reset$1 r0 = (com.tidalab.v2board.clash.service.ProfileReceiver$Companion$reset$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L_0x0016
                int r1 = r1 - r2
                r0.label = r1
                goto L_0x001b
            L_0x0016:
                com.tidalab.v2board.clash.service.ProfileReceiver$Companion$reset$1 r0 = new com.tidalab.v2board.clash.service.ProfileReceiver$Companion$reset$1
                r0.<init>(r4, r5)
            L_0x001b:
                java.lang.Object r4 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r5 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r0.label
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L_0x0037
                if (r1 != r3) goto L_0x002f
                java.lang.Object r5 = r0.L$0
                kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r4)
                goto L_0x0048
            L_0x002f:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r5)
                throw r4
            L_0x0037:
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r4)
                kotlinx.coroutines.sync.Mutex r4 = com.tidalab.v2board.clash.service.ProfileReceiver.lock
                r0.L$0 = r4
                r0.label = r3
                java.lang.Object r0 = r4.lock(r2, r0)
                if (r0 != r5) goto L_0x0047
                goto L_0x0053
            L_0x0047:
                r5 = r4
            L_0x0048:
                com.tidalab.v2board.clash.service.ProfileReceiver$Companion r4 = com.tidalab.v2board.clash.service.ProfileReceiver.Companion     // Catch: all -> 0x0054
                r4 = 0
                com.tidalab.v2board.clash.service.ProfileReceiver.initialized = r4     // Catch: all -> 0x0054
                kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch: all -> 0x0054
                r5.unlock(r2)
                r5 = r4
            L_0x0053:
                return r5
            L_0x0054:
                r4 = move-exception
                r5.unlock(r2)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileReceiver.Companion.access$reset(com.tidalab.v2board.clash.service.ProfileReceiver$Companion, kotlin.coroutines.Continuation):java.lang.Object");
        }

        public final PendingIntent pendingIntentOf(Context context, Imported imported) {
            Intents intents = Intents.INSTANCE;
            Intent component = new Intent(Intents.ACTION_PROFILE_REQUEST_UPDATE).setComponent(PathParser.getComponentName(Reflection.getOrCreateKotlinClass(ProfileReceiver.class)));
            PathParser.setUUID(component, imported.uuid);
            return PendingIntent.getBroadcast(context, 0, component, PathParser.pendingIntentFlags$default(134217728, false, 2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:27:0x007b A[Catch: all -> 0x0123, TRY_LEAVE, TryCatch #0 {all -> 0x0123, blocks: (B:25:0x0077, B:27:0x007b, B:30:0x0081), top: B:64:0x0077 }] */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0081 A[Catch: all -> 0x0123, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0123, blocks: (B:25:0x0077, B:27:0x007b, B:30:0x0081), top: B:64:0x0077 }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00b2 A[Catch: all -> 0x0054, TryCatch #1 {all -> 0x0054, blocks: (B:14:0x003a, B:18:0x0050, B:34:0x009f, B:35:0x00ac, B:37:0x00b2, B:40:0x00cd, B:42:0x00d1, B:43:0x00d5, B:44:0x00e0, B:46:0x00e6, B:50:0x00f6, B:52:0x0100, B:53:0x0104, B:54:0x0108, B:56:0x010e, B:57:0x011a), top: B:65:0x0022 }] */
        /* JADX WARN: Removed duplicated region for block: B:42:0x00d1 A[Catch: all -> 0x0054, TryCatch #1 {all -> 0x0054, blocks: (B:14:0x003a, B:18:0x0050, B:34:0x009f, B:35:0x00ac, B:37:0x00b2, B:40:0x00cd, B:42:0x00d1, B:43:0x00d5, B:44:0x00e0, B:46:0x00e6, B:50:0x00f6, B:52:0x0100, B:53:0x0104, B:54:0x0108, B:56:0x010e, B:57:0x011a), top: B:65:0x0022 }] */
        /* JADX WARN: Removed duplicated region for block: B:43:0x00d5 A[Catch: all -> 0x0054, TryCatch #1 {all -> 0x0054, blocks: (B:14:0x003a, B:18:0x0050, B:34:0x009f, B:35:0x00ac, B:37:0x00b2, B:40:0x00cd, B:42:0x00d1, B:43:0x00d5, B:44:0x00e0, B:46:0x00e6, B:50:0x00f6, B:52:0x0100, B:53:0x0104, B:54:0x0108, B:56:0x010e, B:57:0x011a), top: B:65:0x0022 }] */
        /* JADX WARN: Type inference failed for: r10v1, types: [kotlinx.coroutines.sync.Mutex] */
        /* JADX WARN: Type inference failed for: r10v3 */
        /* JADX WARN: Type inference failed for: r3v0 */
        /* JADX WARN: Type inference failed for: r3v12 */
        /* JADX WARN: Type inference failed for: r3v13 */
        /* JADX WARN: Type inference failed for: r3v14 */
        /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.Object, kotlinx.coroutines.sync.Mutex] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00ca -> B:40:0x00cd). Please submit an issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object rescheduleAll(android.content.Context r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
            /*
                Method dump skipped, instructions count: 296
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileReceiver.Companion.rescheduleAll(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
        }

        public final void scheduleNext(Context context, Imported imported) {
            PendingIntent pendingIntentOf = pendingIntentOf(context, imported);
            AlarmManager alarmManager = (AlarmManager) ContextCompat.getSystemService(context, AlarmManager.class);
            if (alarmManager != null) {
                alarmManager.cancel(pendingIntentOf);
            }
            if (imported.interval >= TimeUnit.MINUTES.toMillis(15L)) {
                long currentTimeMillis = System.currentTimeMillis();
                long lastModified = FilesKt__UtilsKt.resolve(FilesKt__UtilsKt.resolve(InputKt.getImportedDir(context), imported.uuid.toString()), "config.yaml").lastModified();
                long j = 0;
                if (lastModified >= 0) {
                    long j2 = imported.interval - (currentTimeMillis - lastModified);
                    if (j2 >= 0) {
                        j = j2;
                    }
                    AlarmManager alarmManager2 = (AlarmManager) ContextCompat.getSystemService(context, AlarmManager.class);
                    if (alarmManager2 != null) {
                        alarmManager2.set(1, currentTimeMillis + j, pendingIntentOf);
                    }
                }
            }
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        int hashCode;
        String action = intent.getAction();
        if ((action == null || ((hashCode = action.hashCode()) == 502473491 ? !action.equals("android.intent.action.TIMEZONE_CHANGED") : !(hashCode == 798292259 ? action.equals("android.intent.action.BOOT_COMPLETED") : hashCode == 1737074039 && action.equals("android.intent.action.MY_PACKAGE_REPLACED")))) ? Intrinsics.areEqual(action, "android.intent.action.TIME_SET") : true) {
            InputKt.launch$default(Global.INSTANCE, null, null, new ProfileReceiver$onReceive$1(context, null), 3, null);
            return;
        }
        Intents intents = Intents.INSTANCE;
        if (Intrinsics.areEqual(action, Intents.ACTION_PROFILE_REQUEST_UPDATE)) {
            PathParser.startForegroundServiceCompat(context, intent.setComponent(PathParser.getComponentName(Reflection.getOrCreateKotlinClass(ProfileWorker.class))));
        }
    }
}
