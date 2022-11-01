package com.tidalab.v2board.clash.service;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: ProfileWorker.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileWorker", f = "ProfileWorker.kt", l = {82, 86, 231, 231}, m = "run")
/* loaded from: classes.dex */
public final class ProfileWorker$run$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProfileWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileWorker$run$1(ProfileWorker profileWorker, Continuation<? super ProfileWorker$run$1> continuation) {
        super(continuation);
        this.this$0 = profileWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ProfileWorker.access$run(this.this$0, null, this);
    }
}
