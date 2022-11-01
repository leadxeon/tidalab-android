package com.tidalab.v2board.clash.design;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
/* compiled from: ProfilesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.ProfilesDesign", f = "ProfilesDesign.kt", l = {37, 40, 44}, m = "patchProfiles")
/* loaded from: classes.dex */
public final class ProfilesDesign$patchProfiles$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;
    public /* synthetic */ Object result;
    public final /* synthetic */ ProfilesDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesDesign$patchProfiles$1(ProfilesDesign profilesDesign, Continuation<? super ProfilesDesign$patchProfiles$1> continuation) {
        super(continuation);
        this.this$0 = profilesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.patchProfiles(null, this);
    }
}
