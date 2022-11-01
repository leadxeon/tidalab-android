package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: BaseActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.BaseActivity$finish$1", f = "BaseActivity.kt", l = {146, 148, 148}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class BaseActivity$finish$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public Object L$0;
    public int label;
    public final /* synthetic */ BaseActivity<D> this$0;

    /* compiled from: BaseActivity.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.BaseActivity$finish$1$1", f = "BaseActivity.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.BaseActivity$finish$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ BaseActivity<D> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(BaseActivity<D> baseActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = baseActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            BaseActivity<D> baseActivity = this.this$0;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            BaseActivity$finish$1.super.finish();
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            BaseActivity$finish$1.super.finish();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseActivity$finish$1(BaseActivity<D> baseActivity, Continuation<? super BaseActivity$finish$1> continuation) {
        super(2, continuation);
        this.this$0 = baseActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseActivity$finish$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new BaseActivity$finish$1(this.this$0, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [com.tidalab.v2board.clash.BaseActivity<D>, com.tidalab.v2board.clash.BaseActivity] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r6.label
            r2 = 0
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x002a
            if (r1 == r5) goto L_0x0024
            if (r1 == r4) goto L_0x0020
            if (r1 == r3) goto L_0x0018
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0018:
            java.lang.Object r0 = r6.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x0064
        L_0x0020:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x004c
        L_0x0024:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x0028
            goto L_0x003a
        L_0x0028:
            r7 = move-exception
            goto L_0x004f
        L_0x002a:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            com.tidalab.v2board.clash.BaseActivity<D> r7 = r6.this$0     // Catch: all -> 0x0028
            kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r7 = r7.defer     // Catch: all -> 0x0028
            r6.label = r5     // Catch: all -> 0x0028
            java.lang.Object r7 = r7.invoke(r6)     // Catch: all -> 0x0028
            if (r7 != r0) goto L_0x003a
            return r0
        L_0x003a:
            kotlinx.coroutines.NonCancellable r7 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.BaseActivity$finish$1$1 r1 = new com.tidalab.v2board.clash.BaseActivity$finish$1$1
            com.tidalab.v2board.clash.BaseActivity<D> r3 = r6.this$0
            r1.<init>(r3, r2)
            r6.label = r4
            java.lang.Object r7 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r7, r1, r6)
            if (r7 != r0) goto L_0x004c
            return r0
        L_0x004c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x004f:
            kotlinx.coroutines.NonCancellable r1 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.BaseActivity$finish$1$1 r4 = new com.tidalab.v2board.clash.BaseActivity$finish$1$1
            com.tidalab.v2board.clash.BaseActivity<D> r5 = r6.this$0
            r4.<init>(r5, r2)
            r6.L$0 = r7
            r6.label = r3
            java.lang.Object r1 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r1, r4, r6)
            if (r1 != r0) goto L_0x0063
            return r0
        L_0x0063:
            r0 = r7
        L_0x0064:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.BaseActivity$finish$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
