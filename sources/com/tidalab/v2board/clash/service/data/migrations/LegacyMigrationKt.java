package com.tidalab.v2board.clash.service.data.migrations;
/* compiled from: LegacyMigration.kt */
/* loaded from: classes.dex */
public final class LegacyMigrationKt {
    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object migrationFromLegacy(android.content.Context r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            boolean r0 = r12 instanceof com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt$migrationFromLegacy$1
            if (r0 == 0) goto L_0x0013
            r0 = r12
            com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt$migrationFromLegacy$1 r0 = (com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt$migrationFromLegacy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt$migrationFromLegacy$1 r0 = new com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt$migrationFromLegacy$1
            r0.<init>(r12)
        L_0x0018:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            java.lang.String r3 = "clash-config"
            java.lang.String r4 = "ClashForAndroid"
            r5 = 1
            r6 = 2
            r7 = 0
            if (r2 == 0) goto L_0x0045
            if (r2 == r5) goto L_0x0034
            if (r2 != r6) goto L_0x002c
            goto L_0x0034
        L_0x002c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0034:
            java.lang.Object r11 = r0.L$1
            java.io.Closeable r11 = (java.io.Closeable) r11
            java.lang.Object r0 = r0.L$0
            android.content.Context r0 = (android.content.Context) r0
            r10 = r0
            r0 = r11
            r11 = r10
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)     // Catch: all -> 0x0043
            goto L_0x009a
        L_0x0043:
            r12 = move-exception
            goto L_0x00a4
        L_0x0045:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r12)
            java.io.File r12 = r11.getDatabasePath(r3)
            boolean r2 = r12.exists()
            if (r2 != 0) goto L_0x0055
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        L_0x0055:
            java.lang.String r2 = "Migration from legacy database"
            android.util.Log.i(r4, r2, r7)
            java.lang.String r12 = r12.getAbsolutePath()     // Catch: Exception -> 0x00aa
            android.database.sqlite.SQLiteDatabase r12 = android.database.sqlite.SQLiteDatabase.openDatabase(r12, r7, r5)     // Catch: Exception -> 0x00aa
            int r2 = r12.getVersion()     // Catch: all -> 0x00a0
            java.lang.String r8 = "Legacy database version = "
            java.lang.Integer r9 = new java.lang.Integer     // Catch: all -> 0x00a0
            r9.<init>(r2)     // Catch: all -> 0x00a0
            java.lang.String r8 = kotlin.jvm.internal.Intrinsics.stringPlus(r8, r9)     // Catch: all -> 0x00a0
            android.util.Log.i(r4, r8, r7)     // Catch: all -> 0x00a0
            if (r2 == r5) goto L_0x008c
            if (r2 == r6) goto L_0x007f
            r5 = 3
            if (r2 == r5) goto L_0x007f
            r5 = 4
            if (r2 == r5) goto L_0x007f
            goto L_0x0099
        L_0x007f:
            r0.L$0 = r11     // Catch: all -> 0x00a0
            r0.L$1 = r12     // Catch: all -> 0x00a0
            r0.label = r6     // Catch: all -> 0x00a0
            java.lang.Object r0 = migrationFromLegacy234(r11, r12, r2, r0)     // Catch: all -> 0x00a0
            if (r0 != r1) goto L_0x0099
            return r1
        L_0x008c:
            r0.L$0 = r11     // Catch: all -> 0x00a0
            r0.L$1 = r12     // Catch: all -> 0x00a0
            r0.label = r5     // Catch: all -> 0x00a0
            java.lang.Object r0 = migrationFromLegacy1(r11, r12, r0)     // Catch: all -> 0x00a0
            if (r0 != r1) goto L_0x0099
            return r1
        L_0x0099:
            r0 = r12
        L_0x009a:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE     // Catch: all -> 0x0043
            com.tidalab.v2board.clash.design.dialog.InputKt.closeFinally(r0, r7)     // Catch: Exception -> 0x00aa
            goto L_0x00b4
        L_0x00a0:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
        L_0x00a4:
            throw r12     // Catch: all -> 0x00a5
        L_0x00a5:
            r1 = move-exception
            com.tidalab.v2board.clash.design.dialog.InputKt.closeFinally(r0, r12)     // Catch: Exception -> 0x00aa
            throw r1     // Catch: Exception -> 0x00aa
        L_0x00aa:
            r12 = move-exception
            java.lang.String r0 = "Migration legacy database: "
            java.lang.String r0 = kotlin.jvm.internal.Intrinsics.stringPlus(r0, r12)
            android.util.Log.w(r4, r0, r12)
        L_0x00b4:
            r11.deleteDatabase(r3)
            java.lang.String r11 = "Legacy database migrated"
            android.util.Log.i(r4, r11, r7)
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt.migrationFromLegacy(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f2 A[Catch: all -> 0x01ee, TryCatch #2 {all -> 0x01ee, blocks: (B:31:0x00d6, B:36:0x00e9, B:40:0x00f2, B:42:0x00fc, B:44:0x010a, B:61:0x01db, B:63:0x01e1), top: B:76:0x00d6 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0125 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01ad A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01e1 A[Catch: all -> 0x01ee, TRY_LEAVE, TryCatch #2 {all -> 0x01ee, blocks: (B:31:0x00d6, B:36:0x00e9, B:40:0x00f2, B:42:0x00fc, B:44:0x010a, B:61:0x01db, B:63:0x01e1), top: B:76:0x00d6 }] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01e7  */
    /* JADX WARN: Type inference failed for: r15v6, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v16, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Unknown variable types count: 3 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x01ae -> B:58:0x01b0). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x01d3 -> B:61:0x01db). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object migrationFromLegacy1(android.content.Context r30, android.database.sqlite.SQLiteDatabase r31, kotlin.coroutines.Continuation<? super kotlin.Unit> r32) {
        /*
            Method dump skipped, instructions count: 504
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt.migrationFromLegacy1(android.content.Context, android.database.sqlite.SQLiteDatabase, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x002a  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0175 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0197 A[Catch: all -> 0x0057, TryCatch #2 {all -> 0x0057, blocks: (B:13:0x004e, B:29:0x00eb, B:33:0x0103, B:35:0x010d, B:53:0x018b, B:55:0x0197, B:59:0x01a5, B:61:0x01b3, B:63:0x01e7, B:65:0x020b, B:67:0x0219, B:71:0x024e), top: B:99:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01a5 A[Catch: all -> 0x0057, TryCatch #2 {all -> 0x0057, blocks: (B:13:0x004e, B:29:0x00eb, B:33:0x0103, B:35:0x010d, B:53:0x018b, B:55:0x0197, B:59:0x01a5, B:61:0x01b3, B:63:0x01e7, B:65:0x020b, B:67:0x0219, B:71:0x024e), top: B:99:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01e7 A[Catch: all -> 0x0057, TryCatch #2 {all -> 0x0057, blocks: (B:13:0x004e, B:29:0x00eb, B:33:0x0103, B:35:0x010d, B:53:0x018b, B:55:0x0197, B:59:0x01a5, B:61:0x01b3, B:63:0x01e7, B:65:0x020b, B:67:0x0219, B:71:0x024e), top: B:99:0x0028 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0246 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0276 A[Catch: all -> 0x02bd, TRY_LEAVE, TryCatch #4 {all -> 0x02bd, blocks: (B:73:0x0270, B:75:0x0276), top: B:102:0x0270 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02b2  */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v17, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r17v4, types: [java.io.Closeable] */
    /* JADX WARN: Unknown variable types count: 3 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x012f -> B:102:0x0270). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x0247 -> B:71:0x024e). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object migrationFromLegacy234(android.content.Context r32, android.database.sqlite.SQLiteDatabase r33, int r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) {
        /*
            Method dump skipped, instructions count: 715
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt.migrationFromLegacy234(android.content.Context, android.database.sqlite.SQLiteDatabase, int, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
