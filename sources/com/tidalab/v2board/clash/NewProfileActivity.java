package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.NewProfileDesign;
/* compiled from: NewProfileActivity.kt */
/* loaded from: classes.dex */
public final class NewProfileActivity extends BaseActivity<NewProfileDesign> {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$get(com.tidalab.v2board.clash.NewProfileActivity r4, com.tidalab.v2board.clash.design.model.ProfileProvider.External r5, kotlin.coroutines.Continuation r6) {
        /*
            java.util.Objects.requireNonNull(r4)
            boolean r0 = r6 instanceof com.tidalab.v2board.clash.NewProfileActivity$get$1
            if (r0 == 0) goto L_0x0016
            r0 = r6
            com.tidalab.v2board.clash.NewProfileActivity$get$1 r0 = (com.tidalab.v2board.clash.NewProfileActivity$get$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.tidalab.v2board.clash.NewProfileActivity$get$1 r0 = new com.tidalab.v2board.clash.NewProfileActivity$get$1
            r0.<init>(r4, r6)
        L_0x001b:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            goto L_0x0045
        L_0x002a:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0032:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult r6 = new androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult
            r6.<init>()
            android.content.Intent r5 = r5.intent
            r0.label = r3
            java.lang.Object r6 = r4.startActivityForResult(r6, r5, r0)
            if (r6 != r1) goto L_0x0045
            goto L_0x006b
        L_0x0045:
            androidx.activity.result.ActivityResult r6 = (androidx.activity.result.ActivityResult) r6
            int r4 = r6.mResultCode
            r5 = -1
            r1 = 0
            if (r4 == r5) goto L_0x004e
            goto L_0x006b
        L_0x004e:
            android.content.Intent r4 = r6.mData
            if (r4 != 0) goto L_0x0054
            r4 = r1
            goto L_0x0058
        L_0x0054:
            android.net.Uri r4 = r4.getData()
        L_0x0058:
            android.content.Intent r5 = r6.mData
            if (r5 != 0) goto L_0x005e
            r5 = r1
            goto L_0x0064
        L_0x005e:
            java.lang.String r6 = "name"
            java.lang.String r5 = r5.getStringExtra(r6)
        L_0x0064:
            if (r4 == 0) goto L_0x006b
            kotlin.Pair r1 = new kotlin.Pair
            r1.<init>(r4, r5)
        L_0x006b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.NewProfileActivity.access$get(com.tidalab.v2board.clash.NewProfileActivity, com.tidalab.v2board.clash.design.model.ProfileProvider$External, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$launchProperties(com.tidalab.v2board.clash.NewProfileActivity r4, java.util.UUID r5, kotlin.coroutines.Continuation r6) {
        /*
            java.util.Objects.requireNonNull(r4)
            boolean r0 = r6 instanceof com.tidalab.v2board.clash.NewProfileActivity$launchProperties$1
            if (r0 == 0) goto L_0x0016
            r0 = r6
            com.tidalab.v2board.clash.NewProfileActivity$launchProperties$1 r0 = (com.tidalab.v2board.clash.NewProfileActivity$launchProperties$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0016
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x001b
        L_0x0016:
            com.tidalab.v2board.clash.NewProfileActivity$launchProperties$1 r0 = new com.tidalab.v2board.clash.NewProfileActivity$launchProperties$1
            r0.<init>(r4, r6)
        L_0x001b:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            com.tidalab.v2board.clash.NewProfileActivity r4 = (com.tidalab.v2board.clash.NewProfileActivity) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            goto L_0x0056
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
            androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult r6 = new androidx.activity.result.contract.ActivityResultContracts$StartActivityForResult
            r6.<init>()
            java.lang.Class<com.tidalab.v2board.clash.PropertiesActivity> r2 = com.tidalab.v2board.clash.PropertiesActivity.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            android.content.Intent r2 = com.horcrux.svg.PathParser.getIntent(r2)
            com.horcrux.svg.PathParser.setUUID(r2, r5)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r4.startActivityForResult(r6, r2, r0)
            if (r6 != r1) goto L_0x0056
            goto L_0x0062
        L_0x0056:
            androidx.activity.result.ActivityResult r6 = (androidx.activity.result.ActivityResult) r6
            int r5 = r6.mResultCode
            r6 = -1
            if (r5 != r6) goto L_0x0060
            r4.finish()
        L_0x0060:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L_0x0062:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.NewProfileActivity.access$launchProperties(com.tidalab.v2board.clash.NewProfileActivity, java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0090 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a7  */
    @Override // com.tidalab.v2board.clash.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object main(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.NewProfileActivity.main(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
