package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.adapter.AppAdapter;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AccessControlDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.AccessControlDesign$rebindAll$2", f = "AccessControlDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AccessControlDesign$rebindAll$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ AccessControlDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessControlDesign$rebindAll$2(AccessControlDesign accessControlDesign, Continuation<? super AccessControlDesign$rebindAll$2> continuation) {
        super(2, continuation);
        this.this$0 = accessControlDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AccessControlDesign$rebindAll$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        AccessControlDesign accessControlDesign = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        AppAdapter appAdapter = accessControlDesign.adapter;
        appAdapter.mObservable.notifyItemRangeChanged(0, appAdapter.getItemCount(), null);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        AppAdapter appAdapter = this.this$0.adapter;
        appAdapter.mObservable.notifyItemRangeChanged(0, appAdapter.getItemCount(), null);
        return Unit.INSTANCE;
    }
}
