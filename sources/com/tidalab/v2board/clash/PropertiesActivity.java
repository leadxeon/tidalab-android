package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.PropertiesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
/* compiled from: PropertiesActivity.kt */
/* loaded from: classes.dex */
public final class PropertiesActivity extends BaseActivity<PropertiesDesign> {
    public boolean canceled;

    /* JADX WARN: Can't wrap try/catch for region: R(9:2|(2:4|(7:6|8|38|(4:(1:(1:(2:14|15))(2:16|17))|18|36|37)(4:19|(2:21|(1:41))(2:23|(2:29|(2:31|40))(2:27|(1:42)))|36|37)|32|36|37))|7|8|38|(0)(0)|32|36|37) */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ab, code lost:
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00ac, code lost:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00b6, code lost:
        if (com.tidalab.v2board.clash.design.dialog.InputKt.showExceptionToast(r10, r9, r0) == r7) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:?, code lost:
        return r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$verifyAndCommit(com.tidalab.v2board.clash.PropertiesActivity r9, com.tidalab.v2board.clash.design.PropertiesDesign r10, kotlin.coroutines.Continuation r11) {
        /*
            java.util.Objects.requireNonNull(r9)
            com.tidalab.v2board.clash.design.ui.ToastDuration r2 = com.tidalab.v2board.clash.design.ui.ToastDuration.Long
            boolean r0 = r11 instanceof com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$1
            if (r0 == 0) goto L_0x0018
            r0 = r11
            com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$1 r0 = (com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$1) r0
            int r1 = r0.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r1 & r3
            if (r4 == 0) goto L_0x0018
            int r1 = r1 - r3
            r0.label = r1
            goto L_0x001d
        L_0x0018:
            com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$1 r0 = new com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$1
            r0.<init>(r9, r11)
        L_0x001d:
            r4 = r0
            java.lang.Object r11 = r4.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r7 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r0 = r4.label
            r1 = 4
            r3 = 3
            r5 = 2
            r6 = 1
            r8 = 0
            if (r0 == 0) goto L_0x004e
            if (r0 == r6) goto L_0x0049
            if (r0 == r5) goto L_0x0049
            if (r0 == r3) goto L_0x003c
            if (r0 != r1) goto L_0x0034
            goto L_0x0049
        L_0x0034:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003c:
            java.lang.Object r9 = r4.L$1
            r10 = r9
            com.tidalab.v2board.clash.design.PropertiesDesign r10 = (com.tidalab.v2board.clash.design.PropertiesDesign) r10
            java.lang.Object r9 = r4.L$0
            com.tidalab.v2board.clash.PropertiesActivity r9 = (com.tidalab.v2board.clash.PropertiesActivity) r9
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)     // Catch: Exception -> 0x00ab
            goto L_0x00a3
        L_0x0049:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)
            goto L_0x00b9
        L_0x004e:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)
            com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding r11 = r10.binding
            com.tidalab.v2board.clash.service.model.Profile r11 = r11.mProfile
            java.lang.String r11 = r11.name
            boolean r11 = kotlin.text.StringsKt__IndentKt.isBlank(r11)
            if (r11 == 0) goto L_0x006e
            r1 = 2131820699(0x7f11009b, float:1.927412E38)
            r3 = 0
            r5 = 4
            r9 = 0
            r4.label = r6
            r0 = r10
            r6 = r9
            java.lang.Object r9 = com.tidalab.v2board.clash.design.Design.showToast$default(r0, r1, r2, r3, r4, r5, r6)
            if (r9 != r7) goto L_0x00b9
            goto L_0x00bb
        L_0x006e:
            com.tidalab.v2board.clash.design.databinding.DesignPropertiesBinding r11 = r10.binding
            com.tidalab.v2board.clash.service.model.Profile r11 = r11.mProfile
            com.tidalab.v2board.clash.service.model.Profile$Type r0 = r11.type
            com.tidalab.v2board.clash.service.model.Profile$Type r6 = com.tidalab.v2board.clash.service.model.Profile.Type.File
            if (r0 == r6) goto L_0x0091
            java.lang.String r11 = r11.source
            boolean r11 = kotlin.text.StringsKt__IndentKt.isBlank(r11)
            if (r11 == 0) goto L_0x0091
            r1 = 2131820769(0x7f1100e1, float:1.9274262E38)
            r3 = 0
            r9 = 4
            r6 = 0
            r4.label = r5
            r0 = r10
            r5 = r9
            java.lang.Object r9 = com.tidalab.v2board.clash.design.Design.showToast$default(r0, r1, r2, r3, r4, r5, r6)
            if (r9 != r7) goto L_0x00b9
            goto L_0x00bb
        L_0x0091:
            com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$2 r11 = new com.tidalab.v2board.clash.PropertiesActivity$verifyAndCommit$2     // Catch: Exception -> 0x00ab
            r11.<init>(r10, r8)     // Catch: Exception -> 0x00ab
            r4.L$0 = r9     // Catch: Exception -> 0x00ab
            r4.L$1 = r10     // Catch: Exception -> 0x00ab
            r4.label = r3     // Catch: Exception -> 0x00ab
            java.lang.Object r11 = r10.withProcessing(r11, r4)     // Catch: Exception -> 0x00ab
            if (r11 != r7) goto L_0x00a3
            goto L_0x00bb
        L_0x00a3:
            r11 = -1
            r9.setResult(r11)     // Catch: Exception -> 0x00ab
            r9.finish()     // Catch: Exception -> 0x00ab
            goto L_0x00b9
        L_0x00ab:
            r9 = move-exception
            r4.L$0 = r8
            r4.L$1 = r8
            r4.label = r1
            java.lang.Object r9 = com.tidalab.v2board.clash.design.dialog.InputKt.showExceptionToast(r10, r9, r4)
            if (r9 != r7) goto L_0x00b9
            goto L_0x00bb
        L_0x00b9:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
        L_0x00bb:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.PropertiesActivity.access$verifyAndCommit(com.tidalab.v2board.clash.PropertiesActivity, com.tidalab.v2board.clash.design.PropertiesDesign, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ce  */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.PropertiesActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.facebook.react.ReactActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        PropertiesDesign propertiesDesign = (PropertiesDesign) this.design;
        if (propertiesDesign == null) {
            propertiesDesign = null;
        } else {
            InputKt.launch$default(propertiesDesign, null, null, new PropertiesActivity$onBackPressed$1$1(propertiesDesign, this, null), 3, null);
        }
        if (propertiesDesign == null) {
            super.onBackPressed();
        }
    }
}
