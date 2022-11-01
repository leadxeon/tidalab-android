package com.tidalab.v2board.clash.service.remote;

import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: IProfileManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate$onTransact$9", f = "IProfileManager.kt", l = {140}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class IProfileManagerDelegate$onTransact$9 extends SuspendLambda implements Function2<Parcel, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ IProfileManagerDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IProfileManagerDelegate$onTransact$9(IProfileManagerDelegate iProfileManagerDelegate, Continuation<? super IProfileManagerDelegate$onTransact$9> continuation) {
        super(2, continuation);
        this.this$0 = iProfileManagerDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IProfileManagerDelegate$onTransact$9 iProfileManagerDelegate$onTransact$9 = new IProfileManagerDelegate$onTransact$9(this.this$0, continuation);
        iProfileManagerDelegate$onTransact$9.L$0 = obj;
        return iProfileManagerDelegate$onTransact$9;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Parcel parcel, Continuation<? super Unit> continuation) {
        IProfileManagerDelegate$onTransact$9 iProfileManagerDelegate$onTransact$9 = new IProfileManagerDelegate$onTransact$9(this.this$0, continuation);
        iProfileManagerDelegate$onTransact$9.L$0 = parcel;
        return iProfileManagerDelegate$onTransact$9.invokeSuspend(Unit.INSTANCE);
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
            Object queryAll = iProfileManagerDelegate.$$delegate_0.queryAll(this);
            if (queryAll == coroutineSingletons) {
                return coroutineSingletons;
            }
            parcel = parcel2;
            obj = queryAll;
        } else if (i == 1) {
            parcel = (Parcel) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        List<Profile> list = (List) obj;
        parcel.writeNoException();
        parcel.writeInt(list.size());
        for (Profile profile : list) {
            profile.writeToParcel(parcel, 0);
        }
        return Unit.INSTANCE;
    }
}
