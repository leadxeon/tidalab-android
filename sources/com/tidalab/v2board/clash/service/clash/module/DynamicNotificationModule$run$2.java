package com.tidalab.v2board.clash.service.clash.module;

import android.content.Intent;
import android.os.PowerManager;
import androidx.core.content.ContextCompat;
import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.selects.SelectBuilderImpl;
/* compiled from: DynamicNotificationModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.clash.module.DynamicNotificationModule$run$2", f = "DynamicNotificationModule.kt", l = {109}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class DynamicNotificationModule$run$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public int label;
    public final /* synthetic */ DynamicNotificationModule this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DynamicNotificationModule$run$2(DynamicNotificationModule dynamicNotificationModule, Continuation<? super DynamicNotificationModule$run$2> continuation) {
        super(2, continuation);
        this.this$0 = dynamicNotificationModule;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DynamicNotificationModule$run$2 dynamicNotificationModule$run$2 = new DynamicNotificationModule$run$2(this.this$0, continuation);
        dynamicNotificationModule$run$2.L$0 = obj;
        return dynamicNotificationModule$run$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        DynamicNotificationModule$run$2 dynamicNotificationModule$run$2 = new DynamicNotificationModule$run$2(this.this$0, continuation);
        dynamicNotificationModule$run$2.L$0 = coroutineScope;
        return dynamicNotificationModule$run$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref$BooleanRef ref$BooleanRef;
        ReceiveChannel<Intent> receiveChannel;
        ReceiveChannel receiveChannel2;
        Channel<Long> channel;
        SelectBuilderImpl selectBuilderImpl;
        Boolean valueOf;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            ref$BooleanRef = new Ref$BooleanRef();
            PowerManager powerManager = (PowerManager) ContextCompat.getSystemService(this.this$0.service, PowerManager.class);
            ref$BooleanRef.element = (powerManager == null || (valueOf = Boolean.valueOf(powerManager.isInteractive())) == null) ? true : valueOf.booleanValue();
            receiveChannel = this.this$0.receiveBroadcast(false, -1, $$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM.INSTANCE$1);
            receiveChannel2 = Module.receiveBroadcast$default(this.this$0, false, -1, $$LambdaGroup$ks$ODDKaohGUjxHBVvu7cx4SPN4ptM.INSTANCE$0, 1, null);
            channel = PathParser.ticker(coroutineScope, TimeUnit.SECONDS.toMillis(1L));
        } else if (i == 1) {
            DynamicNotificationModule dynamicNotificationModule = (DynamicNotificationModule) this.L$4;
            channel = (Channel) this.L$3;
            receiveChannel2 = (ReceiveChannel) this.L$2;
            receiveChannel = (ReceiveChannel) this.L$1;
            ref$BooleanRef = (Ref$BooleanRef) this.L$0;
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        do {
            DynamicNotificationModule dynamicNotificationModule2 = this.this$0;
            this.L$0 = ref$BooleanRef;
            this.L$1 = receiveChannel;
            this.L$2 = receiveChannel2;
            this.L$3 = channel;
            this.L$4 = dynamicNotificationModule2;
            this.label = 1;
            selectBuilderImpl = new SelectBuilderImpl(this);
            try {
                receiveChannel.getOnReceive().registerSelectClause1(selectBuilderImpl, new DynamicNotificationModule$run$2$1$1(ref$BooleanRef, null));
                receiveChannel2.getOnReceive().registerSelectClause1(selectBuilderImpl, new DynamicNotificationModule$run$2$1$2(dynamicNotificationModule2, null));
                if (ref$BooleanRef.element) {
                    channel.getOnReceive().registerSelectClause1(selectBuilderImpl, new DynamicNotificationModule$run$2$1$3(dynamicNotificationModule2, null));
                }
            } catch (Throwable th) {
                selectBuilderImpl.handleBuilderException(th);
            }
        } while (selectBuilderImpl.getResult() != coroutineSingletons);
        return coroutineSingletons;
    }
}
