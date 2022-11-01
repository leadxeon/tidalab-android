package com.tidalab.v2board.clash.service.data.migrations;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: LegacyMigration.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt", f = "LegacyMigration.kt", l = {34, 35}, m = "migrationFromLegacy")
/* loaded from: classes.dex */
public final class LegacyMigrationKt$migrationFromLegacy$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;

    public LegacyMigrationKt$migrationFromLegacy$1(Continuation<? super LegacyMigrationKt$migrationFromLegacy$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LegacyMigrationKt.migrationFromLegacy(null, this);
    }
}
