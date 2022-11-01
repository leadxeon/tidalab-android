package com.tidalab.v2board.clash.service.remote;

import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: IClashManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.remote.IClashManagerDelegate$onTransact$2", f = "IClashManager.kt", l = {106}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class IClashManagerDelegate$onTransact$2 extends SuspendLambda implements Function2<Parcel, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $group;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ IClashManagerDelegate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IClashManagerDelegate$onTransact$2(IClashManagerDelegate iClashManagerDelegate, String str, Continuation<? super IClashManagerDelegate$onTransact$2> continuation) {
        super(2, continuation);
        this.this$0 = iClashManagerDelegate;
        this.$group = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        IClashManagerDelegate$onTransact$2 iClashManagerDelegate$onTransact$2 = new IClashManagerDelegate$onTransact$2(this.this$0, this.$group, continuation);
        iClashManagerDelegate$onTransact$2.L$0 = obj;
        return iClashManagerDelegate$onTransact$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Parcel parcel, Continuation<? super Unit> continuation) {
        IClashManagerDelegate$onTransact$2 iClashManagerDelegate$onTransact$2 = new IClashManagerDelegate$onTransact$2(this.this$0, this.$group, continuation);
        iClashManagerDelegate$onTransact$2.L$0 = parcel;
        return iClashManagerDelegate$onTransact$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Parcel parcel;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            Parcel parcel2 = (Parcel) this.L$0;
            IClashManagerDelegate iClashManagerDelegate = this.this$0;
            String str = this.$group;
            this.L$0 = parcel2;
            this.label = 1;
            if (iClashManagerDelegate.healthCheck(str, this) == coroutineSingletons) {
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
