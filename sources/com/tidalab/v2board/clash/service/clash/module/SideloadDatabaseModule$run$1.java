package com.tidalab.v2board.clash.service.clash.module;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: SideloadDatabaseModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.SideloadDatabaseModule", f = "SideloadDatabaseModule.kt", l = {34, 92, 79, 81, 83}, m = "run")
/* loaded from: classes.dex */
public final class SideloadDatabaseModule$run$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ SideloadDatabaseModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SideloadDatabaseModule$run$1(SideloadDatabaseModule sideloadDatabaseModule, Continuation<? super SideloadDatabaseModule$run$1> continuation) {
        super(continuation);
        this.this$0 = sideloadDatabaseModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.run(this);
    }
}
