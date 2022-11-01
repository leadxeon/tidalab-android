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
@DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileProcessor$delete$2", f = "ProfileProcessor.kt", l = {198, 140, 141}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class ProfileProcessor$delete$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Context $context;
    public final /* synthetic */ UUID $uuid;
    public Object L$0;
    public Object L$1;
    public Object L$2;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileProcessor$delete$2(UUID uuid, Context context, Continuation<? super ProfileProcessor$delete$2> continuation) {
        super(2, continuation);
        this.$uuid = uuid;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ProfileProcessor$delete$2(this.$uuid, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new ProfileProcessor$delete$2(this.$uuid, this.$context, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0092 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0093  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            if (r1 == 0) goto L_0x0052
            if (r1 == r4) goto L_0x0041
            if (r1 == r3) goto L_0x002c
            if (r1 != r2) goto L_0x0024
            java.lang.Object r0 = r7.L$2
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Object r1 = r7.L$1
            java.util.UUID r1 = (java.util.UUID) r1
            java.lang.Object r2 = r7.L$0
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)     // Catch: all -> 0x0021
            goto L_0x0096
        L_0x0021:
            r8 = move-exception
            goto L_0x00c0
        L_0x0024:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x002c:
            java.lang.Object r1 = r7.L$2
            android.content.Context r1 = (android.content.Context) r1
            java.lang.Object r3 = r7.L$1
            java.util.UUID r3 = (java.util.UUID) r3
            java.lang.Object r4 = r7.L$0
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)     // Catch: all -> 0x003d
            r8 = r4
            goto L_0x0080
        L_0x003d:
            r8 = move-exception
            r2 = r4
            goto L_0x00c0
        L_0x0041:
            java.lang.Object r1 = r7.L$2
            android.content.Context r1 = (android.content.Context) r1
            java.lang.Object r4 = r7.L$1
            java.util.UUID r4 = (java.util.UUID) r4
            java.lang.Object r6 = r7.L$0
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            r8 = r6
            goto L_0x006c
        L_0x0052:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            kotlinx.coroutines.sync.Mutex r8 = com.tidalab.v2board.clash.service.ProfileProcessor.profileLock
            java.util.UUID r1 = r7.$uuid
            android.content.Context r6 = r7.$context
            r7.L$0 = r8
            r7.L$1 = r1
            r7.L$2 = r6
            r7.label = r4
            java.lang.Object r4 = r8.lock(r5, r7)
            if (r4 != r0) goto L_0x006a
            return r0
        L_0x006a:
            r4 = r1
            r1 = r6
        L_0x006c:
            com.tidalab.v2board.clash.service.data.ImportedDao r6 = com.tidalab.v2board.clash.design.dialog.InputKt.ImportedDao()     // Catch: all -> 0x00bd
            r7.L$0 = r8     // Catch: all -> 0x00bd
            r7.L$1 = r4     // Catch: all -> 0x00bd
            r7.L$2 = r1     // Catch: all -> 0x00bd
            r7.label = r3     // Catch: all -> 0x00bd
            java.lang.Object r3 = r6.remove(r4, r7)     // Catch: all -> 0x00bd
            if (r3 != r0) goto L_0x007f
            return r0
        L_0x007f:
            r3 = r4
        L_0x0080:
            com.tidalab.v2board.clash.service.data.PendingDao r4 = com.tidalab.v2board.clash.design.dialog.InputKt.PendingDao()     // Catch: all -> 0x00bd
            r7.L$0 = r8     // Catch: all -> 0x00bd
            r7.L$1 = r3     // Catch: all -> 0x00bd
            r7.L$2 = r1     // Catch: all -> 0x00bd
            r7.label = r2     // Catch: all -> 0x00bd
            java.lang.Object r2 = r4.remove(r3, r7)     // Catch: all -> 0x00bd
            if (r2 != r0) goto L_0x0093
            return r0
        L_0x0093:
            r2 = r8
            r0 = r1
            r1 = r3
        L_0x0096:
            java.io.File r8 = com.tidalab.v2board.clash.design.dialog.InputKt.getPendingDir(r0)     // Catch: all -> 0x0021
            java.lang.String r3 = r1.toString()     // Catch: all -> 0x0021
            java.io.File r8 = kotlin.io.FilesKt__UtilsKt.resolve(r8, r3)     // Catch: all -> 0x0021
            java.io.File r3 = com.tidalab.v2board.clash.design.dialog.InputKt.getImportedDir(r0)     // Catch: all -> 0x0021
            java.lang.String r4 = r1.toString()     // Catch: all -> 0x0021
            java.io.File r3 = kotlin.io.FilesKt__UtilsKt.resolve(r3, r4)     // Catch: all -> 0x0021
            kotlin.io.FilesKt__UtilsKt.deleteRecursively(r8)     // Catch: all -> 0x0021
            kotlin.io.FilesKt__UtilsKt.deleteRecursively(r3)     // Catch: all -> 0x0021
            com.tidalab.v2board.clash.design.dialog.InputKt.sendProfileChanged(r0, r1)     // Catch: all -> 0x0021
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: all -> 0x0021
            r2.unlock(r5)
            return r8
        L_0x00bd:
            r0 = move-exception
            r2 = r8
            r8 = r0
        L_0x00c0:
            r2.unlock(r5)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileProcessor$delete$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
