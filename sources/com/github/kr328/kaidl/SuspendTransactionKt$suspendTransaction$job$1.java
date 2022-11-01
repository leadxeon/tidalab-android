package com.github.kr328.kaidl;

import android.os.IBinder;
import android.os.Parcel;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import java.util.concurrent.CancellationException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: SuspendTransaction.kt */
@DebugMetadata(c = "com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1", f = "SuspendTransaction.kt", l = {133, 159, 159, 141, 159, 159}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class SuspendTransactionKt$suspendTransaction$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ Function2<Parcel, Continuation<? super Unit>, Object> $block;
    public final /* synthetic */ IBinder $completable;
    public final /* synthetic */ Ref$ObjectRef<Function0<Unit>> $finializer;
    public Object L$0;
    public int label;

    /* compiled from: SuspendTransaction.kt */
    @DebugMetadata(c = "com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$1", f = "SuspendTransaction.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        public final /* synthetic */ IBinder $completable;
        public final /* synthetic */ Exception $e;
        public final /* synthetic */ Parcel $r;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Exception exc, IBinder iBinder, Parcel parcel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$e = exc;
            this.$completable = iBinder;
            this.$r = parcel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$e, this.$completable, this.$r, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            boolean z;
            Continuation<? super Boolean> continuation2 = continuation;
            Exception exc = this.$e;
            IBinder iBinder = this.$completable;
            Parcel parcel = this.$r;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            InputKt.throwOnFailure(Unit.INSTANCE);
            if (exc instanceof CancellationException) {
                z = iBinder.transact(2, parcel, null, 1);
            } else {
                parcel.setDataPosition(0);
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException(exc.getMessage());
                illegalArgumentException.setStackTrace(exc.getStackTrace());
                parcel.writeException(illegalArgumentException);
                z = iBinder.transact(1, parcel, null, 1);
            }
            return Boolean.valueOf(z);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            boolean z;
            InputKt.throwOnFailure(obj);
            if (this.$e instanceof CancellationException) {
                z = this.$completable.transact(2, this.$r, null, 1);
            } else {
                this.$r.setDataPosition(0);
                Parcel parcel = this.$r;
                IllegalArgumentException illegalArgumentException = new IllegalArgumentException(this.$e.getMessage());
                illegalArgumentException.setStackTrace(this.$e.getStackTrace());
                Unit unit = Unit.INSTANCE;
                parcel.writeException(illegalArgumentException);
                z = this.$completable.transact(1, this.$r, null, 1);
            }
            return Boolean.valueOf(z);
        }
    }

    /* compiled from: SuspendTransaction.kt */
    @DebugMetadata(c = "com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2", f = "SuspendTransaction.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ Ref$ObjectRef<Function0<Unit>> $finializer;
        public final /* synthetic */ Parcel $r;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(Parcel parcel, Ref$ObjectRef<Function0<Unit>> ref$ObjectRef, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$r = parcel;
            this.$finializer = ref$ObjectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$r, this.$finializer, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            Parcel parcel = this.$r;
            Ref$ObjectRef<Function0<Unit>> ref$ObjectRef = this.$finializer;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            parcel.recycle();
            ref$ObjectRef.element.invoke();
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            this.$r.recycle();
            this.$finializer.element.invoke();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SuspendTransactionKt$suspendTransaction$job$1(Function2<? super Parcel, ? super Continuation<? super Unit>, ? extends Object> function2, IBinder iBinder, Ref$ObjectRef<Function0<Unit>> ref$ObjectRef, Continuation<? super SuspendTransactionKt$suspendTransaction$job$1> continuation) {
        super(2, continuation);
        this.$block = function2;
        this.$completable = iBinder;
        this.$finializer = ref$ObjectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SuspendTransactionKt$suspendTransaction$job$1(this.$block, this.$completable, this.$finializer, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return new SuspendTransactionKt$suspendTransaction$job$1(this.$block, this.$completable, this.$finializer, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005e A[RETURN] */
    /* JADX WARN: Type inference failed for: r1v0, types: [int, android.os.Parcel] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10, types: [android.os.Parcel] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v4 */
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
            r2 = 1
            r3 = 0
            switch(r1) {
                case 0: goto L_0x0031;
                case 1: goto L_0x0027;
                case 2: goto L_0x0022;
                case 3: goto L_0x0022;
                case 4: goto L_0x001a;
                case 5: goto L_0x0022;
                case 6: goto L_0x0011;
                default: goto L_0x0009;
            }
        L_0x0009:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x0011:
            java.lang.Object r0 = r6.L$0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x008d
        L_0x001a:
            java.lang.Object r1 = r6.L$0
            android.os.Parcel r1 = (android.os.Parcel) r1
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: all -> 0x005f, Exception -> 0x008e
            goto L_0x008f
        L_0x0022:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            goto L_0x00b9
        L_0x0027:
            java.lang.Object r1 = r6.L$0
            android.os.Parcel r1 = (android.os.Parcel) r1
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)     // Catch: DeadObjectException -> 0x002f, all -> 0x005f, Exception -> 0x0061
            goto L_0x0045
        L_0x002f:
            goto L_0x00a4
        L_0x0031:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r7)
            android.os.Parcel r1 = android.os.Parcel.obtain()
            kotlin.jvm.functions.Function2<android.os.Parcel, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r7 = r6.$block     // Catch: DeadObjectException -> 0x002f, all -> 0x005f, Exception -> 0x0061
            r6.L$0 = r1     // Catch: DeadObjectException -> 0x002f, all -> 0x005f, Exception -> 0x0061
            r6.label = r2     // Catch: DeadObjectException -> 0x002f, all -> 0x005f, Exception -> 0x0061
            java.lang.Object r7 = r7.invoke(r1, r6)     // Catch: DeadObjectException -> 0x002f, all -> 0x005f, Exception -> 0x0061
            if (r7 != r0) goto L_0x0045
            return r0
        L_0x0045:
            android.os.IBinder r7 = r6.$completable     // Catch: DeadObjectException -> 0x002f, all -> 0x005f, Exception -> 0x0061
            r7.transact(r2, r1, r3, r2)     // Catch: DeadObjectException -> 0x002f, all -> 0x005f, Exception -> 0x0061
            kotlinx.coroutines.NonCancellable r7 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2 r2 = new com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2
            kotlin.jvm.internal.Ref$ObjectRef<kotlin.jvm.functions.Function0<kotlin.Unit>> r4 = r6.$finializer
            r2.<init>(r1, r4, r3)
            r6.L$0 = r3
            r1 = 2
            r6.label = r1
            java.lang.Object r7 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r7, r2, r6)
            if (r7 != r0) goto L_0x00b9
            return r0
        L_0x005f:
            r7 = move-exception
            goto L_0x0077
        L_0x0061:
            r7 = move-exception
            kotlinx.coroutines.NonCancellable r2 = kotlinx.coroutines.NonCancellable.INSTANCE     // Catch: all -> 0x005f, Exception -> 0x008e
            com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$1 r4 = new com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$1     // Catch: all -> 0x005f, Exception -> 0x008e
            android.os.IBinder r5 = r6.$completable     // Catch: all -> 0x005f, Exception -> 0x008e
            r4.<init>(r7, r5, r1, r3)     // Catch: all -> 0x005f, Exception -> 0x008e
            r6.L$0 = r1     // Catch: all -> 0x005f, Exception -> 0x008e
            r7 = 4
            r6.label = r7     // Catch: all -> 0x005f, Exception -> 0x008e
            java.lang.Object r7 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r4, r6)     // Catch: all -> 0x005f, Exception -> 0x008e
            if (r7 != r0) goto L_0x008f
            return r0
        L_0x0077:
            kotlinx.coroutines.NonCancellable r2 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2 r4 = new com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2
            kotlin.jvm.internal.Ref$ObjectRef<kotlin.jvm.functions.Function0<kotlin.Unit>> r5 = r6.$finializer
            r4.<init>(r1, r5, r3)
            r6.L$0 = r7
            r1 = 6
            r6.label = r1
            java.lang.Object r1 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r2, r4, r6)
            if (r1 != r0) goto L_0x008c
            return r0
        L_0x008c:
            r0 = r7
        L_0x008d:
            throw r0
        L_0x008e:
        L_0x008f:
            kotlinx.coroutines.NonCancellable r7 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2 r2 = new com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2
            kotlin.jvm.internal.Ref$ObjectRef<kotlin.jvm.functions.Function0<kotlin.Unit>> r4 = r6.$finializer
            r2.<init>(r1, r4, r3)
            r6.L$0 = r3
            r1 = 5
            r6.label = r1
            java.lang.Object r7 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r7, r2, r6)
            if (r7 != r0) goto L_0x00b9
            return r0
        L_0x00a4:
            kotlinx.coroutines.NonCancellable r7 = kotlinx.coroutines.NonCancellable.INSTANCE
            com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2 r2 = new com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1$2
            kotlin.jvm.internal.Ref$ObjectRef<kotlin.jvm.functions.Function0<kotlin.Unit>> r4 = r6.$finializer
            r2.<init>(r1, r4, r3)
            r6.L$0 = r3
            r1 = 3
            r6.label = r1
            java.lang.Object r7 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r7, r2, r6)
            if (r7 != r0) goto L_0x00b9
            return r0
        L_0x00b9:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.kr328.kaidl.SuspendTransactionKt$suspendTransaction$job$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
