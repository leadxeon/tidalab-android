package com.tidalab.v2board.clash.service.clash.module;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: AppListCacheModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.AppListCacheModule", f = "AppListCacheModule.kt", l = {35, 37}, m = "run")
/* loaded from: classes.dex */
public final class AppListCacheModule$run$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ AppListCacheModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppListCacheModule$run$1(AppListCacheModule appListCacheModule, Continuation<? super AppListCacheModule$run$1> continuation) {
        super(continuation);
        this.this$0 = appListCacheModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.run(this);
    }
}
