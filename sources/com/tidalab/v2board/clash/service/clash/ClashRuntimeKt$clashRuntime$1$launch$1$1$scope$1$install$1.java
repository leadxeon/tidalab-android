package com.tidalab.v2board.clash.service.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.clash.module.Module;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* JADX WARN: Incorrect field signature: TT; */
/* compiled from: ClashRuntime.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.ClashRuntimeKt$clashRuntime$1$launch$1$1$scope$1$install$1", f = "ClashRuntime.kt", l = {39}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ClashRuntimeKt$clashRuntime$1$launch$1$1$scope$1$install$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Module $module;
    public final /* synthetic */ List<Module<?>> $modules;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Incorrect types in method signature: (Ljava/util/List<Lcom/tidalab/v2board/clash/service/clash/module/Module<*>;>;TT;Lkotlin/coroutines/Continuation<-Lcom/tidalab/v2board/clash/service/clash/ClashRuntimeKt$clashRuntime$1$launch$1$1$scope$1$install$1;>;)V */
    public ClashRuntimeKt$clashRuntime$1$launch$1$1$scope$1$install$1(List list, Module module, Continuation continuation) {
        super(2, continuation);
        this.$modules = list;
        this.$module = module;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ClashRuntimeKt$clashRuntime$1$launch$1$1$scope$1$install$1(this.$modules, this.$module, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ClashRuntimeKt$clashRuntime$1$launch$1$1$scope$1$install$1(this.$modules, this.$module, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            this.$modules.add(this.$module);
            Module module = this.$module;
            this.label = 1;
            if (module.execute(this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
