package com.tidalab.v2board.clash.service.remote;

import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: IProfileManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate$onTransact$11", f = "IProfileManager.kt", l = {169}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class IProfileManagerDelegate$onTransact$11 extends SuspendLambda implements Function2<Parcel, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Profile $profile;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ IProfileManagerDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IProfileManagerDelegate$onTransact$11(IProfileManagerDelegate iProfileManagerDelegate, Profile profile, Continuation<? super IProfileManagerDelegate$onTransact$11> continuation) {
        super(2, continuation);
        this.this$0 = iProfileManagerDelegate;
        this.$profile = profile;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IProfileManagerDelegate$onTransact$11 iProfileManagerDelegate$onTransact$11 = new IProfileManagerDelegate$onTransact$11(this.this$0, this.$profile, continuation);
        iProfileManagerDelegate$onTransact$11.L$0 = obj;
        return iProfileManagerDelegate$onTransact$11;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Parcel parcel, Continuation<? super Unit> continuation) {
        IProfileManagerDelegate$onTransact$11 iProfileManagerDelegate$onTransact$11 = new IProfileManagerDelegate$onTransact$11(this.this$0, this.$profile, continuation);
        iProfileManagerDelegate$onTransact$11.L$0 = parcel;
        return iProfileManagerDelegate$onTransact$11.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Parcel parcel;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Parcel parcel2 = (Parcel) this.L$0;
            IProfileManagerDelegate iProfileManagerDelegate = this.this$0;
            Profile profile = this.$profile;
            this.L$0 = parcel2;
            this.label = 1;
            if (iProfileManagerDelegate.setActive(profile, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            parcel = parcel2;
        } else if (i == 1) {
            parcel = (Parcel) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Unit unit = Unit.INSTANCE;
        parcel.writeNoException();
        return unit;
    }
}
