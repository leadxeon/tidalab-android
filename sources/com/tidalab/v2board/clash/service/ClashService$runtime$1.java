package com.tidalab.v2board.clash.service;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.clash.ClashRuntimeScope;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ClashService.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ClashService$runtime$1", f = "ClashService.kt", l = {121, 70, 70, 70}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ClashService$runtime$1 extends SuspendLambda implements Function2<ClashRuntimeScope, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public int label;
    public final /* synthetic */ ClashService this$0;

    /* compiled from: ClashService.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.service.ClashService$runtime$1$1", f = "ClashService.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.service.ClashService$runtime$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ ClashService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(ClashService clashService, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = clashService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            ClashService clashService = this.this$0;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            clashService.stopSelf();
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            this.this$0.stopSelf();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClashService$runtime$1(ClashService clashService, Continuation<? super ClashService$runtime$1> continuation) {
        super(2, continuation);
        this.this$0 = clashService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ClashService$runtime$1 clashService$runtime$1 = new ClashService$runtime$1(this.this$0, continuation);
        clashService$runtime$1.L$0 = obj;
        return clashService$runtime$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(ClashRuntimeScope clashRuntimeScope, Continuation<? super Unit> continuation) {
        ClashService$runtime$1 clashService$runtime$1 = new ClashService$runtime$1(this.this$0, continuation);
        clashService$runtime$1.L$0 = clashRuntimeScope;
        return clashService$runtime$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0142, code lost:
        if (((java.lang.Boolean) r0).booleanValue() == false) goto L_0x00e5;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0195 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x0139 -> B:33:0x013c). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            Method dump skipped, instructions count: 438
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ClashService$runtime$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
