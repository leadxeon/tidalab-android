package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: NativeModule.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$commitProfile$1", f = "NativeModule.kt", l = {53}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$commitProfile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $uuid;
    public int label;

    /* compiled from: NativeModule.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$commitProfile$1$1", f = "NativeModule.kt", l = {54, 55, 57}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.NativeModule$commitProfile$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<IProfileManager, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String $uuid;
        public /* synthetic */ Object L$0;
        public int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$uuid = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$uuid, continuation);
            r0.L$0 = obj;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(IProfileManager iProfileManager, Continuation<? super Unit> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$uuid, continuation);
            r0.L$0 = iProfileManager;
            return r0.invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
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
                if (r1 == 0) goto L_0x002c
                if (r1 == r5) goto L_0x0024
                if (r1 == r4) goto L_0x001c
                if (r1 != r3) goto L_0x0014
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
                goto L_0x0065
            L_0x0014:
                java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r7.<init>(r0)
                throw r7
            L_0x001c:
                java.lang.Object r1 = r6.L$0
                com.tidalab.v2board.clash.service.remote.IProfileManager r1 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r1
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
                goto L_0x0056
            L_0x0024:
                java.lang.Object r1 = r6.L$0
                com.tidalab.v2board.clash.service.remote.IProfileManager r1 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r1
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
                goto L_0x0045
            L_0x002c:
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
                java.lang.Object r7 = r6.L$0
                com.tidalab.v2board.clash.service.remote.IProfileManager r7 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r7
                java.lang.String r1 = r6.$uuid
                java.util.UUID r1 = java.util.UUID.fromString(r1)
                r6.L$0 = r7
                r6.label = r5
                java.lang.Object r1 = r7.commit(r1, r2, r6)
                if (r1 != r0) goto L_0x0044
                return r0
            L_0x0044:
                r1 = r7
            L_0x0045:
                java.lang.String r7 = r6.$uuid
                java.util.UUID r7 = java.util.UUID.fromString(r7)
                r6.L$0 = r1
                r6.label = r4
                java.lang.Object r7 = r1.queryByUUID(r7, r6)
                if (r7 != r0) goto L_0x0056
                return r0
            L_0x0056:
                com.tidalab.v2board.clash.service.model.Profile r7 = (com.tidalab.v2board.clash.service.model.Profile) r7
                if (r7 == 0) goto L_0x0065
                r6.L$0 = r2
                r6.label = r3
                java.lang.Object r7 = r1.setActive(r7, r6)
                if (r7 != r0) goto L_0x0065
                return r0
            L_0x0065:
                kotlin.Unit r7 = kotlin.Unit.INSTANCE
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.NativeModule$commitProfile$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeModule$commitProfile$1(String str, Continuation<? super NativeModule$commitProfile$1> continuation) {
        super(2, continuation);
        this.$uuid = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeModule$commitProfile$1(this.$uuid, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new NativeModule$commitProfile$1(this.$uuid, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            AnonymousClass1 r5 = new AnonymousClass1(this.$uuid, null);
            this.label = 1;
            if (InputKt.withProfile$default(null, r5, this, 1) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
