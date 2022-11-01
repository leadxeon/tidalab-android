package com.tidalab.v2board.clash.design;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: MainDesign.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.design.MainDesign$setProfileName$2", f = "MainDesign.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class MainDesign$setProfileName$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $name;
    public final /* synthetic */ MainDesign this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MainDesign$setProfileName$2(MainDesign mainDesign, String str, Continuation<? super MainDesign$setProfileName$2> continuation) {
        super(2, continuation);
        this.this$0 = mainDesign;
        this.$name = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainDesign$setProfileName$2(this.this$0, this.$name, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        MainDesign mainDesign = this.this$0;
        String str = this.$name;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        mainDesign.binding.setProfileName(str);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        this.this$0.binding.setProfileName(this.$name);
        return Unit.INSTANCE;
    }
}
