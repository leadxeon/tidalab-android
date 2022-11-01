package com.tidalab.v2board.clash.service.clash.module;

import androidx.core.app.NotificationCompat$Builder;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.bridge.Bridge;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.foss.R;
import java.util.Objects;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DynamicNotificationModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.DynamicNotificationModule$run$2$1$3", f = "DynamicNotificationModule.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DynamicNotificationModule$run$2$1$3 extends SuspendLambda implements Function2<Long, Continuation<? super Unit>, Object> {
    public final /* synthetic */ DynamicNotificationModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicNotificationModule$run$2$1$3(DynamicNotificationModule dynamicNotificationModule, Continuation<? super DynamicNotificationModule$run$2$1$3> continuation) {
        super(2, continuation);
        this.this$0 = dynamicNotificationModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DynamicNotificationModule$run$2$1$3(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Long l, Continuation<? super Unit> continuation) {
        l.longValue();
        return new DynamicNotificationModule$run$2$1$3(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        DynamicNotificationModule dynamicNotificationModule = this.this$0;
        Objects.requireNonNull(dynamicNotificationModule);
        Clash clash = Clash.INSTANCE;
        Bridge bridge = Bridge.INSTANCE;
        long nativeQueryTrafficNow = bridge.nativeQueryTrafficNow();
        long nativeQueryTrafficTotal = bridge.nativeQueryTrafficTotal();
        String trafficString = PathParser.trafficString(PathParser.scaleTraffic(nativeQueryTrafficNow >>> 32));
        String trafficString2 = PathParser.trafficString(PathParser.scaleTraffic(nativeQueryTrafficNow & 4294967295L));
        String trafficString3 = PathParser.trafficString(PathParser.scaleTraffic(nativeQueryTrafficTotal >>> 32));
        String trafficString4 = PathParser.trafficString(PathParser.scaleTraffic(nativeQueryTrafficTotal & 4294967295L));
        NotificationCompat$Builder notificationCompat$Builder = dynamicNotificationModule.builder;
        notificationCompat$Builder.setContentText(dynamicNotificationModule.service.getString(R.string.clash_notification_content, new Object[]{Intrinsics.stringPlus(trafficString, "/s"), Intrinsics.stringPlus(trafficString2, "/s")}));
        notificationCompat$Builder.mSubText = NotificationCompat$Builder.limitCharSequenceLength(dynamicNotificationModule.service.getString(R.string.clash_notification_content, new Object[]{trafficString3, trafficString4}));
        dynamicNotificationModule.service.startForeground(R.id.nf_clash_status, notificationCompat$Builder.build());
        return Unit.INSTANCE;
    }
}
