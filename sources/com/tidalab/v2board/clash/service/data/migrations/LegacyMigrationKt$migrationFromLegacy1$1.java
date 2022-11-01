package com.tidalab.v2board.clash.service.data.migrations;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: LegacyMigration.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt", f = "LegacyMigration.kt", l = {163, 189}, m = "migrationFromLegacy1")
/* loaded from: classes.dex */
public final class LegacyMigrationKt$migrationFromLegacy1$1 extends ContinuationImpl {
    public int I$0;
    public int I$1;
    public int I$2;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public Object L$5;
    public int label;
    public /* synthetic */ Object result;

    public LegacyMigrationKt$migrationFromLegacy1$1(Continuation<? super LegacyMigrationKt$migrationFromLegacy1$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LegacyMigrationKt.migrationFromLegacy1(null, null, this);
    }
}
