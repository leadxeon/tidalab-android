package com.tidalab.v2board.clash;

import android.content.pm.PackageInfo;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: AppCrashedActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AppCrashedActivity$main$packageInfo$1", f = "AppCrashedActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AppCrashedActivity$main$packageInfo$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PackageInfo>, Object> {
    public final /* synthetic */ AppCrashedActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCrashedActivity$main$packageInfo$1(AppCrashedActivity appCrashedActivity, Continuation<? super AppCrashedActivity$main$packageInfo$1> continuation) {
        super(2, continuation);
        this.this$0 = appCrashedActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppCrashedActivity$main$packageInfo$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super PackageInfo> continuation) {
        Continuation<? super PackageInfo> continuation2 = continuation;
        AppCrashedActivity appCrashedActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        return appCrashedActivity.getPackageManager().getPackageInfo(appCrashedActivity.getPackageName(), 0);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        return this.this$0.getPackageManager().getPackageInfo(this.this$0.getPackageName(), 0);
    }
}
