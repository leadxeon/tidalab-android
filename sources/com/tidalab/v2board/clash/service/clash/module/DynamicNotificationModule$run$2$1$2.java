package com.tidalab.v2board.clash.service.clash.module;

import android.content.Intent;
import androidx.core.app.NotificationCompat$Builder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.StatusProvider;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
/* compiled from: DynamicNotificationModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.DynamicNotificationModule$run$2$1$2", f = "DynamicNotificationModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DynamicNotificationModule$run$2$1$2 extends SuspendLambda implements Function2<Intent, Continuation<? super Unit>, Object> {
    public final /* synthetic */ DynamicNotificationModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicNotificationModule$run$2$1$2(DynamicNotificationModule dynamicNotificationModule, Continuation<? super DynamicNotificationModule$run$2$1$2> continuation) {
        super(2, continuation);
        this.this$0 = dynamicNotificationModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DynamicNotificationModule$run$2$1$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Intent intent, Continuation<? super Unit> continuation) {
        Continuation<? super Unit> continuation2 = continuation;
        DynamicNotificationModule dynamicNotificationModule = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        NotificationCompat$Builder notificationCompat$Builder = dynamicNotificationModule.builder;
        String str = StatusProvider.currentProfile;
        if (str == null) {
            str = "Not selected";
        }
        notificationCompat$Builder.setContentTitle(str);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        NotificationCompat$Builder notificationCompat$Builder = this.this$0.builder;
        String str = StatusProvider.currentProfile;
        if (str == null) {
            str = "Not selected";
        }
        notificationCompat$Builder.setContentTitle(str);
        return Unit.INSTANCE;
    }
}
