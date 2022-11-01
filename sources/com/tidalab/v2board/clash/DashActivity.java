package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.MainDesign;
/* compiled from: DashActivity.kt */
/* loaded from: classes.dex */
public final class DashActivity extends BaseActivity<MainDesign> {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$queryAppVersionName(com.tidalab.v2board.clash.DashActivity r5, kotlin.coroutines.Continuation r6) {
        /*
            java.util.Objects.requireNonNull(r5)
            boolean r0 = r6 instanceof com.tidalab.v2board.clash.DashActivity$queryAppVersionName$1
            if (r0 == 0) goto L_0x0016
            r0 = r6
            com.tidalab.v2board.clash.DashActivity$queryAppVersionName$1 r0 = (com.tidalab.v2board.clash.DashActivity$queryAppVersionName$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.tidalab.v2board.clash.DashActivity$queryAppVersionName$1 r0 = new com.tidalab.v2board.clash.DashActivity$queryAppVersionName$1
            r0.<init>(r5, r6)
        L_0x001b:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            goto L_0x0048
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            kotlinx.coroutines.Dispatchers r6 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.IO
            com.tidalab.v2board.clash.DashActivity$queryAppVersionName$2 r2 = new com.tidalab.v2board.clash.DashActivity$queryAppVersionName$2
            r4 = 0
            r2.<init>(r5, r4)
            r0.label = r3
            java.lang.Object r6 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r6, r2, r0)
            if (r6 != r1) goto L_0x0048
            goto L_0x0049
        L_0x0048:
            r1 = r6
        L_0x0049:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.DashActivity.access$queryAppVersionName(com.tidalab.v2board.clash.DashActivity, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:2|(2:4|(6:6|8|44|(1:(1:(1:(1:(3:14|39|47)(2:15|16))(6:17|18|32|(1:34)|39|47))(3:19|42|43))(1:20))(2:21|(1:48))|23|(4:40|(1:50)|42|43)(4:27|(4:29|(2:31|46)|32|(0))|39|47)))|7|8|44|(0)(0)|23|(1:25)|40|(0)|42|43) */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009b, code lost:
        r0 = (com.tidalab.v2board.clash.design.MainDesign) r9.design;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a0, code lost:
        if (r0 != null) goto L_0x00a3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00a3, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 4;
        r9 = r0.showToast((int) com.tidalab.v2board.clash.foss.R.string.unable_to_start_vpn, r2, (r5 & 4) != 0 ? com.tidalab.v2board.clash.design.Design$showToast$2.INSTANCE : null, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b4, code lost:
        if (r9 == r7) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
        return r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0097 A[Catch: Exception -> 0x009b, TRY_LEAVE, TryCatch #0 {Exception -> 0x009b, blocks: (B:18:0x0044, B:29:0x007e, B:32:0x0090, B:34:0x0097), top: B:44:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$startClash(com.tidalab.v2board.clash.DashActivity r9, com.tidalab.v2board.clash.design.MainDesign r10, kotlin.coroutines.Continuation r11) {
        /*
            Method dump skipped, instructions count: 210
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.DashActivity.access$startClash(com.tidalab.v2board.clash.DashActivity, com.tidalab.v2board.clash.design.MainDesign, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0098 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ae A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0106 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object fetch(com.tidalab.v2board.clash.design.MainDesign r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.DashActivity.fetch(com.tidalab.v2board.clash.design.MainDesign, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a1  */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            Method dump skipped, instructions count: 235
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.DashActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
