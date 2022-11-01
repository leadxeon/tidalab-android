package com.tidalab.v2board.clash;

import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.core.model.Provider;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
/* compiled from: ProvidersActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.ProvidersActivity$main$2$1", f = "ProvidersActivity.kt", l = {27}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProvidersActivity$main$2$1 extends SuspendLambda implements Function2<BaseActivity.Event, Continuation<? super Unit>, Object> {
    public final /* synthetic */ List<Provider> $providers;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ ProvidersActivity this$0;

    /* compiled from: ProvidersActivity.kt */
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            BaseActivity.Event.values();
            int[] iArr = new int[7];
            iArr[5] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProvidersActivity$main$2$1(List<Provider> list, ProvidersActivity providersActivity, Continuation<? super ProvidersActivity$main$2$1> continuation) {
        super(2, continuation);
        this.$providers = list;
        this.this$0 = providersActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ProvidersActivity$main$2$1 providersActivity$main$2$1 = new ProvidersActivity$main$2$1(this.$providers, this.this$0, continuation);
        providersActivity$main$2$1.L$0 = obj;
        return providersActivity$main$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(BaseActivity.Event event, Continuation<? super Unit> continuation) {
        ProvidersActivity$main$2$1 providersActivity$main$2$1 = new ProvidersActivity$main$2$1(this.$providers, this.this$0, continuation);
        providersActivity$main$2$1.L$0 = event;
        return providersActivity$main$2$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            if (WhenMappings.$EnumSwitchMapping$0[((BaseActivity.Event) this.L$0).ordinal()] == 1) {
                ProvidersActivity$main$2$1$newList$1 providersActivity$main$2$1$newList$1 = new ProvidersActivity$main$2$1$newList$1(null);
                this.label = 1;
                obj = InputKt.withClash$default(null, providersActivity$main$2$1$newList$1, this, 1);
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
        if (!Intrinsics.areEqual((List) obj, this.$providers)) {
            this.this$0.startActivity(PathParser.getIntent(Reflection.getOrCreateKotlinClass(ProvidersActivity.class)));
            this.this$0.finish();
        }
        return Unit.INSTANCE;
    }
}
