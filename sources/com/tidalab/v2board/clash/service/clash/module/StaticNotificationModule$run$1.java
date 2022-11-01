package com.tidalab.v2board.clash.service.clash.module;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: StaticNotificationModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.StaticNotificationModule", f = "StaticNotificationModule.kt", l = {42}, m = "run")
/* loaded from: classes.dex */
public final class StaticNotificationModule$run$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ StaticNotificationModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StaticNotificationModule$run$1(StaticNotificationModule staticNotificationModule, Continuation<? super StaticNotificationModule$run$1> continuation) {
        super(continuation);
        this.this$0 = staticNotificationModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.run(this);
    }
}
