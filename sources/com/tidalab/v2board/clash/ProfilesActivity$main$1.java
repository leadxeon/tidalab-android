package com.tidalab.v2board.clash;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: ProfilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity", f = "ProfilesActivity.kt", l = {17, 81}, m = "main")
/* loaded from: classes.dex */
public final class ProfilesActivity$main$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProfilesActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesActivity$main$1(ProfilesActivity profilesActivity, Continuation<? super ProfilesActivity$main$1> continuation) {
        super(continuation);
        this.this$0 = profilesActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.main(this);
    }
}
