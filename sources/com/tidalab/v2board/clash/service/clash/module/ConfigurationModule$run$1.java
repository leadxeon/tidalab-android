package com.tidalab.v2board.clash.service.clash.module;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: ConfigurationModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.ConfigurationModule", f = "ConfigurationModule.kt", l = {84, 55, 58, 60, WebSocketProtocol.B0_FLAG_RSV1, 72}, m = "run")
/* loaded from: classes.dex */
public final class ConfigurationModule$run$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ConfigurationModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConfigurationModule$run$1(ConfigurationModule configurationModule, Continuation<? super ConfigurationModule$run$1> continuation) {
        super(continuation);
        this.this$0 = configurationModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.run(this);
    }
}
