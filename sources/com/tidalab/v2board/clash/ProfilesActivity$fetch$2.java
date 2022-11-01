package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ProfilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity$fetch$2", f = "ProfilesActivity.kt", l = {74, 74}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfilesActivity$fetch$2 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ProfilesDesign $this_fetch;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesActivity$fetch$2(ProfilesDesign profilesDesign, Continuation<? super ProfilesActivity$fetch$2> continuation) {
        super(2, continuation);
        this.$this_fetch = profilesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProfilesActivity$fetch$2 profilesActivity$fetch$2 = new ProfilesActivity$fetch$2(this.$this_fetch, continuation);
        profilesActivity$fetch$2.L$0 = obj;
        return profilesActivity$fetch$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
        ProfilesActivity$fetch$2 profilesActivity$fetch$2 = new ProfilesActivity$fetch$2(this.$this_fetch, continuation);
        profilesActivity$fetch$2.L$0 = iProfileManager;
        return profilesActivity$fetch$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ProfilesDesign profilesDesign;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            profilesDesign = this.$this_fetch;
            this.L$0 = profilesDesign;
            this.label = 1;
            obj = ((IProfileManager) this.L$0).queryAll(this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            profilesDesign = (ProfilesDesign) this.L$0;
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            InputKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.L$0 = null;
        this.label = 2;
        if (profilesDesign.patchProfiles((List) obj, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
