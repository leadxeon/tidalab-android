package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.core.Clash;
import com.tidalab.v2board.clash.core.bridge.Bridge;
import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.ILogObserver;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;
/* compiled from: ClashManager.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ClashManager$setLogObserver$1$2$1", f = "ClashManager.kt", l = {92, 100, 100, 100, 100}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ClashManager$setLogObserver$1$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ReceiveChannel<LogMessage> $c;
    public final /* synthetic */ ILogObserver $observer;
    public /* synthetic */ Object L$0;
    public Object L$1;
    public int label;

    /* compiled from: ClashManager.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.service.ClashManager$setLogObserver$1$2$1$1", f = "ClashManager.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.service.ClashManager$setLogObserver$1$2$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ReceiveChannel<LogMessage> $c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ReceiveChannel<LogMessage> receiveChannel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$c = receiveChannel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$c, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            ReceiveChannel<LogMessage> receiveChannel = this.$c;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            receiveChannel.cancel(null);
            Clash clash = Clash.INSTANCE;
            Bridge.INSTANCE.nativeForceGc();
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            this.$c.cancel(null);
            Clash clash = Clash.INSTANCE;
            Bridge.INSTANCE.nativeForceGc();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClashManager$setLogObserver$1$2$1(ILogObserver iLogObserver, ReceiveChannel<LogMessage> receiveChannel, Continuation<? super ClashManager$setLogObserver$1$2$1> continuation) {
        super(2, continuation);
        this.$observer = iLogObserver;
        this.$c = receiveChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClashManager$setLogObserver$1$2$1 clashManager$setLogObserver$1$2$1 = new ClashManager$setLogObserver$1$2$1(this.$observer, this.$c, continuation);
        clashManager$setLogObserver$1$2$1.L$0 = obj;
        return clashManager$setLogObserver$1$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        ClashManager$setLogObserver$1$2$1 clashManager$setLogObserver$1$2$1 = new ClashManager$setLogObserver$1$2$1(this.$observer, this.$c, continuation);
        clashManager$setLogObserver$1$2$1.L$0 = coroutineScope;
        return clashManager$setLogObserver$1$2$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059 A[Catch: all -> 0x0099, Exception -> 0x009e, CancellationException -> 0x00d7, TRY_LEAVE, TryCatch #6 {CancellationException -> 0x00d7, Exception -> 0x009e, all -> 0x0099, blocks: (B:22:0x0053, B:24:0x0059), top: B:58:0x0053 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00bf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00d5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ed A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x006a -> B:56:0x0070). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            Method dump skipped, instructions count: 241
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ClashManager$setLogObserver$1$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
