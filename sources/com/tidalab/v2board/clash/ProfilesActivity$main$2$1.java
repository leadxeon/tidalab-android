package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.design.ProfilesDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: ProfilesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProfilesActivity$main$2$1", f = "ProfilesActivity.kt", l = {26}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfilesActivity$main$2$1 extends SuspendLambda implements Function2<BaseActivity.Event, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ProfilesDesign $design;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ProfilesActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfilesActivity$main$2$1(ProfilesActivity profilesActivity, ProfilesDesign profilesDesign, Continuation<? super ProfilesActivity$main$2$1> continuation) {
        super(2, continuation);
        this.this$0 = profilesActivity;
        this.$design = profilesDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProfilesActivity$main$2$1 profilesActivity$main$2$1 = new ProfilesActivity$main$2$1(this.this$0, this.$design, continuation);
        profilesActivity$main$2$1.L$0 = obj;
        return profilesActivity$main$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(BaseActivity.Event event, Continuation<? super Unit> continuation) {
        ProfilesActivity$main$2$1 profilesActivity$main$2$1 = new ProfilesActivity$main$2$1(this.this$0, this.$design, continuation);
        profilesActivity$main$2$1.L$0 = event;
        return profilesActivity$main$2$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2 = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            int ordinal = ((BaseActivity.Event) this.L$0).ordinal();
            if (ordinal == 1 || ordinal == 6) {
                ProfilesActivity profilesActivity = this.this$0;
                ProfilesDesign profilesDesign = this.$design;
                this.label = 1;
                int i2 = ProfilesActivity.$r8$clinit;
                Objects.requireNonNull(profilesActivity);
                Object withProfile$default = InputKt.withProfile$default(null, new ProfilesActivity$fetch$2(profilesDesign, null), this, 1);
                if (withProfile$default != obj2) {
                    withProfile$default = Unit.INSTANCE;
                }
                if (withProfile$default == obj2) {
                    return obj2;
                }
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
