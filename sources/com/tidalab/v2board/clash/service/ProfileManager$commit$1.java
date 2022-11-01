package com.tidalab.v2board.clash.service;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: ProfileManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileManager", f = "ProfileManager.kt", l = {114, 116}, m = "commit")
/* loaded from: classes.dex */
public final class ProfileManager$commit$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProfileManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileManager$commit$1(ProfileManager profileManager, Continuation<? super ProfileManager$commit$1> continuation) {
        super(continuation);
        this.this$0 = profileManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.commit(null, null, this);
    }
}
