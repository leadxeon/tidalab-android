package com.tidalab.v2board.clash;

import android.os.IBinder;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.service.remote.IClashManager;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
/* compiled from: LogcatService.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatService$startObserver$1", f = "LogcatService.kt", l = {107, 111, 117, 117, 117}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatService$startObserver$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ IBinder $binder;
    public /* synthetic */ Object L$0;
    public Object L$1;
    public Object L$2;
    public Object L$3;
    public Object L$4;
    public Object L$5;
    public Object L$6;
    public int label;
    public final /* synthetic */ LogcatService this$0;

    /* compiled from: LogcatService.kt */
    @DebugMetadata(c = "com.tidalab.v2board.clash.LogcatService$startObserver$1$2", f = "LogcatService.kt", l = {}, m = "invokeSuspend")
    /* renamed from: com.tidalab.v2board.clash.LogcatService$startObserver$1$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ IBinder $binder;
        public final /* synthetic */ IClashManager $service;
        public final /* synthetic */ LogcatService this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(IBinder iBinder, IClashManager iClashManager, LogcatService logcatService, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$binder = iBinder;
            this.$service = iClashManager;
            this.this$0 = logcatService;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$binder, this.$service, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            Continuation<? super Unit> continuation2 = continuation;
            IBinder iBinder = this.$binder;
            IClashManager iClashManager = this.$service;
            LogcatService logcatService = this.this$0;
            if (continuation2 != null) {
                continuation2.getContext();
            }
            Unit unit = Unit.INSTANCE;
            InputKt.throwOnFailure(unit);
            if (iBinder.isBinderAlive()) {
                iClashManager.setLogObserver(null);
            }
            logcatService.stopSelf();
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            InputKt.throwOnFailure(obj);
            if (this.$binder.isBinderAlive()) {
                this.$service.setLogObserver(null);
            }
            this.this$0.stopSelf();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatService$startObserver$1(IBinder iBinder, LogcatService logcatService, Continuation<? super LogcatService$startObserver$1> continuation) {
        super(2, continuation);
        this.$binder = iBinder;
        this.this$0 = logcatService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LogcatService$startObserver$1 logcatService$startObserver$1 = new LogcatService$startObserver$1(this.$binder, this.this$0, continuation);
        logcatService$startObserver$1.L$0 = obj;
        return logcatService$startObserver$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        LogcatService$startObserver$1 logcatService$startObserver$1 = new LogcatService$startObserver$1(this.$binder, this.this$0, continuation);
        logcatService$startObserver$1.L$0 = coroutineScope;
        return logcatService$startObserver$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00c5 A[Catch: all -> 0x010b, TryCatch #7 {all -> 0x010b, blocks: (B:28:0x00bf, B:30:0x00c5, B:34:0x00e3, B:41:0x010d), top: B:91:0x00bf }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0100 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x010d A[Catch: all -> 0x010b, TRY_LEAVE, TryCatch #7 {all -> 0x010b, blocks: (B:28:0x00bf, B:30:0x00c5, B:34:0x00e3, B:41:0x010d), top: B:91:0x00bf }] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x018e A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01b5 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x0101 -> B:38:0x0108). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.LogcatService$startObserver$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
