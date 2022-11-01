package com.tidalab.v2board.clash.common.util;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: Ticker.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.common.util.TickerKt$ticker$1", f = "Ticker.kt", l = {WebSocketProtocol.B0_MASK_OPCODE, 17}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class TickerKt$ticker$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Channel<Long> $channel;
    public final /* synthetic */ long $period;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TickerKt$ticker$1(Channel<Long> channel, long j, Continuation<? super TickerKt$ticker$1> continuation) {
        super(2, continuation);
        this.$channel = channel;
        this.$period = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TickerKt$ticker$1 tickerKt$ticker$1 = new TickerKt$ticker$1(this.$channel, this.$period, continuation);
        tickerKt$ticker$1.L$0 = obj;
        return tickerKt$ticker$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        TickerKt$ticker$1 tickerKt$ticker$1 = new TickerKt$ticker$1(this.$channel, this.$period, continuation);
        tickerKt$ticker$1.L$0 = coroutineScope;
        return tickerKt$ticker$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0035 A[Catch: Exception -> 0x0058, TryCatch #0 {Exception -> 0x0058, blocks: (B:7:0x0010, B:12:0x0021, B:16:0x002f, B:18:0x0035, B:21:0x004b), top: B:27:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0057 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0055 -> B:16:0x002f). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 == r3) goto L_0x001d
            if (r1 != r2) goto L_0x0015
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)     // Catch: Exception -> 0x0058
            r9 = r1
            goto L_0x002e
        L_0x0015:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001d:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)     // Catch: Exception -> 0x0058
            r9 = r1
            r1 = r8
            goto L_0x004b
        L_0x0027:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            kotlinx.coroutines.CoroutineScope r9 = (kotlinx.coroutines.CoroutineScope) r9
        L_0x002e:
            r1 = r8
        L_0x002f:
            boolean r4 = com.tidalab.v2board.clash.design.dialog.InputKt.isActive(r9)     // Catch: Exception -> 0x0058
            if (r4 == 0) goto L_0x0058
            kotlinx.coroutines.channels.Channel<java.lang.Long> r4 = r1.$channel     // Catch: Exception -> 0x0058
            long r5 = java.lang.System.currentTimeMillis()     // Catch: Exception -> 0x0058
            java.lang.Long r7 = new java.lang.Long     // Catch: Exception -> 0x0058
            r7.<init>(r5)     // Catch: Exception -> 0x0058
            r1.L$0 = r9     // Catch: Exception -> 0x0058
            r1.label = r3     // Catch: Exception -> 0x0058
            java.lang.Object r4 = r4.send(r7, r1)     // Catch: Exception -> 0x0058
            if (r4 != r0) goto L_0x004b
            return r0
        L_0x004b:
            long r4 = r1.$period     // Catch: Exception -> 0x0058
            r1.L$0 = r9     // Catch: Exception -> 0x0058
            r1.label = r2     // Catch: Exception -> 0x0058
            java.lang.Object r4 = com.tidalab.v2board.clash.design.dialog.InputKt.delay(r4, r1)     // Catch: Exception -> 0x0058
            if (r4 != r0) goto L_0x002f
            return r0
        L_0x0058:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.common.util.TickerKt$ticker$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
