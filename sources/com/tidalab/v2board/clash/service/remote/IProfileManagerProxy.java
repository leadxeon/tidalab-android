package com.tidalab.v2board.clash.service.remote;

import android.os.IBinder;
/* compiled from: IProfileManager.kt */
/* loaded from: classes.dex */
public final class IProfileManagerProxy implements IProfileManager {
    public final IBinder remote;

    public IProfileManagerProxy(IBinder iBinder) {
        this.remote = iBinder;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0069 A[Catch: all -> 0x002f, TRY_LEAVE, TryCatch #1 {all -> 0x002f, blocks: (B:12:0x002b, B:21:0x0060, B:23:0x0069, B:26:0x0072, B:27:0x0079), top: B:34:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0072 A[Catch: all -> 0x002f, TRY_ENTER, TryCatch #1 {all -> 0x002f, blocks: (B:12:0x002b, B:21:0x0060, B:23:0x0069, B:26:0x0072, B:27:0x0079), top: B:34:0x002b }] */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object clone(java.util.UUID r6, kotlin.coroutines.Continuation<? super java.util.UUID> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$clone$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$clone$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$clone$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$clone$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$clone$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x002f
            goto L_0x0060
        L_0x002f:
            r7 = move-exception
            goto L_0x007a
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            android.os.Parcel r7 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x007e
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r7.writeInterfaceToken(r4)     // Catch: all -> 0x007e
            r7.writeSerializable(r6)     // Catch: all -> 0x007e
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x007e
            r4 = 2
            r0.L$0 = r7     // Catch: all -> 0x007e
            r0.L$1 = r2     // Catch: all -> 0x007e
            r0.label = r3     // Catch: all -> 0x007e
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r4, r7, r2, r0)     // Catch: all -> 0x007e
            if (r6 != r1) goto L_0x005e
            return r1
        L_0x005e:
            r0 = r7
            r6 = r2
        L_0x0060:
            r6.readException()     // Catch: all -> 0x002f
            java.io.Serializable r7 = r6.readSerializable()     // Catch: all -> 0x002f
            if (r7 == 0) goto L_0x0072
            java.util.UUID r7 = (java.util.UUID) r7     // Catch: all -> 0x002f
            r0.recycle()
            r6.recycle()
            return r7
        L_0x0072:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException     // Catch: all -> 0x002f
            java.lang.String r1 = "null cannot be cast to non-null type java.util.UUID"
            r7.<init>(r1)     // Catch: all -> 0x002f
            throw r7     // Catch: all -> 0x002f
        L_0x007a:
            r2 = r6
            r6 = r7
            r7 = r0
            goto L_0x007f
        L_0x007e:
            r6 = move-exception
        L_0x007f:
            r7.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.clone(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object commit(java.util.UUID r6, com.tidalab.v2board.clash.service.remote.IFetchObserver r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$commit$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$commit$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$commit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$commit$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$commit$1
            r0.<init>(r5, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r7 = r0.L$0
            android.os.Parcel r7 = (android.os.Parcel) r7
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)     // Catch: all -> 0x002f
            goto L_0x007a
        L_0x002f:
            r8 = move-exception
            goto L_0x0086
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            android.os.Parcel r8 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x008a
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r8.writeInterfaceToken(r4)     // Catch: all -> 0x008a
            r8.writeSerializable(r6)     // Catch: all -> 0x008a
            if (r7 == 0) goto L_0x0064
            r8.writeInt(r3)     // Catch: all -> 0x008a
            boolean r6 = r7 instanceof android.os.IBinder     // Catch: all -> 0x008a
            if (r6 == 0) goto L_0x005a
            android.os.IBinder r7 = (android.os.IBinder) r7     // Catch: all -> 0x008a
            goto L_0x0060
        L_0x005a:
            com.tidalab.v2board.clash.service.remote.IFetchObserverDelegate r6 = new com.tidalab.v2board.clash.service.remote.IFetchObserverDelegate     // Catch: all -> 0x008a
            r6.<init>(r7)     // Catch: all -> 0x008a
            r7 = r6
        L_0x0060:
            r8.writeStrongBinder(r7)     // Catch: all -> 0x008a
            goto L_0x0068
        L_0x0064:
            r6 = 0
            r8.writeInt(r6)     // Catch: all -> 0x008a
        L_0x0068:
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x008a
            r7 = 3
            r0.L$0 = r8     // Catch: all -> 0x008a
            r0.L$1 = r2     // Catch: all -> 0x008a
            r0.label = r3     // Catch: all -> 0x008a
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r7, r8, r2, r0)     // Catch: all -> 0x008a
            if (r6 != r1) goto L_0x0078
            return r1
        L_0x0078:
            r7 = r8
            r6 = r2
        L_0x007a:
            r6.readException()     // Catch: all -> 0x002f
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            r7.recycle()
            r6.recycle()
            return r8
        L_0x0086:
            r2 = r6
            r6 = r8
            r8 = r7
            goto L_0x008b
        L_0x008a:
            r6 = move-exception
        L_0x008b:
            r8.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.commit(java.util.UUID, com.tidalab.v2board.clash.service.remote.IFetchObserver, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0072 A[Catch: all -> 0x002f, TRY_LEAVE, TryCatch #1 {all -> 0x002f, blocks: (B:12:0x002b, B:21:0x0069, B:23:0x0072, B:26:0x007b, B:27:0x0082), top: B:35:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007b A[Catch: all -> 0x002f, TRY_ENTER, TryCatch #1 {all -> 0x002f, blocks: (B:12:0x002b, B:21:0x0069, B:23:0x0072, B:26:0x007b, B:27:0x0082), top: B:35:0x002b }] */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object create(com.tidalab.v2board.clash.service.model.Profile.Type r6, java.lang.String r7, java.lang.String r8, kotlin.coroutines.Continuation<? super java.util.UUID> r9) {
        /*
            r5 = this;
            boolean r0 = r9 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$create$1
            if (r0 == 0) goto L_0x0013
            r0 = r9
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$create$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$create$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$create$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$create$1
            r0.<init>(r5, r9)
        L_0x0018:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r7 = r0.L$0
            android.os.Parcel r7 = (android.os.Parcel) r7
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)     // Catch: all -> 0x002f
            goto L_0x0069
        L_0x002f:
            r8 = move-exception
            goto L_0x0083
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r9)
            android.os.Parcel r9 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x0086
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r9.writeInterfaceToken(r4)     // Catch: all -> 0x0086
            int r6 = r6.ordinal()     // Catch: all -> 0x0086
            r9.writeInt(r6)     // Catch: all -> 0x0086
            r9.writeString(r7)     // Catch: all -> 0x0086
            r9.writeString(r8)     // Catch: all -> 0x0086
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x0086
            r0.L$0 = r9     // Catch: all -> 0x0086
            r0.L$1 = r2     // Catch: all -> 0x0086
            r0.label = r3     // Catch: all -> 0x0086
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r3, r9, r2, r0)     // Catch: all -> 0x0086
            if (r6 != r1) goto L_0x0067
            return r1
        L_0x0067:
            r7 = r9
            r6 = r2
        L_0x0069:
            r6.readException()     // Catch: all -> 0x002f
            java.io.Serializable r8 = r6.readSerializable()     // Catch: all -> 0x002f
            if (r8 == 0) goto L_0x007b
            java.util.UUID r8 = (java.util.UUID) r8     // Catch: all -> 0x002f
            r7.recycle()
            r6.recycle()
            return r8
        L_0x007b:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException     // Catch: all -> 0x002f
            java.lang.String r9 = "null cannot be cast to non-null type java.util.UUID"
            r8.<init>(r9)     // Catch: all -> 0x002f
            throw r8     // Catch: all -> 0x002f
        L_0x0083:
            r2 = r6
            r9 = r7
            goto L_0x0088
        L_0x0086:
            r6 = move-exception
            r8 = r6
        L_0x0088:
            r9.recycle()
            r2.recycle()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.create(com.tidalab.v2board.clash.service.model.Profile$Type, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object delete(java.util.UUID r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$delete$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$delete$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$delete$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$delete$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$delete$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x002f
            goto L_0x0060
        L_0x002f:
            r7 = move-exception
            goto L_0x006c
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            android.os.Parcel r7 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x0070
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r7.writeInterfaceToken(r4)     // Catch: all -> 0x0070
            r7.writeSerializable(r6)     // Catch: all -> 0x0070
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x0070
            r4 = 5
            r0.L$0 = r7     // Catch: all -> 0x0070
            r0.L$1 = r2     // Catch: all -> 0x0070
            r0.label = r3     // Catch: all -> 0x0070
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r4, r7, r2, r0)     // Catch: all -> 0x0070
            if (r6 != r1) goto L_0x005e
            return r1
        L_0x005e:
            r0 = r7
            r6 = r2
        L_0x0060:
            r6.readException()     // Catch: all -> 0x002f
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            r0.recycle()
            r6.recycle()
            return r7
        L_0x006c:
            r2 = r6
            r6 = r7
            r7 = r0
            goto L_0x0071
        L_0x0070:
            r6 = move-exception
        L_0x0071:
            r7.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.delete(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object patch(java.util.UUID r6, java.lang.String r7, java.lang.String r8, long r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r5 = this;
            boolean r0 = r11 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$patch$1
            if (r0 == 0) goto L_0x0013
            r0 = r11
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$patch$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$patch$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$patch$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$patch$1
            r0.<init>(r5, r11)
        L_0x0018:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r7 = r0.L$0
            android.os.Parcel r7 = (android.os.Parcel) r7
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)     // Catch: all -> 0x002f
            goto L_0x0069
        L_0x002f:
            r8 = move-exception
            goto L_0x0075
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r11)
            android.os.Parcel r11 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x0078
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r11.writeInterfaceToken(r4)     // Catch: all -> 0x0078
            r11.writeSerializable(r6)     // Catch: all -> 0x0078
            r11.writeString(r7)     // Catch: all -> 0x0078
            r11.writeString(r8)     // Catch: all -> 0x0078
            r11.writeLong(r9)     // Catch: all -> 0x0078
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x0078
            r7 = 6
            r0.L$0 = r11     // Catch: all -> 0x0078
            r0.L$1 = r2     // Catch: all -> 0x0078
            r0.label = r3     // Catch: all -> 0x0078
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r7, r11, r2, r0)     // Catch: all -> 0x0078
            if (r6 != r1) goto L_0x0067
            return r1
        L_0x0067:
            r7 = r11
            r6 = r2
        L_0x0069:
            r6.readException()     // Catch: all -> 0x002f
            kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            r7.recycle()
            r6.recycle()
            return r8
        L_0x0075:
            r2 = r6
            r11 = r7
            goto L_0x007a
        L_0x0078:
            r6 = move-exception
            r8 = r6
        L_0x007a:
            r11.recycle()
            r2.recycle()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.patch(java.util.UUID, java.lang.String, java.lang.String, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0067 A[Catch: all -> 0x002f, TRY_LEAVE, TryCatch #1 {all -> 0x002f, blocks: (B:12:0x002b, B:21:0x005e, B:23:0x0067), top: B:33:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006e  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object queryActive(kotlin.coroutines.Continuation<? super com.tidalab.v2board.clash.service.model.Profile> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryActive$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryActive$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryActive$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryActive$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryActive$1
            r0.<init>(r7, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r1 = r0.L$1
            android.os.Parcel r1 = (android.os.Parcel) r1
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)     // Catch: all -> 0x002f
            goto L_0x005e
        L_0x002f:
            r8 = move-exception
            goto L_0x0076
        L_0x0031:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            android.os.Parcel r8 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x007b
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r8.writeInterfaceToken(r4)     // Catch: all -> 0x007b
            android.os.IBinder r4 = r7.remote     // Catch: all -> 0x007b
            r5 = 10
            r0.L$0 = r8     // Catch: all -> 0x007b
            r0.L$1 = r2     // Catch: all -> 0x007b
            r0.label = r3     // Catch: all -> 0x007b
            java.lang.Object r0 = com.facebook.react.R$style.suspendTransact(r4, r5, r8, r2, r0)     // Catch: all -> 0x007b
            if (r0 != r1) goto L_0x005c
            return r1
        L_0x005c:
            r0 = r8
            r1 = r2
        L_0x005e:
            r1.readException()     // Catch: all -> 0x002f
            int r8 = r1.readInt()     // Catch: all -> 0x002f
            if (r8 == 0) goto L_0x006e
            com.tidalab.v2board.clash.service.model.Profile$CREATOR r8 = com.tidalab.v2board.clash.service.model.Profile.CREATOR     // Catch: all -> 0x002f
            com.tidalab.v2board.clash.service.model.Profile r8 = r8.createFromParcel(r1)     // Catch: all -> 0x002f
            goto L_0x006f
        L_0x006e:
            r8 = 0
        L_0x006f:
            r0.recycle()
            r1.recycle()
            return r8
        L_0x0076:
            r2 = r1
            r6 = r0
            r0 = r8
            r8 = r6
            goto L_0x007c
        L_0x007b:
            r0 = move-exception
        L_0x007c:
            r8.recycle()
            r2.recycle()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.queryActive(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006d A[Catch: all -> 0x002f, TRY_LEAVE, TryCatch #1 {all -> 0x002f, blocks: (B:12:0x002b, B:21:0x005e, B:23:0x006d), top: B:33:0x002b }] */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object queryAll(kotlin.coroutines.Continuation<? super java.util.List<com.tidalab.v2board.clash.service.model.Profile>> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryAll$1
            if (r0 == 0) goto L_0x0013
            r0 = r8
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryAll$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryAll$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryAll$1
            r0.<init>(r7, r8)
        L_0x0018:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r1 = r0.L$1
            android.os.Parcel r1 = (android.os.Parcel) r1
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)     // Catch: all -> 0x002f
            goto L_0x005e
        L_0x002f:
            r8 = move-exception
            goto L_0x0088
        L_0x0031:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r8)
            android.os.Parcel r8 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x008d
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r8.writeInterfaceToken(r4)     // Catch: all -> 0x008d
            android.os.IBinder r4 = r7.remote     // Catch: all -> 0x008d
            r5 = 9
            r0.L$0 = r8     // Catch: all -> 0x008d
            r0.L$1 = r2     // Catch: all -> 0x008d
            r0.label = r3     // Catch: all -> 0x008d
            java.lang.Object r0 = com.facebook.react.R$style.suspendTransact(r4, r5, r8, r2, r0)     // Catch: all -> 0x008d
            if (r0 != r1) goto L_0x005c
            return r1
        L_0x005c:
            r0 = r8
            r1 = r2
        L_0x005e:
            r1.readException()     // Catch: all -> 0x002f
            int r8 = r1.readInt()     // Catch: all -> 0x002f
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: all -> 0x002f
            r2.<init>(r8)     // Catch: all -> 0x002f
            r3 = 0
        L_0x006b:
            if (r3 >= r8) goto L_0x0081
            java.lang.Integer r4 = new java.lang.Integer     // Catch: all -> 0x002f
            r4.<init>(r3)     // Catch: all -> 0x002f
            r4.intValue()     // Catch: all -> 0x002f
            com.tidalab.v2board.clash.service.model.Profile$CREATOR r4 = com.tidalab.v2board.clash.service.model.Profile.CREATOR     // Catch: all -> 0x002f
            com.tidalab.v2board.clash.service.model.Profile r4 = r4.createFromParcel(r1)     // Catch: all -> 0x002f
            r2.add(r4)     // Catch: all -> 0x002f
            int r3 = r3 + 1
            goto L_0x006b
        L_0x0081:
            r0.recycle()
            r1.recycle()
            return r2
        L_0x0088:
            r2 = r1
            r6 = r0
            r0 = r8
            r8 = r6
            goto L_0x008e
        L_0x008d:
            r0 = move-exception
        L_0x008e:
            r8.recycle()
            r2.recycle()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.queryAll(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006a A[Catch: all -> 0x002f, TRY_LEAVE, TryCatch #1 {all -> 0x002f, blocks: (B:12:0x002b, B:21:0x0061, B:23:0x006a), top: B:33:0x002b }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0071  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object queryByUUID(java.util.UUID r6, kotlin.coroutines.Continuation<? super com.tidalab.v2board.clash.service.model.Profile> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryByUUID$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryByUUID$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryByUUID$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryByUUID$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$queryByUUID$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x002f
            goto L_0x0061
        L_0x002f:
            r7 = move-exception
            goto L_0x0079
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            android.os.Parcel r7 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x007d
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r7.writeInterfaceToken(r4)     // Catch: all -> 0x007d
            r7.writeSerializable(r6)     // Catch: all -> 0x007d
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x007d
            r4 = 8
            r0.L$0 = r7     // Catch: all -> 0x007d
            r0.L$1 = r2     // Catch: all -> 0x007d
            r0.label = r3     // Catch: all -> 0x007d
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r4, r7, r2, r0)     // Catch: all -> 0x007d
            if (r6 != r1) goto L_0x005f
            return r1
        L_0x005f:
            r0 = r7
            r6 = r2
        L_0x0061:
            r6.readException()     // Catch: all -> 0x002f
            int r7 = r6.readInt()     // Catch: all -> 0x002f
            if (r7 == 0) goto L_0x0071
            com.tidalab.v2board.clash.service.model.Profile$CREATOR r7 = com.tidalab.v2board.clash.service.model.Profile.CREATOR     // Catch: all -> 0x002f
            com.tidalab.v2board.clash.service.model.Profile r7 = r7.createFromParcel(r6)     // Catch: all -> 0x002f
            goto L_0x0072
        L_0x0071:
            r7 = 0
        L_0x0072:
            r0.recycle()
            r6.recycle()
            return r7
        L_0x0079:
            r2 = r6
            r6 = r7
            r7 = r0
            goto L_0x007e
        L_0x007d:
            r6 = move-exception
        L_0x007e:
            r7.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.queryByUUID(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object release(java.util.UUID r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$release$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$release$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$release$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$release$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$release$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x002f
            goto L_0x0060
        L_0x002f:
            r7 = move-exception
            goto L_0x006c
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            android.os.Parcel r7 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x0070
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r7.writeInterfaceToken(r4)     // Catch: all -> 0x0070
            r7.writeSerializable(r6)     // Catch: all -> 0x0070
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x0070
            r4 = 4
            r0.L$0 = r7     // Catch: all -> 0x0070
            r0.L$1 = r2     // Catch: all -> 0x0070
            r0.label = r3     // Catch: all -> 0x0070
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r4, r7, r2, r0)     // Catch: all -> 0x0070
            if (r6 != r1) goto L_0x005e
            return r1
        L_0x005e:
            r0 = r7
            r6 = r2
        L_0x0060:
            r6.readException()     // Catch: all -> 0x002f
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            r0.recycle()
            r6.recycle()
            return r7
        L_0x006c:
            r2 = r6
            r6 = r7
            r7 = r0
            goto L_0x0071
        L_0x0070:
            r6 = move-exception
        L_0x0071:
            r7.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.release(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object setActive(com.tidalab.v2board.clash.service.model.Profile r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$setActive$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$setActive$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$setActive$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$setActive$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$setActive$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x002f
            goto L_0x0062
        L_0x002f:
            r7 = move-exception
            goto L_0x006e
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            android.os.Parcel r7 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x0072
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r7.writeInterfaceToken(r4)     // Catch: all -> 0x0072
            r4 = 0
            r6.writeToParcel(r7, r4)     // Catch: all -> 0x0072
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x0072
            r4 = 11
            r0.L$0 = r7     // Catch: all -> 0x0072
            r0.L$1 = r2     // Catch: all -> 0x0072
            r0.label = r3     // Catch: all -> 0x0072
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r4, r7, r2, r0)     // Catch: all -> 0x0072
            if (r6 != r1) goto L_0x0060
            return r1
        L_0x0060:
            r0 = r7
            r6 = r2
        L_0x0062:
            r6.readException()     // Catch: all -> 0x002f
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            r0.recycle()
            r6.recycle()
            return r7
        L_0x006e:
            r2 = r6
            r6 = r7
            r7 = r0
            goto L_0x0073
        L_0x0072:
            r6 = move-exception
        L_0x0073:
            r7.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.setActive(com.tidalab.v2board.clash.service.model.Profile, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    @Override // com.tidalab.v2board.clash.service.remote.IProfileManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object update(java.util.UUID r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$update$1
            if (r0 == 0) goto L_0x0013
            r0 = r7
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$update$1 r0 = (com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$update$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L_0x0013
            int r1 = r1 - r2
            r0.label = r1
            goto L_0x0018
        L_0x0013:
            com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$update$1 r0 = new com.tidalab.v2board.clash.service.remote.IProfileManagerProxy$update$1
            r0.<init>(r5, r7)
        L_0x0018:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$1
            android.os.Parcel r6 = (android.os.Parcel) r6
            java.lang.Object r0 = r0.L$0
            android.os.Parcel r0 = (android.os.Parcel) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x002f
            goto L_0x0060
        L_0x002f:
            r7 = move-exception
            goto L_0x006c
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            android.os.Parcel r7 = android.os.Parcel.obtain()
            android.os.Parcel r2 = android.os.Parcel.obtain()
            int r4 = com.tidalab.v2board.clash.service.remote.IProfileManagerDelegate.$r8$clinit     // Catch: all -> 0x0070
            java.lang.String r4 = "com.tidalab.v2board.clash.service.remote.IProfileManager"
            r7.writeInterfaceToken(r4)     // Catch: all -> 0x0070
            r7.writeSerializable(r6)     // Catch: all -> 0x0070
            android.os.IBinder r6 = r5.remote     // Catch: all -> 0x0070
            r4 = 7
            r0.L$0 = r7     // Catch: all -> 0x0070
            r0.L$1 = r2     // Catch: all -> 0x0070
            r0.label = r3     // Catch: all -> 0x0070
            java.lang.Object r6 = com.facebook.react.R$style.suspendTransact(r6, r4, r7, r2, r0)     // Catch: all -> 0x0070
            if (r6 != r1) goto L_0x005e
            return r1
        L_0x005e:
            r0 = r7
            r6 = r2
        L_0x0060:
            r6.readException()     // Catch: all -> 0x002f
            kotlin.Unit r7 = kotlin.Unit.INSTANCE     // Catch: all -> 0x002f
            r0.recycle()
            r6.recycle()
            return r7
        L_0x006c:
            r2 = r6
            r6 = r7
            r7 = r0
            goto L_0x0071
        L_0x0070:
            r6 = move-exception
        L_0x0071:
            r7.recycle()
            r2.recycle()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.service.remote.IProfileManagerProxy.update(java.util.UUID, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
