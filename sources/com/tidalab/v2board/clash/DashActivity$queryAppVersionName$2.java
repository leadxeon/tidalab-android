package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: DashActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.DashActivity$queryAppVersionName$2", f = "DashActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DashActivity$queryAppVersionName$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    public final /* synthetic */ DashActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashActivity$queryAppVersionName$2(DashActivity dashActivity, Continuation<? super DashActivity$queryAppVersionName$2> continuation) {
        super(2, continuation);
        this.this$0 = dashActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashActivity$queryAppVersionName$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        Continuation<? super String> continuation2 = continuation;
        DashActivity dashActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return dashActivity.getPackageManager().getPackageInfo(dashActivity.getPackageName(), 0).versionName;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return this.this$0.getPackageManager().getPackageInfo(this.this$0.getPackageName(), 0).versionName;
    }
}
