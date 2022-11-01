package com.tidalab.v2board.clash.service.data.migrations;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: LegacyMigration.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.data.migrations.LegacyMigrationKt", f = "LegacyMigration.kt", l = {90, 114}, m = "migrationFromLegacy234")
/* loaded from: classes.dex */
public final class LegacyMigrationKt$migrationFromLegacy234$1 extends ContinuationImpl {
    public int I$0;
    public int I$1;
    public int I$2;
    public int I$3;
    public int I$4;
    public int I$5;
    public int I$6;
    public long J$0;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public int label;
    public /* synthetic */ Object result;

    public LegacyMigrationKt$migrationFromLegacy234$1(Continuation<? super LegacyMigrationKt$migrationFromLegacy234$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return LegacyMigrationKt.migrationFromLegacy234(null, null, 0, this);
    }
}
