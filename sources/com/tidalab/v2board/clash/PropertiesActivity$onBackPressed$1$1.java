package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.PropertiesDesign;
import com.tidalab.v2board.clash.design.PropertiesDesign$requestExitWithoutSaving$2;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.internal.MainDispatcherLoader;
/* compiled from: PropertiesActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.PropertiesActivity$onBackPressed$1$1", f = "PropertiesActivity.kt", l = {74}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class PropertiesActivity$onBackPressed$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ PropertiesDesign $this_apply;
    public int label;
    public final /* synthetic */ PropertiesActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PropertiesActivity$onBackPressed$1$1(PropertiesDesign propertiesDesign, PropertiesActivity propertiesActivity, Continuation<? super PropertiesActivity$onBackPressed$1$1> continuation) {
        super(2, continuation);
        this.$this_apply = propertiesDesign;
        this.this$0 = propertiesActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PropertiesActivity$onBackPressed$1$1(this.$this_apply, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new PropertiesActivity$onBackPressed$1$1(this.$this_apply, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            PropertiesDesign propertiesDesign = this.$this_apply;
            if (!propertiesDesign.binding.mProcessing) {
                this.label = 1;
                Objects.requireNonNull(propertiesDesign);
                Dispatchers dispatchers = Dispatchers.INSTANCE;
                obj = InputKt.withContext(MainDispatcherLoader.dispatcher, new PropertiesDesign$requestExitWithoutSaving$2(propertiesDesign, null), this);
                if (obj == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
            return Unit.INSTANCE;
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (((Boolean) obj).booleanValue()) {
            this.this$0.finish();
        }
        return Unit.INSTANCE;
    }
}
