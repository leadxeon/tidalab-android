package com.tidalab.v2board.clash.service.util;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: Database.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.util.DatabaseKt", f = "Database.kt", l = {10, 10}, m = "generateProfileUUID")
/* loaded from: classes.dex */
public final class DatabaseKt$generateProfileUUID$1 extends ContinuationImpl {
    public Object L$0;
    public int label;
    public /* synthetic */ Object result;

    public DatabaseKt$generateProfileUUID$1(Continuation<? super DatabaseKt$generateProfileUUID$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return InputKt.generateProfileUUID(this);
    }
}
