package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.BaseActivity;
import com.tidalab.v2board.clash.design.LogsDesign;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.ws.WebSocketProtocol;
/* compiled from: LogsActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogsActivity$main$2$1", f = "LogsActivity.kt", l = {28, WebSocketProtocol.B0_FLAG_RSV2}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogsActivity$main$2$1 extends SuspendLambda implements Function2<BaseActivity.Event, Continuation<? super Unit>, Object> {
    public final /* synthetic */ LogsDesign $design;
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ LogsActivity this$0;

    /* compiled from: LogsActivity.kt */
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            BaseActivity.Event.values();
            int[] iArr = new int[7];
            iArr[1] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogsActivity$main$2$1(LogsDesign logsDesign, LogsActivity logsActivity, Continuation<? super LogsActivity$main$2$1> continuation) {
        super(2, continuation);
        this.$design = logsDesign;
        this.this$0 = logsActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LogsActivity$main$2$1 logsActivity$main$2$1 = new LogsActivity$main$2$1(this.$design, this.this$0, continuation);
        logsActivity$main$2$1.L$0 = obj;
        return logsActivity$main$2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(BaseActivity.Event event, Continuation<? super Unit> continuation) {
        LogsActivity$main$2$1 logsActivity$main$2$1 = new LogsActivity$main$2$1(this.$design, this.this$0, continuation);
        logsActivity$main$2$1.L$0 = event;
        return logsActivity$main$2$1.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0061 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r9.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x001c
            if (r1 == r3) goto L_0x0018
            if (r1 != r2) goto L_0x0010
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            goto L_0x0062
        L_0x0010:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0018:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            goto L_0x0042
        L_0x001c:
            com.tidalab.v2board.clash.design.dialog.InputKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            com.tidalab.v2board.clash.BaseActivity$Event r10 = (com.tidalab.v2board.clash.BaseActivity.Event) r10
            int[] r1 = com.tidalab.v2board.clash.LogsActivity$main$2$1.WhenMappings.$EnumSwitchMapping$0
            int r10 = r10.ordinal()
            r10 = r1[r10]
            if (r10 != r3) goto L_0x0062
            kotlinx.coroutines.Dispatchers r10 = kotlinx.coroutines.Dispatchers.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.IO
            com.tidalab.v2board.clash.LogsActivity$main$2$1$files$1 r1 = new com.tidalab.v2board.clash.LogsActivity$main$2$1$files$1
            com.tidalab.v2board.clash.LogsActivity r4 = r9.this$0
            r5 = 0
            r1.<init>(r4, r5)
            r9.label = r3
            java.lang.Object r10 = com.tidalab.v2board.clash.design.dialog.InputKt.withContext(r10, r1, r9)
            if (r10 != r0) goto L_0x0042
            return r0
        L_0x0042:
            r5 = r10
            java.util.List r5 = (java.util.List) r5
            com.tidalab.v2board.clash.design.LogsDesign r10 = r9.$design
            r9.label = r2
            com.tidalab.v2board.clash.design.adapter.LogFileAdapter r3 = r10.adapter
            com.tidalab.v2board.clash.design.LogsDesign$patchLogs$2 r4 = new com.tidalab.v2board.clash.design.LogsDesign$patchLogs$2
            com.tidalab.v2board.clash.design.adapter.LogFileAdapter r10 = r10.adapter
            r4.<init>(r10)
            com.tidalab.v2board.clash.design.LogsDesign$patchLogs$3 r7 = com.tidalab.v2board.clash.design.LogsDesign$patchLogs$3.INSTANCE
            r6 = 0
            r8 = r9
            java.lang.Object r10 = com.tidalab.v2board.clash.design.dialog.InputKt.patchDataSet(r3, r4, r5, r6, r7, r8)
            if (r10 != r0) goto L_0x005d
            goto L_0x005f
        L_0x005d:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
        L_0x005f:
            if (r10 != r0) goto L_0x0062
            return r0
        L_0x0062:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tidalab.v2board.clash.LogsActivity$main$2$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
