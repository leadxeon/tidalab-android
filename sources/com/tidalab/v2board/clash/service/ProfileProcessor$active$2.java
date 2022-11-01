package com.tidalab.v2board.clash.service;

import android.content.Context;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: ProfileProcessor.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileProcessor$active$2", f = "ProfileProcessor.kt", l = {198, 167}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileProcessor$active$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ UUID $uuid;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileProcessor$active$2(UUID uuid, Context context, Continuation<? super ProfileProcessor$active$2> continuation) {
        super(2, continuation);
        this.$uuid = uuid;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileProcessor$active$2(this.$uuid, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProfileProcessor$active$2(this.$uuid, this.$context, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0073 A[Catch: all -> 0x001d, TryCatch #1 {all -> 0x001d, blocks: (B:7:0x0019, B:20:0x006b, B:22:0x0073, B:23:0x0085), top: B:32:0x0019 }] */
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
            r4 = 0
            if (r1 == 0) goto L_0x0039
            if (r1 == r3) goto L_0x0028
            if (r1 != r2) goto L_0x0020
            java.lang.Object r0 = r8.L$2
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Object r1 = r8.L$1
            java.util.UUID r1 = (java.util.UUID) r1
            java.lang.Object r2 = r8.L$0
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)     // Catch: all -> 0x001d
            goto L_0x006b
        L_0x001d:
            r9 = move-exception
            goto L_0x008e
        L_0x0020:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0028:
            java.lang.Object r1 = r8.L$2
            android.content.Context r1 = (android.content.Context) r1
            java.lang.Object r3 = r8.L$1
            java.util.UUID r3 = (java.util.UUID) r3
            java.lang.Object r5 = r8.L$0
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            r9 = r5
            goto L_0x0053
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            kotlinx.coroutines.sync.Mutex r9 = com.tidalab.v2board.clash.service.ProfileProcessor.profileLock
            java.util.UUID r1 = r8.$uuid
            android.content.Context r5 = r8.$context
            r8.L$0 = r9
            r8.L$1 = r1
            r8.L$2 = r5
            r8.label = r3
            java.lang.Object r3 = r9.lock(r4, r8)
            if (r3 != r0) goto L_0x0051
            return r0
        L_0x0051:
            r3 = r1
            r1 = r5
        L_0x0053:
            com.tidalab.v2board.clash.service.data.ImportedDao r5 = com.tidalab.v2board.clash.design.dialog.InputKt.ImportedDao()     // Catch: all -> 0x008b
            r8.L$0 = r9     // Catch: all -> 0x008b
            r8.L$1 = r3     // Catch: all -> 0x008b
            r8.L$2 = r1     // Catch: all -> 0x008b
            r8.label = r2     // Catch: all -> 0x008b
            java.lang.Object r2 = r5.exists(r3, r8)     // Catch: all -> 0x008b
            if (r2 != r0) goto L_0x0066
            return r0
        L_0x0066:
            r0 = r1
            r1 = r3
            r7 = r2
            r2 = r9
            r9 = r7
        L_0x006b:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: all -> 0x001d
            boolean r9 = r9.booleanValue()     // Catch: all -> 0x001d
            if (r9 == 0) goto L_0x0085
            com.tidalab.v2board.clash.service.store.ServiceStore r9 = new com.tidalab.v2board.clash.service.store.ServiceStore     // Catch: all -> 0x001d
            r9.<init>(r0)     // Catch: all -> 0x001d
            com.tidalab.v2board.clash.common.store.Store$Delegate r3 = r9.activeProfile$delegate     // Catch: all -> 0x001d
            kotlin.reflect.KProperty<java.lang.Object>[] r5 = com.tidalab.v2board.clash.service.store.ServiceStore.$$delegatedProperties     // Catch: all -> 0x001d
            r6 = 0
            r5 = r5[r6]     // Catch: all -> 0x001d
            r3.setValue(r9, r5, r1)     // Catch: all -> 0x001d
            com.tidalab.v2board.clash.design.dialog.InputKt.sendProfileChanged(r0, r1)     // Catch: all -> 0x001d
        L_0x0085:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch: all -> 0x001d
            r2.unlock(r4)
            return r9
        L_0x008b:
            r0 = move-exception
            r2 = r9
            r9 = r0
        L_0x008e:
            r2.unlock(r4)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileProcessor$active$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
