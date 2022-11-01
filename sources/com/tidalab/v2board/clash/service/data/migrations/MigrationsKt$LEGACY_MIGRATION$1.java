package com.tidalab.v2board.clash.service.data.migrations;

import android.content.Context;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
/* compiled from: Migrations.kt */
/* loaded from: classes.dex */
public /* synthetic */ class MigrationsKt$LEGACY_MIGRATION$1 extends FunctionReferenceImpl implements Function2<Context, Unit> {
    public static final MigrationsKt$LEGACY_MIGRATION$1 INSTANCE = new MigrationsKt$LEGACY_MIGRATION$1();

    public MigrationsKt$LEGACY_MIGRATION$1() {
        super(2, LegacyMigrationKt.class, "migrationFromLegacy", "migrationFromLegacy(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 1);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Object obj, Object obj2) {
        return LegacyMigrationKt.migrationFromLegacy((Context) obj, (Continuation) obj2);
    }
}
