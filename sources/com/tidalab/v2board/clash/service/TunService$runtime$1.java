package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.clash.ClashRuntimeScope;
import com.tidalab.v2board.clash.service.clash.module.TunModule;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.Channel;
/* compiled from: TunService.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.TunService$runtime$1", f = "TunService.kt", l = {248, 81, 81, 81}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class TunService$runtime$1 extends SuspendLambda implements Function2<ClashRuntimeScope, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public Object L$5;
    public int label;
    public final /* synthetic */ TunService this$0;

    /* compiled from: TunService.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.service.TunService$runtime$1$1", f = "TunService.kt", l = {82}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.service.TunService$runtime$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ TunModule $tun;
        public int label;
        public final /* synthetic */ TunService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TunModule tunModule, TunService tunService, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$tun = tunModule;
            this.this$0 = tunService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$tun, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(this.$tun, this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                TunModule tunModule = this.$tun;
                this.label = 1;
                Channel<Unit> channel = tunModule.close;
                Unit unit = Unit.INSTANCE;
                Object send = channel.send(unit, this);
                if (send == coroutineSingletons) {
                    unit = send;
                }
                if (unit == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else if (i == 1) {
                InputKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            this.this$0.stopSelf();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TunService$runtime$1(TunService tunService, Continuation<? super TunService$runtime$1> continuation) {
        super(2, continuation);
        this.this$0 = tunService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TunService$runtime$1 tunService$runtime$1 = new TunService$runtime$1(this.this$0, continuation);
        tunService$runtime$1.L$0 = obj;
        return tunService$runtime$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(ClashRuntimeScope clashRuntimeScope, Continuation<? super Unit> continuation) {
        TunService$runtime$1 tunService$runtime$1 = new TunService$runtime$1(this.this$0, continuation);
        tunService$runtime$1.L$0 = clashRuntimeScope;
        return tunService$runtime$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x01c3: INVOKE  
      (r4v1 ?? I:com.tidalab.v2board.clash.service.TunService$runtime$1$1)
      (r10 I:com.tidalab.v2board.clash.service.clash.module.TunModule)
      (r5 I:com.tidalab.v2board.clash.service.TunService)
      (r7 I:kotlin.coroutines.Continuation)
     type: DIRECT call: com.tidalab.v2board.clash.service.TunService$runtime$1.1.<init>(com.tidalab.v2board.clash.service.clash.module.TunModule, com.tidalab.v2board.clash.service.TunService, kotlin.coroutines.Continuation):void, block:B:49:0x01bd */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0104 A[Catch: all -> 0x0160, Exception -> 0x0162, TRY_LEAVE, TryCatch #0 {all -> 0x0160, blocks: (B:24:0x00fc, B:26:0x0104, B:29:0x014b, B:30:0x014e, B:33:0x0155, B:43:0x0185), top: B:53:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0181 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01da A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v0, types: [com.tidalab.v2board.clash.service.clash.module.TunModule] */
    /* JADX WARN: Unknown variable types count: 1 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0152 -> B:33:0x0155). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 476
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.TunService$runtime$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
