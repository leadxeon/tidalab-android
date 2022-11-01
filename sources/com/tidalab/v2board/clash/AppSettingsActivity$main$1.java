package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: AppSettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AppSettingsActivity", f = "AppSettingsActivity.kt", l = {22, 66}, m = "main")
/* loaded from: classes.dex */
public final class AppSettingsActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ AppSettingsActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppSettingsActivity$main$1(AppSettingsActivity appSettingsActivity, Continuation<? super AppSettingsActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = appSettingsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
