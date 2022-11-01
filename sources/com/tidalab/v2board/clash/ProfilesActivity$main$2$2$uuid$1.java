package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ProfilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity$main$2$2$uuid$1", f = "ProfilesActivity.kt", l = {57}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfilesActivity$main$2$2$uuid$1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super UUID>, Object> {
    public final /* synthetic */ ProfilesDesign.Request $it;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesActivity$main$2$2$uuid$1(ProfilesDesign.Request request, Continuation<? super ProfilesActivity$main$2$2$uuid$1> continuation) {
        super(2, continuation);
        this.$it = request;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProfilesActivity$main$2$2$uuid$1 profilesActivity$main$2$2$uuid$1 = new ProfilesActivity$main$2$2$uuid$1(this.$it, continuation);
        profilesActivity$main$2$2$uuid$1.L$0 = obj;
        return profilesActivity$main$2$2$uuid$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IProfileManager iProfileManager, Continuation<? super UUID> continuation) {
        ProfilesActivity$main$2$2$uuid$1 profilesActivity$main$2$2$uuid$1 = new ProfilesActivity$main$2$2$uuid$1(this.$it, continuation);
        profilesActivity$main$2$2$uuid$1.L$0 = iProfileManager;
        return profilesActivity$main$2$2$uuid$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            UUID uuid = ((ProfilesDesign.Request.Duplicate) this.$it).profile.uuid;
            this.label = 1;
            obj = ((IProfileManager) this.L$0).clone(uuid, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
