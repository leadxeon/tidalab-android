package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.clash.module.SideloadDatabaseModule;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ClashService.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ClashService$runtime$1$quit$1$3", f = "ClashService.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ClashService$runtime$1$quit$1$3 extends SuspendLambda implements Function2<SideloadDatabaseModule.LoadException, Continuation<? super Boolean>, Object> {
    public /* synthetic */ Object L$0;
    public final /* synthetic */ ClashService this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClashService$runtime$1$quit$1$3(ClashService clashService, Continuation<? super ClashService$runtime$1$quit$1$3> continuation) {
        super(2, continuation);
        this.this$0 = clashService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClashService$runtime$1$quit$1$3 clashService$runtime$1$quit$1$3 = new ClashService$runtime$1$quit$1$3(this.this$0, continuation);
        clashService$runtime$1$quit$1$3.L$0 = obj;
        return clashService$runtime$1$quit$1$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(SideloadDatabaseModule.LoadException loadException, Continuation<? super Boolean> continuation) {
        SideloadDatabaseModule.LoadException loadException2 = loadException;
        Continuation<? super Boolean> continuation2 = continuation;
        ClashService clashService = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        clashService.reason = loadException2.message;
        return Boolean.TRUE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        this.this$0.reason = ((SideloadDatabaseModule.LoadException) this.L$0).message;
        return Boolean.TRUE;
    }
}
