package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfilesDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.ProfilesDesign$patchProfiles$3", f = "ProfilesDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfilesDesign$patchProfiles$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ boolean $updatable;
    public final /* synthetic */ ProfilesDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesDesign$patchProfiles$3(ProfilesDesign profilesDesign, boolean z, Continuation<? super ProfilesDesign$patchProfiles$3> continuation) {
        super(2, continuation);
        this.this$0 = profilesDesign;
        this.$updatable = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfilesDesign$patchProfiles$3(this.this$0, this.$updatable, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        ProfilesDesign profilesDesign = this.this$0;
        boolean z = this.$updatable;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        profilesDesign.binding.updateView.setVisibility(z ? 0 : 8);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        this.this$0.binding.updateView.setVisibility(this.$updatable ? 0 : 8);
        return Unit.INSTANCE;
    }
}
