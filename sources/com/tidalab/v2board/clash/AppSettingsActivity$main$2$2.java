package com.tidalab.v2board.clash;

import android.app.Activity;
import com.tidalab.v2board.clash.design.AppSettingsDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.util.ApplicationObserver;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: AppSettingsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.AppSettingsActivity$main$2$2", f = "AppSettingsActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class AppSettingsActivity$main$2$2 extends SuspendLambda implements Function2<AppSettingsDesign.Request, Continuation<? super Unit>, Object> {
    public AppSettingsActivity$main$2$2(Continuation<? super AppSettingsActivity$main$2$2> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppSettingsActivity$main$2$2(continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(AppSettingsDesign.Request request, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        InputKt.throwOnFailure(Unit.INSTANCE);
        ApplicationObserver applicationObserver = ApplicationObserver.INSTANCE;
        for (Activity activity : ApplicationObserver.activities) {
            activity.recreate();
        }
        return Unit.INSTANCE;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        ApplicationObserver applicationObserver = ApplicationObserver.INSTANCE;
        for (Activity activity : ApplicationObserver.activities) {
            activity.recreate();
        }
        return Unit.INSTANCE;
    }
}
