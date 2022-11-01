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
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate$onTransact$10", f = "IProfileManager.kt", l = {153}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class IProfileManagerDelegate$onTransact$10 extends SuspendLambda implements Function2<Parcel, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ IProfileManagerDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IProfileManagerDelegate$onTransact$10(IProfileManagerDelegate iProfileManagerDelegate, Continuation<? super IProfileManagerDelegate$onTransact$10> continuation) {
        super(2, continuation);
        this.this$0 = iProfileManagerDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IProfileManagerDelegate$onTransact$10 iProfileManagerDelegate$onTransact$10 = new IProfileManagerDelegate$onTransact$10(this.this$0, continuation);
        iProfileManagerDelegate$onTransact$10.L$0 = obj;
        return iProfileManagerDelegate$onTransact$10;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Parcel parcel, Continuation<? super Unit> continuation) {
        IProfileManagerDelegate$onTransact$10 iProfileManagerDelegate$onTransact$10 = new IProfileManagerDelegate$onTransact$10(this.this$0, continuation);
        iProfileManagerDelegate$onTransact$10.L$0 = parcel;
        return iProfileManagerDelegate$onTransact$10.invokeSuspend(Unit.INSTANCE);
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
            this.L$0 = parcel2;
            this.label = 1;
            Object queryActive = iProfileManagerDelegate.$$delegate_0.queryActive(this);
            if (queryActive == coroutineSingletons) {
                return coroutineSingletons;
            }
            parcel = parcel2;
            obj = queryActive;
        } else if (i == 1) {
            parcel = (Parcel) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Profile profile = (Profile) obj;
        parcel.writeNoException();
        if (profile != null) {
            parcel.writeInt(1);
            profile.writeToParcel(parcel, 0);
        } else {
            parcel.writeInt(0);
        }
        return Unit.INSTANCE;
    }
}
