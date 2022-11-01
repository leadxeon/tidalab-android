package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.MainDesign;
import com.tidalab.v2board.clash.design.MainDesign$setProfileName$2;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$fetch$2", f = "DashActivity.kt", l = {106, 106}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$fetch$2 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
    public final /* synthetic */ MainDesign $this_fetch;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$fetch$2(MainDesign mainDesign, Continuation<? super DashActivity$fetch$2> continuation) {
        super(2, continuation);
        this.$this_fetch = mainDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashActivity$fetch$2 dashActivity$fetch$2 = new DashActivity$fetch$2(this.$this_fetch, continuation);
        dashActivity$fetch$2.L$0 = obj;
        return dashActivity$fetch$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
        DashActivity$fetch$2 dashActivity$fetch$2 = new DashActivity$fetch$2(this.$this_fetch, continuation);
        dashActivity$fetch$2.L$0 = iProfileManager;
        return dashActivity$fetch$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MainDesign mainDesign;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            mainDesign = this.$this_fetch;
            this.L$0 = mainDesign;
            this.label = 1;
            obj = ((IProfileManager) this.L$0).queryActive(this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            mainDesign = (MainDesign) this.L$0;
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            InputKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Profile profile = (Profile) obj;
        String str = profile == null ? null : profile.name;
        this.L$0 = null;
        this.label = 2;
        Objects.requireNonNull(mainDesign);
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        Object withContext = InputKt.withContext(MainDispatcherLoader.dispatcher, new MainDesign$setProfileName$2(mainDesign, str, null), this);
        if (withContext != coroutineSingletons) {
            withContext = Unit.INSTANCE;
        }
        if (withContext == coroutineSingletons) {
            return coroutineSingletons;
        }
        return Unit.INSTANCE;
    }
}
