package com.tidalab.v2board.clash;

import com.horcrux.svg.PathParser;
import com.tidalab.v2board.clash.design.LogcatDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$mainStreaming$2$2", f = "LogcatActivity.kt", l = {}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatActivity$mainStreaming$2$2 extends SuspendLambda implements Function2<LogcatDesign.Request, Continuation<? super Unit>, Object> {
    public /* synthetic */ Object L$0;
    public final /* synthetic */ LogcatActivity this$0;

    /* compiled from: LogcatActivity.kt */
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            LogcatDesign.Request.values();
            int[] iArr = new int[3];
            iArr[0] = 1;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatActivity$mainStreaming$2$2(LogcatActivity logcatActivity, Continuation<? super LogcatActivity$mainStreaming$2$2> continuation) {
        super(2, continuation);
        this.this$0 = logcatActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LogcatActivity$mainStreaming$2$2 logcatActivity$mainStreaming$2$2 = new LogcatActivity$mainStreaming$2$2(this.this$0, continuation);
        logcatActivity$mainStreaming$2$2.L$0 = obj;
        return logcatActivity$mainStreaming$2$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(LogcatDesign.Request request, Continuation<? super Unit> continuation) {
        LogcatDesign.Request request2 = request;
        Continuation<? super Unit> continuation2 = continuation;
        LogcatActivity logcatActivity = this.this$0;
        if (continuation2 != null) {
            continuation2.getContext();
        }
        Unit unit = Unit.INSTANCE;
        InputKt.throwOnFailure(unit);
        if (WhenMappings.$EnumSwitchMapping$0[request2.ordinal()] == 1) {
            logcatActivity.stopService(PathParser.getIntent(Reflection.getOrCreateKotlinClass(LogcatService.class)));
            logcatActivity.finish();
        }
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        InputKt.throwOnFailure(obj);
        if (WhenMappings.$EnumSwitchMapping$0[((LogcatDesign.Request) this.L$0).ordinal()] == 1) {
            this.this$0.stopService(PathParser.getIntent(Reflection.getOrCreateKotlinClass(LogcatService.class)));
            this.this$0.finish();
        }
        return Unit.INSTANCE;
    }
}
