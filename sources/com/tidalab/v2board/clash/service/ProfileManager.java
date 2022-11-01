package com.tidalab.v2board.clash.service;

import android.content.Context;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.ProfileReceiver;
import com.tidalab.v2board.clash.service.data.Database;
import com.tidalab.v2board.clash.service.model.Profile;
import com.tidalab.v2board.clash.service.remote.IProfileManager;
import com.tidalab.v2board.clash.service.store.ServiceStore;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt__UtilsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.NonCancellable;
/* compiled from: ProfileManager.kt */
/* loaded from: classes.dex */
public final class ProfileManager implements IProfileManager, CoroutineScope {
    public final /* synthetic */ CoroutineScope $$delegate_0 = InputKt.CoroutineScope(Dispatchers.IO);
    public final Context context;
    public final ServiceStore store;

    /* compiled from: ProfileManager.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.service.ProfileManager$1", f = "ProfileManager.kt", l = {31}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.service.ProfileManager$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                InputKt.throwOnFailure(obj);
                Database.Companion.getDatabase();
                ProfileReceiver.Companion companion = ProfileReceiver.Companion;
                Context context = ProfileManager.this.context;
                this.label = 1;
                if (companion.rescheduleAll(context, this) == coroutineSingletons) {
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

    public ProfileManager(Context context) {
        this.context = context;
        Dispatchers dispatchers = Dispatchers.INSTANCE;
        this.store = new ServiceStore(context);
        InputKt.launch$default(this, null, null, new AnonymousClass1(null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0088 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00c6  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object clone(java.util.UUID r21, kotlin.coroutines.Continuation<? super java.util.UUID> r22) {
        /*
            Method dump skipped, instructions count: 227
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.clone(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void cloneImportedFiles(UUID uuid, UUID uuid2) {
        File resolve = FilesKt__UtilsKt.resolve(InputKt.getImportedDir(this.context), uuid.toString());
        File resolve2 = FilesKt__UtilsKt.resolve(InputKt.getPendingDir(this.context), uuid2.toString());
        if (resolve.exists()) {
            FilesKt__UtilsKt.deleteRecursively(resolve2);
            FilesKt__UtilsKt.copyRecursively$default(resolve, resolve2, false, null, 6);
            return;
        }
        throw new FileNotFoundException("profile " + uuid + " not found");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006d A[RETURN] */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object commit(java.util.UUID r7, com.tidalab.v2board.clash.service.remote.IFetchObserver r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.tidalab.v2board.clash.service.ProfileManager$commit$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.tidalab.v2board.clash.service.ProfileManager$commit$1 r0 = (com.tidalab.v2board.clash.service.ProfileManager$commit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.ProfileManager$commit$1 r0 = new com.tidalab.v2board.clash.service.ProfileManager$commit$1
            r0.<init>(r6, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x003f
            if (r2 == r4) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            goto L_0x006e
        L_0x002b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0033:
            java.lang.Object r7 = r0.L$1
            java.util.UUID r7 = (java.util.UUID) r7
            java.lang.Object r8 = r0.L$0
            com.tidalab.v2board.clash.service.ProfileManager r8 = (com.tidalab.v2board.clash.service.ProfileManager) r8
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            goto L_0x0060
        L_0x003f:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            com.tidalab.v2board.clash.service.ProfileProcessor r9 = com.tidalab.v2board.clash.service.ProfileProcessor.INSTANCE
            android.content.Context r9 = r6.context
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            kotlinx.coroutines.NonCancellable r2 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.service.ProfileProcessor$apply$2 r4 = new com.tidalab.v2board.clash.service.ProfileProcessor$apply$2
            r4.<init>(r8, r9, r7, r5)
            java.lang.Object r8 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r4, r0)
            if (r8 != r1) goto L_0x005a
            goto L_0x005c
        L_0x005a:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
        L_0x005c:
            if (r8 != r1) goto L_0x005f
            return r1
        L_0x005f:
            r8 = r6
        L_0x0060:
            r9 = 0
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r7 = r8.scheduleUpdate(r7, r9, r0)
            if (r7 != r1) goto L_0x006e
            return r1
        L_0x006e:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.commit(java.util.UUID, com.tidalab.v2board.clash.service.remote.IFetchObserver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x009b  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object create(com.tidalab.v2board.clash.service.model.Profile.Type r18, java.lang.String r19, java.lang.String r20, kotlin.coroutines.Continuation<? super java.util.UUID> r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r21
            boolean r2 = r1 instanceof com.tidalab.v2board.clash.service.ProfileManager$create$1
            if (r2 == 0) goto L_0x0017
            r2 = r1
            com.tidalab.v2board.clash.service.ProfileManager$create$1 r2 = (com.tidalab.v2board.clash.service.ProfileManager$create$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L_0x0017
            int r3 = r3 - r4
            r2.label = r3
            goto L_0x001c
        L_0x0017:
            com.tidalab.v2board.clash.service.ProfileManager$create$1 r2 = new com.tidalab.v2board.clash.service.ProfileManager$create$1
            r2.<init>(r0, r1)
        L_0x001c:
            java.lang.Object r1 = r2.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r2.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x0057
            if (r4 == r6) goto L_0x003f
            if (r4 != r5) goto L_0x0037
            java.lang.Object r3 = r2.L$1
            java.util.UUID r3 = (java.util.UUID) r3
            java.lang.Object r2 = r2.L$0
            com.tidalab.v2board.clash.service.ProfileManager r2 = (com.tidalab.v2board.clash.service.ProfileManager) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r1)
            goto L_0x009d
        L_0x0037:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x003f:
            java.lang.Object r4 = r2.L$3
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r6 = r2.L$2
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r2.L$1
            com.tidalab.v2board.clash.service.model.Profile$Type r7 = (com.tidalab.v2board.clash.service.model.Profile.Type) r7
            java.lang.Object r8 = r2.L$0
            com.tidalab.v2board.clash.service.ProfileManager r8 = (com.tidalab.v2board.clash.service.ProfileManager) r8
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r1)
            r11 = r4
            r9 = r6
            r10 = r7
            r4 = r8
            goto L_0x0076
        L_0x0057:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r1)
            r2.L$0 = r0
            r1 = r18
            r2.L$1 = r1
            r4 = r19
            r2.L$2 = r4
            r7 = r20
            r2.L$3 = r7
            r2.label = r6
            java.lang.Object r6 = com.tidalab.v2board.clash.design.dialog.InputKt.generateProfileUUID(r2)
            if (r6 != r3) goto L_0x0071
            return r3
        L_0x0071:
            r10 = r1
            r9 = r4
            r1 = r6
            r11 = r7
            r4 = r0
        L_0x0076:
            java.util.UUID r1 = (java.util.UUID) r1
            com.tidalab.v2board.clash.service.data.Pending r6 = new com.tidalab.v2board.clash.service.data.Pending
            r12 = 0
            r14 = 0
            r16 = 32
            r7 = r6
            r8 = r1
            r7.<init>(r8, r9, r10, r11, r12, r14, r16)
            com.tidalab.v2board.clash.service.data.PendingDao r7 = com.tidalab.v2board.clash.design.dialog.InputKt.PendingDao()
            r2.L$0 = r4
            r2.L$1 = r1
            r8 = 0
            r2.L$2 = r8
            r2.L$3 = r8
            r2.label = r5
            java.lang.Object r2 = r7.insert(r6, r2)
            if (r2 != r3) goto L_0x009b
            return r3
        L_0x009b:
            r3 = r1
            r2 = r4
        L_0x009d:
            android.content.Context r1 = r2.context
            java.io.File r1 = com.tidalab.v2board.clash.design.dialog.InputKt.getPendingDir(r1)
            java.lang.String r2 = r3.toString()
            java.io.File r1 = kotlin.io.FilesKt__UtilsKt.resolve(r1, r2)
            kotlin.io.FilesKt__UtilsKt.deleteRecursively(r1)
            r1.mkdirs()
            java.lang.String r2 = "config.yaml"
            java.io.File r2 = kotlin.io.FilesKt__UtilsKt.resolve(r1, r2)
            r2.createNewFile()
            java.lang.String r2 = "providers"
            java.io.File r1 = kotlin.io.FilesKt__UtilsKt.resolve(r1, r2)
            r1.mkdir()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.create(com.tidalab.v2board.clash.service.model.Profile$Type, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008b A[RETURN] */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object delete(java.util.UUID r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.service.ProfileManager$delete$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.service.ProfileManager$delete$1 r0 = (com.tidalab.v2board.clash.service.ProfileManager$delete$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.ProfileManager$delete$1 r0 = new com.tidalab.v2board.clash.service.ProfileManager$delete$1
            r0.<init>(r6, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003e
            if (r2 == r4) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x008c
        L_0x002a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0032:
            java.lang.Object r7 = r0.L$1
            java.util.UUID r7 = (java.util.UUID) r7
            java.lang.Object r2 = r0.L$0
            com.tidalab.v2board.clash.service.ProfileManager r2 = (com.tidalab.v2board.clash.service.ProfileManager) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            goto L_0x0053
        L_0x003e:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            com.tidalab.v2board.clash.service.data.ImportedDao r8 = com.tidalab.v2board.clash.design.dialog.InputKt.ImportedDao()
            r0.L$0 = r6
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r8 = r8.queryByUUID(r7, r0)
            if (r8 != r1) goto L_0x0052
            return r1
        L_0x0052:
            r2 = r6
        L_0x0053:
            com.tidalab.v2board.clash.service.data.Imported r8 = (com.tidalab.v2board.clash.service.data.Imported) r8
            if (r8 != 0) goto L_0x0058
            goto L_0x006e
        L_0x0058:
            com.tidalab.v2board.clash.service.ProfileReceiver$Companion r4 = com.tidalab.v2board.clash.service.ProfileReceiver.Companion
            android.content.Context r5 = r2.context
            android.app.PendingIntent r8 = r4.pendingIntentOf(r5, r8)
            java.lang.Class<android.app.AlarmManager> r4 = android.app.AlarmManager.class
            java.lang.Object r4 = androidx.core.content.ContextCompat.getSystemService(r5, r4)
            android.app.AlarmManager r4 = (android.app.AlarmManager) r4
            if (r4 != 0) goto L_0x006b
            goto L_0x006e
        L_0x006b:
            r4.cancel(r8)
        L_0x006e:
            com.tidalab.v2board.clash.service.ProfileProcessor r8 = com.tidalab.v2board.clash.service.ProfileProcessor.INSTANCE
            android.content.Context r8 = r2.context
            r2 = 0
            r0.L$0 = r2
            r0.L$1 = r2
            r0.label = r3
            kotlinx.coroutines.NonCancellable r3 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.tidalab.v2board.clash.service.ProfileProcessor$delete$2 r4 = new com.tidalab.v2board.clash.service.ProfileProcessor$delete$2
            r4.<init>(r7, r8, r2)
            java.lang.Object r7 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r3, r4, r0)
            if (r7 != r1) goto L_0x0087
            goto L_0x0089
        L_0x0087:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
        L_0x0089:
            if (r7 != r1) goto L_0x008c
            return r1
        L_0x008c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.delete(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x010f  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object patch(java.util.UUID r27, java.lang.String r28, java.lang.String r29, long r30, kotlin.coroutines.Continuation<? super kotlin.Unit> r32) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.patch(java.util.UUID, java.lang.String, java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0073 A[RETURN] */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object queryActive(kotlin.coroutines.Continuation<? super com.tidalab.v2board.clash.service.model.Profile> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.ProfileManager$queryActive$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.ProfileManager$queryActive$1 r0 = (com.tidalab.v2board.clash.service.ProfileManager$queryActive$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.ProfileManager$queryActive$1 r0 = new com.tidalab.v2board.clash.service.ProfileManager$queryActive$1
            r0.<init>(r6, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x003f
            if (r2 == r4) goto L_0x0033
            if (r2 != r3) goto L_0x002b
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x0072
        L_0x002b:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0033:
            java.lang.Object r2 = r0.L$1
            java.util.UUID r2 = (java.util.UUID) r2
            java.lang.Object r4 = r0.L$0
            com.tidalab.v2board.clash.service.ProfileManager r4 = (com.tidalab.v2board.clash.service.ProfileManager) r4
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x005d
        L_0x003f:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            com.tidalab.v2board.clash.service.store.ServiceStore r7 = r6.store
            java.util.UUID r2 = r7.getActiveProfile()
            if (r2 != 0) goto L_0x004b
            return r5
        L_0x004b:
            com.tidalab.v2board.clash.service.data.ImportedDao r7 = com.tidalab.v2board.clash.design.dialog.InputKt.ImportedDao()
            r0.L$0 = r6
            r0.L$1 = r2
            r0.label = r4
            java.lang.Object r7 = r7.exists(r2, r0)
            if (r7 != r1) goto L_0x005c
            return r1
        L_0x005c:
            r4 = r6
        L_0x005d:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x0073
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r7 = r4.resolveProfile(r2, r0)
            if (r7 != r1) goto L_0x0072
            return r1
        L_0x0072:
            return r7
        L_0x0073:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.queryActive(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008f  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0084 -> B:26:0x0087). Please submit an issue!!! */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object queryAll(kotlin.coroutines.Continuation<? super java.util.List<com.tidalab.v2board.clash.service.model.Profile>> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.ProfileManager$queryAll$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.ProfileManager$queryAll$1 r0 = (com.tidalab.v2board.clash.service.ProfileManager$queryAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.ProfileManager$queryAll$1 r0 = new com.tidalab.v2board.clash.service.ProfileManager$queryAll$1
            r0.<init>(r6, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L_0x0046
            if (r2 == r3) goto L_0x003e
            if (r2 != r4) goto L_0x0036
            java.lang.Object r2 = r0.L$2
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r3 = r0.L$1
            java.util.Collection r3 = (java.util.Collection) r3
            java.lang.Object r5 = r0.L$0
            com.tidalab.v2board.clash.service.ProfileManager r5 = (com.tidalab.v2board.clash.service.ProfileManager) r5
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x0087
        L_0x0036:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x003e:
            java.lang.Object r2 = r0.L$0
            com.tidalab.v2board.clash.service.ProfileManager r2 = (com.tidalab.v2board.clash.service.ProfileManager) r2
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x005f
        L_0x0046:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            kotlinx.coroutines.Dispatchers r7 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.IO
            com.tidalab.v2board.clash.service.ProfileManager$queryAll$uuids$1 r2 = new com.tidalab.v2board.clash.service.ProfileManager$queryAll$uuids$1
            r5 = 0
            r2.<init>(r5)
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r7 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r7, r2, r0)
            if (r7 != r1) goto L_0x005e
            return r1
        L_0x005e:
            r2 = r6
        L_0x005f:
            java.util.List r7 = (java.util.List) r7
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r7 = r7.iterator()
            r5 = r2
            r2 = r7
        L_0x006c:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x008f
            java.lang.Object r7 = r2.next()
            java.util.UUID r7 = (java.util.UUID) r7
            r0.L$0 = r5
            r0.L$1 = r3
            r0.L$2 = r2
            r0.label = r4
            java.lang.Object r7 = r5.resolveProfile(r7, r0)
            if (r7 != r1) goto L_0x0087
            return r1
        L_0x0087:
            com.tidalab.v2board.clash.service.model.Profile r7 = (com.tidalab.v2board.clash.service.model.Profile) r7
            if (r7 == 0) goto L_0x006c
            r3.add(r7)
            goto L_0x006c
        L_0x008f:
            java.util.List r3 = (java.util.List) r3
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.queryAll(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object queryByUUID(UUID uuid, Continuation<? super Profile> continuation) {
        return resolveProfile(uuid, continuation);
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object release(UUID uuid, Continuation<? super Unit> continuation) {
        ProfileProcessor profileProcessor = ProfileProcessor.INSTANCE;
        Object withContext = InputKt.withContext(NonCancellable.INSTANCE, new ProfileProcessor$release$2(uuid, this.context, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0133  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object resolveProfile(java.util.UUID r21, kotlin.coroutines.Continuation<? super com.tidalab.v2board.clash.service.model.Profile> r22) {
        /*
            Method dump skipped, instructions count: 314
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.resolveProfile(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0051  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object scheduleUpdate(java.util.UUID r5, boolean r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r4 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.ProfileManager$scheduleUpdate$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.ProfileManager$scheduleUpdate$1 r0 = (com.tidalab.v2board.clash.service.ProfileManager$scheduleUpdate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.ProfileManager$scheduleUpdate$1 r0 = new com.tidalab.v2board.clash.service.ProfileManager$scheduleUpdate$1
            r0.<init>(r4, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            boolean r6 = r0.Z$0
            java.lang.Object r5 = r0.L$0
            com.tidalab.v2board.clash.service.ProfileManager r5 = (com.tidalab.v2board.clash.service.ProfileManager) r5
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x004a
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            com.tidalab.v2board.clash.service.data.ImportedDao r7 = com.tidalab.v2board.clash.design.dialog.InputKt.ImportedDao()
            r0.L$0 = r4
            r0.Z$0 = r6
            r0.label = r3
            java.lang.Object r7 = r7.queryByUUID(r5, r0)
            if (r7 != r1) goto L_0x0049
            return r1
        L_0x0049:
            r5 = r4
        L_0x004a:
            com.tidalab.v2board.clash.service.data.Imported r7 = (com.tidalab.v2board.clash.service.data.Imported) r7
            if (r7 != 0) goto L_0x0051
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0051:
            if (r6 == 0) goto L_0x006f
            com.tidalab.v2board.clash.service.ProfileReceiver$Companion r6 = com.tidalab.v2board.clash.service.ProfileReceiver.Companion
            android.content.Context r5 = r5.context
            android.app.PendingIntent r6 = r6.pendingIntentOf(r5, r7)
            java.lang.Class<android.app.AlarmManager> r7 = android.app.AlarmManager.class
            java.lang.Object r7 = androidx.core.content.ContextCompat.getSystemService(r5, r7)
            android.app.AlarmManager r7 = (android.app.AlarmManager) r7
            if (r7 != 0) goto L_0x0066
            goto L_0x0069
        L_0x0066:
            r7.cancel(r6)
        L_0x0069:
            r7 = 0
            r0 = 0
            r6.send(r5, r7, r0)
            goto L_0x0076
        L_0x006f:
            com.tidalab.v2board.clash.service.ProfileReceiver$Companion r6 = com.tidalab.v2board.clash.service.ProfileReceiver.Companion
            android.content.Context r5 = r5.context
            r6.scheduleNext(r5, r7)
        L_0x0076:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.ProfileManager.scheduleUpdate(java.util.UUID, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object setActive(Profile profile, Continuation<? super Unit> continuation) {
        ProfileProcessor profileProcessor = ProfileProcessor.INSTANCE;
        Object withContext = InputKt.withContext(NonCancellable.INSTANCE, new ProfileProcessor$active$2(profile.uuid, this.context, null), continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (withContext != coroutineSingletons) {
            withContext = Unit.INSTANCE;
        }
        return withContext == coroutineSingletons ? withContext : Unit.INSTANCE;
    }

    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    public Object update(UUID uuid, Continuation<? super Unit> continuation) {
        Object scheduleUpdate = scheduleUpdate(uuid, true, continuation);
        return scheduleUpdate == CoroutineSingletons.COROUTINE_SUSPENDED ? scheduleUpdate : Unit.INSTANCE;
    }
}
