package com.tidalab.v2board.clash;

import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.widget.Toast;
import com.tidalab.v2board.clash.design.LogcatDesign;
import com.tidalab.v2board.clash.design.model.LogFile;
import com.tidalab.v2board.clash.foss.R;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LogcatActivity.kt */
/* loaded from: classes.dex */
public final class LogcatActivity extends BaseActivity<LogcatDesign> {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ServiceConnection conn;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$writeLogTo(com.tidalab.v2board.clash.LogcatActivity r11, java.util.List r12, com.tidalab.v2board.clash.design.model.LogFile r13, android.net.Uri r14, kotlin.coroutines.Continuation r15) {
        /*
            java.util.Objects.requireNonNull(r11)
            boolean r0 = r15 instanceof com.tidalab.v2board.clash.LogcatActivity$writeLogTo$1
            if (r0 == 0) goto L_0x0016
            r0 = r15
            com.tidalab.v2board.clash.LogcatActivity$writeLogTo$1 r0 = (com.tidalab.v2board.clash.LogcatActivity$writeLogTo$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.tidalab.v2board.clash.LogcatActivity$writeLogTo$1 r0 = new com.tidalab.v2board.clash.LogcatActivity$writeLogTo$1
            r0.<init>(r11, r15)
        L_0x001b:
            java.lang.Object r15 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r11 = r0.L$0
            java.io.Closeable r11 = (java.io.Closeable) r11
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r15)     // Catch: all -> 0x002f
            goto L_0x0069
        L_0x002f:
            r12 = move-exception
            goto L_0x006f
        L_0x0031:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r15)
            com.tidalab.v2board.clash.log.LogcatFilter r15 = new com.tidalab.v2board.clash.log.LogcatFilter
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter
            android.content.ContentResolver r5 = r11.getContentResolver()
            java.io.OutputStream r14 = r5.openOutputStream(r14)
            r2.<init>(r14)
            r15.<init>(r2, r11)
            kotlinx.coroutines.Dispatchers r14 = kotlinx.coroutines.Dispatchers.INSTANCE     // Catch: all -> 0x0071
            kotlinx.coroutines.MainCoroutineDispatcher r14 = kotlinx.coroutines.internal.MainDispatcherLoader.dispatcher     // Catch: all -> 0x0071
            com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1 r2 = new com.tidalab.v2board.clash.LogcatActivity$writeLogTo$2$1     // Catch: all -> 0x0071
            r10 = 0
            r5 = r2
            r6 = r11
            r7 = r12
            r8 = r15
            r9 = r13
            r5.<init>(r6, r7, r8, r9, r10)     // Catch: all -> 0x0071
            r0.L$0 = r15     // Catch: all -> 0x0071
            r0.label = r3     // Catch: all -> 0x0071
            java.lang.Object r11 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r14, r2, r0)     // Catch: all -> 0x0071
            if (r11 != r1) goto L_0x0068
            goto L_0x006e
        L_0x0068:
            r11 = r15
        L_0x0069:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            com.tidalab.v2board.clash.design.dialog.InputKt.closeFinally(r11, r4)
        L_0x006e:
            return r1
        L_0x006f:
            r15 = r11
            goto L_0x0073
        L_0x0071:
            r11 = move-exception
            r12 = r11
        L_0x0073:
            throw r12     // Catch: all -> 0x0074
        L_0x0074:
            r11 = move-exception
            com.tidalab.v2board.clash.design.dialog.InputKt.closeFinally(r15, r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.LogcatActivity.access$writeLogTo(com.tidalab.v2board.clash.LogcatActivity, java.util.List, com.tidalab.v2board.clash.design.model.LogFile, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.tidalab.v2board.clash.BaseActivity
    public Object main(Continuation<? super Unit> continuation) {
        Uri data;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Intent intent = getIntent();
        String str = null;
        if (!(intent == null || (data = intent.getData()) == null)) {
            if (!Intrinsics.areEqual(data.getScheme(), "file")) {
                data = null;
            }
            if (data != null) {
                str = data.getSchemeSpecificPart();
            }
        }
        if (str != null) {
            LogFile logFile = LogFile.Companion;
            LogFile parseFromFileName = LogFile.parseFromFileName(str);
            if (parseFromFileName == null) {
                Toast.makeText(this, (int) R.string.invalid_log_file, 1).show();
                return Unit.INSTANCE;
            }
            Object mainLocalFile = mainLocalFile(parseFromFileName, continuation);
            return mainLocalFile == coroutineSingletons ? mainLocalFile : Unit.INSTANCE;
        }
        Object mainStreaming = mainStreaming(continuation);
        return mainStreaming == coroutineSingletons ? mainStreaming : Unit.INSTANCE;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(5:58|59|83|60|(1:62)(3:63|13|78)) */
    /* JADX WARN: Can't wrap try/catch for region: R(7:2|(2:4|(4:6|8|89|9))|7|8|89|9|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0081, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01fe, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x01ff, code lost:
        r8 = r6;
        r6 = r5;
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0203, code lost:
        r8 = r8;
        r0 = r0;
        r6 = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0225, code lost:
        return r4;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x016a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01fa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0225 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01b7 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r5v0, types: [int] */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Unknown variable types count: 2 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0190 -> B:41:0x016e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:63:0x01fb -> B:13:0x0045). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0223 -> B:13:0x0045). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x0226 -> B:41:0x016e). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:77:0x024a -> B:13:0x0045). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mainLocalFile(com.tidalab.v2board.clash.design.model.LogFile r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            Method dump skipped, instructions count: 632
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.LogcatActivity.mainLocalFile(com.tidalab.v2board.clash.design.model.LogFile, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ae A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object mainStreaming(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.LogcatActivity.mainStreaming(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.tidalab.v2board.clash.BaseActivity, com.facebook.react.ReactActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        ServiceConnection serviceConnection = this.conn;
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
        super.onDestroy();
    }
}
