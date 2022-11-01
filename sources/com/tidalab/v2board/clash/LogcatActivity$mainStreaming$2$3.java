package com.tidalab.v2board.clash;

import com.tidalab.v2board.clash.core.model.LogMessage;
import com.tidalab.v2board.clash.design.LogcatDesign;
import com.tidalab.v2board.clash.design.dialog.InputKt;
import com.tidalab.v2board.clash.log.LogcatCache;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$BooleanRef;
/* compiled from: LogcatActivity.kt */
@DebugMetadata(c = "com.tidalab.v2board.clash.LogcatActivity$mainStreaming$2$3", f = "LogcatActivity.kt", l = {120, 122}, m = "invokeSuspend")
/* loaded from: classes.dex */
public final class LogcatActivity$mainStreaming$2$3 extends SuspendLambda implements Function2<Long, Continuation<? super Unit>, Object> {
    public final /* synthetic */ LogcatDesign $design;
    public final /* synthetic */ Ref$BooleanRef $initial;
    public final /* synthetic */ LogcatService $logcat;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LogcatActivity$mainStreaming$2$3(LogcatService logcatService, Ref$BooleanRef ref$BooleanRef, LogcatDesign logcatDesign, Continuation<? super LogcatActivity$mainStreaming$2$3> continuation) {
        super(2, continuation);
        this.$logcat = logcatService;
        this.$initial = ref$BooleanRef;
        this.$design = logcatDesign;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LogcatActivity$mainStreaming$2$3(this.$logcat, this.$initial, this.$design, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public Object invoke(Long l, Continuation<? super Unit> continuation) {
        l.longValue();
        return new LogcatActivity$mainStreaming$2$3(this.$logcat, this.$initial, this.$design, continuation).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            InputKt.throwOnFailure(obj);
            LogcatService logcatService = this.$logcat;
            boolean z = this.$initial.element;
            this.label = 1;
            obj = logcatService.cache.snapshot(z, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else if (i == 1) {
            InputKt.throwOnFailure(obj);
        } else if (i == 2) {
            InputKt.throwOnFailure(obj);
            this.$initial.element = false;
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        LogcatCache.Snapshot snapshot = (LogcatCache.Snapshot) obj;
        if (snapshot == null) {
            return Unit.INSTANCE;
        }
        LogcatDesign logcatDesign = this.$design;
        List<LogMessage> list = snapshot.messages;
        int i2 = snapshot.removed;
        int i3 = snapshot.appended;
        this.label = 2;
        if (logcatDesign.patchMessages(list, i2, i3, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        this.$initial.element = false;
        return Unit.INSTANCE;
    }
}
