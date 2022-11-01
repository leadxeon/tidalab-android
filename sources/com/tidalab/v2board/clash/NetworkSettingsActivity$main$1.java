package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: NetworkSettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NetworkSettingsActivity", f = "NetworkSettingsActivity.kt", l = {18, 44}, m = "main")
/* loaded from: classes.dex */
public final class NetworkSettingsActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ NetworkSettingsActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NetworkSettingsActivity$main$1(NetworkSettingsActivity networkSettingsActivity, Continuation<? super NetworkSettingsActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = networkSettingsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
