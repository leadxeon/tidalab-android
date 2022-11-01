package com.tidalab.v2board.clash.service.remote;

import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.model.Profile;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: IProfileManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate$onTransact$1", f = "IProfileManager.kt", l = {45}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class IProfileManagerDelegate$onTransact$1 extends SuspendLambda implements Function2<Parcel, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $name;
    public final /* synthetic */ String $source;
    public final /* synthetic */ Profile.Type $type;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ IProfileManagerDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IProfileManagerDelegate$onTransact$1(IProfileManagerDelegate iProfileManagerDelegate, Profile.Type type, String str, String str2, Continuation<? super IProfileManagerDelegate$onTransact$1> continuation) {
        super(2, continuation);
        this.this$0 = iProfileManagerDelegate;
        this.$type = type;
        this.$name = str;
        this.$source = str2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IProfileManagerDelegate$onTransact$1 iProfileManagerDelegate$onTransact$1 = new IProfileManagerDelegate$onTransact$1(this.this$0, this.$type, this.$name, this.$source, continuation);
        iProfileManagerDelegate$onTransact$1.L$0 = obj;
        return iProfileManagerDelegate$onTransact$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Parcel parcel, Continuation<? super Unit> continuation) {
        IProfileManagerDelegate$onTransact$1 iProfileManagerDelegate$onTransact$1 = new IProfileManagerDelegate$onTransact$1(this.this$0, this.$type, this.$name, this.$source, continuation);
        iProfileManagerDelegate$onTransact$1.L$0 = parcel;
        return iProfileManagerDelegate$onTransact$1.invokeSuspend(Unit.INSTANCE);
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
            Profile.Type type = this.$type;
            String str = this.$name;
            String str2 = this.$source;
            this.L$0 = parcel2;
            this.label = 1;
            Object create = iProfileManagerDelegate.create(type, str, str2, this);
            if (create == coroutineSingletons) {
                return coroutineSingletons;
            }
            parcel = parcel2;
            obj = create;
        } else if (i == 1) {
            parcel = (Parcel) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        parcel.writeNoException();
        parcel.writeSerializable((UUID) obj);
        return Unit.INSTANCE;
    }
}
