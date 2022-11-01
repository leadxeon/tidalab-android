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
@DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$updateProfile$1", f = "NativeModule.kt", l = {92}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class NativeModule$updateProfile$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String $uuid;
    public int label;

    /* compiled from: NativeModule.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.NativeModule$updateProfile$1$1", f = "NativeModule.kt", l = {93, 94, 96}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.NativeModule$updateProfile$1$1  reason: invalid class name */
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

        /* JADX WARN: Removed duplicated region for block: B:21:0x0059  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r1 = r5.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L_0x002b
                if (r1 == r4) goto L_0x0023
                if (r1 == r3) goto L_0x001b
                if (r1 != r2) goto L_0x0013
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
                goto L_0x0065
            L_0x0013:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L_0x001b:
                java.lang.Object r1 = r5.L$0
                com.tidalab.v2board.clash.service.remote.IProfileManager r1 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r1
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
                goto L_0x0055
            L_0x0023:
                java.lang.Object r1 = r5.L$0
                com.tidalab.v2board.clash.service.remote.IProfileManager r1 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r1
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
                goto L_0x0044
            L_0x002b:
                com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r6)
                java.lang.Object r6 = r5.L$0
                com.tidalab.v2board.clash.service.remote.IProfileManager r6 = (com.tidalab.v2board.clash.service.remote.IProfileManager) r6
                java.lang.String r1 = r5.$uuid
                java.util.UUID r1 = java.util.UUID.fromString(r1)
                r5.L$0 = r6
                r5.label = r4
                java.lang.Object r1 = r6.update(r1, r5)
                if (r1 != r0) goto L_0x0043
                return r0
            L_0x0043:
                r1 = r6
            L_0x0044:
                java.lang.String r6 = r5.$uuid
                java.util.UUID r6 = java.util.UUID.fromString(r6)
                r5.L$0 = r1
                r5.label = r3
                java.lang.Object r6 = r1.queryByUUID(r6, r5)
                if (r6 != r0) goto L_0x0055
                return r0
            L_0x0055:
                com.tidalab.v2board.clash.service.model.Profile r6 = (com.tidalab.v2board.clash.service.model.Profile) r6
                if (r6 == 0) goto L_0x0065
                r3 = 0
                r5.L$0 = r3
                r5.label = r2
                java.lang.Object r6 = r1.setActive(r6, r5)
                if (r6 != r0) goto L_0x0065
                return r0
            L_0x0065:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.NativeModule$updateProfile$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeModule$updateProfile$1(String str, Continuation<? super NativeModule$updateProfile$1> continuation) {
        super(2, continuation);
        this.$uuid = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NativeModule$updateProfile$1(this.$uuid, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new NativeModule$updateProfile$1(this.$uuid, continuation).invokeSuspend(Unit.INSTANCE);
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
