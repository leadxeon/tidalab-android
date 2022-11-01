package com.tidalab.v2board.clash.service.remote;

import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: IProfileManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate$onTransact$6", f = "IProfileManager.kt", l = {105}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class IProfileManagerDelegate$onTransact$6 extends SuspendLambda implements Function2<Parcel, Continuation<? super Unit>, Object> {
    public final /* synthetic */ long $interval;
    public final /* synthetic */ String $name;
    public final /* synthetic */ String $source;
    public final /* synthetic */ UUID $uuid;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ IProfileManagerDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IProfileManagerDelegate$onTransact$6(IProfileManagerDelegate iProfileManagerDelegate, UUID uuid, String str, String str2, long j, Continuation<? super IProfileManagerDelegate$onTransact$6> continuation) {
        super(2, continuation);
        this.this$0 = iProfileManagerDelegate;
        this.$uuid = uuid;
        this.$name = str;
        this.$source = str2;
        this.$interval = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IProfileManagerDelegate$onTransact$6 iProfileManagerDelegate$onTransact$6 = new IProfileManagerDelegate$onTransact$6(this.this$0, this.$uuid, this.$name, this.$source, this.$interval, continuation);
        iProfileManagerDelegate$onTransact$6.L$0 = obj;
        return iProfileManagerDelegate$onTransact$6;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Parcel parcel, Continuation<? super Unit> continuation) {
        return ((IProfileManagerDelegate$onTransact$6) create(parcel, continuation)).invokeSuspend(Unit.INSTANCE);
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
            UUID uuid = this.$uuid;
            String str = this.$name;
            String str2 = this.$source;
            long j = this.$interval;
            this.L$0 = parcel2;
            this.label = 1;
            if (iProfileManagerDelegate.patch(uuid, str, str2, j, this) == coroutineSingletons) {
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
