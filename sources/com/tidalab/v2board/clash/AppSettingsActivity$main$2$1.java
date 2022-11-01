package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: AppSettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AppSettingsActivity$main$2$1", f = "AppSettingsActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AppSettingsActivity$main$2$1 extends SuspendLambda implements Function2<BaseActivity.Event, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public final /* synthetic */ AppSettingsActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppSettingsActivity$main$2$1(AppSettingsActivity appSettingsActivity, Continuation<? super AppSettingsActivity$main$2$1> continuation) {
        super(2, continuation);
        this.this$0 = appSettingsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AppSettingsActivity$main$2$1 appSettingsActivity$main$2$1 = new AppSettingsActivity$main$2$1(this.this$0, continuation);
        appSettingsActivity$main$2$1.L$0 = obj;
        return appSettingsActivity$main$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(BaseActivity.Event event, Continuation<? super Unit> continuation) {
        BaseActivity.Event event2 = event;
        Continuation<? super Unit> continuation2 = continuation;
        AppSettingsActivity appSettingsActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        int ordinal = event2.ordinal();
        if (ordinal == 0 || ordinal == 3 || ordinal == 4) {
            appSettingsActivity.recreate();
        }
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        int ordinal = ((BaseActivity.Event) this.L$0).ordinal();
        if (ordinal == 0 || ordinal == 3 || ordinal == 4) {
            this.this$0.recreate();
        }
        return Unit.INSTANCE;
    }
}
